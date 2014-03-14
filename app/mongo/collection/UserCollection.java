package mongo.collection;

import models.User;
import mongo.MongoCollection;
import org.jongo.Jongo;

public class UserCollection extends MongoCollection<User>{

    private final static String COLLECTION_NAME = "users";

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public Class<User> getModelClass() {
        return User.class;
    }

    public Iterable<User> allUsers(Jongo j){
        return find(j).sort("{username: 1}").as(User.class);
    }
}
