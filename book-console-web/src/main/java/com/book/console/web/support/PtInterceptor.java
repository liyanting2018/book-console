package com.book.console.web.support;


import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.book.console.common.Constants;
import com.book.console.common.enums.Role;
import com.book.console.common.utils.JsonMapper;
import com.book.console.dao.model.User;
import com.book.console.service.UserService;
import com.book.console.web.config.BodyReaderHttpServletRequestWrapper;
import com.book.console.web.support.annotaion.AuthRequired;
import com.book.console.web.support.exception.NoPermissionException;
import com.book.console.web.support.exception.UnLoginException;
import com.book.console.web.support.utils.CookieUtils;

/**
 * Created by zhengfeng on 2016-07-21 11:56.
 */

@Component
public class PtInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(PtInterceptor.class);
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LoginUser loginUser = this.getLoginUser(request);

        String body = "";
        if(request instanceof BodyReaderHttpServletRequestWrapper) {
            body = ((BodyReaderHttpServletRequestWrapper) request).getBodyString();
        }

        logger.info("请求url: [{}], method: [{}], parameters: [{}], body: [{}], user: [{}]",
                request.getRequestURI(),
                request.getMethod(),
                JsonMapper.nonEmptyMapper().toJson(request.getParameterMap()),
                body,
                loginUser);


        request.setAttribute(Constants.CURRENT_USER_KEY, loginUser);

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if(handlerMethod != null) {
            AuthRequired authRequired = handlerMethod.getMethodAnnotation(AuthRequired.class);

            if (authRequired == null) {
                authRequired = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), AuthRequired.class);
            }

            if (authRequired != null) {
                if (authRequired.login() && loginUser == null) {
                    throw new UnLoginException();
                }

                if (loginUser == null || !Role.hasPermission(authRequired.role(), loginUser.getRole())) {
                    throw new NoPermissionException();
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 获取登陆用户
     * @param request
     * @return
     */
    private LoginUser getLoginUser(HttpServletRequest request) throws UnsupportedEncodingException {
        String name = CookieUtils.getCookieValue(request, Constants.COOKIE_USER_TOKEN);
        User user = userService.findByUsername(name);
        return user != null ? new LoginUser(user) : null;
    }
}
