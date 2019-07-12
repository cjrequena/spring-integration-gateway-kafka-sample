package com.sample.service;

import com.sample.channel.KafkaChannel;
import com.sample.configuration.KafkaConstants;
import com.sample.dto.RequestV1DTO;
import com.sample.dto.RequestV2DTO;
import com.sample.dto.ResponseV1DTO;
import com.sample.dto.ResponseV2DTO;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway
public interface GatewayService {
  @Gateway(requestChannel = KafkaChannel.REQUEST_CHANNEL, replyChannel = KafkaChannel.REPLY_CHANNEL, replyTimeout = 1000, requestTimeout = 1000,
    headers = {@GatewayHeader(name = KafkaConstants.MEDIA_TYPE_HEADER, value = RequestV1DTO.MEDIA_TYPE)})
  ResponseV1DTO process(RequestV1DTO requestV1DTO);

  @Gateway(requestChannel = KafkaChannel.REQUEST_CHANNEL, replyChannel = KafkaChannel.REPLY_CHANNEL, replyTimeout = 1000, requestTimeout = 1000,
    headers = {@GatewayHeader(name = KafkaConstants.MEDIA_TYPE_HEADER, value = RequestV2DTO.MEDIA_TYPE)})
  ResponseV2DTO process(RequestV2DTO requestV1DTO);

  @Gateway(requestChannel = KafkaChannel.REQUEST_CHANNEL_2, replyChannel = KafkaChannel.REPLY_CHANNEL_2, replyTimeout = 1000, requestTimeout = 1000,
    headers = {@GatewayHeader(name = KafkaConstants.MEDIA_TYPE_HEADER, value = RequestV1DTO.MEDIA_TYPE)})
  ResponseV1DTO processSecondary(RequestV1DTO requestV1DTO);

}
