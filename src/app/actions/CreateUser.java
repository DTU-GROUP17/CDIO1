package app.actions;

import app.App;
import models.User;

import java.util.Arrays;

public class CreateUser extends Action {
	public CreateUser(App app) {
		super(app);
	}

	@Override
	public void perform() {
		this.app.getUserController().createUser(
			new User(
				this.app.getUi().getString("Choose name"),
				this.app.getUi().getString("Choose initials"),
				this.app.getUi().getString("Choose cpr"),
				this.app.getUi().getString("Choose password"),
				Arrays.asList("Stor", "Mand")
			)
		);
	}
}
