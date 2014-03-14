package models;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class Author {
	@Required
	@MinLength(value = 3)
	public String authorName;

	@Email
	public String email;

	public Author() {
	}

}
