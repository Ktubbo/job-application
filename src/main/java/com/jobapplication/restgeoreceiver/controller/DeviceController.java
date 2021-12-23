package com.jobapplication.restgeoreceiver.controller;

import com.jobapplication.restgeoreceiver.domain.dto.DeviceDto;
import com.jobapplication.restgeoreceiver.exceptions.DeviceNotFoundException;
import com.jobapplication.restgeoreceiver.mapper.DeviceMapper;
import com.jobapplication.restgeoreceiver.service.DeviceDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DeviceController {

    private final DeviceDBService dbService;
    private final DeviceMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/devices")
    public List<DeviceDto> getDevices() {
        return mapper.mapToDeviceDtoList(dbService.getAllDevices());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/devices/{deviceId}")
    public DeviceDto getDevice(@PathVariable Long deviceId) throws DeviceNotFoundException {
        return mapper.mapToDeviceDto(dbService.getDevice(deviceId).orElseThrow(DeviceNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/devices/{deviceId}")
    public void deleteDevice(@PathVariable Long deviceId) {
        dbService.deleteDevice(deviceId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/devices", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDto createDevice(@RequestBody DeviceDto deviceDto) {
        return mapper.mapToDeviceDto(dbService.saveDevice(mapper.mapToDevice(deviceDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/devices", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDto updateDevice(@RequestBody DeviceDto deviceDto) {
        return mapper.mapToDeviceDto(dbService.saveDevice(mapper.mapToDevice(deviceDto)));
    }
}
