package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Lookup {
    
    public Lookup(){
        super();
    }
    private String type = "";
    private String code = "";
    private String meaning = "";
    private String tag = "";

    public void setType(String type) {
        this.type = type;
        propertyChangeSupport.firePropertyChange("type", this.type,type);
    }

    public String getType() {
        return type;
    }

    public void setCode(String code) {
        this.code = code;
        propertyChangeSupport.firePropertyChange("code", this.code,code);
    }

    public String getCode() {
        return code;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
        propertyChangeSupport.firePropertyChange("meaning", this.meaning,meaning);
    }

    public String getMeaning() {
        return meaning;
    }

    public void setTag(String tag) {
        this.tag = tag;
        propertyChangeSupport.firePropertyChange("tag", this.tag,tag);
    }

    public String getTag() {
        return tag;
    }


    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    
    
    public Lookup(String type,String code,String meaning,String tag) {
        this.type=type;
        this.code=code;
        this.meaning=meaning;
        this.tag=tag;
    }
    
}
