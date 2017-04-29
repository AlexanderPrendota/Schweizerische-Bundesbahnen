package com.schweizerischebundesbahnen.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by aleksandrprendota on 22.03.17.
 */

@Data
@Entity
@Table(name = "STATIONS")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public long id;

    @Column(name = "STATION_NAME")
    public String stationName;


}
