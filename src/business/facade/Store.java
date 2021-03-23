package business.facade;

import java.io.Serializable;

public class Store implements Serializable {

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
