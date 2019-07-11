package com.sample.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
@Data
public class RequestV2DTO implements Serializable{
  public static final String MEDIA_TYPE = "application/vnd.request.v2+json";

  private String text;
}
