package com.deewhale.manageadmin.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/6 上午11:29
 * @description：测试Controller
 * ******************************************/
@RestController
public class HelloController {

    @GetMapping("hello")
    public String admin(){
        return "hello admin";
    }
}
