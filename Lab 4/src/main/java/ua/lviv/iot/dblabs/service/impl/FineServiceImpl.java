package ua.lviv.iot.dblabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.dao.FineDAO;
import ua.lviv.iot.dblabs.domain.Fine;
import ua.lviv.iot.dblabs.service.FineService;

import java.util.List;
import java.util.Optional;

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineDAO fineDAO;

    @Override
    public List<Fine> findAll() {
        return fineDAO.findAll();
    }

    @Override
    public Optional<Fine> findById(Integer id) {
        return fineDAO.findById(id);
    }

    @Override
    public int create(Fine fine) {
        return fineDAO.create(fine);
    }

    @Override
    public int update(Integer id, Fine fine) {
        return fineDAO.update(id, fine);
    }

    @Override
    public int delete(Integer id) {
        return fineDAO.delete(id);
    }

    @Override
    public List<Fine> findByDriverLicenseNumber(String driverLicenseNumber) {
        return fineDAO.findByDriverLicenseNumber(driverLicenseNumber);
    }
}
