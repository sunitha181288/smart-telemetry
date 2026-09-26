package com.smartroom.telemetry_service.model;

import java.time.Instant;

public record SensorReading(
        String roomId,
        String sensorType,
        double value,
        String unit,
        Instant timestamp
) {}