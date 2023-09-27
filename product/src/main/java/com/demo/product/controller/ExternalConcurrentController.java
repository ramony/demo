package com.demo.product.controller;

import com.demo.product.service.ExternalConcurrentService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/concurrent")
public class ExternalConcurrentController {

    @Autowired
    private ExternalConcurrentService externalConcurrentService;

    @GetMapping("/testServiceBulkhead")
    public String testServiceBulkhead() {
        try {
            return externalConcurrentService.bulkhead();
        } catch (Exception e) {
            throw new RuntimeException("haha");
        }
    }

    @GetMapping("/testConcurrentCalls")
    @Bulkhead(name = "testConcurrentCalls")
    public String testConcurrentCalls() {
        return "success";
    }

    @GetMapping("/testMaxWaitDuration")
    @Bulkhead(name = "testMaxWaitDuration")
    public String testMaxWaitDuration() {
        try {
            Thread.sleep(new Random().nextInt(4000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }


    @GetMapping("/circuit-breaker")
    public String circuitBreaker() {
        return externalConcurrentService.circuitBreaker();
    }

}
