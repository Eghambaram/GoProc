package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class DeliverToLocationList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public DeliverToLocationList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public DeliverToLocation[] getDeliverToLocations() {
        DeliverToLocation e[] = null;
        e = (DeliverToLocation[])s_jobs.toArray(new DeliverToLocation[s_jobs.size()]);
        return e;
    }
    
    
    public void getDeliverToLocationList() {
        
        DeliverToLocation j = new DeliverToLocation(); 
        s_jobs.add(j);
        
          
        
    }
}
