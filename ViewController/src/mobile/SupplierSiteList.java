package mobile;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class SupplierSiteList {
    public static List sup_List = new ArrayList();
    public SupplierSiteList() {
        super();
        if (sup_List == null) {
            sup_List = new ArrayList();
        }
    }
    public SupplierSite[] getSupplierSite() {
        SupplierSite e[] = null;
        e = (SupplierSite[])sup_List.toArray(new SupplierSite[sup_List.size()]);
        return e;
    }
    
    
    public void getSupplierSiteList() {
        
        SupplierSite sList = new SupplierSite(); 
        sup_List.add(sList);
        
    }
    public void showSupplierSites() {
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression vemul = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String multiOrgId=(String)vemul.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        //ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierSiteFormSearchText}", String.class);
        
        ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierForm}", String.class);
        String searchText=(String)ve_searchText.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        try{
                    RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
                    restServiceAdapter = Model.createRestServiceAdapter();
                    // Clear any previously set request properties, if any
                    restServiceAdapter.clearRequestProperties();
                    // Set the connection name
                    restServiceAdapter.setConnectionName("enrich");
                    
                    restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                    restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                    restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                    restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_supplier_sites/");
                 String  postData= "{\n" + 
                 "  \"GET_SUPPLIER_SITES_Input\" : {\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "    },\n" + 
                 "   \"InputParameters\": {\n" + 
                 "   	   \"P_VENDOR_NAME\" : \""+searchText+"\",\n" + 
                 "   	   \"P_ORG_ID\" : \""+multiOrgId+"\"\n" + 
                 "       }	   \n" + 
                 "   }\n" + 
                 "}\n";
                        
                            restServiceAdapter.setRetryLimit(0);
                       System.out.println("postData===============================" + postData);
                        
                        String response = restServiceAdapter.send(postData);
                     System.out.println("response===============================" + response);   
                     JSONObject resp=new JSONObject(response);
                     JSONObject output=resp.getJSONObject("OutputParameters");
                    try{
                       JSONObject data=output.getJSONObject("X_SUPPLIER_SITES_TL");
                            SupplierSiteList.sup_List.clear();
                            //System.out.println("GLAccount outside if loop:"+NaturalAcccountList.acc_List.size());
                        
                            if(data.get("X_SUPPLIER_SITES_TL_ITEM") instanceof  JSONArray){
                              JSONArray segments=data.getJSONArray("X_SUPPLIER_SITES_TL_ITEM");
                              for(int i=0;i<segments.length();i++) {
                                  //String name=(String)segments.get(i);
                                  JSONObject na=(JSONObject)segments.get(i);
                                  String vendorSiteId=na.getString("VENDOR_SITE_ID");
                                  String vendorSiteCode=na.getString("VENDOR_SITE_CODE");
                                  
                                  SupplierSite n=new SupplierSite(vendorSiteId, vendorSiteCode);
                                  SupplierSiteList.sup_List.add(n);
                                  
                              }
                            System.out.println("GLAccount inside if loop list:"+NaturalAcccountList.acc_List.size());
                            }
                            
                            else if(data.get("X_SUPPLIER_SITES_TL_ITEM") instanceof  JSONObject){
                               
                               JSONObject na=data.getJSONObject("X_SUPPLIER_SITES_TL_ITEM");
                                String vendorSiteId=na.getString("VENDOR_SITE_ID");
                                String vendorSiteCode=na.getString("VENDOR_SITE_CODE");
                                
                                SupplierSite n=new SupplierSite(vendorSiteId, vendorSiteCode);
                                SupplierSiteList.sup_List.add(n);
                               
                            }  
                         
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
            
            
            }
        catch(Exception e) {
            e.printStackTrace();
        }
         BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.supplierSite.iterator}");  
         vex.refresh();
        }

}