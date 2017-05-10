package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.epam.dao.EmployeeDao;
import com.epam.model.Employee;

@Service
public class LocalEmployeeService implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> findByName(String name) {
        /*List<Employee> list = null;
        String[] nameParts = name.split(" ");

        if (nameParts.length == 0) {
            list = new ArrayList<>();
        } else if (nameParts.length == 1) {
            String firstName = nameParts[0];
            String lastName = "";
            list = employeeDao.findByName(firstName, lastName);
        } else if (nameParts.length == 1) {
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            list = employeeDao.findByName(firstName, lastName);
        }

        return list;*/
        return null;
    }

    public <S extends Employee> S save(S entity) {
        return employeeDao.save(entity);
    }

    public Iterable<Employee> findAll(Sort sort) {
        return employeeDao.findAll(sort);
    }

    public <S extends Employee> Iterable<S> save(Iterable<S> entities) {
        return employeeDao.save(entities);
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeDao.findAll(pageable);
    }

    public Employee findOne(Long id) {
        return employeeDao.findOne(id);
    }

    public boolean exists(Long id) {
        return employeeDao.exists(id);
    }

    public Iterable<Employee> findAll() {
        return employeeDao.findAll();
    }

    public Iterable<Employee> findAll(Iterable<Long> ids) {
        return employeeDao.findAll(ids);
    }

    public void delete(Long id) {
        employeeDao.delete(id);
    }

    public void delete(Employee entity) {
        employeeDao.delete(entity);
    }

    public void delete(Iterable<? extends Employee> entities) {
        employeeDao.delete(entities);
    }

    public void deleteAll() {
        employeeDao.deleteAll();
    }



}
