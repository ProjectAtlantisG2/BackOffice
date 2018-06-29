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


import com.octest.beans.Employee;
import com.octest.dao.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/EmployeesList")
public class EmployeesList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;
       
   public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.employeeDao = daoFactory.getEmployeeDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", employeeDao.lister());
        this.getServletContext().getRequestDispatcher("/employeesList.jsp").forward(request, response);
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}