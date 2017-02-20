package factories;

import models.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;


public class UserFactoryTest {

	@Test
	public void instantiate() throws Exception {
		assertThat(UserFactory.factory.instantiate() instanceof User).isTrue();
	}

	@Test
	public void make() throws Exception {
		// Create 1 user.
		assertThat(UserFactory.factory.make() instanceof User).isTrue();

		// Create 1 user with times method.
		assertThat(UserFactory.factory.make(1) instanceof User).isTrue();

		// Create multiple user with times method.
		assertThat(UserFactory.factory.make(2) instanceof LinkedList).isTrue();
		assertThat(((LinkedList) UserFactory.factory.make(2)).size()).isEqualTo(2);

		((LinkedList) UserFactory.factory.make(3)).forEach(user -> assertThat(user instanceof User).isTrue());
	}

}