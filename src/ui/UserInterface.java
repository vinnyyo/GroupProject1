package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import business.facade.Store;

public class UserInterface {

	private static UserInterface userInterface;
	private static Store store;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final int EXITUI = 0;
	private static final int ADDMEMBER = 1;
	private static final int REMOVEMEMBER = 2;
	private static final int ADDPRODUCT = 3;
	private static final int CHECKOUT = 4;
	private static final int PROCESSSHIPMENT = 5;
	private static final int CHANGEPRICE = 6;
	private static final int GETPRODUCTINFO = 7;
	private static final int GETMEMBERINFO = 8;
	private static final int PRINTTRANSACTIONS = 9;
	private static final int LISTOUTSTANDINGORDERS = 10;
	private static final int LISTMEMBERS = 11;
	private static final int LISTPRODUCT = 12;
	private static final int SAVE = 13;
	private static final int HELP = 14;

	private UserInterface() {
		if (confirm("Load saved data? ")) {
			// load data
		} else {
			store = Store.instance();
		}
	}

    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }
	
    /**
     * Gets a token after prompting
     * 
     * @param prompt - whatever the user wants as prompt
     * @return - the token from the keyboard
     * 
     */
    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }

    private boolean confirm(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no: ");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
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

	/**
     * Prompts for a command from the keyboard
     * 
     * @return a valid command
     * 
     */
    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

	public void inputLoop() {
        int command;
        help();
        while ((command = getCommand()) != EXITUI) {
            switch (command) {
	            case ADDMEMBER:
	            	break;
	            case REMOVEMEMBER:
	            	break;
	            case ADDPRODUCT:
	            	break;
	            case CHECKOUT:
	            	break;
	            case PROCESSSHIPMENT:
	            	break;
	            case CHANGEPRICE:
	            	break;
	            case GETPRODUCTINFO:
	            	break;
	            case GETMEMBERINFO:
	            	break;
	            case PRINTTRANSACTIONS:
	            	break;
	            case LISTOUTSTANDINGORDERS:
	            	break;
	            case LISTMEMBERS:
	            	break;
	            case LISTPRODUCT:
	            	break;
	            case SAVE:
	            	break;
	            case HELP:
	            	help();
	            	break;
            }
        }
        System.out.println("Goodbye.");
    }

	public static void main(String[] args) {
		UserInterface.instance().inputLoop();
	}
}
