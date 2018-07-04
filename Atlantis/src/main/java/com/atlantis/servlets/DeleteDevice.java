package com.atlantis.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atlantis.beans.Device;
import com.atlantis.mongoDB.dao.MongoDBDeviceDAO;

import static com.atlantis.servlets.Connection.ATT_SESSION_USER;
import com.mongodb.MongoClient;
import java.util.List;

public class DeleteDevice extends HttpServlet {
    
    
    public void init() throws ServletException {
    }
 
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        String macAddress = request.getParameter("macAddress");
        
        MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
        
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
            if (request.getParameter("macAddress") != null){
                MongoDBDeviceDAO deviceDAO = new MongoDBDeviceDAO(mongo);
                Device device = deviceDAO.findByMacAddress(macAddress);
                deviceDAO.deleteDevice(device);
            }
            response.sendRedirect("/AtlantisBackOffice/devicesList");
        }
    }
}