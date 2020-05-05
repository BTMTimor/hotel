package org.tm.common.handel;

import com.jfinal.handler.Handler;
import lombok.SneakyThrows;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.api.result.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
    author: Timor
    date: 2020/4/12 0012
*/
public class ApiHandler extends Handler {
    Map<Integer, String> mapping = new HashMap<Integer, String>(){{
        put(401, ApiResult.failure(ResultCode.USER_NOT_LOGIN).toJson());
        put(403, ApiResult.failure(ResultCode.PERMISSION_NO_ACCESS).toJson());
        put(404, ApiResult.failure(ResultCode.SERVICE_IS_INVALID).toJson());
        put(500, ApiResult.failure(ResultCode.ERROR).toJson());
    }};

    @SneakyThrows
    @Override
    public void handle(String s, HttpServletRequest request, HttpServletResponse response, boolean[] booleans) {
        next.handle(s, request, response, booleans);
        if(mapping.containsKey(response.getStatus())){
            // json string
            response.setContentType("application/json;charset=UTF-8");
            response.resetBuffer();
            response.getWriter().write(mapping.get(response.getStatus()));
        }
    }
}
