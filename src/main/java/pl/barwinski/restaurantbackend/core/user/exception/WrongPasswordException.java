package pl.barwinski.restaurantbackend.core.user.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Wrong password.");
    }
}