package com.demo.product.service.impl;

import com.demo.product.service.ExternalConcurrentService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ExternalConcurrentServiceImpl implements ExternalConcurrentService {

    @Bulkhead(name = "externalConcurrentService", fallbackMethod = "fallback2")
    public String bulkhead() {
        try {
            Thread.sleep(2000);
            System.out.println(LocalTime.now() + " Call processing finished = " + Thread.currentThread().getName());
            return LocalTime.now() + " Call processing finished = " + Thread.currentThread().getName();
        } catch (Exception e) {
            throw new RuntimeException("Exception");
        }
    }

    private String fallback(String param1, IllegalArgumentException e) {
        System.out.println(LocalTime.now() + " Call processing fail1 = " + Thread.currentThread().getName());
        return "test fallback 1";
    }

    private String fallback(String param1, Throwable e) {
        System.out.println(LocalTime.now() + " Call processing fail2 = " + Thread.currentThread().getName());
        return "test fallback 2";
    }

    private String fallback2(String param1, IllegalArgumentException e) {
        System.out.println(LocalTime.now() + " Call processing fail3 = " + Thread.currentThread().getName());
        throw new RuntimeException("aaa");
    }

    private String fallback2(Throwable e) {
        System.out.println(LocalTime.now() + " Call processing fail4 = " + Thread.currentThread().getName() + e.getClass().getName());
        throw new RuntimeException("bbb");
    }

    @CircuitBreaker(name = "externalConcurrentService")
    public String circuitBreaker() {
//        if(new Random().nextInt(10) <6) {
//            throw new RuntimeException("error");
//        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

}
