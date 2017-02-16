package dal.exceptions;

public class NotConnectedException extends DALException {

	public NotConnectedException(){
		super();
	}

	public NotConnectedException(String msg) {
		super(msg);
	}

}
