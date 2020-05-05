package org.tm.common.generator.generator;

import com.jfinal.kit.StrKit;
import org.tm.common.generator.GenerateConfig;
import org.tm.common.generator.MetaData;

/*
    author: Timor
    date: 2020/3/13 0013
*/
public class ControllerGenerator extends BaseGenerator  implements IGenerator{

    public ControllerGenerator(GenerateConfig config) {
        super(config);
    }

    @Override
    protected boolean isEnabledGenerate(MetaData metaData) {
        return StrKit.notBlank(metaData.getControllerName());
    }

    @Override
    protected String getFileWritePath(MetaData metaData) {
        String path = config.getBaseOutputDir() +
                "." + metaData.getControllerPackage() +
                "." + metaData.getControllerName();
        return path.replace(".", "\\") + ".java";
    }

    @Override
    protected String getTemplate() {
        return config.getControllerTemplate();
    }

    @Override
    protected boolean isCoverFile() {
        return config.getControllerCoverRule();
    }

}