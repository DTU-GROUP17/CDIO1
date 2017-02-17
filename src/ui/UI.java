package ui;

import models.User;

import java.util.List;

public interface UI {

	String getString(String message);
	int getInt(String message);
	void showMessage(String message);
	void showUser(User user);
	void showUsers(List<User> user);

}
