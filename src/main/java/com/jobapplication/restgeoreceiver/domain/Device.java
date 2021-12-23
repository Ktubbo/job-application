package com.jobapplication.restgeoreceiver.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Device {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_name")
    @NotNull
    private String deviceName;
}