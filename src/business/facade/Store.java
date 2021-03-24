package business.facade;

import business.entities.Member;
import business.entities.Product;
import business.entities.iterators.SafeMemberIterator;
import business.entities.iterators.SafeProductIterator;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Store implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Store store;
	private MemberList memberList = new MemberList();
	private Catalog catalog = new Catalog();
	
	/**
	 * Collection class to hold products of the store
	 *
	 */
	private class Catalog implements Serializable,Iterable<Product> {
        private static final long serialVersionUID = 1L;
		private List<Product> products = new LinkedList<Product>();

		public boolean addProduct(Product product) {
			if (searchName(product.getName()) != null) {
				return products.add(product);
			}
			return false;
		}

		public Product searchName(String productName) {
			Iterator<Product> productIterator = products.iterator();
			while (productIterator.hasNext()) {
				Product curProduct = productIterator.next();
				if (curProduct.matchesName(productName)) {
					return curProduct;
				}
			}
			return null;
		}
		
		public Product searchID(String productId) {
			Iterator<Product> productIterator = products.iterator();
			while (productIterator.hasNext()) {
				Product curProduct = productIterator.next();
				if (curProduct.matchesID(productId)) {
					return curProduct;
				}
			}
			return null;
		}
		
		@Override
		public Iterator<Product> iterator() {
			return products.iterator();
		}
	}
	
	/**
	 * Collection class to hold members of the store
	 *
	 */
	private class MemberList implements Serializable,Iterable<Member> {
        private static final long serialVersionUID = 1L;
		private List<Member> members = new LinkedList<Member>();

		public boolean addMember(Member member) {
			return members.add(member);
		}
		
		public boolean removeMember(int memberID) {
			Member member = search(memberID);
			if (member != null) {
				members.remove(member);
				return true;
			}
			return false;
		}

		public Member search(int memberID) {
			Iterator<Member> memberIterator = members.iterator();
			while (memberIterator.hasNext()) {
				Member curMember = memberIterator.next();
				if (curMember.matchesID(memberID)) {
					return curMember;
				}
			}
			return null;
		}
		
		@Override
		public Iterator<Member> iterator() {
			return members.iterator();
		}
	}


	/**
	 * Private constructor for singleton paradigm
	 */
	private Store() {
	}

	/**
	 * To get an instance of the singleton
	 * @return singleton instance
	 */
	public static Store instance() {
		if (store == null) {
			return store = new Store();
		} else {
			return store;
		}
	}
	
	/**
	 * Enrolls a new member to the store
	 * @param request Member information
	 * @return either a Result.OPERATION_SUCCESSFUL with member info or 
	 * Result.OPERATION_FAILURE if there was an error
	 */
	public Result enrollMember(Request request) {
		Result newResult = new Result();
		Member newMember = new Member(request.getMemberName(), 
									  request.getMemberPhoneNumber(),
									  request.getMemberJoinDate(),
									  request.getMemberFees());
		
		if (memberList.addMember(newMember)) {
			newResult.setStatus(Result.OPERATION_SUCCESSFUL);
			newResult.setMember(newMember);
		} else {
			newResult.setStatus(Result.OPERATION_FAILURE);
		}
		return newResult;
	}

	/**
	 * Removes a member for the MemberList
	 * @param request Member information
	 * @return either a Result.OPERATION_SUCCESSFUL with member info or 
	 * Result.OPERATION_FAILURE if there was an error
	 */
	public Result removeMember(Request request) {
		Result newResult = new Result();
		Member member = memberList.search(request.getMemberId());
		if (member != null) {
			newResult.setMember(member);
			newResult.setStatus(Result.OPERATION_SUCCESSFUL);
			memberList.removeMember(member.getId());
		} else {
			newResult.setStatus(Result.OPERATION_FAILURE);
		}
		return newResult;
	}
	
	/**
	 * Adds a product to the catalog and orders 2x restock level
	 * @param request
	 * @return either a Result.OPERATION_SUCCESSFUL with product info or 
	 * Result.OPERATION_FAILURE if there was an error
	 */
	public Result addProduct(Request request) {
		Result newResult = new Result();
		//String name, String id, int stock, double price, int reOrderLevel
		Product product = new Product(request.getProductName(),
									  request.getProductId(),
									  request.getProductStock(),
									  request.getProductPrice(),
									  request.getProductReOrderLevel());
		if (catalog.addProduct(product)) {
			newResult.setStatus(Result.OPERATION_SUCCESSFUL);
			newResult.setProduct(product);
			// TODO : add code to order 2 x product.getProductReOrderLevel()
		} else {
			newResult.setStatus(Result.OPERATION_FAILURE);
		}
		
		return newResult;
	}
	
	/**
	 * Changes the price of a product identified by id
	 * @param request
	 * @return either a Result.OPERATION_SUCCESSFUL with product info or 
	 * Result.OPERATION_FAILURE if there was an error
	 */
	public Result changeProductPrice(Request request) {
		Result newResult = new Result();
		Product product = catalog.searchID(request.getProductId());
		if (product != null) {
			product.setPrice(request.getNewProductPrice());
			newResult.setStatus(Result.OPERATION_SUCCESSFUL);
			newResult.setProduct(product);
		} else {
			newResult.setStatus(Result.OPERATION_FAILURE);
		}
		
		return newResult;
	}

    /**
     * Returns an iterator to Member info. The Iterator returned is a safe one, in
     * the sense that only copies of the Member fields are assembled into the
     * objects returned via next().
     * 
     * @return an Iterator to Result - only the Member fields are valid.
     */
    public Iterator<Result> getMembers() {
        return new SafeMemberIterator(memberList.iterator());
    }
	
    /**
     * Returns an iterator to Product info. The Iterator returned is a safe one, in
     * the sense that only copies of the Product fields are assembled into the
     * objects returned via next().
     * 
     * @return an Iterator to Result - only the Product fields are valid.
     */
    public Iterator<Result> getProduct() {
        return new SafeProductIterator(catalog.iterator());
    }

    /**
     * Retrieves store data from disk
     * 
     * @return a Store object
     */
    public static Store retrieve() {
        try {
            FileInputStream file = new FileInputStream("StoreData");
            ObjectInputStream input = new ObjectInputStream(file);
            store = (Store) input.readObject();
            Member.retrieve(input);
            return store;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }

    /**
     * Saves the Store object
     * 
     * @return true iff the data could be saved
     */
    public static boolean save() {
        try {
            FileOutputStream file = new FileOutputStream("StoreData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(store);
            Member.save(output);
            file.close();
            return true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
}
