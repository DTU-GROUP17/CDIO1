package factories;

import models.DTO;
import models.User;

import java.util.ArrayList;


public class UserFactory extends Factory {
	public static Factory factory = new UserFactory();

	@SuppressWarnings("unchecked")
	@Override
	<T extends DTO> T instantiate() {
		return (T) new User(
				faker.number().randomDigit(),
				faker.name().firstName(),
				faker.name().prefix(),
				faker.lorem().characters(10),
				faker.pokemon().name(),
				new ArrayList<>()
		);
	}
}
