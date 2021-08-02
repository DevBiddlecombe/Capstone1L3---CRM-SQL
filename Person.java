
public class Person {
	/*
	 * @Author: Devereaux Biddlecombe V1.0
	 */

	/*
	 * initialising variables to create a constructor for person object
	 */
	String personName;
	String personSurn;
	String personNum;
	String personEmail;
	String personAddress;

	/*
	 * Constructor
	 */
	public Person(String personName, String personSurn, String personNum, String personEmail, String personAddress) {

		this.personName = personName;
		this.personSurn = personSurn;
		this.personNum = personNum;
		this.personEmail = personEmail;
		this.personAddress = personAddress;
	}

	/*
	 * A toString method to print all details
	 */
	public String toString() {
		String output = "";
		output += "Customer name " + personName;
		output += "\nCustomer Number " + personNum;
		output += "\nCustomer Email Address " + personEmail;
		output += "\nCustomer Physical Address " + personAddress;
		return output;
	}

	// Getters and setters
	public String getpersonSurn() {
		return personSurn;
	}

	// Getters and setters
	public void setpersonNum(String personNum) {
		this.personNum = personNum;

	}

	// Getters and setters
	public void setpersonEmail(String personEmail) {
		this.personEmail = personEmail;

	}

	// Getters and setters
	public void setpersonAddress(String personAddress) {
		this.personAddress = personAddress;

	}

}
