package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class RFQ {
    
    private String rfqId = "";
    private String rfqNo = "";
    private String itemDescription = "";
    private String rfqCloseDate = "";
    private String rfqNeedByDate = "";
    private String rfqDeliverToLocation = "";

  

    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public void setRfqNeedByDate(String rfqNeedByDate) {
        this.rfqNeedByDate = rfqNeedByDate;
        propertyChangeSupport.firePropertyChange("rfqNeedByDate", this.rfqNeedByDate,rfqNeedByDate);
    }

    public String getRfqNeedByDate() {
        return rfqNeedByDate;
    }

   


    public void setRfqId(String rfqId) {
        this.rfqId = rfqId;
        propertyChangeSupport.firePropertyChange("rfqId", this.rfqId,rfqId);
    }

    public String getRfqId() {
        return rfqId;
    }

    public void setRfqNo(String rfqNo) {
        this.rfqNo = rfqNo;
        propertyChangeSupport.firePropertyChange("rfqNo", this.rfqNo,rfqNo);
    }

    public String getRfqNo() {
        return rfqNo;
    }
    
    public void setRfqDeliverToLocation(String rfqDeliverToLocation) {
        this.rfqDeliverToLocation = rfqDeliverToLocation;
    }

    public String getRfqDeliverToLocation() {
        return rfqDeliverToLocation;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        propertyChangeSupport.firePropertyChange("itemDescription", this.itemDescription,itemDescription);
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setRfqCloseDate(String rfqCloseDate) {
        this.rfqCloseDate = rfqCloseDate;
        propertyChangeSupport.firePropertyChange("rfqCloseDate", this.rfqCloseDate,rfqCloseDate);
    }

    public String getRfqCloseDate() {
        return rfqCloseDate;
    }


    public RFQ() {
        super();
    }
    
    public RFQ(String rfqId,String rfqNo,String itemDescription,String rfqCloseDate,String needByDate,String rfqDeliverToLocation) {
    
     this.rfqId=rfqId;
     this.rfqNo=rfqNo;
     this.itemDescription=itemDescription;
     this.rfqCloseDate=rfqCloseDate;
     this.rfqNeedByDate=needByDate;
     this.rfqDeliverToLocation=rfqDeliverToLocation;
    }
    
}
