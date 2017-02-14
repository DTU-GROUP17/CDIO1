package ui;

import java.util.List;
import java.util.Scanner;
import controllers.UserController;
import models.User;



public class TUI implements UI{
	
	
	
	Scanner input;
	User user;
	String cpr;
	String uid;
		
		public TUI() {
			input = new Scanner(System.in);

		}
		
		public void dialog() {
			
			System.out.println("1: Opret bruger");
			System.out.println("2: Vis brugere");
			System.out.println("3: Opdater bruger");
			System.out.println("4: Slet brugere");
			
			int valg = input.nextInt();
			
			switch (valg) {
				case 1: 
						break;
				case 2: break;
				case 3: break;
				case 4: break;
				
			}
			
			
			
		}
			

		@Override
		public String getString(String message) {
			// TODO Auto-generated method stub
			return null;
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

	
	

