package org.tm.common.generator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.dialect.Dialect;
import com.jfinal.plugin.activerecord.generator.DataDictionaryGenerator;
import com.jfinal.plugin.activerecord.generator.MappingKitGenerator;
import com.jfinal.plugin.activerecord.generator.MetaBuilder;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import com.jfinal.plugin.druid.DruidPlugin;
import org.tm.common.generator.generator.*;
import org.tm.common.generator.util.MyFileUtil;
import org.tm.common.generator.util.MyTimeUtil;
import org.tm.common.generator.util.NameFormat;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    author: Timor
    date: 2020/3/9 0009
*/
public class Generator{
    private List<TableMeta> tableMetas = null;
    protected Dialect dialect;
    protected GenerateConfig configs;
    protected MetaBuilder metaBuilder;
    protected BaseModelGenerator baseModelGenerator;
    protected ModelGenerator modelGenerator;
    protected ServiceGenerator serviceGenerator;
    protected ServiceImplGenerator serviceImplGenerator;
    protected ControllerGenerator controllerGenerator;
    protected ApiControllerGenerator apiControllerGenerator;
    protected MappingKitGenerator mappingKitGenerator;
    protected DataDictionaryGenerator dataDictionaryGenerator;
    protected boolean generateDataDictionary;

    public Generator(DataSource dataSource, String configFilename) throws IOException {
        this(dataSource, MyFileUtil.getFileToJSONObject(configFilename));
    }

    public Generator(DataSource dataSource, JSONObject config) {
        this.dialect = null;
        this.generateDataDictionary = false;
        if (dataSource == null) {
            throw new IllegalArgumentException("dataSource can not be null.");
        }else {
            this.metaBuilder = new MetaBuilder(dataSource);
            this.configs = new GenerateConfig(config);
            this.mappingKitGenerator = null;
            this.dataDictionaryGenerator = null;
            initGenerators(configs);
        }
    }

    protected void initGenerators(GenerateConfig config){
        if(configs.needGenerateBaseModel()){
            baseModelGenerator = new BaseModelGenerator(config);
        }
        if(configs.needGenerateModel()){
            modelGenerator = new ModelGenerator(config);
        }
        if(configs.needGenerateService()){
            serviceGenerator = new ServiceGenerator(config);
        }
        if(configs.needGenerateServiceImpl()){
            serviceImplGenerator = new ServiceImplGenerator(config);
        }
        if(configs.needGenerateController()){
            controllerGenerator = new ControllerGenerator(config);
        }
        if(configs.needGenerateApiController()){
            apiControllerGenerator = new ApiControllerGenerator(config);
        }
    }

    protected List<TableMeta> getTableMates() {
        this.changeDialect();

        if(null == this.tableMetas){
            this.tableMetas = this.metaBuilder.build();
        }
        if (tableMetas.size() == 0){
            System.out.println("TableMeta 数量为0.");
        }
        return this.tableMetas;
    }

