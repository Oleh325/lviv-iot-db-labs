package ua.lviv.iot.dblabs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Parking {
    private Integer id;
    private String location;
    private String type;
    private Integer cityId;
}
