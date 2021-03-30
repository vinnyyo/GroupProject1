package business.entities;
import java.io.Serializable;

/**
 * An object representing a product in a grocery store. Has fields such as name,
 * id, stock, price and reOrderLevel.
 * 
 * @author Vincent Peterson, Michael Olson
 *
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

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
	
	/**
	 * Check to see if the name matches 
	 * @param name name to compare
	 * @return true if successful
	 */
	public boolean matchesName(String name) {
		return (name.equalsIgnoreCase(this.name));
	}
	
	/**
	 * Checks to see if an id matches
	 * @param id id to compare
	 * @return true if they match
	 */
	public boolean matchesID(String id) {
		return (id.equalsIgnoreCase(this.id));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
