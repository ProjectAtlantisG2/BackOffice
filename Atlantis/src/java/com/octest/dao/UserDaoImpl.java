package com.octest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.User;


public class UserDaoImpl implements UserDao {
    private DaoFactory daoFactory;

    UserDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    
    @Override
    public void add(User user) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO user(name, email, hashed_password, salt) VALUES(?, ?, ?,?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getHashedPassword());
            preparedStatement.setString(4, user.getSalt());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public User findByEmail(String email) {
        Connection connexion = null;
        User user = new User();
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT hashed_password, salt FROM user WHERE email =\"" + email +"\";");

            while (resultat.next()) {
                String hashedPassword = resultat.getString("hashed_password");
                String salt = resultat.getString("salt");    
                user.setHashedPassword(hashedPassword);
                user.setSalt(salt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}