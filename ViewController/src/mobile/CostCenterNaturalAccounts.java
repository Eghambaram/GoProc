package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;

public class CostCenterNaturalAccounts {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String costCenter = "";
    private String naturalAccount = "";
    private String costCenterDisc = "";
    private String naturalAccountDisc = "";
    private String DispValue = "";
    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
        propertyChangeSupport.firePropertyChange("costCenter", this.costCenter,costCenter);
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setNaturalAccount(String naturalAccount) {
        this.naturalAccount = naturalAccount;
        propertyChangeSupport.firePropertyChange("naturalAccount", this.naturalAccount,naturalAccount);
    }

    public String getNaturalAccount() {
        return naturalAccount;
    }

    public void setCostCenterDisc(String costCenterDisc) {
        this.costCenterDisc = costCenterDisc;
        propertyChangeSupport.firePropertyChange("costCenterDisc", this.costCenterDisc,costCenterDisc);
    }

    public String getCostCenterDisc() {
        return costCenterDisc;
    }

    public void setNaturalAccountDisc(String naturalAccountDisc) {
        this.naturalAccountDisc = naturalAccountDisc;
        propertyChangeSupport.firePropertyChange("naturalAccountDisc", this.naturalAccountDisc,naturalAccountDisc);
    }

    public String getNaturalAccountDisc() {
        return naturalAccountDisc;
    }

    public void setDispValue(String DispValue) {
        this.DispValue = DispValue;
        propertyChangeSupport.firePropertyChange("DispValue", this.DispValue,DispValue);
    }

    public String getDispValue() {
        return DispValue;
    }
    
    public CostCenterNaturalAccounts() {
        super();
    }
    public CostCenterNaturalAccounts(String costCenter, String naturalAccount, String costCenterDisc, String naturalAccountDisc, String DispValue) {
        this.costCenter=costCenter;
        this.naturalAccount=naturalAccount;
        this.costCenterDisc=costCenterDisc;
        this.naturalAccountDisc=naturalAccountDisc;
        this.DispValue=DispValue;
    }
}
