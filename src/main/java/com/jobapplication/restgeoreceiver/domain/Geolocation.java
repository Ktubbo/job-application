package com.jobapplication.restgeoreceiver.domain;

import com.jobapplication.restgeoreceiver.domain.Device;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Geolocation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude")
    @NotNull
    private Long latitude;

    @Column(name = "longitude")
    @NotNull
    private Long longitude;

    @OneToOne
    @NotNull
    @JoinColumn(name = "device_id")
    private Device device;
}
