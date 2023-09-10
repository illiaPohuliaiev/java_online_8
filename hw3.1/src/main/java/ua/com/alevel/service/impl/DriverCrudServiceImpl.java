package ua.com.alevel.service.impl;

import ua.com.alevel.db.DriverDb;
import ua.com.alevel.entity.Driver;
import ua.com.alevel.service.DriverCrudService;

import java.io.IOException;

public class DriverCrudServiceImpl implements DriverCrudService {

    private DriverDb driverDb = new DriverDb();

    @Override
    public void create(Driver driver) {
        driverDb.create(driver);
    }

    @Override
    public Driver update(String id) throws IOException {
        return driverDb.update(id);
    }

    @Override
    public Driver[] delete(String id) throws IOException {
        return driverDb.delete(id);
    }

    @Override
    public Driver findOne(String id) throws IOException {
        return driverDb.findOne(id);
    }


    @Override
    public Driver[] findAll() {
        return driverDb.findAll();
    }
}
