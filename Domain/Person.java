package Domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    private int ID;
    private String forename;
    private String surname;
    private int noApartment;
    private LocalDate birthdate;
    private String job;

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

    public Person(int ID, String forename, String surname, int noApartment, String birthdate, String job) {
        String format = "yyyy-MM-dd";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);

        this.ID = ID;
        this.forename = forename;
        this.surname = surname;
        this.noApartment = noApartment;
        this.birthdate = LocalDate.parse(birthdate, dateFormat);
        this.job = job;
    }

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

    public int getID() {
        return this.ID;
    }

    public String getForename() {
        return this.forename;
    }

    public void setForename(String newForename) {
        this.forename = newForename;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String newSurname) {
        this.surname = newSurname;
    }

    public int getNoApartment() {
        return this.noApartment;
    }

    public void setNoApartment(int newNoApartment) {
        this.noApartment = newNoApartment;
    }

    public LocalDate getBirhtdate() {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate newBirthdate) {
        this.birthdate = newBirthdate;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String newJob) {
        this.job = newJob;
    }

    public boolean isOver18() {
        LocalDate now = LocalDate.now();
        return this.birthdate.isBefore(now.minusYears(18));
    }

    public String toCSV() {
        return ID + "," + forename + "," + surname + "," + noApartment + "," + birthdate + "," + job;
    }
}
