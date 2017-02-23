package ui;

import models.User;

import java.util.List;

/**
 * Contract for our UI
 *
 * Defines the basic functions which our user interfaces requires.
 */
public interface UI {

	/**
	 * Gets a string written in the UI
	 *
	 * @param message to show in UI
	 * @return the text written in the UI
	 */
	String getString(String message);

	/**
	 * Gives the UI an list of options and returns the chosen option
	 * You can only choose one option with this
	 *
	 * @param message to show in UI
	 * @param options to choose from
	 * @param <T> type to return
	 * @return the chosen option
	 * @see UI#selectOptions(String, List)
	 */
	<T> T selectOption(String message, List<T> options);

	/**
	 * Gives the UI an list of options and returns the chosen options
	 * You can choose more than one option.
	 *
	 * @param message to show in UI
	 * @param options to choose from
	 * @param <T> type to return
	 * @return the chosen options
	 * @see UI#selectOption(String, List)
	 */
	<T> List<T> selectOptions(String message, List<T> options);

	/**
	 * Gets a string written in the UI
	 *
	 * @param message to show in UI
	 * @return the integer written in the UI
	 */
	int getInt(String message);

	/**
	 * Shows a message in the UI
	 *
	 * @param message to show
	 */
	void showMessage(String message);

	/**
	 * Shows a given user in the UI
	 *
	 * @param user to show
	 */
	void showUser(User user);

	/**
	 * Shows multiple users in the UI
	 *
	 * @param user to show
	 */
	void showUsers(List<User> user);

}
