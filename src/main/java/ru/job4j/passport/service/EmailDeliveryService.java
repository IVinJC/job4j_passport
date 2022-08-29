package ru.job4j.passport.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailDeliveryService {
    private final KafkaTemplate<Integer, String> template;

    public EmailDeliveryService(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    @KafkaListener(topics = {"expired"})
    public void sender(ConsumerRecord<Integer, String> input) {
        template.send("Hello", "How are you?");
        template.send("Hello", input.value());
    }
}
