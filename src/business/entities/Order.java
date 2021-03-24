package business.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

	private Integer orderId;
	private Integer memberId;
	private Date createdDate;
	private Double totalPrice;
	private List<OrderItem> orderItems;

	public Order() {
	}
	
	public Order(Integer orderId, Integer memberId, Date createdDate, Double totalPrice) {
		super();
		this.orderId = orderId;
		this.memberId = memberId;
		this.createdDate = createdDate;
		this.totalPrice = totalPrice;
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
	 * @return the memberId
	 */
	public Integer getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the orderItems
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}

