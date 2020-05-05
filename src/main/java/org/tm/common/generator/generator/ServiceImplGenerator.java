package org.tm.common.generator.generator;

import com.jfinal.kit.StrKit;
import org.tm.common.generator.GenerateConfig;
import org.tm.common.generator.MetaData;

/*
    author: Timor
    date: 2020/3/13 0013
*/
public class ServiceImplGenerator extends BaseGenerator implements IGenerator{

    public ServiceImplGenerator(GenerateConfig config) {
        super(config);
    }

    @Override
    protected boolean isEnabledGenerate(MetaData metaData) {
        return StrKit.notBlank(metaData.getServiceImplName());
    }

    @Override
    protected String getFileWritePath(MetaData metaData) {
        String path = config.getBaseOutputDir() +
                "." + metaData.getServiceImplPackage() +
                "." + metaData.getServiceImplName();
        return path.replace(".", "\\") + ".java";
    }

    @Override
    protected String getTemplate() {
        return config.getServiceImplTemplate();
    }

    @Override
    protected boolean isCoverFile() {
        return config.getServiceImplCoverRule();
    }
}
