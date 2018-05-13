package com.oc.controller;

import com.oc.dao.mongo.ArticleMongo;
import com.oc.redis.RedisClient;
import com.oc.service.UserService;
import com.oc.system.message.JsonMessage;
import com.oc.system.page.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestController {

    @Resource(name = "jdkRedisClient")
    private RedisClient<String> redisClient;
    @Resource(name = "userServiceImpl")
    private UserService userService;
    @Resource(name = "articleMongoImpl")
    private ArticleMongo articleMongo;

    @RequestMapping("/test")
    @ResponseBody
    public JsonMessage test(Pageable pageable) {
        return JsonMessage.success("success");
    }

}
