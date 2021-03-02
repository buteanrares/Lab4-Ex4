package Service;

import Containers.Repository;
import Domain.Person;
import Exceptions.ModelException;
import Domain.Apartment;
import Validator.ModelValidator;

import java.util.Collection;
import java.util.Random;


public class Service {

    // Service fields
    private Repository repository;
    private ModelValidator modelValidator = new ModelValidator(this);

    /**
     * Parameterized constructor. Parameters are self-explanatory
     * 
     * @param repository
     * @param modelValidator
     */
    public Service(Repository repository) {
        this.repository = repository;
    }


    /**
     * Creates a person and sends it to repository for further processing.
     * Parameters are self-explanatory
     * 
     * @param forename
     * @param surname
     * @param noApartment
     * @param birthdate
     * @param job
     * @throws ModelException - if model doesnt meet it's requirements (validation
     *                            fails)
     */
    public void createPerson(String forename, String surname, int noApartment, String birthdate, String job)
            throws ModelException {
        Person person = new Person(generatePersonID(), forename, surname, noApartment, birthdate, job);

        this.modelValidator.validate(person);
        this.repository.create(person);

        Apartment apartment = this.getApartmentByNumber(person.getNoApartment());
        if (apartment.getOwner().equals("fara"))
            apartment.setOwner(person.getForename() + " " + person.getSurname());
        apartment.setNoResidents(apartment.getNoResidents() + 1);
        this.repository.update(apartment.getID(), apartment);
    }


    /**
     * Creates an apartment and sends it to repository for further processing.
     * Parameters are self-explanatory
     * 
     * @param noApartment
     * @param owner
     * @param noResidents
     * @param surface
     * @throws ModelException - if model doesnt meet it's requirements (validation
     *                            fails)
     */
    public void createApartment(int noApartment, String owner, int noResidents, int surface) throws ModelException {
        Apartment apartment = new Apartment(generateApartmentID(), noApartment, owner, noResidents, surface);

        this.modelValidator.validate(apartment);
        this.repository.create(apartment);
    }


    /**
     * Reads a specific person
     * 
     * @param ID person's identifier
     * @return person with that ID
     */
    public Person getPerson(Integer ID) {
        return this.repository.getPerson(ID);
    }


    /**
     * Reads a specific apartment
     * 
     * @param ID apartment's identifier
     * @return apartment with that ID
     */
    public Apartment getApartment(Integer ID) {
        return this.repository.getApartment(ID);
    }


    public Apartment getApartmentByNumber(int noApartment) {
        for (Apartment apartment : this.repository.getApartments().values()) {
            if (apartment.getNoApartment() == noApartment)
                return apartment;
        }
        return new Apartment();
    }


    /**
     * Updates a person and sends it to repository for further processing.
     * Parameters are self-explanatory
     * 
     * @param ID
     * @param newForename
     * @param newSurname
     * @param newNoApartment
     * @param newBirthdate
     * @param newJob
     * @throws ModelException - if model doesnt meet it's requirements (validation
     *                            fails)
     */
    public void updatePerson(Integer ID, String newForename, String newSurname, int newNoApartment, String newBirthdate,
            String newJob) throws ModelException {

        Person oldPerson = this.repository.getPerson(ID);
        Person person = new Person(ID, newForename, newSurname, newNoApartment, newBirthdate, newJob);

        this.modelValidator.validate(person);
        this.repository.update(ID, person);

        if (oldPerson.getNoApartment() != person.getNoApartment()) {
            Apartment oldApartment = this.getApartmentByNumber(oldPerson.getNoApartment());
            Apartment newApartment = this.getApartmentByNumber(person.getNoApartment());

            oldApartment.setNoResidents(oldApartment.getNoResidents() - 1);
            newApartment.setNoResidents(newApartment.getNoResidents() + 1);

            if (oldApartment.getOwner().equals(oldPerson.getForename() + " " + oldPerson.getSurname()))
                oldApartment.setOwner(this.findNewOwner(oldApartment, oldPerson));

            if (newApartment.getOwner().equals("fara"))
                newApartment.setOwner(person.getForename() + " " + person.getSurname());

            this.repository.update(oldApartment.getID(), oldApartment);
            this.repository.update(newApartment.getID(), newApartment);
        }
    }


