import java.io.IOException;

import Containers.Repository;
import Service.Service;
import UI.Console;
import Validator.ModelValidator;

public class Program {

    // Driver class and method
    public static void main(String[] args) throws IOException {
        ModelValidator modelValidator = new ModelValidator();

        Repository repository = new Repository("C:\\Users\\butea\\VSCodeProjects\\Java\\Lab4-Ex4\\Database");
        repository.loadFromFile();

        Service service = new Service(repository, modelValidator);
        Console console = new Console(service);

        console.run();
    }

}

// FIXME: Tax table values RNG seed is not working

// TODO: Document every method
// TODO: Find some bugs.
// TODO: Fix CRUD interactions between Person-Apartment
