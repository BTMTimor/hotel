package org.tm.common.util;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.tm.common.generator.Generator;
import org.tm.doc.model.ColumnMeta;
import org.tm.doc.model.DataBaseMeta;
import org.tm.doc.model.TableMeta;

import javax.sql.DataSource;
import java.util.*;

/*
    author: Timor
    date: 2020/3/24 0024
*/
public class MyDbMetaUtil {
    public static final boolean DEBUG = true;
    public static final String DEFAULT_DB_NAME = "select database()";
    public static final String DB_META = "select * from information_schema.schemata;";
    public static final String DB_NAME = "select schema_name from information_schema.schemata;";
    public static final String TABLE_META = "select * from information_schema.tables where table_schema = ?;";
    public static final String TABLE_NAME = "select table_name from information_schema.tables where table_schema = ?;";
    public static final String COLUMN_META = "select * from information_schema.columns where table_schema = ? and table_name = ?;";
    public static final String COLUMN_NAME = "select column_name from information_schema.columns where table_schema = ? and table_name = ?;";

    ActiveRecordPlugin plugin = null;
    List<String> ignoreRule = new ArrayList<String>(){{
        add(".+_mapping");
    }};

    List<String> ignoreDb = new ArrayList<String>(){{
        add("mysql");
        add("information_schema");
        add("performance_schema");
    }};

    public MyDbMetaUtil() {
    }

    public MyDbMetaUtil(DataSource dataSource) {
        init(dataSource);
    }

    public MyDbMetaUtil(String dbUrl, String username, String password) {
        init(Generator.getDataSource(dbUrl, username, password));
    }

    // 非jfinal项目使用时需要调用此方法
    public void init(DataSource dataSource){
        if(DEBUG) {
            this.plugin = new ActiveRecordPlugin(dataSource);
            this.plugin.start();
        }
    }

    public void destroy(){
        if(null != this.plugin){
            this.plugin.stop();
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public String getDbName(){
        return Db.queryStr(DEFAULT_DB_NAME);
    }

    public List<String> getAllDbName(){
        return Db.query(DB_NAME);
    }

    public DataBaseMeta getDbMeta(String dbName){
        return getAllDbMeta(new ArrayList<String>(){{add(dbName);}}).get(0);
    }

    public List<DataBaseMeta> getAllDbMeta(){
        return getAllDbMeta(getAllDbName());
    }

    public List<DataBaseMeta> getAllDbMeta(List<String> dbName){
        dbName.removeIf((name)->ignoreDb.contains(name));

        List<Record> records = Db.find(DB_META);
        List<DataBaseMeta> dbMeta = new ArrayList<>(records.size());

        for (Record record : records){
            if(dbName.contains(record.getStr("SCHEMA_NAME"))){
                dbMeta.add(new DataBaseMeta()._setAttrs(record.getColumns()));
            }
        }

        return dbMeta;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public List<String> getAllTableName(){
        return Db.query(TABLE_NAME, getDbName());
    }

    public List<String> getAllTableName(String dbName){
        return Db.query(TABLE_NAME, dbName);
    }

    public List<TableMeta> getAllTableMeta(){
        return getAllTableMeta(getDbName(), getAllTableName(getDbName()));
    }

    public List<TableMeta> getAllTableMeta(String dbName){
        return getAllTableMeta(dbName, getAllTableName(dbName));
    }

    public TableMeta getTableMeta(String tableName){
        List<TableMeta> tableMeta = getAllTableMeta(getDbName(), new ArrayList<String>() {{
            add(tableName);
        }});
        return tableMeta.isEmpty() ? new TableMeta() : tableMeta.get(0);
    }

    public TableMeta getTableMeta(String dbName, String tableName){
        List<TableMeta> tableMeta = getAllTableMeta(dbName, new ArrayList<String>() {{
            add(tableName);
        }});
        return tableMeta.isEmpty() ? new TableMeta() : tableMeta.get(0);
    }

    public List<TableMeta> getAllTableMeta(List<String> tableNames){
        tableNames.removeIf((name)->{
            for (String rule : ignoreRule){
                if(name.matches(rule)){
                    return true;
                }
            }
            return false;
        });

        List<TableMeta> tableMetas = new LinkedList<>();
        List<Record> records = Db.find(TABLE_META, getDbName());

        for (Record record : records){
            if(tableNames.contains(record.getStr("TABLE_NAME"))){
                TableMeta tableMeta = new TableMeta();
                tableMeta._setAttrs(record.getColumns());
                tableMeta.setColumnMetas(getAllColumnMeta(getDbName(), tableMeta.getTableName()));

                tableMetas.add(tableMeta);
            }
        }
        return tableMetas;
    }

    public List<TableMeta> getAllTableMeta(String dbName, List<String> tableNames){
        tableNames.removeIf((name)->{
            for (String rule : ignoreRule){
                if(name.matches(rule)){
                    return true;
                }
            }
            return false;
        });

        List<TableMeta> tableMetas = new LinkedList<>();
        List<Record> records = Db.find(TABLE_META, dbName);

        for (Record record : records){
            if(tableNames.contains(record.getStr("TABLE_NAME"))){
                TableMeta tableMeta = new TableMeta();
                tableMeta._setAttrs(record.getColumns());
                tableMeta.setColumnMetas(getAllColumnMeta(dbName, tableMeta.getTableName()));

                tableMetas.add(tableMeta);
            }
        }
        return tableMetas;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public List<String> getAllColumnName(String dbName, String tableName){
        return Db.query(COLUMN_NAME, dbName, tableName);
    }

    public List<ColumnMeta> getAllColumnMeta(String dbName, String tableName){
        List<ColumnMeta> columnMetas = new ArrayList<>();

        List<Record> records = Db.find(COLUMN_META, dbName, tableName);
        for (Record record : records){
            columnMetas.add(new ColumnMeta()._setAttrs(record.getColumns()));
        }
        return columnMetas;
    }
}
