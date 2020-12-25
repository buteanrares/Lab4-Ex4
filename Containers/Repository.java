package Containers;

import Domain.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Repository {
    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<Apartment> apartments = new ArrayList<>();
    private String dbFilePath;

    public Repository(String dbFilePath) {
        this.dbFilePath = dbFilePath;
    }

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

    public void loadFromFile() throws IOException {
        String peopleFilePath = this.dbFilePath + "\\people.csv";
        String apartmentsFilePath = this.dbFilePath + "\\apartments.csv";

        FileReader fReader = new FileReader(peopleFilePath);
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(",");
                this.create(new Person(data));
            }
        }

        fReader = new FileReader(apartmentsFilePath);
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(",");
                this.create(new Apartment(data));
            }
        }
    }

    public void create(Person p) {
        try {
            this.people.add(p);
            this.savePeople();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void create(Apartment a) {
        try {
            this.apartments.add(a);
            this.saveApartments();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Person getPerson(int ID) {
        try {
            return this.people.get(ID);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Apartment getApartment(int ID) {
        try {
            return this.apartments.get(ID);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public ArrayList<Person> getPeople() {
        return this.people;
    }

    public ArrayList<Apartment> getApartments() {
        return this.apartments;
    }

    public void update(int ID, Person p) {
        this.people.set(ID, p);
        try {
            this.savePeople();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void update(int ID, Apartment a) {
        this.apartments.set(ID, a);
        try {
            this.saveApartments();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deletePerson(int ID) {
        this.people.remove(ID);
        try {
            this.savePeople();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteApartment(int ID) {
        this.apartments.remove(ID);
        try {
            this.saveApartments();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
