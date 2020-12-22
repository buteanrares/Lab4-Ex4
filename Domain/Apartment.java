package Domain;

public class Apartment extends GenericModel {

    private int noApartment;
    private String owner;
    private int noResidents;
    private int surface;

    public Apartment() {
        this.ID = -1;
        this.noApartment = -1;
        this.owner = "noowner";
        this.noResidents = -1;
        this.surface = -1;
    }

    public Apartment(int ID, int noApartment, String owner, int noResidents, int surface) {
        super(ID);
        this.noApartment = noApartment;
        this.owner = owner;
        this.noResidents = noResidents;
        this.surface = surface;
    }

    public Apartment(String[] data) {
        this.ID = Integer.parseInt(data[0]);
        this.noApartment = Integer.parseInt(data[1]);
        this.owner = data[2];
        this.noResidents = Integer.parseInt(data[3]);
        this.surface = Integer.parseInt(data[4]);
    }

    public int getID() {
        return this.ID;
    }

    public int getNoApartment() {
        return this.noApartment;
    }

    public void setNoApartment(int newNoApartment) {
        this.noApartment = newNoApartment;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String newOwner) {
        this.owner = newOwner;
    }

    public int getNoResidents() {
        return this.noResidents;
    }

    public void setNoResidents(int newNoResidents) {
        this.noResidents = newNoResidents;
    }

    public int getSurface() {
        return this.surface;
    }

    public void setSurface(int newSurface) {
        this.surface = newSurface;
    }

    public String toCSV() {
        return ID + "," + noApartment + "," + owner + "," + noResidents + "," + surface;
    }
}
