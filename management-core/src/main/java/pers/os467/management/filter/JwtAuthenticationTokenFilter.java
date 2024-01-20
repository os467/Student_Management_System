package pers.os467.management.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import pers.os467.management.common.Constant;
import pers.os467.management.utils.CookieUtils;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static List<String> noTokenUrl;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    static  {
        noTokenUrl = new ArrayList<>();
        noTokenUrl.add("/**/*.html");
        noTokenUrl.add("/**/*.js");
        noTokenUrl.add("/**/*.css");
        noTokenUrl.add("/**/*.woff");
        noTokenUrl.add("/**/*.ttf");
        noTokenUrl.add("/**/*.png");
        noTokenUrl.add("/user/login");
        noTokenUrl.add("/user/searchAvatarUrl");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //判断是否在token白名单
        if (inNoTokenUrl(request.getRequestURI())){
            //直接放行
            filterChain.doFilter(request,response);
            return;
        }

        //需要验证token
        //String token = request.getHeader("Authentication");
        Cookie cookie = CookieUtils.findCookie(request.getCookies(), Constant.TOKEN_COOKIE_NAME);

        String token = null;
        if (cookie != null){
            token = cookie.getValue();
        }
        if (token == null){
            //token不存在，拒绝服务
            return;
        }

        //检查token信息
        JwtUser jwtUser = JwtUtils.checkLogin(token);
        if(jwtUser == null){
            //token无效，拒绝服务
            return;
        }

        //缓存登录用户信息到session域
        request.getSession().setAttribute(Constant.JWT_USER,jwtUser);



        filterChain.doFilter(request,response);

    }

    private boolean inNoTokenUrl(String requestURI) {
        for (String pattern : noTokenUrl) {
            if (pathMatcher.match(pattern,requestURI)){
                return true;
            }
        }
        return false;
    }


}
