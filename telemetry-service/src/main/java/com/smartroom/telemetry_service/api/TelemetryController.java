package com.smartroom.telemetry_service.api;

import com.smartroom.telemetry_service.model.RoomReading;
import com.smartroom.telemetry_service.repository.RoomReadingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class TelemetryController {

    private final RoomReadingRepository repository;

    public TelemetryController(RoomReadingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{roomId}/latest")
    public ResponseEntity<RoomReading> latest(@PathVariable String roomId) {
        return repository.findFirstByRoomIdOrderByTimestampDesc(roomId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{roomId}/history")
    public List<RoomReading> history(
            @PathVariable String roomId,
            @RequestParam(required = false) String sensorType) {
        if (sensorType != null) {
            return repository.findByRoomIdAndSensorTypeOrderByTimestampDesc(roomId, sensorType);
        }
        return repository.findByRoomIdOrderByTimestampDesc(roomId);
    }
}
