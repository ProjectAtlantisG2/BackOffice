/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.mongoDB.converter;

import org.bson.types.ObjectId;

import com.atlantis.beans.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class UserConverter {

	// convert Person Object to MongoDB DBObject
	// take special note of converting id String to ObjectId
	public static DBObject toDBObject(User u) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("name", u.getName()).append("email", u.getEmail())
                        .append("password", u.getHashedPassword()).append("salt", u.getSalt());
		if (u.getId() != null)
			builder = builder.append("_id", new ObjectId(u.getId()));
		return builder.get();
	}

	// convert DBObject Object to Person
	// take special note of converting ObjectId to String
	public static User toUser(DBObject doc) {
		User u = new User();
		u.setName((String) doc.get("name"));
		u.setEmail((String) doc.get("email"));
                u.setHashedPassword((String) doc.get("password"));
                u.setSalt((String) doc.get("salt"));
		ObjectId id = (ObjectId) doc.get("_id");
		u.setId(id.toString());
		return u;

	}
	
}