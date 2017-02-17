package app.actions;

import app.App;

public class DeleteUser extends Action {
	public DeleteUser(App app) {
		super(app);
	}

	@Override
	public void perform() {
		this.app.getUserController().deleteUser(
			this.app.getUi().getInt("Choose user id")
		);
	}
}
