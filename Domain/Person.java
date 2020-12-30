package Domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    // Person's fields
    private Integer ID;
    private String forename;
    private String surname;
    private int noApartment;
    private LocalDate birthdate;
    private String job;

    // Default constructor
    public Person() {
        String format = "yyyy-MM-dd";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);

        this.ID = -1;
        this.forename = "noforename";
        this.surname = "nosurname";
        this.noApartment = -1;
        this.birthdate = LocalDate.parse("0001-01-01", dateFormat);
        this.job = "nojob";
    }

    /**
     * Parameterized constructor. Parameters are self explanatory
     * 
     * @param ID
     * @param forename
     * @param surname
     * @param noApartment
     * @param birthdate
     * @param job
     */
    public Person(Integer ID, String forename, String surname, int noApartment, String birthdate, String job) {
        String format = "yyyy-MM-dd";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);

        this.ID = ID;
        this.forename = forename;
        this.surname = surname;
        this.noApartment = noApartment;
        this.birthdate = LocalDate.parse(birthdate, dateFormat);
        this.job = job;
    }

    /**
     * Parameterized constructor
     * 
     * @param data - String Array containing a person's data
     */
    public Person(String[] data) {
        String format = "yyyy-MM-dd";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);

        this.ID = Integer.parseInt(data[0]);
        this.forename = data[1];
        this.surname = data[2];
        this.noApartment = Integer.parseInt(data[3]);
        this.birthdate = LocalDate.parse(data[4], dateFormat);
        this.job = data[5];
    }

    /**
     * Getter for ID
     * 
     * @return person's ID
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * Getter for forename
     * 
     * @return Person's forename
     */
    public String getForename() {
        return this.forename;
    }

    /**
     * Setter for forename
     * 
     * @param newForename Person's new forename
     */
    public void setForename(String newForename) {
        this.forename = newForename;
    }

    /**
     * Getter for surname
     * 
     * @return Person's surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Setter for surname
     * 
     * @param newSurname Person's new surname
     */
    public void setSurname(String newSurname) {
        this.surname = newSurname;
    }

    /**
     * Getter for person's apartment number
     * 
     * @return person's apartment number
     */
    public int getNoApartment() {
        return this.noApartment;
    }

    /**
     * Setter for person's apartment number
     * 
     * @param newNoApartment person's new apartment number
     */
    public void setNoApartment(int newNoApartment) {
        this.noApartment = newNoApartment;
    }

    /**
     * Getter for person's birthdate
     * 
     * @return person's birthdate
     */
    public LocalDate getBirhtdate() {
        return this.birthdate;
    }

    /**
     * Setter for person's birthdate
     * 
     * @param newBirthdate person's new birthdate
     */
    public void setBirthdate(LocalDate newBirthdate) {
        this.birthdate = newBirthdate;
    }

    /**
     * Getter for person's job
     * 
     * @return person's job
     */
    public String getJob() {
        return this.job;
    }

    /**
     * Setter for person's job
     * 
     * @param newJob person's new job
     */
    public void setJob(String newJob) {
        this.job = newJob;
    }

    /**
     * Checks if a person is over 18 years old
     * 
     * @return Boolean values, resulting from the condition above.
     */
    public boolean isOver18() {
        LocalDate now = LocalDate.now();
        return this.birthdate.isBefore(now.minusYears(18));
    }

    /**
     * Converts person's data into a CSV type String
     * 
     * @return CSV type string containing person's data
     */
    public String toCSV() {
        return ID + "," + forename + "," + surname + "," + noApartment + "," + birthdate + "," + job;
    }
}