    /**
     * Updates an apartment and sends it to repository for further processing.
     * Parameters are self-explanatory
     * 
     * @param ID
     * @param newNoApartment
     * @param newOwner
     * @param newNoResidents
     * @param newSurface
     * @throws ModelException - if model doesnt meet it's requirements (validation
     *                            fails)
     */
    public void updateApartment(Integer ID, int newNoApartment, String newOwner, int newNoResidents, int newSurface)
            throws ModelException {
        Apartment oldApartment = this.getApartment(ID);
        Apartment newApartment = new Apartment(ID, newNoApartment, newOwner, newNoResidents, newSurface);

        this.modelValidator.validate(newApartment);

        if (oldApartment.getNoApartment() != newApartment.getNoApartment()) {
            Collection<Person> peopleCopy = this.repository.getPeople().values();
            for (Person person : peopleCopy) {
                if (person.getNoApartment() == oldApartment.getNoApartment()) {
                    person.setNoApartment(newNoApartment);
                    this.repository.update(person.getID(), person);
                }
            }
        }

        if (!oldApartment.getOwner().equals(newApartment.getOwner())) {
            Person movingPerson = this.repository.getPerson(newApartment.getOwner());
            Apartment apartment = this.repository.getApartmentByNumber(movingPerson.getNoApartment());

            apartment.setNoResidents(apartment.getNoResidents() - 1);

            if (apartment.getOwner().equals(movingPerson.getForename() + " " + movingPerson.getSurname())) {
                apartment.setOwner(this.findNewOwner(apartment, movingPerson));
                this.repository.update(apartment.getID(), apartment);
            }

            movingPerson.setNoApartment(newApartment.getNoApartment());
            this.repository.update(movingPerson.getID(), movingPerson);

            newApartment.setNoResidents(newApartment.getNoResidents() + 1);
        }

        this.repository.update(ID, newApartment);
    }


    /**
     * Deletes a person by ID, calling repository
     * 
     * @param ID person's ID
     */
    public void deletePerson(Integer ID) {
        Person deletedPerson = this.repository.getPerson(ID);

        this.repository.deletePerson(ID);

        Apartment apartment = this.getApartmentByNumber(deletedPerson.getNoApartment());
        if (apartment.getOwner().equals(deletedPerson.getForename() + " " + deletedPerson.getSurname())) {
            apartment.setOwner(this.findNewOwner(apartment, deletedPerson));
        }
        apartment.setNoResidents(apartment.getNoResidents() - 1);

        this.repository.update(apartment.getID(), apartment);
    }


    /**
     * Deletes an apartment by ID, calling repository
     * 
     * @param ID
     */
    public void deleteApartment(Integer ID) {
        Apartment deletedApartment = this.repository.getApartment(ID);

        this.repository.deleteApartment(ID);

        Collection<Person> peopleCopy = this.repository.getPeople().values();
        for (Person person : peopleCopy) {
            if (person.getNoApartment() == deletedApartment.getNoApartment())
                this.repository.deletePerson(person.getID());
        }
    }


    /**
     * Generates a available ID for a new person
     * 
     * @return person available ID
     */
    private Integer generatePersonID() {
        int ID = 1;
        while (this.repository.getPerson(ID) != null) {
            ID++;
        }
        return ID;
    }


    /**
     * Generates a available ID for a new apartment
     * 
     * @return apartment available ID
     */
    private Integer generateApartmentID() {
        int ID = 1;
        while (this.repository.getApartment(ID) != null) {
            ID++;
        }
        return ID;
    }


    /**
     * Generates a table-like String containing person information
     * 
     * @return table-like String
     */
    public String generatePeopleTable() {
        String header = String.format("%-5s | %-15s | %-15s | %-20s | %-15s | %-15s", "ID", "Nume", "Prenume",
                "Nr. apartament", "Zi nastere", "Job");
        String output = header + "\n";
        for (Person person : this.repository.getPeople().values()) {
            String formattedPerson = String.format("%5s | %-15s | %-15s | %20s | %-15s | %-15s", person.getID(),
                    person.getSurname(), person.getForename(), person.getNoApartment(), person.getBirhtdate(),
                    person.getJob());
            output += formattedPerson + "\n";
        }
        return output;
    }


