package com.schweizerischebundesbahnen.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by aleksandrprendota on 22.03.17.
 */

@Data
@Entity
@Table(name = "SEATS")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public long id;

    @Column(name = "CABINE")
    public int cabine;

    @Column(name = "NUMBER")
    public int number;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "train_id")
    private Train train;

}
