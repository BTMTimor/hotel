package org.tm.doc.model;

import com.jfinal.plugin.activerecord.Model;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/*
    author: Timor
    date: 2020/3/24 0024
*/
public class TableMeta extends Model<TableMeta>{
    private List<ColumnMeta> columnMetas = new ArrayList<>();

    public String modelName() {
        String temp = getTableName().toLowerCase();
        if(temp.length() < 1) return temp;
        StringBuilder sb = new StringBuilder(temp.length());
        sb.append(Character.toUpperCase(temp.charAt(0)));
        int i = 0;
        while (++i < temp.length()){
            if ('_' == temp.charAt(i)){
                if(++i < temp.length()){
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            }else {
                sb.append(temp.charAt(i));
            }
        }
        return sb.toString();
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public List<ColumnMeta> getColumnMetas() {
        return columnMetas;
    }

    public List<ColumnMeta> getNullableColumnMetas() {
        ArrayList<ColumnMeta> nullableColumnMetas = new ArrayList<>(this.columnMetas);
        nullableColumnMetas.removeIf((meta) -> meta.notNullable());
        return nullableColumnMetas;
    }

    public List<ColumnMeta> getNotNullableColumnMetas() {
        ArrayList<ColumnMeta> nullableColumnMetas = new ArrayList<>(this.columnMetas);
        nullableColumnMetas.removeIf((meta) -> meta.isNullable());
        return nullableColumnMetas;
    }

    public void setColumnMetas(List<ColumnMeta> columnMetas) {
        this.columnMetas = columnMetas;
    }

    public String getTableCatalog() {
        return get("TABLE_CATALOG");
    }

    public String getTableSchema() {
        return get("TABLE_SCHEMA");
    }

    public String getTableName() {
        return get("TABLE_NAME");
    }

    public String getTableType() {
        return get("TABLE_TYPE");
    }

    public String getEngine() {
        return get("ENGINE");
    }

    public BigInteger getVersion() {
        return get("VERSION");
    }

    public String getRowFormat() {
        return get("ROW_FORMAT");
    }

    public BigInteger getTableRows() {
        return get("TABLE_ROWS");
    }

    public BigInteger getAvgRowLength() {
        return get("AVG_ROW_LENGTH");
    }

    public BigInteger getDataLength() {
        return get("DATA_LENGTH");
    }

    public BigInteger getMaxDataLength() {
        return get("MAX_DATA_LENGTH");
    }

    public BigInteger getIndexLength() {
        return get("INDEX_LENGTH");
    }

    public BigInteger getDataFree() {
        return get("DATA_FREE");
    }

    public BigInteger getAutoIncrement() {
        return get("AUTO_INCREMENT");
    }

    public Date getCreateTime() {
        return get("CREATE_TIME");
    }

    public Date getUpdateTime() {
        return get("UPDATE_TIME");
    }

    public Date getCheckTime() {
        return get("CHECK_TIME");
    }

    public String getTableCollation() {
        return get("TABLE_COLLATION");
    }

    public BigInteger getChecksum() {
        return get("CHECKSUM");
    }

    public String getCreateOptions() {
        return get("CREATE_OPTIONS");
    }

    public String getTableComment() {
        return get("TABLE_COMMENT");
    }
}
