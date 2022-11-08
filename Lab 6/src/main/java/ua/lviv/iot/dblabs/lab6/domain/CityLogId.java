package ua.lviv.iot.dblabs.lab6.domain;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

@EqualsAndHashCode
public class CityLogId implements Serializable {

    private String id;
    private Timestamp timestamp;

    public CityLogId(String id, Timestamp timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }
}
