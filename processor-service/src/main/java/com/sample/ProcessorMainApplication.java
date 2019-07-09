package com.sample;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

@Log4j2
@SpringBootApplication
@EnableAutoConfiguration
@EnableBinding(Processor.class)
public class ProcessorMainApplication {
  private static Class<ProcessorMainApplication> mainApplicationClass = ProcessorMainApplication.class;

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) {
    try {
      SpringApplication springApplication = new SpringApplication(mainApplicationClass);
      springApplication.run(args);
    } catch (Exception ex) {
      log.error("Error: " + ex);
      throw ex;
    }
  }

  @StreamListener(Processor.INPUT)
  @SendTo(Processor.OUTPUT)
  public Message<?> process(Message<String> request) {
    log.debug("instance_id {}", request.getHeaders().get("instance_id"));
    return MessageBuilder.withPayload(request.getPayload().toUpperCase())
      .copyHeaders(request.getHeaders())
      .build();
  }
}
