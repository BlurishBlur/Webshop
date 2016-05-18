package database;

/**
 *
 * @author Niels
 */
public class DatabaseDriver {
    
    private static DatabaseDriver instance = null;
    private Controllable pim, urm;
    
    private DatabaseDriver() {
        pim = new PIM();
        urm = new URM();
    }
    
    public void connectPIM() {
        pim.connect();
    }
    
    public void connectURM() {
        urm.connect();
    }
    
    public void disconnectPIM() {
        pim.disconnect();
    }
    
    public void disconnectURM() {
        urm.disconnect();
    }
    
    public boolean isPIMConnected() {
        return pim.isConnected();
    }
    
    public boolean isURMConnected() {
        return urm.isConnected();
    }
    
    public void pim() {
        pim.getData();
    }
    
    public static DatabaseDriver getInstance() {
        if(instance == null) {
            instance = new DatabaseDriver();
        }
        return instance;
    }
}
