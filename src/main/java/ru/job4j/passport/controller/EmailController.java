package ru.job4j.passport.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EmailController {
    private final KafkaTemplate<Integer, String> template;

    public EmailController(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    @KafkaListener(topics = {"Hello"})
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        System.out.println(input.value());
    }
}
