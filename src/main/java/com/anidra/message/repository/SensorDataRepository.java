package com.anidra.message.repository;

import com.anidra.message.dto.VitalsDto;

public interface SensorDataRepository {

    void saveSensorData(VitalsDto sensorData);

//    List<VitalsDto> getAllSensorData();

}