package database;

/**
 *
 * @author Niels
 */
public class DatabaseDriver implements IDatabase {
    
    private static DatabaseDriver instance = null;
    private Controllable pim, urm;
    
    private DatabaseDriver() {
        pim = new PIM();
        urm = new URM();
    }
    
    @Override
    public void connectPIM() {
        pim.connect();
    }
    
    @Override
    public void connectURM() {
        urm.connect();
    }
    
    @Override
    public void disconnectPIM() {
        pim.disconnect();
    }
    
    @Override
    public void disconnectURM() {
        urm.disconnect();
    }
    
    @Override
    public boolean isPIMConnected() {
        return pim.isConnected();
    }
    
    @Override
    public boolean isURMConnected() {
        return urm.isConnected();
    }
    
    @Override
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
