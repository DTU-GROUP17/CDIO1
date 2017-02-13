package app.Actions;

import app.App;

public abstract class Action{

	protected App app;

	public Action(App app){
		this.app = app;
	}

	public abstract void perform();

}
