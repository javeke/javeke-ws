package com.example.javeke.ws.ace.controllers;

import com.example.javeke.ws.ace.models.SocketControlMessage;
import com.example.javeke.ws.ace.models.SocketDataMessage;
import com.example.javeke.ws.ace.models.dto.DeviceData;
import com.example.javeke.ws.ace.models.dto.DeviceDto;
import com.example.javeke.ws.ace.services.OrganizationService;
import com.example.javeke.ws.ace.util.ActionResponse;
import com.example.javeke.ws.ace.util.ControlType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SocketController {

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    private final OrganizationService organizationService;

    @Autowired
    public SocketController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @MessageMapping("/data/organizations/{organizationId}/devices/{deviceId}")
    @SendTo("/deviceData/organizations/{organizationId}/devices/{deviceId}")
    public SocketDataMessage handleDataRecord(@DestinationVariable("organizationId") String organizationId, @DestinationVariable("deviceId") String deviceId, @RequestBody SocketDataMessage message){
        logger.info("Device Id: {} -- Message Received: {}", deviceId, message.getMessage());
        logger.info("Device Id: {} -- Data Received: {} - {}", deviceId, message.getData().getParamName(), message.getData().getParamValue());
        logger.info("Device Id: {} -- Time Received: {}", deviceId, message.getData().getCreatedAt());

        DeviceData deviceData =  new DeviceData();
        deviceData.setParamName("Temperature");
        deviceData.setParamValue(message.getData().getParamValue());
        deviceData.setCreatedAt(message.getData().getCreatedAt());

        ActionResponse<DeviceDto> actionResponse = organizationService.updateData(organizationId, deviceId, message.getData());

        if(!actionResponse.isSuccessful()){
            logger.error("Failed to add data point to device with id {}", deviceId);
        }

        return new SocketDataMessage(deviceData, "message received");
    }

    @MessageMapping("/control/organizations/{organizationId}/devices/{deviceId}")
    @SendTo("/controlData/organizations/{organizationId}/devices/{deviceId}")
    public SocketControlMessage handleControl(@DestinationVariable("organizationId") String organizationId, @DestinationVariable("deviceId") String deviceId, @RequestBody SocketControlMessage controlMessage){
        logger.info("Device Id: {} -- Message Received: {}", deviceId, controlMessage.getMessage());
        logger.info("Device Id: {} -- Control Request Received: {}",deviceId, controlMessage.getControl());

        ControlType controlType = ControlType.valueOf(controlMessage.getMessage());

        ActionResponse<DeviceDto> response = new ActionResponse<>();
        SocketControlMessage socketControlMessage = new SocketControlMessage();

        switch (controlType){
            case StateChange:
                response = organizationService.toggleDeviceState(organizationId, deviceId, controlMessage.getControl().isEnabled());
                break;
            default:
                break;
        }

        socketControlMessage.setControl(response.getData());

        if(!response.isSuccessful()){
            logger.error("Failed to send control signal to device with id {}", deviceId);
        }
        logger.info("Sending control signal to device with id {}", deviceId);
        socketControlMessage.setMessage(controlType.toString());
        return socketControlMessage;
    }
}

