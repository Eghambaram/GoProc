package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class AliasList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public AliasList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public Alias[] getAliass() {
        Alias e[] = null;
        e = (Alias[])s_jobs.toArray(new Alias[s_jobs.size()]);
        return e;
    }
    
    
    public void getAliasList() {
        
        Alias j = new Alias(); 
        s_jobs.add(j);
        
          
        
    }
    }