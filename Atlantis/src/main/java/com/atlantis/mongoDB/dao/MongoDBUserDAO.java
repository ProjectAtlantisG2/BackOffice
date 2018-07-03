/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.mongoDB.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.atlantis.mongoDB.converter.UserConverter;
import com.atlantis.beans.User;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBUserDAO {

	private DBCollection col;

	public MongoDBUserDAO(MongoClient mongo) {
		this.col = mongo.getDB("Atlantis").getCollection("user");
	}

	public User createPerson(User u) {
		DBObject doc = UserConverter.toDBObject(u);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		u.setId(id.toString());
		return u;
	}

	public void updateUser(User u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		this.col.update(query, UserConverter.toDBObject(u));
	}

	public List<User> readAllUser() {
		List<User> data = new ArrayList<User>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			User u = UserConverter.toUser(doc);
			data.add(u);
		}
		return data;
	}
        
        public User findByEmail(String email) {
		User user = new User();
                BasicDBObject query = new BasicDBObject();
                query.put("email", email);
		DBCursor cursor = col.find(query);
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			user = UserConverter.toUser(doc);
		}
		return user;
	}

	public void deletePerson(User u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		this.col.remove(query);
	}

	public User readPerson(User u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		DBObject data = this.col.findOne(query);
		return UserConverter.toUser(data);
	}

}