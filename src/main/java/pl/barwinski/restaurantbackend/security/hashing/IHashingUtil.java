package pl.barwinski.restaurantbackend.security.hashing;

public interface IHashingUtil {
    String hashPassword(String password);
    boolean verifyPassword(String password, String hashedPassword);
}
