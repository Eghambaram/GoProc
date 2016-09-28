package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class RequisitionLineItemsList {
    
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    
    public RequisitionLineItemsList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public RequisitionLineItemsList(String a) {
        
    }
    
    
    
    public RequisitionLineItem[] getRequesitionLineItems() {
        RequisitionLineItem e[] = null;
        e = (RequisitionLineItem[])s_jobs.toArray(new RequisitionLineItem[s_jobs.size()]);
        return e;
    }
    
    
    public void getRequesitionLineItemsList() {
        
        RequisitionLineItem j = new RequisitionLineItem(); 
        s_jobs.add(j);
        
          
        
    }
    
    public void addProviderChangeListener(ProviderChangeListener l) {

            providerChangeSupport.addProviderChangeListener(l);

        }

     

        public void removeProviderChangeListener(ProviderChangeListener l) {

            providerChangeSupport.removeProviderChangeListener(l);

        }
    
    
}
