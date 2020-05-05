package org.tm.common.generator.generator;

/*
    author: Timor
    date: 2020/3/8 0008
*/

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import com.jfinal.template.Engine;
import org.tm.common.generator.GenerateConfig;
import org.tm.common.generator.MetaData;
import org.tm.common.generator.util.MyFileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseGenerator{
    protected Engine engine;
    protected GenerateConfig config;

    public BaseGenerator(GenerateConfig config) {
        this.config = config;
        initEngine();
    }

    protected void initEngine() {
        this.engine = new Engine();
        this.engine.setToClassPathSourceFactory();
        this.engine.addSharedMethod(new StrKit());
    }

    protected abstract boolean isEnabledGenerate(MetaData metaData);

    protected abstract String getFileWritePath(MetaData metaData);

    protected abstract String getTemplate();

    protected abstract boolean isCoverFile();

    public void generate(List<TableMeta> tableMetas, HashMap<String, MetaData> metaData) {
        HashMap<String, String> data = new HashMap<>();
        for (TableMeta tableMeta : tableMetas) {
            MetaData meta = metaData.get(tableMeta.name);
            if(null != meta && isEnabledGenerate(meta)){
                data.put(getFileWritePath(meta),
                    generateContent(tableMeta, meta));
            }

        }
//        config.
        writeToFile(data);
    }

    protected String generateContent(TableMeta tableMeta, MetaData metaData){
        return generateContent(new HashMap<>(), tableMeta, metaData);
    }

    protected String generateContent(Map<String, Object> map, TableMeta tableMeta, MetaData metaData){
        Kv data = Kv.by("metaData", metaData);
        data.set("tableMeta", tableMeta);
        data.set("config", config);
        data.putAll(map);
        return engine.getTemplate(getTemplate()).renderToString(data);
    }

    protected void writeToFile(Map<String, String> fileMetas) {
        try{
            for (String key : fileMetas.keySet()) {
                writeToFile(key, fileMetas.get(key));
            }
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    protected void writeToFile(String path, String content) throws IOException {
        File dir = new File(path.substring(0, path.lastIndexOf("\\")));
        if (!dir.exists() && isCoverFile()) {
            dir.mkdirs();
        }

        File file = new File(path);
        if (!file.exists() || isCoverFile()) {
            MyFileUtil.writeStringToFile(content, path);
        }
    }

}
