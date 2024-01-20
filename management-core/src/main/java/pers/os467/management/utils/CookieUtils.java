package pers.os467.management.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *根据cookie的key来找到某一个具体的cookie,因为api中我们只能获取所有cookie
 * 获取单个cookie的话只能通过标识去过滤，这样就很麻烦，所以我们可以封装一个工具类
 * 来提高开发速率
 */
public class CookieUtils {

    private static final Integer maxAge = 60 * 60 * 24;

    //构造方法私有化
    private CookieUtils(){

    }

    /**
     * 根据key来获取cookie
     * @return
     */
    public static Cookie findCookie(Cookie[] cookies,String cookieName){

        if (cookies == null || cookies.length == 0 || cookieName == null){
            return null;
        }

        //遍历cookie数组
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)){
                return cookie;
            }
        }
        return null;

    }

    //	设置Cookie值的方法
    public static void setHttpCookieValue(HttpServletResponse response, String cookieName, String cookieValue){

        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge); // Cookie的存活时间（自定义）
        cookie.setPath("/"); // 默认路径
        response.addCookie(cookie);
    }

    public static void setHttpCookieValue(HttpServletResponse response, String cookieName, String cookieValue,Integer maxAge){

        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge); // Cookie的存活时间（自定义）
        cookie.setPath("/"); // 默认路径
        response.addCookie(cookie);
    }

    //	设置Cookie值的方法
    public static void setHttpOnlyCookieValue(HttpServletResponse response, String cookieName, String cookieValue){

        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge); // Cookie的存活时间（自定义）
        cookie.setPath("/"); // 默认路径
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }


    public static void deleteCookie(HttpServletResponse response, String cookieName) {
        try {
            String deleteValue = URLEncoder.encode("", "utf-8");
            CookieUtils.setHttpCookieValue(response,cookieName,deleteValue,0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}