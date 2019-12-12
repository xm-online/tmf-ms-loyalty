package com.icthh.xm.tmf.ms.loyalty.web.rest;

import com.icthh.xm.tmf.ms.loyalty.service.LoyaltyKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loyalty-kafka")
public class LoyaltyKafkaResource {

    private final Logger log = LoggerFactory.getLogger(LoyaltyKafkaResource.class);

    private LoyaltyKafkaProducer kafkaProducer;

    public LoyaltyKafkaResource(LoyaltyKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.send(message);
    }
}
