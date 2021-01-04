package Exceptions;

public class ModelException extends Exception {

    private static final long serialVersionUID = 1L; // added by default

    /**
     * Parameterized constructor. Basic super() usage for a custom exception
     * 
     * @param errorMessage
     */
    public ModelException(String errorMessage) {
        super(errorMessage);
    }
}
