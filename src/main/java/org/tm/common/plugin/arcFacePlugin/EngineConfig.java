package org.tm.common.plugin.arcFacePlugin;

import com.jfinal.kit.Prop;
import lombok.Getter;

/*
    author: Timor
    date: 2020/3/15 0015
*/
@Getter
class EngineConfig {
    protected String sdkLibPath;
    private String appId;
    private String sdkKey;
    protected int threadPoolSize;
    private int passRate = 80;

    // 人脸识别模式：视频模式: 0; 图像模式:1
    private int detectMode;
    // 人脸检测角度(逆时针),推荐0(正的图片)
    private int detectFaceOrientPriority;
    // 识别的最小人脸比例 = 图片长边 / 人脸框长边的比值
    private int detectFaceScaleVal;
    // 是否支持人脸检测功能
    private boolean supportFaceDetect;
    // 是否支持人脸识别功能
    private boolean supportFaceRecognition;
    // 是否支持年龄检测功能
    private boolean supportAge;
    // 是否支持性别检测功能
    private boolean supportGender;
    // 是否支持3D检测功能
    private boolean supportFace3dAngle;
    // 是否支持RGB活体检测功能
    private boolean supportLiveness;
    // 是否支持IR活体检测功能
    private boolean supportIRLiveness;

    protected static EngineConfig newInstance(Prop config) {
        EngineConfig engineConfig = new EngineConfig();
        engineConfig.sdkLibPath = config.get("arcface.sdk-lib-path");
        engineConfig.appId = config.get("arcface.app-id");
        engineConfig.sdkKey = config.get("arcface.sdk-key");

        engineConfig.threadPoolSize = config.getInt("arcface.thread-pool-size", 5);
        engineConfig.detectMode = config.getInt("arcface.faceEngine.detectMode", 1);
        engineConfig.detectFaceOrientPriority = config.getInt("arcface.faceEngine.detectFaceOrientPriority", 0);
        engineConfig.detectFaceScaleVal = config.getInt("arcface.faceEngine.detectFaceScaleVal", 32);

        engineConfig.supportFaceDetect = config.getBoolean("arcface.faceEngine.supportFaceDetect", true);
        engineConfig.supportFaceRecognition = config.getBoolean("arcface.faceEngine.supportFaceRecognition", true);
        engineConfig.supportAge = config.getBoolean("arcface.faceEngine.supportAge", true);
        engineConfig.supportGender = config.getBoolean("arcface.faceEngine.supportGender", true);
        engineConfig.supportFace3dAngle = config.getBoolean("arcface.faceEngine.supportFace3dAngle", true);
        engineConfig.supportLiveness = config.getBoolean("arcface.faceEngine.supportLiveness", true);
        engineConfig.supportIRLiveness = config.getBoolean("arcface.faceEngine.supportIRLiveness", true);

        return engineConfig;
    }

}
