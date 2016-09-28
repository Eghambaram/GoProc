package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class MultiOrg {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String orgId = "";
    private String orgName = "";

    public void setOrgName(String orgName) {
        this.orgName = orgName;
        propertyChangeSupport.firePropertyChange("orgName", this.orgName,orgName);
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
        propertyChangeSupport.firePropertyChange("orgId", this.orgId,orgId);
    }

    public String getOrgId() {
        return orgId;
    }


    
    public MultiOrg() {
        super();
    }
    public MultiOrg(String orgId, String orgName) {
        this.orgId=orgId;
        this.orgName=orgName;
        
    }
}
