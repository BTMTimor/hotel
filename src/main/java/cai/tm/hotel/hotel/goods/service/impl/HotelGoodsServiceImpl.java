package cai.tm.hotel.hotel.goods.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.hotel.goods.service.HotelGoodsService;
import cai.tm.hotel.hotel.goods.model.HotelGoods;

@Bean
public class HotelGoodsServiceImpl extends BaseServiceImpl<HotelGoods> implements HotelGoodsService {

    @Override
    public Page<HotelGoods> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}