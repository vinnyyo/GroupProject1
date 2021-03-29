package business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(createdDate);
	}

	public boolean addOrder() {
		orderItem.setStock(orderItem.getStock() + quantity);
		return true;
	}

	public boolean matchesId(int id) {
		return orderId == id;
	}

	public String toString() {
		return orderId + "\t" + orderItem.getName() + "\t" + this.getDate() + "\t" + quantity;
	}

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}
}