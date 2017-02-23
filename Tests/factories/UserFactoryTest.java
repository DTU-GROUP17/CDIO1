package factories;

import models.User;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class UserFactoryTest {

	@Test
	public void make() throws Exception {
		// Create 1 user.
		assertThat(new UserFactory().make() instanceof User).isTrue();

		// Create 1 user with times method.
		assertThat(new UserFactory().make(1) instanceof User).isTrue();

		// Create multiple user with times method.
		assertThat(new UserFactory().make(2) instanceof LinkedList).isTrue();
		assertThat(((LinkedList) new UserFactory().make(2)).size()).isEqualTo(2);

		((LinkedList) new UserFactory().make(3)).forEach(user -> assertThat(user instanceof User).isTrue());
	}

}