package org.tm.doc.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;

import java.math.BigInteger;
import java.util.HashMap;

public class ColumnMeta extends Model<ColumnMeta> {
/*    private String table_catalog; // 表目录
    private String table_schema; // 数据库名
    private String table_name; // 数据表名
    private String column_name; // 字段名称
    private BigInteger ordinal_position; // 字段序号
    private String column_default; // 默认值
    private String is_nullable; // 是否可空
    private String data_type; // 数据类型
    private BigInteger character_maximum_length; // 字符最大长度
    private BigInteger character_octet_length; // 8位字节数
    private BigInteger numeric_precision; // 小数点位数
    private BigInteger numeric_scale; // 数字个数
    private String character_set_name; // 字符集名称
    private String collation_name; // 集合名称
    private String column_type; // 字段类型
    private String column_key; // 主键类型
    private String extra; // 额外信息
    private String privileges; // 字段权限
    private String column_comment; // 字段备注*/

    // 表目录 table_catalog
    public String getTableCatalog() {
        return get("TABLE_CATALOG");
    }

    // 数据库名 table_schema
    public String getTableSchema() {
        return get("TABLE_SCHEMA");
    }

    // 数据表名 table_name
    public String getTableName() {
        return get("TABLE_NAME");
    }

    // 字段名称 column_name
    public String getColumnName() {
        return get("COLUMN_NAME");
    }

    // 数据类型 java_type 对应的java类型
    public String getJavaAttrName() {
        return changeToJavaFiled(getColumnName());
    }

    public static String changeToJavaFiled(String field){
        if(StrKit.notBlank(field)){
            char[] chars = field.toLowerCase().toCharArray();
            StringBuilder sb = new StringBuilder(chars.length);
            int i = 0;
            boolean flag = false;
            do{
                if(chars[i] == '_'){
                    flag = true;
                }else {
                    if(flag){
                        flag = false;
                        sb.append(Character.toUpperCase(chars[i]));
                    }else {
                        sb.append(chars[i]);
                    }
                }
            }while(++i < chars.length);
            return sb.toString();
        }
        return "";
    }

    // 字段序号 ordinal_position
    public BigInteger getOrdinalPosition() {
        return get("ORDINAL_POSITION");
    }

    // 默认值 column_default
    public String getColumnDefault() {
        return get("COLUMN_DEFAULT");
    }

    // 是否可空 is_nullable
    public String getIsNullable() {
        return get("IS_NULLABLE");
    }

    // 是否可空 is_nullable
    public boolean isNullable() {
        return getStr("IS_NULLABLE").equalsIgnoreCase("yes");
    }

    // 是否可空 is_nullable
    public boolean notNullable() {
        return getStr("IS_NULLABLE").equalsIgnoreCase("no");
    }

    // 数据类型 java_type 对应的java类型
    public String getJavaDataType() {
        String type = sqlTypeToJavaType(getDataType());
        if(!type.isEmpty()){
            if(type.lastIndexOf(".") > 0){
                type = type.substring(type.lastIndexOf(".") + 1);
            }
        }else{
            System.out.println(getDataType());
            type = getDataType();
        }
        return type;
    }

    // 数据类型 data_type
    public String getDataType() {
        return get("DATA_TYPE");
    }

    // 字符最大长度 character_maximum_length
    public BigInteger getCharacterMaximumLength() {
        return get("CHARACTER_MAXIMUM_LENGTH");
    }

    // 位字节数 character_octet_length
    public BigInteger getCharacterOctetLength() {
        return get("CHARACTER_OCTET_LENGTH");
    }

    // 小数点位数 numeric_precision
    public BigInteger getNumericPrecision() {
        return get("NUMERIC_PRECISION");
    }

    // 数字个数 numeric_scale
    public BigInteger getNumericScale() {
        return get("NUMERIC_SCALE");
    }

    // 字符集名称 character_set_name
    public String getCharacterSetName() {
        return get("CHARACTER_SET_NAME");
    }

    // 集合名称 collation_name
    public String getCollationName() {
        return get("COLLATION_NAME");
    }

    // 字段类型 column_type
    public String getColumnType() {
        return get("COLUMN_TYPE");
    }

    // 主键类型 column_key
    public String getColumnKey() {
        return get("COLUMN_KEY");
    }

    // 额外信息 extra
    public String getExtra() {
        return get("EXTRA");
    }

    // 字段权限 privileges
    public String getPrivileges() {
        return get("PRIVILEGES");
    }

    // 字段备注 column_comment
    public String getColumnComment() {
        return get("COLUMN_COMMENT");
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private String sqlTypeToJavaType(String type){
        HashMap<String, String> mapping = new HashMap<String, String>(){{
            put("char", "java.lang.String");
            put("varchar", "java.lang.String");
            put("longvarchar", "java.lang.String");
            put("numeric", "java.math.BigDecimal");
            put("decimal", "java.math.BigDecimal");
            put("bit", "java.lang.Boolean");
            put("boolean", "java.lang.Boolean");
            put("tinyint", "java.lang.Byte");
            put("smallint", "java.lang.Short");
            put("integer", "java.lang.Integer");
            put("bigint", "java.lang.Long");
            put("real", "java.lang.Float");
            put("float", "java.lang.Double");
            put("double", "java.lang.Double");
            put("binary", "byte[]");
            put("varbinary", "byte[]");
            put("longvarbinary", "byte[]");
            put("date", "java.sql.Date");
            put("datetime", "java.sql.Date");
            put("time", "java.sql.Time");
            put("timestamp", "java.sql.Timestamp");
            put("clob", "java.lang.Clob");
            put("blob", "java.lang.Blob");
            put("array", "Array");
            put("struct", "Struct");
            put("ref", "Ref");
            put("datalink", "java.net.URL");
        }};
        return mapping.getOrDefault(type, "");
    }

}