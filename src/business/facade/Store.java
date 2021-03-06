package business.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import business.entities.Member;
import business.entities.Order;
import business.entities.Product;
import business.entities.Transaction;
import business.entities.iterators.SafeMemberIterator;
import business.entities.iterators.SafeOrderIterator;
import business.entities.iterators.SafeProductIterator;

/**
 * The facade representing the store and all its inventories. It is a singleton
 * and has collection subclasses for MemberList, Catalog and PendingOrders. This
 * class contains methods to safely utilize the store.
 * 
 * @author Micheal Olson, Vincent Peterson,
 *
 */
public class Store implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Store store;
	private MemberList memberList = new MemberList();
	private Catalog catalog = new Catalog();
	private PendingOrders orders = new PendingOrders();

	/**
	 * Collection class to hold products of the store
	 *
	 */
	private class Catalog implements Serializable, Iterable<Product> {
		private static final long serialVersionUID = 1L;
		private List<Product> products = new LinkedList<Product>();

		/**
		 * Adds product to the collection class Catalog
		 * 
		 * @param product product to add
		 * @return success of operation
		 */
		public boolean addProduct(Product product) {
			if (searchName(product.getName()) == null) {
				return products.add(product);
			}
			return false;
		}

		/**
		 * Searches for a product by name
		 * 
		 * @param productName name of product to search for
		 * @return product if found, null if not
		 */
		public Product searchName(String productName) {
			Iterator<Product> productIterator = products.iterator();
			while (productIterator.hasNext()) {
				Product curProduct = productIterator.next();
				if (curProduct.matchesName(productName)) {
					return curProduct;
				}
			}
			return null;
		}

		/**
		 * Searches for product by id
		 * 
		 * @param productId id to search for
		 * @return product if found, null if not
		 */
		public Product searchID(String productId) {
			Iterator<Product> productIterator = products.iterator();
			while (productIterator.hasNext()) {
				Product curProduct = productIterator.next();
				if (curProduct.matchesID(productId)) {
					return curProduct;
				}
			}
			return null;
		}

		/**
		 * iterator to iterate through the Catalog
		 */
		@Override
		public Iterator<Product> iterator() {
			return products.iterator();
		}
	}

	/**
	 * Collection class to hold members of the store
	 *
	 */
	private class MemberList implements Serializable, Iterable<Member> {
		private static final long serialVersionUID = 1L;
		private List<Member> members = new LinkedList<Member>();

		/**
		 * adds a member to the collection
		 * 
		 * @param member member to add
		 * @return success of the operation
		 */
		public boolean addMember(Member member) {
			return members.add(member);
		}

		/**
		 * removes a member from the collection
		 * 
		 * @param memberID id of member to remove
		 * @return operation success
		 */
		public boolean removeMember(int memberID) {
			Member member = search(memberID);
			if (member != null) {
				members.remove(member);
				return true;
			}
			return false;
		}

		/**
		 * searches for a member by id
		 * 
		 * @param memberID id of member to search for
		 * @return member if successful, null if not
		 */
		public Member search(int memberID) {
			Iterator<Member> memberIterator = members.iterator();
			while (memberIterator.hasNext()) {
				Member curMember = memberIterator.next();
				if (curMember.matchesID(memberID)) {
					return curMember;
				}
			}
			return null;
		}

		/**
		 * iterator for the MemberList
		 */
		@Override
		public Iterator<Member> iterator() {
			return members.iterator();
		}
	}

	/**
	 * Collection class to hold orders of the store
	 *
	 */
	private class PendingOrders implements Serializable, Iterable<Order> {

		private static final long serialVersionUID = 1L;
		private List<Order> orderItems = new LinkedList<Order>();

		/**
		 * adds an order to the collection
		 * 
		 * @param order to add
		 */
		public void addOrderItem(Order order) {
			orderItems.add(order);
		}

		/**
		 * searches for a order by id
		 * 
		 * @param orderId id of member to search for
		 * @return order if successful, null if not
		 */
		public Order searchOrderId(int orderId) {
			Iterator<Order> orderIterator = orderItems.iterator();
			while (orderIterator.hasNext()) {
				Order cursor = orderIterator.next();
				if (cursor.matchesId(orderId)) {
					return cursor;
				}
			}
			return null;
		}

		/**
		 * removes a order from the collection
		 * 
		 * @param orderId id of order to remove
		 * @return the order that was deleted or null if it was not found
		 */
		public Order deleteOrderItem(int orderId) {
			Order item = this.searchOrderId(orderId);
			if (item == null) {
				return null;
			}
			orderItems.remove(item);
			return item;
		}

		/**
		 * Returns a boolean value representing if there is a order for the specified
		 * product.
		 * 
		 * @param product - the product to look for
		 * @return true if the product is in the list of orders, false if it is not.
		 */
		public boolean contains(Product product) {
			Iterator<Order> orderIterator = orderItems.iterator();
			while (orderIterator.hasNext()) {
				Order cursor = orderIterator.next();
				if (cursor.getOrderItem().getId().equals(product.getId())) {
					return true;
				}
			}
			return false;
		}

		/**
		 * iterator for the PendingOrders
		 */
		@Override
		public Iterator<Order> iterator() {
			return orderItems.iterator();
		}

		public String toString() {
			return orderItems.toString();
		}
	}

	/**
	 * Private constructor for singleton paradigm
	 */
	private Store() {
	}

	/**
	 * To get an instance of the singleton
	 * 
	 * @return singleton instance
	 */
	public static Store instance() {
		if (store == null) {
			return store = new Store();
		} else {
			return store;
		}
	}

	/**
	 * Enrolls a new member to the store
	 * 
	 * @param request Member information
	 * @return either a Result.OPERATION_SUCCESSFUL with member info or
	 *         Result.OPERATION_FAILURE if there was an error
	 */
	public Result enrollMember(Request request) {
		Result newResult = new Result();
		Member newMember = new Member(request.getMemberName(), request.getMemberAddress(),
				request.getMemberPhoneNumber(), request.getMemberJoinDate(), request.getMemberFees());

		if (memberList.addMember(newMember)) {
			newResult.setSuccess(Result.OPERATION_SUCCESSFUL);
			newResult.setStatus(Result.MEMBER_ADDED);
			newResult.setMember(newMember);
		} else {
			newResult.setSuccess(Result.OPERATION_FAILURE);
			newResult.setStatus(Result.MEMBER_FOUND);
		}
		return newResult;
	}

	/**
	 * Removes a member for the MemberList
	 * 
	 * @param request Member information
	 * @return either a Result.OPERATION_SUCCESSFUL with member info or
	 *         Result.OPERATION_FAILURE if there was an error
	 */
	public Result removeMember(Request request) {
		Result newResult = new Result();
		Member member = memberList.search(request.getMemberId());
		if (member != null) {
			newResult.setMember(member);
			newResult.setSuccess(Result.OPERATION_SUCCESSFUL);
			newResult.setStatus(Result.MEMBER_REMOVED);
			memberList.removeMember(member.getId());
		} else {
			newResult.setSuccess(Result.OPERATION_FAILURE);
			newResult.setStatus(Result.MEMBER_NOT_FOUND);
		}
		return newResult;
	}

	/**
	 * Adds a product to the catalog and orders 2x restock level
	 * 
	 * @param request
	 * @return either a Result.OPERATION_SUCCESSFUL with product info or
	 *         Result.OPERATION_FAILURE if there was an error
	 */
	public Result addProduct(Request request) {
		Result newResult = new Result();
		// String name, String id, int stock, double price, int reOrderLevel
		Product product = new Product(request.getProductName(), request.getProductId(), request.getProductStock(),
				request.getProductPrice(), request.getProductReOrderLevel());
		if (catalog.addProduct(product)) {
			newResult.setSuccess(Result.OPERATION_SUCCESSFUL);
			newResult.setStatus(Result.PRODUCT_ADDED);
			newResult.setProduct(product);
			orders.addOrderItem(new Order(product, product.getReOrderLevel() * 2));
		} else {
			newResult.setSuccess(Result.OPERATION_FAILURE);
			newResult.setStatus(Result.PRODUCT_FOUND);
		}

		return newResult;
	}

	/**
	 * This method takes in a request with the product id, member id, and amount
	 * purchased then creates a transaction object and adds the transaction to the
	 * members record.
	 * 
	 * @param request - a request with the product id, member id, and amount
	 *                purchased
	 * @return a result with the transaction string and total price.
	 */
	public Result checkoutMember(Request request) {
		Product item = catalog.searchID(request.getProductId());
		Transaction purchase = new Transaction(item, request.getQuantity());
		Member customer = memberList.search(request.getMemberId());
		customer.addTransaction(purchase);
		item.setStock(item.getStock() - request.getQuantity());
		Result output = new Result();
		output.setMessage(purchase.toString());
		output.setProductPrice(purchase.getTotalPrice());
		return output;
	}

	/**
	 * This method takes in a product id and if the order level is higher than the
	 * stock level of that object and there is not already an order for that
	 * product, then creates an order for twice the reorder level.
	 * 
	 * @param request - the request containing the product id
	 * @return a result object containing null if no order was made else it returns
	 *         a message that the order was made.
	 */
	public Result checkForOrder(Request request) {
		Result output = new Result();
		Product item = catalog.searchID(request.getProductId());
		if (item.getStock() <= item.getReOrderLevel() && !orders.contains(item)) {
			String message = "";
			Order order = new Order(item, item.getReOrderLevel() * 2);
			orders.addOrderItem(order);
			message += item.getName() + " has been ordered.\n";
			message += order.toString();
			output.setMessage(message);
		} else {
			output.setMessage(null);
		}
		return output;
	}

	/**
	 * This method takes an Order object and adds the quantity ordered to the
	 * Product object's stock. Then deletes the order.
	 * 
	 * @param request - the request with the orderId
	 * @return a Result with the product and updated stock.
	 */
	public Result processShipment(Request request) {
		Result output = new Result();
		Order order = orders.deleteOrderItem(request.getOrderId());
		order.getOrderItem().setStock(order.getOrderItem().getStock() + order.getQuantity());
		output.setProduct(order.getOrderItem());
		output.setSuccess(true);
		return output;
	}

	/**
	 * Changes the price of a product identified by id
	 * 
	 * @param request
	 * @return either a Result.OPERATION_SUCCESSFUL with product info or
	 *         Result.OPERATION_FAILURE if there was an error
	 */
	public Result changeProductPrice(Request request) {
		Result newResult = new Result();
		Product product = catalog.searchID(request.getProductId());
		if (product != null) {
			product.setPrice(request.getNewProductPrice());
			newResult.setSuccess(Result.OPERATION_SUCCESSFUL);
			newResult.setStatus(Result.PRODUCT_FOUND);
			newResult.setProduct(product);
		} else {
			newResult.setSuccess(Result.OPERATION_FAILURE);
			newResult.setStatus(Result.PRODUCT_NOT_FOUND);
		}

		return newResult;
	}

	/**
	 * gets a safe iterator for the products in the catalog
	 * 
	 * @return safe iterator for product
	 */
	public SafeProductIterator getProductList() {
		SafeProductIterator resultIterator = new SafeProductIterator(catalog.iterator());

		return resultIterator;
	}

	/**
	 * gets a safe iterator for the members in MemberList
	 * 
	 * @return safe iterator for members
	 */
	public SafeMemberIterator getMemberList() {
		SafeMemberIterator resultIterator = new SafeMemberIterator(memberList.iterator());

		return resultIterator;
	}

	/**
	 * gets a safe iterator for the orders in PendingOrders
	 * 
	 * @return safe iterator for orders
	 */
	public SafeOrderIterator getOrderList() {
		SafeOrderIterator resultIterator = new SafeOrderIterator(orders.iterator());

		return resultIterator;
	}

	/**
	 * gets information about a product by id from the catalog
	 * 
	 * @param request request from UI
	 * @return result of the request
	 */
	public Result getProduct(Request request) {
		Result newResult = new Result();
		Product product = catalog.searchID(request.getProductId());
		if (product != null) {
			newResult.setProduct(product);
			newResult.setSuccess(Result.OPERATION_SUCCESSFUL);
			newResult.setStatus(Result.PRODUCT_FOUND);
		} else {
			newResult.setSuccess(Result.OPERATION_FAILURE);
			newResult.setStatus(Result.PRODUCT_NOT_FOUND);
		}
		return newResult;
	}

	/**
	 * gets information about a member by id from the members list
	 * 
	 * @param request request from UI
	 * @return result of the request
	 */
	public Result getMember(Request request) {
		Result newResult = new Result();
		Member member = memberList.search(request.getMemberId());
		if (member != null) {
			newResult.setMember(member);
			newResult.setSuccess(Result.OPERATION_SUCCESSFUL);
			newResult.setStatus(Result.PRODUCT_FOUND);
		} else {
			newResult.setSuccess(Result.OPERATION_FAILURE);
			newResult.setStatus(Result.PRODUCT_NOT_FOUND);
		}
		return newResult;
	}

	/**
	 * gets information about a order by id from the orders list
	 * 
	 * @param request request from UI
	 * @return result of the request
	 */
	public Result getOrder(Request request) {
		Result newResult = new Result();
		Order order = orders.searchOrderId(request.getOrderId());
		if (order != null) {
			newResult.setOrder(order);
			newResult.setSuccess(Result.OPERATION_SUCCESSFUL);
			newResult.setStatus(Result.PRODUCT_FOUND);
		} else {
			newResult.setSuccess(Result.OPERATION_FAILURE);
			newResult.setStatus(Result.PRODUCT_NOT_FOUND);
		}
		return newResult;
	}

	/**
	 * Takes in a member id and two dates and returns a list of Results containing
	 * the string message of all transactions made by that member between the two
	 * given dates.
	 * 
	 * @param request - containing the memberId and two Date objects.
	 * @return a Linked List of Result objects containing the string of the
	 *         transactions.
	 */
	public LinkedList<Result> printTransactions(Request request) {
		LinkedList<Result> output = new LinkedList<Result>();
		Member customer = memberList.search(request.getMemberId());
		if (customer == null) {
			Result result1 = new Result();
			result1.setMessage("Error invalid member ID!");
			output.add(result1);
			return output;
		}
		LinkedList<Transaction> transactions = customer.getTransactions();
		Iterator<Transaction> list = transactions.iterator();
		while (list.hasNext()) {
			Transaction purchase = list.next();
			if (purchase.compareTo(request.getStartDate()) >= 0 && purchase.compareTo(request.getEndDate()) <= 0) {
				Result result2 = new Result();
				result2.setMessage(purchase.toString());
				output.add(result2);
			}
		}
		return output;

	}

	/**
	 * Retrieves store data from disk
	 * 
	 * @return a Store object
	 */
	public static Store retrieve() {
		try {
			FileInputStream file = new FileInputStream("StoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			store = (Store) input.readObject();
			Member.retrieve(input);
			Order.retrieve(input);
			return store;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Saves the Store object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("StoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(store);
			Member.save(output);
			Order.save(output);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
}
