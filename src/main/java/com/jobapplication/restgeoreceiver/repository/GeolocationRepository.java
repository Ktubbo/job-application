package com.jobapplication.restgeoreceiver.repository;

import com.jobapplication.restgeoreceiver.domain.Device;
import com.jobapplication.restgeoreceiver.domain.Geolocation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GeolocationRepository extends CrudRepository<Geolocation, Long> {

    List<Geolocation> findAll();
    List<Geolocation> findByDevice(Device device);
    Optional<Geolocation> findById(Long id);
    Geolocation save (Geolocation device);
    void deleteById(Long id);

}
