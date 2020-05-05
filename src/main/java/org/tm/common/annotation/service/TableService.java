package org.tm.common.annotation.service;

import org.tm.common.annotation.TableColDesc;
import org.tm.common.annotation.TableDesc;
import com.jfinal.plugin.activerecord.Record;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/*
    author: Timor
    date: 2020/2/20 0020
*/
public class TableService {
    private final AnnotationService ans;

    public TableService() {
        ans = new AnnotationService(new WebNameFormatter());
    }

    /**
     * 获取Table注解的内容，并映射到record中
     * @param clazz 需要获取Table类注解的类
     * @return 注解内容键值对record
     */
    public Record getTableAnnotation(Class<?> clazz) {
        return ans.getAnnotationToRecord(clazz, TableDesc.class);
    }

    /**
     * 获取类成员所有TableCol注解的内容
     * @param clazz 待获取类
     * @return 映射record对象列表
     */
    public List<Record> getTableColsAnnotation(Class clazz) {
        List<Record> table_cols_desc = new ArrayList<>();
        if(null != clazz){
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields) {
                Record table_col_dict = getTableColAnnotation(field);
                table_cols_desc.add(table_col_dict);
            }
        }
        return table_cols_desc;
    }

    private Record getTableColAnnotation(Field field) {
        return ans.getAnnotationToRecord(field, TableColDesc.class);
    }
}
