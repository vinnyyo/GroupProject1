package business.entities;

import java.util.Date;

public class OrderItem {
	private Product orderItem;
	private Long productId;
	private int quantity;
	private double price;
	private Integer orderId;
	private Date createdDate;

	public OrderItem() {
		
	}
	public OrderItem(Product orderItem, Long productId, int quantity, double price, Integer orderId,
			Date createdDate) {
		super();
		this.orderItem = orderItem;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.orderId = orderId;
		this.createdDate = createdDate;
	}
	
	/**
	 * @return the orderItemId
	 */
	public Product getOrderItemId() {
		return orderItem;
	}

	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(Product orderItem) {
		this.orderItem = orderItem;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
}