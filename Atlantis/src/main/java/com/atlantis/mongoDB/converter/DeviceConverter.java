/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.mongoDB.converter;

import com.atlantis.beans.Device;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import java.util.UUID;
import org.bson.types.Binary;

public class DeviceConverter {

	// convert Person Object to MongoDB DBObject
	// take special note of converting id String to ObjectId
	public static DBObject toDBObject(Device d) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("name", d.getName()).append("type", d.getType())
                        .append("macAddress", d.getMacAddress()).append("employee", d.getEmployee()).append("idEmployee",  new ObjectId(d.getIdEmployee()));
		if (d.getId() != null)
			builder = builder.append("_id", new ObjectId(d.getId()));
                if (d.getUuid() == null){
                    builder = builder.append("uuid", UUID.randomUUID());
                } else {
                    builder = builder.append("uuid", UUID.fromString(d.getUuid()));
                }
			
		return builder.get();
	}

	// convert DBObject Object to Person
	// take special note of converting ObjectId to String
	public static Device toDevice(DBObject doc) {
		Device d = new Device();
		d.setName((String) doc.get("name"));
		d.setType((String) doc.get("type"));
                d.setMacAddress((String) doc.get("macAddress"));
                d.setEmployee((String) doc.get("employee"));
                
                UUID uuid = (UUID) doc.get("uuid");
                d.setUuid(uuid.toString());
               
                ObjectId id_employee = (ObjectId) doc.get("idEmployee");
                d.setIdEmployee(id_employee.toString());
                
		ObjectId id = (ObjectId) doc.get("_id");
		d.setId(id.toString());
		return d;

	}
	
}