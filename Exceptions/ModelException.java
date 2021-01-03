package Exceptions;

public class ModelException extends Exception {

    private static final long serialVersionUID = 1L; // added by default

    public ModelException(String errorMessage) {
        super(errorMessage);
    }
}
