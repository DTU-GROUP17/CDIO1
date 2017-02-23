package factories.exceptions;

public class InvalidPasswordException extends InvalidInputException {
	public InvalidPasswordException() {
	}

	public InvalidPasswordException(String message) {
		super(message);
	}
}
