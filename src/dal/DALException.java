package dal;

public class DALException extends Exception {
	private Exception e;

	public DALException(String msg) {
		super(msg);
	}
	
	public DALException(Exception e){
		super(e.getMessage());
		this.e = e;
	}

	@Override
	public void printStackTrace() {
		e.printStackTrace();
	}
	
	

}