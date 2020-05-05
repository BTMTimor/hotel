package org.tm.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.tm.common.annotation.ApiController;

import java.util.HashMap;

/*
    author: Timor
    date: 2020/3/18 0018
*/
public class ApiInterceptor implements Interceptor {
    public static final String RENDER_JSON = "json";
    private ActionCache action = new ActionCache();

    @Override
    public void intercept(Invocation invocation) {
        // 这里只处理通过返回值返回客户端的数据
        ApiController annotation = invocation.getController().getClass().getAnnotation(ApiController.class);
        invocation.invoke();
        if(null != annotation){
            process(invocation , annotation);
        }
    }

    public void process(Invocation invocation, ApiController annotation){
        if(action.notReturnVoid(invocation)){
            Object returnValue = invocation.getReturnValue();
            String renderType = annotation.value().toLowerCase();
//            System.out.println("[api] process: " + renderType);
            if (RENDER_JSON.equals(renderType)){
                invocation.getController().renderJson(returnValue);
            }
        }
    }

    static class ActionCache{
        HashMap<String, Boolean> mapping = new HashMap<>();

        public boolean notReturnVoid(Invocation invocation){
            return !isReturnVoid(invocation);
        }

        public boolean isReturnVoid(Invocation invocation) {
            String target = invocation.getActionKey();
            if (mapping.containsKey(target)) {
                return mapping.get(target);
            }else {
                boolean isReturnVoid = invocation.getMethod().getReturnType().equals(Void.TYPE);
//                System.out.println("[routeMapping] add " + target + " : " + isReturnVoid);
                mapping.put(target, isReturnVoid);
                return isReturnVoid;
            }
        }
    }

}
