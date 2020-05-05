package org.tm.common.plugin.arcFacePlugin.pojo;

import cn.tm.hotel.common.base.model.BaseUserFace;
import io.jboot.db.annotation.Table;

import java.sql.Timestamp;

/*
    author: Timor
    date: 2020/3/14 0014
*/
@Table(tableName = "user_face", primaryKey = "id")
public class UserFace extends BaseUserFace<UserFace> implements IUserFace {
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String FACE_FEATURE = "face_feature";
    public static final String FACE_FILE_PATH = "face_file_path";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

    private int similarValue;

    @Override
    public String getFaceId() {
        return getId();
    }

    public UserFace setFaceId(String id) {
        return setId(id);
    }

    public Integer getSimilarValue() {
        return this.similarValue;
    }

    public void setSimilarValue(Integer similarValue) {
        this.similarValue = similarValue;
    }

    @Override
    public UserFace newInstance(String userId, String faceId, int similarValue) {
        UserFace userFace = new UserFace();
        userFace.setUserId(userId);
        userFace.setFaceId(faceId);
        userFace.setSimilarValue(similarValue);
        return userFace;
    }

    public static UserFace initForFirstUpload(String userId, byte[] faceFuture, String filePath) {
        UserFace userFace = new UserFace();
        userFace.setUserId(userId);
        userFace.setFaceFeature(faceFuture);
        userFace.setFaceFilePath(filePath);
        userFace.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userFace.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return userFace;
    }

    public static UserFace initForUpdate(String userId, byte[] faceFuture, String filePath) {
        return new UserFace().setUserId(userId)
            .setFaceFeature(faceFuture)
            .setFaceFilePath(filePath);
//            .setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }


    @Override
    public String checkForAdd() {
        this.keep(ID, USER_ID, FACE_FEATURE, FACE_FILE_PATH, CREATE_TIME, UPDATE_TIME);
        return super.checkForAdd();
    }

    @Override
    public String checkForUpdate() {
        this.keep(ID, USER_ID, FACE_FEATURE, FACE_FILE_PATH, CREATE_TIME, UPDATE_TIME);
        return super.checkForUpdate();
    }

    @Override
    public String checkFiled() {
        /*if(StrKit.isBlank(getKey())){
            return "XX不能为空！";
        }*/
        return "";
    }
}
