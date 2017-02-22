package app.actions;

import app.App;


public class CreateUser extends Action {
	public CreateUser(App app) {
		super(app);
	}

	@Override
	public void perform() {
		this.app.getUserController().createUser();
	}
}
