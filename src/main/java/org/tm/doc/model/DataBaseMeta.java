package org.tm.doc.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.ArrayList;
import java.util.List;

/*
    author: Timor
    date: 2020/3/26 0026
*/
public class DataBaseMeta extends Model <DataBaseMeta>{
    private List<TableMeta> tableMetas = new ArrayList<>();

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public List<TableMeta> getTableMetas() {
        return tableMetas;
    }

    public void setTableMetas(List<TableMeta> tableMetas) {
        this.tableMetas = tableMetas;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // 不清楚，目录名称？
    public String getCatalogName() {
        return get("CATALOG_NAME");
    }

    // 获取数据库名称
    public String getSchemaName() {
        return get("SCHEMA_NAME");
    }

    // 获取默认字符集
    public String getDefaultCharacterSetName() {
        return get("DEFAULT_CHARACTER_SET_NAME");
    }

    // 获取，默认集合名称
    public String getDefaultCollationName() {
        return get("DEFAULT_COLLATION_NAME");
    }

    // 获取sql文件存储路径
    public String getSqlPath() {
        return get("SQL_PATH");
    }

}
