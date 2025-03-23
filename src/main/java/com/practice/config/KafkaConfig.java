package com.practice.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Created 3/22/25
 * @Project springboot-microservice-kafka
 * @User Kumar Padigeri
 */
@Configuration
public class KafkaConfig {


    @Bean
    public NewTopic newTopicCreation(){
        return new NewTopic("Topic-Spring-obj",3,(short)1);
    }

}
