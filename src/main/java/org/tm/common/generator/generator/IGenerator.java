package org.tm.common.generator.generator;

import com.jfinal.plugin.activerecord.generator.TableMeta;
import org.tm.common.generator.MetaData;

import java.util.HashMap;
import java.util.List;

/*
    author: Timor
    date: 2020/3/13 0013
*/
public interface IGenerator {
    void generate(List<TableMeta> tableMetas, HashMap<String, MetaData> metaData);
}
