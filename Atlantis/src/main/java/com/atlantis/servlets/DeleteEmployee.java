package com.atlantis.servlets;

import com.atlantis.beans.Device;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atlantis.beans.Employee;
import com.atlantis.mongoDB.dao.MongoDBDeviceDAO;
import com.atlantis.mongoDB.dao.MongoDBEmployeeDAO;
import static com.atlantis.servlets.Connection.ATT_SESSION_USER;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;


public class DeleteEmployee extends HttpServlet {
    

    @Override
    public void init() throws ServletException {
    }
 
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String name = request.getParameter("lastName");
        
        MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
        
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
            if ( id != null){ 
                MongoDBDeviceDAO deviceDAO = new MongoDBDeviceDAO(mongo);
                
                List<Device> devices = new ArrayList<Device>();
                devices = deviceDAO.findByNameEmployee(name, id);
                for (final Device device : devices){
                    device.setIdEmployee(null);
                    device.setEmployee(null);
                    deviceDAO.updateDevice(device);
                }
                MongoDBEmployeeDAO employeeDAO = new MongoDBEmployeeDAO(mongo);
                List<Employee> employees = employeeDAO.findByName(name,id);
                for(Employee employee : employees) {
                   employeeDAO.deleteEmployee(employee);
                }  
            }
            response.sendRedirect("/AtlantisBackOffice/employeesList");
        }
    }
}