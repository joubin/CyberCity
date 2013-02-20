package person;

//All attributes that makes a "person" will exist here

import java.util.Date;
import java.util.PriorityQueue;

public class Person {
	public enum genders {
		MALE, FEMALE;
	}

	public void person() {
		String name;
		Date birthDate;
		genders gender;
	}
	
	PriorityQueue<Task> personPriorityQueue = new PriorityQueue<Task>();
	
}
