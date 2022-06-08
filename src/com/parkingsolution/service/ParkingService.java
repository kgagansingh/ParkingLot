package src.com.parkingsolution.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import src.com.parkingsolution.common.Pair;
import src.com.parkingsolution.comparators.CustomComparatorForSlots;
import src.com.parkingsolution.exceptions.InvalidInputException;
import src.com.parkingsolution.exceptions.ParkingIsFullException;
import src.com.parkingsolution.model.Driver;
import src.com.parkingsolution.model.Vehicle;

public class ParkingService {
	private Integer size;
	private PriorityQueue<Integer> space;
	
	//Map to keep track of vehicles with same age Drivers
	private HashMap<Integer, List<Vehicle>> ageVehileMap = new HashMap<>();
	//Map to keep track of registration  numbers and their slots
	private HashMap<String, Integer> rcNumSlotMap = new HashMap<>();
	//map to keep track of slots with same age drivers
	private HashMap<Integer, List<Integer>> ageSlotMap = new HashMap<>();
	//map to keep track of slot and vehicle,Driver associated with it
	private HashMap<Integer, Pair<Driver, Vehicle>> slotUserMapping = new HashMap<Integer, Pair<Driver, Vehicle>>();

	
	//contructor using Minheap for faster serach of vacant spot
	public ParkingService(int size) {
		this.size = size;
		this.space = new PriorityQueue<Integer>(new CustomComparatorForSlots());
		for (int i = 1; i <= size; i++) {
			this.space.offer(i);
		}
		System.out.println("Created parking of " + size + " slots");
	}

	
	public void park(Driver driver, Vehicle vehicle) {
		if (!this.space.isEmpty()) {
			int slot = this.space.poll();

			slotUserMapping.put(slot, new Pair<>(driver, vehicle));

			if (ageVehileMap.get(driver.getAge()) != null) {
				ageVehileMap.get(driver.getAge()).add(vehicle);
			} else {
				List<Vehicle> vehicleList = new LinkedList<>();
				vehicleList.add(vehicle);
				ageVehileMap.put(driver.getAge(), vehicleList);
			}

			rcNumSlotMap.put(vehicle.getRegistrationNumber(), slot);

			if (ageSlotMap.get(driver.getAge()) != null) {
				ageSlotMap.get(driver.getAge()).add(slot);
			} else {
				List<Integer> slotList = new LinkedList<>();
				slotList.add(slot);
				ageSlotMap.put(driver.getAge(), slotList);
			}
			System.out.println("Car with vehicle registration number \"" + vehicle.getRegistrationNumber()
					+ "\" has been parked at slot number " + slot);

		} else {
			throw new ParkingIsFullException();
		}

	}

	public void leave(Integer slot) {
		if (slot < 1 || slot > this.size || slotUserMapping.get(slot) == null) {
			throw new InvalidInputException("Invalid Parking Slot");
		}
		this.space.offer(slot);
		Driver driver = slotUserMapping.get(slot).getFirst();
		Vehicle vehicle = slotUserMapping.get(slot).getSecond();
		slotUserMapping.put(slot, null);

		ageVehileMap.get(driver.getAge()).remove(vehicle);
		ageSlotMap.get(driver.getAge()).remove(slot);
		rcNumSlotMap.remove(vehicle.getRegistrationNumber());
		System.out.println("Slot number " + slot + " vacated, the car with vehicle registration number "
				+ vehicle.getRegistrationNumber() + " left the space, the driver of the car was of age "
				+ driver.getAge());

	}

	public void vehicleRegistrationNumberForDriverOfAge(int age) throws Exception {
		if (ageVehileMap.get(age) == null) {
			return;
		}
		printList(ageVehileMap.get(age));
	}

	public void slotNumberForCarWithNumber(Vehicle vehicle) {
		if (rcNumSlotMap.get(vehicle.getRegistrationNumber()) == null) {
			throw new InvalidInputException("Registration Number Doesnot Exist");
		}
		System.out.println(rcNumSlotMap.get(vehicle.getRegistrationNumber()));
	}

	public void slotNumbersForDriverOfAge(int age) {
		if (ageSlotMap.get(age) == null) {
			return;
		}
		printList(ageSlotMap.get(age));
	}

	
	private void printList(List<?> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			System.out.print(list.get(i) + ",");
		}
		System.out.println(list.get(list.size() - 1));
	}

}
