package com.epam.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.epam.model.Employee;

@Service
public interface EmployeeService {

    List<Employee> findByName(String name);

    <S extends Employee> S save(S entity);

    Iterable<Employee> findAll(Sort sort);

    <S extends Employee> Iterable<S> save(Iterable<S> entities);

    Page<Employee> findAll(Pageable pageable);

    Employee findOne(Long id);

    boolean exists(Long id);

    Iterable<Employee> findAll();

    Iterable<Employee> findAll(Iterable<Long> ids);

    void delete(Long id);

    void delete(Employee entity);

    void delete(Iterable<? extends Employee> entities);

    void deleteAll();
}
