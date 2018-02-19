package com.book.console.web.support.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * CookieUtils
 */
public class CookieUtils {

    /**
     * 默认当前窗口cookie有效
     */
    private static int DEFAULT_MAX_AGE = -1;
    /**
     * 获取COOKIE
     *
     * @param name
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name, ck.getName()))
                return ck;
        }
        return null;
    }

    /**
     * 获取COOKIE
     *
     * @param name
     */
    public static String getCookieValue(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name, ck.getName()))
                return URLDecoder.decode(ck.getValue(), "utf-8");
        }
        return null;
    }

    /**
     * 设置cookie, 有效期当前窗口
     * @param response
     * @param name
     * @param value
     */
    public static void setCookie(HttpServletResponse response, String name, String value) throws UnsupportedEncodingException {
        setCookie(response, name, value, DEFAULT_MAX_AGE);
    }

    /**
     * 设置COOKIE
     *
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) throws UnsupportedEncodingException {
        value = URLEncoder.encode(value, "utf-8");
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response, String name) throws UnsupportedEncodingException {
        setCookie(response, name, "", 0);
    }
}
