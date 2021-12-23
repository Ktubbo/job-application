package com.jobapplication.restgeoreceiver.service;

import com.jobapplication.restgeoreceiver.domain.Log;
import com.jobapplication.restgeoreceiver.repository.LogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Aspect
@Component
public class WatcherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherService.class);
    @Autowired
    private LogRepository repository;

    @Before("execution(* com.jobapplication.restgeoreceiver.controller.*Controller.*(..)) && target(object)")
    public void logEvent(JoinPoint joinPoint, Object object) {
        String info = "Class name: " + object.getClass().getName().substring(46)
                + " Method name: " + joinPoint.getSignature().getName();
        LOGGER.info(info);
        repository.save(new Log(new Timestamp(System.currentTimeMillis()),info));
    }
}