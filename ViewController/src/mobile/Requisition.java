package mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Requisition {
    private String requisitionHeaderId = "";
    private String requisitionNo = "";
    private String rfqNo = "";
    private String rfqStatus = "";
    private String rfqSubmissionDate = "";
    private String rfqDescription = "";
    private String rfqHeaderId = "";
    private String showRfqSection = "";
    private String showRequesitionSection = "";
    private String showRow = "";

    public void setShowRow(String showRow) {
        this.showRow = showRow;
        propertyChangeSupport.firePropertyChange("showRow", this.showRow,showRow);
    }

    public String getShowRow() {
        return showRow;
    }

    public void setShowRequesitionSection(String showRequesitionSection) {
        this.showRequesitionSection = showRequesitionSection;
        propertyChangeSupport.firePropertyChange("showRequesitionSection", this.showRequesitionSection,showRequesitionSection);
    }

    public String getShowRequesitionSection() {
        return showRequesitionSection;
    }

    public void setShowRfqSection(String showRfqSection) {
        this.showRfqSection = showRfqSection;
        propertyChangeSupport.firePropertyChange("showRfqSection", this.showRfqSection,showRfqSection);
    }

    public String getShowRfqSection() {
        return showRfqSection;
    }
    
    

    public void setRfqHeaderId(String rfqHeaderId) {
        this.rfqHeaderId = rfqHeaderId;
        propertyChangeSupport.firePropertyChange("rfqHeaderId", this.rfqHeaderId,rfqHeaderId);
    }

    public String getRfqHeaderId() {
        return rfqHeaderId;
    }

    public void setRfqNo(String rfqNo) {
        this.rfqNo = rfqNo;
        propertyChangeSupport.firePropertyChange("rfqNo", this.rfqNo,rfqNo);
    }

    public String getRfqNo() {
        return rfqNo;
    }

    public void setRfqStatus(String rfqStatus) {
        this.rfqStatus = rfqStatus;
        propertyChangeSupport.firePropertyChange("rfqStatus", this.rfqStatus,rfqStatus);
    }

    public String getRfqStatus() {
        return rfqStatus;
    }

    public void setRfqSubmissionDate(String rfqSubmissionDate) {
        this.rfqSubmissionDate = rfqSubmissionDate;
        propertyChangeSupport.firePropertyChange("rfqSubmissionDate", this.rfqSubmissionDate,rfqSubmissionDate);
    }

    public String getRfqSubmissionDate() {
        return rfqSubmissionDate;
    }

    public void setRfqDescription(String rfqDescription) {
        this.rfqDescription = rfqDescription;
        propertyChangeSupport.firePropertyChange("rfqDescription", this.rfqDescription,rfqDescription);
    }

    public String getRfqDescription() {
        return rfqDescription;
    }

    public void setRequisitionHeaderId(String requisitionHeaderId) {
        
        this.requisitionHeaderId = requisitionHeaderId;
        propertyChangeSupport.firePropertyChange("requisitionHeaderId", this.requisitionHeaderId,requisitionHeaderId);
    }

    public String getRequisitionHeaderId() {
        return requisitionHeaderId;
    }

    public void setRequisitionNo(String requisitionNo) {
        this.requisitionNo = requisitionNo;
        propertyChangeSupport.firePropertyChange("requisitionNo", this.requisitionNo,requisitionNo);
    }

    public String getRequisitionNo() {
        return requisitionNo;
    }
    private String createdDate = "";
    private String status = "";
    private String totalAmount = "";

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        propertyChangeSupport.firePropertyChange("createdDate", this.createdDate,createdDate);
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setStatus(String status) {
        this.status = status;
        propertyChangeSupport.firePropertyChange("status", this.status,status);
    }

    public String getStatus() {
        return status;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        propertyChangeSupport.firePropertyChange("totalAmount", this.totalAmount,totalAmount);
    }
    
    

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setProviderChangeSupport(ProviderChangeSupport providerChangeSupport) {
        this.providerChangeSupport = providerChangeSupport;
    }

    public ProviderChangeSupport getProviderChangeSupport() {
        return providerChangeSupport;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public Requisition() {
        super();
    }
    
    
    public Requisition(String requisitionHeaderId,String requisitionNo,String createdDate,String status,String totalAmount,String rfqNo,String rfqStatus,String rfqSubmissionDate,String rfqDescription,String rfqHeaderId,String showRfqSection,String showRequesitionSection,String showRow) {
        this.requisitionHeaderId = requisitionHeaderId;
        this.requisitionNo = requisitionNo;
        this.createdDate = createdDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.rfqNo = rfqNo;
        this.rfqStatus = rfqStatus;
        this.rfqSubmissionDate = rfqSubmissionDate;
        this.rfqDescription = rfqDescription;
        this.rfqHeaderId=rfqHeaderId;
        this.showRfqSection=showRfqSection;
        this.showRequesitionSection=showRequesitionSection;
        this.showRow=showRow;
    }

  
}
