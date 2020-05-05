package org.tm.common.generator.generator;

import com.jfinal.kit.JavaKeyword;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import org.tm.common.generator.GenerateConfig;
import org.tm.common.generator.MetaData;

import java.util.HashMap;
import java.util.Map;

public class BaseModelGenerator extends BaseGenerator implements IGenerator{

    public BaseModelGenerator(GenerateConfig config) {
        super(config);
        operateEngine();
    }

    public void operateEngine(){
        Map<String, String> getterTypeMap = new HashMap<String, String>() {
            {
                this.put("java.lang.String", "getStr");
                this.put("java.lang.Integer", "getInt");
                this.put("java.lang.Long", "getLong");
                this.put("java.lang.Double", "getDouble");
                this.put("java.lang.Float", "getFloat");
                this.put("java.lang.Short", "getShort");
                this.put("java.lang.Byte", "getByte");
            }
        };
        this.engine.addSharedObject("getterTypeMap", getterTypeMap);
        this.engine.addSharedObject("javaKeyword", JavaKeyword.me);
    }

    @Override
    protected boolean isEnabledGenerate(MetaData metaData) {
        return StrKit.notBlank(metaData.getBaseModelName());
    }

    @Override
    protected String getFileWritePath(MetaData metaData) {
        String path = config.getBaseOutputDir() +
                "." + config.getBaseModelPackage() +
                "." + metaData.getBaseModelName();
        return path.replace(".", "\\") + ".java";
    }

    @Override
    protected String getTemplate() {
        return config.getBaseModelTemplate();
    }

    @Override
    protected String generateContent(TableMeta tableMeta, MetaData metaData){
        Map kv = new HashMap<String, String>();
        kv.put("generateChainSetter", config.getGenerateChainSetter());
        return generateContent(kv, tableMeta, metaData);
    }

    @Override
    protected boolean isCoverFile() {
        return config.getBaseModelCoverRule();
    }
}

