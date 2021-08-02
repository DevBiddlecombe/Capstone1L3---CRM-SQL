/*
 * @Author: Devereaux Biddlecombe
 * V1.0
 * Object Project for class Poised. 
 */
public class Project {

	/*
	 * initialising variables to create a constructor for project object
	 */
	String projName;
	int projNum;
	String projType;
	String projAddress;
	String projErf;
	String projDeadline;
	double projFee;
	double projPaid;
	String projCompleted;
	boolean projFinalized;

	/*
	 * Creating different types of <Person> to include in project description
	 */

	Person architect;
	Person contractor;
	Person customer;

	/*
	 * Constructor
	 */
	public Project(int projNum, String projName, String projType, String projAddress, String projErf,
			String projDeadline, double projFee, double projPaid, String projCompleted, boolean projFinalized,
			Person architect, Person contractor, Person customer) {

		this.projNum = projNum;
		this.projName = projName;
		this.projType = projType;
		this.projAddress = projAddress;
		this.projErf = projErf;
		this.projDeadline = projDeadline;
		this.projFee = projFee;
		this.projPaid = projPaid;
		this.projCompleted = projCompleted;
		this.projFinalized = projFinalized;
		this.architect = architect;
		this.contractor = contractor;
		this.customer = customer;
	}

	/*
	 * Updating deadline from Poised.Java case 2
	 */
	public void setDeadline(String projDeadline) {
		this.projDeadline = projDeadline;
	}

	/*
	 * Updating projFee from Poised.Java case 3.
	 */
	public void setProjectFee(double projPaid) {
		this.projPaid = projPaid;
	}

	/*
	 * Calculating invoice cost for Poised.Java case 5.
	 */
	public double generateInvoice() {
		double invoice;
		invoice = projFee - projPaid;
		return invoice;

	}

	/*
	 * toString method to print Projects.
	 */
	public String toString() {
		String output = "";
		System.out.println("---------------------------");
		output += "Project Number " + projNum;
		output += "\nProject Name " + projName;
		output += "\nBuilding type " + projType;
		output += "\nProject Address " + projAddress;
		output += "\nProject Erf " + projErf;
		output += "\nProject Deadline " + projDeadline;
		output += "\nProject Fee " + projFee;
		output += "\nProject fees paid to date " + projPaid;
		output += "\nProject finlaized " + projFinalized;
		return output;
	}

	// Setters and getters
	public void setprojCompleted(Object currentDate) {
		this.projCompleted = (String) currentDate;

	}

	// Getters and setters
	public void setprojFinalized(boolean b) {
		this.projFinalized = true;

	}

	// Getters and setters
	public String getprojDeadline() {
		return projDeadline;
	}

	/*
	 * this will write project details to ProjectDetails.txt with a # between data
	 * to allow the program to read variables.
	 */
	public Object write() {
		String output = (projNum + "#" + projName + "#" + projType + "#" + projAddress + "#" + projErf + "#"
				+ projDeadline + "#" + projFee + "#" + projPaid + "#" + projCompleted + "#" + projFinalized + "#"
				+ architect.personName + "#" + architect.personSurn + "#" + architect.personNum + "#"
				+ architect.personEmail + "#" + architect.personAddress + "#" + customer.personName + "#"
				+ customer.personSurn + "#" + customer.personNum + "#" + customer.personEmail + "#"
				+ customer.personAddress + "#" + contractor.personName + "#" + contractor.personSurn + "#"
				+ contractor.personNum + "#" + contractor.personEmail + "#" + contractor.personAddress + "\n");
		return output;
	}

	/*
	 * This is to output finalized projects to CompletedProjects.txt It is written
	 * in a readable format for users outside of the program.
	 */
	public Object writeCompleted() {
		String output = ("----------------------------------");
		output += ("\nProject Number: " + projNum + "\nProject Name: " + projName + "\nProject Type: " + projType
				+ "\nProject Address: " + projAddress + "\nErf Number: " + projErf + "\nDeadline: " + projDeadline
				+ "\nTotal Cost of project :" + projFee + "\nTotal fees paid by client: " + projPaid
				+ "\nCompleted date " + projCompleted + "\nFinalized: " + projFinalized + "\n\nArchitect details\n"
				+ "\nName: " + architect.personName + "\nSurname: " + architect.personSurn + "\nEmail address: "
				+ architect.personEmail + "\nPhysical address: " + architect.personAddress + "\nPhone number :"
				+ architect.personNum + "\n\nContractor detials\n" + "\nName: " + contractor.personName + "\nSurname: "
				+ contractor.personSurn + "\nEmail address: " + contractor.personEmail + "\nPhysical address: "
				+ contractor.personAddress + "\nPhone number :" + contractor.personNum + "\n\nCustomer details\n"
				+ "\nName: " + customer.personName + "\nSurname: " + customer.personSurn + "\nEmail address: "
				+ customer.personEmail + "\nPhysical address: " + customer.personAddress + "\nPhone Number :"
				+ customer.personNum + "\n----------------------------------");
		return output;
	}
}
