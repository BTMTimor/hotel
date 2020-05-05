package org.tm.common.plugin.arcFacePlugin;

import com.jfinal.kit.LogKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.IPlugin;

/*
    author: Timor
    date: 2020/3/8 0008
*/
public class ArcFacePlugin implements IPlugin {
    static final String MAIN_CONFIG = "main";
    String configName = MAIN_CONFIG;
    String configFile;

    public ArcFacePlugin(String configFile) {
        this(MAIN_CONFIG, configFile);
    }

    public ArcFacePlugin(String configName, String configFile) {
        this.configName = configName;
        this.configFile = configFile;
    }

    @Override
    public boolean start() {
        Prop prop = PropKit.useFirstFound(configFile);
        checkConfig(prop);
        ArcFaceKit.init(configName, FaceKitFactory.newArcFaceKit(EngineConfig.newInstance(prop)));
        return true;
    }

    public void checkConfig(Prop config){
        // sdk相关配置
        if(StrKit.isBlank(config.get("arcface.sdk-lib-path"))){
            throw new RuntimeException("未设置SDK路径");
        }
        if(StrKit.isBlank(config.get("arcface.app-id"))){
            throw new RuntimeException("未设置SDK appID");
        }
        if(StrKit.isBlank(config.get("arcface.sdk-key"))){
            throw new RuntimeException("未设置SDK key");
        }

        // 人脸引擎相关配置
        if(StrKit.isBlank(config.get("arcface.thread-pool-size"))){
            LogKit.warn("未设置协议虹软线程池大小，使用默认值: 5");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.detectMode"))){
            LogKit.warn("未设置arcface.faceEngine.detectMode，使用默认值:"+"1");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.detectFaceOrientPriority"))){
            LogKit.warn("未设置arcface.faceEngine.detectFaceOrientPriority，使用默认值:"+"0");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.detectFaceScaleVal"))){
            LogKit.warn("未设置arcface.faceEngine.detectFaceScaleVal，使用默认值:"+"32");
        }

        // 人脸检测属性设置
        if(StrKit.isBlank(config.get("arcface.faceEngine.supportFaceDetect"))){
            LogKit.warn("未设置arcface.faceEngine.supportFaceDetect，使用默认值:"+"true");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.supportFaceRecognition"))){
            LogKit.warn("未设置arcface.faceEngine.supportFaceRecognition，使用默认值:"+"true");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.supportAge"))){
            LogKit.warn("未设置arcface.faceEngine.supportAge，使用默认值:"+"true");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.supportGender"))){
            LogKit.warn("未设置arcface.faceEngine.supportGender，使用默认值:"+"true");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.supportFace3dAngle"))){
            LogKit.warn("未设置arcface.faceEngine.supportFace3dAngle，使用默认值:"+"true");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.supportLiveness"))){
            LogKit.warn("未设置arcface.faceEngine.supportLiveness，使用默认值:"+"true");
        }
        if(StrKit.isBlank(config.get("arcface.faceEngine.supportIRLiveness"))){
            LogKit.warn("未设置arcface.faceEngine.supportIRLiveness，使用默认值:"+"true");
        }
    }

    @Override
    public boolean stop() {
        ArcFaceKit.use(configName).destroy();
        return true;
    }
}
