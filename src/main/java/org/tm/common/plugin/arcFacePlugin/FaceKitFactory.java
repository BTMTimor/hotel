package org.tm.common.plugin.arcFacePlugin;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.concurrent.Executors;

/*
    author: Timor
    date: 2020/3/15 0015
*/
class FaceKitFactory {

    protected static ArcFaceKit newArcFaceKit(EngineConfig config){
        ArcFaceKit arcFaceKit = new ArcFaceKit();

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);

        //功能配置
        engineConfiguration.setFunctionConfiguration(getFunctionConfig(config));

        arcFaceKit.passRate = 80;
        arcFaceKit.executorService = Executors.newFixedThreadPool(config.getThreadPoolSize());
        arcFaceKit.faceEngineObjectPool = new GenericObjectPool(
                new FaceEngineFactory(config, engineConfiguration), getPoolConfig(config));
        return arcFaceKit;
    }

    private static GenericObjectPoolConfig getPoolConfig(EngineConfig config){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(config.getThreadPoolSize());
        poolConfig.setMaxTotal(config.getThreadPoolSize());
        poolConfig.setMinIdle(config.getThreadPoolSize());
        poolConfig.setLifo(false);
        return poolConfig;
    }

    private static FunctionConfiguration getFunctionConfig(EngineConfig config) {
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(config.isSupportAge());
        functionConfiguration.setSupportFace3dAngle(config.isSupportFace3dAngle());
        functionConfiguration.setSupportFaceDetect(config.isSupportFaceDetect());
        functionConfiguration.setSupportFaceRecognition(config.isSupportFaceRecognition());
        functionConfiguration.setSupportGender(config.isSupportGender());
        functionConfiguration.setSupportLiveness(config.isSupportLiveness());
        functionConfiguration.setSupportIRLiveness(config.isSupportIRLiveness());
        return functionConfiguration;
    }

}
