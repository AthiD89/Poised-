/**
 *
 * The class contains the attributes for a person involved
 * with a project such as the Architect, contractor or
 * customer.
 *
 */
public class Person {
	
	//Attributes
	/**
	 * Database object to retrieve and read data to database.
	 */
	DatabaseManager databaseManager;

	/**
	 * String value for jobType of Person
	 */
	private String jobType;

	/**
	 * String value for name of Person
	 */
	private String name;

	/**
	 * String value for telNumber of Person
	 */
	private String telNumber;

	/**
	 * String value for email address of Person
	 */
	private String emailAddress;

	/**
	 * String value for physical address of Person
	 */
	private String physicalAddress;

	/**
	 * String value for the table name of the person object
	 */
	private String tableName;

	// Constructor
	/**
	 *
	 * Constructor
	 *
	 * @param jobType Job type of person
	 * @param name Name of person
	 * @param telNumber Tel number person
	 * @param emailAddress Email address of person
	 * @param physicalAddress Physical address of person */
	public Person(String jobType, String name, String telNumber, String emailAddress,
				  String physicalAddress) {
		this.jobType = jobType;
		this.name = name;
		this.telNumber = telNumber;
		this.emailAddress = emailAddress;
		this.physicalAddress = physicalAddress;
		databaseManager = DatabaseManager.getInstance();
		tableName = jobType + "s";
	}
	/**
	 *
	 * The method returns the name of the Person object.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 *
	 * The method returns the job type of the Person object.
	 *
	 * @return jobType
	 */
	public String getJobType() {
		return jobType;
	}
	/**
	 *
	 * The method returns the tel number of the Person object.
	 *
	 * @return telNumber
	 */
	public String getTelNumber() {
		return telNumber;
	}
	/**
	 *
	 * The method returns the email address of the Person object.
	 *
	 * @return emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 *
	 * The method returns the physical of the Person object.
	 *
	 * @return physicalAddress
	 * @since version 1.00
	 */
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	/**
	 *
	 * The method sets the tel number of the Person object to
	 * a new given value and changes its respective value in the
	 * database table.
	 *
	 * @param newTelNumber new value to set telNUmber to.
	 * */
	public void setTelNumber(String newTelNumber) {
		databaseManager.updatePerson(tableName, name, "Tel", newTelNumber);
		this.telNumber = newTelNumber;
	}
	/**
	 *
	 * The method sets the email address of the Person object to
	 * a new given value and its changes respective value in the
	 * database table.
	 *
	 * @param newEmailAddress new value to set emailAddress to.
	 * */
	public void setEmailAddress(String newEmailAddress) {
		databaseManager.updatePerson(tableName, name, "Email", newEmailAddress);
		this.emailAddress = newEmailAddress;
	}

	/**
	 *
	 * The method sets the name of the Person object to
	 * a new given name and changes its respective value in the
	 * database table.
	 *
	 * @param newName new name to set Person name to.
	 * */
	public void setName(String newName) {
		databaseManager.updatePersonName(tableName, name, this, newName);
		this.name = newName;
	}

	/**
	 *
	 * The method sets the physical address of the Person object to
	 * a new given value and changes its respective value in the
	 * database table.
	 *
	 * @param newAddress new address to set physicalAddress to.
	 * */
	public void setPhysicalAddress(String newAddress) {
		databaseManager.updatePerson(tableName, name, "Address", newAddress);
		this.physicalAddress = newAddress;
	}
	
}