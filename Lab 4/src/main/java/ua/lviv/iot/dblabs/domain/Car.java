package ua.lviv.iot.dblabs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    private Integer id;
    private String model;
    private String color;
    private String transmissionType;
    private Integer seatsCount;
    private Boolean hasAC;
    private Float baggageCapacityKG;
    private Float rentCostPerDayUSD;
    private String fuelType;
    private String additionalInfo;
    private Integer parkingId;
}
