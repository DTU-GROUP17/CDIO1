package factories;

import models.User;
import org.junit.Test;

import java.util.ArrayList;

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
		assertThat(UserFactory.factory.times(1).make() instanceof User).isTrue();

		// Create multiple user with times method.
		assertThat(UserFactory.factory.times(2).make() instanceof ArrayList).isTrue();
		assertThat(((ArrayList) UserFactory.factory.times(2).make()).size()).isEqualTo(2);

		((ArrayList) UserFactory.factory.times(2).make()).forEach(user -> assertThat(user instanceof User).isTrue());
	}

	@Test
	public void create() throws Exception {
		// Create 1 user.
		assertThat(UserFactory.factory.create() instanceof User).isTrue();

		// Create 1 user with times method.
		assertThat(UserFactory.factory.times(1).create() instanceof User).isTrue();

		// Create multiple user with times method.
		assertThat(UserFactory.factory.times(2).create() instanceof ArrayList).isTrue();
		assertThat(((ArrayList) UserFactory.factory.times(2).create()).size()).isEqualTo(2);

		((ArrayList) UserFactory.factory.times(2).create()).forEach(user -> assertThat(user instanceof User).isTrue());
	}

}