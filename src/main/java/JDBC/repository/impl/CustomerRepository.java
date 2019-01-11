package JDBC.repository.impl;

import JDBC.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public class CustomerRepository implements CrudRepository {
    @Override
    public void add(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public Optional findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }
}
