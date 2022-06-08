package src.com.parkingsolution.exceptions;

public class ParkingIsFullException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ParkingIsFullException() {
		super("Parking is Full");
	}

}
