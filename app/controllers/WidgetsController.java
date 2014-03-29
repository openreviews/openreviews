package controllers;

import models.Review;
import models.User;
import models.WidgetData;

import org.bson.types.ObjectId;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by pplr on 14/03/14.
 */
public class WidgetsController extends Controller {

	public static Result show(String userOid) {
		ObjectId oid;
		try {
			oid = new ObjectId(userOid);
		} catch (IllegalArgumentException e) {
			return notFound("Invalid userOid : " + userOid);
		}
		User user = mongo.Mongo.findUserByOid(oid);

		WidgetData widgetData = new WidgetData();

		if (user == null) {
			return notFound("Unknown userOid : " + userOid);
		}
		
		widgetData.reviewsCount = mongo.Mongo.countReviews(user, true);
		widgetData.notationPageUrl = controllers.routes.ReviewsController.new_(user.getOid().toString()).url();
		widgetData.itemName = user.itemName;
		widgetData.notationPageLabel = "Donnez votre avis";

		if (widgetData.reviewsCount > 0) {
			// if the item has at least one review 
			Iterable<Review> reviews = mongo.Mongo.findAllReviews(user, 10000, true);
			widgetData.reviews = reviews;
			
			Float computedAverageOfReviews = computeAverageOfReviews(reviews);
			widgetData.grade = roundToOneDecimal(computedAverageOfReviews);
			widgetData.starsCount = Math.round(computedAverageOfReviews);
		} else {
			// if no review on item
			widgetData.reviews = null;
			widgetData.grade = null;
			widgetData.starsCount = 0;
		}
		
		return ok(views.html.widget.render(widgetData));
	}

	/**
	 * return a float rounded to one decimal
	 * @param value
	 * @return
	 */
	private static float roundToOneDecimal(float value) {
		return (float)(Math.round(value*10)) / 10;
	}

	/**
	 * compute the average of the grade of the reviews
	 * @param reviews
	 * @return null if no reviews
	 */
	static Float computeAverageOfReviews(Iterable<Review> reviews) {
		Float avgGrade = null;
		float sumOfGrade = 0;
		float numberOfReviews = 0;
		if (reviews != null && reviews.iterator().hasNext()) {
			for (Review review : reviews) {
				sumOfGrade += review.grade;
				numberOfReviews++;
			}
			avgGrade = sumOfGrade / (float)numberOfReviews;
		}
		return avgGrade;
	}

}
