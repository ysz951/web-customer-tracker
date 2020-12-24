package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRSMLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*.(..))")
    private void forControllerPackage() {

    }

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*.(..))")
    private void forDaoPackage() {

    }

    @Pointcut("forControllerPackage() || forServicepackage() || forDaoPackage() ")
    private void forAppFlow() {

    }

    // add @Before advice

    // add @AfterReturning advice
}
