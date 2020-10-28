package com.zch.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过日志监控service执行时间
 *
 * @author zch
 * @date 2020/10/10 9:48
 */
@Aspect
@Component
public class ServiceLogAspect {

    /**
     * AOP通知：
     * 1. 前置通知：在方法调用之前执行
     * 2. 后置通知：在方法正常执行之后执行
     * 3. 环绕通知：在方法调用之前和之后都可以执行的通知
     * 4. 异常通知：在方法发生异常时通知
     * 5. 最终通知：在方法执行之后通知
     */


    public final static Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * 切面表达式：
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下的所有类方法
     * 第四处 * 代表类名，*代表所有类
     * 第五处 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    //@Around("execution(* com.zch.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable{

        //{} 表示占位符
        log.info("======== 开始执行 {}.{} =========",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        // 记录开始时间
        long begin = System.currentTimeMillis();

        //执行目标 service
        Object result = joinPoint.proceed();

        //记录结束时间
        long end = System.currentTimeMillis();

        long takeTime = end - begin;

        if(takeTime > 3000){
            log.error("====== 执行结束，耗时 {} 毫秒 ======", takeTime);
        }else if(takeTime > 2000){
            log.warn("====== 执行结束，耗时 {} 毫秒 ======", takeTime);
        }else{
            log.info("====== 执行结束，耗时 {} 毫秒 ======", takeTime);
        }

        return result;
    }
}
