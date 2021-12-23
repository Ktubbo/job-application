package com.jobapplication.restgeoreceiver.service;

import com.jobapplication.restgeoreceiver.domain.Device;
import com.jobapplication.restgeoreceiver.domain.Geolocation;
import com.jobapplication.restgeoreceiver.exceptions.DeviceNotFoundException;
import com.jobapplication.restgeoreceiver.repository.DeviceRepository;
import com.jobapplication.restgeoreceiver.repository.GeolocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeolocationDBService {

    @Autowired
    private final GeolocationRepository repository;
    @Autowired
    private final DeviceRepository deviceRepository;

    public List<Geolocation> getAllGeolocations() { return repository.findAll(); }
    public Optional<Geolocation> getGeolocation(final Long geolocationId) { return repository.findById(geolocationId); }
    public List<Geolocation> getDeviceGeolocations(final Long deviceId) throws DeviceNotFoundException {
        Device device = deviceRepository.findById(deviceId).orElseThrow(DeviceNotFoundException::new);
        return repository.findByDevice(device);
    }
    public Geolocation saveGeolocation(Geolocation geolocation) { return repository.save(geolocation); }

}
