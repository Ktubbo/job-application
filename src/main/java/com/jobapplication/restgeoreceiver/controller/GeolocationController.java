package com.jobapplication.restgeoreceiver.controller;

import com.jobapplication.restgeoreceiver.domain.Device;
import com.jobapplication.restgeoreceiver.domain.Geolocation;
import com.jobapplication.restgeoreceiver.domain.dto.GeolocationDto;
import com.jobapplication.restgeoreceiver.exceptions.DeviceNotFoundException;
import com.jobapplication.restgeoreceiver.exceptions.GeolocationNotFoundException;
import com.jobapplication.restgeoreceiver.mapper.GeolocationMapper;
import com.jobapplication.restgeoreceiver.service.DeviceDBService;
import com.jobapplication.restgeoreceiver.service.GeolocationDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GeolocationController {

    private final GeolocationDBService geolocationDBService;
    private final DeviceDBService deviceDBService;
    private final GeolocationMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/geolocations")
    public List<GeolocationDto> getGeolocations() {
        return mapper.mapToGeolocationDtoList(geolocationDBService.getAllGeolocations());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/geolocations/{geolocationId}")
    public GeolocationDto getGeolocation(@PathVariable Long geolocationId) throws GeolocationNotFoundException {
        return mapper.mapToGeolocationDto(geolocationDBService.getGeolocation(geolocationId).orElseThrow(GeolocationNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deviceGeolocations/{deviceId}")
    public List<GeolocationDto> getDeviceGeolocation(@PathVariable Long deviceId) throws DeviceNotFoundException {
        deviceDBService.getDevice(deviceId).orElseThrow(DeviceNotFoundException::new);
        return mapper.mapToGeolocationDtoList(geolocationDBService.getDeviceGeolocations(deviceId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/geolocations")
    public GeolocationDto createGeolocation(@RequestParam double latitude, @RequestParam double longitude, @RequestParam Long deviceId)
            throws DeviceNotFoundException {
        Device device = deviceDBService.getDevice(deviceId).orElseThrow(DeviceNotFoundException::new);
        return mapper.mapToGeolocationDto(geolocationDBService.saveGeolocation(new Geolocation(latitude,longitude,device)));
    }
}
