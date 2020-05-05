package cn.tm.hotel.test.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cn.tm.hotel.test.service.TestService;
import cn.tm.hotel.test.model.TestModel;

@Bean
public class TestServiceImpl extends BaseServiceImpl<TestModel> implements TestService {

    @Override
    public Page<TestModel> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}