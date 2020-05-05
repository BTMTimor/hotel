package cn.tm.hotel.system.service.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cn.tm.hotel.system.service.service.SystemServiceService;
import cn.tm.hotel.system.service.model.SystemService;

@Bean
public class SystemServiceServiceImpl extends BaseServiceImpl<SystemService> implements SystemServiceService {

    @Override
    public Page<SystemService> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}