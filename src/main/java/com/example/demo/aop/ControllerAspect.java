package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by harbor on 2019/5/20.
 */
@Aspect
@Component
public class ControllerAspect {

    private Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
    public void pointcut(){}


    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("com.example.demo.aop.ControllerAspect.pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        System.out.println(signature.getName());
        //AOP代理类的名字
        System.out.println(signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = new HashMap();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
//        String str = JSON.toJSONString(parameterMap);
//        if(obj.length > 0) {
//            System.out.println("请求的参数信息为："+str);
//        }

        System.out.println("请求的参数信息为："+parameterMap);


        logger.info("doBefore() => {}.{}().", joinPoint.getTarget().getClass().getCanonicalName(),joinPoint.getSignature().getName());

    }

    /**
     *
     * @param joinPoint
     * @throws Throwable
     */
    @After("com.example.demo.aop.ControllerAspect.pointcut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {

        logger.info("doAfter() => {}.{}().", joinPoint.getTarget().getClass().getCanonicalName(),joinPoint.getSignature().getName());

    }

    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(value = "com.example.demo.aop.ControllerAspect.pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {

//        logger.info("doAround() => {}.{}().", proceedingJoinPoint.getTarget().getClass().getCanonicalName(),proceedingJoinPoint.getSignature().getName());

        try {
            long start = System.currentTimeMillis();
            Object obj = proceedingJoinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;

            logger.info("doAround() => {}.{}(), elapsed time is {} ms.", proceedingJoinPoint.getTarget().getClass().getCanonicalName(),
                    proceedingJoinPoint.getSignature().getName(),
                    elapsedTime);

            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;

    }

    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *           对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "com.example.demo.aop.ControllerAspect.pointcut()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception) {
        logger.info("doAfterThrowingAdvice() => {}.{}(), thrown exception is.", joinPoint.getTarget().getClass().getCanonicalName(),
                joinPoint.getSignature().getName(),
                exception.getClass().getCanonicalName()
                );
    }

    /**
     *
     * @param joinPoint
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret",pointcut = "com.example.demo.aop.ControllerAspect.pointcut()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        logger.info("doAfterThrowingAdvice() => {}.{}(), return values is {}.", joinPoint.getTarget().getClass().getCanonicalName(),
                joinPoint.getSignature().getName(),
                ret
        );
    }


}
