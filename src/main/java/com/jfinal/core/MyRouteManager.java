package com.jfinal.core;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    author: Timor
    date: 2020/3/14 0014
*/
public class MyRouteManager {
    private static ActionMapping actionMapping = null;
    private static Map<String, Action> map = null;

    private MyRouteManager() {}

    public static MyRouteManager me(){
        return new MyRouteManager();
    }

    public Map<String, ApiModel> getAllRoutes() throws NoSuchFieldException, IllegalAccessException {
        if(null == map){
            ActionMapping actionMapping = getActionMapping();
            map = getRoutesMapping(actionMapping);
        }
        Map<String, ApiModel> routes = new HashMap<>(map.size());

        for (String route : map.keySet()) {
            ApiModel apiModel = new ApiModel(route, route);
            Parameter[] parameters = map.get(route).getMethod().getParameters();

            for (Parameter parameter : parameters) {
                String type = parameter.getParameterizedType().getTypeName();
                type = type.substring(type.lastIndexOf(".") + 1);

                apiModel.addParas(new ApiPara(parameter.getName(), type));
            }
            routes.put(route, apiModel);
        }
        return routes;
    }

    public Map<String, Action> getRoutesMapping(ActionMapping actionMapping) throws NoSuchFieldException, IllegalAccessException {
        Class<? extends ActionMapping> clazz = actionMapping.getClass();
        Field routeMap = clazz.getDeclaredField("mapping");

        routeMap.setAccessible(true);
        //noinspection unchecked
        return (Map<String, Action>) routeMap.get(actionMapping);
    }

    public List<String> getAllActionKeys() throws NoSuchFieldException, IllegalAccessException {
        return getActionMapping().getAllActionKeys();
    }

    public ActionMapping getActionMapping() throws NoSuchFieldException, IllegalAccessException {
        if(null == actionMapping){
            ActionHandler actionHandler = Config.getHandlers().getActionHandler();
            Class<?> clazz = actionHandler.getClass().getSuperclass();
            Field actionMappingField = clazz.getDeclaredField("actionMapping");

            actionMappingField.setAccessible(true);
            actionMapping = (ActionMapping) actionMappingField.get(actionHandler);
        }
        return actionMapping;
    }

}
