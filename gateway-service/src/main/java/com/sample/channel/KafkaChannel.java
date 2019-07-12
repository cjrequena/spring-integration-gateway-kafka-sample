package com.sample.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaChannel {
  String REPLY_CHANNEL = "reply_channel";
  String REQUEST_CHANNEL = "request_channel";
  String REPLY_CHANNEL_2 = "reply_channel_2";
  String REQUEST_CHANNEL_2 = "request_channel_2";

  @Input(REPLY_CHANNEL)
  SubscribableChannel reply();

  @Output(REQUEST_CHANNEL)
  MessageChannel request();

  @Input(REPLY_CHANNEL_2)
  SubscribableChannel reply2();

  @Output(REQUEST_CHANNEL_2)
  MessageChannel request2();
}
