package com.smartroom.telemetry_service.mqtt;
import tools.jackson.databind.ObjectMapper;
import com.smartroom.telemetry_service.model.SensorReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MqttInboundHandler {

    private static final Logger log = LoggerFactory.getLogger(MqttInboundHandler.class);

    private final ObjectMapper objectMapper;

    public MqttInboundHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public void handle(Message<?> message) {
        String payload = message.getPayload().toString();
        try {
            SensorReading reading = objectMapper.readValue(payload, SensorReading.class);
            log.info("Parsed reading: room={}, sensor={}, value={}",
                    reading.roomId(), reading.sensorType(), reading.value());
        } catch (Exception e) {
            log.warn("Dropping malformed message: {}", e.getMessage());
        }
    }
}