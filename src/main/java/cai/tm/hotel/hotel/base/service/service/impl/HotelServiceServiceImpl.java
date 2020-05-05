package cai.tm.hotel.hotel.base.service.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.hotel.base.service.service.HotelServiceService;
import cai.tm.hotel.hotel.base.service.model.HotelService;

@Bean
public class HotelServiceServiceImpl extends BaseServiceImpl<HotelService> implements HotelServiceService {

    @Override
    public Page<HotelService> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}