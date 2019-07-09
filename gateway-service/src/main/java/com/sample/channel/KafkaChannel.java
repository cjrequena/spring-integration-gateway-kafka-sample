package com.sample.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaChannel {

  String REPLY_CHANNEL = "reply_channel";

  String REQUEST_CHANNEL = "request_channel";

  @Input(REPLY_CHANNEL)
  SubscribableChannel reply();

  @Output(REQUEST_CHANNEL)
  MessageChannel request();
}
