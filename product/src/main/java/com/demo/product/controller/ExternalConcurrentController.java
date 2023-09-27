package com.demo.product.controller;

import com.demo.product.service.ExternalConcurrentService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concurrent")
public class ExternalConcurrentController {

    @Autowired
    private ExternalConcurrentService externalConcurrentService;

    @GetMapping("/bulkhead")
    @Bulkhead(name = "externalConcurrentService")
    public String bulkhead() {
        try {
            return externalConcurrentService.bulkhead();
        } catch (Exception e) {
//            System.out.println(LocalTime.now() + " bulkhead error = " + Thread.currentThread().getName() + e.getMessage());
//            return "error";
            throw new RuntimeException("haha");
        }
    }

    @GetMapping("/bulkhead2")
    @Bulkhead(name = "externalConcurrentService2")
    public String bulkhead2() {
        return "success";
    }

    @GetMapping("/circuit-breaker")
    public String circuitBreaker() {
        return externalConcurrentService.circuitBreaker();
    }

}
