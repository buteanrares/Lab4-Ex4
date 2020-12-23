package Containers;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import Domain.Person;

public class PeopleRepository extends GenericRepository<Person> {

    public PeopleRepository(String filePath) {
        super(filePath);
    }

    @Override
    protected void loadFromFile() throws IOException {
        FileReader fReader = new FileReader(filePath);
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(",");
                this.create(new Person(data));
            }
        }
    }

}
