package mongo.collection;

import models.Review;
import models.ReviewStatus;
import models.User;
import mongo.MongoCollection;

import org.jongo.Jongo;

/**
 * Created by pplr on 14/03/14.
 */
public class ReviewCollection extends MongoCollection<Review> {

    private final static String COLLECTION_NAME = "reviews";

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public Class<Review> getModelClass() {
        return Review.class;
    }

    public Iterable<Review> findAllReviews(Jongo j, User user, int limit){
    	return find(j, "{userId: #}", user.getOid()).limit(limit).
//                sort("createdAt: -1").
                as(Review.class);
    }
    
    public Iterable<Review> findAllReviews(Jongo j, User user, int limit, boolean onlyPublishedReviews){
    	if (onlyPublishedReviews) {
    		return find(j, "{userId: #, status: #}", user.getOid(), ReviewStatus.PUBLISHED).limit(limit).
//                sort("createdAt: -1").
                as(Review.class);
    	} else {
    		return find(j, "{userId: #}", user.getOid()).limit(limit).
//                  sort("createdAt: -1").
                  as(Review.class);
    	}
    }
    
    public long count(Jongo j, User user, boolean onlyPublishedReviews){
    	if (onlyPublishedReviews) {
    		return count(j, "{userId: #, status: #}", user.getOid(), ReviewStatus.PUBLISHED);
    	} else {
    		return count(j, "{userId: #}", user.getOid());
    	}
    } 
}
