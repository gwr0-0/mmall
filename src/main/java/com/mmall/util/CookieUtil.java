package com.mmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gwr0-0
 * @date 2017/12/4
 */
@Slf4j
public class CookieUtil {

    private final static String COOKIE_DOMAIN = "gaowr.com";
    private final static String COOKIE_NAME = "mmall_login_token";

    public static void  writeLoginToken(HttpServletResponse httpServletResponse, String token) {
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setDomain(COOKIE_DOMAIN);
        //设置在根目录
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        //单位：秒。-1表示永久有效
        //如果不设值，则cookie不会写入硬盘，而是写在内存，只在当前页面有效
        cookie.setMaxAge(60 * 60 * 24);
        log.info("cookieName:{},cookieValue:{}", cookie.getName(), cookie.getValue());
        httpServletResponse.addCookie(cookie);
    }

    public static String readLoginToken(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("read cookieName:{}, cookieValue:{}", cookie.getName(), cookie.getValue());
                if (StringUtils.equals(cookie.getName(), COOKIE_NAME)) {
                    log.info("read true cookieName:{}, cookieValue:{}", cookie.getName(), cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void delLoginToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), COOKIE_NAME)) {
                    cookie.setDomain(COOKIE_DOMAIN);
                    cookie.setPath("/");
                    //0表示删除此cookie
                    cookie.setMaxAge(0);
                    log.info("del cookieName:{}, cookieValue:{}", cookie.getName(), cookie.getValue());
                    httpServletResponse.addCookie(cookie);
                    return;
                }
            }
        }
    }


}
