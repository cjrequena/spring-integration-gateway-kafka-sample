package com.sample;

import com.sample.configuration.KafkaChannel;
import com.sample.configuration.KafkaConstants;
import com.sample.dto.RequestV1DTO;
import com.sample.dto.RequestV2DTO;
import com.sample.dto.ResponseV1DTO;
import com.sample.dto.ResponseV2DTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@Log4j2
@SpringBootApplication
@EnableAutoConfiguration
@EnableBinding(KafkaChannel.class)
public class ProcessorMainApplication {
  private static Class<ProcessorMainApplication> mainApplicationClass = ProcessorMainApplication.class;
  @Autowired
  private KafkaChannel kafkaChannel;

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

  @StreamListener(target = KafkaChannel.REQUEST_CHANNEL_1,
    condition = "headers['" + KafkaConstants.MEDIA_TYPE_HEADER + "']=='" + RequestV1DTO.MEDIA_TYPE + "'")
  public void processV1(Message<RequestV1DTO> message) {
    ResponseV1DTO responseV1DTO = new ResponseV1DTO();
    responseV1DTO.setText("Process V1: " + message.getPayload().getText().toUpperCase());

    kafkaChannel.reply().send(MessageBuilder.withPayload(responseV1DTO)
      .copyHeaders(message.getHeaders())
      .setHeader(KafkaConstants.MEDIA_TYPE_HEADER, ResponseV1DTO.MEDIA_TYPE)
      .build());
  }

  @StreamListener(target = KafkaChannel.REQUEST_CHANNEL_1,
    condition = "headers['" + KafkaConstants.MEDIA_TYPE_HEADER + "']=='" + RequestV2DTO.MEDIA_TYPE + "'")
  public void processV2(Message<RequestV2DTO> message) {
    ResponseV2DTO responseV2DTO = new ResponseV2DTO();
    responseV2DTO.setText("Process V2: " + message.getPayload().getText().toLowerCase());

    kafkaChannel.reply().send(MessageBuilder.withPayload(responseV2DTO)
      .copyHeaders(message.getHeaders())
      .setHeader(KafkaConstants.MEDIA_TYPE_HEADER, ResponseV1DTO.MEDIA_TYPE)
      .build());

  }

  @StreamListener(target = KafkaChannel.REQUEST_CHANNEL_2,
    condition = "headers['" + KafkaConstants.MEDIA_TYPE_HEADER + "']=='" + RequestV1DTO.MEDIA_TYPE + "'")
  public void processV3(Message<RequestV1DTO> message) {
    ResponseV1DTO responseV1DTO = new ResponseV1DTO();
    responseV1DTO.setText("Secondary process for : " + message.getPayload().getText() + ". Thanks !!!!");

    kafkaChannel.reply2().send(MessageBuilder.withPayload(responseV1DTO)
      .copyHeaders(message.getHeaders())
      .setHeader(KafkaConstants.MEDIA_TYPE_HEADER, ResponseV1DTO.MEDIA_TYPE)
      .build());
  }

}
