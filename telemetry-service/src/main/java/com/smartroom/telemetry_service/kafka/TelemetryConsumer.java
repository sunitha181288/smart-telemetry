package com.smartroom.telemetry_service.kafka;

import com.smartroom.telemetry_service.model.RoomReading;
import com.smartroom.telemetry_service.model.SensorReading;
import com.smartroom.telemetry_service.repository.RoomReadingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TelemetryConsumer {

    private static final Logger log = LoggerFactory.getLogger(TelemetryConsumer.class);

    private final RoomReadingRepository repository;

    public TelemetryConsumer(RoomReadingRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${kafka.topic.telemetry}")
    public void consume(SensorReading reading) {
        RoomReading entity = new RoomReading(
                reading.roomId(),
                reading.sensorType(),
                reading.value(),
                reading.unit(),
                reading.timestamp()
        );
        repository.save(entity);
        log.info("Saved to DB: room={}, sensor={}, value={}",
                reading.roomId(), reading.sensorType(), reading.value());
    }
}
