import Containers.ApartmentsRepository;
import Containers.PeopleRepository;
import Service.Service;
import UI.Console;
import Validator.ModelValidator;

public class Program {

    public static void main(String[] args) {
        ModelValidator modelValidator = new ModelValidator();

        PeopleRepository peopleRepository = new PeopleRepository(
                "C:\\Users\\butea\\VSCodeProjects\\Java\\Lab4-Ex4\\Database\\people.csv");

        ApartmentsRepository apartmentsRepository = new ApartmentsRepository(
                "C:\\Users\\butea\\VSCodeProjects\\Java\\Lab4-Ex4\\Database\\apartments.csv");

        Service service = new Service(peopleRepository, apartmentsRepository, modelValidator);
        Console console = new Console(service);

        console.run();
    }

}