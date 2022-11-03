package ua.lviv.iot.dblabs.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lviv.iot.dblabs.controller.FineController;
import ua.lviv.iot.dblabs.domain.Fine;
import ua.lviv.iot.dblabs.service.FineService;

import java.util.List;
import java.util.Optional;

@Component
public class FineControllerImpl implements FineController {

    @Autowired
    private FineService fineService;

    @Override
    public List<Fine> findAll() {
        return fineService.findAll();
    }

    @Override
    public Optional<Fine> findById(Integer id) {
        return fineService.findById(id);
    }

    @Override
    public int create(Fine fine) {
        return fineService.create(fine);
    }

    @Override
    public int update(Integer id, Fine fine) {
        return fineService.update(id, fine);
    }

    @Override
    public int delete(Integer id) {
        return fineService.delete(id);
    }

    @Override
    public List<Fine> findByDriverLicenseNumber(String driverLicenseNumber) {
        return fineService.findByDriverLicenseNumber(driverLicenseNumber);
    }
}
