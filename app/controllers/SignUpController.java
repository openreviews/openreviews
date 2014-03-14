package controllers;

import com.mongodb.WriteResult;
import mongo.Mongo;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.User;


public class SignUpController extends Controller {

    /**
     * Defines a form wrapping the User class.
     */
    final static Form<User> signupForm = Form.form(User.class);


    public static Result submit(){
        Form<User> filledForm = signupForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(MainController.renderIndex(filledForm));
        } else {
            User user = filledForm.get();
            WriteResult r = Mongo.save(user);
            return redirect(controllers.routes.MainController.index());
        }

    }
}
