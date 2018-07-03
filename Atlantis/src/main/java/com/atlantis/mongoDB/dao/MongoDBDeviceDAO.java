/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.mongoDB.dao;

import com.atlantis.mongoDB.converter.DeviceConverter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.atlantis.mongoDB.converter.UserConverter;
import com.atlantis.beans.Device;
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
public class MongoDBDeviceDAO {

	private DBCollection col;

	public MongoDBDeviceDAO(MongoClient mongo) {
		this.col = mongo.getDB("Atlantis").getCollection("device");
	}

	public Device createDevice(Device d) {
		DBObject doc = DeviceConverter.toDBObject(d);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		d.setId(id.toString());
		return d;
	}

	public void updateDevice(Device d) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(d.getId())).get();
		this.col.update(query, DeviceConverter.toDBObject(d));
	}

	public List<Device> readAllDevice() {
		List<Device> data = new ArrayList<Device>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Device d = DeviceConverter.toDevice(doc);
			data.add(d);
		}
		return data;
	}
         public Device findByMacAddress(String macAddress) {
		Device device = new Device();
                BasicDBObject query = new BasicDBObject();
                query.put("macAddress", macAddress);
		DBCursor cursor = col.find(query);
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			device = DeviceConverter.toDevice(doc);
		}
		return device;
	}
         
         public List<Device> findByNameEmployee(String name, String id) {
		List<Device> devices = new ArrayList<Device>();
                BasicDBObject query = new BasicDBObject();
                query.put("employee", name);
		DBCursor cursor = col.find(query);
                
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Device device = DeviceConverter.toDevice(doc);
                        String idDevice = device.getIdEmployee().toString();
                        if (idDevice.equals(id)) {
                            devices.add(device);
                        }
		}
		return devices;
	}

	public void deleteDevice(Device d) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(d.getId())).get();
		this.col.remove(query);
	}

	public Device readDevice(Device d) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(d.getId())).get();
		DBObject data = this.col.findOne(query);
		return DeviceConverter.toDevice(data);
	}

}