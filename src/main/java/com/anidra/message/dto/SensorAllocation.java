package com.anidra.message.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class SensorAllocation implements Serializable {

    private String sensorId;
    private String sensorAllocationId;

}
