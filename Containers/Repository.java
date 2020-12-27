package Containers;

import Domain.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Repository {

    // Repository fields
    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<Apartment> apartments = new ArrayList<>();
    private String dbFilePath;

    /**
     * Parameterized constructor
     * 
     * @param dbFilePath - String representing the filepath to a database folder.
     * @note - The folder must contain two files named apartment.csv and people.csv
     */
    public Repository(String dbFilePath) {
        this.dbFilePath = dbFilePath;
    }

    /**
     * Appends memory-saved data of people into the people.csv file
     * 
     * @throws IOException if file does not exist / cannot be accessed / written
     *                     into
     */
    private void savePeople() throws IOException {
        String peopleFilePath = this.dbFilePath + "\\people.csv";
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(peopleFilePath))) {
            for (Person p : people) {
                bWriter.append(p.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fisier inexistent sau format incorect.");
        }
    }

    /**
     * Appends memory-saved data of apartments into the apartments.csv file
     * 
     * @throws IOException if file does not exist / cannot be accessed / written
     *                     into
     */
    private void saveApartments() throws IOException {
        String apartmentsFilePath = this.dbFilePath + "\\apartments.csv";
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(apartmentsFilePath))) {
            for (Apartment a : apartments) {
                bWriter.append(a.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fisier inexistent sau format incorect.");
        }
    }

    /**
     * Loads data from Database folder into memory
     * 
     * @throws IOException if files do not exist / cannot be accessed
     */
    public void loadFromFile() throws IOException {
        String peopleFilePath = this.dbFilePath + "\\people.csv";
        String apartmentsFilePath = this.dbFilePath + "\\apartments.csv";

        // People loading
        FileReader fReader = new FileReader(peopleFilePath);
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(",");
                this.create(new Person(data));
            }
        }

        // Apartments loading
        fReader = new FileReader(apartmentsFilePath);
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(",");
                this.create(new Apartment(data));
            }
        }
    }

    /**
     * Creates and saves a person
     * 
     * @param p person to be created
     */
    public void create(Person p) {
        try {
            this.people.add(p);
            this.savePeople();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and saves a apartment
     * 
     * @param a apartment to be created
     */
    public void create(Apartment a) {
        try {
            this.apartments.add(a);
            this.saveApartments();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a person saved in memory
     * 
     * @param ID person's identifier
     * @return Person with that ID
     */
    public Person getPerson(int ID) {
        try {
            return this.people.get(ID);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Reads an apartment saved in memory
     * 
     * @param ID apartment's identifier
     * @return Apartment with that ID
     */
    public Apartment getApartment(int ID) {
        try {
            return this.apartments.get(ID);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Getter for the list of people
     * 
     * @return All people saved in memory as an ArrayList
     */
    public ArrayList<Person> getPeople() {
        return this.people;
    }

    /**
     * Getter for the list of apartments
     * 
     * @return All apartments saved in memory as an ArrayList
     */
    public ArrayList<Apartment> getApartments() {
        return this.apartments;
    }

    /**
     * Updates a person and saves changes
     * 
     * @param ID person to be replaced identifier
     * @param p  new person
     */
    public void update(int ID, Person p) {
        this.people.set(ID, p);
        try {
            this.savePeople();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an apartment and saves changes
     * 
     * @param ID apartment to be replaced identifier
     * @param a  new apartment
     */
    public void update(int ID, Apartment a) {
        this.apartments.set(ID, a);
        try {
            this.saveApartments();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Deletes a person and saves changes
     * 
     * @param ID person's identifier
     */
    public void deletePerson(int ID) {
        this.people.remove(ID);
        try {
            this.savePeople();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an apartment and saves changes
     * 
     * @param ID apartment's identifier
     */
    public void deleteApartment(int ID) {
        this.apartments.remove(ID);
        try {
            this.saveApartments();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
