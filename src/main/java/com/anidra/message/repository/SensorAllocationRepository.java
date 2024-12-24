package com.anidra.message.repository;

import com.anidra.message.dto.SensorAllocation;
import java.util.List;

public interface SensorAllocationRepository {

    List<SensorAllocation> getAllOnlineSensors();

}
