package controllers;

import java.util.Date;
import java.util.Random;

import models.Author;
import models.Review;
import models.ReviewStatus;
import models.User;
import mongo.Mongo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestWidgetController {

	final int nbReviews = 3;
	float expectedAvg;
	User user;

	@Before
	public void setup() {
		user = getUser();
		expectedAvg = createReviews(user, nbReviews);
	}
	@After
	public void teardown() {
		Mongo.removeAll(user);
	}
	
	@Test
	@Ignore
	public void testComputeAverageOfReviews() {

		Iterable<Review> reviews = mongo.Mongo.findAllReviews(user, 10000, true);
		
		float computedAvg = WidgetsController.computeAverageOfReviews(reviews);

		Assert.assertEquals(expectedAvg, computedAvg);
	}


	/**
	 * create nbReviews reviews and return the average of the grades
	 * @param user
	 * @param nbReviews
	 * @return
	 */
	private float createReviews(User user, int nbReviews) {

		int sumOfGrade = 0;
		for (int i = 1; i <= nbReviews; i++) {

			int currentGrade = new Random().nextInt(5);
			sumOfGrade = currentGrade;
			Review review = new Review(new Date(), user, currentGrade,
					ReviewStatus.PUBLISHED, new Author("author" + i, "author"
							+ i + "@email.com"));
			review.text = "Texte de description " + i;

			mongo.Mongo.save(review);
		}
		return (float) sumOfGrade / nbReviews;
	}

	private User getUser() {
		User user = Mongo.findUserByEmail("test@email.com");
		if (user == null) {
			user = new User("test@email.com", "password", "itemName"
					+ new Date());
			Mongo.save(user);
		}
		return user;
	}

}
