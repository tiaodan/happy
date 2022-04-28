package com.haha.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过切面进行日志打印,controller的情趣响应
 * Componnent 泛指各种组件,如果想要把该类加入到Springboot中,就用这个注解,只要不符合@Controller @Service 就可以用这个注解
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    /**
     * 拦截Controller下的所有方法 com.haha.business.*.controller.*(..)
     * 表示拦截 com.haha.business.* 下的controller的所有 public 方法,方法参数可以是0或任意个
     */
    @Around("execution(public * com.haha.business.*.controller.*(..))")
    public void pointcut() throws Throwable{
        // do nothing
    }

    @Before()

    @After()


    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
//        log.debug("Request Args   : {}", JSONObject().toString(joinPoint.getArgs()));  // 不行
//        log.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));  // 报错 java.lang.UnsupportedOperationException: Attempted to serialize java.lang.Class: java.lang.String. Forgot to register a type adapter?
        log.info("Request Args   : {}", gson.toJson(joinPoint.getArgs()));

    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("pointcut()")
    public void doAfter() throws Throwable {
        log.info("=========================================== End ===========================================");
        // 每个请求之间空一行
        log.info("");
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", new Gson().toJson(result));
        // 执行耗时
        log.info("Time-Consuming 耗时: {} ms", System.currentTimeMillis() - startTime);
        return result;
    }
}
