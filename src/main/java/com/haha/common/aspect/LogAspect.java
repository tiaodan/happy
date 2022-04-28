package com.haha.common.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
     * execution 规则 权限控制 返回值 路径（参数）
     * 参考 : https://blog.csdn.net/weixin_39681171/article/details/113039439
     */
    @Pointcut("execution(public * com.haha.business.audiobook.controller..*.*(..))")
    public void pointcut() throws Throwable{
        // do nothing
    }

    /**
     * 前置方法,在目标方法执行前执行
     * @param joinPoint  封装了代理方法信息的对象,若用不到则可以忽略不写
     * @throws Throwable
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        // 初始化请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  // 获取请求属性
        HttpServletRequest request = attributes.getRequest();

        // 打印请求日志
        // 打印controller的全路径和执行方法
        log.info("=========================================== Start ===========================================");
        log.info("Class Method   : {} {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("Request Args   : {}", JSONObject.toJSONString(joinPoint.getArgs()));
        // 请求参数相关
        log.info("URL            : {}", request.getRequestURI());
        log.info("HTTP Method    : {}", request.getMethod());
        log.info("IP src         : {}", request.getRemoteAddr());
    }

    /**
     * 进入方法内
     * @param proceedingJoinPoint
     * @throws Throwable
     * 参考： https://blog.csdn.net/lichuangcsdn/article/details/87741811
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object response = proceedingJoinPoint.proceed();

        // 打印返回内容
        log.info("Response Args  : {}", JSONObject.toJSONString(response));
        // 打印耗时
        log.info("Time-Consuming 耗时: {} ms", System.currentTimeMillis() - startTime);

        // 如果这里不返回response, 则目标方法返回值会被置为null
        return response;
    }

    /**
     * 方法结束后的操作
     * @throws Throwable
     */
    @After("pointcut()")
    public void doAfter() throws  Throwable{
        log.info("=========================================== End ===========================================");
        log.info("");  // 每个请求空一行
    }

}
