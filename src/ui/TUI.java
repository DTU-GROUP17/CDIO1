package ui;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import controllers.UserController;
import models.User;



public class TUI implements UI{
	
	
	UserController ctrl;
	Scanner input;
	Console console;
	User user;
	String cpr;
	String uid;
		
		public TUI() {
			input = new Scanner(System.in);
			console = System.console();

		}
		
		public void dialog() {
			
			System.out.println("1: Opret bruger");
			System.out.println("2: Vis brugere");
			System.out.println("3: Opdater bruger");
			System.out.println("4: Slet brugere");
			
			int valg = input.nextInt();
			
			switch (valg) {
				case 1: createUser();
						break;
				case 2: break;
				case 3: break;
				case 4: break;
				
			}
			
			
			
		}
			

		private void createUser() {
			
			System.out.println("Indtast userId");
			int userId = input.nextInt();
			System.out.println("Indtast brugernavn");
			String userName = input.nextLine();
			System.out.println("Indtast initialer");
			String initials = input.nextLine();
			System.out.println("Indtast cpr");
			String cpr = input.nextLine();
			System.out.println("Indtast password");
			char[] passwordString = console.readPassword();
			String password = new String(passwordString);
			List<String> roles 
			
			
			
			User bruger = new User(userId, userName, initials, password, cpr, tc, );
		}
		
		@Override
		public String getString(String message) {

		}

		@Override
		public void showMessage(String message) {
			System.out.println(message);
			
		}

		@Override
		public void showUser(User user) {
			
			int userId = user.getUserId();
			String username = user.getUserName();
			String initials = user.getInitials();
			String cpr = user.getCpr();
			String password = user.getPassword();
			String roles = user.getRoles().toString();
			
			
			
		}

		@Override
		public void showUsers(List<User> user) {
			// TODO Auto-generated method stub
		}

	}

	
	

