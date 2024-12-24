package com.anidra.message.consumer.kafka;

import com.anidra.message.dto.VitalsDto;
import com.anidra.message.repository.SensorDataRepository;
import com.anidra.message.util.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class SensorTopicConsumer {

    private final String CONSUMER_TOPIC_NAME = "sensor";
    private final String GROUP_ID = "MYSQL";

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @KafkaListener(topics = CONSUMER_TOPIC_NAME, groupId = GROUP_ID)
    public void consumeMessage(String message) {
        log.info("SensorTopicConsumer::consumeMessage received message on topic={} message={} ", CONSUMER_TOPIC_NAME, message);

        if (CommonUtils.isValidJson(message)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                VitalsDto vitalsData = mapper.readValue(message, VitalsDto.class);
                String utcDateTime = CommonUtils.timeInUtcString(LocalDateTime.now());
                vitalsData.setCreateTimeStamp(utcDateTime);

                sensorDataRepository.saveSensorData(vitalsData);

            } catch (JsonProcessingException e) {
                log.error("SensorTopicConsumer unable to convert to incoming data from Kafka {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

}
