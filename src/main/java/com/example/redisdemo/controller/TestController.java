package com.example.redisdemo.controller;

import com.example.redisdemo.domain.User;
import com.example.redisdemo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    private static final Logger log =  LoggerFactory.getLogger(TestController.class);
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/hello")
    @ResponseBody
    public String test(){

        return "hello world";
    }

    @RequestMapping("/testMybatis")
    @ResponseBody
    @Transactional(rollbackFor = Throwable.class)
    public void testMybatis(){
        for (int i = 0;i<5;i++){
            User user = userMapper.find("2");
            System.out.println(user.toString());
        }
    }
}
