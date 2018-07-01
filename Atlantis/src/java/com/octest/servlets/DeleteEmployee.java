package com.octest.servlets;

import com.octest.beans.Device;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.Employee;
import com.octest.dao.*;
import static com.octest.servlets.Connection.ATT_SESSION_USER;
import java.util.ArrayList;
import java.util.List;

public class DeleteEmployee extends HttpServlet {
    
    private EmployeeDao employeeDao;
    private DeviceDao deviceDao;
    
    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.employeeDao = daoFactory.getEmployeeDao();
        this.deviceDao = daoFactory.getDeviceDao();
    }
 
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
            if (request.getParameter("id") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                List<Device> devices = new ArrayList<Device>();
                devices = deviceDao.findByEmployee(id);
                for (final Device device : devices){
                    device.setIdEmployee(null);
                    deviceDao.update(device);
                }
                Employee employee =  employeeDao.find(id);
                employeeDao.delete(employee);
            }
            response.sendRedirect("/AtlantisBackOffice/employeesList");
        }
    }
}