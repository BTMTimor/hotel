package org.tm.common.base.controller;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.web.controller.JbootController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.api.result.ResultCode;
import org.tm.common.base.model.BaseModel;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.BaseService;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/*
    author: Timor
    date: 2020/1/6 0006
*/
public abstract class BaseApiController<T extends BaseModel<T>> extends JbootController {

    protected abstract BaseService<T> getService();

    protected ApiResult getById(String id){
        if(StrKit.isBlank(id)){
            return ApiResult.failure(ResultCode.PARAM_IS_MISSING) ;
        }
        T result = getService().findById(id);
        if (null == result){
            return ApiResult.failure(ResultCode.DATA_NOT_EXIST) ;
        }
        return ApiResult.success(filterForReturn(result));
    }

    protected ApiResult add(T entity){
        Object id =  getService().save(entity);
        if(null == id){
            return ApiResult.failure(ResultCode.DATA_ADD_ERROR);
        }
        return ApiResult.success(ResultCode.DATA_ADD_SUCCESS, id);
    }

    protected ApiResult checkAndAdd(T entity){
        return this.checkAndAdd(entity, () -> "");
    }

    protected ApiResult checkAndAdd(T entity, Supplier<String> otherCheck){
        ApiResult apiResult = checkModel(entity, this::checkForAdd,otherCheck);
        if(null != apiResult){
            return apiResult;
        }
        return this.add(entity);
    }

    protected ApiResult updateModel(T entity){
        if(getService().update(entity)){
            return ApiResult.success(ResultCode.DATA_UPDATE_SUCCESS, true);
        }
        return ApiResult.failure(ResultCode.DATA_UPDATE_ERROR);
    }

    protected ApiResult checkAndUpdate(T entity){
        return checkAndUpdate(entity, () -> "");
    }

    protected ApiResult checkAndUpdate(T entity, Supplier<String> otherCheck){
        ApiResult apiResult = checkModel(entity, this::checkForUpdate, otherCheck);
        if(null != apiResult){
            return apiResult;
        }
        return updateModel(entity);
    }

    protected ApiResult deleteById(String id) {
        if(StrKit.notBlank(id)){
            if(getService().deleteById(id)){
                return ApiResult.success(ResultCode.DATA_DELETE_SUCCESS, true);
            }
            return ApiResult.failure(ResultCode.DATA_DELETE_ERROR);
        }
        return ApiResult.failure(ResultCode.PARAM_IS_MISSING);
    }

    protected ApiResult list(int pageNumber, int pageSize) {
        if(pageNumber < 1) pageNumber = 1;
        if(pageSize < 1 ) pageSize = 5;
        if(pageSize > 100) pageSize = 100;
        return ApiResult.success(getService().paginate(pageNumber, pageSize));
    }

    protected ApiResult listAndFilter(int pageNumber, int pageSize) {
        if(pageNumber < 1) pageNumber = 1;
        if(pageSize < 1 ) pageSize = 5;
        if(pageSize > 100) pageSize = 100;
        return ApiResult.success(filterForReturnPage(getService().paginate(pageNumber, pageSize)));
    }

    protected ApiResult findAll(ICondition condition) {
        String message = checkCondition(condition);
        if(!message.isEmpty()){
            return ApiResult.error(ResultCode.PARAM_IS_INVALID, message);
        }
        return ApiResult.success(getService().findAllByCondition(condition));
    }

    protected ApiResult findAllAndFilter(ICondition condition) {
        String message = checkCondition(condition);
        if(!message.isEmpty()){
            return ApiResult.error(ResultCode.PARAM_IS_INVALID, message);
        }
        return ApiResult.success(filterForReturnPage(getService().findAllByCondition(condition)));
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    protected ApiResult checkModel(T entity, Function<T, String> baseCheck, Supplier<String> otherCheck){
        String message = baseCheck.apply(entity);
        if(!message.isEmpty()){
            return ApiResult.error(ResultCode.PARAM_IS_BLANK, message);
        }
        // 自定义校验逻辑
        message = otherCheck.get();
        if(!message.isEmpty()){
            return ApiResult.error(ResultCode.PARAM_IS_BLANK, message);
        }
        return null;
    }

    protected T filterForReturn(T entity) {
        return entity.filterForReturn();
    }

    protected void filterForReturnList(List<T> entityList) {
        for (T entity : entityList) {
            entity.filterForReturn();
        }
    }

    protected Page<T> filterForReturnPage(Page<T> entityPage) {
        filterForReturnList(entityPage.getList());
        return entityPage;
    }

    protected String checkForAdd(T entity) {
        return entity.checkForAdd();
    }

    protected String checkForUpdate(T entity) {
        return entity.checkForUpdate();
    }

    protected String checkCondition(ICondition condition){
        if(null == condition){
            return "condition is missing";
        }
        return condition.check();
    }

    // - - - - - - - - - - - - - - - - - - - - - - - -

    protected String getUserId(){
        return getAttr("user_id");
    }

}

