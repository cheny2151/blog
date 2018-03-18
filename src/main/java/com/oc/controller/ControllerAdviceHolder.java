package com.oc.controller;

import com.oc.system.databind.DateEditor;
import com.oc.system.databind.StringEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.util.Date;

/**
 * controller统一通知
 */
@ControllerAdvice
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

    @ExceptionHandler
    public void exceptionHandler(Exception e) {
        e.printStackTrace();
    }

}