    protected void changeDialect(){
        if (this.dialect != null) {
            this.metaBuilder.setDialect(this.dialect);
        }
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    protected List<JSONObject> getMappingRule(List<TableMeta> tableMetas){
        ArrayList<JSONObject> metas = new ArrayList<>();
        for (TableMeta tablemeta : tableMetas) {
            JSONObject mappingRule = new JSONObject();
            NameFormat nameFmt = NameFormat.newFormat(tablemeta, configs);
            mappingRule.put("tableName", tablemeta.name);

            mappingRule.put("modelName", tablemeta.modelName);
            mappingRule.put("modelPackage", nameFmt.getModelPackage());
            mappingRule.put("baseModelName", tablemeta.baseModelName);
            mappingRule.put("baseModelPackage", nameFmt.getBaseModelPackage());

            mappingRule.put("servicePackage", nameFmt.getIServicePackage());
            mappingRule.put("serviceName", nameFmt.getIServiceName());
            mappingRule.put("serviceImplName", nameFmt.getServiceImplName());
            mappingRule.put("serviceImplPackage", nameFmt.getServiceImplPackage());

            mappingRule.put("controllerName", nameFmt.getControllerName());
            mappingRule.put("controllerPackage", nameFmt.getControllerPackage());
            mappingRule.put("controllerRoute", nameFmt.getControllerRoute());
            mappingRule.put("apiControllerRoute", nameFmt.getApiControllerRoute());
            mappingRule.put("apiControllerName", nameFmt.getApiControllerName());
            mappingRule.put("apiControllerPackage", nameFmt.getApiControllerPackage());

            metas.add(mappingRule);
        }
        return metas;
    }

    public JSONObject generateMappingConfig(JSONObject json) {
        List<TableMeta> tableMetas = this.getTableMates();
        GenerateConfig config = new GenerateConfig(json);
        // 移除不需要生成代码的表
        tableMetas.removeIf(tableMeta -> config.isIgnored(tableMeta.name));

        JSONObject metadata = new JSONObject();
        long usedTime = MyTimeUtil.getExecuteTime(()->{
            metadata.put("data", getMappingRule(getTableMates()));
        });
        System.out.println("Generate MappingJSONFile complete in " + usedTime + " seconds.");
        return metadata;
    }

    public static JSONObject getDemoConfig(){
        return new DefaultGenerateConfig().generate();
    }

    public void generate(JSONObject metadata) {
        List<TableMeta> tableMetas = this.getTableMates();
        tableMetas.removeIf(tableMeta -> configs.isIgnored(tableMeta.name));
        HashMap<String, MetaData> metaData = new HashMap<>(tableMetas.size());

        JSONArray array = metadata.getJSONArray("data");
        for (int i = 0; i < array.size(); i++) {
            MetaData object = array.getObject(i, MetaData.class);
            metaData.put(object.getTableName(), object);
        }

        long usedTime = MyTimeUtil.getExecuteTime(()->{
            System.out.println("\n[generator] begin...");
            if(configs.needGenerateBaseModel()){
                generateBaseModel(tableMetas, metaData);
            }
            if(configs.needGenerateModel()){
                generateModel(tableMetas, metaData);
            }
            if(configs.needGenerateService()){
                generateService(tableMetas, metaData);
            }
            if(configs.needGenerateServiceImpl()){
                generateServiceImpl(tableMetas, metaData);
            }
            if(configs.needGenerateController()){
                generateController(tableMetas, metaData);
            }
            if(configs.needGenerateApiController()){
                generateApiController(tableMetas, metaData);
            }
        });
        System.out.println("[Generator] complete in " + usedTime + " seconds.\n");
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    protected void generateModel(List<TableMeta> tableMetas, HashMap<String, MetaData> metaData) {
        long usedTime = MyTimeUtil.getExecuteTime(()->{modelGenerator.generate(tableMetas, metaData);});
        System.out.println("Generate Model complete in " + usedTime + " seconds.");
    }

    protected void generateBaseModel(List<TableMeta> tableMetas, HashMap<String, MetaData> metaData) {
        long usedTime = MyTimeUtil.getExecuteTime(()->{baseModelGenerator.generate(tableMetas, metaData);});
        System.out.println("Generate BaseModel complete in " + usedTime + " seconds.");
    }

    protected void generateService(List<TableMeta> tableMetas, HashMap<String, MetaData> metaData) {
        long usedTime = MyTimeUtil.getExecuteTime(()->{serviceGenerator.generate(tableMetas, metaData);});
        System.out.println("Generate Service complete in " + usedTime + " seconds.");
    }

    protected void generateServiceImpl(List<TableMeta> tableMetas, HashMap<String, MetaData> metaData) {
        long usedTime = MyTimeUtil.getExecuteTime(()->{serviceImplGenerator.generate(tableMetas, metaData);});
        System.out.println("Generate ServiceImpl complete in " + usedTime + " seconds.");
    }

    protected void generateController(List<TableMeta> tableMetas, HashMap<String, MetaData> metaData) {
        long usedTime = MyTimeUtil.getExecuteTime(()->{controllerGenerator.generate(tableMetas, metaData);});
        System.out.println("Generate Controller complete in " + usedTime + " seconds.");
    }

    protected void generateApiController(List<TableMeta> tableMetas, HashMap<String, MetaData> metaData) {
        long usedTime = MyTimeUtil.getExecuteTime(()->{apiControllerGenerator.generate(tableMetas, metaData);});
        System.out.println("Generate ApiController complete in " + usedTime + " seconds.");
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public static DataSource getDataSource(String url, String username, String password) {
        DruidPlugin druidPlugin = new DruidPlugin(url,username, password);
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

}
