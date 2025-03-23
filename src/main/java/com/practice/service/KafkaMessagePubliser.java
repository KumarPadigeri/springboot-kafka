package com.practice.service;


/*
 * @Created 3/22/25
 * @Project springboot-microservice-kafka
 * @User Kumar Padigeri
 */

import com.practice.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaMessagePubliser {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage( String message) {
        CompletableFuture<SendResult<String, Object>> response = kafkaTemplate.send("Topic-Spring-New", message);
        response.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Exception : ", ex);

            } else {
                log.info("Message sent: {}, {} ", result.getProducerRecord(), result.getRecordMetadata());
            }
        });
    }

    public void sendMessageObj(Employee employee) {

        CompletableFuture<SendResult<String, Object>> response = kafkaTemplate.send("Topic-Spring-obj", employee);
        response.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Exception : ", ex);

            } else {
                log.info("Message sent: {}, {}  {} ", result.getProducerRecord(), result.getRecordMetadata(), employee.toString());
            }
        });
    }

    public void sendMessageObjRetry(Employee employee) {

        CompletableFuture<SendResult<String, Object>> response = kafkaTemplate.send("Retry-Topic", employee);
        response.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Exception : ", ex);

            } else {
                log.info("Message sent: {}, {}  {} ", result.getProducerRecord(), result.getRecordMetadata(), employee.toString());
            }
        });
    }
}
