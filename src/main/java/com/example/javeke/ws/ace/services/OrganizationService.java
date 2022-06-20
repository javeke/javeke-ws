package com.example.javeke.ws.ace.services;

import com.example.javeke.ws.ace.models.dao.Organization;
import com.example.javeke.ws.ace.models.dto.DeviceData;
import com.example.javeke.ws.ace.models.dto.DeviceDto;
import com.example.javeke.ws.ace.repositories.IOrganizationRepository;
import com.example.javeke.ws.ace.util.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrganizationService {

    private final IOrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(IOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationByOrganizationId(String organizationId){
        return organizationRepository.findByOrganizationId(organizationId);
    }

    public Organization addOrganization(Organization organization){

        String randomId = UUID.randomUUID().toString();

        Organization found = organizationRepository.findByOrganizationId(randomId);

        while (found != null){
            randomId = UUID.randomUUID().toString();
            found = organizationRepository.findByOrganizationId(randomId);
        }
        organization.setOrganizationId(randomId);

        return organizationRepository.save(organization);
    }

    public ActionResponse<List<Organization>> removeOrganization(String organizationId) {
        int numOfDeleted = organizationRepository.deleteByOrganizationId(organizationId);
        boolean wasDeleted = numOfDeleted > 0;
        List<Organization> organizations =  organizationRepository.findAll();
        return new ActionResponse<>(wasDeleted, organizations);
    }

    public ActionResponse<Organization> updateOrganization(String organizationId, Organization updatedOrganization){
        Organization found = organizationRepository.findByOrganizationId(organizationId);

        if(updatedOrganization.getDescription() != null){
            found.setDescription(updatedOrganization.getDescription());
        }

        if(updatedOrganization.getName() != null){
            found.setName(updatedOrganization.getName());
        }

        organizationRepository.save(found);
        return new ActionResponse<>(true, found);
    }

    public List<DeviceDto> getDevices(String organizationId){
        return organizationRepository.findByOrganizationId(organizationId).getDevices();
    }

    public DeviceDto getDeviceById(String organizationId, String deviceId){
        return organizationRepository.findByOrganizationId(organizationId).getDevices().stream().filter(deviceDto -> deviceDto.getId().equals(deviceId)).findFirst().orElse(null);
    }

    public ActionResponse<List<DeviceDto>> addDevice(String organizationId, DeviceDto device){
        Organization organization = organizationRepository.findByOrganizationId(organizationId);

        device.setId(UUID.randomUUID().toString());

        if(organization.getDevices() == null){
            ArrayList<DeviceDto> devices = new ArrayList<>();
            organization.setDevices(devices);
        }

        boolean wasAdded = organization.getDevices().add(device);
        if (wasAdded) organizationRepository.save(organization);
        return new ActionResponse<>(wasAdded, organization.getDevices());
    }

    public ActionResponse<List<DeviceDto>> removeDevice(String organizationId, String deviceId){
        Organization organization = organizationRepository.findByOrganizationId(organizationId);
        ActionResponse<List<DeviceDto>> response = new ActionResponse<>();

        if(organization.getDevices() == null){
            response.setSuccessful(false);
            response.setData(null);
            return response;
        }

        boolean wasDeleted = organization.getDevices().removeIf((DeviceDto device)-> device.getId().equals(deviceId));

        if(wasDeleted) organizationRepository.save(organization);

        response.setSuccessful(wasDeleted);
        response.setData(organization.getDevices());
        return response;
    }

    public ActionResponse<DeviceDto> toggleDeviceState(String organizationId, String deviceId, boolean state){
        Organization organization = organizationRepository.findByOrganizationId(organizationId);

        ActionResponse<DeviceDto> response = new ActionResponse<>();

        for(DeviceDto device : organization.getDevices()){
            if(device.getId().equals(deviceId)){
                device.setEnabled(state);
                organizationRepository.save(organization);
                response.setSuccessful(true);
                response.setData(device);
                return response;
            }
        }

        response.setSuccessful(false);
        response.setData(null);
        return response;
    }

    public ActionResponse<DeviceDto> updateData(String organizationId, String deviceId, DeviceData data){
        Organization organization = organizationRepository.findByOrganizationId(organizationId);

        ActionResponse<DeviceDto> response  = new ActionResponse<>();

        if(organization == null){
            response.setData(null);
            response.setSuccessful(false);
            return response;
        }

        Optional<DeviceDto> deviceDtoOptional = organization.getDevices().stream().filter(deviceDto -> deviceDto.getId().equals(deviceId)).findFirst();

        if(!deviceDtoOptional.isPresent()){
            response.setData(null);
            response.setSuccessful(false);
            return response;
        }

        DeviceDto device = deviceDtoOptional.get();

        if(device.getDataPoints() == null || (device.getDataPoints().size() == 0)){
            ArrayList<DeviceData> newList = new ArrayList<>();
            newList.add(data);
            device.setDataPoints(newList);
        }
        else {
            device.getDataPoints().add(data);
        }

        for(DeviceDto organizationDevice : organization.getDevices()){
            if(organizationDevice.getId().equals(device.getId())){
                organizationDevice.setDataPoints(device.getDataPoints());
                break;
            }
        }

        organizationRepository.save(organization);
        response.setSuccessful(true);
        response.setData(device);
        return response;
    }
}
