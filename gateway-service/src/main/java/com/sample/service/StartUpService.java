package com.sample.service;

import com.sample.common.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Log4j2
@Service
public class StartUpService {

  @PostConstruct
  public void init() {
    log.info("Starting up service {} ", Constants.INSTANCE_ID);
  }
}
