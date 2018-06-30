package com.octest.beans;
/**
 *
 * @author Ollocip
 */
public class Device { 
    private String name;
    private int id;
    private String type;
    private String macAddress;
    private String idEmployee;
    private String nameEmployee;
    
    public int getId(){
        return id;
    }
    
     public void setId(int id){
         this.id = id;
    }
    
    public String getIdEmployee() {
		return idEmployee;
    }
    
    public void setIdEmployee(String idEmployee) {
            this.idEmployee = idEmployee;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getNameEmployee(){
        return nameEmployee;
    }
    
    public void setNameEmployee(String nameEmployee){
        this.nameEmployee = nameEmployee;
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
