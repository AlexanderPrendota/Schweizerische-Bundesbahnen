package com.schweizerischebundesbahnen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@Data
@NoArgsConstructor
@Entity(name = "USER_CHATS")
public class UserChat {

    @Id
    @GeneratedValue
    @Column(name = "USER_CHAT_ID", nullable =  false)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CHAT_ID")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}