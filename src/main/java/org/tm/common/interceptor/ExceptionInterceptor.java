package org.tm.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.api.result.ResultCode;

/*
    author: Timor
    date: 2020/2/9 0009
*/
public class ExceptionInterceptor implements Interceptor {
     static Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public void intercept(Invocation invocation) {
        ApiResult result = null;
        try {
            invocation.invoke();
        } catch (NullPointerException e) {
            logger.error(e.toString());
            result = ApiResult.error(ResultCode.PARAM_IS_MISSING);
        } catch(SecurityException e){
            logger.error(e.toString());
            result = ApiResult.error(ResultCode.PERMISSION_NO_ACCESS);
        } catch (Exception e){
            logger.error(e.toString());
            result = ApiResult.error(ResultCode.SYSTEM_INNER_ERROR);
        }finally {
            if(null != result){
                // 返回统一格式json数据
                invocation.getController().renderJson(result);
            }
        }
    }
}

