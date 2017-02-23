package controllers;

import dal.contracts.DAO;
import dal.exceptions.NotConnectedException;
import dal.exceptions.NotFoundException;
import factories.exceptions.*;
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

	private User buildUser(UserFactory builder) throws InvalidInputException {
		while (true) {
			try {
				return builder.make();
			} catch (InvalidCprException e) {
				this.ui.showMessage(e.getMessage());
				builder.setCpr(this.ui.getString("Choose new cpr"));
			} catch (InvalidInitialsException e) {
				this.ui.showMessage(e.getMessage());
				builder.setInitials(this.ui.getString("Choose new initials"));
			} catch (InvalidPasswordException e) {
				this.ui.showMessage(e.getMessage());
				builder.setPassword(this.ui.getString("Choose new password"));
			} catch (InvalidUsernameException e) {
				this.ui.showMessage(e.getMessage());
				builder.setName(this.ui.getString("Choose new username"));
			}
		}
	}

	public void createUser() {
		try {
			this.dao.getUserDAO().createUser(
				this.buildUser(
					new UserFactory()
						.setName(this.ui.getString("Choose name"))
						.setInitials(this.ui.getString("Choose initials"))
						.setCpr(this.ui.getString("Choose cpr"))
						.setPassword(this.ui.getString("Choose password"))
						.setRoles(this.chooseRoles())
				)
			);
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		} catch (InvalidInputException e) {
			this.ui.showMessage(e.getMessage());
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
			this.dao.getUserDAO().updateUser(
				this.buildUser(
					new UserFactory()
						.setId(user.getId())
						.setName(this.chooseNewProperty("Current name", user.getName()))
						.setInitials(this.chooseNewProperty("Current ini", user.getInitials()))
						.setCpr(this.chooseNewProperty("Current cpr", user.getCpr()))
						.setPassword(this.chooseNewProperty("Current password", user.getPassword()))
						.setRoles(this.chooseRoles())
				)
			);
		} catch (NotFoundException e) {
			this.ui.showMessage("No user with that id");
		} catch (NotConnectedException e) {
			this.ui.showMessage("Not connected to database");
		} catch (InvalidInputException e) {
			this.ui.showMessage(e.getMessage());
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
