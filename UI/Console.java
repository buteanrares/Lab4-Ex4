package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import Service.Service;

public class Console {

    // Console fields
    private Scanner scan = new Scanner(System.in);
    private Service service;

    /**
     * Parameterized constructor. Parameters are self-explanatory
     * 
     * @param service
     */
    public Console(Service service) {
        this.service = service;
    }

    // Driver method for Console class
    public void run() {
        handleShowMenu();
        this.handleOption();
    }

    // Displays menu containing available options
    private static void handleShowMenu() {
        System.out.println();
        System.out.println("1.  Adaugati persoana");
        System.out.println("2.  Adaugati apartament");
        System.out.println("3.  Modificati persoana");
        System.out.println("4.  Modificati apartament");
        System.out.println("5.  Stergeti persoana");
        System.out.println("6.  Stergeti apartament");
        System.out.println("7.  Afisare tabel persoane");
        System.out.println("8.  Afisare tabel apartamente");
        System.out.println("9.  Afisare tabel taxe");
        System.out.println("10. Afisare tabel informatii bloc");
        System.out.println("11. Inchide");
    }

    // Handles user's option selection
    private void handleOption() {
        String option = "0";

        while (!option.equals("11")) {
            System.out.print("\nSelectati optiunea: ");
            option = this.scan.next();

            switch (option) {
                case "1":
                    this.handleAddPerson();
                    break;
                case "2":
                    this.handleAddApartment();
                    break;
                case "3":
                    this.handleUpdatePerson();
                    break;
                case "4":
                    this.handleUpdateApartment();
                    break;
                case "5":
                    this.handleDeletePerson();
                    break;
                case "6":
                    this.handleDeleteApartment();
                    break;
                case "7":
                    this.handleShowPeople();
                    break;
                case "8":
                    this.handleShowApartments();
                    break;
                case "9":
                    this.handleShowTaxes();
                    break;
                case "10":
                    this.handleShowInfo();
                    break;
                default:
                    System.out.println("Optiune inexistenta.");
                    break;
            }
            handleShowMenu();
        }
    }

    // Handler for adding a person
    private void handleAddPerson() {
        try {
            System.out.println("Dati atributele persoanei conform modelului");
            System.out.println("prenume,nume,nr. apartament,zi nastere,job");
            this.scan.nextLine(); // consuma \n

            String userInput;
            while (!(userInput = scan.nextLine()).equals("")) {
                String[] personData = userInput.split(",");
                if (personData.length % 5 != 0)
                    throw new IndexOutOfBoundsException();
                String forename = personData[0];
                String surname = personData[1];
                int noApartment = Integer.parseInt(personData[2]);
                String birthdate = personData[3];
                String job = personData[4];
                this.service.createPerson(forename, surname, noApartment, birthdate, job);
            }
        } catch (NumberFormatException e) {
            System.out.println("Conversie nr. apartament esuata.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Datele introduse nu pot alcatui persoane valide.");
        }
    }

    // Handler for adding an apartment
    private void handleAddApartment() {
        try {
            System.out.println("Dati atributele apartamentului conform modelului");
            System.out.println("nr. apartament,suprafata");
            this.scan.nextLine(); // consuma \n

            String userInput;
            while (!(userInput = scan.nextLine()).equals("")) {
                String[] apartmentData = userInput.split(",");
                if (apartmentData.length % 2 != 0)
                    throw new IndexOutOfBoundsException();
                int noApartment = Integer.parseInt(apartmentData[0]);
                int surface = Integer.parseInt(apartmentData[1]);
                this.service.createApartment(noApartment, "fara", 0, surface);
            }
        } catch (NumberFormatException e) {
            System.out.println("Conversie nr. apartament / suprafata esuata.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Datele introduse nu pot alcatui apartamente valide.");
        }
    }

    // Handler for updating a person
    private void handleUpdatePerson() {
        try {
            System.out.print("ID-ul persoanei de modificat: ");
            int ID = this.scan.nextInt();

            System.out.println("Dati atributele persoanei conform modelului");
            System.out.println("prenume,nume,nr. apartament,zi nastere,job");
            this.scan.nextLine(); // consuma \n

            String userInput = this.scan.nextLine();
            String[] personData = userInput.split(",");

            String newForename = personData[0];
            String newSurname = personData[1];
            int newNoApartment = Integer.parseInt(personData[2]);
            String newBirthdate = personData[3];
            String newJob = personData[4];
            this.service.updatePerson(ID, newForename, newSurname, newNoApartment, newBirthdate, newJob);
        } catch (NumberFormatException e) {
            System.out.println("ID-ul trebuie sa fie un numar natural pozitiv.");
        }
    }

    // Handler for updating an apartment
    private void handleUpdateApartment() {
        try {
            System.out.print("ID-ul apartamentului de modificat: ");
            int ID = this.scan.nextInt();

            System.out.println("Dati atributele apartamentului conform modelului");
            System.out.println("nr. apartament,proprietar,suprafata");
            this.scan.nextLine(); // consuma \n

            String userInput = this.scan.nextLine();
            String[] apartmentData = userInput.split(",");

            int newNoApartment = Integer.parseInt(apartmentData[0]);
            String newOwner = apartmentData[1];
            int newSurface = Integer.parseInt(apartmentData[2]);

            int noResidents = this.service.getApartment(ID).getNoResidents();
            this.service.updateApartment(ID, newNoApartment, newOwner, noResidents, newSurface);
        } catch (NumberFormatException e) {
            System.out.println("ID-ul trebuie sa fie un numar natural pozitiv.");

        }
    }

    // Handler for deleting a person
    private void handleDeletePerson() {
        try {
            System.out.print("ID-ul persoanei de sters: ");
            int ID = this.scan.nextInt();

            this.service.deletePerson(ID);
        } catch (NumberFormatException e) {
            System.out.println("ID inexistent sau invalid.");
        }
    }

    // Handler for deleting an apartment
    private void handleDeleteApartment() {
        try {
            System.out.print("ID-ul apartamentului de sters: ");
            int ID = this.scan.nextInt();

            this.service.deleteApartment(ID);
        } catch (NumberFormatException e) {
            System.out.println("ID inexistent sau invalid.");
        }
    }

    // Handler for displaying people
    private void handleShowPeople() {
        try {
            System.out.println("\n" + this.service.generatePeopleTable());
        } catch (Exception e) {
            System.out.println("Eroare??");
        }
    }

    // Handler for displaying apartments
    private void handleShowApartments() {
        try {
            System.out.println("\n" + this.service.generateApartmentsTable());
        } catch (Exception e) {
            System.out.println("Eroare??");
        }
    }

    // Handler for displaying tax information
    private void handleShowTaxes() {
        try {
            System.out.print("Dati luna (nr): ");
            int month = this.scan.nextInt();

            if (month < 1 && month > 12)
                throw new IllegalArgumentException();

            System.out.println("\n" + this.service.generateTaxesTable(month));
        } catch (InputMismatchException e) {
            System.out.println("Informatia data nu poate fi convertita intr-un numar.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dati un numar de la 1 la 12.");
        }
    }

    // Handler for displaying events
    private void handleShowInfo() {
        try {
            System.out.println("\n" + this.service.generateInfoTable());
        } catch (Exception e) {
            System.out.println("Eroare??");
        }
    }

}
