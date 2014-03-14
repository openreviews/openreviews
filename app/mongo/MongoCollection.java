package mongo;

import models.User;
import org.jongo.Find;
import org.jongo.Jongo;

/**
 * Created by pplr on 13/03/14.
 */
public abstract class MongoCollection<T> {

    public abstract String getCollectionName();

    public abstract Class<T> getModelClass();

    public org.jongo.MongoCollection getCollection(Jongo j){
        return j.getCollection(getCollectionName());
    }

    public T findOne(Jongo j, String query, Object... parameters){
        return getCollection(j).findOne(query, parameters).as(getModelClass());
    }

    public Find find(Jongo j){
        return getCollection(j).find();
    }
}
