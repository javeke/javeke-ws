package com.example.javeke.ws.ace.controllers;

import com.example.javeke.ws.ace.models.SocketControlMessage;
import com.example.javeke.ws.ace.models.SocketDataMessage;
import com.example.javeke.ws.ace.models.dto.DeviceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;

@Controller
public class SocketController {

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @MessageMapping("/data/{deviceId}")
    @SendTo("/device/data/{deviceId}")
    public SocketDataMessage handleDataRecord(@DestinationVariable("deviceId") String deviceId, @RequestBody SocketDataMessage message){
        logger.info("Device Id: {} -- Message Received: {}", deviceId, message.getMessage());
        logger.info("Device Id: {} -- Data Received: {} - {}",deviceId, message.getData().getParamName(), message.getData().getParamValue());

        DeviceData deviceData =  new DeviceData();
        deviceData.setParamName("Temperature");
        String data = String.format("New Temperature Reading from %s is %d", deviceId, randomGenerator.nextInt(128));
        deviceData.setParamValue(data);

        return new SocketDataMessage(deviceData, "message received");
    }

    @MessageMapping("/control/{deviceId}")
    @SendTo("/device/control/{deviceId}")
    public SocketControlMessage handleControl(@DestinationVariable("deviceId") String deviceId, @RequestBody SocketControlMessage message){
        logger.info("Device Id: {} -- Message Received: {}", deviceId, message.getMessage());
        logger.info("Device Id: {} -- Control Request Received: {}",deviceId, message.getControl());

//        start doing things with the control


        String control = String.format("Turn off LED 1 on device %s", deviceId);

        return new SocketControlMessage(control, "control received");
    }
}

