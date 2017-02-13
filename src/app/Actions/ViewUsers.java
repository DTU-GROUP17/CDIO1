package app.Actions;

import app.App;

public class ViewUsers extends Action {
	public ViewUsers(App app) {
		super(app);
	}

	@Override
	public void perform() {
		this.app.getUserController().showUsers();
	}

}
