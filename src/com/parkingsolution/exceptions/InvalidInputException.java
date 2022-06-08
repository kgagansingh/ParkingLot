package src.com.parkingsolution.exceptions;

public class InvalidInputException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public static String MESSAGE="Invalid Input";

	public InvalidInputException(String message) {
		super(message);
	}

}
