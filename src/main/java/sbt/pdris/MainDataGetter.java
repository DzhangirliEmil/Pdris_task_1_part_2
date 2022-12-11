package sbt.pdris;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MainDataGetter {
    public static void main(String[] args) throws InterruptedException {
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);
        while(true) {
            System.out.println("10 секунд подожду и прочитаю из бд: ");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("Получайте ваши данные из бд : " + getUsers());
        }
    }

    private static String getUsers() {
        try {
            MongoClient client = MongoClients.create("mongodb://db:27017");
            MongoDatabase database = client.getDatabase("users");
            MongoCollection<Document> users = database.getCollection("users");
            StringBuilder answer = new StringBuilder("The users:");

            MongoCursor<Document> iter = users.find().iterator();
            while(iter.hasNext()) {
                answer.append(iter.tryNext().get("userid") + ", ");
            }
            return answer.toString();
        } catch (Exception e) {
            return "error connecting";
        }
    }
}