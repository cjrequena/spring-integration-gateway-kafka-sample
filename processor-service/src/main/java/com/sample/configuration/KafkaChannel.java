package com.sample.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaChannel {
  String REPLY_CHANNEL = "reply_channel";
  String REQUEST_CHANNEL = "request_channel";
  String REPLY_CHANNEL_2 = "reply_channel_2";
  String REQUEST_CHANNEL_2 = "request_channel_2";

  @Output(REPLY_CHANNEL)
  MessageChannel reply();
  @Input(REQUEST_CHANNEL)
  SubscribableChannel request();

  @Output(REPLY_CHANNEL_2)
  MessageChannel reply2();
  @Input(REQUEST_CHANNEL_2)
  SubscribableChannel request2();
}
