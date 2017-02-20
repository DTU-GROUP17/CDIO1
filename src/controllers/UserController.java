package controllers;

import dal.exceptions.NotConnectedException;
import dal.exceptions.NotFoundException;
import dal.contracts.UserDAO;
import models.User;
import ui.UI;

import java.util.Arrays;

public class UserController {

	private UI ui;
	private UserDAO userDAO;

	public UserController(UI ui, UserDAO userDAO) {
		this.ui = ui;
		this.userDAO = userDAO;
	}

	public void showUser(int userId) {
		try {
			this.ui.showUser(this.userDAO.findUser(userId));
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	public void showUsers() {
		try {
			this.ui.showUsers(this.userDAO.getUsers());
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	public void createUser(User user) {
		try {
			this.userDAO.createUser(user);
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	private String chooseNewProperty(String originalVal) {
		String chosenValue = this.ui.getString("Choose new value for: "+originalVal);
		if (chosenValue.isEmpty()){
			return originalVal;
		} else {
			return chosenValue;
		}
	}

	public void updateUser(int userId) {
		try {
			User user = this.userDAO.findUser(userId);
			User other = new User(
				this.chooseNewProperty(user.getUserName()),
				this.chooseNewProperty(user.getInitials()),
				this.chooseNewProperty(user.getCpr()),
				this.chooseNewProperty(user.getPassword()),
				Arrays.asList("Stor", "Mand")
			);
			this.userDAO.updateUser(user);
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	public void deleteUser(int userId){
		try {
			this.userDAO.deleteUser(userId);
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

}
