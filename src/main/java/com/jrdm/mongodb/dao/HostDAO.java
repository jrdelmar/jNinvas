package com.jrdm.mongodb.dao;

import com.google.gson.Gson;
import com.jrdm.common.Utility;
import com.jrdm.model.HostDto;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class HostDAO {

	//DAO class for different MongoDB CRUD operations
	//take special note of "id" String to ObjectId conversion and vice versa
	//also take note of "_id" key for primary key

	private DBCollection collection;
	private DB connection;

	//collection: table
	private String tableName = "HOST";
    final static Logger logger = Logger.getLogger(HostDAO.class);


	public HostDAO(DB conn){
	    connection = conn;
        collection = connection.getCollection(tableName);

    }

    public HostDto createHost(HostDto p){

        //Converting a custom Class(HostDto) to BasicDBObject
        Gson gson = new Gson();
        p.setInsertedDate(Utility.getCurrentDateForDb());

        BasicDBObject doc = (BasicDBObject) JSON.parse(gson.toJson(p));
        doc.append("_id",new ObjectId());
        collection.insert(doc);

        ObjectId id = (ObjectId) doc.get("_id");
        p.setId(id.toString());
        String insertedDate = (String) doc.get("insertedDate");
        p.setInsertedDate(insertedDate);

        return p;
    }

    public List<HostDto> readAll(){

        List<HostDto> data = new ArrayList<HostDto>();
       // DBCursor cursor = collection.find().sort(new BasicDBObject("insertedDate", -1));
        DBCursor cursor = collection.find();

        Gson gson = new Gson();

        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
			HostDto hostDto = gson.fromJson(doc.toString(),HostDto.class);

            //logger.debug(doc.toString());
            //logger.debug(hostDto.toString());
            data.add(hostDto);
        }
        return data;


    }
}
