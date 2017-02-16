package dal.exceptions;

import dal.exceptions.DALException;

public class NotFoundException extends DALException {

	public NotFoundException(){
		super();
	}

	public NotFoundException(String msg) {
		super(msg);
	}

}
