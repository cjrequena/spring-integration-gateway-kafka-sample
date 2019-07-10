package com.sample.service;

import com.sample.channel.KafkaChannel;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway
public interface GatewayService {

  @Gateway(requestChannel = KafkaChannel.REQUEST_CHANNEL, replyChannel = KafkaChannel.REPLY_CHANNEL, replyTimeout = 1000, requestTimeout = 1000)
  String process(String payload);

}
