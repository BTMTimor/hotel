package org.tm.common.base.service;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.db.model.Columns;
import org.tm.common.base.model.BaseModel;
import org.tm.common.base.model.ICondition;

import java.util.List;

/*
    author: Timor
    date: 2020/1/7 0007
*/
public interface BaseService<T extends BaseModel<?>> {

    // 根据主键查找Model
    T findById(Object id);

    // 根据 Columns 查找单条数据
    T findFirstByColumns(Columns columns);

    // 根据 Columns 查找单条数据
    T findFirstByColumns(Columns columns, String orderBy);

    // 查找全部数据
    List<T> findAll();

    // 根据 Columns 查找数据
    List<T> findListByColumns(Columns columns);

    // 根据 Columns 查找数据
    List<T> findListByColumns(Columns columns, String orderBy);

    // 根据 Columns 查找数据
    List<T> findListByColumns(Columns columns, Integer count);

    // 根据 Columns 查找数据
    List<T> findListByColumns(Columns columns, String orderBy, Integer count);

    // 根据提交查询数据量
    long findCountByColumns(Columns columns);

    // 根据Object 删除model
    boolean deleteById(Object id);

    // 删除
    boolean delete(T model);

    // 根据 多个 id 批量删除
    boolean batchDeleteByIds(Object... ids);

    /**
     * 保存到数据库
     *
     * @return id
     */
    Object save(T model);

    Object saveOrUpdate(T model);

    // 更新
    boolean update(T model);

    // 分页
    Page<T> paginate(int page, int pageSize);

    // 分页
    Page<T> paginateByColumns(int page, int pageSize, Columns columns);

    // 分页
    Page<T> paginateByColumns(int page, int pageSize, Columns columns, String orderBy);

    // 复杂条件分页查询(sqlTemplateName, condition)
    Page<T> findAllByCondition(ICondition condition);
}