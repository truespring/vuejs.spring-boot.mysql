package com.taskagile.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Writer;

@Slf4j
@NoArgsConstructor
public final class JsonUtils {

  public static String toJson(final Object object) {
    final ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error("Failed to convert object to JSON string", e);
      return null;
    }
  }

  public static <T> T toObject(final String json, final Class<T> clazz) {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(json, clazz);
    } catch (IOException e) {
      log.error("Failed to convert string`" + json + "` class `" + clazz.getName() + "`", e);
      return null;
    }
  }

  public static void write(final Writer writer, final Object value) throws IOException {
    new ObjectMapper().writeValue(writer, value);
  }
}
