package com.anidra.message.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class VitalsDto implements Serializable {

    @JsonProperty("sakey")
    private String sensorDataKey;

    @JsonProperty("sid")
    private String sensorId;

    @JsonProperty("spo2")
    private String spO2;

    @JsonProperty("resp")
    private String respiratoryRate;

    @JsonProperty("pr")
    private String pulseRate;

    @JsonProperty("temp")
    private String temperature;

    @JsonProperty("dia")
    private String diaPressure;

    @JsonProperty("sys")
    private String sysPressure;

    private String createTimeStamp;

}
