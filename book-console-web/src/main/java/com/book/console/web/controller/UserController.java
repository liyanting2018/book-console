package com.book.console.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.console.common.Constants;
import com.book.console.common.enums.ErrorMessage;
import com.book.console.common.enums.Role;
import com.book.console.common.utils.JsonMapper;
import com.book.console.dao.model.User;
import com.book.console.service.UserService;
import com.book.console.web.support.JsonResult;
import com.book.console.web.support.LoginUser;
import com.book.console.web.support.annotaion.AuthRequired;
import com.book.console.web.support.annotaion.CurrentUser;
import com.book.console.web.support.utils.CookieUtils;
import com.github.pagehelper.PageInfo;

/**
 * com.pajk.skyeye.web.controller
 * Created by zhengfeng on 2016-08-05 14:33.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @AuthRequired
    @RequestMapping("/current")
    public JsonResult user(@CurrentUser LoginUser loginUser) {
        return JsonResult.success(loginUser.getUser());
    }


    @AuthRequired(role = Role.MANAGER)
    @RequestMapping("/updateRole")
    public JsonResult updateRole(@Valid @RequestBody User user, @CurrentUser LoginUser loginUser, HttpServletResponse response) {
        userService.updateRole(user.getUsername(), user.getRole());
        return JsonResult.success(user);
    }

    @AuthRequired(role = Role.MANAGER)
    @RequestMapping("/all")
    public JsonResult users(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                            @RequestParam(value = "username", defaultValue = "") String username) {
        PageInfo<User> pageInfo = userService.findAll(username, page, size);
        return JsonResult.success(pageInfo);
    }

    @RequestMapping("/login")
    public JsonResult login(String username, String password, HttpServletResponse response) throws Exception {
        /*if(Constants.ADMIN_USERNAME.equals(username) && this.adminPassword.equalsIgnoreCase(DigestUtils.md5Hex(password))) {*/
    	User user = userService.findByUsername(username);
        if(Constants.ADMIN_USERNAME.equals(username) && "admin".equalsIgnoreCase(password)) {   
            CookieUtils.setCookie(response, Constants.COOKIE_USER_TOKEN, user.getUsername());
            CookieUtils.setCookie(response, Constants.COOKIE_USER, JsonMapper.nonEmptyMapper().toJson(user));
            return JsonResult.success(user);
        }else if(user.getPassword().equals(password)){
        	return JsonResult.success(user);
        }
        return JsonResult.error(ErrorMessage.LOGIN_PASSWORD_ERROR);
    }

    @RequestMapping("/logout")
    public JsonResult logout(HttpServletResponse response) throws Exception {
        CookieUtils.deleteCookie(response, Constants.COOKIE_SSO_TOKEN);
        CookieUtils.deleteCookie(response, Constants.COOKIE_USER_TOKEN);
        CookieUtils.deleteCookie(response, Constants.COOKIE_USER);
        return JsonResult.success();
    }
    
    @RequestMapping("/register")
    public JsonResult updateRole(@Valid @RequestBody User user, HttpServletResponse response) {
        user.setCreatedAt(new Date());
        user.setRole(1);
        userService.add(user);
        return JsonResult.success(user);
    }

}
