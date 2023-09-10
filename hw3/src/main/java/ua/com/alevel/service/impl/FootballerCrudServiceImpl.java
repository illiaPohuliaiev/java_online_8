package ua.com.alevel.service.impl;

import ua.com.alevel.db.FootballerDb;
import ua.com.alevel.entity.Footballer;
import ua.com.alevel.service.FootballerCrudService;

import java.io.IOException;

public class FootballerCrudServiceImpl implements FootballerCrudService {

    private FootballerDb footballerDb = new FootballerDb();

    @Override
    public void create(Footballer footballer) {
        footballerDb.create(footballer);
    }

    @Override
    public Footballer update(String id) throws IOException {
        return footballerDb.update(id);
    }

    @Override
    public Footballer[] delete(String id) throws IOException {
        return footballerDb.delete(id);
    }

    @Override
    public Footballer findOne(String id) throws IOException {
        return footballerDb.findOne(id);
    }


    @Override
    public Footballer[] findAll() {
        return footballerDb.findAll();
    }
}
