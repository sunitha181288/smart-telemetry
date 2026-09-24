package com.smartroom.telemetry_service.kafka;

import com.smartroom.telemetry_service.model.SensorReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class TelemetryProducer {
    private static final Logger log = LoggerFactory.getLogger(TelemetryProducer.class);
    private final KafkaTemplate<String, SensorReading> kafkaTemplate;
    @Value("${kafka.topic.telemetry}")
    private String topic;

    public TelemetryProducer(KafkaTemplate<String, SensorReading> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(SensorReading reading) {
        kafkaTemplate.send(topic, reading.roomId(),reading);
        log.debug("Published to Kafka: room={}", reading.roomId());
    }
}