import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * @author Devereaux Biddlecombe
 * V1.0
 * Main method for poised management. View projects, add a project, edit existing projects, finalize and generate invoices.
 */
public class Poised {

	public static void main(String[] args) throws IOException, SQLException {
		Connection conn = null;
		// logging into database. Surrounded with error catching to let the user know if
		// they are connected.
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poised_db", "otheruser", "swordfish");
		} catch (SQLException e) {
			System.out.print("MySql Database not connected!");
		}
		if (conn != null) {
			System.out.print("SUCCESS! Connected to the database\n");
		}

		// Create a direct line to the database for running queries
		Statement stmt = conn.createStatement();
		/*
		 * Initiating a scanner used for inputs from keyboard.
		 */
		Scanner input = new Scanner(System.in);

		boolean exit = false;

		/*
		 * A do while loop to validate inputs and keep menu recurring.
		 */
		do {
			// Menu inside do loop to repeat after every action
			System.out.println("Welcome to Poised projects project management tool");
			System.out.println("--------------------------------------------------");
			System.out.println("Please select an option from the menu\n1. Add a new project"
					+ "\n2. Edit an existing project \n3. View uncompleted projects \n4. View overdue projects"
					+ " \n5. Finalise the project & generate invoice. \n6. View all projects \n9. Exit the program");
			// Using switch to create a usable menu with options above.
			String choice = input.nextLine();
			switch (choice) {

			case "1":
				/*
				 * Using scanner from earlier to create a new object to add to file
				 */

				System.out.println("First we enter project details");

				System.out.println("Project number ");
				int projNum = input.nextInt();
				input.nextLine();

				System.out.println("Project name ");
				String projName = input.nextLine();

				System.out.println("Project type ");
				String projType = input.nextLine();

				System.out.println("project address");
				String projAddress = input.nextLine();

				System.out.println("ERF Number ");
				String projErf = input.nextLine();

				System.out.println("Deadline  - DD/MM/YYYY");
				String projDeadline = input.nextLine();

				// Surrounded with try catch to avoid currency errors
				double projFee = 0;
				while (true) {
					try {
						System.out.print("Please enter total project fee");
						projFee = input.nextDouble();
						input.nextLine();
						break;
					} catch (Exception e) {
						System.out.print("Numbers only please - no currency symbols (R, $ etc)");

					}
				}

				/*
				 * Surrounded with try catch to avoid currency errors
				 */
				double projPaid = 0;
				while (true) {
					try {
						System.out.println("Please enter fee's paid to date by customer");
						projPaid = input.nextDouble();

						break;
					} catch (Exception e) {
						System.out.println("Numbers only please - no currency symbols (R, $ etc)");

					}
				}
				/*
				 * The project has just been added so it is neither complete nor finalized
				 */
				String projCompleted = "no";

				boolean projFinalized = false;

				/*
				 * Creating object project by calling project class project class constructor
				 * creates object project.
				 */

				// Using Scanner to input date to create object architect
				System.out.println("Architect details");

				System.out.println("Architect first name");
				input.nextLine();
				String archName = input.nextLine();

				System.out.println("Architect Surname");
				String archSurn = input.nextLine();

				System.out.println("Architect phone number");
				String archNum = input.nextLine();

				System.out.println("Architect Email address");
				String archEmail = input.nextLine();

				System.out.println("Architect physical address");
				String archAddress = input.nextLine();

				// Using Scanner to input date to create object contractor
				System.out.println("Contractor Details");

				System.out.println("Contractor first name ");
				String contName = input.nextLine();

				System.out.println("Contractor Surname");
				String contSurn = input.nextLine();

				System.out.println("Contractor number");
				String contNum = input.nextLine();

				System.out.println("Contractor Email address");
				String contEmail = input.nextLine();

				System.out.println("Contractor physical address");
				String contAddress = input.nextLine();

				// Using Scanner to input date to create object customer
				System.out.println("Customer Details");

				System.out.println("Customer first name ");
				String custName = input.nextLine();

				System.out.println("Customer surname ");
				String custSurn = input.nextLine();

				System.out.println("Customer number");
				String custNum = input.nextLine();

				System.out.println("Customer Email address");
				String custEmail = input.nextLine();

				System.out.println("Customer address");
				String custAddress = input.nextLine();

				// Creating object contractor by calling person class
				// person class constructor creates object contractor

				if (projName.equals("")) {
					projName = projType + " " + custSurn;
				}

				addProject(stmt, projNum, projName, projType, projAddress, projErf, projFee, projPaid, projDeadline,
						projFinalized, projCompleted, archName, archSurn, archNum, archEmail, archAddress, contName,
						contSurn, contNum, contEmail, contAddress, custName, custSurn, custNum, custEmail, custAddress);
				break;

			/*
			 * Editing current projects - a sub menu is created to allow ease of use.
			 */
			case "2":

				System.out.print("You have chosen to edit a project");
				boolean menuExit = false;

				do {
					System.out.print("\n1. Change the deadline \n2. Change the fees paid to date "
							+ "\n3. Change a person's contact details \n8. Exit");
					String menuOptions = input.nextLine();
					switch (menuOptions) {

					case "1":
						System.out.println("You have chosen to change the deadline" + "\n");
						int deadlineIndex = chooseIndex();
						System.out.print(deadlineIndex); // DELETE

						/**
						 * Change the due date of the project
						 */
						System.out.print("Please enter the new deadline of the project - for example: 15/07/2021");
						String newDeadline = input.nextLine();

						/**
						 * Setting new deadline in array
						 */
						stmt.executeUpdate("UPDATE project SET projDeadline=" + newDeadline + " WHERE projNum = "
								+ deadlineIndex + "");
						break;

					case "2":

						System.out.println("You have chosen to change the fees paid by customer" + "\n");
						int feesPaidIndex = chooseIndex();

						/**
						 * Change fee paid used try and catch block to determine correct input
						 */

						System.out.println("Enter new fee amount paid by the customer: - numbers only ");

						try {
							double typedProjPaid = input.nextDouble();
							input.nextLine();
							stmt.executeUpdate("UPDATE project SET projPaid =" + typedProjPaid + " WHERE projNum = "
									+ feesPaidIndex + "");
						} catch (Exception e) {
							System.out.print("Numbers only please");

						}

						break;
					/*
					 * Updating a persons contact details
					 */

					case "3":

						int updateIndex = chooseIndex();

						System.out.print("\n1. Contractor details \n2. Architect details \n3. Customer details \n");
						String updateChoice = input.nextLine();

						System.out.print("New Phone number ");
						String typedPersonNum = input.nextLine();

						System.out.print("New email address ");
						String typedPersonEmail = input.nextLine();

						System.out.print("New Physical address ");
						String typedPersonAddress = input.nextLine();
						switch (updateChoice) {

						case "1":
							try {
								System.out.print("You have chosen to edit contractor details");
								stmt.executeUpdate("UPDATE project SET contNum = " + typedPersonNum
										+ " WHERE projnum = " + updateIndex + "");
								stmt.executeUpdate("UPDATE project SET contEmail = " + typedPersonEmail
										+ " WHERE projnum = " + updateIndex + "");
								stmt.executeUpdate("UPDATE project SET contAddress = " + typedPersonAddress
										+ " WHERE projnum = " + updateIndex + "");
								break;
							} catch (Exception e) {
								System.out.print("Unable to update");
							}

						case "2":
							System.out.print("You have chosen to edit architect details");
							try {
								stmt.executeUpdate("UPDATE project SET archNum = " + typedPersonNum
										+ " WHERE projnum = " + updateIndex + "");
								stmt.executeUpdate("UPDATE project SET archEmail = " + typedPersonEmail
										+ " WHERE projnum = " + updateIndex + "");
								stmt.executeUpdate("UPDATE project SET archAddress = " + typedPersonAddress
										+ " WHERE projnum = " + updateIndex + "");
								break;
							} catch (Exception e) {
								System.out.print("Unable to update");
							}

						case "3":
							System.out.print("You have chosen to edit customer details");
							try {
								stmt.executeUpdate("UPDATE project SET custNum = " + typedPersonNum
										+ " WHERE projnum = " + updateIndex + "");
								stmt.executeUpdate("UPDATE project SET custEmail = " + typedPersonEmail
										+ " WHERE projnum = " + updateIndex + "");
								stmt.executeUpdate("UPDATE project SET custAddress = " + typedPersonAddress
										+ " WHERE projnum = " + updateIndex + "");
								break;
							} catch (Exception e) {
								System.out.print("Unable to update");
							}
						}
						break;

					case "8":
						menuExit = true;
						System.out.print("Goodbye");
						break;
					}
				} while (menuExit == false);

			case "3":
				System.out.print("\nView all uncompleted projects\n");

				/**
				 * Looping through array to print uncompleted projects
				 */

				printUncompleted(stmt);
				break;

			case "4":

				checkOverdue(stmt);

				break;

			case "5":
				/*
				 * Finalizing a project. If the customer has an amount to pay an invoice is
				 * generated A copy of the data is then written to ProjectCompleted.txt as a
				 * readable file.
				 */
				System.out.print("Finalize a project \n");
				int finalizedIndex = chooseIndex();
				ResultSet projCost = stmt
						.executeQuery("SELECT projPaid FROM project WHERE projnum = " + finalizedIndex + "");
				ResultSet feePaid = stmt
						.executeQuery("SELECT projfee FROM project WHERE projnum = " + finalizedIndex + "");
				break;

			case "6":
				printAll(stmt);

				break;

			case "9":

				input.close();
				exit = true;
				System.out.print("Goodbye");
				break;

			}
		} while (exit == false);

