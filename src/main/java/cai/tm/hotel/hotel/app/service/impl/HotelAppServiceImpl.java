package cai.tm.hotel.hotel.app.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.hotel.app.service.HotelAppService;
import cai.tm.hotel.hotel.app.model.HotelApp;

@Bean
public class HotelAppServiceImpl extends BaseServiceImpl<HotelApp> implements HotelAppService {

    @Override
    public Page<HotelApp> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}