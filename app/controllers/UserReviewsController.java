package controllers;

import models.ReviewStatus;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by pplr on 14/03/14.
 */
public class UserReviewsController extends Controller {

    public static Result list(String userOid){
        return ok();
    }

    public static Result updateStatus( String userOid, String reviewOid){
        return ok();
    }
}
