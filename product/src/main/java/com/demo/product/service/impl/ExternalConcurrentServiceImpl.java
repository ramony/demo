package com.demo.product.service.impl;

import com.demo.product.service.ExternalConcurrentService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ExternalConcurrentServiceImpl implements ExternalConcurrentService {

    @Bulkhead(name = "bulkhead", fallbackMethod = "fallback2")
    public String bulkhead() {
        try {
            Thread.sleep(2000);
            System.out.println(LocalTime.now() + " Call processing finished = " + Thread.currentThread().getName());
            return LocalTime.now() + " Call processing finished = " + Thread.currentThread().getName();
        } catch (Exception e) {
            throw new RuntimeException("Exception");
        }
    }

    private String fallback2(Throwable e) {
        System.out.println(LocalTime.now() + " Call processing fail4 = " + Thread.currentThread().getName() + e.getClass().getName());
        throw new RuntimeException("bbb");
    }

    @CircuitBreaker(name = "externalConcurrentService")
    public String circuitBreaker() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

}
