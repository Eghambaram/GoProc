package mobile;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Item {
    
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
    private String rowId="";
    private String diverseSupplier="";
    private String showDiverseImage="";
    private int pageNo;
    private String indixCategoryId="";
    private String sellerName="";
    private String showSeller="";
    private String attrib="";
    private String showAttrib="";
    private String spec="";
    private String showSpec="";
    private List specList = new ArrayList();

    public void setSpecLlist(List specList) {
        this.specList = specList;
        propertyChangeSupport.firePropertyChange("specList", this.specList,specList);  
    }

    public List getSpecLlist() {
        return specList;
        
    }

    public void setAttrib(String attrib) {
        this.attrib = attrib;
        propertyChangeSupport.firePropertyChange("attrib", this.attrib,attrib);  
    }

    public String getAttrib() {
        return attrib;
    }

    public void setShowAttrib(String showAttrib) {
        this.showAttrib = showAttrib;
        propertyChangeSupport.firePropertyChange("showAttrib", this.showAttrib,showAttrib);  
    }

    public String getShowAttrib() {
        return showAttrib;
    }

    public void setSpec(String spec) {
        this.spec = spec;
        propertyChangeSupport.firePropertyChange("spec", this.spec,spec);  
    }

    public String getSpec() {
        return spec;
    }

    public void setShowSpec(String showSpec) {
        this.showSpec = showSpec;
        propertyChangeSupport.firePropertyChange("showSpec", this.showSpec,showSpec);  
    }

    public String getShowSpec() {
        return showSpec;
    }

    public void setShowSeller(String showSeller) {
        this.showSeller = showSeller;
        propertyChangeSupport.firePropertyChange("showSeller", this.showSeller,showSeller);
    }

    public String getShowSeller() {
        return showSeller;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
        propertyChangeSupport.firePropertyChange("sellerName", this.sellerName,sellerName);
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setIndixCategoryId(String indixCategoryId) {
        this.indixCategoryId = indixCategoryId;
    }

    public String getIndixCategoryId() {
        return indixCategoryId;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        propertyChangeSupport.firePropertyChange("pageNo", this.pageNo,pageNo);
    }

    public int getPageNo() {
        return pageNo;
    }


    private String diverseImageURL="";

    public void setDiverseSupplier(String diverseSupplier) {
        this.diverseSupplier = diverseSupplier;
        propertyChangeSupport.firePropertyChange("diverseSupplier", this.diverseSupplier,diverseSupplier);
    }

    public String getDiverseSupplier() {
        return diverseSupplier;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
        propertyChangeSupport.firePropertyChange("rowId", this.rowId,rowId);
    }

    public String getRowId() {
        return rowId;
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
    
    public Item() {
        super();
    }
    
    public Item(String poNo,String vendorName,String vendorSiteCode,String productCategory,String productTitle,String unitPrice,String imageUrl,String checked,String source,String uom,String rowId,String diverseSupplier,String showDiverseImage,String diverseImageURL,int pageNo,String indixCategoryId,String sellerName,String showSeller,String attrib,String showAttrib,String spec,String showSpec) {
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
        this.rowId=rowId;
        this.diverseSupplier=diverseSupplier;
        this.showDiverseImage=showDiverseImage;
        this.diverseImageURL=diverseImageURL;
        this.pageNo=pageNo;
        this.indixCategoryId=indixCategoryId;
        this.sellerName=sellerName;
        this.showSeller=showSeller;
        this.attrib=attrib;
        this.showAttrib=showAttrib;
        this.spec=spec;
        this.showSpec=showSpec;
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
    
    public void setShowDiverseImage(String showDiverseImage) {
        this.showDiverseImage = showDiverseImage;
        propertyChangeSupport.firePropertyChange("showDiverseImage", this.showDiverseImage,showDiverseImage);
    }

    public String getShowDiverseImage() {
        return showDiverseImage;
    }

    public void setDiverseImageURL(String diverseImageURL) {
        this.diverseImageURL = diverseImageURL;
        propertyChangeSupport.firePropertyChange("diverseImageURL", this.diverseImageURL,diverseImageURL);
    }

    public String getDiverseImageURL() {
        return diverseImageURL;
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

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }
    
}
