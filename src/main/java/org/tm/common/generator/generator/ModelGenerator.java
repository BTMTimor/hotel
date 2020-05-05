package org.tm.common.generator.generator;

import com.jfinal.kit.StrKit;
import org.tm.common.generator.GenerateConfig;
import org.tm.common.generator.MetaData;

/*
    author: Timor
    date: 2020/3/8 0008
*/
public class ModelGenerator extends BaseGenerator implements IGenerator{

    public ModelGenerator(GenerateConfig config) {
        super(config);
    }

    @Override
    protected boolean isEnabledGenerate(MetaData metaData) {
        return StrKit.notBlank(metaData.getModelName());
    }

    @Override
    protected String getFileWritePath(MetaData metaData) {
        String path = config.getBaseOutputDir() +
                "." + metaData.getModelPackage() +
                "." + metaData.getModelName();
        return path.replace(".", "\\") + ".java";
    }

    @Override
    protected String getTemplate() {
        return config.getModelTemplate();
    }

    @Override
    protected boolean isCoverFile() {
        return config.getModelCoverRule();
    }
}
