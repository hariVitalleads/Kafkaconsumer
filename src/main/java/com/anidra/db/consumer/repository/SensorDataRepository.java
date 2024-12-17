package com.anidra.db.consumer.repository;

import com.anidra.db.consumer.dto.VitalsDto;

import java.util.List;

public interface SensorDataRepository {

    void saveSensorData(VitalsDto sensorData);

    List<VitalsDto> getAllSensorData();

}