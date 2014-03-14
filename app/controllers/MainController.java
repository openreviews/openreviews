package controllers;

import mongo.Mongo;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.User;

public class MainController extends Controller {
    
    public static Result index() {
        return ok(renderIndex(Form.form(User.class)));
    }

    public static Html renderIndex(Form<User> form){
        return views.html.index.render(form, Mongo.findAllUsers());
    }

}
