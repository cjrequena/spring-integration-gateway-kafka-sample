package com.sample.configuration;

import java.util.UUID;

/*
 *
 *
 * @author joseluis.nogueira on 10/07/2019
 */
public class KafkaConstants {
  public static final String MEDIA_TYPE_HEADER = "media-type";
  public static final String INSTANCE_ID_HEADER = "instance-id";

  public static final String INSTANCE_ID = UUID.randomUUID().toString();
}
