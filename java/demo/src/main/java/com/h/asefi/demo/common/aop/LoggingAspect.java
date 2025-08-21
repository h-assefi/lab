package com.h.asefi.demo.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Logs before entering a method, including the method signature and all
     * arguments that were passed to it.
     * 
     * @param joinPoint The aspect's join point that contains information about the
     *                  method.
     */
    @Before("execution(* com.h.asefi.demo..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    /**
     * Logs after exiting a method, including the method signature and the result it
     * returned.
     * 
     * @param joinPoint The aspect's join point that contains information about the
     *                  method.
     * @param result    The result returned by the method.
     */
    @AfterReturning(pointcut = "execution(* com.h.asefi.demo..*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", joinPoint.getSignature(), result);
    }
}
