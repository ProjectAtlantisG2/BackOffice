package com.atlantis.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mongodb.MongoClient;

import com.atlantis.beans.User;

import com.atlantis.hashtool.PasswordUtils;
import com.atlantis.mongoDB.dao.MongoDBUserDAO;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Connexion")
public class Connection extends HttpServlet {
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/login.jsp";
    

    @Override
    public void init() throws ServletException {       
    }

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        String password = request.getParameter("inputPassword");
        String email = request.getParameter("inputEmail");
        
        MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
        User user = userDAO.findByEmail(email);

        if (user.getHashedPassword() != null){
            HttpSession session = request.getSession();
            boolean passwordMatch = PasswordUtils.verifyUserPassword(password, user.getHashedPassword(), user.getSalt());
              if(passwordMatch) 
                {
                    session.setAttribute( ATT_SESSION_USER, user );
                    request.setAttribute( ATT_USER, user );
                } else {
                    System.out.println("Provided password is incorrect");
                }
        }
        response.sendRedirect("/AtlantisBackOffice/devicesList");
    }
    

}