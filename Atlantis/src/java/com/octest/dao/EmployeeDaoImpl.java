package com.octest.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
    private DaoFactory daoFactory;

    EmployeeDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Employee employee) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO employee(name) VALUES(?);");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Employee employee) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE `employee` SET `name`=\""+ employee.getName() +"\" WHERE id =\""+ employee.getId() +"\";");
            preparedStatement.executeUpdate();
            }
  
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> lister() {
        List<Employee> employees = new ArrayList<Employee>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id, name FROM employee;");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String name = resultat.getString("name");
               
                Employee employee = new Employee();
                employee.setName(name);
                employee.setId(id);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
    @Override
    public void delete(Employee employee) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM `employee`  WHERE id =\""+ employee.getId() +"\";");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee find(int id) {
        Connection connexion = null;
        Employee employee = new Employee();
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id, name FROM employee A WHERE id =" + id +";");

            while (resultat.next()) {
                String name = resultat.getString("name");
                employee.setName(name);
                employee.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}