package ui;

import java.util.List;
import java.util.Scanner;

import controllers.UserController;
import models.User;

public class TUI implements UI{
		
	Scanner tastatur;
	String cpr;
	String uid;
		
		public TUI() {
			tastatur = new Scanner(System.in);
			cpr = null;
			uid = null;
		}
		
		public void dialog() {
			
		}
			

		@Override
		public String getString(String message) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void showMessage(String message) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void showUser(User user) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void showUsers(List<User> user) {
			// TODO Auto-generated method stub
			
		}

	}

	
	
}
