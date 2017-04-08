package com.jrdm.mongodb.dao;

import com.jrdm.common.MyConstants;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by joannarosedelmar on 24/3/17.
 */
public class MongoDBConnect {

    private String server;
    private int port ;
    private String dbname;
    private String user ;
    private char[] password;
    private DB connection;

    private Properties prop = new Properties();

    public DB getConnection(){
        try {
            InputStream in = getClass().getResourceAsStream(MyConstants.PROP_CONFIG_FILE);
            prop.load(in);

            server = prop.getProperty("dbserver");
            port  =  Integer.parseInt(prop.getProperty("dbport"));
            dbname = prop.getProperty("dbname");
            user = prop.getProperty("dbuser");
            password = prop.getProperty("dbpw").toCharArray();

            MongoClient mongoClient = new MongoClient(server , port);
            //TODO: Add credentials to mongod instance (programmatically?)
           // MongoCredential credential = MongoCredential.createCredential(user, dbname, password);

           connection = mongoClient.getDB( dbname );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;

    }
}
