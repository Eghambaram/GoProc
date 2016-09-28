package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class RequisitionLineItem {
    private String requisitionLineNo = "";
    private String productTitle = "";
    private String quantity = "";
    private String deliverToLocation = "";
    private String unitPrice = "";
    private String currencyCode = "";
    private String uom = "";
    private String needByDate = "";
    private String lineTotal = "";
    private String category = "";
    private String poNo = "";
    private String vendorName = "";
    private String vendorSite = "";
    private String costCenter = "";
    private String costCenterDescription = "";
    private String lineType = "";
    private String reqHeaderId="";
    private String reqLineId="";
    private String showReqSection="";
    private String showRfqSection="";

    public void setShowReqSection(String showReqSection) {
        this.showReqSection = showReqSection;
        propertyChangeSupport.firePropertyChange("showReqSection", this.showReqSection,showReqSection);
    }

    public String getShowReqSection() {
        return showReqSection;
    }

    public void setShowRfqSection(String showRfqSection) {
        this.showRfqSection = showRfqSection;
        propertyChangeSupport.firePropertyChange("showRfqSection", this.showRfqSection,showRfqSection);
    }

    public String getShowRfqSection() {
        return showRfqSection;
    }
    private String reqNum="";

    public void setReqNum(String reqNum) {
        this.reqNum = reqNum;
        propertyChangeSupport.firePropertyChange("reqNum", this.reqNum,reqNum);
    }

    public String getReqNum() {
        return reqNum;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
        propertyChangeSupport.firePropertyChange("reqStatus", this.reqStatus,reqStatus);
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setRfqNum(String rfqNum) {
        this.rfqNum = rfqNum;
        propertyChangeSupport.firePropertyChange("rfqNum", this.rfqNum,rfqNum);
    }

    public String getRfqNum() {
        return rfqNum;
    }

    public void setRfqStatus(String rfqStatus) {
        this.rfqStatus = rfqStatus;
        propertyChangeSupport.firePropertyChange("rfqStatus", this.rfqStatus,rfqStatus);
    }

    public String getRfqStatus() {
        return rfqStatus;
    }

    public void setRfqHeaderId(String rfqHeaderId) {
        this.rfqHeaderId = rfqHeaderId;
        propertyChangeSupport.firePropertyChange("rfqHeaderId", this.rfqHeaderId,rfqHeaderId);
    }

    public String getRfqHeaderId() {
        return rfqHeaderId;
    }
    private String reqStatus="";
    private String rfqNum="";
    private String rfqStatus="";
    private String rfqHeaderId="";
    

    public void setReqHeaderId(String reqHeaderId) {
        this.reqHeaderId = reqHeaderId;
        propertyChangeSupport.firePropertyChange("reqHeaderId", this.reqHeaderId,reqHeaderId);
    }

    public String getReqHeaderId() {
        return reqHeaderId;
    }

    public void setReqLineId(String reqLineId) {
        this.reqLineId = reqLineId;
        propertyChangeSupport.firePropertyChange("reqLineId", this.reqLineId,reqLineId);
    }

    public String getReqLineId() {
        return reqLineId;
    }
   

    public void setLineType(String lineType) {
        this.lineType = lineType;
        propertyChangeSupport.firePropertyChange("lineType", this.lineType,lineType);
    }

    public String getLineType() {
        return lineType;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
        propertyChangeSupport.firePropertyChange("vendorName", this.vendorName,vendorName);
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorSite(String vendorSite) {
        this.vendorSite = vendorSite;
        propertyChangeSupport.firePropertyChange("vendorSite", this.vendorSite,vendorSite);
    }

    public String getVendorSite() {
        return vendorSite;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
        propertyChangeSupport.firePropertyChange("costCenter", this.costCenter,costCenter);
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenterDescription(String costCenterDescription) {
        this.costCenterDescription = costCenterDescription;
        propertyChangeSupport.firePropertyChange("costCenterDescription", this.costCenterDescription,costCenterDescription);
    }

    public String getCostCenterDescription() {
        return costCenterDescription;
    }
   
    
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setRequisitionLineNo(String requisitionLineNo) {
        this.requisitionLineNo = requisitionLineNo;
        propertyChangeSupport.firePropertyChange("requisitionLineNo", this.requisitionLineNo,requisitionLineNo);
    }

    public String getRequisitionLineNo() {
        return requisitionLineNo;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
        propertyChangeSupport.firePropertyChange("productTitle", this.productTitle,productTitle);
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        propertyChangeSupport.firePropertyChange("quantity", this.quantity,quantity);
    }

    public String getQuantity() {
        return quantity;
    }

    public void setDeliverToLocation(String deliverToLocation) {
        this.deliverToLocation = deliverToLocation;
        propertyChangeSupport.firePropertyChange("deliverToLocation", this.deliverToLocation,deliverToLocation);
    }

    public String getDeliverToLocation() {
        return deliverToLocation;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        propertyChangeSupport.firePropertyChange("unitPrice", this.unitPrice,unitPrice);
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        propertyChangeSupport.firePropertyChange("currencyCode", this.currencyCode,currencyCode);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setUom(String uom) {
        this.uom = uom;
        propertyChangeSupport.firePropertyChange("uom", this.uom,uom);
    }

    public String getUom() {
        return uom;
    }

    public void setNeedByDate(String needByDate) {
        this.needByDate = needByDate;
        propertyChangeSupport.firePropertyChange("needByDate", this.needByDate,needByDate);
    }

    public String getNeedByDate() {
        return needByDate;
    }

    public void setLineTotal(String lineTotal) {
        this.lineTotal = lineTotal;
        propertyChangeSupport.firePropertyChange("lineTotal", this.lineTotal,lineTotal);
    }

    public String getLineTotal() {
        return lineTotal;
    }

    public void setCategory(String category) {
        this.category = category;
        propertyChangeSupport.firePropertyChange("category", this.category,category);
    }

    public String getCategory() {
        return category;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
        propertyChangeSupport.firePropertyChange("poNo", this.poNo,poNo);
    }

    public String getPoNo() {
        return poNo;
    }


    public RequisitionLineItem() {
        super();
    }
    
    public RequisitionLineItem(String requisitionLineNo,String productTitle,String quantity,String deliverToLocation,String unitPrice,String currencyCode,String uom,String needByDate,String lineTotal,String category,String poNo,String vendorName,String vendorSite,String costCenter,String costCenterDescription,String lineType,String reqHeaderId,String reqLineId,String reqNum,String reqStatus,String rfqNum,String rfqStatus,String rfqHeaderId,String showReqSection,String showRfqSection) {
       
        this.requisitionLineNo = requisitionLineNo;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.deliverToLocation = deliverToLocation;
        this.unitPrice = unitPrice;
        this.currencyCode = currencyCode;
        this.uom = uom;
        this.needByDate = needByDate;
        this.lineTotal = lineTotal;
        this.category = category;
        this. poNo = poNo;
        this.vendorName = vendorName;
        this.vendorSite = vendorSite;
        this.costCenter =costCenter ;
        this.costCenterDescription = costCenterDescription;
        this.lineType =lineType;
        this.reqHeaderId=reqHeaderId;
        this.reqLineId=reqLineId;
        this.reqNum=reqNum;
        this.reqStatus=reqStatus;
        this.rfqNum=rfqNum;
        this.rfqStatus=rfqStatus;
        this.rfqHeaderId=rfqHeaderId;
        this.showReqSection=showReqSection;
        this.showRfqSection=showRfqSection;
    }
    
    
}
