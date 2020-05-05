package org.tm.common.plugin.arcFacePlugin.pojo;

public interface IUserFace{
    String getFaceId();

    String getUserId();

    Integer getSimilarValue();

    byte[] getFaceFeature();

    IUserFace newInstance(String userId, String faceId, int similarValue);
}
