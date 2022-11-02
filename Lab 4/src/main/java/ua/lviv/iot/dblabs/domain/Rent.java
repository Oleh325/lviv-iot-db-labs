package ua.lviv.iot.dblabs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rent {
    private Integer id;
    private Time dateOfRent;
    private Time endDateOfRent;
    private String paymentType;
    private String transactionId;
    private Integer carId;
    private String driverLicenseNumber;
}
