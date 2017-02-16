package app;

import dal.inmemorydao.InMemoryDAO;
import ui.TUI;

public class Starter {
	public static void main(String... args){
		App app = new App(
				new TUI(),
				new InMemoryDAO()
		);
		app.start();
	}
}
