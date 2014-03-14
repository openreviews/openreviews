package models;

import javax.validation.*;

import org.bson.types.ObjectId;
import play.data.validation.Constraints.*;

public class User {

    private ObjectId _id;
    
    @Required
    @Email
    public String email;
    
    @Required
    @MinLength(value = 6)
    public String password;

    @Required
    @MinLength(value = 2)
    public String itemName;

    public User() {}
    
    public User(String email, String password, String itemName) {
        this.email = email;
        this.password = password;
        this.itemName = itemName;
    }
    
    public ObjectId getUserId() {
    	return _id;
    }
}
