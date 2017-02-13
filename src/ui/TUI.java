package ui;

import models.User;

import java.util.List;
import java.util.Scanner;

public class TUI implements UI {

	private Scanner scanner;

	public TUI(){
		this.scanner = new Scanner(System.in);
	}

	@Override
	public String getString(String message) {
		System.out.print(message+": ");
		return this.scanner.nextLine();
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void showUser(User user) {
		System.out.println("user: "+user.getUserId());
	}

	@Override
	public void showUsers(List<User> users) {
		System.out.println("All users:");
		for (User user : users){
			this.showUser(user);
		}
	}

}