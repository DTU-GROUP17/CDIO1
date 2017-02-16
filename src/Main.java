import ui.*;

import java.sql.SQLException;

import app.App;
import controllers.*;
import dal.DALException;
import dal.JDBCUserDAO;
import dal.UserDAO;
public class Main {

	public static void main(String[] args) throws DALException, SQLException {
		UserDAO d = new JDBCUserDAO(false);
		UI g = new TUI();
		UserController ctrl = new UserController(g, d);
		App app = new App(g);
		app.start();
		
		
		
		

	}
}

