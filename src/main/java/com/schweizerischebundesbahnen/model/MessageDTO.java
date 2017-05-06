package com.schweizerischebundesbahnen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private long recipientId;
    private String text;
    private Date timeStamp;
    private Long chatId;
}
