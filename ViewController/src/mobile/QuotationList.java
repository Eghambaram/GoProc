package mobile;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import oracle.adfmf.util.GenericType;

import org.json.JSONArray;
import org.json.JSONObject;

public class QuotationList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs=new ArrayList();
    public static Quotation selected_item=new Quotation();
    public QuotationList() {
        //super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public Quotation[] getQuotations() {
        Quotation e[] = null;
        e = (Quotation[])s_jobs.toArray(new Quotation[s_jobs.size()]);
        return e;
    }
    
    
    public void getQuotationList() {
        
        Quotation j = new Quotation(); 
        s_jobs.add(j);
        
          
        
    }
    
    public void addProviderChangeListener(ProviderChangeListener l) { 
            providerChangeSupport.addProviderChangeListener(l);
          }
          public void removeProviderChangeListener(ProviderChangeListener l) { 
            providerChangeSupport.removeProviderChangeListener(l);
          }  
          
          public void setQuotations(List quotations){
              QuotationList.s_jobs=quotations;
          }
    
    public void refresh(){
               try{
               ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
               String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
               
               ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
               String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
                   
                   ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
                   String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
                   
                   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                 
                       
               //        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
               //        String needByDate =   sdf.format(new Date());
               //        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), needByDate);
                   
               RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
               // Clear any previously set request properties, if any
               restServiceAdapter.clearRequestProperties();
               // Set the connection name
               restServiceAdapter.setConnectionName("enrich");
               
               restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
               restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
               restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
               restServiceAdapter.addRequestProperty("Content-Type", "application/json");
               restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_rfq/");
               String postData= "{\n" + 
               "  \"GET_RFQ_Input\" : {\n" + 
               "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_req_summary/\",\n" + 
               "   \"RESTHeader\": {\n" + 
               "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
               "    },\n" + 
               "   \"InputParameters\": {\n" + 
               "        \"P_USER_ID\": \""+userId+"\"\n" + 
               "     }\n" + 
               "  }\n" + 
               "}  \n";
                                           restServiceAdapter.setRetryLimit(0);
                  System.out.println("postData===============================" + postData);
                   
                  String response = restServiceAdapter.send(postData);
                   
                 //  System.out.println("response===============================" + response);
                   JSONObject resp=new JSONObject(response);
                   JSONObject output=resp.getJSONObject("OutputParameters");
                   JSONObject summTBL=output.getJSONObject("X_RFQ_TL");
                   
                   RFQList.s_jobs.clear();
                   RFQ rfq1=new RFQ("", "", "Please Select", "","","");
                   RFQList.s_jobs.add(rfq1);
                   
                   if(summTBL.get("X_RFQ_TL_ITEM") instanceof JSONArray) {
                      // System.out.println("Inside JSON ===============================" + response);                             
                       JSONArray summItm=summTBL.getJSONArray("X_RFQ_TL_ITEM");
                       for(int i=0;i<summItm.length();i++) {
                           JSONObject data=(JSONObject)summItm.get(i);
                           
                           String rfqId=data.getString("RFQ_ID");
                           String rfgNo=data.getString("RFQ_NUM");
                           String itemDescription=data.getString("ITEM_DESCRIPTION");
                           itemDescription=itemDescription+"-"+rfgNo;
                           String rfqCloseDate=data.getString("RFQ_CLOSE_DATE");
                           String rfqNeedByDate=data.getString("NEED_BY_DATE");
                           if (rfqNeedByDate.contains("{")) {
                               rfqNeedByDate = "";
                           }
                           String deliverToLocation=data.getString("DELIVER_TO_LOC_CODE");
                           RFQ rfq=new RFQ(rfqId, rfgNo, itemDescription, rfqCloseDate,rfqNeedByDate,deliverToLocation);
                           RFQList.s_jobs.add(rfq);
                           
                       }
                       
                       
                       
                   }
                   
                   if(summTBL.get("X_RFQ_TL_ITEM") instanceof JSONObject) {
                          
                       JSONObject data=(JSONObject)summTBL.getJSONObject("X_RFQ_TL_ITEM");
                       
                       String rfqId=data.getString("RFQ_ID");
                       String rfgNo=data.getString("RFQ_NUM");
                       String itemDescription=data.getString("ITEM_DESCRIPTION");
                       itemDescription=itemDescription+"-"+rfgNo;
                       String rfqCloseDate=data.getString("RFQ_CLOSE_DATE");
                       String rfqNeedByDate=data.getString("NEED_BY_DATE");
                       if (rfqNeedByDate.contains("{")) {
                                               rfqNeedByDate = "";
                                           }
                       String deliverToLocation=data.getString("DELIVER_TO_LOC_CODE");
                       RFQ rfq=new RFQ(rfqId, rfgNo, itemDescription, rfqCloseDate,rfqNeedByDate,deliverToLocation);
                       RFQList.s_jobs.add(rfq);                                          
                   
                   }
                   
               }
               catch(Exception e){
                   e.printStackTrace();
               }
               
               
               
               
               
               
               ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{bindings.RFQ.inputValue}", String.class);
               String rfqItemDescription = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
               if(!rfqItemDescription.equalsIgnoreCase("") && !rfqItemDescription.equalsIgnoreCase("Please Select")){
               String arr[]=rfqItemDescription.split("-");
               String rfqNo=arr[arr.length-1];
               System.out.println("rfqId is =>"+rfqNo);
               String rfqId="";
               String rfqCloseDate="";
               String rfqNeedByDate="";
               String rfqDeliverToLocation="";
               System.out.println("RFQList size is =======>"+RFQList.s_jobs.size());
               for(int k=0;k<RFQList.s_jobs.size();k++) {
                   RFQ r=(RFQ)RFQList.s_jobs.get(k);
                   System.out.println(r.getRfqNo()+"="+rfqNo);
                   if(r.getRfqNo().equalsIgnoreCase(rfqNo)) {
                       rfqId=r.getRfqId();
                       rfqCloseDate=r.getRfqCloseDate();
                       rfqNeedByDate=r.getRfqNeedByDate();
                       rfqDeliverToLocation=r.getRfqDeliverToLocation();
                   }
               }
               
               try{
                   
                   
               ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
               String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
               
               ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
               String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
               
               RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
               // Clear any previously set request properties, if any
               restServiceAdapter.clearRequestProperties();
               // Set the connection name
               restServiceAdapter.setConnectionName("enrich");
               
               restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
               restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
               restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
               restServiceAdapter.addRequestProperty("Content-Type", "application/json");
               restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_quotations/");
               String postData= "{\n" + 
               "\n" + 
               "  \"GET_QUOTATIONS_Input\" : {\n" + 
               "\n" + 
               "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_quotations/\",\n" + 
               "\n" + 
               "   \"RESTHeader\": {\n" + 
               "\n" + 
               "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
               "    },\n" + 
               "\n" + 
               "   \"InputParameters\": {\n" + 
               "\n" + 
               "        \"P_RFQ_ID\":"+rfqId+"\n" + 
               "\n" + 
               "     }\n" + 
               "\n" + 
               "  }\n" + 
               "\n" + 
               "}  ";
                                           restServiceAdapter.setRetryLimit(0);
                  System.out.println("postData===============================" + postData);
                   
                  String response = restServiceAdapter.send(postData);
                   
                  System.out.println("response===============================" + response);
                   
                   
                   JSONObject resp=new JSONObject(response);
                   JSONObject output=resp.getJSONObject("OutputParameters");
                   JSONObject summTBL=output.getJSONObject("X_QUOTATIONS_TL");
                   //RequisitionLineItemsList.s_jobs.clear();
                   
                   mobile.QuotationList.s_jobs=new ArrayList();
                  // mobile.QuotationList.s_jobs.clear();
                  
                   if(summTBL.get("X_QUOTATIONS_TL_ITEM") instanceof JSONArray) {
                       System.out.println("inside json");
                       
                       JSONArray summItm=summTBL.getJSONArray("X_QUOTATIONS_TL_ITEM");
                      
                       for(int i=0;i<summItm.length();i++) {
                           System.out.println("for");
                           JSONObject data=(JSONObject)summItm.get(i);
                           
                           rfqId=data.getString("RFQ_ID");
                           String itemDescription=data.getString("ITEM_DESCRIPTION");
                           String quotationId=data.getString("QUOTATION_ID");
                           String quotationNo=data.getString("QUOTATION_NUM");
                           String quotationLineId=data.getString("QUOTATION_LINE_ID");
                           String quotationLineNo=data.getString("QUOTATION_LINE_NUM");
                           String vendorId=data.getString("VENDOR_ID");
                           String vendorName=data.getString("VENDOR_NAME");
                           String quantity=data.getString("QUANTITY");
                           String uom=data.getString("UOM");
                           String promiseDate=data.getString("PROMISE_DATE");
                           if (promiseDate.contains("{")) {
                                                   promiseDate = "";
                                               }
                           String price=data.getString("PRICE");
                           String currencyCode=data.getString("CURRENCY_CODE");
                           String supplierQuotationNo=data.getString("SUPPLIER_QUOTATION_NUM");
                           String needByDate=data.getString("NEED_BY_DATE");
                         
                           Quotation q=new Quotation(rfqId, itemDescription, quotationId, quotationNo, quotationLineId, quotationLineNo, vendorId, vendorName, quantity, uom, promiseDate, price, currencyCode, supplierQuotationNo,"/images/no.png",needByDate); 
                           boolean ret=QuotationList.s_jobs.add(q);
                           System.out.println("Add "+ret);
                      
                       }
                       
                       
                       
                       
                   }
                   
                   if(summTBL.get("X_QUOTATIONS_TL_ITEM") instanceof JSONObject) {
                       
                       JSONObject data=summTBL.getJSONObject("X_QUOTATIONS_TL_ITEM");
                       
                       rfqId=data.getString("RFQ_ID");
                       String itemDescription=data.getString("ITEM_DESCRIPTION");
                       String quotationId=data.getString("QUOTATION_ID");
                       String quotationNo=data.getString("QUOTATION_NUM");
                       String quotationLineId=data.getString("QUOTATION_LINE_ID");
                       String quotationLineNo=data.getString("QUOTATION_LINE_NUM");
                       String vendorId=data.getString("VENDOR_ID");
                       String vendorName=data.getString("VENDOR_NAME");
                       String quantity=data.getString("QUANTITY");
                       String uom=data.getString("UOM");
                       if (uom.contains("{")) {
                           uom = "";
                       }
                       String promiseDate=data.getString("PROMISE_DATE");
                       if (promiseDate.contains("{")) {
                           promiseDate = "";
                       }
                       String price=data.getString("PRICE");
                       if (price.contains("{")) {
                           price = "";
                       }
                       String currencyCode=data.getString("CURRENCY_CODE");
                       if (currencyCode.contains("{")) {
                           currencyCode = "";
                       }
                       String supplierQuotationNo=data.getString("SUPPLIER_QUOTATION_NUM");
                       
                       if (supplierQuotationNo.contains("{")) {
                           supplierQuotationNo = "";
                       }
                       
                       String needByDate=data.getString("NEED_BY_DATE");
                       
                       if (needByDate.contains("{")) {
                           needByDate = "";
                       }
                       
                       
                       Quotation q=new Quotation(rfqId, itemDescription, quotationId, quotationNo, quotationLineId, quotationLineNo, vendorId, vendorName, quantity, uom, promiseDate, price, currencyCode, supplierQuotationNo,"/images/no.png",needByDate); 
                       boolean ret=QuotationList.s_jobs.add(q);
                       System.out.println("Add "+ret);
                       
                   }
                   
                   
                   String arr_s[]=rfqCloseDate.split("T");
                   ValueExpression ve36 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationRFQCloseDate}", String.class);
                   ve36.setValue(AdfmfJavaUtilities.getAdfELContext(),arr_s[0]);
                   
                   
                   String arr1[]=rfqItemDescription.split("-"+rfqNo);
                   
                   
                   ValueExpression ve37 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDescription}", String.class);
                   ve37.setValue(AdfmfJavaUtilities.getAdfELContext(),arr1[0]);
                   
                   
                   ValueExpression ve38 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqQuotNo}", String.class);
                   ve38.setValue(AdfmfJavaUtilities.getAdfELContext(),rfqNo);
                   
                   ValueExpression ve39 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
                   ve39.setValue(AdfmfJavaUtilities.getAdfELContext(),rfqNeedByDate);
                   
                   ValueExpression ve40 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationDeliverToLocation}", String.class);
                   ve40.setValue(AdfmfJavaUtilities.getAdfELContext(),rfqDeliverToLocation);
                   
                   
                   AdfmfJavaUtilities.flushDataChangeEvent();
               }
              
               catch(Exception e){
                   e.printStackTrace();
               }
              
              ////////////////////
               System.out.println("Elements size "+  QuotationList.s_jobs.size());
               providerChangeSupport.fireProviderRefresh("quotations");
           }
    }
    
    
    public void pickQuotations(){
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.quotations.iterator}");  
        selected_item=(Quotation)vex.getDataProvider();
        
        
        for(int i=0;i<vex.getTotalRowCount();i++)
         {
             vex.setCurrentIndex(i);
            Quotation q=(Quotation)vex.getDataProvider();
            GenericType row= (GenericType)vex.getCurrentRow();
           
            if(q.getQuotationId().equalsIgnoreCase(selected_item.getQuotationId()) && q.getQuotationLineId().equalsIgnoreCase(selected_item.getQuotationLineId())){
               row.setAttribute("selected", new String("/images/yes.png"));
            }
            else{
                row.setAttribute("selected", new String("/images/no.png"));
            }
            
         }
        
        
        //        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
        //        ve.setValue(AdfmfJavaUtilities.getAdfELContext(),selected_item.getNeedByDate());
        //        AdfmfJavaUtilities.flushDataChangeEvent();
        vex.refresh();
    }
    
    
    public void proceedPlaceOrder() {
        
        try{
            
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
        
        
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/convert_quote_to_req/");
            
             
                  
            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression(" #{pageFlowScope.quotationNeedByDate}", String.class);
            String needByDate = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());    
            
            String arr[];
            String need="";
            SimpleDateFormat fdf=new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat tdf=new SimpleDateFormat("yyyy-MM-dd");
            if(!needByDate.equalsIgnoreCase("")){
                arr=needByDate.split("T");
                if(arr[0].contains("/")){
                 need=tdf.format(fdf.parse(arr[0]));
                }
                else{
                    need=arr[0];
                }
                 
            }
            
            //please change the variable name
            ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{bindings.deliverToLocations.inputValue}", String.class);
            String deliverToLocation = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext()); 
            String locationId="";
            
            for(int k=0;k<DeliverToLocationList.s_jobs.size();k++) {
                DeliverToLocation loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get(k);
                if(loc.getCode().equalsIgnoreCase(deliverToLocation)){
                    locationId=loc.getId();
                }
            }
            
            
            
          
            
            
        String postData= "{\n" + 
        "  \"CONVERT_QUOTE_TO_REQ_Input\" : {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/convert_quote_to_req/\",\n" + 
        "\n" + 
        "   \"RESTHeader\": {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "\n" + 
        "   \"InputParameters\": {\n" + 
        "\n" + 
        "        \"P_QUOTE_RESPONSE_REC\": {\n" + 
        "\n" + 
        "          \"QUOTATION_ID\" : "+selected_item.getQuotationId()+",\n" + 
        "\n" + 
        "          \"QUOTATION_LINE_ID\" :"+selected_item.getQuotationLineId()+",\n" + 
        "\n" + 
        "          \"VENDOR_ID\" :"+ selected_item.getVendorId()+",\n" + 
        "\n" + 
        "          \"VENDOR_SITE_ID\" : \"\",\n" + 
        "\n" + 
        "          \"NEED_BY_DATE\" : \""+need+"\",\n" + 
        "\n" + 
        "          \"DELIVER_TO_LOCATION_ID\" :"+ locationId+",\n" + 
        "\n" + 
        "          \"USER_ID\" : "+userId+",\n" + 
        "          \"RFQ_ID\" : "+selected_item.getRfqId()+"\n" + 
        "\n" + 
        "        }\n" + 
        "\n" + 
        "     }\n" + 
        "\n" + 
        "  }\n" + 
        "\n" + 
        "}  ";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            System.out.println("response===============================" + response); 
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                       "displayAlert",
                                                                       new Object[] { });
            
            selected_item=new Quotation();
            QuotationList.s_jobs.clear();
            
            
            ValueExpression ve37 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDescription}", String.class);
            ve37.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            ValueExpression ve38 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqQuotNo}", String.class);
            ve38.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            ValueExpression ve39 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
            ve39.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            ValueExpression ve40 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationRFQCloseDate}", String.class);
            ve40.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
            RFQList.s_jobs.clear();
            getQuotations();
            
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{bindings.RFQ.inputValue}", String.class);
            ve41.setValue(AdfmfJavaUtilities.getAdfELContext(),"0");
            
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.quotations.iterator}");  
            vex.refresh();
            
            //AdfmfContainerUtilities.resetFeature("mp.Quotation");
    //
    //
    //            for(int i=0;i<vex.getTotalRowCount();i++)
    //             {
    //                 vex.setCurrentIndex(i);
    //                Quotation q=(Quotation)vex.getDataProvider();
    //                GenericType row= (GenericType)vex.getCurrentRow();
    //                 row.setAttribute("selected", new String("/images/no.png"));
    //             }
    //
    //            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
        }
        catch(Exception e) {
            
            e.printStackTrace();
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "Cannot connect to Services on Oracle Server.",
                                         null,
                                         null }); 
        }
        
        //getQuotations("");
        
    }
    
}
