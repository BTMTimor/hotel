package cn.tm.hotel.common.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;
import io.jboot.aop.jfinal.JfinalHandlers;
import io.jboot.aop.jfinal.JfinalPlugins;
import io.jboot.core.listener.JbootAppListener;
import io.jboot.web.fixedinterceptor.FixedInterceptors;
import org.tm.common.handel.AuthHandler;

public class JbootAppListenerBase implements JbootAppListener {

    @Override
    public void onInit() { 
        //会在以下所有方法之前进行优先调用
    }

    @Override
    public void onConstantConfig(Constants constants) {
        //对应 JFinalConfig 的 configConstant
    }

    @Override
    public void onRouteConfig(Routes routes) {
        //对应 JFinalConfig 的 configRoute
    }

    @Override
    public void onEngineConfig(Engine engine) {
        //对应 JFinalConfig 的 configEngine
    }

    @Override
    public void onPluginConfig(JfinalPlugins plugins) {
    }

    @Override
    public void onInterceptorConfig(Interceptors interceptors) {
    }

    @Override
    public void onFixedInterceptorConfig(FixedInterceptors fixedInterceptors) {
        //FixedInterceptor 类似 Interceptor，
        // 但是 FixedInterceptor 不会被注解 @Clear 清除
    }

    @Override
    public void onHandlerConfig(JfinalHandlers handlers) {
        //对应 JFinalConfig 的 configHandler
        handlers.add(new AuthHandler());
    }

    @Override
    public void onStartBefore() {
        // 此方法会在 onStart() 之前调用
    }

    @Override
    public void onStart() {
        //对应 JFinalConfig 的 onStart()
    }

    @Override
    public void onStop() {
        //对应 JFinalConfig 的 onStop()
    }

}