package com.book.console.web.support;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.book.console.web.support.annotaion.CurrentUser;

/**
 * controller 方法中, 支持使用 @CurrentUser 注解获取当前用户信息
 * Created by zhengfeng on 2016-07-26 16:14.
 */

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CurrentUser currentUser = parameter.getParameterAnnotation(CurrentUser.class);
        return webRequest.getAttribute(currentUser.value(), NativeWebRequest.SCOPE_REQUEST);
    }
}
