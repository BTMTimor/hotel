package org.tm.common.plugin.arcFacePlugin;

import org.tm.common.plugin.arcFacePlugin.pojo.IUserFace;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import com.google.common.collect.Lists;
import com.jfinal.log.Log;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

/*
    author: Timor
    date: 2020/3/14 0014
*/
public class ArcFaceKit2 {
    private static final Log log = Log.getLog(ArcFaceKit2.class);
    static Map<String, ArcFaceKit2> proMap = new HashMap<>();
    static ArcFaceKit2 arcFaceKitPro;

    protected Integer passRate = 80;
    protected ExecutorService executorService;
    // 底层库算法对象池
    protected GenericObjectPool<FaceEngine> faceEngineObjectPool;

    protected ArcFaceKit2() {}

    static void init(String configName , ArcFaceKit2 arcFaceKit){
        if(proMap.get(configName) != null){
            throw new RuntimeException(configName+"配置的Mail已经存在！");
        }
        proMap.put(configName, arcFaceKit);
        if(ArcFacePlugin.MAIN_CONFIG.equals(configName)){
            ArcFaceKit2.arcFaceKitPro = arcFaceKit;
        }
    }

    protected void destroy(){
        System.out.println("destroy...nothing impl.");
    }

    public static ArcFaceKit2 use(){
        return use(ArcFacePlugin.MAIN_CONFIG);
    }

    public static ArcFaceKit2 use(String configName){
        ArcFaceKit2 arcFaceKit = proMap.get(configName);
        if(arcFaceKit == null){
            throw new RuntimeException("配置的ArcFaceKit不存在: " + configName);
        }
        return arcFaceKit;
    }

    private boolean isSuccess(int code) {
        return code == ErrorInfo.MOK.getValue();
    }

    private static <T> T process(Function<FaceEngine, T> function){
        FaceEngine faceEngine = null;
        T result = null;
        try {
            //获取引擎对象
            faceEngine = (FaceEngine) use().faceEngineObjectPool.borrowObject();
            // 业务处理
            result = function.apply(faceEngine);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (faceEngine != null) {
                use().faceEngineObjectPool.returnObject(faceEngine);
            }
        }
        return result;
    }

    public FaceFeature extractFaceFeature(ImageInfo imageInfo){
        return process((faceEngine)->{
            //人脸检测
            List<FaceInfo> faceInfoList = new ArrayList<>();
            int code = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(),
                    imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);

            if(isSuccess(code) && !faceInfoList.isEmpty()){
                FaceFeature finalFaceFeature = new FaceFeature();
                code = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(),
                        imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), finalFaceFeature);
                if(isSuccess(code)){
                    return finalFaceFeature;
                }
            }
            // 如果程序执行到了这里，说明发生了错误.
            log.info("getFaceFeature failure!");
            return null;
        });
    }

    // 获取最相似的人脸信息
    public IUserFace getMostSimilarFace(ImageInfo imageInfo, List<IUserFace> faceInfoList) throws ExecutionException, InterruptedException {
        List<IUserFace> faces = compareFaceFeature(extractFaceFeature(imageInfo), faceInfoList);
        if (null != faces && !faces.isEmpty()){
            return faces.get(0);
        }
        return null;
    }

    /**
     * 人脸比对
     * @return 按相似度从大到小排序的人脸列表
     */
    public FaceSimilar compareFaceFeature(FaceFeature targetFaceFeature, FaceFeature sourceFaceFeature) {
        return process((faceEngine) -> {
            FaceSimilar faceSimilarResult = new FaceSimilar();
            int resultCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilarResult);
            if (isSuccess(resultCode)){
                return faceSimilarResult;
            }
            return null;
        });
    }

    public List<IUserFace> compareFaceFeature(FaceFeature faceFeature, List<IUserFace> faceInfoList) throws InterruptedException, ExecutionException {
        //识别到的人脸列表
        List<IUserFace> resultFaceInfoList = Lists.newLinkedList();

        //分成1000一组，多线程处理
        List<List<IUserFace>> faceUserInfoPartList = Lists.partition(faceInfoList, 1000);
        CompletionService<List<IUserFace>> completionService = new ExecutorCompletionService(executorService);
        for (List<IUserFace> part : faceUserInfoPartList) {
            completionService.submit(new CompareFaceTask(part, faceFeature));
        }

        for (int i = 0; i < faceUserInfoPartList.size(); i++) {
            List<IUserFace> IUserFaceList = completionService.take().get();
            if (!faceInfoList.isEmpty()) {
                resultFaceInfoList.addAll(IUserFaceList);
            }
        }

        //从大到小排序
        resultFaceInfoList.sort((h1, h2) -> h2.getSimilarValue().compareTo(h1.getSimilarValue()));

        return resultFaceInfoList;
    }

    // 人脸比较的执行实体（多线程执行比较任务的载体类）
    private class CompareFaceTask implements Callable<List<IUserFace>> {
        private List<IUserFace> IUserFaceList;
        private FaceFeature targetFaceFeature;

        public CompareFaceTask(List<IUserFace> IUserFaceList, FaceFeature targetFaceFeature) {
            this.IUserFaceList = IUserFaceList;
            this.targetFaceFeature = targetFaceFeature;
        }

        private int plusHundred(Float value) {
            BigDecimal target = new BigDecimal(value);
            BigDecimal hundred = new BigDecimal("100");
            return target.multiply(hundred).intValue();
        }

        public List<IUserFace> call() {
            return process((faceEngine -> {
                List<IUserFace> faceInfoList = Lists.newLinkedList();//识别到的人脸列表

                for (IUserFace faceUserInfo : IUserFaceList) {
                    FaceFeature sourceFaceFeature = new FaceFeature();
                    sourceFaceFeature.setFeatureData(faceUserInfo.getFaceFeature());
                    FaceSimilar faceSimilar = new FaceSimilar();
                    faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
                    int similarValue = plusHundred(faceSimilar.getScore());//获取相似值
                    if (similarValue > passRate) {
                        //相似值大于配置预期，加入到识别到人脸的列表(会移除人脸数据)
                        faceInfoList.add(faceUserInfo.newInstance(faceUserInfo.getUserId(), faceUserInfo.getFaceId(), similarValue));
                    }
                }
                return faceInfoList;
            }));
        }

    }

}
