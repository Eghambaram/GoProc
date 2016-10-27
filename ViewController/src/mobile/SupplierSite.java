package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SupplierSite {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String vendorSiteId = "";
    private String vendorSiteCode = "";

    public void setVendorSiteId(String vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
        propertyChangeSupport.firePropertyChange("vendorSiteId", this.vendorSiteId,vendorSiteId);     
    }

    public String getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
        propertyChangeSupport.firePropertyChange("vendorSiteCode", this.vendorSiteCode,vendorSiteCode);
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public SupplierSite() {
        super();
    }
    public SupplierSite(String vendorSiteId, String vendorSiteCode) {
        this.vendorSiteId=vendorSiteId;
        this.vendorSiteCode=vendorSiteCode;
    }
}
