package mobile;

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
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.JSONArray;
import org.json.JSONObject;

public class OrderList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public OrderList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public Order[] getOrders() {
        Order e[] = null;
        e = (Order[])s_jobs.toArray(new Order[s_jobs.size()]);
        return e;
    }
    
    
    public void getOrderList() {
        
        Order j = new Order(); 
        s_jobs.add(j);
        
          
        
    }
    
    public void searchRequisition() {
     
    //        try{
    //        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
    //        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
    //
    //        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
    //        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
    //
    //        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
    //        String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
    //
    //        ValueExpression veSearch = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchRequisitionValue}", String.class);
    //        String veSearchText = (String)veSearch.getValue(AdfmfJavaUtilities.getAdfELContext());
    //
    //
    //        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
    //        // Clear any previously set request properties, if any
    //        restServiceAdapter.clearRequestProperties();
    //        // Set the connection name
    //        restServiceAdapter.setConnectionName("enrich");
    //
    //        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
    //        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
    //        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
    //        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
    //        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_req_summary/");
    //        String postData= "{\n" +
    //        "  \"GET_REQ_SUMMARY_Input\" : {\n" +
    //        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_req_summary/\",\n" +
    //        "   \"RESTHeader\": {\n" +
    //        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
    //        "    },\n" +
    //        "   \"InputParameters\": {\n" +
    //        "        \"P_USER_ID\": \""+userId+"\",\n" +
    //        "        \"P_ITEM_DESCRIPTION\": \""+veSearchText+"\"\n" +
    //        "     }\n" +
    //        "  }\n" +
    //        "}  ";
    //                                    restServiceAdapter.setRetryLimit(0);
    //           System.out.println("postData===============================" + postData);
    //
    //           String response = restServiceAdapter.send(postData);
    //
    //            System.out.println("response===============================" + response);
    //            JSONObject resp=new JSONObject(response);
    //            JSONObject output=resp.getJSONObject("OutputParameters");
    //            try{
    //            JSONObject summTBL=output.getJSONObject("X_REQ_SUMMARY_TL");
    //            RequisitionsList.s_jobs.clear();
    //            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONArray) {
    //                System.out.println("Inside JSON ===============================" + response);
    //                JSONArray summItm=summTBL.getJSONArray("X_REQ_SUMMARY_TL_ITEM");
    //
    //                for(int i=0;i<summItm.length();i++) {
    //                    JSONObject data=(JSONObject)summItm.get(i);
    //
    //                    String requisitionHeaderId=data.getString("REQUISITION_HEADER_ID");
    //                    String requisitionNo=data.getString("REQUISITION_NUM");
    //                    String createdDate="";
    //                    if(!data.getString("CREATED_DATE").equalsIgnoreCase("")){
    //                       String arr[]=data.getString("CREATED_DATE").split("T");
    //                       createdDate=arr[0];
    //                    }
    //                    String status=data.getString("STATUS");
    //                    String totalAmount=data.getString("TOTAL_AMOUNT");
    //                    String rfqNo=data.getString("RFQ_NUM");
    //                    String rfqStatus=data.getString("RFQ_STATUS");
    //                    String rfqSubmissionDate=data.getString("RFQ_SUBMISSION_DATE");
    //                    String rfqDescription=data.getString("RFQ_DESCRIPTION");
    //                    String rfqHeaderId=data.getString("RFQ_HEADER_ID");
    //                    String showRfqSection="true";
    //                    String showRequisitionSection="true";
    //
    //                    if(requisitionHeaderId.contains("{")){
    //                        requisitionHeaderId = "";
    //                        showRequisitionSection="false";
    //                    }
    //                    if(requisitionNo.contains("{")){
    //                        requisitionNo="";
    //                    }
    //                    if(createdDate.contains("{")){
    //                        createdDate="";
    //                    }
    //                    if(status.contains("{")){
    //                        status="";
    //                    }
    //                    if(totalAmount.contains("{")){
    //                        totalAmount="";
    //                    }
    //
    //                    if (rfqNo.contains("{")) {
    //                        rfqNo = "";
    //                        showRfqSection="false";
    //                    }
    //                    if (rfqStatus.contains("{")) {
    //                        rfqStatus = "";
    //                    }
    //                    if (rfqSubmissionDate.contains("{")) {
    //                        rfqSubmissionDate = "";
    //                    }
    //                    if (rfqDescription.contains("{")) {
    //                        rfqDescription = "";
    //                    }
    //                    if (rfqHeaderId.contains("{")) {
    //                        rfqHeaderId = "";
    //                    }
    //
    //
    //                    String showRow="true";
    //                    if(showRequisitionSection.equalsIgnoreCase("false") && showRfqSection.equalsIgnoreCase("false")) {
    //                        showRow="false";
    //                    }
    //
    //
    //                    Requisition req=new Requisition(requisitionHeaderId, requisitionNo, createdDate, status, totalAmount,rfqNo,rfqStatus,rfqSubmissionDate,rfqDescription,rfqHeaderId,showRfqSection,showRequisitionSection,showRow);
    //                    RequisitionsList.s_jobs.add(req);
    //                }
    //
    //                System.out.println("RequisitionsList ===============================" + RequisitionsList.s_jobs.size());
    //
    //            }
    //
    //            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONObject) {
    //
    //                JSONObject data=(JSONObject)summTBL.getJSONObject("X_REQ_SUMMARY_TL_ITEM");
    //
    //                String requisitionHeaderId=data.getString("REQUISITION_HEADER_ID");
    //                String requisitionNo=data.getString("REQUISITION_NUM");
    //                String createdDate="";
    //                if(!data.getString("CREATED_DATE").equalsIgnoreCase("")){
    //                   String arr[]=data.getString("CREATED_DATE").split("T");
    //                   createdDate=arr[0];
    //                }
    //                String status=data.getString("STATUS");
    //                String totalAmount=data.getString("TOTAL_AMOUNT");
    //
    //                String rfqNo=data.getString("RFQ_NUM");
    //                String rfqStatus=data.getString("RFQ_STATUS");
    //                String rfqSubmissionDate=data.getString("RFQ_SUBMISSION_DATE");
    //                String rfqDescription=data.getString("RFQ_DESCRIPTION");
    //                String rfqHeaderId=data.getString("RFQ_HEADER_ID");
    //                String showRfqSection="true";
    //                String showRequisitionSection="true";
    //
    //                if(requisitionHeaderId.contains("{")){
    //                    requisitionHeaderId = "";
    //                    showRequisitionSection="false";
    //                }
    //                if(requisitionNo.contains("{")){
    //                    requisitionNo="";
    //                }
    //                if(createdDate.contains("{")){
    //                    createdDate="";
    //                }
    //                if(status.contains("{")){
    //                    status="";
    //                }
    //                if(totalAmount.contains("{")){
    //                    totalAmount="";
    //                }
    //
    //                if (rfqNo.contains("{")) {
    //                    rfqNo = "";
    //                    showRfqSection="false";
    //                }
    //                if (rfqStatus.contains("{")) {
    //                    rfqStatus = "";
    //                }
    //                if (rfqSubmissionDate.contains("{")) {
    //                    rfqSubmissionDate = "";
    //                }
    //                if (rfqDescription.contains("{")) {
    //                    rfqDescription = "";
    //                }
    //                if (rfqHeaderId.contains("{")) {
    //                    rfqHeaderId = "";
    //                }
    //
    //
    //                String showRow="true";
    //                if(showRequisitionSection.equalsIgnoreCase("false") && showRfqSection.equalsIgnoreCase("false")) {
    //                    showRow="false";
    //                }
    //
    //                Requisition req=new Requisition(requisitionHeaderId, requisitionNo, createdDate, status, totalAmount,rfqNo,rfqStatus,rfqSubmissionDate,rfqDescription,rfqHeaderId,showRfqSection,showRequisitionSection,showRow);
    //                RequisitionsList.s_jobs.add(req);
    //
    //            }
    //
    //        //            AdfmfContainerUtilities.gotoFeature("mp.Requisition");
    //
    //
    //        //            Collections.sort( RequisitionsList.s_jobs,new Comparator<Requisition>() {
    //        //                                              public int compare(Requisition o1, Requisition o2) {
    //        //                                                      int res=0;
    //        //                                                  if (o1.getCreatedDate() == null || o2.getCreatedDate() == null)
    //        //                                                  {
    //        //                                                    res= 0;
    //        //                                                  }
    //        //                                                  else{
    //        //
    //        //                                                      SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
    //        //
    //        //                                                  try {
    //        //                                                            res= sdf.parse(o2.getCreatedDate()).compareTo(sdf.parse(o1.getCreatedDate()));
    //        //                                                    } catch (Exception e) {
    //        //                                                            // TODO Auto-generated catch block
    //        //                                                            e.printStackTrace();
    //        //                                                    }
    //        //                                                  }
    //        //                                                    return res;
    //        //                                              }
    //        //
    //        //                                            });
    //
    //            }
    //            catch(Exception e) {
    //                e.printStackTrace();
    //            }
    //
    //        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.requisitions.iterator}");
    //        vex.refresh();
    //        AdfmfJavaUtilities.flushDataChangeEvent();
    //
    //        }
    //        catch(Exception e) {
    //
    //            e.printStackTrace();
    //
    //            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
    //                                         AdfmfJavaUtilities.getFeatureName(),
    //                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
    //                                         "Cannot connect to Services on Oracle Server.",
    //                                         null,
    //                                         null });
    //        }
        
        
                try{
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression veSearch = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchRequisitionValue}", String.class);
        String veSearchText = (String)veSearch.getValue(AdfmfJavaUtilities.getAdfELContext());           
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_req_summary_v2/");
        String postData= "{\n" + 
        "  \"GET_REQ_SUMMARY_V2_Input\" : {\n" + 
        "   \"RESTHeader\": {\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "            \"P_USER_ID\":  \""+userId+"\",\n" + 
        "            \"P_ITEM_DESCRIPTION\":  \""+veSearchText+"\"\n" +                         
        "         \n" + 
        "       }    \n" + 
        "   }\n" + 
        "}";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            OrderList.s_jobs.clear();
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            try{
            JSONObject summTBL=output.getJSONObject("X_REQ_SUMMARY_TL");
            RequisitionsList.s_jobs.clear();
            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONArray) {
                System.out.println("Inside JSON ===============================" + response);                             
                JSONArray summItm=summTBL.getJSONArray("X_REQ_SUMMARY_TL_ITEM");
               
                for(int i=0;i<summItm.length();i++) {
                    JSONObject data=(JSONObject)summItm.get(i);
                    
                    String searchGroupId=data.getString("SEARCH_GROUP_ID");
                    String requisitionDate=data.getString("REQ_DATE");
                    String reqType=data.getString("REQUEST_TYPE");
                    String totalAmount="$"+data.getString("AMOUNT");
                   
                    
                    if(totalAmount.contains("{")){
                        totalAmount = "";
                        
                    }
                    
                   Order or=new Order(searchGroupId, requisitionDate, reqType, totalAmount);
                   OrderList.s_jobs.add(or);
                }
                
            }
            
            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONObject) {
                
                JSONObject data=(JSONObject)summTBL.getJSONObject("X_REQ_SUMMARY_TL_ITEM");
                
                String searchGroupId=data.getString("SEARCH_GROUP_ID");
                String requisitionDate=data.getString("REQ_DATE");
                String reqType=data.getString("REQUEST_TYPE");
                String totalAmount="$"+data.getString("AMOUNT");
                
                
                if(totalAmount.contains("{")){
                    totalAmount = "";
                    
                }
                
                Order or=new Order(searchGroupId, requisitionDate, reqType, totalAmount);
                OrderList.s_jobs.add(or);                     
            
            }
            

                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
          
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.orders.iterator}");   
        vex.refresh();
            
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
    }
    
    public void clearRequisitionSearch() {
        
        OrderList.s_jobs.clear();
    
        ValueExpression veSearch = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchRequisitionValue}", String.class);
        veSearch.setValue(AdfmfJavaUtilities.getAdfELContext(),"");    
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        try{
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_req_summary_v2/");
        String postData= "{\n" + 
        "  \"GET_REQ_SUMMARY_V2_Input\" : {\n" + 
        "   \"RESTHeader\": {\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "            \"P_USER_ID\":  \""+userId+"\"\n" + 
        "         \n" + 
        "       }    \n" + 
        "   }\n" + 
        "}";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            try{
            JSONObject summTBL=output.getJSONObject("X_REQ_SUMMARY_TL");
            RequisitionsList.s_jobs.clear();
            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONArray) {
                System.out.println("Inside JSON ===============================" + response);                             
                JSONArray summItm=summTBL.getJSONArray("X_REQ_SUMMARY_TL_ITEM");
               
                for(int i=0;i<summItm.length();i++) {
                    JSONObject data=(JSONObject)summItm.get(i);
                    
                    String searchGroupId=data.getString("SEARCH_GROUP_ID");
                    String requisitionDate=data.getString("REQ_DATE");
                    String reqType=data.getString("REQUEST_TYPE");
                    String totalAmount="$"+data.getString("AMOUNT");
                   
                    
                    if(totalAmount.contains("{")){
                        totalAmount = "";
                        
                    }
                    
                   Order or=new Order(searchGroupId, requisitionDate, reqType, totalAmount);
                   OrderList.s_jobs.add(or);
                }
                
            }
            
            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONObject) {
                
                JSONObject data=(JSONObject)summTBL.getJSONObject("X_REQ_SUMMARY_TL_ITEM");
                
                String searchGroupId=data.getString("SEARCH_GROUP_ID");
                String requisitionDate=data.getString("REQ_DATE");
                String reqType=data.getString("REQUEST_TYPE");
                String totalAmount="$"+data.getString("AMOUNT");
                
                
                if(totalAmount.contains("{")){
                    totalAmount = "";
                    
                }
                
                Order or=new Order(searchGroupId, requisitionDate, reqType, totalAmount);
                OrderList.s_jobs.add(or);                     
            
            }
            

                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
          
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.orders.iterator}");   
        vex.refresh();
            
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
        
    }
    
    
}
