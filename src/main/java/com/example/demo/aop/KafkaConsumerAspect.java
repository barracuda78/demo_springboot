package com.example.demo.aop;

import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerAspect {

    private final MessageService messageService;

    @Pointcut("execution(public * com.example.demo.service.CountryServiceImpl.*(..))")
    public void callGetCountries() { }

    @Pointcut("execution(public * com.example.demo.kafka.consumer.KafkaConsumer.consume(..))")
    public void callKafkaConsumer() { }

    @After("callGetCountries()")
    public void afterGetCountries(JoinPoint jp) {
        log.info("=============afterGetCountries " + jp.toString() + "=============");
    }

    @AfterReturning(pointcut = "callKafkaConsumer()", returning = "message")
    public void saveConsumedKafkaMessageToDb(String message) {
        log.info("=============after kafka consume===================");
        messageService.saveMessage(message);
    }

}
