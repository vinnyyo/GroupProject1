package business.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private Product orderItem;
	private int quantity;
	private int orderId;
	private Date createdDate;

	public OrderItem(Product orderItem, int quantity, int orderId) {
		Date date = new Date(System.currentTimeMillis());
		this.orderItem = orderItem;
		this.quantity = quantity;
		this.orderId = orderId;
		this.createdDate = date;
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
}