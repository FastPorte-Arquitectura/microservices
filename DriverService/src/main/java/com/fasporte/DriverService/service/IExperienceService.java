package com.fasporte.DriverService.service;

import com.fasporte.DriverService.entities.Experience;

import java.util.List;

public interface IExperienceService extends CrudService<Experience>{
    List<Experience> findByDriverId(Long driverId) throws Exception;
}
