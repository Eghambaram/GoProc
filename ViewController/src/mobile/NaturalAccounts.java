package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;

public class NaturalAccounts {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String segValue = "";
    private String description = "";
    public void setSegValue(String segValue) {
        this.segValue = segValue;
        propertyChangeSupport.firePropertyChange("segValue", this.segValue,segValue);
    }

    public String getSegValue() {
        return segValue;
    }

    public void setDescription(String description) {
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", this.description,description);
    }

    public String getDescription() {
        return description;
    }
   
    public NaturalAccounts() {
        super();
    }
    public NaturalAccounts(String segValue, String description) {
        this.segValue=segValue;
        this.description=description;
        
    }
}
