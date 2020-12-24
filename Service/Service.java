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

    public Apartment getNoApartment(int ID) {
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

}
