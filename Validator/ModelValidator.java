package Validator;

import Service.Service;

import java.util.ArrayList;
import Domain.Person;
import Exceptions.ModelException;
import Domain.Apartment;


public class ModelValidator {

    private Service service;

    public ModelValidator(Service service) {
        this.service = service;
    }


    /**
     * Validates a person. Performs model binding and validation
     * 
     * @param person - person to be validated
     * @throws ModelException - if validation fails
     */
    public void validate(Person person) throws ModelException {
        ArrayList<String> errorMessage = new ArrayList<>();

        if (!person.getForename().matches("[a-zA-Z]+"))
            errorMessage.add("Prenumele persoanei este invalid. ");
        if (!person.getSurname().matches("[a-zA-Z]+"))
            errorMessage.add("Numele persoanei este invalid. ");
        try {
            person.isOver18(); // checking person birthdate format and conversion
        } catch (Exception e) {
            errorMessage.add("Data de nastere a persoanei este invalida. Format: yyyy-MM-dd. ");
        }
        if (this.service.getApartment(person.getNoApartment()).getID() == -1)
            errorMessage.add("Apartamentul cu numarul dat nu exista. ");
        else if (!person.isOver18() && this.service.getApartment(person.getNoApartment()).getOwner().equals("fara"))
            errorMessage.add("Persoana este un minor si nu poate detine un apartament. ");
        if (!person.getJob().matches("[a-zA-Z]+"))
            errorMessage.add("Job-ul persoanei este invalid. ");

        if (!errorMessage.isEmpty()) {
            String exceptionMessage = "";
            for (String string : errorMessage) {
                exceptionMessage += string;
            }
            throw new ModelException(exceptionMessage);
        }
    }


    /**
     * Validates an apartment. Performs model binding and validation
     * 
     * @param apartment - apartment to be validated
     * @throws ModelException - if validation fails
     */
    public void validate(Apartment apartment) throws ModelException {
        ArrayList<String> errorMessage = new ArrayList<>();

        if (apartment.getNoApartment() < 1)
            errorMessage.add("Numarul apartamentului trebuie sa fie mai mare sau egal ca 1. ");
        if (apartment.getSurface() < 1)
            errorMessage.add("Suprafata apartamentului este invalida. ");

        if (!errorMessage.isEmpty()) {
            String exceptionMessage = "";
            for (String string : errorMessage) {
                exceptionMessage += string;
            }
            throw new ModelException(exceptionMessage);
        }
    }
}
