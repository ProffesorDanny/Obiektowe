package controlersClasses.src;

public class DataFailureException extends RuntimeException {
    public DataFailureException() {
        super();
        System.out.println("Nieprawidłowe dane");
    }
}
