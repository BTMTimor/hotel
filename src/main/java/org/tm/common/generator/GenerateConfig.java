package org.tm.common.generator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.StrKit;

/*
    author: Timor
    date: 2020/3/13 0013
*/
public class GenerateConfig {
    JSONObject common;
    JSONObject template;
    JSONObject dependency;
    JSONObject ignore;
    JSONObject coverRule;
    JSONObject generate;
    JSONObject packageNameRule;
    JSONObject classNameRule;

    public GenerateConfig(JSONObject config) {
        parseJSONConfig(config);
    }

    public void parseJSONConfig(JSONObject config){
        this.common = config.getJSONObject("common");
        this.template = config.getJSONObject("template");
        this.dependency = config.getJSONObject("dependency");
        this.ignore = config.getJSONObject("ignore");
        this.generate = config.getJSONObject("generate");
        this.coverRule = config.getJSONObject("coverRule");
        this.packageNameRule = config.getJSONObject("packageNameRule");
        this.classNameRule = config.getJSONObject("classNameRule");
    }

    // 有人喜欢把生成代码的配置内容写在代码里，可以去修改那个类
    public static JSONObject getDefaultConfig(){
        return new DefaultGenerateConfig().generate();
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public String getBaseControllerRoute(){
        return common.getString("baseControllerRoute");
    }
    public String getBaseApiControllerRoute(){
        return common.getString("baseApiControllerRoute");
    }
    public String getBaseOutputDir(){
        return common.getString("baseOutputDir");
    }
    public String getBasePackage(){
        return common.getString("basePackage");
    }
    public String getBaseModelPackage(){
        return common.getString("baseModelPackage");
    }
    public String getGenerateChainSetter(){
        return common.getString("generateChainSetter");
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public boolean isIgnored(String tableName){
        JSONArray tables = ignore.getJSONArray("tables");
        if(tables.contains("tableName")){
            return true;
        }
        JSONArray rule = ignore.getJSONArray("rule");
        for (int i = 0; i < rule.size(); i++) {
            if(tableName.matches(rule.getString(i))){
                return true;
            }
        }
        return false;
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public String getBaseModel(){
        return dependency.getString("baseModel");
    }
    public String getBaseService(){
        return dependency.getString("baseService");
    }
    public String getBaseServiceImpl(){
        return dependency.getString("baseServiceImpl");
    }
    public String getBaseController(){
        return dependency.getString("baseController");
    }
    public String getBaseApiController(){
        return dependency.getString("baseApiController");
    }
    public String getApiResult(){
        return dependency.getString("apiResult");
    }
    public String getBaseCondition(){
        return dependency.getString("baseCondition");
    }
    public String getBaseICondition(){
        return dependency.getString("baseICondition");
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public boolean getModelCoverRule(){
        return coverRule.getBoolean("model");
    }
    public boolean getBaseModelCoverRule(){
        return coverRule.getBoolean("baseModel");
    }
    public boolean getServiceCoverRule(){
        return coverRule.getBoolean("service");
    }
    public boolean getServiceImplCoverRule(){
        return coverRule.getBoolean("serviceImpl");
    }
    public boolean getControllerCoverRule(){
        return coverRule.getBoolean("controller");
    }
    public boolean getApiControllerCoverRule(){
        return coverRule.getBoolean("apiController");
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public String getModelTemplate(){
        return template.getString("model");
    }
    public String getBaseModelTemplate(){
        return template.getString("baseModel");
    }
    public String getServiceTemplate(){
        return template.getString("service");
    }
    public String getServiceImplTemplate(){
        return template.getString("serviceImpl");
    }
    public String getControllerTemplate(){
        return template.getString("controller");
    }
    public String getApiControllerTemplate(){
        return template.getString("apiController");
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public boolean needGenerateModel(){
        return generate.getBoolean("model");
    }
    public boolean needGenerateBaseModel(){
        return generate.getBoolean("baseModel");
    }
    public boolean needGenerateService(){
        return generate.getBoolean("service");
    }
    public boolean needGenerateServiceImpl(){
        return generate.getBoolean("serviceImpl");
    }
    public boolean needGenerateController(){
        return generate.getBoolean("controller");
    }
    public boolean needGenerateApiController(){
        return generate.getBoolean("apiController");
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public String getControllerPackageName(){
        if(StrKit.notBlank(packageNameRule.getString("controller"))){
            return packageNameRule.getString("controller");
        }
        return ".controller";
    }
    public String getApiControllerPackageName(){
        if(StrKit.notBlank(packageNameRule.getString("apiController"))){
            return packageNameRule.getString("apiController");
        }
        return ".controller.api";
    }
    public String getServicePackageName(){
        if(StrKit.notBlank(packageNameRule.getString("service"))){
            return packageNameRule.getString("service");
        }
        return ".service";
    }
    public String getServiceImplPackageName(){
        if(StrKit.notBlank(packageNameRule.getString("serviceImpl"))){
            return packageNameRule.getString("serviceImpl");
        }
        return ".service.impl";
    }
    public String getModelPackageName(){
        if(StrKit.notBlank(packageNameRule.getString("model"))){
            return packageNameRule.getString("model");
        }
        return ".model";
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public String getControllerNameTail(){
        if(StrKit.notBlank(classNameRule.getString("controller"))){
            return classNameRule.getString("controller");
        }
        return "Controller";
    }
    public String getApiControllerNameTail(){
        if(StrKit.notBlank(classNameRule.getString("apiController"))){
            return classNameRule.getString("apiController");
        }
        return "ApiController";
    }
    public String getServiceNameHead(){
        if(StrKit.notBlank(classNameRule.getString("serviceHead"))){
            return classNameRule.getString("serviceHead");
        }
        return "";
    }
    public String getServiceNameTail(){
        if(StrKit.notBlank(classNameRule.getString("serviceTail"))){
            return classNameRule.getString("serviceTail");
        }
        return "Service";
    }
    public String getServiceImplNameTail(){
        if(StrKit.notBlank(classNameRule.getString("serviceImpl"))){
            return classNameRule.getString("serviceImpl");
        }
        return "ServiceImpl";
    }
    public String getModelNameTail(){
        if(StrKit.notBlank(classNameRule.getString("model"))){
            return classNameRule.getString("model");
        }
        return "";
    }
    public String getBaseModelNameHead() {
        if(StrKit.notBlank(classNameRule.getString("modelBaseHead"))){
            return classNameRule.getString("modelBaseHead");
        }
        return "Base";
    }
    public String getBaseModelNameTail() {
        if(StrKit.notBlank(classNameRule.getString("modelBaseTail"))){
            return classNameRule.getString("modelBaseTail");
        }
        return "";
    }

}
