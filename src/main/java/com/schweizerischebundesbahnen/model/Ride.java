package com.schweizerischebundesbahnen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aleksandrprendota on 22.03.17.
 */


@Entity
@Table(name = "RIDES")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "ID", nullable = false)
    public long id;

    @Column(name = "TIME_DEPARTURE")
    @Getter
    @Setter
    public Date timeDeparture;

    @Column(name = "TIME_ARRIVAL")
    @Getter
    @Setter
    public Date timeArrival;

    @OneToOne
    @Getter
    @Setter
    public Train train;

    @OneToOne
    @Getter
    @Setter
    public Seat seat;

    @OneToOne
    @Getter
    @Setter
    public Station stationDeparture;

    @OneToOne
    @Getter
    @Setter
    public Station stationArrival;

    @ManyToOne
    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


}
