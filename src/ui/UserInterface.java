package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import business.entities.iterators.SafeMemberIterator;
import business.entities.iterators.SafeOrderIterator;
import business.entities.iterators.SafeProductIterator;
import business.facade.Request;
import business.facade.Result;
import business.facade.Store;

public class UserInterface {

	private static UserInterface userInterface;
	private static Store store;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final int EXITUI = 0;
	private static final int ADDMEMBER = 1;
	private static final int REMOVEMEMBER = 2;
	private static final int ADDPRODUCT = 3;
	private static final int CHECKOUT = 4;
	private static final int PROCESSSHIPMENT = 5;
	private static final int CHANGEPRICE = 6;
	private static final int GETPRODUCTINFO = 7;
	private static final int GETMEMBERINFO = 8;
	private static final int PRINTTRANSACTIONS = 9;
	private static final int LISTOUTSTANDINGORDERS = 10;
	private static final int LISTMEMBERS = 11;
	private static final int LISTPRODUCT = 12;
	private static final int SAVE = 13;
	private static final int HELP = 14;

	/**
	 * UserInterface Constructor this is a Singleton
	 */
	private UserInterface() {
		if (confirm("Load saved data? ")) {
			store = Store.retrieve();
			// load data
		} else {
			store = Store.instance();
		}
	}

	/**
	 * Method to get the instance of the Singleton
	 * 
	 * @return the instance of UserInterface
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Simple function to get a Yes or No answer
	 * 
	 * @param prompt Prompt for user
	 * @return true or false from input
	 */
	private boolean confirm(String prompt) {
		String confirmation = getToken(prompt + " Y for yes, anything else for no: ");
		if (confirmation.charAt(0) == 'y' || confirmation.charAt(0) == 'Y') {
			return true;
		}
		return false;
	}

	/**
	 * Gets a String from the input
	 * 
	 * @param prompt Prompt for user
	 * @return String from user
	 */
	private String inputString(String prompt) {
		String input = getToken(prompt);

		return input;
	}

	/**
	 * Gets an integer from the user, verifies to make sure it is an integer
	 * 
	 * @param prompt Prompt for user
	 * @return integer from user
	 */
	private int inputInteger(String prompt) {
		boolean isInt = false;
		int output = 0;

		do {
			String input = getToken(prompt);
			try {
				output = Integer.parseInt(input);
				isInt = true;
			} catch (NumberFormatException e) {
				System.out.println("Not a number. Try again.");
			}
		} while (!isInt);
		return output;
	}

	/**
	 * Gets a double from the user, verifies to make sure it is an double
	 * 
	 * @param prompt Prompt for user
	 * @return double from user
	 */
	private double inputDouble(String prompt) {
		boolean isDouble = false;
		double output = 0.0;

		do {
			String input = getToken(prompt);
			try {
				output = Double.parseDouble(input);
				isDouble = true;
			} catch (NumberFormatException e) {
				System.out.println("Not a double. Try again.");
			}
		} while (!isDouble);
		return output;
	}

	/**
	 * Prompts the store to save it's data
	 * 
	 * @return the success of the save method
	 */
	private boolean saveStore() {
		boolean returnValue;

		System.out.println("Saving Store Data.");
		returnValue = Store.save();
		if (returnValue) {
			System.out.println("Save Successful.");
		} else {
			System.out.println("Failure to save data.");
		}
		return returnValue;
	}

	/**
	 * Enrolls a member to the store Requires Name, Address, Phone Number, Fees
	 * Paid. Sets the join date to the current date.
	 */
	private void enrollMember() {
		Request request = new Request();
		System.out.println("Enroll a member:");
		request.setMemberName(inputString("Enter Name :"));
		request.setMemberAddress(inputString("Enter Address :"));
		request.setMemberPhoneNumber(inputString("Enter Phone number :"));
		request.setMemberFees(inputDouble("Enter Fee Paid :"));
		request.setMemberJoinDate(new Date());
		Result result = store.enrollMember(request);
		if (result.getSuccess()) {
			System.out.println("Member enrolled.");
		} else {
			System.out.println("Unable to enroll member.");
		}
	}

	/**
	 * Removes a member from the store Requests the id of the member, if member is
	 * found, they are removed
	 */
	private void removeMember() {
		Request request = new Request();
		System.out.println("Remove member by id:");
		int memberId = inputInteger("Enter member id :");
		request.setMemberId(memberId);
		Result result = store.removeMember(request);
		if (result.getSuccess()) {
			System.out.println("Member " + memberId + "removed.");
		} else {
			System.out.println("Member not found.");
		}
	}

