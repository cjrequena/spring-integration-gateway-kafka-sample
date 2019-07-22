package com.sample.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaChannel {

  String ENRICH_REQUEST_CHANNEL_1 = "enrich_request_channel_1";
  String FILTER_REPLY_CHANNEL_1 = "filter_reply_channel_1";
  String REQUEST_CHANNEL_1 = "request_channel_1";
  String REPLY_CHANNEL_1 = "reply_channel_1";

  String ENRICH_REQUEST_CHANNEL_2 = "enrich_request_channel_2";
  String FILTER_REPLY_CHANNEL_2 = "filter_reply_channel_3";
  String REQUEST_CHANNEL_2 = "request_channel_2";
  String REPLY_CHANNEL_2 = "reply_channel_2";

  @Input(REPLY_CHANNEL_1)
  SubscribableChannel reply();

  @Output(REQUEST_CHANNEL_1)
  MessageChannel request();

  @Input(REPLY_CHANNEL_2)
  SubscribableChannel reply2();

  @Output(REQUEST_CHANNEL_2)
  MessageChannel request2();
}
