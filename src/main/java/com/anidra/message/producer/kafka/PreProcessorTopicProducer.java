package com.anidra.message.producer.kafka;

import com.anidra.message.dto.SensorAllocation;
import com.anidra.message.repository.SensorAllocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PreProcessorTopicProducer {

    private final String PRODUCER_TOPIC = "preprocessed";
    private final String GROUP_ID = "MYSQL";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private SensorAllocationRepository sensorAllocationRepository;

    public void preProcessorProducer() {
        log.info("Message to be produced to kafka stream");
        List<SensorAllocation> onlineSensors = sensorAllocationRepository.getAllOnlineSensors();
        log.info("Online Sensors returned size {}", onlineSensors.size());
        kafkaTemplate.send(PRODUCER_TOPIC, "some message");
    }
}
