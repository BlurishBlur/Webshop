package database;

/**
 *
 * @author Niels
 */
public interface Controllable {
    
    String URL = "jdbc:postgresql://127.0.0.1:5432/";
    String USERNAME = "postgres";
    String PASSWORD = "12345";
    
    void connect();
    void disconnect();
    boolean isConnected();
    void getData();
    
}
