package business.facade;

import java.util.Date;

import business.entities.Member;
import business.entities.Product;

public abstract class DataTransfer {
	private int memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhoneNumber;
	private Date memberJoinDate;
	private double memberFees;
	private Integer orderId;
	private Date orderCreatedDate;
	private double orderTotalPrice;
	private String productName;
	private String productId;
	private int productStock;
	private double productPrice;
	private int productReOrderLevel;
	
	public DataTransfer() {
		memberId = 0;
		memberName = "none";
		memberPhoneNumber = "none";
		memberJoinDate = new Date();
		memberFees = 0;
		orderId = 0;
		orderCreatedDate = new Date();
		orderTotalPrice = 0.0;
		productName = "none";
		productId = "none";
		productStock = 0;
		productPrice = 0;
		productReOrderLevel = 0;
	}
	
	public void setMember(Member member) {
		this.memberFees = member.getFees();
		this.memberId = member.getId();
		this.memberJoinDate = member.getJoinDate();
		this.memberName = member.getName();
		this.memberPhoneNumber = member.getPhoneNumber();
	}
	
	public void setProduct(Product product) {
		this.productId = product.getId();
		this.productName = product.getName();
		this.productPrice = product.getPrice();
		this.productReOrderLevel = product.getReOrderLevel();
		this.productStock = product.getStock();
	}
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public void setMemberAddress(String address) {
		this.memberAddress = address;
	}
	
	public String getMemberAddress() {
		return this.memberAddress;
	}
	
	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}
	
	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}
	
	public Date getMemberJoinDate() {
		return memberJoinDate;
	}
	
	public void setMemberJoinDate(Date memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}
	
	public double getMemberFees() {
		return memberFees;
	}
	
	public void setMemberFees(double memberFees) {
		this.memberFees = memberFees;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Date getOrderCreatedDate() {
		return orderCreatedDate;
	}
	
	public void setOrderCreatedDate(Date orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}
	
	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}
	
	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public int getProductStock() {
		return productStock;
	}
	
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	
	public double getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	public int getProductReOrderLevel() {
		return productReOrderLevel;
	}
	
	public void setProductReOrderLevel(int productReOrderLevel) {
		this.productReOrderLevel = productReOrderLevel;
	}	

}
