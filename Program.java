import java.io.IOException;

import Containers.Repository;
import Service.Service;
import UI.Console;


public class Program {

    // Driver class and method
    public static void main(String[] args) throws IOException {

        Repository repository = new Repository("C:\\Users\\butea\\VSCodeProjects\\Java\\Lab4-Ex4\\Database");
        repository.loadFromFile();

        Service service = new Service(repository);
        Console console = new Console(service);

        console.run();
    }

}
