package com.epam.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Employee;

@Repository
public interface EmployeeDao extends PagingAndSortingRepository<Employee, Long> {

}
