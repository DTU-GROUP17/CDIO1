package app;

import dal.InMemoryUserDAO;
import ui.TUI;
import ui.UI;

public class Starter {
	public static void main(String... args){
		App app = new App(
				new TUI(),
				new InMemoryUserDAO()
		);
		app.start();
	}
}