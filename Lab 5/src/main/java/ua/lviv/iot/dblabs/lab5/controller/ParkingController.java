package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.dblabs.lab5.dto.assembler.ParkingDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.ParkingService;

@RestController
@RequestMapping("/api/parkings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingDTOAssembler parkingDTOAssembler;

}
