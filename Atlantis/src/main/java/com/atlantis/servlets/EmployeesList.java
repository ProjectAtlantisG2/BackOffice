package com.atlantis.servlets;

import com.atlantis.beans.Employee;
import com.atlantis.mongoDB.dao.MongoDBEmployeeDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.atlantis.servlets.Connection.ATT_SESSION_USER;
import com.mongodb.MongoClient;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/EmployeesList")
public class EmployeesList extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    @Override
   public void init() throws ServletException {
        
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
            MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
            MongoDBEmployeeDAO employeeDAO = new MongoDBEmployeeDAO(mongo);
            List<Employee> employees = employeeDAO.readAllEmployee();
            request.setAttribute("employees", employees);
            this.getServletContext().getRequestDispatcher("/employeesList.jsp").forward(request, response);
        }    
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}