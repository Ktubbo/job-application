package com.jobapplication.restgeoreceiver.service;

import com.jobapplication.restgeoreceiver.domain.Device;
import com.jobapplication.restgeoreceiver.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceDBService {
    
    @Autowired
    private final DeviceRepository repository;

    public List<Device> getAllDevices() { return repository.findAll(); }
    public Optional<Device> getDevice(final Long deviceId) { return repository.findById(deviceId); }
    public void deleteDevice(final Long deviceId) { repository.deleteById(deviceId); }
    public Device saveDevice(Device device) { return repository.save(device); }

}
