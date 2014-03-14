package controllers;

import models.User;
import mongo.Mongo;
import org.bson.types.ObjectId;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by pplr on 14/03/14.
 */
public class UserController extends Controller {

    public static Result dashboard(String userOid){
        User u = Mongo.findUserByOid(new ObjectId(userOid));
        if(u == null){
            return notFound();
        } else {
            return ok();
        }
    }

    public static Result edit(String userOid){
        return ok();
    }

    public static Result update(String userOid){
        return ok();
    }

}
