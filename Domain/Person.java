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

    public Person(int ID, String forename, String surname, int noApartment, LocalDate birthdate, String job) {
        this.ID = ID;
        this.forename = forename;
        this.surname = surname;
        this.noApartment = noApartment;
        this.birthdate = birthdate;
        this.job = job;
    }

    public Person(String[] data) {
        String format = "yyyy/MM/dd";
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
        if (this.birthdate.isBefore(now.minusYears(18)))
            return true;
        return false;
    }

    public String toCSV() {
        return ID + "," + forename + "," + surname + "," + noApartment + "," + birthdate + "," + job;
    }
}
