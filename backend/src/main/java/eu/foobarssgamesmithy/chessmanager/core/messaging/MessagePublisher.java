package eu.foobarssgamesmithy.chessmanager.core.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public MessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void sendMessage(String topic, Object msg) {
        kafkaTemplate.send(topic, msg);
    }
}
