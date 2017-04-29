package com.schweizerischebundesbahnen.model;

/**
 * Created by aleksandrprendota on 22.03.17.
 */

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SCHEDULES")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public long id;

    @Column(name = "TIME_DEPARTURE")
    public Date timeDeparture;

    @Column(name = "TIME_ARRIVAL")
    public Date timeArrival;

    @OneToOne
    public Train train;

    @OneToOne
    public Station stationDeparture;

    @OneToOne
    public Station stationArrival;


}
