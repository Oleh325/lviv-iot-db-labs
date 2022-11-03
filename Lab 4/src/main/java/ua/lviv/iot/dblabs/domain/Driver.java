package ua.lviv.iot.dblabs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver {
    private String licenseNumber;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String phoneNumber;
}
