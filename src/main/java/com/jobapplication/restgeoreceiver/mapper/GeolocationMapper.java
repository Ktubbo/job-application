package com.jobapplication.restgeoreceiver.mapper;

import com.jobapplication.restgeoreceiver.domain.Geolocation;
import com.jobapplication.restgeoreceiver.domain.dto.GeolocationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeolocationMapper {

    public Geolocation mapToGeolocation(GeolocationDto geolocationDto) {
        return new Geolocation(geolocationDto.getId(),
                geolocationDto.getLatitude(),
                geolocationDto.getLongitude(),
                geolocationDto.getDevice());
    }

    public GeolocationDto mapToGeolocationDto(Geolocation geolocation) {
        return new GeolocationDto(geolocation.getId(),
                geolocation.getLatitude(),
                geolocation.getLongitude(),
                geolocation.getDevice());
    }

    public List<Geolocation> mapToGeolocationList(final List<GeolocationDto> geolocationDtoList) {
        return geolocationDtoList.stream().map(this::mapToGeolocation).collect(Collectors.toList());
    }

    public List<GeolocationDto> mapToGeolocationDtoList(final List<Geolocation> geolocationList) {
        return geolocationList.stream().map(this::mapToGeolocationDto).collect(Collectors.toList());
    }
}
