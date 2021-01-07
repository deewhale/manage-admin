package com.deewhale.manageadmin.sys.controller;

import com.deewhale.manageadmin.sys.domain.User;
import com.deewhale.manageadmin.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/6 上午11:29
 * @description：测试Controller
 * ******************************************/
@RestController
public class HelloController {

    @Resource
    private UserMapper mapper;

    @GetMapping("hello")
    public String admin(){
        return "hello admin";
    }

    @GetMapping("getUser")
    public String getUser(){
        List<User> users = mapper.selectList(null);
        return users.get(0).toString();
    }
}
