package models;

import factories.InvalidInputException;
import factories.UserFactory;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

	@Before
	public void setUp() throws Exception {


	}

	@Test
	public void passwordVerify() throws Exception {

		// Invalid password - does not match 3 categories.
		assertThatThrownBy(() -> new UserFactory().setPassword("invalid").make())
				.isInstanceOf(InvalidInputException.class)
					.hasMessage("Password does not contain 3 of the 4 categories.");

		// Invalid password - Not long enough.
		assertThatThrownBy(() -> new UserFactory().setPassword("Ab!").make())
				.isInstanceOf(InvalidInputException.class)
					.hasMessage("Password is not 6 characters long.");

		// Invalid password - Contains username.
		assertThatThrownBy(() -> new UserFactory().setName("brugernavn").setPassword("Ab!aaa"+"brugernavn").make())
				.isInstanceOf(InvalidInputException.class)
					.hasMessage("Username cannot be part of password.");
	}

}