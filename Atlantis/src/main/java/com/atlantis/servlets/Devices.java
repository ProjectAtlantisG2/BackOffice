package com.atlantis.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlantis.beans.Device;
import com.atlantis.beans.Employee;
import com.atlantis.mongoDB.dao.MongoDBDeviceDAO;
import com.atlantis.mongoDB.dao.MongoDBEmployeeDAO;
import static com.atlantis.servlets.Connection.ATT_SESSION_USER;
import com.mongodb.MongoClient;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Devices
 */
@WebServlet("/Devices")
public class Devices extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
            MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
            //request.setAttribute("employees", employeeDao.lister());
            MongoDBEmployeeDAO employeeDAO = new MongoDBEmployeeDAO(mongo);
            List<Employee> employees = employeeDAO.readAllEmployee();
            request.setAttribute("employees", employees);
            if (request.getParameter("macAddress") != null){
                MongoDBDeviceDAO deviceDAO = new MongoDBDeviceDAO(mongo);
                Device device = deviceDAO.findByMacAddress(request.getParameter("macAddress"));
                request.setAttribute("device", device);
                
                //request.setAttribute("device", deviceDao.find(id));
            }
            else{
                request.setAttribute("device",new Device());
            }

            this.getServletContext().getRequestDispatcher("/device.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        String name = request.getParameter("name");
	String type = request.getParameter("type");
        String[] employee = request.getParameter("idEmployee").split("@");
        String macAddress = request.getParameter("macAddressDevice");
        
        MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
        
        if (!request.getParameter("macAddress").equals("")){
            
            MongoDBDeviceDAO deviceDAO = new MongoDBDeviceDAO(mongo);
            Device device = deviceDAO.findByMacAddress(request.getParameter("macAddress"));
            device.setName(name);
            device.setType(type);
            device.setIdEmployee(employee[0]);
            device.setEmployee(employee[1]);
            device.setMacAddress(macAddress);
            deviceDAO.updateDevice(device);
        } 
        else {     
            MongoDBDeviceDAO deviceDAO = new MongoDBDeviceDAO(mongo);
            Device device = new Device();
            device.setName(name);
            device.setType(type);
            device.setIdEmployee(employee[0]);
            device.setEmployee(employee[1]);
            device.setMacAddress(macAddress);
            deviceDAO.createDevice(device);
        }
        response.sendRedirect("/AtlantisBackOffice/devicesList");
    }
}