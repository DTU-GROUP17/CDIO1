package dal.exceptions;

public abstract class DALException extends Exception {
	private Exception e;

	public DALException(){
		super();
	}

	public DALException(String msg) {
		super(msg);
	}
	
	public DALException(Exception e){
		super(e.getMessage());
		this.e = e;
	}

	@Override
	public void printStackTrace() {
		this.e.printStackTrace();
	}
	
	 
	
	

}