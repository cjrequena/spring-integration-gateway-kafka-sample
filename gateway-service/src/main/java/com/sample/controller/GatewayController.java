package com.sample.controller;

import com.sample.configuration.thread.ThreadPoolMonitor;
import com.sample.service.GatewayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

@Log4j2
@RestController
public class GatewayController {

  @Autowired
  private GatewayService gateway;
  @Autowired
  @Qualifier("threadPoolExecutor")
  private ThreadPoolExecutor threadPoolExecutor;
  @Autowired
  private ThreadPoolMonitor threadPoolMonitor;


  @PostConstruct
  public void init() {
    this.threadPoolMonitor.setExecutor(this.threadPoolExecutor);
    Thread monitor = new Thread(this.threadPoolMonitor);
    monitor.start();
  }

  @GetMapping(value = "/string/{string}")
  public DeferredResult<ResponseEntity<?>> process(@PathVariable("string") String string) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(500l);
    String operationIdentifier = java.util.UUID.randomUUID().toString();
    threadPoolExecutor.submit(() -> {
      deferredResult.setResult(ResponseEntity.ok(this.gateway.process(string)));
    });
    return deferredResult;
  }
}
