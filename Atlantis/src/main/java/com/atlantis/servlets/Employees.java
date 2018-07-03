package com.atlantis.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlantis.beans.Employee;
import com.atlantis.mongoDB.dao.MongoDBEmployeeDAO;
import static com.atlantis.servlets.Connection.ATT_SESSION_USER;
import com.mongodb.MongoClient;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Devices
 */
@WebServlet("/Employees")
public class Employees extends HttpServlet {
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
        if (request.getParameter("id") != null){
             MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
            String name = request.getParameter("lastName");
            String id = request.getParameter("id");
            MongoDBEmployeeDAO employeeDAO = new MongoDBEmployeeDAO(mongo);
            List<Employee> employees = employeeDAO.findByName(name, id);
            request.setAttribute("employees", employees);

        }
        else{
            request.setAttribute("employee",new Employee());
        }
        this.getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        
        MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
        String id = request.getParameter("id");
        String name = request.getParameter("nameEmployee");
        if (request.getParameter("nameEmployee") != null){
            MongoDBEmployeeDAO employeeDAO = new MongoDBEmployeeDAO(mongo);
            List<Employee> employees  = employeeDAO.findByName(name,id);
            for(Employee employee : employees) {
                employee.setLastName(request.getParameter("lastName"));
                employee.setFirstName(request.getParameter("firstName"));
                employeeDAO.updateEmployee(employee);
            }
        } 
        else {
            Employee employee = new Employee();
            employee.setLastName(request.getParameter("lastName")); 
            employee.setFirstName(request.getParameter("firstName")); 
            MongoDBEmployeeDAO employeeDAO = new MongoDBEmployeeDAO(mongo);
            employeeDAO.createEmployee(employee);
        }
        response.sendRedirect("/AtlantisBackOffice/EmployeesList");
        //request.setAttribute("device", deviceDao.getDevice(id));
        //this.getServletContext().getRequestDispatcher("/device.jsp").forward(request, response);
    }
}