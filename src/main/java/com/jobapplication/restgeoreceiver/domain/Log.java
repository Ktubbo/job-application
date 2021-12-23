package com.jobapplication.restgeoreceiver.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity(name = "Log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Log {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "Time")
    private Timestamp timestamp;

    @Column(name = "Info")
    private String info;

    public Log(Timestamp timestamp, String info) {
        this.timestamp = timestamp;
        this.info = info;
    }
}
