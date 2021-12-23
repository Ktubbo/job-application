package com.jobapplication.restgeoreceiver.domain.dto;

import com.jobapplication.restgeoreceiver.domain.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeolocationDto {

    private Long id;
    private double latitude;
    private double longitude;
    private Device device;

}
