package business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {

	/**
	 * id, an integer that uniquely identifies the Member object.
	 */
	private int id = 0;
	private String name;
	private String phoneNumber;
	private Date joinDate;
	private long fees;

	/**
	 * A static field (say, idCounter) to generate the value to be stored in id.
	 * Increment this field once within the constructor and store the value in id.
	 * Since there is exactly one instance of idCounter, every Point object will
	 * have a unique value stored in id.
	 */
	private static int idCounter = 1;

	public Member(String name, String phoneNumber, Date joinDate,
			long fees) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.joinDate = joinDate;
		this.fees = fees;
		this.id = idCounter;
		idCounter++;
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
	 * @return the fees
	 */
	public long getFees() {
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
}