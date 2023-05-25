package com.fasporte.DriverService.service.impl;

import com.fasporte.DriverService.entities.Vehicle;
import com.fasporte.DriverService.repository.IVehicleRepository;
import com.fasporte.DriverService.service.IVehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VehicleServiceImpl implements IVehicleService {

    private final IVehicleRepository vehicleRepository;
    public VehicleServiceImpl(IVehicleRepository vehicleRepository){this.vehicleRepository = vehicleRepository;}

    @Override
    @Transactional
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleRepository.save(vehicle);
    }
    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        vehicleRepository.deleteById(id);
    }
    @Override
    public List<Vehicle> findByDriverId(Long driverId) throws Exception {
        return vehicleRepository.findByDriverId(driverId);
    }
    @Override
    public List<Vehicle> getAll() throws Exception {
        return vehicleRepository.findAll();
    }
    @Override
    public Optional<Vehicle> getById(Long id) throws Exception {
        return vehicleRepository.findById(id);
    }
    //@Override
    //public List<Vehicle> finByType_cardQuantityCategory(String type_card, Long quantity, String category) {
    //    return vehicleRepository.finByType_cardQuantityCategory(type_card,quantity,category);
    //}



}