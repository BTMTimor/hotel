package cai.tm.hotel.hotel.goods.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.hotel.goods.model.HotelGoods;
import cai.tm.hotel.hotel.goods.service.HotelGoodsService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/hotel/goods")
public class HotelGoodsApiController extends BaseApiController<HotelGoods> {
    // static Logger logger = LoggerFactory.getLogger(HotelGoodsApiController.class);

    @Inject
    private HotelGoodsService hotelGoodsService;

    @ActionKey("/api/v1/hotel/goods/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/hotel/goods/add")
    public ApiResult save(@Para("") HotelGoods entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") HotelGoods entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/hotel/goods/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/hotel/goods/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<HotelGoods> getService() {
        return hotelGoodsService;
    }

}