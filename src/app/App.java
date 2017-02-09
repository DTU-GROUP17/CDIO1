package app;

import controllers.UserController;
import dal.JDBCUserDAO;
import dal.UserDAO;
import ui.UI;

public class App {

	private UI ui;
	private UserController userController;

	public App(UI ui) {
		this.ui = ui;
		this.userController = new UserController(new JDBCUserDAO());
	}

	public void start() {
		String choice;
		do {
			choice =
		} while (true);
	}

	public UI getUi() {
		return ui;
	}

}
