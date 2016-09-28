package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class ContractLookupList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public ContractLookupList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public Lookup[] getContractLookups() {
        Lookup e[] = null;
        e = (Lookup[])s_jobs.toArray(new Lookup[s_jobs.size()]);
        return e;
    }
    
    
    public void getContractLookupList() {
        Lookup j = new Lookup(); 
        s_jobs.add(j);
        
          
        
    }
}
