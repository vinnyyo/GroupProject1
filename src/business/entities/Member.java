package business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id, an integer that uniquely identifies the Member object.
	 */
	private int id = 0;
	private String name;
	private String phoneNumber;
	private String address;
	private Date joinDate;
	private double fees;
	private LinkedList<Transaction> transactions;

	/**
	 * A static field (say, idCounter) to generate the value to be stored in id.
	 * Increment this field once within the constructor and store the value in id.
	 * Since there is exactly one instance of idCounter, every Point object will
	 * have a unique value stored in id.
	 */
	private static int idCounter = 1;

	public Member(String name, String address, String phoneNumber, Date joinDate, double fees) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.joinDate = joinDate;
		this.fees = fees;
		this.id = idCounter;
		idCounter++;
		transactions = new LinkedList<Transaction>();
	}

	/**
	 * @return the memberId
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the joinDate
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the fees
	 */
	public double getFees() {
		return fees;
	}

	public int getIdCounter() {
		return idCounter;
	}

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}

	public boolean matchesID(int id) {
		return (id == this.id);
	}

	public boolean addTransaction(Product item, int amount) {
		return transactions.add(new Transaction(item, amount));
	}

	public boolean addTransaction(Transaction transaction) {
		return transactions.add(transaction);
	}

	public LinkedList<Transaction> getTransactions() {
		return transactions;
	}

}
