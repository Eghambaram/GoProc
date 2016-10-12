package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class QuotationSortList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List sort_List = new ArrayList();
    public QuotationSortList() {
        super();
        if (sort_List == null) {
            sort_List = new ArrayList();
        }
    }
    
    public QuotationSort[] getQuotationSort() {
        QuotationSort e[] = null;
        e = (QuotationSort[])sort_List.toArray(new QuotationSort[sort_List.size()]);
        return e;
    }
    
    
    public void getQuotationSortList() {
        
        QuotationSort j = new QuotationSort(); 
        sort_List.add(j);
        
          
        
    }
    }