		// Closing Scanner to avoid data leak.
	}

	private static void printAll(Statement stmt) throws SQLException {
		ResultSet allProj = stmt.executeQuery("SELECT * FROM project WHERE projDeadline < NOW()");
		while (allProj.next()) {
			System.out.println("Project Num: " + allProj.getInt("projId") + "\nProject Name "
					+ allProj.getString("projName") + "\nProject Type: " + allProj.getString("projType")
					+ "\nProject Address: " + allProj.getString("projAddress") + "\nProject Address: "
					+ allProj.getString("projAddress") + "\nProject Erf: " + allProj.getString("projErf")
					+ "\nProject Fee: " + allProj.getString("projFee") + "Fees paid by client: "
					+ allProj.getString("projPaid") + "\nProject Deadline: " + allProj.getString("projDeadline")
					+ "\nProject Finalised: " + allProj.getString("projFinalized") + "\nProject Completed: "
					+ allProj.getString("projCompleted") + "\nArchitect name: " + allProj.getString("archName")
					+ "\nArchitect number: " + allProj.getString("archNum") + "\nArchitect email: "
					+ allProj.getString("archEmail") + "\nArchitect Address: " + allProj.getString("archAddress")
					+ "\nContractor name: " + allProj.getString("contName") + "\n Contractor number: "
					+ allProj.getString("contNum") + "\nContractor email: " + allProj.getString("contEmail")
					+ "\nContractor Address: " + allProj.getString("contAddress") + "\nCustomer name: "
					+ allProj.getString("custName") + "\n Customer number: " + allProj.getString("custNum")
					+ "\nCustomer email: " + allProj.getString("custEmail") + "\nCustomer Address: "
					+ allProj.getString("custAddress"));

		}

	}

	private static void checkOverdue(Statement stmt) throws SQLException {
		ResultSet datecheck = stmt.executeQuery("SELECT * FROM project WHERE projDeadline < NOW()");
		while (datecheck.next()) {
			System.out.println("Project Num: " + datecheck.getInt("projId") + "\nProject Name "
					+ datecheck.getString("projName") + "\nProject Type: " + datecheck.getString("projType")
					+ "\nProject Address: " + datecheck.getString("projAddress") + "\nProject Address: "
					+ datecheck.getString("projAddress") + "\nProject Erf: " + datecheck.getString("projErf")
					+ "\nProject Fee: " + datecheck.getString("projFee") + "Fees paid by client: "
					+ datecheck.getString("projPaid") + "\nProject Deadline: " + datecheck.getString("projDeadline")
					+ "\nProject Finalised: " + datecheck.getString("projFinalized") + "\nProject Completed: "
					+ datecheck.getString("projCompleted") + "\nArchitect name: " + datecheck.getString("archName")
					+ "\nArchitect number: " + datecheck.getString("archNum") + "\nArchitect email: "
					+ datecheck.getString("archEmail") + "\nArchitect Address: " + datecheck.getString("archAddress")
					+ "\nContractor name: " + datecheck.getString("contName") + "\n Contractor number: "
					+ datecheck.getString("contNum") + "\nContractor email: " + datecheck.getString("contEmail")
					+ "\nContractor Address: " + datecheck.getString("contAddress") + "\nCustomer name: "
					+ datecheck.getString("custName") + "\n Customer number: " + datecheck.getString("custNum")
					+ "\nCustomer email: " + datecheck.getString("custEmail") + "\nCustomer Address: "
					+ datecheck.getString("custAddress"));
		}

	}

	private static void printUncompleted(Statement stmt) throws SQLException {
		ResultSet results = stmt.executeQuery("SELECT * FROM project WHERE projCompleted = 'no'");
		while (results.next()) {

			System.out.println("Project Num: " + results.getInt("projId") + "\nProject Name "
					+ results.getString("projName") + "\nProject Type: " + results.getString("projType")
					+ "\nProject Address: " + results.getString("projAddress") + "\nProject Address: "
					+ results.getString("projAddress") + "\nProject Erf: " + results.getString("projErf")
					+ "\nProject Fee: " + results.getString("projFee") + "Fees paid by client: "
					+ results.getString("projPaid") + "\nProject Deadline: " + results.getString("projDeadline")
					+ "\nProject Finalised: " + results.getString("projFinalized") + "\nProject Completed: "
					+ results.getString("projCompleted") + "\nArchitect name: " + results.getString("archName")
					+ "\nArchitect number: " + results.getString("archNum") + "\nArchitect email: "
					+ results.getString("archEmail") + "\nArchitect Address: " + results.getString("archAddress")
					+ "\nContractor name: " + results.getString("contName") + "\n Contractor number: "
					+ results.getString("contNum") + "\nContractor email: " + results.getString("contEmail")
					+ "\nContractor Address: " + results.getString("contAddress") + "\nCustomer name: "
					+ results.getString("custName") + "\n Customer number: " + results.getString("custNum")
					+ "\nCustomer email: " + results.getString("custEmail") + "\nCustomer Address: "
					+ results.getString("custAddress"));

		}

	}

	private static void addProject(Statement stmt, int projNum, String projName, String projType, String projAddress,
			String projErf, double projFee, double projPaid, String projDeadline, boolean projFinalized,
			String archName, String archSurn, String archNum, String archEmail, String archAddress, String contName,
			String contSurn, String contNum, String contEmail, String contAddress, String custName, String custSurn,
			String custNum, String custEmail, String custAddress, String projCompleted) throws SQLException {

		stmt.executeUpdate("insert into project values('" + projNum + "' , '" + projName + "' , '" + projType + "' , '"
				+ projAddress + "' , " + "'" + projErf + "' , '" + projFee + "' , '" + projPaid + "' , '" + projDeadline
				+ "' , '" + projFinalized + "' , '" + projCompleted + "' ,  + '" + archName + "' , '" + archSurn
				+ "' , '" + archNum + "' , '" + archEmail + "' , '" + archAddress + "' ," + " '" + contName + "' , '"
				+ contSurn + "' , '" + contNum + "' , '" + contEmail + "' ," + "'" + contAddress + "' , '" + custName
				+ "' , '" + custSurn + "' , '" + custNum + "' ," + "'" + custEmail + "' , '" + custAddress + "')");
		System.out.print("Values inserted successfully\n");

	}

	/*
	 * Choosing an index for editing existing projects. Method made to avoid
	 * repeating code.
	 */
	private static int chooseIndex() {
		int index = 0;

		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("Please enter the index of the project you would like to change");
				index = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.print("number only please");
				sc.next();
			}
			sc.close();
		}

		return index;

	}

}
