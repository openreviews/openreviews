package mongo;

import models.User;
import org.bson.types.ObjectId;
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

    public T findOne(Jongo j, ObjectId oid){
        return getCollection(j).findOne(oid).as(getModelClass());
    }

    public Find find(Jongo j){
        return getCollection(j).find();
    }

    public Find find(Jongo j, String query) {
        return getCollection(j).find(query);
    }

    public Find find(Jongo j, String query, Object... parameters) {
        return getCollection(j).find(query, parameters);
    }
    
    public long count(Jongo j, String query, Object... parameters) {
    	return getCollection(j).count(query, parameters);
    }
}
