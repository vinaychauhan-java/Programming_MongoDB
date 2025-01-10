package com.vinay.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.vinay.domain.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoDBCrud {

    private final MongoCollection<Document> collection;

    public MongoDBCrud(String connectionString, String databaseName, String collectionName) {
        MongoClient client = MongoClients.create(connectionString);
        MongoDatabase database = client.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (Document doc : collection.find()) {
            users.add(mapDocumentToUser(doc));
        }
        return users;
    }

    public Optional<User> getUserById(String id) {
        Document doc = collection.find(new Document("_id", new ObjectId(id))).first();
        return Optional.ofNullable(mapDocumentToUser(doc));
    }

    public Optional<User> getUserByName(String name) {
        Document doc = collection.find(new Document("name", name)).first();
        return Optional.ofNullable(mapDocumentToUser(doc));
    }

    public void createUser(User user) {
        Document doc = new Document("name", user.getName())
                .append("email", user.getEmail())
                .append("age", user.getAge());
        collection.insertOne(doc);
        user.setId(doc.getObjectId("_id").toString());
    }

    public void updateUser(String id, User user) {
        Document updatedDoc = new Document("name", user.getName())
                .append("email", user.getEmail())
                .append("age", user.getAge());
        collection.updateOne(new Document("_id", new ObjectId(id)), new Document("$set", updatedDoc));
    }

    public void deleteUser(String id) {
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    private User mapDocumentToUser(Document doc) {
        if (doc == null) {
            return null;
        }
        User user = new User();
        user.setId(doc.getObjectId("_id").toString());
        user.setName(doc.getString("name"));
        user.setEmail(doc.getString("email"));
        user.setAge(doc.getInteger("age"));
        return user;
    }

}
