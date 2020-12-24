import Containers.Repository;
import Service.Service;
import UI.Console;
import Validator.ModelValidator;

public class Program {

    public static void main(String[] args) {
        ModelValidator modelValidator = new ModelValidator();

        Repository repository = new Repository("C:\\Users\\butea\\VSCodeProjects\\Java\\Lab4-Ex4\\Database");

        Service service = new Service(repository, modelValidator);
        Console console = new Console(service);

        console.run();
    }

}