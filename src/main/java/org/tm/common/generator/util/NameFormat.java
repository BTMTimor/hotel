package org.tm.common.generator.util;

import com.jfinal.plugin.activerecord.generator.TableMeta;
import org.tm.common.generator.GenerateConfig;

/*
    author: Timor
    date: 2020/3/8 0008
*/
public class NameFormat {
    static String a = "";
    GenerateConfig config;
    private final String tableName;
    private final String modelName;
    private final String basePackage;
    private final String baseModelPackage;
    private final String baseControllerRoute;
    private final String baseApiControllerRoute;

    private NameFormat(TableMeta tableMeta, GenerateConfig config) {
        this.config = config;
        this.modelName = tableMeta.modelName;
        this.tableName = tableMeta.name;
        this.basePackage = config.getBasePackage();
        this.baseModelPackage = config.getBaseModelPackage();
        this.baseControllerRoute = config.getBaseControllerRoute();
        this.baseApiControllerRoute = config.getBaseApiControllerRoute();
    }

    public static NameFormat newFormat(TableMeta tableMeta, GenerateConfig config) {
        return new NameFormat(tableMeta, config);
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public String getModelName(){
        return modelName + config.getModelNameTail();
    }

    public String getBaseModelName(){
        return config.getBaseModelNameHead() +  modelName + config.getBaseModelNameTail();
    }

    public String getControllerName(){
        return modelName + config.getControllerNameTail();
    }

    public String getApiControllerName(){
        return modelName + config.getApiControllerNameTail();
    }

    public String getIServiceName(){
        return config.getServiceNameHead() + modelName + config.getServiceNameTail();
    }

    public String getServiceImplName(){
        return modelName + config.getServiceImplNameTail();
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public String getModelPackage(){
        return basePackage + "." + tableNameToPackage(tableName) + config.getModelPackageName();
    }

    public String getBaseModelPackage(){
        return baseModelPackage;
    }

    public String getControllerPackage(){
        return basePackage + "." + tableNameToPackage(tableName) + config.getControllerPackageName();
    }

    public String getApiControllerPackage(){
        return basePackage + "." + tableNameToPackage(tableName) + config.getApiControllerPackageName();
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    public String getIServicePackage(){
        return basePackage + "." + tableNameToPackage(tableName) + config.getServicePackageName();
    }

    public String getServiceImplPackage(){
        return basePackage + "." + tableNameToPackage(tableName) + config.getServiceImplPackageName();
    }

    public String getControllerRoute(){
        if(baseControllerRoute.equals("/")){
            return baseControllerRoute + tableNameToRoute(tableName);
        }
        return baseControllerRoute + "/" + tableNameToRoute(tableName);
    }

    public String getApiControllerRoute(){
        if(baseApiControllerRoute.equals("/")){
            return baseApiControllerRoute + tableNameToRoute(tableName);
        }
        return baseApiControllerRoute + "/" + tableNameToRoute(tableName);
    }

    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    // 默认不会有XXX_YYY_mapping类似的名字（中间关系表不会生成controller，所以不用处理route转换）
    public String tableNameToRoute(String tableName){
        return tableName.toLowerCase().replace("_", "/");
        /*
        StringBuilder sb = new StringBuilder(tableName.toLowerCase());
        for (int i = 0; i < sb.length(); i++) {
            if ('_' == sb.charAt(i)){
                sb.setCharAt(i, '/');
            }
        }
        return sb.toString();*/
    }

    public String tableNameToPackage(String tableName){
        return tableName.toLowerCase().replace("_", ".");
        /*
        StringBuilder sb = new StringBuilder(tableName.toLowerCase());
        for (int i = 0; i < sb.length(); i++) {
            if ('_' == sb.charAt(i)){
                sb.setCharAt(i, '/');
            }
        }
        return sb.toString();*/
    }

}
