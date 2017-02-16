package dal;

public class NotFoundException extends DALException{

	public NotFoundException(){
		super();
	}

	public NotFoundException(String msg) {
		super(msg);
	}

}
