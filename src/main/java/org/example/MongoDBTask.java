package org.example;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MongoDBTask {
    public static void main(String [] args){
        String url = "mongodb+srv://admin:8g1Ocw53JGQI4gvD@cluster0.aqm9fvy.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase database = mongoClient.getDatabase("project4");
            MongoCollection table = database.getCollection("task1");
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Please provide a string to store in the DB: ");
                String str = in.nextLine();
                Document document = new Document("string", str);
                table.insertOne(document);
                System.out.println("Connected successfully to server.");
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }

            // Read all documents currently stored in the database.
            System.out.println("All strings contained in these documents:");
            FindIterable<Document> findIterable = table.find();
            Iterator iterator = findIterable.iterator();
            // Print all strings contained in these documents to the console:
            while (iterator.hasNext()) {
                Document document = new Document((Map<String, Object>) iterator.next());
                System.out.println(document.get("string"));
            }

        }
    }
}