package com.andrejk.kafkatest;

import com.andrejk.kafkatest.producer.IdEventsProducer;
import com.andrejk.kafkatest.producer.LocalEventsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class KafkaScheduledExecutor {
    private final LocalEventsProducer localEventsProducer;
    private final IdEventsProducer idEventsProducer;

    @Scheduled(initialDelay = 10 * 1000L, fixedRate = 10 * 1000L)
    public void sendPackage() throws ExecutionException, InterruptedException, TimeoutException {
        String uniqueId = "key-123";

        String message = String.valueOf(Math.round(Math.abs(Math.random() * 10_000)));

        if (Math.random() > 0.8) {
            idEventsProducer.sendMessage(uniqueId, message).get(10, TimeUnit.MINUTES);
            log.info("Sent message '{}' WITH KEY '{}' to topic {}", message, uniqueId, idEventsProducer.getTopic());
        } else {
            idEventsProducer.sendMessage(message).get(10, TimeUnit.MINUTES);
            log.info("Sent message '{}' to topic {}", message, idEventsProducer.getTopic());
        }
    }
}
