package com.schweizerischebundesbahnen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by aleksandrprendota on 22.03.17.
 */


@Entity
@Table(name = "TRAINS")
public class Train {

    @Id
    @Getter
    @Setter
    @Column(name = "id", nullable = false, unique = true)
    public String id;

    @Getter
    @Setter
    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Set<Seat> seats;

}

