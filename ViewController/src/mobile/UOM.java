package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class UOM {
    private String name = "";

    public void setName(String name) {
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", this.name,name);
    }

    public String getName() {
        return name;
    }
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public UOM() {
        super();
    }
    
    public UOM(String name) {
        this.name=name;
    }
}
