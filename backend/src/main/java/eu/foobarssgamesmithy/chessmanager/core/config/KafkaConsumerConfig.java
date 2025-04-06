package eu.foobarssgamesmithy.chessmanager.core.config;

import eu.foobarssgamesmithy.chessmanager.core.messaging.data.BaseChessManagerMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    private final SslBundles sslBundles;

    public KafkaConsumerConfig(KafkaProperties kafkaProperties, SslBundles sslBundles) {
        this.kafkaProperties = kafkaProperties;
        this.sslBundles = sslBundles;
    }

    @Bean
    public ConsumerFactory<String, BaseChessManagerMessage> consumerFactory() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties(this.sslBundles));
//        props.put(
//                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                bootstrapAddress);
//        props.put(
//                ConsumerConfig.GROUP_ID_CONFIG,
//                groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(
                BaseChessManagerMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BaseChessManagerMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BaseChessManagerMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
