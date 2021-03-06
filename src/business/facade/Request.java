package business.facade;

import java.util.Date;

public class Request extends DataTransfer {

	private double newProductPrice;
	private Date startDate;
	private Date endDate;
	private int quantity;

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

	public Request() {
		super();
		newProductPrice = 0;
		startDate = new Date();
		endDate = new Date();
	}

	public void setNewProductPrice(double newPrice) {
		this.newProductPrice = newPrice;
	}

	public double getNewProductPrice() {
		return newProductPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
