package com.oc.controller;

import com.oc.system.databind.DateEditor;
import com.oc.system.databind.StringEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * controller统一通知
 */
@ControllerAdvice()
public class ControllerAdviceHolder {

    @Resource(name = "dateEditor")
    private DateEditor dateEditor;
    @Resource(name = "stringEditor")
    private StringEditor stringEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, dateEditor);
        binder.registerCustomEditor(String.class, stringEditor);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String UsernameNotFoundException(UsernameNotFoundException e) {
        System.out.println("user not ex");
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseBody
    public void exceptionHandler(Exception e) {
        System.out.println("all ex");
        e.printStackTrace();
    }

}
