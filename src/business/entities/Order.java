package business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An Order object that represents an order made from the store. The fields
 * include a product object, the quantity ordered, the order ID and the date the
 * order was placed.
 * 
 * @author Vincent Peterson,
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private Product orderItem;
	private int quantity;
	private int orderId;
	private Date createdDate;

	private static int idCounter = 1;

	public Order(Product orderItem, int quantity) {
		Date date = new Date(System.currentTimeMillis());
		this.orderItem = orderItem;
		this.quantity = quantity;
		this.orderId = idCounter;
		this.createdDate = date;
		idCounter++;
	}

	/**
	 * @return the orderItemId
	 */
	public Product getOrderItem() {
		return orderItem;
	}

	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItem(Product orderItem) {
		this.orderItem = orderItem;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * gets date of creation
	 * 
	 * @return date
	 */
	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(createdDate);
	}

	/**
	 * adds an order for the item
	 * 
	 * @return true
	 */
	public boolean addOrder() {
		orderItem.setStock(orderItem.getStock() + quantity);
		return true;
	}

	/**
	 * checks to see if the order matches the id
	 * 
	 * @param id id to compare
	 * @return true if they match
	 */
	public boolean matchesId(int id) {
		return orderId == id;
	}

	/**
	 * returns a formatted string for the order
	 */
	public String toString() {
		return "ID: " + orderId + "\t" + orderItem.getName() + "\t" + this.getDate() + "\tAmount: " + quantity;
	}

	/**
	 * saves the order id to file
	 * 
	 * @param output file to save it to
	 * @throws IOException file error
	 */
	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	/**
	 * reads the order id from a file
	 * 
	 * @param input file to read from
	 * @throws IOException            file error
	 * @throws ClassNotFoundException
	 */
	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}
}