	/**
	 * Adds a product to the store Requires Product name, id, stock price and
	 * reorder level
	 */
	private void addProduct() {
		Request request = new Request();
		System.out.println("Adding Product");
		request.setProductName(inputString("Enter Product name : "));
		request.setProductId(inputString("Enter Product id : "));
		request.setProductStock(inputInteger("Enter Product stock : "));
		request.setProductPrice(inputDouble("Enter Product price : "));
		request.setProductReOrderLevel(inputInteger("Enter Product reorder level : "));
		Result result = store.addProduct(request);
		if (result.getSuccess()) {
			System.out.println("Product added.");
		} else {
			System.out.println("Product already in system.");
		}
	}

	/**
	 * Checkout member at the counter. Gets a list of product and quantity to order
	 */
	private void checkoutMember() {
		// TODO : implement this
		LinkedList<String> display = new LinkedList<String>();
		Request request = new Request();
		System.out.println("Partially implemented.");
		boolean done = false;
		System.out.println("Member Checkout:");
		int memberId = inputInteger("Enter Member ID:");
		request.setMemberId(memberId);
		Result result = store.getMember(request);
		if (result.getSuccess()) {
			do {
				int count = 0;
				String idInput = inputString("Enter Product ID (0 to finish):");
				if (idInput.charAt(0) != '0') {
					request.setProductId(idInput);
					result = store.getProduct(request);
					if (result.getSuccess()) {
						count = inputInteger("Enter amount : ");
						if ((count > 0) && (count < result.getProductStock())) {
							// Add the product, and count to a transaction list.
							request.setQuantity(count);
							display.add(store.checkoutMember(request));
						} else if (count <= 0) {
							System.out.println("Must order more than 0.");
						} else {
							System.out.println("Order exceeds stock.");
						}
					} else {
						System.out.println("Item not found.");
					}
				} else {
					done = true;
				}
			} while (!done);
			Iterator<String> list = display.iterator();
			System.out.println("Product\tQty\tPrice\tTotal");
			while (list.hasNext()) {
				System.out.println(list.next());
			}
		}
		LinkedList<String> orders = store.checkForOrder();
		Iterator<String> list = orders.iterator();
		while (list.hasNext()) {
			System.out.println(list.next());
		}
	}

	/**
	 * not implemented - will process a received shipment to the store
	 */
	private void processShipment() {
		// TODO : implement this
		System.out.println("Not implemented.");
	}

	/**
	 * Changes the price of a product in the store Requires product id
	 */
	private void changeProductPrice() {
		Request request = new Request();
		System.out.println("Change product price.");
		String prodID = inputString("Enter product id : ");
		request.setProductId(prodID);
		Result result = store.changeProductPrice(request);
		if (result.getSuccess()) {
			System.out.println(result.getProductName() + " " + result.getProductPrice());
		} else {
			System.out.println("Operation failed, product not found.");
		}
	}

	/**
	 * Searches for all product that matches a string given.
	 */
	private void getProductInfo() {
		System.out.println("Get product info.");
		String searchString = inputString("Enter search string : ");
		SafeProductIterator iterator = store.getProductList();
		System.out.println("Product that matches:");
		while (iterator.hasNext()) {
			Result curResult = iterator.next();
			if (curResult.getProductName().contains(searchString)) {
				System.out.println(
						curResult.getProductName() + " " + curResult.getProductId() + " " + curResult.getProductPrice()
								+ " " + curResult.getProductStock() + " " + curResult.getProductReOrderLevel());
			}
		}
	}

	/**
	 * Searches the store for all members with a matching Name
	 */
	private void getMemberInfo() {
		System.out.println("Get member info.");
		String searchString = inputString("Enter search string : ");
		SafeMemberIterator iterator = store.getMemberList();
		System.out.println("Members that matches");
		while (iterator.hasNext()) {
			Result curResult = iterator.next();
			if (curResult.getMemberName().contains(searchString)) {
				System.out.println(curResult.getMemberName() + " " + curResult.getMemberAddress() + " "
						+ curResult.getMemberFees() + " " + curResult.getMemberId());
			}
		}
	}

