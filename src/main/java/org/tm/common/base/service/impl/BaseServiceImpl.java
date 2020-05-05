package org.tm.common.base.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.service.JbootServiceBase;
import org.tm.common.base.model.BaseModel;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.BaseService;

/*
    author: Timor
    date: 2020/3/14 0014
*/
public class BaseServiceImpl<T extends BaseModel<T>> extends JbootServiceBase<T> implements BaseService<T> {

    protected Page<T> findAllByCondition(String sqlKey, ICondition condition){
        Kv cond = Kv.by("con", condition);
        // 使用 Db 的 template 方法
        return DAO.template(sqlKey, cond).paginate(condition.getPageNumber(), condition.getPageSize());
    }

    protected Page<T> findAll(String sqlKey, ICondition condition){
        Kv cond = Kv.by("con", condition);
        // 使用 Db 的 template 方法
        return DAO.template(sqlKey).paginate(condition.getPageNumber(), condition.getPageSize());
    }

    @Override
    // 这个方法要具体的子类去实现，或者说抽取出接口来, 为了方便，就先这样写
    public Page<T> findAllByCondition(ICondition condition) {
        return paginate(condition.getPageNumber(), condition.getPageSize());
    }
}
