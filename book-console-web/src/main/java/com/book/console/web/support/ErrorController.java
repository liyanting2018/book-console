package com.book.console.web.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.book.console.common.enums.ErrorMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 异常处理 see: http://stackoverflow.com/questions/25356781/spring-boot-remove-whitelabel-error-page
 * com.pajk.skyeye.web.controller
 * Created by zhengfeng on 2016-08-05 15:17.
 */

@RestController
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private final static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;


    @RequestMapping(value = PATH)
    public JsonResult error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> errorAttributes = this.getErrorAttributes(request);

        logger.error("请求出错: {}", errorAttributes);

        response.setStatus(HttpServletResponse.SC_OK);
        return JsonResult.error(ErrorMessage.SYSTEM_ERROR, errorAttributes);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, false);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
