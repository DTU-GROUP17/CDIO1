package app;

import app.actions.Action;
import app.actions.CreateUser;
import app.actions.ViewUsers;
import controllers.UserController;
import dal.DAO;
import dal.UserDAO;
import ui.UI;

import java.util.Hashtable;

public class App {

	private UI ui;
	private UserController userController;
	private Hashtable<String, Action> actions;

	public App(UI ui, DAO dao) {
		this.ui = ui;
		this.userController = new UserController(
				this.ui,
				dao.getUserDAO()
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
