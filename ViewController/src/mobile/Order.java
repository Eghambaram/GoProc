package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Order {
    private String searchGroupId = "";
    private String reqDate = "";
    private String reqType = "";
    private String amount = "";

    public void setSearchGroupId(String searchGroupId) {
        this.searchGroupId = searchGroupId;
        propertyChangeSupport.firePropertyChange("searchGroupId", this.searchGroupId,searchGroupId);
    }

    public String getSearchGroupId() {
        return searchGroupId;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
        propertyChangeSupport.firePropertyChange("reqDate", this.reqDate,reqDate);
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
        propertyChangeSupport.firePropertyChange("reqType", this.reqType,reqType);
    }

    public String getReqType() {
        return reqType;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        propertyChangeSupport.firePropertyChange("amount", this.amount,amount);
    }

    public String getAmount() {
        return amount;
    }

    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    
    
    public Order() {
        super();
    }
    
    public Order(String searchGroupId,String reqDate,String reqType,String amount) {
    
        this.searchGroupId=searchGroupId;
        this.reqDate=reqDate;
        this.reqType=reqType;
        this.amount=amount;
    }
}
