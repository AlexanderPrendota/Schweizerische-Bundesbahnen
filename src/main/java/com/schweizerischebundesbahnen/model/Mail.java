package com.schweizerischebundesbahnen.model;

import lombok.Data;

/**
 * Created by aleksandrprendota on 01.04.17.
 */

@Data
public class Mail {
    public String to;
    public String subject;
    public String text;
}
