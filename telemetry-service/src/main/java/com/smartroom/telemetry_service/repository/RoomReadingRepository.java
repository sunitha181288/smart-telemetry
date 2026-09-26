package com.smartroom.telemetry_service.repository;

import com.smartroom.telemetry_service.model.RoomReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomReadingRepository extends JpaRepository<RoomReading, Long> {

    List<RoomReading> findByRoomIdOrderByTimestampDesc(String roomId);
}
