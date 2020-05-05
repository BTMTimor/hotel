package com.jfinal.core;

import lombok.Data;

/*
    author: Timor
    date: 2020/3/17 0017
*/
@Data
public class ApiPara {
    private String name;
    private String type;
    private String description;

    public ApiPara() {}

    public ApiPara(String name, String type) {
        this(name, type, "");
    }

    public ApiPara(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
