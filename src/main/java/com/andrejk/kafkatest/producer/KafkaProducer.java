package com.andrejk.kafkatest.producer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public abstract class KafkaProducer<T> {
    @Getter
    private final String topic;
    private final KafkaTemplate<String, T> kafkaTemplate;

    public CompletableFuture<SendResult<String, T>> sendMessage(T message) {
        return kafkaTemplate.send(topic, message);
    }

    public CompletableFuture<SendResult<String, T>> sendMessage(String key, T message) {
        return kafkaTemplate.send(topic, key, message);
    }

    public CompletableFuture<SendResult<String, T>> sendMessage(int partition, String key, T message) {
        return kafkaTemplate.send(topic, partition, key, message);
    }

    public CompletableFuture<SendResult<String, T>> sendMessage(int partition, long timestamp, String key, T message) {
        return kafkaTemplate.send(topic, partition, timestamp, key, message);
    }
}
