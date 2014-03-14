package mongo;

import com.mongodb.DB;
import com.mongodb.WriteResult;
import models.User;
import mongo.collection.UserCollection;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import play.Play;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class Mongo{

    private final static UserCollection USER_COLLECTION = new UserCollection();

    public static Jongo jongo(){
        // TODO : connect at application startup
	    MongoClientURI mcUri = new MongoClientURI(Play.application().configuration().getString("mongodb.uri"));
        try {
            MongoClient mongoClient = new MongoClient(mcUri);
            DB db = mongoClient.getDB(mcUri.getDatabase());
            return new Jongo(db);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }

    public static User findUserByEmail(String email){
        return USER_COLLECTION.findOne(jongo(), "{email: #}", email);
    }

    public static Iterable<User> findAllUsers(){
        return USER_COLLECTION.allUsers(jongo());
    }

    public static WriteResult save(User u){
        MongoCollection users = USER_COLLECTION.getCollection(jongo());
        return users.save(u);
    }

}
