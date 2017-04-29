package com.schweizerischebundesbahnen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by aleksandrprendota on 27.03.17.
 *
 * Configuration storage properties
 * Path to location counld be change in application.properties.
 *
 * Images, QR-codes upload from this location.
 */

@Primary
@Component
@ConfigurationProperties(prefix="storage")
public class StorageProperties {

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
