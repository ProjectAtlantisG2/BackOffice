package com.octest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.octest.beans.Device;
import com.octest.beans.Employee;

public class DeviceDaoImpl implements DeviceDao {
    private DaoFactory daoFactory;

    DeviceDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Device device) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO device(name, type, macAddress, id_employee) VALUES(?, ?, ?, ?);");
            preparedStatement.setString(1, device.getName());
            preparedStatement.setString(2, device.getType());
            preparedStatement.setString(3, device.getMacAddress());
            preparedStatement.setString(4, device.getIdEmployee());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Device device) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE `device` SET `name`=\""+ device.getName() +"\",`type`=\""+ device.getType() + "\",`macAddress`=\""+device.getMacAddress()+"\",`id_employee`="+device.getIdEmployee()+" WHERE id ="+ device.getId() +";");
            preparedStatement.executeUpdate();
            } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(Device device) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM `device`  WHERE id =\""+ device.getId() +"\";");
            preparedStatement.executeUpdate();
            }
        
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<Device> lister() {
        List<Device> devices = new ArrayList<Device>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT d.name,d.id, d.type, d.macAddress,e.id, e.name FROM device AS d LEFT JOIN employee AS e ON d.id_employee = e.id");

            while (resultat.next()) {
                String name = resultat.getString("d.name");
                String type = resultat.getString("d.type");
                String macAddress = resultat.getString("d.macAddress");
                String idEmployee = resultat.getString("e.id");
                String nameEmployee = resultat.getString("e.name");
                int id = resultat.getInt("d.id");
                

                Device device = new Device();
                device.setId(id);
                device.setName(name);
                device.setType(type);
                device.setMacAddress(macAddress);
                device.setIdEmployee(idEmployee);
                device.setNameEmployee(nameEmployee);
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }
    
    @Override
    public List<Device> findByEmployee( int id ) {
       
        List<Device> devices = new ArrayList<Device>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT  id , name, type, macAddress, id_employee FROM device WHERE id_employee = " + id + ";");

            while (resultat.next()) {
                int idDevice = resultat.getInt("id");
                String name = resultat.getString("name");
                String type = resultat.getString("type");
                String macAddress = resultat.getString("macAddress");
                String idEmployee = resultat.getString("id_employee");
                
                Device device = new Device();
                device.setId(idDevice);
                device.setName(name);
                device.setType(type);
                device.setMacAddress(macAddress);
                device.setIdEmployee(idEmployee);
                devices.add(device);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }
    
    @Override
    public Device find(int id) {
        Connection connexion = null;
        Device device = new Device();
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT d.id, d.name, type, macAddress,e.id, e.name FROM device AS d LEFT JOIN employee AS e ON d.id_employee = e.id WHERE d.id =" + id +";");

            while (resultat.next()) {
                String name = resultat.getString("d.name");
                String type = resultat.getString("d.type");
                String macAddress = resultat.getString("d.macAddress");
                String idEmployee = resultat.getString("e.id");
                String nameEmployee = resultat.getString("e.name");
                
                device.setId(id);
                device.setName(name);
                device.setType(type);
                device.setMacAddress(macAddress);
                device.setIdEmployee(idEmployee);
                device.setNameEmployee(nameEmployee);   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return device;
    }
}