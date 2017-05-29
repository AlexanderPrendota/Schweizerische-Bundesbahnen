package com.schweizerischebundesbahnen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by aleksandrprendota on 22.03.17.
 */


@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "ID", nullable = false)
    public long id;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    @Setter
    @JoinColumn(name = "USER_ID")
    public User user;

    @OneToMany(mappedBy = "ticket")
    @Getter
    @Setter
    private Set<Ride> ride;

}
