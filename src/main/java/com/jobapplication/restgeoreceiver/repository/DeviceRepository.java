package com.jobapplication.restgeoreceiver.repository;

import com.jobapplication.restgeoreceiver.domain.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device,Long> {

    List<Device> findAll();
    Optional<Device> findById(Long id);
    Device save (Device device);
    void deleteById(Long id);

}
