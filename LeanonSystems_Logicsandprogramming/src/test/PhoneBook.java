package test;

import java.util.Optional;
import java.util.Random;
import java.util.Vector;

/*
 * 2 – A huge phone book containing pairs of the form {phone number, person's name} was
 * stored as a vector sorted by name in alphabetical order. Write a program that finds the
 * phone number of a given person in this list, bearing in mind that the list is very large and that
 * users need the search results to be as fast as possible.
 */
public class PhoneBook {

	/*
	 * To be honest it is really weird having this phonebook in a Vector structure, it should instead be a Map 
	 */
	Vector<PhoneBookRecord> phoneBookVector = new Vector<PhoneBookRecord>();

	public PhoneBook() {
		createDataSet();
	}

	/*
	 * Names generated using: https://www.name-generator.org.uk/quick/
	 * Sorted by: https://sortmylist.com/
	 * Array created in notepad++
	 */
	private void createDataSet() {
		String[] names = { "Aaliyah Long", "Aled Moreno", "Alistair Bonilla", "Alvin Fletcher", "Ameera Horton",
				"Amna Mccarty", "Andy Schaefer", "Anika Ballard", "Annalise Hernandez", "Asad Floyd", "Ayub Dillon",
				"Bethany Jordan", "Brayden Higgins", "Briony Wall", "Brogan Hess", "Bronte Bush", "Bryn Peters",
				"Carlos Mueller", "Carter Love", "Cecilia Clayton", "Cerys Cantrell", "Chad Hampton", "Chaya Greene",
				"Ciara Moran", "Cora Velazquez", "Craig Chase", "Dan Clarke", "Danyal Marks", "Darcey", "Spence",
				"Dexter Meadows", "Diana Barlow", "Diego Pearson", "Dominik Meyers", "Donovan Owens", "Dorothy Andrews",
				"Duncan James", "Edith Lindsay", "Elena Robinson", "Elise Case", "Ellen Graves", "Ellis Curry",
				"Elsa Goodwin", "Emmie Sweeney", "Esmee Dawson", "Esther Burke", "Fahad Robbins", "Faizan O'Neill",
				"Felicity Giles", "Freddie Gaines", "Georgina Kidd", "Grover Macdonald", "Hassan Tapia", "Hayden Weiss",
				"Hugh Ingram", "Isaac O'Doherty", "John", "Sanders", "Jonathan Pace", "Julie Hebert", "Kaden Haas",
				"Keanu Dickerson", "Keelan Mcdowell", "Kendra Sheppard", "Kiara Hanson", "Kieron Vazquez",
				"Kimberly Herman", "Kyle Murphy", "Layton Gamble", "Leah Holloway", "Leighton Shaffer", "Lena Best",
				"Lewis Cox", "Louise Adkins", "Mae Armstrong", "Mahdi Booth", "Maia Castillo", "Maisey Thomson",
				"Maisha Mayo", "Melanie Rice", "Michaela Boone", "Milly Newman", "Mohamed Juarez", "Nadine Guerrero",
				"Nellie Haney", "Nikhil Randall", "Pamela Gates", "Polly Mitchell", "Rafael House", "Rafferty Sims",
				"Reid Santos", "Riley Bond", "Safiya Castro", "Sahar Charles", "Samira Sampson", "Taha Mccall",
				"Theodore Finch", "Theresa Mcgowan", "Tyrone Dunlap", "Valerie Ayers", "Xanthe Suarez",
				"Zachary Patrick" };

		for (String name : names) {
			phoneBookVector.add(new PhoneBookRecord(name, generateRandomPhoneNumber()));
		}
	}

	private String generateRandomPhoneNumber() {
		Random randomPhoneNumber = new Random();

		String phoneNumber = String.valueOf(randomPhoneNumber.nextInt(10000000, 99999999));

		return phoneNumber;
	}

	/*
	 * I think parallel stream should do the work
	 */
	public Optional<PhoneBookRecord> findPhoneNumberByName(String name) {
		return phoneBookVector.stream().parallel().filter(phoneBookRecord -> phoneBookRecord.getName().equals(name))
				.findFirst();
	}

	public static void main(String[] args) {
		PhoneBook phoneBook = new PhoneBook();
		
		String[] names = { "Aaliyah Long", "Milly Newman", "John Doe" };

		for (String name : names) {
			System.out.println(String.format("Checking %s phone number...\nResult: %s\n----------------", name,
					phoneBook.findPhoneNumberByName(name)));
		}
	}
}

/*
 * Support class - test purpose 
 */
class PhoneBookRecord {
	private String name;
	private String phoneNumber;

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public PhoneBookRecord(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "PhoneBookRecord [name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}
}