import java.io.Serializable;

/**
 * An object representing a product in a grocery store. Has fields such as name,
 * id, stock, price and reOrderLevel.
 * 
 * @author Vincent Peterson, Michael Olson
 *
 */
public class Product implements Serializable {

	private String name;
	private String id;
	private int stock;
	private double price;
	private int reOrderLevel;

	public Product(String name, String id, int stock, double price, int reOrderLevel) {
		this.name = name;
		this.id = id;
		this.stock = stock;
		this.price = price;
		this.reOrderLevel = reOrderLevel;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
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
	 * @return the reOrderLevel
	 */
	public int getReOrderLevel() {
		return reOrderLevel;
	}

	/**
	 * @param reOrderLevel the reOrderLevel to set
	 */
	public void setReOrderLevel(int reOrderLevel) {
		this.reOrderLevel = reOrderLevel;
	}

}
