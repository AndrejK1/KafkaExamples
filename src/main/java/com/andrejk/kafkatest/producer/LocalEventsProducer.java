package com.andrejk.kafkatest.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LocalEventsProducer extends KafkaProducer<String> {
    public LocalEventsProducer(@Value(value = "${kafka.topic.local-events}") String topic,
                               KafkaTemplate<String, String> kafkaTemplate) {
        super(topic, kafkaTemplate);
    }
}
