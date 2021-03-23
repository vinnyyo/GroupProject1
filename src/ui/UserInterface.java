package ui;

import business.facade.Store;

public class UserInterface {

	private static UserInterface userInterface;
	private static Store store;

	private UserInterface() {
		store = Store.instance();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
