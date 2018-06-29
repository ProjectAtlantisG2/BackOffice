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
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeviceDao deviceDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.deviceDao = daoFactory.getDeviceDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("devices", deviceDao.lister());
        this.getServletContext().getRequestDispatcher("/bonjour.jsp").forward(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Device device = new Device();
        device.setName(request.getParameter("name"));
        device.setType(request.getParameter("type"));
        device.setMacAddress(request.getParameter("macAddress"));
        
        deviceDao.add(device);
        
        request.setAttribute("devices", deviceDao.lister());
        
        this.getServletContext().getRequestDispatcher("/bonjour.jsp").forward(request, response);
    }
    
    

}