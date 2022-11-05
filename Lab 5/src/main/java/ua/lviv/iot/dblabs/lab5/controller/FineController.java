package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.dblabs.lab5.dto.assembler.FineDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.FineService;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    @Autowired
    private FineService fineService;
    @Autowired
    private FineDTOAssembler fineDTOAssembler;
}
