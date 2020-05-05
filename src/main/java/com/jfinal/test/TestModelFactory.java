package com.jfinal.test;

import com.jfinal.plugin.activerecord.Model;
import io.jboot.db.annotation.Table;
import lombok.SneakyThrows;
import org.tm.doc.model.ColumnMeta;
import org.tm.doc.model.TableMeta;
import org.tm.common.util.MyDbMetaUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    author: Timor
    date: 2020/4/1 0001
*/
public class TestModelFactory {
    private static Map<String, TableMeta> modelMeta = null;

    private static TableMeta getTableMeta(String tableName){
        if(null == modelMeta){
            modelMeta = new HashMap<>();
            List<TableMeta> allTableMeta = new MyDbMetaUtil().getAllTableMeta();
            for (TableMeta tableMate : allTableMeta) {
                modelMeta.put(tableMate.getTableName(), tableMate);
            }
        }
        if(modelMeta.containsKey(tableName)){
            return modelMeta.get(tableName);
        }
        throw new IllegalArgumentException("[error] 数据库表("+ tableName +")不存在！");
    }

    public static String getModelMappingTableName(Class<?> modelClazz) {
        Table annotation = modelClazz.getAnnotation(Table.class);
        if (null == annotation) {
            throw new IllegalArgumentException("model没有使用@Table注解");
        }
        return annotation.tableName();
    }

        // new 一个空的指定model
    @SneakyThrows
    public static <T extends Model<T>> T newEmptyModel(Class<T> modelClazz){
        return modelClazz.newInstance();
    }

    // new 一个填充完所有字段的model
    public static <T extends Model<T>> T newLestFieldModel(Class<T> modelClazz){
        TableMeta tableMeta = getTableMeta(getModelMappingTableName(modelClazz));
        T model = newEmptyModel(modelClazz);
        for (ColumnMeta col : tableMeta.getColumnMetas()){
            model.set(col.getColumnName(), ValueFactory.getValue(col.getJavaDataType()));
        }
        return model;
    }

    // new 一个填充完必填字段的model
    public static <T extends Model<T>> T newFullFieldModel(Class<T> modelClazz){
        TableMeta tableMeta = getTableMeta(getModelMappingTableName(modelClazz));
        T model = newEmptyModel(modelClazz);
        for (ColumnMeta col : tableMeta.getNotNullableColumnMetas()){
            model.set(col.getColumnName(), ValueFactory.getValue(col.getJavaDataType()));
        }
        return model;
    }

    // new 一个缺失必要字段的model
    public static <T extends Model<T>> T newMissingFieldModel(Class<T> modelClazz) {
        TableMeta tableMeta = getTableMeta(getModelMappingTableName(modelClazz));
        T model = newEmptyModel(modelClazz);
        for (ColumnMeta col : tableMeta.getNotNullableColumnMetas()) {
            // 一半的可能不填充字段
            if(Math.random() > 0.5){
                model.set(col.getColumnName(), ValueFactory.getValue(col.getJavaDataType()));
            }
        }
        return model;
    }

}
