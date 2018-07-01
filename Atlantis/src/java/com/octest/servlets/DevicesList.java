package com.octest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.dao.*;

import static com.octest.servlets.Connection.ATT_SESSION_USER;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/DevicesList")
public class DevicesList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeviceDao deviceDao;
       
    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.deviceDao = daoFactory.getDeviceDao();
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
        request.setAttribute("devices", deviceDao.lister());
        this.getServletContext().getRequestDispatcher("/devicesList.jsp").forward(request, response);
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