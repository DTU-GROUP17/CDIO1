package app.actions;

import app.App;

public class CloseProgram extends Action {

	public CloseProgram(App app) {
		super(app);
	}

	@Override
	public void perform() {
		System.exit(0);
	}

}
