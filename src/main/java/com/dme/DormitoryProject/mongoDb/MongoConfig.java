package com.dme.DormitoryProject.mongoDb;

import com.dme.DormitoryProject.repository.IStudentDao;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.Document;

public class MongoConfig {

    private IStudentDao studentDao;

    @Autowired
    public MongoConfig(IStudentDao studentDao){
        this.studentDao=studentDao;
    }

    public void MongoDBWriter(){
        try {
            var mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoCollection<Document> collection =
            MongoCollection<Document> collection = database.getCollection("yourCollection");

            // Örnek veri ekleme
            Document doc = new Document("name", "Example")
                    .append("value", 123);
            collection.insertOne(doc);

            System.out.println("Veri MongoDB'ye eklendi.");
        } catch (Exception e) {

        }
    }


}
