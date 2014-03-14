package models;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class Author {
	@Required
	@MinLength(value = 3)
	public String name;

	@Email
	public String email;

	public Author() {
	}

	public Author(String name) {
		this.name = name;
	}

	public Author(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
