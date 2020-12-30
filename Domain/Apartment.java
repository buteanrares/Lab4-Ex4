package Domain;

public class Apartment {

    // Apartment's fields
    private Integer ID;
    private int noApartment;
    private String owner;
    private int noResidents;
    private int surface;

    // Default constructor
    public Apartment() {
        this.ID = -1;
        this.noApartment = -1;
        this.owner = "noowner";
        this.noResidents = -1;
        this.surface = -1;
    }

    /**
     * Parameterized constructor. Parameters are self explanatory
     * 
     * @param ID
     * @param noApartment
     * @param owner
     * @param noResidents
     * @param surface
     */
    public Apartment(Integer ID, int noApartment, String owner, int noResidents, int surface) {
        this.ID = ID;
        this.noApartment = noApartment;
        this.owner = owner;
        this.noResidents = noResidents;
        this.surface = surface;
    }

    /**
     * Parameterized constructor
     * 
     * @param data - String array containing apartment data
     */
    public Apartment(String[] data) {
        this.ID = Integer.parseInt(data[0]);
        this.noApartment = Integer.parseInt(data[1]);
        this.owner = data[2];
        this.noResidents = Integer.parseInt(data[3]);
        this.surface = Integer.parseInt(data[4]);
    }

    /**
     * Getter for ID
     * 
     * @return apartment's ID
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * Getter for number of the apartment
     * 
     * @return number of the apartment
     */
    public int getNoApartment() {
        return this.noApartment;
    }

    /**
     * Setter for number of the apartment
     * 
     * @param newNoApartment new apartment number
     */
    public void setNoApartment(int newNoApartment) {
        this.noApartment = newNoApartment;
    }

    /**
     * Getter for apartment's owner
     * 
     * @return apartment's owner
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Setter for apartment's owenr
     * 
     * @param newOwner apartment's new owner
     */
    public void setOwner(String newOwner) {
        this.owner = newOwner;
    }

    /**
     * Getter for the number of residents
     * 
     * @return number of residents
     */
    public int getNoResidents() {
        return this.noResidents;
    }

    /**
     * Setter for the number of residents
     * 
     * @param newNoResidents new number of residents
     */
    public void setNoResidents(int newNoResidents) {
        this.noResidents = newNoResidents;
    }

    /**
     * Getter for the surface
     * 
     * @return surface of the apartment
     */
    public int getSurface() {
        return this.surface;
    }

    /**
     * Setter for the surface
     * 
     * @param newSurface new apartment's surface
     */
    public void setSurface(int newSurface) {
        this.surface = newSurface;
    }

    /**
     * Converts apartment's data as a CSV type string
     * 
     * @return apartment's data as a CSV type string
     */
    public String toCSV() {
        return ID + "," + noApartment + "," + owner + "," + noResidents + "," + surface;
    }
}
