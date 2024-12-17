package com.anidra.db.consumer;

import com.anidra.db.consumer.dto.VitalsDto;
import com.anidra.db.consumer.repository.SensorDataRepository;
import com.anidra.db.consumer.util.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MysqlConsumer {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @KafkaListener(topics = "preprocessed", groupId = "MYSQL")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);

        if (CommonUtils.isValidJson(message)) {
            ObjectMapper mapper = new ObjectMapper();
            VitalsDto vitalsData = null;
            try {
                vitalsData = mapper.readValue(message, VitalsDto.class);
                String utcDateTime = CommonUtils.timeInUtcString(LocalDateTime.now());
                vitalsData.setCreateTimeStamp(utcDateTime);

                sensorDataRepository.saveSensorData(vitalsData);
            } catch (JsonProcessingException e) {
                log.error("Unable to convert to Pojo {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

}
