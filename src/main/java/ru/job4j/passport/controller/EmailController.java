package ru.job4j.passport.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    @KafkaListener(topics = {"Hello"})
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        log.info("Email Sender: Topic -> " + input.topic());
        log.info("Email Sender: Passport has expired -> " + input.value());
    }
}
