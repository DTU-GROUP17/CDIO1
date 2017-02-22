package ui;

import models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TUI implements UI {

	private Scanner scanner;

	public TUI(){
		this.scanner = new Scanner(System.in);
	}

	@Override
	public String getString(String message) {
		this.scanner.reset();
		System.out.print(message+": ");
		return this.scanner.nextLine();
	}

	@Override
	public <T> T selectOption(String message, List<T> options) {
		List<String> optionsRepresentation =
			options.stream()
				.map(object -> object.toString())
					.collect(Collectors.toList());
		System.out.println(message+": "+optionsRepresentation.toString());
		int index = 0;
		while (!optionsRepresentation.isEmpty()) {
			String selected = this.scanner.nextLine();
			if (optionsRepresentation.contains(selected)) {
				index = optionsRepresentation.indexOf(selected);
				break;
			} else {
				System.out.println("No such option");
			}
		}
		return options.get(index);
	}

	@Override
	public <T> List<T> selectOptions(String message, List<T> options) {
		List<T> selection = new LinkedList<>();
		while (!options.isEmpty()) {
			List<String> optionsRepresentation =
				options.stream()
					.map(object -> object.toString())
					.collect(Collectors.toList());
			System.out.println(
				message
				+": "
				+optionsRepresentation.toString()
				+", press enter to finish selecting\nCurrently selected: "
				+selection.toString()
			);
			while (!optionsRepresentation.isEmpty()) {
				String selected = this.scanner.nextLine();
				if (optionsRepresentation.contains(selected)) {
					T obj = options.get(optionsRepresentation.indexOf(selected));
					options.remove(obj);
					selection.add(obj);
					break;
				} else if (selected.isEmpty()){
					return selection;
				} else {
					System.out.println("No such option");
				}
			}
		}
		return selection;
	}

	@Override
	public int getInt(String message) {
		System.out.print(message+": ");
		return this.scanner.nextInt();
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void showUser(User user) {
		System.out.println("user: "+user.getId());
	}

	@Override
	public void showUsers(List<User> users) {
		System.out.println("All users:");
		for (User user : users){
			this.showUser(user);
		}
	}

}
