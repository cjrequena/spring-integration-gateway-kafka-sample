package com.sample.configuration;

import com.sample.channel.KafkaChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.IntegrationConverter;
import org.springframework.integration.dsl.HeaderEnricherSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;

import java.util.UUID;

@Configuration
@EnableIntegration
public class GatewayConfiguration {

  //ublic static final UUID instanceUUID = UUID.randomUUID();
  public static final String instanceUUID = "GATEWAY_2";

  @Bean
  @IntegrationConverter
  public Converter<byte[], String> bytesToStringConverter() {
    return new Converter<byte[], String>() {
      @Override
      public String convert(byte[] source) {
        return new String(source);
      }
    };
  }

  @Bean
  public IntegrationFlow requestFlow() {
    return IntegrationFlows.from(KafkaChannel.REQUEST_CHANNEL)
      .enrichHeaders(HeaderEnricherSpec::headerChannelsToString)
      .enrichHeaders(headerEnricherSpec -> headerEnricherSpec.header("instance_id",instanceUUID))
      .channel(KafkaChannel.REQUEST_CHANNEL)
      .get();
  }

  @Bean
  public IntegrationFlow replyFlow() {
    return IntegrationFlows.from(KafkaChannel.REPLY_CHANNEL)
      .filter(Message.class, message -> instanceUUID.equals(message.getHeaders().get("instance_id")))
      .channel(KafkaChannel.REPLY_CHANNEL)
      .get();
  }
}
