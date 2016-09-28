package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class DeliverToLocation {
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String id = "";
    private String code = "";
    private String description = "";

    public void setId(String id) {
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", this.id,id);
    }

    public String getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
        propertyChangeSupport.firePropertyChange("code", this.code,code);
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", this.description,description);
    }

    public String getDescription() {
        return description;
    }

    public DeliverToLocation() {
        super();
    }
    public DeliverToLocation(String id,String code,String description) {
       this.id=id;
       this.code=code;
       this.description=description;
    }
}
