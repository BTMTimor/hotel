package cn.tm.hotel.user.face.controller;

import cn.tm.hotel.user.face.service.UserFaceService;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.NotAction;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.ImageKit;
import com.jfinal.kit.StrKit;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.api.result.ResultCode;
import org.tm.common.base.controller.BaseController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import org.tm.common.plugin.arcFacePlugin.pojo.UserFace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/*
    author: Timor
    date: 2020/3/14 0014
*/
@ApiController
@RequestMapping("/user/face")
public class UserFaceController extends BaseController<UserFace> {
    static Logger logger = LoggerFactory.getLogger(UserFaceController.class);

    @Inject
    private UserFaceService<UserFace> service;

    @ActionKey("/user/face/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/user/face/getUserByFace")
    public ApiResult getUserByFace(String faceInfo) {
        return faceProcessor(faceInfo, (imageInfo)->{
            String userId = null;
            try {
                userId = service.getUserIdByFace(imageInfo);
            } catch (ExecutionException | InterruptedException e) {
                logger.info("getUserIdByFace failure.");
                ApiResult.failure(ResultCode.ERROR, "获取人脸信息失败！");
            }
            return ApiResult.success(userId);
        });
    }

    // 人脸检测
    private List<FaceInfo> detectFaces(String image) throws IOException {
        ImageInfo imageInfo = base64ToImageInfo(image);

        return service.detectFaces(imageInfo);
    }

    // 不支持jpeg
    // 用户上传人脸照片，上传后可以使用人脸登录
    public ApiResult upload(String faceInfo, String faceInfo2) {
        if(StrKit.isBlank(faceInfo) || StrKit.isBlank(faceInfo2)){
            return ApiResult.failure(ResultCode.PARAM_IS_MISSING, "请上传两张人脸照片！");
        }

        List<String> faceInfoList = new ArrayList<String>(2){{
            add(faceInfo); add(faceInfo2);
        }};

        return faceProcessor(faceInfoList, (imageInfoList)->{
            // 这里可以不判断
            if(imageInfoList.size() < 2){
                return ApiResult.error("未知错误！请稍后重试或联系管理员！");
            }

            FaceFeature faceFeature1 = service.extractFaceFeature(imageInfoList.get(0));
            FaceFeature faceFeature2 = service.extractFaceFeature(imageInfoList.get(1));
            if(null == faceFeature1 || null == faceFeature2){
                return ApiResult.failure("获取人脸特征失败！请重试！");
            }

            try {
                if(service.isUserFaceNotExists(faceFeature1)){
                    return ApiResult.failure("人脸已存在，请勿重复添加！");
                }
            } catch (ExecutionException | InterruptedException e) {
                logger.info("getUserIdByFaceFuture failure.");
                return ApiResult.error("服务器出现异常！");
            }

            String userId = getAttr("user_id", "12345");
            saveImageInfoToDisk(imageInfoList, userId);

            if (service.isTheSameUser(faceFeature1, faceFeature2)) {
                // 随机保存一个人脸特征到数据库即可
                return super.add(UserFace.initForFirstUpload(userId, faceFeature1.getFeatureData(), "d:\\temp\\image"));
            }
            return ApiResult.failure("两张照片比对不一致，请确保为同一人后重试。");
        });
    }

    // 不支持jpeg
    public ApiResult add(String userId, String faceInfo) {
        return faceProcessor(faceInfo, (imageInfo)->{
            FaceFeature faceFeature = service.extractFaceFeature(imageInfo);
            if(null == faceFeature){
                return ApiResult.failure("获取人脸特征失败！");
            }
            try {
                if(service.isUserFaceExists(faceFeature)){
                    return ApiResult.failure("人脸已存在，请勿重复添加！");
                }
            } catch (ExecutionException | InterruptedException e) {
                logger.info("getUserIdByFaceFuture failure.");
                return ApiResult.error("服务器出现异常！");
            }

            saveImageInfoToDisk(imageInfo, userId);
            return super.add(UserFace.initForFirstUpload(getAttr("user_id", "12345"), faceFeature.getFeatureData(), "d:\\temp\\image"));
        });
    }

    // 更新人脸特征信息（需管理员权限）
    public ApiResult update(String userId, String faceInfo) {
        return faceProcessor(faceInfo, (imageInfo) -> {
            FaceFeature faceFeature = service.extractFaceFeature(imageInfo);
            // 只要提取人脸特征成功就直接更新，这里不对比人脸特征（或者相似度调低一点）
            if (null == faceFeature) {
                return ApiResult.failure("获取人脸特征失败！");
            }
            saveImageInfoToDisk(imageInfo, userId);
            return super.update(UserFace.initForFirstUpload(getAttr("user_id", "12345"), faceFeature.getFeatureData(), "d:\\temp\\image"));
        });
    }

    @ActionKey("/user/face/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    public ApiResult list(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Override
    protected BaseService<UserFace> getService() {
        return service;
    }

    // 通用人脸处理
    @NotAction
    public static ApiResult faceProcessor(String faceInfo, Function<ImageInfo, ApiResult> processor){
        if (StrKit.isBlank(faceInfo)) {
            return ApiResult.failure(ResultCode.PARAM_IS_BLANK, "请上传人脸图片！");
        }

        ImageInfo imageInfo = base64ToImageInfo(faceInfo);
        if (null == imageInfo) {
            return ApiResult.failure(ResultCode.PARAM_IS_INVALID, "图片已损坏！");
        }

        return processor.apply(imageInfo);
    }

    // 通用批量人脸处理
    private ApiResult faceProcessor(List<String> faceInfoList, Function<List<ImageInfo>, ApiResult> processor){
        if(null == faceInfoList || faceInfoList.isEmpty()){
            return ApiResult.failure(ResultCode.PARAM_IS_BLANK, "请上传人脸图片！");
        }
        List<ImageInfo> imageInfoList = new ArrayList<ImageInfo> (faceInfoList.size());

        for (String faceInfo : faceInfoList) {
            if (StrKit.isBlank(faceInfo)) {
                return ApiResult.failure(ResultCode.PARAM_IS_BLANK, "请上传人脸图片！");
            }

            ImageInfo imageInfo = base64ToImageInfo(faceInfo);
            if (null == imageInfo) {
                return ApiResult.failure(ResultCode.PARAM_IS_INVALID, "图片已损坏！");
            }

            imageInfoList.add(imageInfo);
        }

        return processor.apply(imageInfoList);
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private static ImageInfo base64ToImageInfo(String base64Str){
        if (StrKit.notBlank(base64Str)) {
            byte[] decode = Base64.decode(base64Process(base64Str));
            return ImageFactory.getRGBData(decode);
        }
        return null;
    }

    private static String base64Process(String base64Str) {
        String photoBase64 = base64Str.substring(0, 30).toLowerCase();
        int indexOf = photoBase64.indexOf("base64,");
        if (indexOf > 0) {
            base64Str = base64Str.substring(indexOf + 7);
        }
        return base64Str;
    }

    private static String base64Process2(String base64Str) {
        String result = null;
        try {
            result = ImageKit.encodeBase64(base64Str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveImageInfoToDisk(ImageInfo imageInfo, String userId){

    }

    private void saveImageInfoToDisk(List<ImageInfo> imageInfo, String userId){

    }

}
