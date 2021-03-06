package com.oc.controller;

import com.oc.system.databind.DateEditor;
import com.oc.system.databind.StringEditor;
import com.oc.system.message.JsonMessage;
import org.apache.log4j.Logger;
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

    private final Logger logger = Logger.getLogger(this.getClass());

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
    public JsonMessage UsernameNotFoundException(UsernameNotFoundException e) {
        logger.info("找不到用户名:" + e.getMessage());
        return JsonMessage.error("username not found");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonMessage exceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return JsonMessage.serverError(e.getMessage());
    }

}
