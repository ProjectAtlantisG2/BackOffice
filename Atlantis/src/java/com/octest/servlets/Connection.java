package com.octest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.User;

import com.octest.hashtool.PasswordUtils;
import com.octest.dao.*;

public class Connection extends HttpServlet {
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/login.jsp";
    private UserDao userDao;
    
    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        //Create a New User
        //PasswordUtils hash = new PasswordUtils();
        //String salt = hash.getSalt(30);
        //User user = new User();
        //user.setEmail("mail");
        //user.setName("Username");
        //user.setSalt(salt);
        //user.setHashedPassword(hash.generateSecurePassword("password",salt));
        //userDao.add(user);
        
        String password = request.getParameter("inputPassword");
        String email = request.getParameter("inputEmail");

        User user = userDao.findByEmail(email);
        
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
        //this.getServletContext().getRequestDispatcher("/devicesList.jsp").forward( request, response );
        response.sendRedirect("/AtlantisBackOffice/devicesList");
    }
}