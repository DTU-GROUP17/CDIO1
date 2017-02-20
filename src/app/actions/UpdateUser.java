package app.actions;

import java.util.Arrays;

import app.App;
import models.User;

public class UpdateUser extends Action {
	public UpdateUser(App app) {
		super(app);
	}

	@Override
	public void perform() {
		this.app.getUserController().updateUser(
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
