package com.deewhale.manageadmin.sys.domain.test;

import lombok.Data;

@Data
public class BeanList {
    private String name;

    public BeanList(){}

    public BeanList(String name){
        this.name = name;
    }
}
