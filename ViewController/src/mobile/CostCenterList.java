package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class CostCenterList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public CostCenterList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public CostCenter[] getCostCenters() {
        CostCenter e[] = null;
        e = (CostCenter[])s_jobs.toArray(new CostCenter[s_jobs.size()]);
        return e;
    }
    
    
    public void getCostCenterList() {
        
        CostCenter j = new CostCenter(); 
        s_jobs.add(j);
        
          
        
    }
}
