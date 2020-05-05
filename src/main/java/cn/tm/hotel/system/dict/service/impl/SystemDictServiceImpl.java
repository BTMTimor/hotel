package cn.tm.hotel.system.dict.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cn.tm.hotel.system.dict.service.SystemDictService;
import cn.tm.hotel.system.dict.model.SystemDict;

@Bean
public class SystemDictServiceImpl extends BaseServiceImpl<SystemDict> implements SystemDictService {

    @Override
    public Page<SystemDict> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}