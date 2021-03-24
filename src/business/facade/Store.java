package business.facade;

import java.util.List;

import business.entities.Member;
import business.entities.OrderItem;
import business.entities.Product;

import java.util.LinkedList;
import java.io.Serializable;

public class Store implements Serializable {

	private static Store store;
	
	private List<Product> catalog = new LinkedList<Product>();
	private List<Member> memberList = new LinkedList<Member>();
	private List<OrderItem> orders = new LinkedList<OrderItem>();
	

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
