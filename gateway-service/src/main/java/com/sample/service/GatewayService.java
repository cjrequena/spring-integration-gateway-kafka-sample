package com.sample.service;

import com.sample.channel.KafkaChannel;
import com.sample.common.Constants;
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
  @Gateway(requestChannel = KafkaChannel.ENRICH_REQUEST_CHANNEL_1, replyChannel = KafkaChannel.FILTER_REPLY_CHANNEL_1, replyTimeout = 1000, requestTimeout = 1000,
    headers = {@GatewayHeader(name = Constants.MEDIA_TYPE_HEADER, value = RequestV1DTO.MEDIA_TYPE)})
  ResponseV1DTO process(RequestV1DTO requestV1DTO);

  @Gateway(requestChannel = KafkaChannel.ENRICH_REQUEST_CHANNEL_1, replyChannel = KafkaChannel.FILTER_REPLY_CHANNEL_1, replyTimeout = 1000, requestTimeout = 1000,
    headers = {@GatewayHeader(name = Constants.MEDIA_TYPE_HEADER, value = RequestV2DTO.MEDIA_TYPE)})
  ResponseV2DTO process(RequestV2DTO requestV1DTO);

  @Gateway(requestChannel = KafkaChannel.ENRICH_REQUEST_CHANNEL_2, replyChannel = KafkaChannel.FILTER_REPLY_CHANNEL_2, replyTimeout = 1000, requestTimeout = 1000,
    headers = {@GatewayHeader(name = Constants.MEDIA_TYPE_HEADER, value = RequestV1DTO.MEDIA_TYPE)})
  ResponseV1DTO processSecondary(RequestV1DTO requestV1DTO);

}
