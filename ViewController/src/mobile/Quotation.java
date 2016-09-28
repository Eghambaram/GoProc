package mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Quotation {
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public String rfqId="";
    public String itemDescription="";
    public String quotationId="";
    public String quotationNo="";
    public String quotationLineId="";
    public String quotationLineNo="";
    public String vendorId="";
    public String vendorName="";
    public String quantity="";
    public String uom="";
    public String promiseDate="";
    public String price="";
    public String currencyCode="";
    public String supplierQuotationNo="";
    public String selected="";
    public String needByDate="";

    public void setNeedByDate(String needByDate) {
        this.needByDate = needByDate;
        propertyChangeSupport.firePropertyChange("needByDate", this.needByDate,needByDate);
    }

    public String getNeedByDate() {
        return needByDate;
    }

    public void setSelected(String selected) {
        this.selected = selected;
        propertyChangeSupport.firePropertyChange("selected", this.selected,selected);
    }

    public String getSelected() {
        return selected;
    }

    public void setRfqId(String rfqId) {
        this.rfqId = rfqId;
        propertyChangeSupport.firePropertyChange("rfqId", this.rfqId,rfqId);
    }

    public String getRfqId() {
        return rfqId;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        propertyChangeSupport.firePropertyChange("itemDescription", this.itemDescription,itemDescription);
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
        propertyChangeSupport.firePropertyChange("quotationId", this.quotationId,quotationId);
    }

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationNo(String quotationNo) {
        this.quotationNo = quotationNo;
        propertyChangeSupport.firePropertyChange("quotationNo", this.quotationNo,quotationNo);
    }

    public String getQuotationNo() {
        return quotationNo;
    }

    public void setQuotationLineId(String quotationLineId) {
        this.quotationLineId = quotationLineId;
        propertyChangeSupport.firePropertyChange("quotationLineId", this.quotationLineId,quotationLineId);
    }

    public String getQuotationLineId() {
        return quotationLineId;
    }

    public void setQuotationLineNo(String quotationLineNo) {
        this.quotationLineNo = quotationLineNo;
        propertyChangeSupport.firePropertyChange("quotationLineNo", this.quotationLineNo,quotationLineNo);
    }

    public String getQuotationLineNo() {
        return quotationLineNo;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
        propertyChangeSupport.firePropertyChange("vendorId", this.vendorId,vendorId);
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
        propertyChangeSupport.firePropertyChange("vendorName", this.vendorName,vendorName);
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        propertyChangeSupport.firePropertyChange("quantity", this.quantity,quantity);
    }

    public String getQuantity() {
        return quantity;
    }

    public void setUom(String uom) {
        this.uom = uom;
        propertyChangeSupport.firePropertyChange("uom", this.uom,uom);
    }

    public String getUom() {
        return uom;
    }

    public void setPromiseDate(String promiseDate) {
        this.promiseDate = promiseDate;
        propertyChangeSupport.firePropertyChange("promiseDate", this.promiseDate,promiseDate);
    }

    public String getPromiseDate() {
        return promiseDate;
    }

    public void setPrice(String price) {
        this.price = price;
        propertyChangeSupport.firePropertyChange("price", this.price,price);
    }

    public String getPrice() {
        return price;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        propertyChangeSupport.firePropertyChange("currencyCode", this.currencyCode,currencyCode);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setSupplierQuotationNo(String supplierQuotationNo) {
        this.supplierQuotationNo = supplierQuotationNo;
        propertyChangeSupport.firePropertyChange("supplierQuotationNo", this.supplierQuotationNo,supplierQuotationNo);
    }

    public String getSupplierQuotationNo() {
        return supplierQuotationNo;
    }

    public Quotation() {
        super();
    }
    
    public Quotation(String rfqId,String itemDescription,String quotationId,String quotationNo,String quotationLineId,String quotationLineNo,String vendorId,String vendorName,String quantity,String uom,String promiseDate,String price,String currencyCode,String supplierQuotationNo,String selected,String needByDate) {
       this.rfqId=rfqId;
        this.itemDescription=itemDescription;
        this.quotationId=quotationId;
        this.quotationNo=quotationNo;
        this.quotationLineId=quotationLineId;
        this.quotationLineNo=quotationLineNo;
        this.vendorId=vendorId;
        this.vendorName=vendorName;
        this.quantity=quantity;
        this.uom=uom;
        this.promiseDate=promiseDate;
        this.price=price;
        this.currencyCode=currencyCode;
        this.supplierQuotationNo=supplierQuotationNo;    
        this.selected=selected;
        this.needByDate=needByDate;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }
    
}
