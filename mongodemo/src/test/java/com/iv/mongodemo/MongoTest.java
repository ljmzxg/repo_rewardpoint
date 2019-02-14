package com.iv.mongodemo;


import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoTest {
	
	public static void main(String[] args) {
		//链接MongoDB 服务器
		MongoClient client = new MongoClient("192.168.8.82");
		//得到要操作的数据库
		MongoDatabase spitdb = client.getDatabase("spitdb");
		//得到要操作的集合
		MongoCollection<Document> spit = spitdb.getCollection("spit");
		//得到集合中的所有文档
		
		BasicDBObject bson = new BasicDBObject("visits", new BasicDBObject("$gt", 1000));
		
		FindIterable<Document> documents = spit.find(bson);
		
		for(Document d : documents) {
			System.out.println("_id:" + d.getString("_id"));
			System.out.println("title:" + d.getString("title"));
			System.out.println("location:" + d.getString("location"));
		}
		
		client.close();
		
	}

}
