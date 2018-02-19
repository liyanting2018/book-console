package com.book.console.web.support.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.book.console.common.Constants;


/**
 * Created by zhengfeng on 2016-07-26 16:18.
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
    String value() default Constants.CURRENT_USER_KEY;
}
