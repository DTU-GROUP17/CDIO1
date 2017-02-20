package models;

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
		User user = UserFactory.factory.make();

		// Invalid password - does not match 3 categories.
		assertThatThrownBy(() -> user.setPassword("invalid"))
				.isInstanceOf(InvalidInputException.class)
					.hasMessage("Password does not contain 3 of the 4 categories.");

		// Invalid password - Not long enough.
		assertThatThrownBy(() -> user.setPassword("Ab!"))
				.isInstanceOf(InvalidInputException.class)
					.hasMessage("Password is not 6 characters long.");

		// Invalid password - Contains username.
		assertThatThrownBy(() -> user.setPassword("Ab!aaa"+user.getUserName()))
				.isInstanceOf(InvalidInputException.class)
					.hasMessage("Username cannot be part of password.");

		// Invalid password - Contains user ID.
		assertThatThrownBy(() -> user.setPassword("Ab!aaa"+user.getUserId()))
				.isInstanceOf(InvalidInputException.class)
					.hasMessage("User id cannot be part of password.");
	}

}