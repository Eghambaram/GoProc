package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Supplier {
    private String name = "";
    private Object[] newVal;
    

    public void setNewVal(Object[] newVal) {
        this.newVal = newVal;
        propertyChangeSupport.firePropertyChange("newVal", this.newVal,newVal);
    }

    public Object[] getNewVal() {
        return newVal;
    }

    public void setName(String name) {
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", this.name,name);
    }

    public String getName() {
        return name;
    }
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public Supplier() {
       super();
    }
    
    public Supplier(String name) {
        this.name=name;
    }
}
