package com.ythalorossy.kafkaclient.configurations;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.ythalorossy.kafkaclient.YCustomMessage;
import com.ythalorossy.kafkaclient.serializations.YCustomMessageDeserializer;
import com.ythalorossy.kafkaclient.serializations.YCustomMessageSerializer;

@Configuration
@EnableConfigurationProperties(value = { KafkaConfigurationProperties.class })
public class KafkaConfiguration {

    private final KafkaConfigurationProperties kafkaConfigProps;

    KafkaConfiguration(KafkaConfigurationProperties kafkaConfigurationProperties) {
        this.kafkaConfigProps = kafkaConfigurationProperties;
    }

    @Bean
    public ProducerFactory<String, YCustomMessage> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(".", kafkaConfigProps.getProducer().getBootstrapServers()));                
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, YCustomMessageSerializer.class);
        return new DefaultKafkaProducerFactory<String, YCustomMessage>(props);
    }

    @Bean
    public ConsumerFactory<String, YCustomMessage> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                String.join(".", kafkaConfigProps.getConsumer().getBootstrapServers()));
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, YCustomMessageDeserializer.class);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfigProps.getConsumer().getAutoOffsetReset());
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, YCustomMessage> kafkaTemplate() {
        var kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setConsumerFactory(consumerFactory());
        return kafkaTemplate;
    }

}
