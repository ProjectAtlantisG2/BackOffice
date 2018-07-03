/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.mongoDB.converter;

import org.bson.types.ObjectId;

import com.atlantis.beans.Employee;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class EmployeeConverter {

	// convert Person Object to MongoDB DBObject
	// take special note of converting id String to ObjectId
	public static DBObject toDBObject(Employee e) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("name", e.getName());
		if (e.getId() != null)
			builder = builder.append("_id", new ObjectId(e.getId()));
		return builder.get();
	}

	// convert DBObject Object to Person
	// take special note of converting ObjectId to String
	public static Employee toEmployee(DBObject doc) {
		Employee e = new Employee();
		e.setName((String) doc.get("name"));
		ObjectId id = (ObjectId) doc.get("_id");
		e.setId(id.toString());
		return e;

	}
	
}