package mongo;

import com.mongodb.DB;
import com.mongodb.WriteResult;
import models.Review;
import models.User;
import mongo.collection.ReviewCollection;
import mongo.collection.UserCollection;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import play.Play;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class Mongo{

    private final static UserCollection USER_COLLECTION = new UserCollection();
    private final static ReviewCollection REVIEW_COLLECTION = new ReviewCollection();

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

    public static User findUserByOid(ObjectId oid){
        return USER_COLLECTION.findOne(jongo(), oid);
    }

    public static User findUserByEmail(String email){
        return USER_COLLECTION.findOne(jongo(), "{email: #}", email);
    }

    public static Iterable<User> findAllUsers(){
        return USER_COLLECTION.allUsers(jongo());
    }

    public static Iterable<Review> findAllReviews(User user, int limit){
        return REVIEW_COLLECTION.findAllReviews(jongo(), user, limit);
    }

    public static WriteResult save(User u){
        return USER_COLLECTION.getCollection(jongo()).save(u);
    }

    public static WriteResult save(Review r){
        return REVIEW_COLLECTION.getCollection(jongo()).save(r);
    }

}
