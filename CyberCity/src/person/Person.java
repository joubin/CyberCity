package person;

//All attributes that makes a "person" will exist here

import java.util.Date;
import java.util.PriorityQueue;

public class Person {
	String name;
	Date birthDate;
	genders gender;
	public enum genders {
		MALE, FEMALE;
	}

	public void person() {
		
	}

	PriorityQueue<Task> personPriorityQueue = new PriorityQueue<Task>();

}
