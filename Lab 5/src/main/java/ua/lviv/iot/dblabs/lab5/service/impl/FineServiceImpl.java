package ua.lviv.iot.dblabs.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab5.domain.Fine;
import ua.lviv.iot.dblabs.lab5.exception.FineNotFoundException;
import ua.lviv.iot.dblabs.lab5.repository.FineRepository;
import ua.lviv.iot.dblabs.lab5.service.DriverService;
import ua.lviv.iot.dblabs.lab5.service.FineService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineRepository fineRepository;

    @Autowired
    private DriverService driverService;

    public List<Fine> findAll() {
        return fineRepository.findAll();
    }

    public Fine findById(Integer id) {
        return fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));
    }

    @Transactional
    public Fine create(Fine fine) {
        fineRepository.save(fine);
        return fine;
    }

    @Transactional
    public void update(Integer id, Fine uFine) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));
        fine.setViolationType(uFine.getViolationType());
        fine.setDriver(uFine.getDriver());
        fineRepository.save(fine);
    }

    @Transactional
    public void update(Integer id, Fine uFine, String driverLicenseNumber) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));
        fine.setViolationType(uFine.getViolationType());
        fine.setDriver(driverService.findById(driverLicenseNumber));
        fineRepository.save(fine);
    }

    @Transactional
    public void delete(Integer id) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new FineNotFoundException(id));
        fineRepository.delete(fine);
    }

    public List<Fine> findByDriverLicenseNumber(String driverLicenseNumber) {
        return fineRepository.findAllByDriverLicenseNumber(driverLicenseNumber);
    }
}
