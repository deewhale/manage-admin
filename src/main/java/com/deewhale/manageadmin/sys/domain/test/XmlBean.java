package com.deewhale.manageadmin.sys.domain.test;

import lombok.Data;

import java.util.List;

/**
 * xml 测试用的 bean
 */

@Data
public class XmlBean {

    private String name;
    private String age;
    private BeanList[] beanLists;
}
