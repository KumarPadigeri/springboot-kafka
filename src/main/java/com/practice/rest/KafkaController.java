package com.practice.rest;


/*
 * @Created 3/22/25
 * @Project springboot-microservice-kafka
 * @User Kumar Padigeri
 */

import com.practice.domain.Employee;
import com.practice.service.KafkaMessagePubliser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaMessagePubliser kafkaMessagePubliser;


    @GetMapping(path = "/sendmsg/{msg}")
    ResponseEntity<String> publishMessage(@PathVariable("msg") String message) {
        try {
            kafkaMessagePubliser.sendMessage( message);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILURE");
        }
    }

    @PostMapping(path = "/sendmsg")
    ResponseEntity<String> publishMessageObj(@RequestBody Employee employee) {
        try {
            kafkaMessagePubliser.sendMessageObj( employee);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILURE");
        }
    }

    @PostMapping(path = "/sendMsgRetry")
    ResponseEntity<String> publishMessageObjRetry(@RequestBody Employee employee) {
        try {
            kafkaMessagePubliser.sendMessageObjRetry( employee);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILURE");
        }
    }
}
