package controllers;

import dal.contracts.DAO;
import dal.exceptions.NotConnectedException;
import dal.exceptions.NotFoundException;
import factories.InvalidInputException;
import factories.UserFactory;
import models.Role;
import models.User;
import ui.UI;

import java.util.List;

public class UserController {

	private UI ui;
	private DAO dao;

	public UserController(UI ui, DAO dao) {
		this.ui = ui;
		this.dao = dao;
	}

	public void showUser(int userId) {
		try {
			this.ui.showUser(this.dao.getUserDAO().findUser(userId));
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	public void showUsers() {
		try {
			this.ui.showUsers(this.dao.getUserDAO().getUsers());
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	private List<Role> chooseRoles() throws NotConnectedException {
		return this.ui.selectOptions("Select roles", this.dao.getRoleDAO().getRoles());
	}

	public void createUser() {
		try {
			User user;
			UserFactory builder =
				new UserFactory()
					.setName(this.ui.getString("Choose name"))
					.setInitials(this.ui.getString("Choose initials"))
					.setCpr(this.ui.getString("Choose cpr"))
					.setPassword(this.ui.getString("Choose password"))
					.setRoles(this.chooseRoles());
			while (true) {
				try {
					user = builder.make();
					break;
				} catch (InvalidInputException e) {
					this.ui.showMessage(e.getMessage());
					builder.setPassword(this.ui.getString("Choose new password"));
				}
			}
			this.dao.getUserDAO().createUser(user);
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	private String chooseNewProperty(String message, String originalVal) {
		String chosenValue = this.ui.getString(message+": "+originalVal);
		if (chosenValue.isEmpty()){
			return originalVal;
		} else {
			return chosenValue;
		}
	}

	public void updateUser(int userId) {
		try {
			User user = this.dao.getUserDAO().findUser(userId);
			User newUser;
			UserFactory builder =
				new UserFactory()
					.setId(user.getId())
					.setName(this.chooseNewProperty("Current name", user.getName()))
					.setInitials(this.chooseNewProperty("Current ini", user.getInitials()))
					.setCpr(this.chooseNewProperty("Current cpr", user.getCpr()))
					.setPassword(this.chooseNewProperty("Current password", user.getPassword()))
					.setRoles(this.chooseRoles());
			while (true) {
				try {
					newUser = builder.make();
					break;
				} catch (InvalidInputException e) {
					this.ui.showMessage(e.getMessage());
					builder.setPassword(this.ui.getString("Choose new password"));
				}
			}
			this.dao.getUserDAO().updateUser(newUser);
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

	public void deleteUser(int userId){
		try {
			this.dao.getUserDAO().deleteUser(userId);
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		}
	}

}
