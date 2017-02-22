package app.actions;

import app.App;

public class UpdateUser extends Action {
	public UpdateUser(App app) {
		super(app);
	}

	@Override
	public void perform() {
		this.app.getUserController().updateUser(
			this.app.getUi().getInt("Choose user id")
		);
	}
}
