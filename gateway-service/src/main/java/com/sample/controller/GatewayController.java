package com.sample.controller;

import com.sample.service.GatewayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

@Log4j2
@RestController
public class GatewayController {

  private GatewayService gateway;
  private final ExecutorService pool;

  @Autowired
  public GatewayController(GatewayService gateway){
    this.gateway = gateway;
    this.pool = new ForkJoinPool(1000);
  }

  @GetMapping(value = "/string/{string}")
  public DeferredResult<ResponseEntity<?>> process(@PathVariable("string") String string) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(500l);
    String operationIdentifier = java.util.UUID.randomUUID().toString();
    pool.submit(() -> {
      deferredResult.setResult(ResponseEntity.ok(this.gateway.process(string)));
    });
    return deferredResult;
  }
}
