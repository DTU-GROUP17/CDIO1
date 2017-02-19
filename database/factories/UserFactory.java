package factories;

import models.Model;
import models.User;

import java.util.ArrayList;


public class UserFactory extends Factory{


	public UserFactory(int x) {
		super(x);
	}

	@SuppressWarnings("unchecked")
	@Override
	<T extends Model> T create() {
		return (T) new User(
				faker.name().firstName(),
				faker.name().prefix(),
				faker.lorem().characters(10),
				faker.pokemon().name(),
				new ArrayList<>()
		);
	}
}
