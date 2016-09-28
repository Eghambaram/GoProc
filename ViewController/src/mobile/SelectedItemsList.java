package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class SelectedItemsList {
    private static Object tag;
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    private static List items = null;
    public static List items_selected = new ArrayList();
    
    public static List purchase_items = new ArrayList();
    
    
    
    public SelectedItemsList() {
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
      
    }
    
    public SelectedItemsList(String a) {
        
    }
    
    
    
    public SelectedItem[] getAssets() {
        SelectedItem e[] = null;
        e = (SelectedItem[])s_jobs.toArray(new SelectedItem[s_jobs.size()]);
        return e;
    }
    
    
    public void getAssetList() {
        
        SelectedItem j = new SelectedItem(); 
        s_jobs.add(j);
        
          
        
    }
    
    
}
