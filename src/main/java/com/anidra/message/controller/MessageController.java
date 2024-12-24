package com.anidra.message.controller;

import com.anidra.message.consumer.kafka.SensorTopicConsumer;
import com.anidra.message.producer.kafka.PreProcessorTopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private SensorTopicConsumer sensorTopicConsumer;

    @Autowired
    private PreProcessorTopicProducer preProcessorTopicProducer;

/*
    public MessageController(SensorTopicConsumer sensorTopicConsumer, PreProcessorTopicProducer preProcessorTopicProducer) {
        this.sensorTopicConsumer = sensorTopicConsumer;
        this.preProcessorTopicProducer = preProcessorTopicProducer;
    }
*/

    @PostMapping("/publishEvent")
    public void sendMessageToKafka(@RequestBody String message) {
        sensorTopicConsumer.consumeMessage(message);
    }

    @PostMapping("/publishMessage")
    public void publishMessageToKafka(@RequestBody String message) {
        preProcessorTopicProducer.preProcessorProducer();
//        sensorTopicConsumer.sendMessage(message);
    }

}
