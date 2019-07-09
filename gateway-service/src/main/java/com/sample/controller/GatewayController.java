package com.sample.controller;

import com.sample.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
  @Autowired
  GatewayService gateway;

  @GetMapping(value = "/string/{string}")
  public ResponseEntity<String> getUser(@PathVariable("string") String string) {
    return new ResponseEntity<String>(gateway.process(string), HttpStatus.OK);
  }
}
