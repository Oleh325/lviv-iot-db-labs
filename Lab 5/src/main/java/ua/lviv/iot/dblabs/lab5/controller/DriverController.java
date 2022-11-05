package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.dblabs.lab5.domain.Driver;
import ua.lviv.iot.dblabs.lab5.dto.DriverDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.DriverDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverDTOAssembler driverDTOAssembler;

    @GetMapping("/{licenseNumber}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable String licenseNumber) {
        Driver driver = driverService.findById(licenseNumber);
        DriverDTO driverDTO = driverDTOAssembler.toModel(driver);
        return new ResponseEntity<>(driverDTO, HttpStatus.OK);
    }
}
