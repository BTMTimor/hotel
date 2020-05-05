package org.tm.common.base.controller;

import com.jfinal.core.NotAction;
import com.jfinal.kit.StrKit;
import io.jboot.web.controller.JbootController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.api.result.ResultCode;
import org.tm.common.base.model.BaseModel;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.BaseService;

import java.util.function.Supplier;

/*
    author: Timor
    date: 2020/1/6 0006
*/
public abstract class BaseController<T extends BaseModel<T>> extends JbootController {

    protected abstract BaseService<T> getService();

    @NotAction
    public ApiResult getById(String id){
        if(StrKit.isBlank(id)){
            return ApiResult.failure(ResultCode.PARAM_IS_MISSING) ;
        }
        return ApiResult.success(getService().findById(id));
    }

    @NotAction
    public ApiResult add(T entity){
        Object id =  getService().save(entity);
        if(null == id){
            return ApiResult.failure(ResultCode.DATA_ADD_ERROR);
        }
        return ApiResult.success(ResultCode.DATA_ADD_SUCCESS, id);
    }

    @NotAction
    public ApiResult checkAndAdd(T entity){
        // 普通entity字段校验
        String message = checkEntity(entity);
        if(!message.isEmpty()){
            return ApiResult.error(ResultCode.PARAM_IS_BLANK, message);
        }
        return this.add(entity);
    }

    @NotAction
    public ApiResult checkAndAdd(T entity, Supplier<String> otherCheck){
        ApiResult apiResult = checkModel(entity, otherCheck);
        if(null != apiResult){
            return apiResult;
        }
        return this.add(entity);
    }

    @NotAction
    public ApiResult update(T entity){
        if(getService().update(entity)){
            return ApiResult.success(ResultCode.DATA_UPDATE_SUCCESS);
        }
        return ApiResult.failure(ResultCode.DATA_UPDATE_ERROR);
    }

    @NotAction
    public ApiResult checkAndUpdate(T entity){
        String message = checkEntity(entity);
        if(!message.isEmpty()){
            return ApiResult.error(ResultCode.PARAM_IS_BLANK, message);
        }
        return update(entity);
    }

    @NotAction
    public ApiResult checkAndUpdate(T entity, Supplier<String> otherCheck){
        ApiResult apiResult = checkModel(entity, otherCheck);
        if(null != apiResult){
            return apiResult;
        }
        return update(entity);
    }

    @NotAction
    public ApiResult deleteById(String id) {
        if(StrKit.notBlank(id)){
            if(getService().deleteById(id)){
                return ApiResult.success(ResultCode.DATA_DELETE_SUCCESS);
            }
            return ApiResult.failure(ResultCode.DATA_DELETE_ERROR);
        }
        return ApiResult.failure(ResultCode.PARAM_IS_MISSING) ;
    }

    @NotAction
    public ApiResult list(int pageNumber, int pageSize) {
        if(pageNumber < 1) pageNumber = 1;
        if(pageSize < 1 ) pageSize = 5;
        if(pageSize > 100) pageSize = 100;
        return ApiResult.success(getService().paginate(pageNumber, pageSize));
    }

    @NotAction
    public ApiResult findAll(ICondition condition) {
        String message = checkCondition(condition);
        if(!message.isEmpty()){
            return ApiResult.error(ResultCode.PARAM_IS_INVALID, message);
        }
        return ApiResult.success(getService().findAllByCondition(condition));
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotAction
    private ApiResult checkModel(T entity, Supplier<String> otherCheck){
        String message = checkEntity(entity);
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

    @NotAction
    public String checkEntity(T entity){
        assert entity != null;
        // 必填字段校验，暂无其他校验
        return entity.checkFiled();
    }

    @NotAction
    public String checkCondition(ICondition condition){
        assert condition != null;
       /* if(null == condition){
            return "condition is missing";
        }*/
        return condition.check();
    }

}

