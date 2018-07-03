package com.atlantis.servlets;

import com.atlantis.beans.Device;
import com.atlantis.beans.User;
import com.atlantis.mongoDB.dao.MongoDBDeviceDAO;
import com.atlantis.mongoDB.dao.MongoDBUserDAO;
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
@WebServlet("/DevicesList")
public class DevicesList extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect("/AtlantisBackOffice/connexion");
        } else {
        //request.setAttribute("devices", deviceDao.lister());
        
        MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
	MongoDBDeviceDAO deviceDAO = new MongoDBDeviceDAO(mongo);
        List<Device> devices = deviceDAO.readAllDevice();
        request.setAttribute("devices", devices);
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