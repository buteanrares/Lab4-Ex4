package Domain;

public abstract class GenericModel {
    protected int ID;

    GenericModel(int ID) {
        this.ID = ID;
    }

    GenericModel() {
        this.ID = -1;
    }

    public abstract String toCSV();
}