	/**
	 * Not implemented - should print all transactions from Members
	 */
	private boolean printTransations() {
		System.out.println("Print transactions.");
		Request request = new Request();
		System.out.println("Please enter a member ID and the dates to look between.");
		int memberId = inputInteger("Enter the member ID:");
		request.setMemberId(memberId);
		try {
			String dateString1 = inputString("Enter the start date in format mm/dd/yyyy");
			Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(dateString1);
			String dateString2 = inputString("Enter the end date in format mm/dd/yyyy");
			Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(dateString2);
			if (date1.compareTo(date2) > 0) {
				System.out.println("Invalid dates entered.");
				return false;
			}
			request.setStartDate(date1);
			request.setEndDate(date2);
		} catch (ParseException e) {
			System.out.println("Please enter valid dates.");
			return false;
		}
		LinkedList<String> read = store.printTransactions(request);
		Iterator<String> list = read.iterator();
		String firstString = list.next();
		if (firstString.equals("Error invalid member ID!")) {
			System.out.println(firstString);
			return false;
		}
		if (firstString == null) {
			System.out.println("There are no transactions.");
			return true;
		}
		System.out.println("Product\tQty\tPrice\tTotal");
		System.out.println(firstString);
		while (list.hasNext()) {
			System.out.println(list.next());
		}
		return true;
	}

	/**
	 * lists all orders the store has not received yet.
	 */
	private void listOutstandingOrders() {
		SafeOrderIterator iterator = store.getOrderList();
		System.out.println("Order List:");
		while (iterator.hasNext()) {
			Result currentResult = iterator.next();
			System.out.println(currentResult.getOrderId() + " " + currentResult.getProductName() + " "
					+ currentResult.getOrderCreatedDate() + " " + currentResult.getOrderQuantity());
		}
		System.out.println("Not implemented");

	}

	/**
	 * gets a list of all members of the store memberList
	 */
	private void getMemberList() {
		SafeMemberIterator iterator = store.getMemberList();
		System.out.println("Member List:");
		while (iterator.hasNext()) {
			Result curResult = iterator.next();
			System.out.println(curResult.getMemberName() + " " + curResult.getMemberJoinDate() + " "
					+ curResult.getMemberAddress() + " " + curResult.getMemberPhoneNumber());
		}
	}

	/**
	 * gets a list of all product in the store catalog
	 */
	private void getProductList() {
		SafeProductIterator iterator = store.getProductList();
		System.out.println("Product List:");
		while (iterator.hasNext()) {
			Result curResult = iterator.next();
			System.out.println(curResult.getProductName() + " " + curResult.getProductId() + " "
					+ curResult.getProductStock() + " " + curResult.getProductReOrderLevel());
		}
	}

	/**
	 * Displays a help screen for the store UserInterface
	 */
	public void help() {
		System.out.println("Interface Help - ");
		System.out.println(EXITUI + "\t:Exit\n");
		System.out.println(ADDMEMBER + "\t:Add Member");
		System.out.println(REMOVEMEMBER + "\t:Remove Member");
		System.out.println(ADDPRODUCT + "\t:Add Product");
		System.out.println(CHECKOUT + "\t:Check Out Member Items");
		System.out.println(PROCESSSHIPMENT + "\t:Process Shipment");
		System.out.println(CHANGEPRICE + "\t:Change Product Price");
		System.out.println(GETPRODUCTINFO + "\t:Get Product Info");
		System.out.println(GETMEMBERINFO + "\t:Get Member Info");
		System.out.println(PRINTTRANSACTIONS + "\t:Print Transations");
		System.out.println(LISTOUTSTANDINGORDERS + "\t:List Outstanding Orders");
		System.out.println(LISTMEMBERS + "\t:List All Members");
		System.out.println(LISTPRODUCT + "\t:List All Product");
		System.out.println(SAVE + "\t:Save Store Data");
		System.out.println(HELP + "\t:Print Help\n");
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			int value = inputInteger("Enter command:" + HELP + " for help");
			if (value >= EXITUI && value <= HELP) {
				return value;
			}
		} while (true);
	}

	/**
	 * the input loop for the store, will run until clerk exits
	 */
	public void inputLoop() {
		int command;
		help();
		while ((command = getCommand()) != EXITUI) {
			switch (command) {
			case ADDMEMBER:
				enrollMember();
				break;
			case REMOVEMEMBER:
				removeMember();
				break;
			case ADDPRODUCT:
				addProduct();
				break;
			case CHECKOUT:
				checkoutMember();
				break;
			case PROCESSSHIPMENT:
				processShipment();
				break;
			case CHANGEPRICE:
				changeProductPrice();
				break;
			case GETPRODUCTINFO:
				getProductInfo();
				break;
			case GETMEMBERINFO:
				getMemberInfo();
				break;
			case PRINTTRANSACTIONS:
				printTransations();
				break;
			case LISTOUTSTANDINGORDERS:
				listOutstandingOrders();
				break;
			case LISTMEMBERS:
				getMemberList();
				break;
			case LISTPRODUCT:
				getProductList();
				break;
			case SAVE:
				saveStore();
				break;
			case HELP:
				help();
				break;
			}
		}
		System.out.println("Goodbye.");
	}

	/**
	 * program entry point, just calls the input loop
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().inputLoop();
	}
}
