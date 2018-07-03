package com.atlantis.beans;

public class User {
    private String email;
    private String hashedPassword;
    private String salt;
    private String name;
    private String id;
 
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }

    public void setEmail(String email) {
	this.email = email;
    }
    
    public String getEmail() {
	return email;
    }

    public void setHashedPassword(String hashedPassword) {
	this.hashedPassword = hashedPassword;
    }
    
    public String getHashedPassword() {
	return hashedPassword;
    }
    
    public void setSalt(String salt) {
	this.salt = salt;
    }
    
    public String getSalt() {
	return salt;
    }

    public void setName(String name) {
	this.name = name;
    }
    
    public String getName() {
	return name;
    }
}