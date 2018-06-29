package com.octest.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Device;
import com.octest.dao.*;

/**
 * Servlet implementation class Devices
 */
@WebServlet("/Devices")
public class Devices extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeviceDao deviceDao;
    private EmployeeDao employeeDao;
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.deviceDao = daoFactory.getDeviceDao();
        this.employeeDao = daoFactory.getEmployeeDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", employeeDao.lister());
        if (request.getParameter("id") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("device", deviceDao.find(id));
            
        }
        else{
            request.setAttribute("device",new Device());
        }
        
        this.getServletContext().getRequestDispatcher("/device.jsp").forward(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        if (request.getParameter("id") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            Device device =  deviceDao.find(id);
            device.setId(id);
            device.setName(request.getParameter("name"));
            device.setType(request.getParameter("type"));
            device.setMacAddress(request.getParameter("macAddress"));
            device.setIdEmployee(request.getParameter("idEmployee"));
            
            deviceDao.update(device);
           
        } 
        else {
            Device device = new Device();
            device.setName(request.getParameter("name"));
            device.setType(request.getParameter("type"));
            device.setMacAddress(request.getParameter("macAddress"));
            device.setIdEmployee(request.getParameter("idEmployee"));
            deviceDao.add(device);
            
        }
        response.sendRedirect("/AtlantisBackOffice/devicesList");
        
        //request.setAttribute("device", deviceDao.getDevice(id));
        //this.getServletContext().getRequestDispatcher("/device.jsp").forward(request, response);
    }
    
    

}