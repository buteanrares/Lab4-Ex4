package Containers;

import Domain.Apartment;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class ApartmentsRepository extends GenericRepository<Apartment> {

    public ApartmentsRepository(String filePath) {
        super(filePath);
    }

    @Override
    public void loadFromFile() throws IOException {
        FileReader fReader = new FileReader(filePath);
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(",");
                this.create(new Apartment(data));
            }
        }
    }

}
