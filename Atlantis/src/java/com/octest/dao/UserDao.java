package com.octest.dao;

import java.util.List;

import com.octest.beans.User;

public interface UserDao {
    void add( User user );
    User findByEmail( String email );
}