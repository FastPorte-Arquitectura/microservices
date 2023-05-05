package com.fasporte.DriverService.service;

import com.fasporte.DriverService.entities.Driver;

public interface IDriverService extends CrudService<Driver> {

    Driver findByEmailAndPassword(String email, String password);
}
