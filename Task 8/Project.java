import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.File;
import java.time.format.DateTimeFormatter;

/**
 *
 * The class contains the data and attributes of a building
 * project. This class is dependent of the Person class as
 * you cannot create a project class without a person class.
 *
public class Project {

  /**
   * Database object to retrieve and read data to database.
   */
  DatabaseManager databaseManager;

  //Attributes
  /**
   * Person object for the architect of the project
   */
  private Person architect;

  /**
   * Person object for contractor of the project
   */
  private Person contractor;

  /**
   * Person object for the customer of the project
   */
  private Person customer;

  /**
   * String value for project number
   */
  private String projectNumber;

  /**
   * String value for name of project
   */
  private String name;

  /**
   * String value for Building Type of project
   */
  private String buildingType;

  /**
   * String value for address of project
   */
  private String address;

  /**
   * String value for ERF Number of project
   */
  private String erfNumber;

  /**
   * LocalDate value for deadline of project
   */
  private String deadline;

  /**
   * LocalDate value for completion date of project
   */
  private String completionDate;

  /**
   * Float value for total fees of project
   */
  private float totalProjectFees;

  /**
   * Float value for total fees paid of project
   */
  private float amountPaidToDate;

  /**
   * Boolean value to show if project is finalised
   */
  private boolean isFinalised;


  /**
   *
   * Constructor
   *
   * @param projectNumber Unique number of project
   * @param name Name of project
   * @param buildingType Type of building
   * @param address Address of project
   * @param erfNumber ERF Number of project
   * @param totalProjectFees Total cost of project
   * @param amountPaidToDate Total already paid for project
   * @param deadline Deadline for project
   * @param architect Architect for the project
   * @param contractor Contractor for the project
   * @param customer Customer of the project*/
  // Constructor
  public Project(String projectNumber, String name, String buildingType,
                 String address, String erfNumber, float totalProjectFees,
                 float amountPaidToDate, String deadline, Person contractor,
                 Person architect, Person customer) {

    this.projectNumber = projectNumber;
    this.name = name;
    this.buildingType = buildingType;
    this.address = address;
    this.erfNumber = erfNumber;
    this.totalProjectFees = totalProjectFees;
    this.amountPaidToDate = amountPaidToDate;
    this.deadline = deadline;
    this.contractor = contractor;
    this.architect = architect;
    this.customer = customer;
    databaseManager = DatabaseManager.getInstance();
  }

  /**
   *
   * The method finalises the project by changing the isFinalised
   * attribute to true and generates an invoice.
   *
   */
  public void finaliseProject() {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");
    setCompletionDate(dateFormatter.format(LocalDate.now()));
    if(getTotalProjectFees() - getAmountPaidToDate() > 0) {
      generateInvoice();
    }
    setFinalised(true);
    System.out.println("Project finalised.");
  }

  // Generate invoice to be saved to textfile
  /**
   *
   * The method generates an invoice for the customer
   * with the necessary information gathered from the
   * attributes of the project object
   *
   */
  public void generateInvoice() {
    String invoiceDirectory = "invoices";
    float amountToBePaid = getTotalProjectFees() - getAmountPaidToDate();
    FileWriter fileWriter = null;
    if(amountToBePaid > 0) {
      // Create Invoice.
      String invoice = "Customer: " + getCustomer().getName();
      invoice += "\nTel number: " + getCustomer().getTelNumber();
      invoice += "\nEmail: " + getCustomer().getEmailAddress();
      invoice += "\nCompletion Date: " + getCompletionDate();
      invoice += "\n\nTotal amount to be paid: R" + amountToBePaid;
      System.out.println(invoice);
      try {
        // Create file and save invoice to file
        new File(invoiceDirectory).mkdirs();
        invoiceDirectory += "\\";
        invoiceDirectory += name + ".txt";
        File invoiceFile = new File(invoiceDirectory);
        fileWriter = new FileWriter(invoiceFile);
        fileWriter.write(invoice + "\n");

      } catch (NullPointerException | IOException e) {
        System.out.println("File error occurred");
        e.printStackTrace();
      } finally {
        if(fileWriter != null) {
          try {
            fileWriter.close();
          } catch (IOException e) {
            System.out.println("Closing invoice file error.");
          }
        }
      }
    }
  }

  // Print data to console in readable format
  /**
   *
   * The method returns all the information of the
   * project object in string format
   *
   * @return All information of object in String format
   */
  public String toString() {
    return  ("****************************************************"
            + "\nProject name: " + name
            + "\nProject number: " + projectNumber
            + "\nBuilding type: " + getBuildingType()
            + "\nAddress: " + getAddress()
            + "\nERF Number: " + getErfNumber()
            + "\nDue date: " + deadline
            + "\nArchitect: " + getArchitect().getName()
            + "\n\tTel number:" + getArchitect().getTelNumber()
            + "\n\tEmail:" + getArchitect().getEmailAddress()
            + "\nContractor: " + getContractor().getName()
            + "\n\tTel number:" + getContractor().getTelNumber()
            + "\n\tEmail:" + getContractor().getEmailAddress()
            + "\nCustomer: " + getCustomer().getName()
            + "\n\tTel number:" + getCustomer().getTelNumber()
            + "\n\tEmail:" + getCustomer().getEmailAddress()
            + "\nTotal project fee: R" + getTotalProjectFees()
            + "\nTotal paid to date: R" + getAmountPaidToDate()
            + "\nProject finalised: " + isFinalised
            + "\nCompletion date: " + getCompletionDate()
            + "\n****************************************************");
  }

  /**
   *
   * The method returns the name of the Project object.
   *
   * @return name Project name
   */
  public String getName() { return name; }

  /**
   *
   * The method returns the project number of the Project object.
   *
   * @return projectNumber Unique number of project
   */
  public String getProjectNumber() { return projectNumber;}

  /**
   *
   * The method returns the boolean value for the
   * isFinalised attribute of the Person object.
   *
   * @return isFinalised Boolean value that displays
   * if project is finalised
   */
  public boolean getIsFinalised() {
    return isFinalised;
  }

  /**
   *
   * The method returns the deadline of the Project object.
   *
   * @return deadline Project deadline
   */
  public String getDeadline() {return deadline;}

  /**
   *
   * The method returns the contractor for the Project object.
   *
   * @return contractor Contractor for project
   */
  public Person getContractor() { return contractor; }

  /**
   *
   * The method returns the architect for the Project object.
   *
   * @return architect Architect for project
   */
  public Person getArchitect() { return architect; }

  /**
   *
   * The method returns the customer of the Project object.
   *
   * @return customer Customer of project
   */
  public Person getCustomer() { return customer; }

  /**
   *
   * The method returns the building type of the Project object.
   *
   * @return buildingType Project building type
   */
  public String getBuildingType() { return buildingType; }

  /**
   *
   * The method returns the address of the Project object.
   *
   * @return address Project address
   */
  public String getAddress() { return address; }

  /**
   *
   * The method returns the ERF number of the Project object.
   *
   * @return erfNumber Project ERF number
   */
  public String getErfNumber() { return erfNumber; }

  /**
   *
   * The method returns the completion date of the Project object.
   *
   * @return completionDate Project completion date
   */
  public String getCompletionDate() { return completionDate; }

  /**
   *
   * The method returns the total fee of the Project object.
   *
   * @return totalProjectFees Projects total Fee
   */
  public float getTotalProjectFees() { return totalProjectFees; }

  /**
   *
   * The method returns the total amount paid to date
   * of the Project object.
   *
   * @return amountPaidToDate Total of project fee that is paid
   */
  public float getAmountPaidToDate() { return amountPaidToDate; }

  /**
   *
   * The method sets the amount paid to date of the Project object to
   * a new given value and changes its respective value in the
   * database table.
   *
   * @param newAmount new value to set amountPaidToDate to.
   * */
  public void setAmountPaidToDate(float newAmount) {
    databaseManager.updateProject(projectNumber,"PaidToDate", newAmount);
    this.amountPaidToDate = newAmount;
  }

  /**
   *
   * The method sets the Project number to a new given number
   * and changes its respective value in the database table.
   *
   * @param number new number to set ProjectNumber to.
   * */
  public void setProjectNumber(String number){
    databaseManager.updateProject(projectNumber,"Number", number);
    this.projectNumber = number;
  }

  /**
   *
   * The method sets the amount paid to date of the Project object to
   * a new given value and changes its respective value in the
   * database table.
   *
   * @param newDeadline new value to set project deadline to.
   * */
  public void setDeadline(String newDeadline) {
    databaseManager.updateProject(projectNumber, "DeadLine", newDeadline);
    this.deadline = newDeadline;
  }

  /**
   *
   * The method sets the amount paid to date of the Project object to
   * a new given value and changes its respective value in the
   * database table.
   *
   * @param completionDate new value to set completion date to.
   * */
  public void setCompletionDate(String completionDate) {
    databaseManager.updateProject(projectNumber, "CompletionDate", completionDate);
    this.completionDate = completionDate;
  }

  /**
   *
   * The method sets the amount paid to date of the Project object to
   * a new given value.
   *
   * @param completionDate new value to set completion date to.
   * @since version 1.00
   * */
  public void setCompletionDateOnly(String completionDate) {
    this.completionDate = completionDate;
  }

  /**
   *
   * The method sets the amount paid to date of the Project object to
   * a new given value and changes its respective value in the
   * database table.
   *
   * @param finalised new value to set isFinalised to.
   * */
  public void setFinalised(boolean finalised) {
    String isFinal = "";
    if (finalised) {
      isFinal = "true";
    } else {
      isFinal = "false";
    }
    databaseManager.updateProject(projectNumber,"Finalised", isFinal);
    this.isFinalised = finalised;
  }

  /**
   *
   * The method sets the amount paid to date of the Project object to
   * a new given value.
   *
   * @param finalised new value to set isFinalised to.
   * */
  public void setFinalisedOnly(boolean finalised) {
    this.isFinalised = finalised;
  }

  /**
   *
   * The method sets the Building type of the Project object to
   * a new given building type and changes its respective value in the
   * database table.
   *
   * @param buildingType new String to set buildingType to.
   * */
  public void setBuildingType(String buildingType) {
    databaseManager.updateProject(projectNumber,"BuildingType", buildingType);
    this.buildingType = buildingType;
  }

  /**
   *
   * The method sets theAddress of the Project object to
   * a new given Address and changes its respective value in the
   * database table.
   *
   * @param address new String to set Address to.
   * */
  public void setAddress(String address) {
    databaseManager.updateAddress(this.address, erfNumber, address);
    this.address = address;
  }

  /**
   *
   * The method sets the Erf number of the Project object to
   * a new given Erf number and changes its respective value in the
   * database table.
   *
   * @param erfNumber new String to set erfNumber to.
   * */
  public void setErfNumber(String erfNumber) {
    databaseManager.updateERFNumber(address, erfNumber);
    this.erfNumber = erfNumber;
  }
}