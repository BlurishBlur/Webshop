package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Niels
 */
public abstract class AbstractDatabase implements Controllable {
    
    private final String location;
    protected Connection connection = null;

    public AbstractDatabase(String location) {
        this.location = location;
    }

    @Override
    public void connect() {
        System.out.println("Connecting to " + location + " database...");
        try {
            connection = DriverManager.getConnection(URL + location, USERNAME, PASSWORD);
            System.out.println("Successfully connected to " + location + " database.");
        }
        catch(SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from " + location + " database...");
        try {
            connection.close();
            System.out.println("Successfully disconnected from " + location + " database.");
        }
        catch(SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }
}
