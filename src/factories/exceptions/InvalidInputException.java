package factories.exceptions;


public abstract class InvalidInputException extends Exception {

	public InvalidInputException() {
	}

	public InvalidInputException(String message) {
		super(message);
	}
}
