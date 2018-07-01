package com.octest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.Device;
import com.octest.dao.*;

import static com.octest.servlets.Connection.ATT_SESSION_USER;

public class DeleteDevice extends HttpServlet {
    
    private DeviceDao deviceDao;
    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.deviceDao = daoFactory.getDeviceDao();
    }
 
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
            if (request.getParameter("id") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                Device device =  deviceDao.find(id);
                deviceDao.delete(device);
            }
            response.sendRedirect("/AtlantisBackOffice/devicesList");
        }
    }
}