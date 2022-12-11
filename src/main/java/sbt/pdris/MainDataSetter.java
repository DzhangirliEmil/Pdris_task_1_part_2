package sbt.pdris;

import com.mongodb.client.*;
import org.bson.Document;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainDataSetter {
    public static void main(String[] args) throws InterruptedException {
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);
        while(true) {
            System.out.println("5 секунд подожду и запишу в бд нового челка ");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Только что добавил:" + setUsers("Пользователь " + System.currentTimeMillis()));
        }
    }

    public static String setUsers(String userId) {
        MongoClient client = MongoClients.create("mongodb://db:27017");
        MongoDatabase database = client.getDatabase("users");
        MongoCollection<Document> users = database.getCollection("users");
        users.insertOne(new Document("userid", userId));
        return "bd updated by user with id" + userId;
    }
}