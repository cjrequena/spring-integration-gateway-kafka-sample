package com.sample.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {

  /**
   * Jackson builder.
   * @return the jackson2 object mapper builder
   */
  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.indentOutput(true);
    builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return builder;
  }

  /**
   *
   * @return
   */
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper builder = jacksonBuilder().simpleDateFormat("yyyy-MM-dd").modules(new JavaTimeModule()).build();
    builder.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    builder.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    return builder;
  }

}
