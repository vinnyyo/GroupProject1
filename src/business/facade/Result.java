package business.facade;

public class Result extends DataTransfer {
	public static final int OPERATION_SUCCESSFUL = 1;
	public static final int OPERATION_FAILURE = 2;
	public static final int PRODUCT_NOT_FOUND = 3;
	public static final int PRODUCT_FOUND = 4;
	public static final int MEMBER_NOT_FOUND = 5;
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
}
