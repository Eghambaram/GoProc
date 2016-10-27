package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;

public class RequestType {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String lookupType = "";
    private String lookupCode = "";
    private String meaning = "";
    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
        propertyChangeSupport.firePropertyChange("lookupType", this.lookupType,lookupType);
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
        propertyChangeSupport.firePropertyChange("lookupCode", this.lookupCode,lookupCode);
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
        propertyChangeSupport.firePropertyChange("meaning", this.meaning,meaning);
    }

    public String getMeaning() {
        return meaning;
    }

    public RequestType() {
        super();
    }
    public RequestType(String lookupType, String lookupCode, String meaning) {
        this.lookupType=lookupType;
        this.lookupCode=lookupCode;
        this.meaning=meaning;
    }
}
