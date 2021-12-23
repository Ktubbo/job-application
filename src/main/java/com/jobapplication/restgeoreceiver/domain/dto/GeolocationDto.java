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
    private Long latitude;
    private Long longitude;
    private Device device;

}
