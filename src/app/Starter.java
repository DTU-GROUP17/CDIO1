package app;

import dal.jdbcdao.JDBCDAO;
import ui.TUI;

/**
 * Simple class which starts the whole program
 */
public class Starter {

	/**
	 * Main method for the program.
	 *
	 * @param args for application
	 */
	public static void main(String... args) {

		/*
		 * Here you can define which UI and DAO should be used for the
		 * whole application.
		 */
		App app = new App(
			new TUI(),
			new JDBCDAO("Weight")
		);
		app.start();
	}
}