    /**
     * Generates a table-like String containing apartment information
     * 
     * @return table-like String
     */
    public String generateApartmentsTable() {
        String header = String.format("%-5s | %-20s | %-25s | %-13s | %-9s", "ID", "Nr. apartament", "Proprietar",
                "Nr. locatari", "Suprafata");
        String output = header + "\n";
        for (Apartment apartment : this.repository.getApartments().values()) {
            String formattedApartment = String.format("%5s | %20s | %-25s | %13s | %9s", apartment.getID(),
                    apartment.getNoApartment(), apartment.getOwner(), apartment.getNoResidents(),
                    apartment.getSurface());
            output += formattedApartment += "\n";
        }
        return output;
    }


    /**
     * Generates a table-like String containing tax information for each inhabited
     * apartment
     * 
     * @param month - int, seed for random nuber generator and the month to generate
     *                  taxes for
     * @return table-like String
     */
    public String generateTaxesTable(int month) {
        Random RNG = new Random(month);
        int[] internetTaxArray = { 60, 65, 70, 75, 80, 85 };

        String header = String.format("%-14s | %-20s | %-6s | %-15s | %-6s | %-7s | %-8s | %-6s", "Nr. apartament",
                "Proprietar", "Gaz", "Mentenanta bloc", "Apa", "Curent", "Internet", "Total");
        String output = header + "\n";

        for (Apartment apartment : this.repository.getApartments().values()) {
            if (apartment.getOwner().equals("fara"))
                continue;

            Random apartmentRNG = new Random(apartment.getID());

            double randomMultiplier = (double) (RNG.nextInt(7) + 7) / (double) 10;
            double apartmentMultiplier = (double) (apartmentRNG.nextInt(7) + 7) / (double) 10;
            double residentsMultiplier = apartment.getNoResidents() * 0.5;
            double surfaceMultiplier = apartment.getSurface() * 0.01;
            int gasTax = (int) (85 * randomMultiplier * apartmentMultiplier * surfaceMultiplier);
            int maintenanceTax = 50;
            int waterTax = (int) (60 * randomMultiplier * apartmentMultiplier * residentsMultiplier);
            int electricityTax = (int) (90 * randomMultiplier * apartmentMultiplier * residentsMultiplier);
            int internetTax = internetTaxArray[apartmentRNG.nextInt(internetTaxArray.length)];
            int total = gasTax + maintenanceTax + waterTax + electricityTax + internetTax;

            output += String.format("%14s | %-20s | %6s | %15s | %6s | %7s | %8s | %6s", apartment.getNoApartment(),
                    apartment.getOwner(), gasTax, maintenanceTax, waterTax, electricityTax, internetTax, total) + "\n";
        }

        return output;
    }


    /**
     * Generates a table-like String containing events from the block of flats
     * 
     * @return table-like String
     */
    public String generateInfoTable() {
        int currentInfo = 1;
        String header = String.format("%-7s | %-35s", "Nr. Crt", "Descriere");
        String output = header + "\n";
        for (Person person : this.repository.getPeople().values()) {
            if (person.getJob().equals("pensionar") || person.getJob().equals("somer")) {
                String description = "Locatarul " + person.getSurname() + " " + person.getForename() + " este "
                        + person.getJob();
                output += String.format("%7d | %-35s", currentInfo++, description) + "\n";
            }
        }
        for (Apartment apartment : this.repository.getApartments().values()) {
            if (apartment.getOwner().equals("fara")) {
                String description = "Apartamentul " + apartment.getNoApartment() + " este de vanzare";
                output += String.format("%7d | %-35s", currentInfo++, description) + "\n";
            }
        }
        return output;
    }


    /**
     * Finds a new owner for an apartment. If no owner can be found, the apartment's
     * owner will be none
     * 
     * @param apartment - apartment to find a new owner for
     * @param exOwner   - leaving person
     * @return
     */
    private String findNewOwner(Apartment apartment, Person exOwner) {
        Collection<Person> people = this.repository.getPeople().values();
        people.remove(exOwner);

        for (Person person : people) {
            if (person.getNoApartment() == apartment.getNoApartment()) {
                return person.getForename() + " " + person.getSurname();
            }
        }
        return "fara";
    }
}
