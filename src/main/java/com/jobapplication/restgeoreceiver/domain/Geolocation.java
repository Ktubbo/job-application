package com.jobapplication.restgeoreceiver.domain;

import com.jobapplication.restgeoreceiver.domain.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Geolocation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude")
    @NotNull
    private double latitude;

    @Column(name = "longitude")
    @NotNull
    private double longitude;

    @OneToOne
    @NotNull
    @JoinColumn(name = "device_id")
    private Device device;

    public Geolocation(double latitude, double longitude, Device device) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.device = device;
    }
}
