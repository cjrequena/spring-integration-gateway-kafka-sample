package com.sample.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.channel.KafkaChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.IntegrationConverter;
import org.springframework.integration.dsl.HeaderEnricherSpec;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Configuration
@EnableIntegration
@Slf4j
public class GatewayConfiguration {
  private static final String[] OUTPUT_STREAMS = {KafkaChannel.REQUEST_CHANNEL, KafkaChannel.REQUEST_CHANNEL_2};
  private static final String[] INPUT_STREAMS = {KafkaChannel.REPLY_CHANNEL, KafkaChannel.REPLY_CHANNEL_2};

  @Autowired
  private ApplicationContext applicationContext;

  @PostConstruct
  public void setUp() {
    AutowireCapableBeanFactory beanFactory = this.applicationContext.getAutowireCapableBeanFactory();
    for (String streamName : OUTPUT_STREAMS) {
      StandardIntegrationFlow integrationFlows = IntegrationFlows.from(streamName)
        .enrichHeaders(HeaderEnricherSpec::headerChannelsToString)
        .enrichHeaders(headerEnricherSpec -> headerEnricherSpec.header(KafkaConstants.INSTANCE_ID_HEADER, KafkaConstants.INSTANCE_ID))
        .channel(streamName)
        .get();
      beanFactory.initializeBean(integrationFlows, "requestFlow" + streamName);
    }
    for (String streamName : INPUT_STREAMS) {
      StandardIntegrationFlow integrationFlows = IntegrationFlows.from(streamName)
        .filter(Message.class, message -> KafkaConstants.INSTANCE_ID.equals(message.getHeaders().get(KafkaConstants.INSTANCE_ID_HEADER)))
        .channel(streamName)
        .get();
      beanFactory.initializeBean(integrationFlows, "responseFlow" + streamName);
    }
  }

  @Bean
  @IntegrationConverter
  public GenericConverter genericConverter() {
    return new JsonMessageConverter();
  }

  public class JsonMessageConverter implements GenericConverter {
    private final ObjectMapper MAPPER;

    public JsonMessageConverter() {
      MAPPER = new MappingJackson2MessageConverter().getObjectMapper();
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
      return Collections.singleton(new ConvertiblePair(byte[].class, Object.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
      Object result;
      try {
        result = source instanceof String ? MAPPER.readValue((String) source, targetType.getType()) : MAPPER.readValue((byte[]) source, targetType.getType());
      } catch (IOException e) {
        log.error("Error parsing json {} to {}", source instanceof String ? source : new String((byte[]) source), sourceType.getType().getTypeName(), e);
        result = null;
      }

      return result;
    }
  }
}
