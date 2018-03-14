package com.oc.controller.mybatis;

import com.oc.entity.User;
import com.oc.mService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller("mybatisController")
@RequestMapping(value = "/mybatis")
public class MybatisController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(){
        User user = userService.getUserDao("user");
        System.out.println(user.toString());
    }
}
