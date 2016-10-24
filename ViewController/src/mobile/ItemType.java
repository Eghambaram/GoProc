package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ItemType {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private String lineTypeId = "";
    private String lineTypeCode = "";
    private String lineTypeDisc = "";

    public void setLineTypeId(String lineTypeId) {
        this.lineTypeId = lineTypeId;
        propertyChangeSupport.firePropertyChange("lineTypeId", this.lineTypeId,lineTypeId);
    }

    public String getLineTypeId() {
        return lineTypeId;
    }

    public void setLineTypeCode(String lineTypeCode) {
        this.lineTypeCode = lineTypeCode;
        propertyChangeSupport.firePropertyChange("lineTypeCode", this.lineTypeCode,lineTypeCode);
    }

    public String getLineTypeCode() {
        return lineTypeCode;
    }

    public void setLineTypeDisc(String lineTypeDisc) {
        this.lineTypeDisc = lineTypeDisc;
        propertyChangeSupport.firePropertyChange("lineTypeDisc", this.lineTypeDisc,lineTypeDisc);
    }

    public String getLineTypeDisc() {
        return lineTypeDisc;
    }

    public ItemType() {
        super();
    }
    public ItemType(String lineTypeId, String lineTypeCode, String lineTypeDisc) {
        this.lineTypeId=lineTypeId;
        this.lineTypeCode=lineTypeCode;
        this.lineTypeDisc=lineTypeDisc;
    }
}
