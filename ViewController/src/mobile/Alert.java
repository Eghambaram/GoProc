package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Alert {
    private String id = "";
    private String title = "";
    private String reason = "";
    private String source = "";
    private String read = "";
    private String type = "";
    private String name = "";

   


    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    
    public void setName(String name) {
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", this.name,name);
    }

    public String getName() {
        return name;
    }
    public void setId(String id) {
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", this.id,id);
    }
    
    public void setType(String type) {
        this.type = type;
        propertyChangeSupport.firePropertyChange("type", this.type,type);
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
    
    public void setTitle(String title) {
        this.title = title;
        propertyChangeSupport.firePropertyChange("title", this.title,title);
    }

    public String getTitle() {
        return title;
    }

    public void setReason(String reason) {
        this.reason = reason;
        propertyChangeSupport.firePropertyChange("reason", this.reason,reason);
    }

    public String getReason() {
        return reason;
    }

    public void setSource(String source) {
        this.source = source;
        propertyChangeSupport.firePropertyChange("source", this.source,source);
    }

    public String getSource() {
        return source;
    }

    public void setRead(String read) {
        this.read = read;
        propertyChangeSupport.firePropertyChange("read", this.read,read);
    }

    public String getRead() {
        return read;
    }

    public Alert() {
        super();
    }
    public Alert(String id,String title,String reason,String source,String read,String type,String name) {
       this.id=id;
       this.title=title;
       this.reason=reason;
       this.source=source;
       this.read=read;
       this.type=type;
       this.name=name;
    }
    
    
}
