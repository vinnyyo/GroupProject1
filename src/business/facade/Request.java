package business.facade;

public class Request extends DataTransfer {
	
	private double newProductPrice;
	
	public Request() {
		super();
		newProductPrice = 0;
	}

	public void setNewProductPrice(double newPrice) {
		this.newProductPrice = newPrice;
	}
	
	public double getNewProductPrice() {
		return newProductPrice;
	}
}
