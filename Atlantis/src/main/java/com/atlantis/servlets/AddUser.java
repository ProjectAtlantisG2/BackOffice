/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlantis.mongoDB.dao.MongoDBUserDAO;
import com.atlantis.beans.User;
import com.atlantis.hashtool.PasswordUtils;
import static com.atlantis.servlets.Connection.ATT_SESSION_USER;
import com.mongodb.MongoClient;
import javax.servlet.http.HttpSession;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {

	private static final long serialVersionUID = -7060758261496829905L;

	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
                String password = request.getParameter("password");
                HttpSession session = request.getSession();
                
                PasswordUtils hash = new PasswordUtils();
                String salt = hash.getSalt(30);
                if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
                    response.sendRedirect("/AtlantisBackOffice/connexion");
                } else { 
     
                    if ((name == null || name.equals(""))
                                    || (email == null || email.equals(""))) {
                            request.setAttribute("error", "Mandatory Parameters Missing");
                            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                                            "/users.jsp");
                            rd.forward(request, response);
                    } else {
                            User u = new User();
                            u.setEmail(email);
                            u.setName(name);
                            u.setSalt(salt);
                            u.setHashedPassword(hash.generateSecurePassword(password,salt));
                            MongoClient mongo = (MongoClient) request.getServletContext()
                                            .getAttribute("MONGO_CLIENT");
                            MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
                            userDAO.createPerson(u);
                            System.out.println("Person Added Successfully with id="+u.getId());
                            request.setAttribute("success", "Person Added Successfully");
                            List<User> users = userDAO.readAllUser();
                            request.setAttribute("users", users);

                            this.getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
                    }
                }
	}

}