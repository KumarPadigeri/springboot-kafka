package com.practice.service;


import com.practice.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/*
 * @Created 3/22/25
 * @Project springboot-microservice-kafka
 * @User Kumar Padigeri
 */
@Service
@AllArgsConstructor
@Slf4j
public class KafkaMessageConsumer {


   /* @KafkaListener(topics = "Topic-Spring-New", groupId = "consumer-group")
    public void consumeKafkaMsg1(String message){
        log.info("Consumer1 pulled message : {}",message);
    }

    @KafkaListener(topics = "Topic-Spring-New", groupId = "consumer-group")
    public void consumeKafkaMsg2(String message){
        log.info("Consumer2 pulled message : {}",message);
    }

    @KafkaListener(topics = "Topic-Spring-New", groupId = "consumer-group")
    public void consumeKafkaMsg3(String message){
        log.info("Consumer3 pulled message : {}",message);
    }

    @KafkaListener(topics = "Topic-Spring-New", groupId = "consumer-group")
    public void consumeKafkaMsg4(String message){
        log.info("Consumer4 pulled message : {}",message);
    }*/

   @KafkaListener(topics = "Topic-Spring-obj", groupId = "consumer-group")
   public void consumeKafkaMsgObj(Employee employee){
       log.info("Consumer pulled message obj : {}",employee.toString());
   }


    @KafkaListener(topics = "Topic-Spring-New", groupId = "consumer-group-parti",
            topicPartitions = {@TopicPartition(topic = "Topic-Spring-New",partitions = {"2"})})
    public void consumeKafkaMsg3(String message){
        log.info("Consumer pulled message : {}",message);
    }

    @KafkaListener(topics = "Retry-Topic", groupId = "my-retry-consumer-group")
    @RetryableTopic(attempts = "4",exclude = NullPointerException.class)
    public void consumeKafkaMsgObjRetry(Employee employee){
       if( 10> employee.getAge())
       {
           throw new RuntimeException("Age Validation");
       }else{
           log.info("Consumer pulled message obj : {}", employee);
       }

    }


    @DltHandler
    void dltMessages(Employee employee, @Header(KafkaHeaders.RECEIVED_TOPIC )String topic){
       log.info("Message sent to DLT : {}, : TOPIC : {}",employee, topic);

    }

}
