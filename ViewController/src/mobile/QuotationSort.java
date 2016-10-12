package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class QuotationSort {
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String lookupType = "";
    private String lookupCode = "";
    private String meaning = "";

    public void setMeaning(String meaning) {
        this.meaning = meaning;
        propertyChangeSupport.firePropertyChange("meaning", this.meaning,meaning);
    }

    public String getMeaning() {
        return meaning;
    }

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

    public QuotationSort() {
        super();
    }
    public QuotationSort(String lookupType, String lookupCode, String meaning) {
        this.lookupType=lookupType;
        this.lookupCode=lookupCode;
        this.meaning=meaning;
        
    }
}
