package Service;

import Containers.Repository;
import Domain.Person;
import Domain.Apartment;
import Validator.ModelValidator;

public class Service {

    private Repository repository;
    private ModelValidator modelValidator;

    public Service(Repository repository, ModelValidator modelValidator) {
        this.repository = repository;
        this.modelValidator = modelValidator;
    }

    public void createPerson(String forename, String surname, int noApartment, String birthdate, String job) {
        Person person = new Person(generatePersonID(), forename, surname, noApartment, birthdate, job);

        this.modelValidator.validate(person);
        this.repository.create(person);
    }

    public void createApartment(int noApartment, String owner, int noResidents, int surface) {
        Apartment apartment = new Apartment(generateApartmentID(), noApartment, owner, noResidents, surface);

        this.modelValidator.validate(apartment);
        this.repository.create(apartment);
    }

    public Person getPerson(int ID) {
        return this.repository.getPerson(ID);
    }

    public Apartment getApartment(int ID) {
        return this.repository.getApartment(ID);
    }

    public void updatePerson(int ID, String newForename, String newSurname, int newNoApartment, String newBirthdate,
            String newJob) {
        Person person = new Person(ID, newForename, newSurname, newNoApartment, newBirthdate, newJob);

        this.modelValidator.validate(person);
        this.repository.update(ID, person);
    }

    public void updateApartment(int ID, int newNoApartment, String newOwner, int newNoResidents, int newSurface) {
        Apartment apartment = new Apartment(ID, newNoApartment, newOwner, newNoResidents, newSurface);

        this.modelValidator.validate(apartment);
        this.repository.update(ID, apartment);
    }

    public void deletePerson(int ID) {
        this.repository.deletePerson(ID);
    }

    public void deleteApartment(int ID) {
        this.repository.deleteApartment(ID);
    }

    private int generatePersonID() {
        int ID = 0;
        while (repository.getPerson(ID) != null) {
            ID++;
        }
        return ID;
    }

    private int generateApartmentID() {
        int ID = 0;
        while (repository.getApartment(ID) != null) {
            ID++;
        }
        return ID;
    }

    public String generatePeopleTable() {
        String header = String.format("%-5s | %-15s | %-15s | %-20s | %-15s | %-15s", "ID", "Nume", "Prenume",
                "Nr. apartament", "Zi nstere", "Job");
        String output = header + "\n";
        for (Person person : this.repository.getPeople()) {
            String formattedPerson = String.format("%-5s | %-15s | %-15s | %20s | %-15s | %-15s", person.getID(),
                    person.getSurname(), person.getForename(), person.getNoApartment(), person.getBirhtdate(),
                    person.getJob());
            output += formattedPerson + "\n";
        }
        return output;
    }

    public String generateApartmentsTable() {
        String header = String.format("%-5s | %-20s | %-25s | %-13s | %-9s", "ID", "Nr. apartament", "Proprietar",
                "Nr. locatari", "Suprafata");
        String output = header + "\n";
        for (Apartment apartment : this.repository.getApartments()) {
            String formattedApartment = String.format("%-5s | %20s | %-25s | %13s | %9s", apartment.getID(),
                    apartment.getNoApartment(), apartment.getOwner(), apartment.getNoResidents(),
                    apartment.getSurface());
            output += formattedApartment += "\n";
        }
        return output;
    }

    public String generateInfoTable() {
        int currentInfo = 1;
        String header = String.format("%-7s | %-35s", "Nr. Crt", "Descriere");
        String output = header + "\n";
        for (Person person : this.repository.getPeople()) {
            if (person.getJob().equals("pensionar") || person.getJob().equals("somer")) {
                String description = "Locatarul " + person.getSurname() + " " + person.getForename() + " este "
                        + person.getJob();
                output += String.format("%-7d | %-35s", currentInfo++, description) + "\n";
            }
        }
        for (Apartment apartment : this.repository.getApartments()) {
            if (apartment.getOwner().equals("fara")) {
                String description = "Apartamentul " + apartment.getNoApartment() + " este de vanzare";
                output += String.format("%-7d | %-35s", currentInfo++, description) + "\n";
            }
        }
        return output;
    }
}
