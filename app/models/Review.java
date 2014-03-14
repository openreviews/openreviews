package models;

import java.util.Date;

import org.bson.types.ObjectId;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

public class Review {

    public final static String MONGO_COLLECTION = "reviews";

    private ObjectId _id;
   
    @Required
    public Date createdAt;
    
    @Required
    public ObjectId userId;

    @Required
    @Min(value=0)
    @Max(value=5)
    public Integer grade;
    
    @Required
    public ReviewStatus status;
    
    public String text;
    
    @Required
    public Author author;
    
    public Review() {}

    public Review(Date createdAt, ObjectId idClient, int grade, ReviewStatus status, Author author) {
    	this.createdAt = createdAt;
    	this.userId = idClient;
    	this.grade = grade;
    	this.status = status;
    	this.author = author;
    }
    
    public Review(Date createdAt, User user, int grade, ReviewStatus status, Author author) {
        this(createdAt, user.getOid(), grade, status, author);
    }
    
}
