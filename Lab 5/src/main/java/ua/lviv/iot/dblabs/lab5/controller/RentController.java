package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.dblabs.lab5.dto.assembler.RentDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.RentService;

@RestController
@RequestMapping("/api/rents")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private RentDTOAssembler rentDTOAssembler;

}
