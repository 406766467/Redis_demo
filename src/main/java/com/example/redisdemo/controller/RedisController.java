package com.example.redisdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.redisdemo.config.RedisConfig;
import com.example.redisdemo.domain.User;
import com.example.redisdemo.mapper.UserMapper;
import com.example.redisdemo.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {
    private static final Logger log =  LoggerFactory.getLogger(TestController.class);
    private static final String key = "userCache_";
    //@Autowired按byType自动注入，而@Resource默认按 byName自动注入
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisService redisService;
    
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/redis/setAndGet")
    @ResponseBody
    public String setAndGetValues(String name,String value){
        redisTemplate.opsForValue().set(name,value);
        return (String) redisTemplate.opsForValue().get(name);
    }

    @RequestMapping("/redis/selectById")
    @ResponseBody
    public User serviceTest(String id){
        User user = null;
        if (redisService.get(key + id) !=null){
             user =  JSON.parseObject(redisService.get(key + id).toString(),User.class);
        }
        //先从redis里拿对象

        //如果redis里没有该对象
        if (user == null){
            //从数据库中查
            User user1 = userMapper.find(id);
            //如果查到了，放到redis里，并返回
            if (user1 != null){
                redisService.set(key+id, JSON.toJSON(user1));
                return user;
            }
        }
        //如果redis里有该对象
       return user;
    }
}
