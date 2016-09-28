package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class RFQList {
    
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    
    public RFQList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public RFQ[] getRFQ() {
        RFQ e[] = null;
        e = (RFQ[])s_jobs.toArray(new RFQ[s_jobs.size()]);
        return e;
    }
    
    
    public void getRFQList() {
        
        RFQ j = new RFQ(); 
        s_jobs.add(j);
        
          
        
    }
    
    
}
