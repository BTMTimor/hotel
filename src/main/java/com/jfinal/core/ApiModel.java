package com.jfinal.core;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/*
    author: Timor
    date: 2020/3/17 0017
*/
public class ApiModel {
    @Getter
    private String name;
    @Getter
    private String route;
    @Getter @Setter
    private List<ApiPara> parameters;
    @Getter @Setter
    private List<String> permission;
    @Getter @Setter
    private List<String> role;
    @Getter @Setter
    private String description;

    public ApiModel(String name, String route) {
        this(name, route, "");
    }

    public ApiModel(String name, String route, String description) {
        this.name = name;
        this.route = route;
        this.description = description;
        this.parameters = new ArrayList<>();
        this.permission = new ArrayList<>();
        this.role = new ArrayList<>();
    }

    public void addParas(ApiPara para){
        this.parameters.add(para);
    }

    public void addPermission(String permission){
        this.permission.add(permission);
    }

    public void addRole(String role){
        this.role.add(role);
    }


    public String getParaString(){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        int counter = 0;
        for (ApiPara parameter : parameters) {
            String type = parameter.getType();
            type = type.substring(type.lastIndexOf(".") + 1);

            if (counter++ > 0){
                sb.append(", ");
            }
            sb.append(type + " " + parameter.getName());
        }
        sb.append(")");
        return sb.toString();
    }

    public String getPermissionString(){
        return list2String(permission);
    }

    public String getRoleString(){
        return list2String(role);
    }

    private String list2String(List<String> list){
        if (null == list || list.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (String string : list){
            if(counter++ > 0){
                sb.append("、");
            }
            sb.append(string);
        }
        return sb.toString();
    }

}
