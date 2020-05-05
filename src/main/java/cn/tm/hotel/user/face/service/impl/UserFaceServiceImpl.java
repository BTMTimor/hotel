package cn.tm.hotel.user.face.service.impl;

import cn.tm.hotel.user.face.service.UserFaceService;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.plugin.arcFacePlugin.ArcFaceKit;
import org.tm.common.plugin.arcFacePlugin.pojo.UserFace;

import java.util.List;
import java.util.concurrent.ExecutionException;

/*
    author: Timor
    date: 2020/3/15 0015
*/
@Bean
public class UserFaceServiceImpl extends BaseServiceImpl<UserFace> implements UserFaceService<UserFace>{
    @Override
    public Page<UserFace> findAllByCondition(ICondition condition) {
        return findAllByCondition("user.getAllUserFace", condition);
    }

    @Override
    public List<FaceInfo> detectFaces(ImageInfo image) {
        return null;
    }

    @Override
    public FaceFeature extractFaceFeature(ImageInfo image) {
        return ArcFaceKit.use().extractFaceFeature(image);
    }

    @Override
    public boolean isTheSameUser(FaceFeature feature1, FaceFeature feature2) {
        // todo 这个相似度先写死在这，回来重构
        return ArcFaceKit.use().compareFaceFeature(feature1, feature2).getScore() > 0.8;
    }

    @Override
    public boolean isUserFaceExists(FaceFeature faceFeature) throws ExecutionException, InterruptedException {
        return (null != getUserIdByFaceFuture(faceFeature));
    }

    @Override
    public boolean isUserFaceNotExists(FaceFeature faceFeature) throws ExecutionException, InterruptedException {
        return !isUserFaceExists(faceFeature);
    }

    @Override
    public String getUserIdByFace(ImageInfo imageInfo) throws ExecutionException, InterruptedException {
        return getUserIdByFaceFuture(ArcFaceKit.use().extractFaceFeature(imageInfo));
    }

    @Override
    public String getUserIdByFaceFuture(FaceFeature faceFeature) throws ExecutionException, InterruptedException {
        List<UserFace> allFace = findAll();
        UserFace mostSimilarFace = ArcFaceKit.use().getMostSimilarFace(faceFeature, allFace);
        if(null != mostSimilarFace){
            return mostSimilarFace.getUserId();
        }
        return null;
    }
}
