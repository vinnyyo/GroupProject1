package business.facade;

public class Result extends DataTransfer {
	public static final boolean OPERATION_SUCCESSFUL = true;
	public static final boolean OPERATION_FAILURE = false;
	public static final int PRODUCT_NOT_FOUND = 1;
	public static final int PRODUCT_FOUND = 2;
	public static final int PRODUCT_ADDED = 3;
	public static final int PRODUCT_REMOVED = 4;
	public static final int MEMBER_NOT_FOUND = 5;
	public static final int MEMBER_FOUND = 6;
	public static final int MEMBER_REMOVED = 7;
	public static final int MEMBER_ADDED = 8;
	private boolean success;
	private int status = 0;
	
	public Result() {
		super();
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
