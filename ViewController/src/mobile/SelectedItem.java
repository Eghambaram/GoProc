package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class SelectedItem {
    private String rowid = "";

    public void setRowid(String rowid) {
        this.rowid = rowid;
        propertyChangeSupport.firePropertyChange("rowid", this.rowid,rowid);
    }

    public String getRowid() {
        return rowid;
    }
    private String poNo = "";
    private String vendorName = "";
    private String vendorSiteCode = "";
    private String productCategory = "";
    private String productTitle = "";
    private String unitPrice = "";
    private String imageUrl = "";
    private String checked="";
    private String source="";
    private String uom="";
    private String quantity="";
    private String amount="";
    private String costCenter="";
    private String itemRef="";
    private String indixCategoryId="";
    private String indixAttributes="";
    private String naturalAccount="";
    private String costCenterNaturalAccount="";

    public void setCostCenterNaturalAccount(String costCenterNaturalAccount) {
        this.costCenterNaturalAccount = costCenterNaturalAccount;
        propertyChangeSupport.firePropertyChange("costCenterNaturalAccount", this.costCenterNaturalAccount,costCenterNaturalAccount);
    }

    public String getCostCenterNaturalAccount() {
        return costCenterNaturalAccount;
    }

    public void setNaturalAccount(String naturalAccount) {
        this.naturalAccount = naturalAccount;
        propertyChangeSupport.firePropertyChange("naturalAccount", this.naturalAccount,naturalAccount);
    }

    public String getNaturalAccount() {
        return naturalAccount;
    }

    public void setIndixAttributes(String indixAttributes) {
        this.indixAttributes = indixAttributes;
        propertyChangeSupport.firePropertyChange("indixAttributes", this.indixAttributes,indixAttributes);
    }

    public String getIndixAttributes() {
        return indixAttributes;
    }

    public void setIndixCategoryId(String indixCategoryId) {
        this.indixCategoryId = indixCategoryId;
        propertyChangeSupport.firePropertyChange("indixCategoryId", this.indixCategoryId,indixCategoryId);
    }

    public String getIndixCategoryId() {
        return indixCategoryId;
    }


    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
        propertyChangeSupport.firePropertyChange("costCenter", this.costCenter,costCenter);
    }

    public String getCostCenter() {
        return costCenter;
    }
    
    public void setItemRef(String itemRef) {
        this.itemRef = itemRef;
        propertyChangeSupport.firePropertyChange("itemRef", this.itemRef,itemRef);
    }

    public String getItemRef() {
        return itemRef;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        propertyChangeSupport.firePropertyChange("amount", this.amount,amount);
    }

    public String getAmount() {
        return amount;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        propertyChangeSupport.firePropertyChange("quantity", this.quantity,quantity);
    }

    public String getQuantity() {
        return quantity;
    }

    public void setNeed_by_date(String need_by_date) {
        this.need_by_date = need_by_date;
        propertyChangeSupport.firePropertyChange("need_by_date", this.need_by_date,need_by_date);
    }

    public String getNeed_by_date() {
        return need_by_date;
    }

    public void setDeliver_to_location(String deliver_to_location) {
        this.deliver_to_location = deliver_to_location;
        propertyChangeSupport.firePropertyChange("deliver_to_location", this.deliver_to_location,deliver_to_location);
    }

    public String getDeliver_to_location() {
        return deliver_to_location;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
    private String need_by_date="";
    private String deliver_to_location="";
    
    public SelectedItem() {
        super();
    }
    public void setChecked(String checked) {
        this.checked = checked;
        propertyChangeSupport.firePropertyChange("checked", this.checked,checked);
    }

    public String getChecked() {
        return checked;
    }
    
    public void setSource(String source) {
        this.source = source;
        propertyChangeSupport.firePropertyChange("source", this.source,source);
    }

    public String getSource() {
        return source;
    }

    public void setUom(String uom) {
        this.uom = uom;
        propertyChangeSupport.firePropertyChange("uom", this.uom,uom);
    }

    public String getUom() {
        return uom;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        propertyChangeSupport.firePropertyChange("imageUrl", this.imageUrl,imageUrl);
    }

    public String getImageUrl() {
        return imageUrl;
    }
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
  
    
    public SelectedItem(String poNo,String vendorName,String vendorSiteCode,String productCategory,String productTitle,String unitPrice,String imageUrl,String checked,String source,String uom,String quantity,String deliver_to_location,String need_by_date,String amount,String rowId,String costCenter,String itemRef,String indixCategoryId, String indixAttributes, String naturalAccount,String costCenterNaturalAccount) {
        super();
        
        
        this.poNo = poNo;
        this.vendorName = vendorName;
        this.vendorSiteCode = vendorSiteCode;
        this.productCategory = productCategory;
        this.productTitle = productTitle;
        this.unitPrice = unitPrice;
        this.imageUrl=imageUrl;
        this.checked=checked;
        this.source=source;
        this.uom=uom;
        this.quantity=quantity;
        this.deliver_to_location=deliver_to_location;
        this.need_by_date=need_by_date;
        this.amount=amount;
        this.rowid=rowId;
        this.costCenter=costCenter;
        this.itemRef=itemRef;
        this.indixCategoryId=indixCategoryId;
        this.indixAttributes=indixAttributes;
        this.naturalAccount=naturalAccount;
        this.costCenterNaturalAccount=costCenterNaturalAccount;
    }
    
    

    public void setPoNo(String poNo) {
        this.poNo = poNo;
        propertyChangeSupport.firePropertyChange("poNo", this.poNo,poNo);
    }

    public String getPoNo() {
        return poNo;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
        propertyChangeSupport.firePropertyChange("vendorName", this.vendorName,vendorName);
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
        propertyChangeSupport.firePropertyChange("vendorSiteCode", this.vendorSiteCode,vendorSiteCode);
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
        propertyChangeSupport.firePropertyChange("productCategory", this.productCategory,productCategory);
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
        propertyChangeSupport.firePropertyChange("productTitle", this.productTitle,productTitle);
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        propertyChangeSupport.firePropertyChange("unitPrice", this.unitPrice,unitPrice);
    }

    public String getUnitPrice() {
        return unitPrice;
    }
    
}