package com.octest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Employee;
import com.octest.dao.*;
import static com.octest.servlets.Connection.ATT_SESSION_USER;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Devices
 */
@WebServlet("/Employees")
public class Employees extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;
    
    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.employeeDao = daoFactory.getEmployeeDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
        if (request.getParameter("id") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("employee", employeeDao.find(id)); 
        }
        else{
            request.setAttribute("employee",new Employee());
        }
        this.getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        if (request.getParameter("id") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            Employee employee =  employeeDao.find(id);
            employee.setId(id);
            employee.setName(request.getParameter("name"));
            employeeDao.update(employee);  
        } 
        else {
            Employee employee = new Employee();
            employee.setName(request.getParameter("name")); 
            employeeDao.add(employee);   
        }
        response.sendRedirect("/AtlantisBackOffice/EmployeesList");
        //request.setAttribute("device", deviceDao.getDevice(id));
        //this.getServletContext().getRequestDispatcher("/device.jsp").forward(request, response);
    }
}