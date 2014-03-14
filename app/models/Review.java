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
    public Date date;
    
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

    public Review(Date date, ObjectId idClient, int grade, ReviewStatus status, Author author) {
    	this.date = date;
    	this.userId = idClient;
    	this.grade = grade;
    	this.status = status;
    	this.author = author;
    }
    
}
