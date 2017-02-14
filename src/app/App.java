package app;

import java.sql.SQLException;

import controllers.UserController;
import dal.DALException;
import dal.JDBCUserDAO;
import dal.UserDAO;
import ui.UI;

public class App {

	private UI ui;
	private UserController userController;

	public App(UI ui) throws DALException, SQLException {
		this.ui = ui;
		this.userController = new UserController(ui, new JDBCUserDAO(false));
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
