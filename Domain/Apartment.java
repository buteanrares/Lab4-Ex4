package Domain;

public class Apartment {

    private int ID;
    private int noApartment;
    private String owner;
    private int noResidents;
    private int surface;

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
}
