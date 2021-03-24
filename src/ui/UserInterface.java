package ui;

import business.facade.Store;

public class UserInterface {

	private static UserInterface userInterface;
	private static Store store;
	private static int EXITUI = 0;
	private static int ADDMEMBER = 1;
	private static int REMOVEMEMBER = 2;
	private static int ADDPRODUCT = 3;
	private static int CHECKOUT = 4;
	private static int PROCESSSHIPMENT = 5;
	private static int CHANGEPRICE = 6;
	private static int GETPRODUCTINFO = 7;
	private static int GETMEMBERINFO = 8;
	private static int PRINTTRANSACTIONS = 9;
	private static int LISTOUTSTANDINGORDERS = 10;
	private static int LISTMEMBERS = 11;
	private static int LISTPRODUCT = 12;
	private static int SAVE = 13;
	private static int HELP = 14;

	private UserInterface() {
		store = Store.instance();
	}
	
	public void help() {
		System.out.println("Interface Help - ");
		System.out.println(EXITUI + "\t:Exit\n");
		System.out.println(ADDMEMBER + "\t:Add Member");
		System.out.println(REMOVEMEMBER + "\t:Remove Member");
		System.out.println(ADDPRODUCT + "\t:Add Product");
		System.out.println(CHECKOUT + "\t:Check Out Member Items");
		System.out.println(PROCESSSHIPMENT + "\t:Process Shipment");
		System.out.println(CHANGEPRICE + "\t:Change Product Price");
		System.out.println(GETPRODUCTINFO + "\t:Get Product Info");
		System.out.println(GETMEMBERINFO + "\t:Get Member Info");
		System.out.println(PRINTTRANSACTIONS + "\t:Print Transations");
		System.out.println(LISTOUTSTANDINGORDERS + "\t:List Outstanding Orders");
		System.out.println(LISTMEMBERS + "\t:List All Members");
		System.out.println(LISTPRODUCT + "\t:List All Product");
		System.out.println(SAVE + "\t:Save Store Data");
		System.out.println(HELP + "\t:Print Help\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
