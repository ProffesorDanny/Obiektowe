package controlersClasses.src;

public class OpenException extends RuntimeException {
    public OpenException() {
        super();
        System.out.println("Zamkniete");
    }
}
