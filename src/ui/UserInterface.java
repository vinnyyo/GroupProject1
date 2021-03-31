package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
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

/**
 * This class implements the user interface for the Store application. The
 * commands are encoded as integers using static constants. The methods
 * contained in this class are for the maintenance of the store's data.
 * 
 * @author Vincent Peterson, Micheal Olson,
 */

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
	 * Converts a double into formatted price string
	 * 
	 * @param price the desired double to format
	 * @return String that is the formatted double
	 */
	private String formatPrice(Double price) {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		return "$" + df.format(price);
	}

	/**
	 * Converts a Date into formatted date string
	 * 
	 * @param date the desired date to format
	 * @return String that is the formatted date
	 */
	private String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
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
		request.setMemberJoinDate(new Date(System.currentTimeMillis()));
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
	 * Checkout member at the counter. Requests for the memberId, the ID's of the
	 * products and quantities that correlate with those. If the product runs low on
	 * stock the method checks if the item needs to be ordered.
	 */
	private void checkoutMember() {
		double total = 0.0;
		LinkedList<Result> display = new LinkedList<Result>();
		LinkedList<Request> products = new LinkedList<Request>();
		Request request = new Request();
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
						products.add(request);
						count = inputInteger("Enter amount : ");
						if ((count > 0) && (count <= result.getProductStock())) {
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
			Iterator<Result> list = display.iterator();
			System.out.println("Product\t\tQty\tPrice\tTotal");
			while (list.hasNext()) {
				result = list.next();
				total += result.getProductPrice();
				System.out.println(result.getMessage());
			}
			System.out.println("Order Total:\t\t" + formatPrice(total));
		}
		Iterator<Request> items = products.iterator();
		while (items.hasNext()) {
			Result status = store.checkForOrder(items.next());
			if (status.getMessage() != null) {
				System.out.println(status.getMessage());
			}
		}
	}

	/**
	 * Process a received shipment to the store. Enters the ordeId of the shipment,
	 * updates the product stock then deletes the order.
	 */
	private void processShipment() {
		System.out.println("Process Shipment.");
		Request request = new Request();
		Result result = new Result();
		boolean done = false;
		do {
			int idInput = inputInteger("Enter Order ID (0 to finish):");
			if (idInput != 0) {
				request.setOrderId(idInput);
				result = store.getOrder(request);
				if (result.getSuccess()) {
					result = store.processShipment(request);
					System.out.println("Order Processed.");
					System.out.println(
							result.getProductId() + " " + result.getProductName() + " " + result.getProductStock());
				} else {
					System.out.println("Order not found.");
				}
			} else {
				done = true;
			}
		} while (!done);
	}

	/**
	 * Changes the price of a product in the store Requires product id and the new
	 * price of the product.
	 */
	private void changeProductPrice() {
		Request request = new Request();
		System.out.println("Change product price.");
		String productID = inputString("Enter product id : ");
		double productPrice = inputDouble("Enter product price : ");
		request.setProductId(productID);
		request.setNewProductPrice(productPrice);
		Result result = store.changeProductPrice(request);
		if (result.getSuccess()) {
			System.out.println(result.getProductName() + " is now " + formatPrice(result.getProductPrice()));
		} else {
			System.out.println("Operation failed, product not found.");
		}
	}

	/**
	 * Searches for all product that matches a string given. And displays them to
	 * the user.
	 */
	private void getProductInfo() {
		System.out.println("Get product info.");
		String searchString = inputString("Enter search string : ");
		SafeProductIterator iterator = store.getProductList();
		System.out.println("Product that matches:");
		System.out.println("Product\t\tID\tPrice\tStock\tReorder Level");
		while (iterator.hasNext()) {
			Result curResult = iterator.next();
			if (curResult.getProductName().contains(searchString)) {
				System.out.println(curResult.getProductName() + "\t\t" + curResult.getProductId() + "\t"
						+ formatPrice(curResult.getProductPrice()) + "\t" + curResult.getProductStock() + "\t"
						+ curResult.getProductReOrderLevel());
			}
		}
	}

	/**
	 * Searches the store for all members with a matching Name. And displays them to
	 * the user.
	 */
	private void getMemberInfo() {
		System.out.println("Get member info.");
		String searchString = inputString("Enter search string : ");
		SafeMemberIterator iterator = store.getMemberList();
		System.out.println("Members that matches");
		System.out.println("Name\t\tAddress\t\tFee Paid\tID");
		while (iterator.hasNext()) {
			Result curResult = iterator.next();
			if (curResult.getMemberName().contains(searchString)) {
				System.out.println(curResult.getMemberName() + "\t" + curResult.getMemberAddress() + "\t"
						+ formatPrice(curResult.getMemberFees()) + "\t" + curResult.getMemberId());
			}
		}
	}

	/**
	 * Prints all transactions from a member between the requested dates.
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
			date2.setTime(date2.getTime() + 86400000);
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
		LinkedList<Result> read = store.printTransactions(request);
		Iterator<Result> list = read.iterator();
		String firstString = null;
		if (list.hasNext()) {
			firstString = list.next().getMessage();
			if (firstString.equals("Error invalid member ID!")) {
				System.out.println(firstString);
				return false;
			}
		}
		if (firstString == null) {
			System.out.println("There are no transactions.");
			return true;
		}
		System.out.println("Product\t\tQty\tPrice\tTotal");
		System.out.println(firstString);
		while (list.hasNext()) {
			System.out.println(list.next().getMessage());
		}
		return true;
	}

	/**
	 * lists all orders the store has not received yet.
	 */
	private void listOutstandingOrders() {
		SafeOrderIterator iterator = store.getOrderList();
		System.out.println("Order List:");
		System.out.println("ID\tProduct\t\tDate Placed\tAmount Ordered");
		while (iterator.hasNext()) {
			Result currentResult = iterator.next();
			System.out.println(currentResult.getOrderId() + "\t" + currentResult.getProductName() + "\t\t"
					+ formatDate(currentResult.getOrderCreatedDate()) + "\t" + currentResult.getOrderQuantity());
		}
	}

	/**
	 * Displays a list of all members of the store memberList
	 */
	private void getMemberList() {
		SafeMemberIterator iterator = store.getMemberList();
		System.out.println("Member List:");
		System.out.println("Name\t\tDate Joined\tAddress\t\tPhone");
		while (iterator.hasNext()) {
			Result curResult = iterator.next();
			System.out.println(curResult.getMemberName() + "\t" + formatDate(curResult.getMemberJoinDate()) + "\t"
					+ curResult.getMemberAddress() + "\t" + curResult.getMemberPhoneNumber());
		}
	}

	/**
	 * Displays a list of all products in the store catalog
	 */
	private void getProductList() {
		SafeProductIterator iterator = store.getProductList();
		System.out.println("Product List:");
		System.out.println("Product\t\tID\tStock\tPrice\tReorder Level");
		while (iterator.hasNext()) {
			Result curResult = iterator.next();
			System.out.println(curResult.getProductName() + "\t\t" + curResult.getProductId() + "\t"
					+ curResult.getProductStock() + "\t" + formatPrice(curResult.getProductPrice()) + "\t"
					+ curResult.getProductReOrderLevel());
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
