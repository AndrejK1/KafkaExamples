package com.andrejk.kafkatest.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class IdEventsProducer extends KafkaProducer<String> {
    public IdEventsProducer(@Value(value = "${kafka.topic.id-events}") String topic,
                            KafkaTemplate<String, String> kafkaTemplate) {
        super(topic, kafkaTemplate);
    }
}
