package controllers;

import java.util.Date;
import java.util.Random;

import models.Author;
import models.Review;
import models.ReviewStatus;
import models.User;
import mongo.Mongo;
import play.mvc.Controller;
import play.mvc.Result;

public class DevController extends Controller {
	final static int nbReviews = 4;

	public static Result inject(String userEmail) {
		User user = getUser(userEmail);

		createReviews(user, nbReviews);

		return ok(nbReviews + " reviews created for user '" + userEmail + "' (oid : " + user.getOid() + ")");
	}
	
/**
 * retrieve an existing user or create it
 * @param userEmail
 * @return
 */
	private static User getUser(String userEmail) {
		User user = Mongo.findUserByEmail(userEmail);
		if (user == null) {
			user = new User(userEmail, "password", "itemName" + new Date());
			Mongo.save(user);
		}
		return user;
	}

	/**
	 * @param user
	 * @param nbReviews
	 * @return
	 */
	private static void createReviews(User user, int nbReviews) {

		for (int i = 1; i <= nbReviews; i++) {
			int currentGrade = new Random().nextInt(5);
			Review review = new Review(new Date(), user, currentGrade,
					ReviewStatus.PUBLISHED, new Author("author" + i, "author"
							+ i + "@email.com"));
			review.text = "Texte de description " + i;

			mongo.Mongo.save(review);
		}
		
	}

}