package cai.tm.hotel.hotel.device.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.hotel.device.service.HotelDeviceService;
import cai.tm.hotel.hotel.device.model.HotelDevice;

@Bean
public class HotelDeviceServiceImpl extends BaseServiceImpl<HotelDevice> implements HotelDeviceService {

    @Override
    public Page<HotelDevice> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}