package cai.tm.hotel.hotel.base.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.hotel.base.service.HotelService;
import cai.tm.hotel.hotel.base.model.Hotel;

@Bean
public class HotelServiceImpl extends BaseServiceImpl<Hotel> implements HotelService {

    @Override
    public Page<Hotel> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}