package com.andrejk.kafkatest.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(id = "id-events-listener",
            topics = "${kafka.topic.id-events}",
            groupId = "${kafka.consumer.groups.id}",
            concurrency = "2")
    public void listenForIdEvents(String idMessage,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                                  @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key) {
        LOGGER.info("Received id message '{}' with key '{}' from partition {}", idMessage, key, partition);
    }

    @KafkaListener(id = "local-events-listener",
            topics = "${kafka.topic.local-events}",
            groupId = "${kafka.consumer.groups.local}")
    public void listenForLocalEvents(String idMessage,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                                     @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key) {
        LOGGER.info("Received local message '{}' with key '{}' from partition {}", idMessage, key, partition);
    }

    @KafkaListener(id = "id-events-with-key-listener",
            topics = "${kafka.topic.id-events}",
            groupId = "${kafka.consumer.groups.not-null-id}",
            filter = "kafkaMessageWithKeyFilter")
    public void listenForIdEventsFirstPartition(String idMessage,
                                                @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                                                @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key) {
        LOGGER.info("Received local message '{}' with key '{}' DIRECTLY from partition {}", idMessage, key, partition);
    }
}
