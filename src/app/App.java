package app;

import app.Actions.Action;
import app.Actions.CreateUser;
import app.Actions.ViewUsers;
import controllers.UserController;
import dal.JDBCUserDAO;
import dal.UserDAO;
import ui.UI;

import java.util.Hashtable;

public class App {

	private UI ui;
	private UserController userController;
	private Hashtable<String, Action> actions;

	public App(UI ui, UserDAO userDAO) {
		this.ui = ui;
		this.userController = new UserController(
				this.ui,
				userDAO
		);
		this.actions = new Hashtable<>();
		this.actions.put("Create user", new CreateUser(this));
		this.actions.put("View users", new ViewUsers(this));
	}

	private void chooseAction(String actionKey){
		if (this.actions.containsKey(actionKey)){
			this.actions.get(actionKey).perform();
		} else {
			this.getUi().showMessage("No such action '" + actionKey + "'");
		}
	}

	public void start() {
		String choice;
		do {
			this.chooseAction(
				this.getUi().getString(
					"Choose action: " + this.actions.keySet().toString()
				)
			);
		} while (true);
	}

	public UI getUi() {
		return ui;
	}

	public UserController getUserController() {
		return userController;
	}
}
