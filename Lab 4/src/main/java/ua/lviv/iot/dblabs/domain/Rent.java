package ua.lviv.iot.dblabs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rent {
    private Integer id;
    private Timestamp dateOfRent;
    private Timestamp endDateOfRent;
    private String paymentType;
    private String transactionId;
    private Integer carId;
    private String driverLicenseNumber;
}
