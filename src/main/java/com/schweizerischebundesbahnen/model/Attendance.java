package com.schweizerischebundesbahnen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aleksandrprendota on 16.05.17.
 */
@Data
@Entity
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue
    @Column(name = "ATTENDANCE_ID", nullable = false)
    public long id;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    public Date date;

    @Column(name = "COUNT")
    public int count;
}
