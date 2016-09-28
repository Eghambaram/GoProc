package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class CostCenter {
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String name = "";
    private String description = "";

    public void setDescription(String description) {
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", this.description,description);
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", this.name,name);
    }

    public String getName() {
        return name;
    }

    public CostCenter() {
        super();
    }
    
    public CostCenter(String name,String description) {
        this.name=name;
        this.description=description;
    }
}
