package src.com.parkingsolution.comparators;

import java.util.Comparator;

public class CustomComparatorForSlots implements Comparator<Integer>{
	public int compare(Integer slot1,Integer slot2) {
		return Integer.compare(slot1, slot2);
	}

}
