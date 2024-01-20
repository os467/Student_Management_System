package pers.os467.management.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.common.Constant;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.utils.JwtUser;

import javax.servlet.http.HttpSession;

@Component
@Aspect
public class AuthenticationAop {

    @Autowired
    private HttpSession session;

    @Pointcut("@annotation(pers.os467.management.annotion.AuthenticationList)")
    public void pt(){

    }

    @Around("pt()")
    public Object checkAuthentication(ProceedingJoinPoint joinPoint){
        //获取目标方法的返回值
        Object ret = null;
        try {

            //验证权限
            if (accessController(joinPoint)){
                //执行目标方法
                ret = joinPoint.proceed();
            }else {
                ret = ResponseUtils.getFailResult("无访问权限");
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //返回切入点方法执行结果
        return ret;
    }

    private boolean accessController(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();

        AuthenticationList authenticationList = methodSignature.getMethod().getAnnotation(AuthenticationList.class);

        //检查鉴权列表
        if (authenticationList != null){
            RoleEnum[] roleList = authenticationList.value();
            //从session域中获取到当前登录用户信息
            JwtUser jwtUser = (JwtUser) session.getAttribute(Constant.JWT_USER);
            //检查是否在鉴权列表中
            for (RoleEnum role : roleList) {
                if (role.getRid().equals(jwtUser.getRid())){
                    //属于鉴权列表的角色组
                    return true;
                }
            }
            //不存在鉴权列表中，不可放行
            return false;
        }

        //不需要鉴权直接放行
        return true;
    }

}
