package src.com.parkingsolution.model;

public class Vehicle {
	private String registrationNumber;
	

	public Vehicle(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(registrationNumber);
		return builder.toString();
	}
	
	

}
