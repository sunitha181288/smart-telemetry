package com.smartroom.telemetry_service.mqtt;
import org.springframework.stereotype.Component;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MqttInboundHandler {

    private static final Logger log = LoggerFactory.getLogger(MqttInboundHandler.class);

    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public void handle(Message<?> message) {
        log.info("MQTT message received: {}", message.getPayload());
    }

}