package src.com.parkingsolution.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import src.com.parkingsolution.exceptions.GeneralException;
import src.com.parkingsolution.exceptions.InvalidInputException;
import src.com.parkingsolution.exceptions.ParkingIsFullException;
import src.com.parkingsolution.model.Driver;
import src.com.parkingsolution.model.Vehicle;
import src.com.parkingsolution.service.ParkingService;

public class Main {
	public static void main(String[] args) throws Exception {

		// relative file path so that the program runs anywhere irrespective of the directory
		String filePath = new File("").getAbsolutePath() + "/src/input.txt";
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		String line;
		Integer lineNumber = 1;
		ParkingService parkingService = null;

		// reading line by line in the file
		while ((line = br.readLine()) != null) {
			try {
				String choice = line.split(" ")[0];
				String data = line.split(" ")[1];
				switch (choice) {
					case "Create_parking_lot":
						parkingService = new ParkingService(Integer.parseInt(data));
						break;
					case "Park":
						parkingService.park(new Driver(Integer.parseInt(line.split(" ")[3])), new Vehicle(data));
						break;
					case "Slot_numbers_for_driver_of_age":
						parkingService.slotNumbersForDriverOfAge(Integer.parseInt(data));
						break;
					case "Slot_number_for_car_with_number":
						parkingService.slotNumberForCarWithNumber(new Vehicle(data));
						break;
					case "Leave":
						parkingService.leave(Integer.parseInt(data));
						break;
					case "Vehicle_registration_number_for_driver_of_age":
						parkingService.vehicleRegistrationNumberForDriverOfAge(Integer.parseInt(data));
						break;
					default:
						throw new GeneralException();

				}
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			} catch (ParkingIsFullException e) {
				System.out.println(e.getMessage());
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
				System.out.println(InvalidInputException.MESSAGE + " at Line: " + lineNumber);
			} catch (GeneralException e) {
				System.out.println(e.getMessage() + " at Line: " + lineNumber);
			} catch (Exception e) {
				System.out.println("Something went Wrong!" + " at Line: " + lineNumber);
			}
		}
		br.close();

	}

}
