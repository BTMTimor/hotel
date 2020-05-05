package org.tm.common.generator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.tm.common.generator.util.MyTimeUtil;

/*
    author: Timor
    date: 2020/3/13 0013
*/
class DefaultGenerateConfig {

    public DefaultGenerateConfig() {}
    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    protected JSONObject getCommonConfig(){
        JSONObject base = new JSONObject();
        base.put("baseOutputDir", "D:\\MyProjects\\temp");
        base.put("baseControllerRoute", "/");
        base.put("baseApiControllerRoute", "/api/v0.1");
        base.put("basePackage", "com.tm.hotel");
        base.put("baseModelPackage", "com.tm.hotel.common.code");
        return base;
    }

    protected JSONObject getGenerateRuleConfig(){
        JSONObject config = new JSONObject();

        config.put("baseRule", GenerateRule.GENERATE_ALL.getRule());

        config.put("model", GenerateRule.GENERATE_NOT_EXIST.getRule());
        config.put("baseModel", GenerateRule.GENERATE_EXIST.getRule());

        config.put("service", GenerateRule.GENERATE_NOT_EXIST.getRule());
        config.put("serviceImpl", GenerateRule.GENERATE_NOT_EXIST.getRule());

        config.put("controller", GenerateRule.GENERATE_NOT_EXIST.getRule());
        config.put("apiController", GenerateRule.GENERATE_NOT_EXIST.getRule());
        return config;
    }

    protected JSONObject getGenerateConfig(){
        JSONObject config = new JSONObject();
        config.put("model", true);
        config.put("baseModel", true);

        config.put("service", true);
        config.put("serviceImpl", true);

        config.put("controller", true);
        config.put("apiController", true);
        return config;
    }

    protected JSONObject getTemplateConfig(){
        JSONObject template = new JSONObject();
        template.put("apiController", "ApiController.jf");
        template.put("controller", "Controller.jf");
        template.put("service", "Service.jf");
        template.put("serviceImpl", "ServiceImpl.jf");
        template.put("model", "Model,jf");
        template.put("baseModel", "BaseModel.jf");
        return template;
    }

    protected JSONObject getDependencyConfig(){
        JSONObject base = new JSONObject();
        base.put("apiResult", "ApiResult.java");
        base.put("baseModel", "BaseModel.java");
        base.put("baseController", "BaseController.java");
        base.put("baseService", "BaseService.java");
        base.put("baseServiceImpl", "BaseServiceImpl.java");
        base.put("baseApiController", "BaseApiController.java");
        return base;
    }

    protected JSONObject getIgnoreConfig(){
        JSONObject config = new JSONObject();
        config.put("rule", getIgnoreRules());
        config.put("tables", getIgnoreTables());
        return config;
    }

    protected JSONArray getIgnoreTables(){
        JSONArray config = new JSONArray();
        config.add("abc");
        return config;
    }

    protected JSONArray getIgnoreRules(){
        JSONArray config = new JSONArray();
        config.add(".+_mapping");
        return config;
    }

    public JSONObject generate() {
        JSONObject defaultConfig = new JSONObject();
        long usedTime = MyTimeUtil.getExecuteTime(()->{
            defaultConfig.put("common", getCommonConfig());
            defaultConfig.put("generate", getGenerateConfig());
            defaultConfig.put("generateRule", getGenerateRuleConfig());
            defaultConfig.put("template", getTemplateConfig());
            defaultConfig.put("ignore", getIgnoreConfig());
            defaultConfig.put("dependency", getDependencyConfig());
        });
        System.out.println("Generate MappingJSONFile complete in " + usedTime + " seconds.");
        return defaultConfig;
    }

}
