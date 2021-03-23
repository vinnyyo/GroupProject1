package business.facade;

public class Store {

	private static Store store;

	private Store() {
	}

	public static Store instance() {
		if (store == null) {
			return store = new Store();
		} else {
			return store;
		}
	}
}
