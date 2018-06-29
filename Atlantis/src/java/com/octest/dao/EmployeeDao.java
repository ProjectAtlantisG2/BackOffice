package com.octest.dao;

import java.util.List;

import com.octest.beans.Employee;

public interface EmployeeDao {
    void add( Employee employee );
    void update( Employee employee );
    List<Employee> lister();
    Employee find( int id );
}