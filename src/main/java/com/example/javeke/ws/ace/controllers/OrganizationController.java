package com.example.javeke.ws.ace.controllers;

import com.example.javeke.ws.ace.exceptions.FailedActionException;
import com.example.javeke.ws.ace.models.dao.Organization;
import com.example.javeke.ws.ace.models.dto.DeviceDto;
import com.example.javeke.ws.ace.models.dto.OrganizationDto;
import com.example.javeke.ws.ace.services.OrganizationService;
import com.example.javeke.ws.ace.util.ActionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final static Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getOrganizations(){
        List<OrganizationDto> organizations = organizationService.getOrganizations().stream().map(OrganizationDto::new).collect(Collectors.toList());

        if(organizations.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> addOrganization( @RequestBody OrganizationDto organizationDto) throws FailedActionException{

        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDto, organization);

        logger.info("Trying to add new organization");

        Organization newOrganization = organizationService.addOrganization(organization);

        if (newOrganization == null){
            logger.info("Failed to add new organization");
            throw new FailedActionException("Organization Not Added");
        }

        OrganizationDto organizationResponse = new OrganizationDto();

        BeanUtils.copyProperties(newOrganization, organizationResponse);
        logger.info("New organization added successfully");

        return new ResponseEntity<>(organizationResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("organizationId") String organizationId){
        logger.info("Fetching the data for organization with id {}", organizationId);
        Organization organization = organizationService.getOrganizationByOrganizationId(organizationId);

        OrganizationDto organizationResponse = new OrganizationDto();
        BeanUtils.copyProperties(organization, organizationResponse);
        logger.info("Successfully fetched data for organization with id {}", organizationId);
        return new ResponseEntity<>(organizationResponse, HttpStatus.OK);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<OrganizationDto> updateOrganization(@RequestBody OrganizationDto organizationDto, @PathVariable("organizationId") String organizationId) throws FailedActionException{
        logger.info("Attempting to update the data for organization with id {}", organizationId);
        Organization organizationRequest = new Organization();

        BeanUtils.copyProperties(organizationDto, organizationRequest);
        ActionResponse<Organization> actionResponse = organizationService.updateOrganization(organizationId, organizationRequest);

        if(!actionResponse.isSuccessful()){
            logger.info("Failed to update the data for organization with id {}", organizationId);
            throw new FailedActionException("Organization Not Updated");
        }
        OrganizationDto organizationResponse = new OrganizationDto();
        BeanUtils.copyProperties(actionResponse.getData(), organizationResponse);
        logger.info("Successfully updated the data for organization with id {}", organizationId);
        return new ResponseEntity<>(organizationResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<List<OrganizationDto>> deleteOrganization( @PathVariable("organizationId") String organizationId ) throws FailedActionException{
        ActionResponse<List<Organization>> actionResponse = organizationService.removeOrganization(organizationId);

        logger.info("Attempting to remove organization with id {}", organizationId);

        if (!actionResponse.isSuccessful()){
            logger.error("Could not remove organization with id {}", organizationId);
            throw new FailedActionException("Organization Not Removed");
        }
        logger.info("Successfully removed organization with id {}", organizationId);

        List<OrganizationDto> organizationDtos = actionResponse.getData().stream().map(OrganizationDto::new).collect(Collectors.toList());

        return new ResponseEntity<>(organizationDtos, HttpStatus.OK);
    }

    @GetMapping("/{organizationId}/devices")
    public ResponseEntity<List<DeviceDto>> getDevices(@PathVariable("organizationId") String organizationId){
        List<DeviceDto> devices = organizationService.getDevices(organizationId);

        if(devices.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/{organizationId}/devices/{deviceId}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable("organizationId") String organizationId, @PathVariable("deviceId") String deviceId){
        DeviceDto device = organizationService.getDeviceById(organizationId, deviceId);

        if(device == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @PostMapping("/{organizationId}/devices")
    public ResponseEntity<List<DeviceDto>> addDevice(@PathVariable("organizationId") String organizationId, @RequestBody DeviceDto device) throws FailedActionException {

        ActionResponse<List<DeviceDto>> actionResponse = organizationService.addDevice(organizationId, device);

        if(!actionResponse.isSuccessful()){
            throw new FailedActionException("Device Not Added");
        }

        return new ResponseEntity<>(actionResponse.getData(), HttpStatus.OK);
    }

    @DeleteMapping("/{organizationId}/devices/{deviceId}")
    public ResponseEntity<List<DeviceDto>> removeDevice(@PathVariable("organizationId") String organizationId, @PathVariable("deviceId") String deviceId) throws FailedActionException{

        ActionResponse<List<DeviceDto>> actionResponse = organizationService.removeDevice(organizationId, deviceId);

        if(!actionResponse.isSuccessful()){
            throw new FailedActionException("Device Not Removed");
        }

        return new ResponseEntity<>(actionResponse.getData(), HttpStatus.OK);
    }

    @ExceptionHandler(FailedActionException.class)
    public ResponseEntity<Object> handleFailedAction(FailedActionException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_MODIFIED);
    }
}
