

package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Brand {
    
    private String id = "";
    private String name = "";

    public void setId(String id) {
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", this.id,id);
    }

    public String getId() {
        return id;
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
    
    public Brand() {
        super();
    }
    
    public Brand(String id,String name) {
        this.id=id;
        this.name=name;
    }
    
}
