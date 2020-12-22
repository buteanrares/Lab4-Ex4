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

    public void create(T object) {
        this.container.add(object);
    }

    public T get(int ID) {
        return this.container.get(ID);
    }

    public ArrayList<T> getAll() {
        return this.container;
    }

    public void update(int ID, T newObject) {
        this.container.set(ID, newObject);
    }

    public void delete(int ID) {
        this.container.remove(ID);
    }

    public void delete(T object) {
        this.container.remove(object);
    }

}
