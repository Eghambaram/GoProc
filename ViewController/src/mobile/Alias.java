package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Alias {
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this); 
    private String name = "";
    private String oracleId = "";
    private String indixId = "";
    private String oracleCategotySeg ="";

    public void setName(String name) {
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", this.name,name);
    }

    public String getName() {
        return name;
    }

    public void setOracleId(String oracleId) {
        this.oracleId = oracleId;
        propertyChangeSupport.firePropertyChange("oracleId", this.oracleId,oracleId);        
    }

    public String getOracleId() {
        return oracleId;
    }

    public void setIndixId(String indixId) {
        this.indixId = indixId;
        propertyChangeSupport.firePropertyChange("indixId", this.indixId,indixId);        
    }

    public String getIndixId() {
        return indixId;
    }

    public void setOracleCategotySeg(String oracleCategotySeg) {
        this.oracleCategotySeg = oracleCategotySeg;
        propertyChangeSupport.firePropertyChange("oracleCategotySeg", this.oracleCategotySeg,oracleCategotySeg);        
    }

    public String getOracleCategotySeg() {
        return oracleCategotySeg;
    }

    public Alias() {
        super();
    }
    public Alias(String name, String oracleId, String indixId, String oracleCategotySeg) {
        this.name=name;
        this.oracleId=oracleId;
        this.indixId=indixId;
        this.oracleCategotySeg=oracleCategotySeg;
    }
}
