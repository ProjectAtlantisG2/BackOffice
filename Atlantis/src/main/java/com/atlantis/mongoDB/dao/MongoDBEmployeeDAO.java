/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.mongoDB.dao;

import com.atlantis.mongoDB.converter.EmployeeConverter;
import com.atlantis.beans.Employee;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.atlantis.mongoDB.converter.UserConverter;
import com.atlantis.beans.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBEmployeeDAO {

	private DBCollection col;

	public MongoDBEmployeeDAO(MongoClient mongo) {
		this.col = mongo.getDB("test").getCollection("employee");
	}

	public Employee createEmployee(Employee e) {
		DBObject doc = EmployeeConverter.toDBObject(e);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		e.setId(id.toString());
		return e;
	}

	public void updateEmployee(Employee e) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(e.getId())).get();
		this.col.update(query, EmployeeConverter.toDBObject(e));
	}

	public List<Employee> readAllEmployee() {
		List<Employee> data = new ArrayList<Employee>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Employee e = EmployeeConverter.toEmployee(doc);
			data.add(e);
		}
		return data;
	}
        
        public List<Employee> findByName(String name, String id) {
		List<Employee> data = new ArrayList<Employee>();
                BasicDBObject query = new BasicDBObject();
                query.put("lastName", name);
		DBCursor cursor = col.find(query);
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Employee e = EmployeeConverter.toEmployee(doc);
                        String idEmployee = e.getId().toString();
                        if (idEmployee.equals(id)) {
                            data.add(e);
                        }
		}
		return data;
	}
    
    
	public void deleteEmployee(Employee e) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(e.getId())).get();
		this.col.remove(query);
	}

	public Employee readEmployee(Employee e) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(e.getId())).get();
		DBObject data = this.col.findOne(query);
		return EmployeeConverter.toEmployee(data);
	}

}