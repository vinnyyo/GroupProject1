package business.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	public Product item;
	public int amountPurchased;
	public Date timePurchased;

	public Transaction(Product item, int amountPurchased) {
		Date date = new Date(System.currentTimeMillis());
		this.item = new Product(item.getName(), item.getId(), item.getStock(), item.getPrice(), item.getReOrderLevel());
		this.amountPurchased = amountPurchased;
		this.timePurchased = date;
	}

	/**
	 * @return the item
	 */
	public Product getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Product item) {
		this.item = item;
	}

	/**
	 * @return the amountPurchased
	 */
	public int getAmountPurchased() {
		return amountPurchased;
	}

	/**
	 * @param amountPurchased the amountPurchased to set
	 */
	public void setAmountPurchased(int amountPurchased) {
		this.amountPurchased = amountPurchased;
	}

	/**
	 * @return the timePurchased
	 */
	public Date getTimePurchased() {
		return timePurchased;
	}

	/**
	 * @param timePurchased the timePurchased to set
	 */
	public void setTimePurchased(Date timePurchased) {
		this.timePurchased = timePurchased;
	}

	/**
	 * @return the total price of purchase
	 */
	public double getTotalPrice() {
		return item.getPrice() * amountPurchased;
	}

	/**
	 * Nicely formatted string for the transaction
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		String output = item.getName() + "\t";
		if (item.getName().length() < 8) {
			output += "\t";
		}
		return output + amountPurchased + "\t$" + df.format(item.getPrice()) + "\t$" + df.format(getTotalPrice());
	}

	public int compareTo(Date date) {
		return timePurchased.compareTo(date);
	}

}
