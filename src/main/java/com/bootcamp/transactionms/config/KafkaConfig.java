package com.bootcamp.transactionms.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
  @Bean
  public NewTopic topic(@Value("${app.kafka.topic:transaction-events}") String topic) {
    return new NewTopic(topic, 1, (short) 1);
  }
}
