package com.ray.test.api.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1)
@RestControllerAdvice
public class ThrowableExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThrowableExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleThrowable(final Throwable t) {
        LOGGER.error("UnhandledException", t);
        String restResponse = "error";
        return new ResponseEntity<>(restResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
