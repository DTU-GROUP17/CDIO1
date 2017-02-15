package controllers;

import dal.DALException;
import dal.NotConnectedException;
import dal.NotFoundException;
import dal.UserDAO;
import models.User;
import ui.UI;

public class UserController {

	private UI ui;
	private UserDAO userDAO;

	public UserController(UI ui, UserDAO userDAO) {
		this.ui = ui;
		this.userDAO = userDAO;
	}

	public void showUser(int userId) throws DALException {
		try {
			this.ui.showUser(this.userDAO.findUser(userId));
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	public void showUsers() throws DALException {
		try {
			this.ui.showUsers(this.userDAO.getUsers());
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	public void createUser(User user) throws DALException {
		try {
			this.userDAO.createUser(user);
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	public void updateUser(User user) throws DALException {
		try {
			this.userDAO.updateUser(user);
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

}
