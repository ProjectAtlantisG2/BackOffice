package com.atlantis.beans;
/**
 *
 * @author Ollocip
 */
public class Device { 
    private String name;
    private String id;
    private String type;
    private String macAddress;
    private String employee;
    private String idEmployee;
    
    public String getId(){
        return id;
    }
    
     public void setId(String id){
         this.id = id;
    }
     
    public String getIdEmployee(){
        return idEmployee;
    }
    
    public void setIdEmployee(String idEmployee){
         this.idEmployee = idEmployee;
    }
    
    public String getEmployee() {
		return employee;
    }
    
    public void setEmployee(String employee) {
            this.employee = employee;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getMacAddress(){
        return macAddress;
    }
    
    public void setMacAddress(String macAddress){
        this.macAddress = macAddress;
    }
}
