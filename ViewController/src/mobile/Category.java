package mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Category {
    
    private String id = "";
    private String parent_id = "";
    private String source = "";
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setId(String id) {
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", this.id,id);
    }

    public String getId() {
        return id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
        propertyChangeSupport.firePropertyChange("parent_id", this.parent_id,parent_id);
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setSource(String source) {
        this.source = source;
        propertyChangeSupport.firePropertyChange("source", this.source,source);
    }

    public String getSource() {
        return source;
    }
    private String name = "";
    
    public Category(String name,String id,String parent_id,String source) {
        this.name=name;
        this.id=id;
        this.parent_id=parent_id;
        this.source=source;
    }

    public void setName(String name) {
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", this.name,name);
    }

    public String getName() {
        return name;
    }

    public Category() {
        super();
    }
}
