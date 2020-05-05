package cn.tm.hotel.user.face.service;

import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import org.tm.common.base.model.BaseModel;
import org.tm.common.base.service.BaseService;

import java.util.List;
import java.util.concurrent.ExecutionException;

/*
    author: Timor
    date: 2020/3/15 0015
*/
public interface UserFaceService<T extends BaseModel<T>> extends BaseService<T> {
    // 检测人脸
    List<FaceInfo> detectFaces(ImageInfo image);

    FaceFeature extractFaceFeature(ImageInfo image);

    boolean isTheSameUser(FaceFeature feature1, FaceFeature feature2);

    boolean isUserFaceExists(FaceFeature faceFeature) throws ExecutionException, InterruptedException;

    boolean isUserFaceNotExists(FaceFeature faceFeature) throws ExecutionException, InterruptedException;

    // 获取人脸对应的用户id
    String getUserIdByFace(ImageInfo imageInfo) throws ExecutionException, InterruptedException;

    String getUserIdByFaceFuture(FaceFeature faceFeature) throws ExecutionException, InterruptedException;
}
