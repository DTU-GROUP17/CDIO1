package factories.exceptions;

public class InvalidUsernameException extends InvalidInputException {
	public InvalidUsernameException() {
	}

	public InvalidUsernameException(String message) {
		super(message);
	}
}
