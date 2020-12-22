package Containers;

import Domain.GenericModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class GenericRepository<T extends GenericModel> {
    private ArrayList<T> container = new ArrayList<>();
    protected String filePath;

    protected GenericRepository(String filePath) {
        this.filePath = filePath;
    }

    protected void saveToFile() throws IOException {
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (T t : container) {
                bWriter.write(t.toCSV());
            }
        }
    }

    protected abstract void loadFromFile() throws IOException;

    protected void create(T object) {
        this.container.add(object);
    }

    protected T get(int ID) {
        return this.container.get(ID);
    }

    protected ArrayList<T> getAll() {
        return this.container;
    }

    protected void update(int ID, T newObject) {
        this.container.set(ID, newObject);
    }

    protected void delete(int ID) {
        this.container.remove(ID);
    }

    protected void delete(T object) {
        this.container.remove(object);
    }

}
