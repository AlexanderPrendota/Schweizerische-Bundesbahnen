package com.schweizerischebundesbahnen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by aleksandrprendota on 06.05.17.
 */

@Data
@Entity(name = "CHATS")
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue
    @Column(name = "CHAT_ID", nullable =  false)
    private long id;
}
