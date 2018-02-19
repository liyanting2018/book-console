package com.book.console.web.support.annotaion;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.book.console.common.enums.Role;

/**
 * Created by zhengfeng on 2016-07-26 15:40.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthRequired {
    boolean login() default true;

    Role[] role() default {Role.NORMAL};
}
