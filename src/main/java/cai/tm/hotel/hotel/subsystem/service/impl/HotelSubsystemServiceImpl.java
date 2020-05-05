package cai.tm.hotel.hotel.subsystem.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.hotel.subsystem.service.HotelSubsystemService;
import cai.tm.hotel.hotel.subsystem.model.HotelSubsystem;

@Bean
public class HotelSubsystemServiceImpl extends BaseServiceImpl<HotelSubsystem> implements HotelSubsystemService {

    @Override
    public Page<HotelSubsystem> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}