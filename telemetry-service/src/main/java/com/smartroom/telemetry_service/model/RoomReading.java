package com.smartroom.telemetry_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "room_reading")
public class RoomReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String sensorType;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Instant timestamp;

    protected RoomReading() {
    }

    public RoomReading(String roomId, String sensorType, double value, String unit, Instant timestamp) {
        this.roomId = roomId;
        this.sensorType = sensorType;
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
