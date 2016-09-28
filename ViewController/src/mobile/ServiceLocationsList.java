package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class ServiceLocationsList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public ServiceLocationsList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public ServiceLocation[] getServiceLocations() {
        ServiceLocation e[] = null;
        e = (ServiceLocation[])s_jobs.toArray(new ServiceLocation[s_jobs.size()]);
        return e;
    }
    
    
    public void getServiceLocationList() {
        
        ServiceLocation j = new ServiceLocation(); 
        s_jobs.add(j);
        
          
        
    }
    
}
