package mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import oracle.adf.model.datacontrols.device.Location;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.dbf.AmxAttributeBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.FeatureContext;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;

import oracle.adfmf.framework.exception.AdfException;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequesitionRest {
    
    public static Quotation selected_item=new Quotation();
    public static List deliverToLocationList=new ArrayList();
    public static ArrayList<MultiOrg> multiOrgList = new ArrayList<MultiOrg>();
    
    public static List rejectionReasonList = new ArrayList();
    public static ArrayList<QuotationSort> quotationSortList = new ArrayList<QuotationSort>();
    public static ArrayList<ItemType> itemTypeList = new ArrayList<ItemType>();
    public static ArrayList<Alias> aliasList = new ArrayList<Alias>();
    //public static ArrayList<CostCenter> costCenterList = new ArrayList<CostCenter>();
    public static List costCenterList=new ArrayList();
    public static ArrayList<NaturalAccounts> naturalAccountList = new ArrayList<NaturalAccounts>();
    public static ArrayList<RequestType> requestTypeList = new ArrayList<RequestType>();
    public RequesitionRest() {
    }

    public void getRequesitions(ActionEvent actionEvent) {
        // Add event code here...
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
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_req_summary/");
        String postData= "{\n" + 
        "  \"GET_REQ_SUMMARY_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_req_summary/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "        \"P_USER_ID\": \""+userId+"\"\n" + 
        "     }\n" + 
        "  }\n" + 
        "}  ";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            JSONObject summTBL=output.getJSONObject("X_REQ_SUMMARY_TL");
            
            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONArray) {
                System.out.println("Inside JSON ===============================" + response);                             
                JSONArray summItm=summTBL.getJSONArray("X_REQ_SUMMARY_TL_ITEM");
                RequisitionsList.s_jobs.clear();
                for(int i=0;i<summItm.length();i++) {
                    JSONObject data=(JSONObject)summItm.get(i);
                    
                    String requisitionHeaderId=data.getString("REQUISITION_HEADER_ID");
                    String requisitionNo=data.getString("REQUISITION_NUM");
                    String createdDate=data.getString("CREATED_DATE");
                    String status=data.getString("STATUS");
                    String totalAmount=data.getString("TOTAL_AMOUNT");
                    String rfqNo=data.getString("RFQ_NUM");
                    String rfqStatus=data.getString("RFQ_STATUS");
                    String rfqSubmissionDate=data.getString("RFQ_SUBMISSION_DATE");
                    String rfqDescription=data.getString("RFQ_DESCRIPTION");
                    String rfqHeaderId=data.getString("RFQ_HEADER_ID");
                    String showRfqSection="true";
                    String showRequisitionSection="true";
                    
                    if(requisitionHeaderId.contains("{")){
                        requisitionHeaderId = "";
                        showRequisitionSection="false";
                    }
                    if(requisitionNo.contains("{")){
                        requisitionNo="";
                    }
                    if(createdDate.contains("{")){
                        createdDate="";
                    }
                    if(status.contains("{")){
                        status="";
                    }
                    if(totalAmount.contains("{")){
                        totalAmount="";
                    }
                    
                    if (rfqNo.contains("{")) {
                        rfqNo = "";
                        showRfqSection="false";
                    }
                    if (rfqStatus.contains("{")) {
                        rfqStatus = "";
                    }
                    if (rfqSubmissionDate.contains("{")) {
                        rfqSubmissionDate = "";
                    }
                    if (rfqDescription.contains("{")) {
                        rfqDescription = "";
                    }
                    if (rfqHeaderId.contains("{")) {
                        rfqHeaderId = "";
                    }
                    
                    
                    String showRow="true";
                    if(showRequisitionSection.equalsIgnoreCase("false") && showRfqSection.equalsIgnoreCase("false")) {
                        showRow="false";
                    }
                    
                    
                    Requisition req=new Requisition(requisitionHeaderId, requisitionNo, createdDate, status, totalAmount,rfqNo,rfqStatus,rfqSubmissionDate,rfqDescription,rfqHeaderId,showRfqSection,showRequisitionSection,showRow);
                    RequisitionsList.s_jobs.add(req);
                }
                
                System.out.println("RequisitionsList ===============================" + RequisitionsList.s_jobs.size());
                
            }
            
            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONObject) {
                                                             
            
            }
            
          
            
            
            
//            AdfmfContainerUtilities.gotoFeature("mp.Requisition"); 
            
            
                
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.requisitions.iterator}");   
//            vex.refresh();
                
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                           "adf.mf.api.amx.doNavigation", new Object[] { "refresh" });    
            
            
            
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
    
    public void getRequisitions(String rr) {
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
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_req_summary/");
        String postData= "{\n" + 
        "  \"GET_REQ_SUMMARY_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_req_summary/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "        \"P_USER_ID\": \""+userId+"\"\n" + 
        "     }\n" + 
        "  }\n" + 
        "}  ";
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
                    
                    String requisitionHeaderId=data.getString("REQUISITION_HEADER_ID");
                    String requisitionNo=data.getString("REQUISITION_NUM");
                    String createdDate="";
                    if(!data.getString("CREATED_DATE").equalsIgnoreCase("")){
                       String arr[]=data.getString("CREATED_DATE").split("T");
                       createdDate=arr[0];
                    }
                    String status=data.getString("STATUS");
                    String totalAmount=data.getString("TOTAL_AMOUNT");
                    String rfqNo=data.getString("RFQ_NUM");
                    String rfqStatus=data.getString("RFQ_STATUS");
                    String rfqSubmissionDate=data.getString("RFQ_SUBMISSION_DATE");
                    String rfqDescription=data.getString("RFQ_DESCRIPTION");
                    String rfqHeaderId=data.getString("RFQ_HEADER_ID");
                    String showRfqSection="true";
                    String showRequisitionSection="true";
                    
                    if(requisitionHeaderId.contains("{")){
                        requisitionHeaderId = "";
                        showRequisitionSection="false";
                    }
                    if(requisitionNo.contains("{")){
                        requisitionNo="";
                    }
                    if(createdDate.contains("{")){
                        createdDate="";
                    }
                    if(status.contains("{")){
                        status="";
                    }
                    if(totalAmount.contains("{")){
                        totalAmount="";
                    }
                    
                    if (rfqNo.contains("{")) {
                        rfqNo = "";
                        showRfqSection="false";
                    }
                    if (rfqStatus.contains("{")) {
                        rfqStatus = "";
                    }
                    if (rfqSubmissionDate.contains("{")) {
                        rfqSubmissionDate = "";
                    }
                    if (rfqDescription.contains("{")) {
                        rfqDescription = "";
                    }
                    if (rfqHeaderId.contains("{")) {
                        rfqHeaderId = "";
                    }
                    
                    
                    String showRow="true";
                    if(showRequisitionSection.equalsIgnoreCase("false") && showRfqSection.equalsIgnoreCase("false")) {
                        showRow="false";
                    }
                    
                    Requisition req=new Requisition(requisitionHeaderId, requisitionNo, createdDate, status, totalAmount,rfqNo,rfqStatus,rfqSubmissionDate,rfqDescription,rfqHeaderId,showRfqSection,showRequisitionSection,showRow);
                    RequisitionsList.s_jobs.add(req);
                }
                
                System.out.println("RequisitionsList ===============================" + RequisitionsList.s_jobs.size());
                
            }
            
            if(summTBL.get("X_REQ_SUMMARY_TL_ITEM") instanceof JSONObject) {
                
                JSONObject data=(JSONObject)summTBL.getJSONObject("X_REQ_SUMMARY_TL_ITEM");
                
                String requisitionHeaderId=data.getString("REQUISITION_HEADER_ID");
                String requisitionNo=data.getString("REQUISITION_NUM");
                String createdDate="";
                if(!data.getString("CREATED_DATE").equalsIgnoreCase("")){
                   String arr[]=data.getString("CREATED_DATE").split("T");
                   createdDate=arr[0];
                }
                String status=data.getString("STATUS");
                String totalAmount=data.getString("TOTAL_AMOUNT");
                
                String rfqNo=data.getString("RFQ_NUM");
                String rfqStatus=data.getString("RFQ_STATUS");
                String rfqSubmissionDate=data.getString("RFQ_SUBMISSION_DATE");
                String rfqDescription=data.getString("RFQ_DESCRIPTION");
                String rfqHeaderId=data.getString("RFQ_HEADER_ID");
                String showRfqSection="true";
                String showRequisitionSection="true";
                
                if(requisitionHeaderId.contains("{")){
                    requisitionHeaderId = "";
                    showRequisitionSection="false";
                }
                if(requisitionNo.contains("{")){
                    requisitionNo="";
                }
                if(createdDate.contains("{")){
                    createdDate="";
                }
                if(status.contains("{")){
                    status="";
                }
                if(totalAmount.contains("{")){
                    totalAmount="";
                }
                
                if (rfqNo.contains("{")) {
                    rfqNo = "";
                    showRfqSection="false";
                }
                if (rfqStatus.contains("{")) {
                    rfqStatus = "";
                }
                if (rfqSubmissionDate.contains("{")) {
                    rfqSubmissionDate = "";
                }
                if (rfqDescription.contains("{")) {
                    rfqDescription = "";
                }
                if (rfqHeaderId.contains("{")) {
                    rfqHeaderId = "";
                }
                
                String showRow="true";
                if(showRequisitionSection.equalsIgnoreCase("false") && showRfqSection.equalsIgnoreCase("false")) {
                    showRow="false";
                }                
                
                Requisition req=new Requisition(requisitionHeaderId, requisitionNo, createdDate, status, totalAmount,rfqNo,rfqStatus,rfqSubmissionDate,rfqDescription,rfqHeaderId,showRfqSection,showRequisitionSection,showRow);
                RequisitionsList.s_jobs.add(req);                                      
            
            }
            
        //            AdfmfContainerUtilities.gotoFeature("mp.Requisition");
          
          
//            Collections.sort( RequisitionsList.s_jobs,new Comparator<Requisition>() {
//                                              public int compare(Requisition o1, Requisition o2) {
//                                                      int res=0;
//                                                  if (o1.getCreatedDate() == null || o2.getCreatedDate() == null)
//                                                  {
//                                                    res= 0;
//                                                  }
//                                                  else{
//                                                      
//                                                      SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
//                                                      
//                                                  try {
//                                                            res= sdf.parse(o2.getCreatedDate()).compareTo(sdf.parse(o1.getCreatedDate()));
//                                                    } catch (Exception e) {
//                                                            // TODO Auto-generated catch block
//                                                            e.printStackTrace();
//                                                    }
//                                                  }
//                                                    return res;
//                                              }
//                                             
//                                            });   
                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
          
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.requisitions.iterator}");   
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
    
    
    public void getQuotations(String rr) {
        
        try{
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String multiOrgId = (String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
     
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
        "          \"P_USER_ID\" : \""+userId+"\",\n" +
        "          \"P_ORG_ID\" : \""+multiOrgId+"\"\n" + 
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
            
        //            AdfmfContainerUtilities.gotoFeature("mp.Requisition");
          
          
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.RFQ.items}");   
//        vex.refresh();
                        // Get Quotation Sort Values
            try{
                
            // Clear any previously set request properties, if any
            restServiceAdapter.clearRequestProperties();
            // Set the connection name
            restServiceAdapter.setConnectionName("enrich");
                
                    
            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_sort_options/");
           postData= "{\n" + 
           "  \"GET_SORT_OPTIONS_Input\" : {\n" + 
           "   \"RESTHeader\": {\n" + 
           "    },\n" + 
           "   \"InputParameters\": {\n" + 
           "        \"P_TYPE\": \"QUOTE\"\n" + 
           "     }\n" + 
           "  }\n" + 
           "}";
                
               
                
                restServiceAdapter.setRetryLimit(0);
                System.out.println("postData===============================" + postData);
                
                response = restServiceAdapter.send(postData);
                
                System.out.println("response===============================" + response);
                
                resp=new JSONObject(response);
                output=resp.getJSONObject("OutputParameters");
                JSONObject data=output.getJSONObject("X_SORT_OPTIONS_TL");
                QuotationSortList.sort_List.clear();
                quotationSortList.clear();
                if(data.get("X_SORT_OPTIONS_TL_ITEM") instanceof  JSONArray){
                  JSONArray segments=data.getJSONArray("X_SORT_OPTIONS_TL_ITEM");
                  for(int i=0;i<segments.length();i++) {
                    JSONObject notification=segments.getJSONObject(i);
                    String lookupType=notification.getString("LOOKUP_TYPE");
                    String lookupCode=notification.getString("LOOKUP_CODE");
                   
                    String meaning=notification.getString("MEANING");
                    QuotationSort sort=new QuotationSort(lookupType, lookupCode, meaning);
                    QuotationSortList.sort_List.add(sort);
                    quotationSortList.add(sort);
                    //System.out.println("Quotation List Size"+quotationSortList.size());  
                      
                  }
                
                }
                
                else if(data.get("X_SORT_OPTIONS_TL_ITEM") instanceof  JSONObject){
                   
                    JSONObject notification=data.getJSONObject("X_SORT_OPTIONS_TL_ITEM");
                    String lookupType=notification.getString("LOOKUP_TYPE");
                    String lookupCode=notification.getString("LOOKUP_CODE");
                    
                    String meaning=notification.getString("MEANING");
                    
                    
                    QuotationSort sort=new QuotationSort(lookupType, lookupCode, meaning);
                    QuotationSortList.sort_List.add(sort);
                    quotationSortList.add(sort);  
                   
                }
                    System.out.println("Quotation List Size"+QuotationSortList.sort_List.size());
                 
                    
                    
                
                
                }
                catch(Exception e){
                e.printStackTrace();
                }
            
            try{
                for(int k=0;k<QuotationSortList.sort_List.size();k++) {
                 QuotationSort qsrt=(QuotationSort)QuotationSortList.sort_List.get(k);
                    
                    if(qsrt.getMeaning().equalsIgnoreCase("Relevance")){
                        System.out.println("*****Defult_SpringBoardMultiOrg_getId"+qsrt.getMeaning()+"value of "+String.valueOf(k));
                         ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.QuotationSortBy}", String.class);
                         ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k)); 
                         String updateQuotSort=qsrt.getLookupCode();
                         
                            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.QuotationSortValue}", String.class);
                            ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), updateQuotSort);

                        }
                   
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
            
                    try{
            
      
         //Deliver to Location   
            
          restServiceAdapter = Model.createRestServiceAdapter();
         // Clear any previously set request properties, if any
         restServiceAdapter.clearRequestProperties();
         // Set the connection name
         restServiceAdapter.setConnectionName("enrich");
         
         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
          postData= "{\n" + 
         "\n" + 
         "  \"GET_DELIVER_TO_Input\" : {\n" + 
         "\n" + 
         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_deliver_to/\",\n" + 
         "\n" + 
         "   \"RESTHeader\": {\n" + 
         "\n" + 
         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
         "    },\n" + 
         "\n" + 
         "   \"InputParameters\": {\n" + 
         "\n" + 
         "        \"P_USER_ID\":"+userId+"\n" + 
         "\n" + 
         "     }\n" + 
         "\n" + 
         "  }\n" + 
         "\n" + 
         "}  ";
                                     restServiceAdapter.setRetryLimit(0);
            System.out.println("postData===============================" + postData);
             
             response = restServiceAdapter.send(postData);
             
             System.out.println("response===============================" + response); 
              resp=new JSONObject(response);
              output=resp.getJSONObject("OutputParameters");
            JSONObject data=new JSONObject();
          try{
              data=output.getJSONObject("X_DELIVER_TO_TL");
             DeliverToLocationList.s_jobs.clear();
             
             if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
               JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
               for(int i=0;i<segments.length();i++) {
                 JSONObject location=segments.getJSONObject(i);
                 String locationId=location.getString("LOCATION_ID");
                 String locationCode=location.getString("LOCATION_CODE");
                 String locationDescription=location.getString("DESCRIPTION");
                 DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                 DeliverToLocationList.s_jobs.add(loc);
               }
             
             }
             
             else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
                
                JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                 String locationId=location.getString("LOCATION_ID");
                 String locationCode=location.getString("LOCATION_CODE");
                 String locationDescription=location.getString("DESCRIPTION");
                 DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                 DeliverToLocationList.s_jobs.add(loc);
                
             }
             }
             catch(Exception e) {
                 e.printStackTrace();
             }
         
            
            
                    }
            
            catch(Exception e) {
                e.printStackTrace();
            }
            
           
            
            
            
             AmxAttributeBinding customerList = (AmxAttributeBinding) AdfmfJavaUtilities
                               .evaluateELExpression("#{bindings.RFQ}");
            AmxIteratorBinding amxListIterator =  customerList.getIteratorBinding();
            amxListIterator.refresh();
            
            
            AmxAttributeBinding deliverToList = (AmxAttributeBinding) AdfmfJavaUtilities
                              .evaluateELExpression("#{bindings.deliverToLocations}");
            AmxIteratorBinding amxListIterator1 =  deliverToList.getIteratorBinding();
            amxListIterator1.refresh();
            AmxAttributeBinding quotationSortList = (AmxAttributeBinding) AdfmfJavaUtilities
                                                      .evaluateELExpression("#{bindings.quotationSort}");
            AmxIteratorBinding quotationSortListIterator =  quotationSortList.getIteratorBinding();
            quotationSortListIterator.refresh();
            
            
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            System.out.println("Quotation List Size"+QuotationSortList.sort_List.size());
            
//            QuotationList.s_jobs.clear();
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.quotations.iterator}");   
//            vex.refresh();
            
        }
        catch(Exception e) {
            
            e.printStackTrace();
            
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Cannot connect to Services on Oracle Server.",
//                                         null,
//                                         null }); 
        }
        
        
    }

    public void getRequesitionDetail(ActionEvent actionEvent) {
        // Add event code here...
        
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                  "adf.mf.api.amx.doNavigation", new Object[] { "Requ_details" });    

    }
    
    
    
    
    
    

    public void onRFQChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        
//        try{
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
//        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
//        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
//            String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//          
//                
//        //        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
//        //        String needByDate =   sdf.format(new Date());
//        //        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), needByDate);
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
//        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_rfq/");
//        String postData= "{\n" + 
//        "  \"GET_RFQ_Input\" : {\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_req_summary/\",\n" + 
//        "   \"RESTHeader\": {\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//        "    },\n" + 
//        "   \"InputParameters\": {\n" + 
//        "        \"P_USER_ID\": \""+userId+"\"\n" + 
//        "     }\n" + 
//        "  }\n" + 
//        "}  \n";
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//            
//           String response = restServiceAdapter.send(postData);
//            
//          //  System.out.println("response===============================" + response);
//            JSONObject resp=new JSONObject(response);
//            JSONObject output=resp.getJSONObject("OutputParameters");
//            JSONObject summTBL=output.getJSONObject("X_RFQ_TL");
//            
//            RFQList.s_jobs.clear();
//            RFQ rfq1=new RFQ("", "", "Please Select", "","");
//            RFQList.s_jobs.add(rfq1);
//            
//            if(summTBL.get("X_RFQ_TL_ITEM") instanceof JSONArray) {
//               // System.out.println("Inside JSON ===============================" + response);                             
//                JSONArray summItm=summTBL.getJSONArray("X_RFQ_TL_ITEM");
//                for(int i=0;i<summItm.length();i++) {
//                    JSONObject data=(JSONObject)summItm.get(i);
//                    
//                    String rfqId=data.getString("RFQ_ID");
//                    String rfgNo=data.getString("RFQ_NUM");
//                    String itemDescription=data.getString("ITEM_DESCRIPTION");
//                    itemDescription=itemDescription+"-"+rfgNo;
//                    String rfqCloseDate=data.getString("RFQ_CLOSE_DATE");
//                    String rfqNeedByDate=data.getString("NEED_BY_DATE");
//                    if (rfqNeedByDate.contains("{")) {
//                        rfqNeedByDate = "";
//                    }
//                    
//                    RFQ rfq=new RFQ(rfqId, rfgNo, itemDescription, rfqCloseDate,rfqNeedByDate);
//                    RFQList.s_jobs.add(rfq);
//                    
//                }
//                
//                
//                
//            }
//            
//            if(summTBL.get("X_RFQ_TL_ITEM") instanceof JSONObject) {
//                   
//                JSONObject data=(JSONObject)summTBL.getJSONObject("X_RFQ_TL_ITEM");
//                
//                String rfqId=data.getString("RFQ_ID");
//                String rfgNo=data.getString("RFQ_NUM");
//                String itemDescription=data.getString("ITEM_DESCRIPTION");
//                itemDescription=itemDescription+"-"+rfgNo;
//                String rfqCloseDate=data.getString("RFQ_CLOSE_DATE");
//                String rfqNeedByDate=data.getString("NEED_BY_DATE");
//                if (rfqNeedByDate.contains("{")) {
//                                        rfqNeedByDate = "";
//                                    }
//                RFQ rfq=new RFQ(rfqId, rfgNo, itemDescription, rfqCloseDate,rfqNeedByDate);
//                RFQList.s_jobs.add(rfq);                                          
//            
//            }
//            
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        
//        
//        
//        
//        
//        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{bindings.RFQ.inputValue}", String.class);
//        String rfqItemDescription = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
//        if(!rfqItemDescription.equalsIgnoreCase("") && !rfqItemDescription.equalsIgnoreCase("Please Select")){
//        String arr[]=rfqItemDescription.split("-");
//        String rfqNo=arr[arr.length-1];
//        System.out.println("rfqId is =>"+rfqNo);
//        String rfqId="";
//        String rfqCloseDate="";
//        String rfqNeedByDate="";
//        
//        System.out.println("RFQList size is =======>"+RFQList.s_jobs.size());
//        for(int k=0;k<RFQList.s_jobs.size();k++) {
//            RFQ r=(RFQ)RFQList.s_jobs.get(k);
//            System.out.println(r.getRfqNo()+"="+rfqNo);
//            if(r.getRfqNo().equalsIgnoreCase(rfqNo)) {
//                rfqId=r.getRfqId();
//                rfqCloseDate=r.getRfqCloseDate();
//                rfqNeedByDate=r.getRfqNeedByDate();
//            }
//        }
//        
//        try{
//            
//            
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
//        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
//        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
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
//        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_quotations/");
//        String postData= "{\n" + 
//        "\n" + 
//        "  \"GET_QUOTATIONS_Input\" : {\n" + 
//        "\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_quotations/\",\n" + 
//        "\n" + 
//        "   \"RESTHeader\": {\n" + 
//        "\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//        "    },\n" + 
//        "\n" + 
//        "   \"InputParameters\": {\n" + 
//        "\n" + 
//        "        \"P_RFQ_ID\":"+rfqId+"\n" + 
//        "\n" + 
//        "     }\n" + 
//        "\n" + 
//        "  }\n" + 
//        "\n" + 
//        "}  ";
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//            
//           String response = restServiceAdapter.send(postData);
//            
//           System.out.println("response===============================" + response);
//            
//            
//            JSONObject resp=new JSONObject(response);
//            JSONObject output=resp.getJSONObject("OutputParameters");
//            JSONObject summTBL=output.getJSONObject("X_QUOTATIONS_TL");
//            //RequisitionLineItemsList.s_jobs.clear();
//            
//            mobile.QuotationList.s_jobs=new ArrayList();
//           // mobile.QuotationList.s_jobs.clear();
//           
//            if(summTBL.get("X_QUOTATIONS_TL_ITEM") instanceof JSONArray) {
//                System.out.println("inside json");
//                
//                JSONArray summItm=summTBL.getJSONArray("X_QUOTATIONS_TL_ITEM");
//               
//                for(int i=0;i<summItm.length();i++) {
//                    System.out.println("for");
//                    JSONObject data=(JSONObject)summItm.get(i);
//                    
//                    rfqId=data.getString("RFQ_ID");
//                    String itemDescription=data.getString("ITEM_DESCRIPTION");
//                    String quotationId=data.getString("QUOTATION_ID");
//                    String quotationNo=data.getString("QUOTATION_NUM");
//                    String quotationLineId=data.getString("QUOTATION_LINE_ID");
//                    String quotationLineNo=data.getString("QUOTATION_LINE_NUM");
//                    String vendorId=data.getString("VENDOR_ID");
//                    String vendorName=data.getString("VENDOR_NAME");
//                    String quantity=data.getString("QUANTITY");
//                    String uom=data.getString("UOM");
//                    String promiseDate=data.getString("PROMISE_DATE");
//                    if (promiseDate.contains("{")) {
//                                            promiseDate = "";
//                                        }
//                    String price=data.getString("PRICE");
//                    String currencyCode=data.getString("CURRENCY_CODE");
//                    String supplierQuotationNo=data.getString("SUPPLIER_QUOTATION_NUM");
//                    String needByDate=data.getString("NEED_BY_DATE");
//                  
//                    Quotation q=new Quotation(rfqId, itemDescription, quotationId, quotationNo, quotationLineId, quotationLineNo, vendorId, vendorName, quantity, uom, promiseDate, price, currencyCode, supplierQuotationNo,"/images/no.png",needByDate); 
//                    boolean ret=QuotationList.s_jobs.add(q);
//                    System.out.println("Add "+ret);
//               
//                }
//                
//                
//                
//                
//            }
//            
//            if(summTBL.get("X_QUOTATIONS_TL_ITEM") instanceof JSONObject) {
//                
//                JSONObject data=summTBL.getJSONObject("X_QUOTATIONS_TL_ITEM");
//                
//                rfqId=data.getString("RFQ_ID");
//                String itemDescription=data.getString("ITEM_DESCRIPTION");
//                String quotationId=data.getString("QUOTATION_ID");
//                String quotationNo=data.getString("QUOTATION_NUM");
//                String quotationLineId=data.getString("QUOTATION_LINE_ID");
//                String quotationLineNo=data.getString("QUOTATION_LINE_NUM");
//                String vendorId=data.getString("VENDOR_ID");
//                String vendorName=data.getString("VENDOR_NAME");
//                String quantity=data.getString("QUANTITY");
//                String uom=data.getString("UOM");
//                if (uom.contains("{")) {
//                    uom = "";
//                }
//                String promiseDate=data.getString("PROMISE_DATE");
//                if (promiseDate.contains("{")) {
//                    promiseDate = "";
//                }
//                String price=data.getString("PRICE");
//                if (price.contains("{")) {
//                    price = "";
//                }
//                String currencyCode=data.getString("CURRENCY_CODE");
//                if (currencyCode.contains("{")) {
//                    currencyCode = "";
//                }
//                String supplierQuotationNo=data.getString("SUPPLIER_QUOTATION_NUM");
//                
//                if (supplierQuotationNo.contains("{")) {
//                    supplierQuotationNo = "";
//                }
//                
//                String needByDate=data.getString("NEED_BY_DATE");
//                
//                if (needByDate.contains("{")) {
//                    needByDate = "";
//                }
//                
//                
//                Quotation q=new Quotation(rfqId, itemDescription, quotationId, quotationNo, quotationLineId, quotationLineNo, vendorId, vendorName, quantity, uom, promiseDate, price, currencyCode, supplierQuotationNo,"/images/no.png",needByDate); 
//                boolean ret=QuotationList.s_jobs.add(q);
//                System.out.println("Add "+ret);
//                
//            }
//            
//            
//            String arr_s[]=rfqCloseDate.split("T");
//            ValueExpression ve36 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationRFQCloseDate}", String.class);
//            ve36.setValue(AdfmfJavaUtilities.getAdfELContext(),arr_s[0]);
//            
//            
//            String arr1[]=rfqItemDescription.split("-"+rfqNo);
//            
//            
//            ValueExpression ve37 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDescription}", String.class);
//            ve37.setValue(AdfmfJavaUtilities.getAdfELContext(),arr1[0]);
//            
//            
//            ValueExpression ve38 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqQuotNo}", String.class);
//            ve38.setValue(AdfmfJavaUtilities.getAdfELContext(),rfqNo);
//            
//            ValueExpression ve39 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
//            ve39.setValue(AdfmfJavaUtilities.getAdfELContext(),rfqNeedByDate);
//            
//            
//            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
         
            
//            AmxIteratorBinding ib =(AmxIteratorBinding) AdfmfJavaUtilities.evaluateELExpression("#{bindings.quotations.iterator}");
//            ib.getIterator().createRowWithData(provider, insertFlag)
            
             
            
//              MethodExpression me1 = AdfmfJavaUtilities.getMethodExpression("#{bindings.setQuotations.execute}", Object.class, new Class[] {});
//              me1.invoke(AdfmfJavaUtilities.getAdfELContext(),new Object[]{QuotationList.s_jobs});
            
            
            
           
        try{
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.quotations.iterator}");   
            vex.setDataProvider(QuotationList.s_jobs);
            
            
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
           // System.out.println("Size is "+ mobile.QuotationList.s_jobs.size()+" "+vex.getTotalRowCount());
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
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

    public void getRFQ(ActionEvent actionEvent) {
        // Add event code here...
        
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
            
            if(summTBL.get("X_RFQ_TL_ITEM") instanceof JSONArray) {
               // System.out.println("Inside JSON ===============================" + response);                             
                JSONArray summItm=summTBL.getJSONArray("X_RFQ_TL_ITEM");
                for(int i=0;i<summItm.length();i++) {
                    JSONObject data=(JSONObject)summItm.get(i);
                    
                    String rfqId=data.getString("RFQ_ID");
                    String rfgNo=data.getString("RFQ_NUM");
                    String itemDescription=data.getString("ITEM_DESCRIPTION");
                    itemDescription=itemDescription+"-"+rfqId;
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
                                                             
            
            }
            
        //            AdfmfContainerUtilities.gotoFeature("mp.Requisition");
          
          
        //        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.RFQ.items}");
        //        vex.refresh();
            
            
         
                  
                  
            
            AmxAttributeBinding customerList = (AmxAttributeBinding) AdfmfJavaUtilities
                               .evaluateELExpression("#{bindings.RFQ}");
            AmxIteratorBinding amxListIterator =  customerList.getIteratorBinding();
            amxListIterator.refresh();
            
        }
        catch(Exception e) {
            
            e.printStackTrace();
            
        //            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
        //                                         AdfmfJavaUtilities.getFeatureName(),
        //                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
        //                                         "Cannot connect to Services on Oracle Server.",
        //                                         null,
        //                                         null });
        }
        
    }

    public void selectQuote(ActionEvent actionEvent) {
        // Add event code here...
        try{
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationRFQCloseDate}", String.class);
        String closeDate = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//                  Date sel_date=sdf.parse(closeDate);
//                  Date today_date=new Date();
//                  
//                  long diff = today_date.getTime() - sel_date.getTime();
//                  
//                  System.out.println("today_date "+sdf.format(today_date));
//                  System.out.println("Comparing Dates "+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
//                  
//                  if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)<0) {
//                         //less than today date
//                         System.out.println("Inside if ");
//                          AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                                     "confirm_quote",
//                                                                                     new Object[] { });
////                         AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
////                                                                                   "popupUtilsShowPopup", new Object[] {
////                                                                                   "showPopUp" });
//                      }
//                  else{
//                      
//                      pickQuotations("");
//                  }
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
        
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.quotations.iterator}");  
//        selected_item=(Quotation)vex.getDataProvider();
//       
//       
//        for(int i=0;i<vex.getTotalRowCount();i++)
//         {
//             vex.setCurrentIndex(i);
//            Quotation q=(Quotation)vex.getDataProvider();
//            GenericType row= (GenericType)vex.getCurrentRow();
//           
//            if(q.getQuotationId().equalsIgnoreCase(selected_item.getQuotationId()) && q.getQuotationLineId().equalsIgnoreCase(selected_item.getQuotationLineId())){
//               row.setAttribute("selected", new String("/images/check.png"));
//            }
//            else{
//                row.setAttribute("selected", new String("/images/uncheck.png"));
//            }
//            
//         }
//        
//        
//           vex.refresh();
        
        
        
        pickQuotations("");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void pickQuotations(String rr) {
//                BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.quotations.iterator}");  
//                selected_item=(Quotation)vex.getDataProvider();
//               
//               
//                for(int i=0;i<vex.getTotalRowCount();i++)
//                 {
//                     vex.setCurrentIndex(i);
//                    Quotation q=(Quotation)vex.getDataProvider();
//                    GenericType row= (GenericType)vex.getCurrentRow();
//                   
//                    if(q.getQuotationId().equalsIgnoreCase(selected_item.getQuotationId()) && q.getQuotationLineId().equalsIgnoreCase(selected_item.getQuotationLineId())){
//                       row.setAttribute("selected", new String("/images/yes.png"));
//                    }
//                    else{
//                        row.setAttribute("selected", new String("/images/no.png"));
//                    }
//                    
//                 }
//                
//                
////        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
////        ve.setValue(AdfmfJavaUtilities.getAdfELContext(),selected_item.getNeedByDate());
////        AdfmfJavaUtilities.flushDataChangeEvent();
//        vex.refresh();
         
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.pickQuotations.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }
    
    public void proceedPlaceOrder(String rr) {
        
//        try{
//            
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
//        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            
//        
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
//        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/convert_quote_to_req/");
//            
//             
//                  
//            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression(" #{pageFlowScope.quotationNeedByDate}", String.class);
//            String needByDate = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());    
//            
//            String arr[];
//            String need="";
//            SimpleDateFormat fdf=new SimpleDateFormat("MM/dd/yyyy");
//            SimpleDateFormat tdf=new SimpleDateFormat("yyyy-MM-dd");
//            if(!needByDate.equalsIgnoreCase("")){
//                arr=needByDate.split("T");
//                if(arr[0].contains("/")){
//                 need=tdf.format(fdf.parse(arr[0]));
//                }
//                else{
//                    need=arr[0];
//                }
//                 
//            }
//            
//            //please change the variable name
//            ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{bindings.deliverToLocations.inputValue}", String.class);
//            String deliverToLocation = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext()); 
//            String locationId="";
//            
//            for(int k=0;k<DeliverToLocationList.s_jobs.size();k++) {
//                DeliverToLocation loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get(k);
//                if(loc.getCode().equalsIgnoreCase(deliverToLocation)){
//                    locationId=loc.getId();
//                }
//            }
//            
//            
//            
//          
//            
//            
//        String postData= "{\n" + 
//        "  \"CONVERT_QUOTE_TO_REQ_Input\" : {\n" + 
//        "\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/convert_quote_to_req/\",\n" + 
//        "\n" + 
//        "   \"RESTHeader\": {\n" + 
//        "\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//        "    },\n" + 
//        "\n" + 
//        "   \"InputParameters\": {\n" + 
//        "\n" + 
//        "        \"P_QUOTE_RESPONSE_REC\": {\n" + 
//        "\n" + 
//        "          \"QUOTATION_ID\" : "+selected_item.getQuotationId()+",\n" + 
//        "\n" + 
//        "          \"QUOTATION_LINE_ID\" :"+selected_item.getQuotationLineId()+",\n" + 
//        "\n" + 
//        "          \"VENDOR_ID\" :"+ selected_item.getVendorId()+",\n" + 
//        "\n" + 
//        "          \"VENDOR_SITE_ID\" : \"\",\n" + 
//        "\n" + 
//        "          \"NEED_BY_DATE\" : \""+need+"\",\n" + 
//        "\n" + 
//        "          \"DELIVER_TO_LOCATION_ID\" :"+ locationId+",\n" + 
//        "\n" + 
//        "          \"USER_ID\" : "+userId+",\n" + 
//        "          \"RFQ_ID\" : "+selected_item.getRfqId()+"\n" + 
//        "\n" + 
//        "        }\n" + 
//        "\n" + 
//        "     }\n" + 
//        "\n" + 
//        "  }\n" + 
//        "\n" + 
//        "}  ";
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//            
//           String response = restServiceAdapter.send(postData);
//            System.out.println("response===============================" + response); 
//            
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                       "displayAlert",
//                                                                       new Object[] { });
//            
//            selected_item=new Quotation();
//            QuotationList.s_jobs.clear();
//            
//            
//            ValueExpression ve37 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDescription}", String.class);
//            ve37.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
//            
//            
//            ValueExpression ve38 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqQuotNo}", String.class);
//            ve38.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
//            
//            ValueExpression ve39 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
//            ve39.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
//            
//            ValueExpression ve40 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationRFQCloseDate}", String.class);
//            ve40.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
//            
//            
//            AdfmfJavaUtilities.flushDataChangeEvent();
//            
//            
//            RFQList.s_jobs.clear();
//            getQuotations("");
//            
//            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{bindings.RFQ.inputValue}", String.class);
//            ve41.setValue(AdfmfJavaUtilities.getAdfELContext(),"0");
//            
//            
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.quotations.iterator}");  
//            vex.refresh();
//            
//            //AdfmfContainerUtilities.resetFeature("mp.Quotation");
////           
////            
////            for(int i=0;i<vex.getTotalRowCount();i++)
////             {
////                 vex.setCurrentIndex(i);
////                Quotation q=(Quotation)vex.getDataProvider();
////                GenericType row= (GenericType)vex.getCurrentRow();
////                 row.setAttribute("selected", new String("/images/no.png"));
////             }
////            
////            AdfmfJavaUtilities.flushDataChangeEvent();
//            
//            
//        }
//        catch(Exception e) {
//            
//            e.printStackTrace();
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Cannot connect to Services on Oracle Server.",
//                                         null,
//                                         null }); 
//        }
//        
//        //getQuotations("");
//        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.proceedPlaceOrder.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

    public void placeOrder(ActionEvent actionEvent) {
        // Add event code here...
        try{
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationRFQCloseDate}", String.class);
        String closeDate = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                          Date sel_date=sdf.parse(closeDate);
                          Date today_date=sdf.parse(sdf.format(new Date()));
        
                          long diff = today_date.getTime() - sel_date.getTime();
        
                          System.out.println("today_date "+sdf.format(today_date));
                          System.out.println("Comparing Dates "+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        
                             if(sel_date.after(today_date)){
                                 
                                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                              "confirm_quote",
                                                                                              new Object[] { });
                                 
                               }
                               else if(sel_date.equals(today_date)){
                                  
                                   proceedPlaceOrder("");
                                  
                               }
                               else{
                                    
                                     proceedPlaceOrder("");
                                 }
        
        
        
        
        
//                          if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)<0) {
//                                 //less than today date
//                                 System.out.println("Inside if ");
//                                  AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                                             "confirm_quote",
//                                                                                             new Object[] { });
//        //                         AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
//        //                                                                                   "popupUtilsShowPopup", new Object[] {
//        //                                                                                   "showPopUp" });
//                              }
//                          else{
//        
//                              proceedPlaceOrder("");
//                          }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
        
      
    }
    
    
    public void needByDateChanged(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        try{
        
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quotationNeedByDate}", String.class);
            String needByDate = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
                  
         String arr[]=valueChangeEvent.getNewValue().toString().split("T");
         String date="";
            if(arr.length>1){
                 date=arr[0];    
            }
        
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date sel_date=sdf.parse(date);
            Date today_date=sdf.parse(sdf.format(new Date()));
            
            
            long diff = today_date.getTime() - sel_date.getTime();
            
            System.out.println("today_date "+sdf.format(today_date));
            System.out.println("Comparing Dates "+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            
            if(sel_date.after(today_date)){
               
               
            }
            else if(sel_date.equals(today_date)){
                
            }
            else{
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                             AdfmfJavaUtilities.getFeatureName(),
                                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                             "Need by date should be greater than or equal to current date",
                                                             null,
                                                             null });
                              needByDate="";
                              ve.setValue(AdfmfJavaUtilities.getAdfELContext(), needByDate);
                              AdfmfJavaUtilities.flushDataChangeEvent();
            }
            
            System.out.println("Formatted Date "+date);
//            if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)>0) {
//                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                             AdfmfJavaUtilities.getFeatureName(),
//                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                             "Need by date should be greater than or equal to current date",
//                                             null,
//                                             null });
//                needByDate="";
//                
//                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), needByDate);
//                AdfmfJavaUtilities.flushDataChangeEvent();
//                
//            }
            
            System.out.println("Formatted Date "+date);
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
                
              
        
    }
    
    public void getUserProfile(String rr) {
        
        //sdf
        
        try{
            
            
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
         //Deliver to Location   
            
         RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
         // Clear any previously set request properties, if any
         restServiceAdapter.clearRequestProperties();
         // Set the connection name
         restServiceAdapter.setConnectionName("enrich");
         
         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
         String postData= "{\n" + 
         "\n" + 
         "  \"GET_DELIVER_TO_Input\" : {\n" + 
         "\n" + 
         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_deliver_to/\",\n" + 
         "\n" + 
         "   \"RESTHeader\": {\n" + 
         "\n" + 
         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
         "    },\n" + 
         "\n" + 
         "   \"InputParameters\": {\n" + 
         "\n" + 
         "        \"P_USER_ID\":"+userId+"\n" + 
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
            JSONObject data=new JSONObject();
          try{
              data=output.getJSONObject("X_DELIVER_TO_TL");
             DeliverToLocationList.s_jobs.clear();
                 deliverToLocationList.clear();
             if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
               JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
               for(int i=0;i<segments.length();i++) {
                 JSONObject location=segments.getJSONObject(i);
                 String locationId=location.getString("LOCATION_ID");
                 String locationCode=location.getString("LOCATION_CODE");
                 String locationDescription=location.getString("DESCRIPTION");
                 DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                 DeliverToLocationList.s_jobs.add(loc);
                   deliverToLocationList.add(loc);
               }
             
             }
             
             else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
                
                JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                 String locationId=location.getString("LOCATION_ID");
                 String locationCode=location.getString("LOCATION_CODE");
                 String locationDescription=location.getString("DESCRIPTION");
                 DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                 DeliverToLocationList.s_jobs.add(loc);
                 deliverToLocationList.add(loc);
             }
             }
             catch(Exception e) {
                 e.printStackTrace();
             }
            
            
            //Multiorg
 
                restServiceAdapter = Model.createRestServiceAdapter();
                // Clear any previously set request properties, if any
                restServiceAdapter.clearRequestProperties();
                // Set the connection name
                restServiceAdapter.setConnectionName("enrich");
                
                restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                restServiceAdapter.setRequestURI("/webservices/rest/XXEUserService/get_user_org_access/");
                postData="{\n" + 
                     "\n" + 
                     "    \"GET_USER_ORG_ACCESS_Input\":{\n" + 
                     "        \"RESTHeader\":{\n" + 
                     "        },\n" + 
                     "        \"InputParameters\":{\n" + 
                     "            \"P_USER_ID\": "+userId+"\n" + 
                     "        }\n" + 
                     "    }\n" + 
                     "\n" + 
                     "}";
                
                                            restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                    
                   response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response); 
                    resp=new JSONObject(response);
                    output=resp.getJSONObject("OutputParameters");
                   JSONObject data1=new JSONObject();
                try{
                     data1=output.getJSONObject("X_ORG_ACCESS_TL");
                     MultiOrgList.org_List.clear();
                     multiOrgList.clear();
                   if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONArray){
                     MultiOrg m1=new MultiOrg("Please Select","Please Select"); 
                       MultiOrgList.org_List.add(m1);
                       multiOrgList.add(m1);
                     JSONArray segments=data1.getJSONArray("X_ORG_ACCESS_TL_ITEM");
                     for(int i=0;i<segments.length();i++) {
                       JSONObject org=segments.getJSONObject(i);
                       String orgId=org.getString("ORG_ID");
                       String orgName=org.getString("ORG_NAME");
                       MultiOrg mulList=new MultiOrg(orgId, orgName);
                       MultiOrgList.org_List.add(mulList);
                       multiOrgList.add(mulList);
                         
                     }
                       
                       
                     System.out.println("++++MULTIORGLIST"+MultiOrgList.org_List.size());
                   }
                       else if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONObject){
                         MultiOrg m1=new MultiOrg("Please Select","Please Select"); 
                         MultiOrgList.org_List.add(m1);
                         multiOrgList.add(m1);
                           JSONObject org=data1.getJSONObject("X_ORG_ACCESS_TL_ITEM");
                            String orgId=org.getString("ORG_ID");
                            String orgName=org.getString("ORG_NAME");
                           MultiOrg mulList=new MultiOrg(orgId, orgName);
                           MultiOrgList.org_List.add(mulList);
                           multiOrgList.add(mulList);
                       }

                       
                   
                   }
                   catch(Exception e) {
                       e.printStackTrace();
                   }
            AmxAttributeBinding MulOrg = (AmxAttributeBinding) AdfmfJavaUtilities
                              .evaluateELExpression("#{bindings.multiOrgs}");
            AmxIteratorBinding orgListIterator =  MulOrg.getIteratorBinding();
            orgListIterator.refresh();

                
                 //end MultiOrg
            
            
//            //cost center
//            
//            restServiceAdapter = Model.createRestServiceAdapter();
//            // Clear any previously set request properties, if any
//            restServiceAdapter.clearRequestProperties();
//            // Set the connection name
//            restServiceAdapter.setConnectionName("enrich");
//            
//            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cost_center/");
//            postData= "{\n" + 
//            "\n" + 
//            "  \"GET_COST_CENTER_Input\" : {\n" + 
//            "\n" + 
//            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_cost_center/\",\n" + 
//            "\n" + 
//            "   \"RESTHeader\": {\n" + 
//            "\n" + 
//            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//            "    },\n" + 
//            "\n" + 
//            "   \"InputParameters\": {\n" + 
//            "\n" + 
//            "          \"P_USER_ID\" : "+userId+"\n" + 
//            "\n" + 
//            "     }\n" + 
//            "\n" + 
//            "  }\n" + 
//            "\n" + 
//            "}  ";
//                                        restServiceAdapter.setRetryLimit(0);
//               System.out.println("postData===============================" + postData);
//                
//                response = restServiceAdapter.send(postData);
//                
//                System.out.println("response===============================" + response); 
//                 resp=new JSONObject(response);
//                 output=resp.getJSONObject("OutputParameters");
//            try{
//                 data=output.getJSONObject("X_COST_CENTER_TL");
//                CostCenterList.s_jobs.clear();
//                
//                if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
//                  JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
//                  for(int i=0;i<segments.length();i++) {
//                      String name=(String)segments.get(i);
//                      CostCenter c=new CostCenter(name);
//                      CostCenterList.s_jobs.add(c);
//                      
//                  }
//                
//                }
//                
//                else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
//                   
//                   JSONObject segments=data.getJSONObject("X_COST_CENTER_TL_ITEM");
//                   
//                }
//                }
//                catch(Exception e) {
//                    e.printStackTrace();
//                }
            
                 
                 //get user Preferences
            
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_user_preferences/");
                 postData= "\n" + 
                 "{\n" + 
                 "  \"GET_USER_PREFERENCES_Input\" : {\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_user_preferences/\",\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                 "    },\n" + 
                 "   \"InputParameters\": {\n" + 
                 "                   \"P_USER_ID\" :"+userId+"\n" + 
                 "       }         \n" + 
                 "   }\n" + 
                 "}\n";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                     response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                 try{
                      data=output.getJSONObject("X_USER_PREFERENCE_TL");
                     if(data.get("X_USER_PREFERENCE_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_USER_PREFERENCE_TL_ITEM");
                       for(int i=0;i<segments.length();i++) {
                           JSONObject pref= segments.getJSONObject(i);
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("DELIVER_TO")) {
                               
                               for(int k=0;k<DeliverToLocationList.s_jobs.size();k++) {
                                   DeliverToLocation loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get(k);
                                   if(loc.getId().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE")))     {
                                         System.out.println("*****Loc.getId"+loc.getId()+"ATTRIBUTE_VALUE"+pref.getString("ATTRIBUTE_VALUE")+"Location Discription"+loc.getDescription()+"Location Code"+loc.getCode()+"value of "+String.valueOf(k));
                                         
                                         String deliver= loc.getCode();
                                       ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.Prefered_UserLocation}", String.class);
                                                                              ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));  
                                       
                                             System.out.println("Setting Value"+ve12+"^^^^^^"+deliver);
                                       /*ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{bindings.deliverToLocations.inputValue}", String.class);
                                       ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));*/
                                   }
                                   
                               }
                                     
                           }
                           
                           //Multi Org Value Set
                           
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("ORG_ID")) {
                               
                               for(int k=0;k<MultiOrgList.org_List.size();k++) {
                                   MultiOrg mOrg=(MultiOrg)MultiOrgList.org_List.get(k);
                                   if(mOrg.getOrgId().equalsIgnoreCase(pref.getString("ATTRIBUTE_VALUE")))     {
                                         System.out.println("Multi OrgID"+mOrg.getOrgId()+"ATTRIBUTE_VALUE"+pref.getString("ATTRIBUTE_VALUE")+"MultiOrg Discription"+mOrg.getOrgName()+"value of "+String.valueOf(k));
                                         
                                        String multiOrg= mOrg.getOrgId();
                                       ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.profileselectedMultiOrg}", String.class);
                                       ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));  
                                       
                                             System.out.println("Setting MultiOrgValue"+ve12+"^^^^^^"+multiOrg);
                                       /*ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{bindings.deliverToLocations.inputValue}", String.class);
                                       ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));*/
                                   }
                                   
                               }
                                     
                           }
                           
                           
                           
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("HOME_PAGE")) {
                               
                               ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.landing_screen}", String.class);
                               ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),pref.getString("ATTRIBUTE_VALUE"));
                               
                           }
                       }
                     
                     }
                     
                     else if(data.get("X_USER_PREFERENCE_TL_ITEM") instanceof  JSONObject){
                        
                        JSONObject segments=data.getJSONObject("X_USER_PREFERENCE_TL_ITEM");
                        
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
            
            
        
        
        
              
        
           
            
            
            
            
            
         
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
        
        AmxAttributeBinding deliverToList = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.deliverToLocations}");
        AmxIteratorBinding amxListIterator1 =  deliverToList.getIteratorBinding();
        amxListIterator1.refresh();
        
        
        
        AmxAttributeBinding costCenterList = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.costCenters}");
        AmxIteratorBinding costCenterIterator1 =  costCenterList.getIteratorBinding();
        costCenterIterator1.refresh();
        
        
        
        
            
            
    }


    public void homeScreenChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.home_screen}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(),valueChangeEvent.getNewValue().toString());
        
        
    }

    public void updateMemberPreference(ActionEvent actionEvent) {
        // Add event code here...
        
        //String multiOrgValue1="83";
        
     
        try{
            
               
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        // ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{bindings.deliverToLocations.inputValue}", String.class);
         
        
        
              
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.Prefered_UserLocation}", String.class);
        String locationName=(String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
            
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.profileselectedMultiOrg}", String.class);
        String multiOrgId=(String)ve14.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            System.out.println("++++Multiorg name+++++"+multiOrgId);
            
           
        System.out.println("++++Location name+++++"+locationName);
        String locationId="";
            
            
            //Deliver to Location   
               
            RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
            // Clear any previously set request properties, if any
            restServiceAdapter.clearRequestProperties();
            // Set the connection name
            restServiceAdapter.setConnectionName("enrich");
            
            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
            String postData= "{\n" + 
            "\n" + 
            "  \"GET_DELIVER_TO_Input\" : {\n" + 
            "\n" + 
            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_deliver_to/\",\n" + 
            "\n" + 
            "   \"RESTHeader\": {\n" + 
            "\n" + 
            "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
            "    },\n" + 
            "\n" + 
            "   \"InputParameters\": {\n" + 
            "\n" + 
            "        \"P_USER_ID\":"+userId+"\n" + 
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
               JSONObject data=new JSONObject();
             try{
                 data=output.getJSONObject("X_DELIVER_TO_TL");
                DeliverToLocationList.s_jobs.clear();
                    deliverToLocationList.clear();
                if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
                  JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
                  for(int i=0;i<segments.length();i++) {
                    JSONObject location=segments.getJSONObject(i);
                    String locationId1=location.getString("LOCATION_ID");
                    String locationCode=location.getString("LOCATION_CODE");
                    String locationDescription=location.getString("DESCRIPTION");
                    DeliverToLocation loc=new DeliverToLocation(locationId1, locationCode, locationDescription);
                    DeliverToLocationList.s_jobs.add(loc);
                      deliverToLocationList.add(loc);
                  }
                
                }
                
                else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
                   
                   JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                    String locationId1=location.getString("LOCATION_ID");
                    String locationCode=location.getString("LOCATION_CODE");
                    String locationDescription=location.getString("DESCRIPTION");
                    DeliverToLocation loc=new DeliverToLocation(locationId1, locationCode, locationDescription);
                    DeliverToLocationList.s_jobs.add(loc);
                    deliverToLocationList.add(loc);
                }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            
            
//Location
                        String locDeliver="";
            if(!locationName.equalsIgnoreCase("")){
                
                DeliverToLocation loc=(DeliverToLocation)deliverToLocationList.get((Integer.parseInt(locationName)));
               locDeliver=loc.getCode();
                System.out.println("UOM++++++++"+locDeliver);
            }
            else{
                DeliverToLocation loc=(DeliverToLocation)deliverToLocationList.get(0);
                locDeliver=loc.getCode();
                System.out.println("UOM++++++++"+locDeliver);
            }
            
            System.out.println("UOM++++++++"+locDeliver);
            
            
            
            
            
            
        
            System.out.println("Deliver to size is "+deliverToLocationList.size()+" loca name is "+locationName );
            for(int k=0;k<deliverToLocationList.size();k++) {
                DeliverToLocation loc=(DeliverToLocation)deliverToLocationList.get(k);
                System.out.println("location "+loc.getCode()+"-"+locDeliver);
                if(loc.getCode().trim().equalsIgnoreCase(locDeliver.trim()))     {
                    locationId=loc.getId();
                    System.out.println("Match occurs location id is "+locationId);
                    ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to}", String.class);
                    ve49.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k)); 
                }
            }
            

            

            //Multiorg
            
                restServiceAdapter = Model.createRestServiceAdapter();
                // Clear any previously set request properties, if any
                restServiceAdapter.clearRequestProperties();
                // Set the connection name
                restServiceAdapter.setConnectionName("enrich");
                
                restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                restServiceAdapter.setRequestURI("/webservices/rest/XXEUserService/get_user_org_access/");
                postData="{\n" + 
                     "\n" + 
                     "    \"GET_USER_ORG_ACCESS_Input\":{\n" + 
                     "        \"RESTHeader\":{\n" + 
                     "        },\n" + 
                     "        \"InputParameters\":{\n" + 
                     "            \"P_USER_ID\": "+userId+"\n" + 
                     "        }\n" + 
                     "    }\n" + 
                     "\n" + 
                     "}";
                
                                            restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                    
                   response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response); 
                    resp=new JSONObject(response);
                    output=resp.getJSONObject("OutputParameters");
                   JSONObject data1=new JSONObject();
                try{
                     data1=output.getJSONObject("X_ORG_ACCESS_TL");
                     MultiOrgList.org_List.clear();
                     multiOrgList.clear();
                       MultiOrg m1=new MultiOrg("Please Select","Please Select"); 
                       MultiOrgList.org_List.add(m1);
                       multiOrgList.add(m1);
                   if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONArray){
                     JSONArray segments=data1.getJSONArray("X_ORG_ACCESS_TL_ITEM");
                     for(int i=0;i<segments.length();i++) {
                       JSONObject org=segments.getJSONObject(i);
                       String orgId=org.getString("ORG_ID");
                       String orgName=org.getString("ORG_NAME");
                       MultiOrg mulList=new MultiOrg(orgId, orgName);
                       MultiOrgList.org_List.add(mulList);
                       multiOrgList.add(mulList);
                         
                     }
                       
                       
                     System.out.println("++++MULTIORGLIST"+MultiOrgList.org_List.size());
                   }
                       else if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONObject){
                           JSONObject org=data1.getJSONObject("X_ORG_ACCESS_TL_ITEM");
                            String orgId=org.getString("ORG_ID");
                            String orgName=org.getString("ORG_NAME");
                           MultiOrg mulList=new MultiOrg(orgId, orgName);
                           MultiOrgList.org_List.add(mulList);
                           multiOrgList.add(mulList);
                           System.out.println("---->"+orgId+orgName+multiOrgList.size());
                       }
                       System.out.println("++++Single-ORGLIST"+MultiOrgList.org_List.size());
                   }
                   catch(Exception e) {
                       e.printStackTrace();
                   }

            //////////// MultiOrg
            
            System.out.println("MultiOrg_ID--->"+multiOrgId);
            String multiorg="";
            if(!multiOrgId.equalsIgnoreCase("")){
                
                MultiOrg mOrg=(MultiOrg)multiOrgList.get((Integer.parseInt(multiOrgId)));
               multiorg=mOrg.getOrgId();
                System.out.println("MUL++++++++"+multiorg);
            }
            else{
                MultiOrg mOrg=(MultiOrg)multiOrgList.get(0);
                multiorg=mOrg.getOrgId();
                System.out.println("MUL++++++++"+multiorg);
            }
            
            System.out.println("MUL++++++++"+multiorg);
            
            
            
            if(!multiorg.equalsIgnoreCase("Please Select"))
            {
            
            
            ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.home_screen}", String.class);
            String home_page=(String)ve13.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),multiorg); 
         
            System.out.println("MultiOrgValue--->"+multiorg+ve15);
        
        restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/update_user_preferences/");
            
        
        postData= "{\n" + 
        "\n" + 
        "  \"UPDATE_USER_PREFERENCES_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/update_user_preferences/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "\n" + 
        "   \"InputParameters\": {\n" + 
        "                   \"P_USER_ID\" : "+userId+"\n" + 
        "                  ,\"P_USER_PREFERENCE_TL\" : { \"P_USER_PREFERENCE_TL_ITEM\" : [\n" + 
        "\n" + 
        "{\n" + 
        "    \"ATTRIBUTE_NAME\" : \"HOME_PAGE\",\n" + 
        "    \"ATTRIBUTE_VALUE\" : \""+home_page+"\",\n" + 
        "    \"USER_MODIFIED\" : \"Y\"\n" + 
        "\n" + 
        "},\n" + 
        "{\n" + 
        "    \"ATTRIBUTE_NAME\" : \"DELIVER_TO\",\n" + 
        "    \"ATTRIBUTE_VALUE\" : \""+locationId+"\",\n" + 
        "    \"USER_MODIFIED\" : \"Y\"\n" + 
        "},\n" +
         "{\n" + 
         "    \"ATTRIBUTE_NAME\" : \"ORG_ID\",\n" + 
         "    \"ATTRIBUTE_VALUE\" : \""+multiorg+"\",\n" + 
         "    \"USER_MODIFIED\" : \"Y\"\n" + 
         "}\n" + 
                     
        "\n" + 
        "\n" + 
        "     ]\n" + 
        "\n" + 
        "   }\n" + 
        "\n" + 
        "   }         \n" + 
        "\n" + 
        "   }\n" + 
        "\n" + 
        "}";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response); 
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                       "displayAlert",
                                                                       new Object[] { });
            
        
            AdfmfContainerUtilities.resetFeature("mp.springboard",true);
            AdfmfContainerUtilities.resetFeature("feature1",false);
            AdfmfContainerUtilities.resetFeature("mp.Requisition",false);
            AdfmfContainerUtilities.resetFeature("mp.Quotation",false);
            AdfmfContainerUtilities.resetFeature("mp.Notification",false);    
            
        }
            else {
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                           "checkMultiOrgValue",
                                                                           new Object[] { });

            }
        }
            
        catch(Exception e){
            e.printStackTrace();
        }
        
        /*try{
            for(int k=0;k<MultiOrgList.org_List.size();k++) {
             MultiOrg mul=(MultiOrg)MultiOrgList.org_List.get(k);
                //System.out.println("*****mul.getId"+mul.getOrgId()+"Application Scope"+mulOrg);
                
                if(mul.getOrgId().equals(updatemultiorg)){
                    System.out.println("*****mul.getId"+mul.getOrgId()+"value of "+String.valueOf(k));
                     ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.profileselectedMultiOrg}", String.class);
                     ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));  

                    }
                else {
                    ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.profileselectedMultiOrg}", String.class);
                    ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                    System.out.println("Not Inside the MultiOrg");
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }*/
        
    }
     
    public void showNotifications(String rr) {
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
        restServiceAdapter.setRequestURI("/webservices/rest/XXE_MOBILE_NOTIFN_SERVICE/show_notifications/");
        String postData= "{\n" + 
        "  \"SHOW_NOTIFICATIONS_Input\" : {\n" + 
        "   \"RESTHeader\": {\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "        	   \"P_USER_ID\":"+ userId+"\n" + 
        "       }	   \n" + 
        "   }\n" + 
        "}";
            
            AlertsList.alertList.clear();
           restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            JSONObject data=output.getJSONObject("X_NOTIFICATIONS_TL");
            if(data.get("X_NOTIFICATIONS_TL_ITEM") instanceof  JSONArray){
              JSONArray segments=data.getJSONArray("X_NOTIFICATIONS_TL_ITEM");
              for(int i=0;i<segments.length();i++) {
                JSONObject notification=segments.getJSONObject(i);
                String notificationId=notification.getString("NOTIFICATION_ID");
                String notificationTitle=notification.getString("SUBJECT");
               
                String messageType=notification.getString("MESSAGE_TYPE");
                String messageName =notification.getString("MESSAGE_NAME");
                
                //System.out.println(notificationId+"="+notificationTitle+"="+notificationReason);  
                Alert a=new Alert(notificationId,notificationTitle, "", "", "",messageType,messageName);
                AlertsList.alertList.add(a);  
              }
            
            }
            
            else if(data.get("X_NOTIFICATIONS_TL_ITEM") instanceof  JSONObject){
               
                JSONObject notification=data.getJSONObject("X_NOTIFICATIONS_TL_ITEM");
                String notificationId=notification.getString("NOTIFICATION_ID");
                String notificationTitle=notification.getString("SUBJECT");
                
                String messageType=notification.getString("MESSAGE_TYPE");
                String messageName =notification.getString("MESSAGE_NAME");
                
                Alert a=new Alert(notificationId,notificationTitle, "", "", "",messageType,messageName);
                AlertsList.alertList.add(a);  
               
            }
            
            
            
            
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.alerts.iterator}");   
            vex.refresh();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    public String showNotificationDetail() {
        // Add event code here...
//        ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationId}", String.class);
//        String notificationId=(String)ve13.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        for(int k=0;k<AlertsList.alertList.size();k++)
//        {
//          Alert al=(Alert)AlertsList.alertList.get(k);
//           if(al.getId().equalsIgnoreCase(notificationId)) {
//               ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationTitle}", String.class);
//               ve14.setValue(AdfmfJavaUtilities.getAdfELContext(), al.getTitle());
//               
//               ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationReason}", String.class);
//               ve15.setValue(AdfmfJavaUtilities.getAdfELContext(), al.getReason());
//                   
//           }
//        }
        
        return "Details_notification";
    }

    public void showSearchPage(ActionEvent actionEvent) {
        // Add event code here...
        
       // AdfmfContainerUtilities.gotoFeature("feature1"); 
//        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
//                                                                          "adf.mf.api.amx.doNavigation", new Object[] { "valid_login" });   
        
    }

    public void showQuotations(ActionEvent actionEvent) {
        // Add event code here...
        getQuotations("");
        AdfmfContainerUtilities.gotoFeature("mp.Quotation");
            
    }

    public void doSignOut(ActionEvent actionEvent) {
        // Add event code here...
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve131 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        ve131.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.password}", String.class);
        ve1.setValue(AdfmfJavaUtilities.getAdfELContext(),"");

        ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ValueExpression fve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_firstName}", String.class);
        fve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ValueExpression lve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_lastName}", String.class);
        lve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");

        
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
        ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to}", String.class);
        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
        ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        ve23.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        AdfmfJavaUtilities.flushDataChangeEvent();
        AdfmfContainerUtilities.resetFeature("feature1",false);   
        AdfmfContainerUtilities.resetFeature("mp.Requisition",false);
        AdfmfContainerUtilities.resetFeature("mp.Userprofile",false);
        AdfmfContainerUtilities.resetFeature("mp.Notification",false);
        AdfmfContainerUtilities.resetFeature("mp.Quotation",false);
        AdfmfContainerUtilities.resetFeature("mp.springboard");
        
//        ItemsList.cpy_list.clear();
//        ItemsList.filt_list.clear();
//        ItemsList.items_ref.clear();
//        ItemsList.items_selected.clear();
        try{
      //  ItemsList.s_jobs.clear();
            
            
            
        }
        catch(Exception e) {
                        StringWriter sw = new StringWriter();
                        e.printStackTrace(new PrintWriter(sw));
            
            
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                      "adf.mf.api.amx.addMessage", new Object[] {
                                                                      AdfException.ERROR,
                                                                      "Inside Exception===> " + sw.toString(),
                                                                      null, null
                        });
        }
        
//        SelectedItemsList.items_selected.clear();
//        SelectedItemsList.purchase_items.clear();
//        SelectedItemsList.s_jobs.clear();
//        
//        RequisitionsList.s_jobs.clear();
//        RequisitionLineItemsList.s_jobs.clear();
//        
//        RFQList.s_jobs.clear();
//        QuotationList.s_jobs.clear();

        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
      AdfmfContainerUtilities.gotoFeature("mp.login");
      AdfmfContainerUtilities.resetFeature("mp.login");
    }

    public void showRequesitionScreen(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfContainerUtilities.gotoFeature("mp.Requisition");
        AdfmfContainerUtilities.resetFeature("mp.Requisition");
    }
    
    public void showSearchScreen(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfContainerUtilities.gotoFeature("feature1");
        AdfmfContainerUtilities.resetFeature("feature1");
    }
    
    public void showQuotationScreen(ActionEvent actionEvent) {
        // Add event code here...
        //AdfmfContainerUtilities.resetFeature("mp.Quotation");
        getQuotations("");
        AdfmfContainerUtilities.gotoFeature("mp.Quotation");
        
    }
    
    public void showNotificationScreen(ActionEvent actionEvent) {
        // Add event code here...
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String DefaultMultiOrgOrg=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        if(DefaultMultiOrgOrg.contains(("{\"@xsi:nil\":\"true\"}"))) {
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                           "chooseMultiOrg",
                                                                                           new Object[] { });
        }
        else{
        AdfmfContainerUtilities.resetFeature("mp.Notification");
        AdfmfContainerUtilities.gotoFeature("mp.Notification");
        }
        
    }
    
    public void showUserProfileScreen(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfContainerUtilities.gotoFeature("mp.Userprofile");
        AdfmfContainerUtilities.resetFeature("mp.Userprofile");
    }

    public void showNotificationDetailScreen(ActionEvent actionEvent) {
        // Add event code here...
              
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationId}", String.class);
        String s=(String)ve18.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        
        ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationMessageType}", String.class);
        String type=(String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve27 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationMessageName}", String.class);
        String name=(String)ve27.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        
        
        System.out.println("Notification id ="+s+" ="+AlertsList.alertList.size());
        
        String messageType=type;
        String notificationId=s;
        
                        if(name.equalsIgnoreCase("PO_REQ_APPROVE_SIMPLE_JRAD") || name.equalsIgnoreCase("PO_REQ_APPROVE_JRAD")){
                            System.out.println("Match occurs");
                        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayFYI}", String.class);
                        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                        
                        ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayApprove}", String.class);
                        ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                            
                        
                        }
                       else {
                            ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayFYI}", String.class);
                            ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                            
                            ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayApprove}", String.class);
                            ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                        }
        
        
        
   
//        for(int z=0;z<AlertsList.alertList.size();z++) {
//            Alert a=(Alert)AlertsList.alertList.get(z);
//            System.out.println(a.getId()+"="+s);
//            if(a.getId().equalsIgnoreCase(s)) {
//                messageType=a.getType();
//                notificationId=a.getId();
//               
//                if(a.getType().equalsIgnoreCase("REQAPPRV")){
//                ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayFYI}", String.class);
//                ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
//                
//                ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayApprove}", String.class);
//                ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//                    
//                
//                }
//               else {
//                    ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayFYI}", String.class);
//                    ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//                    
//                    ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayApprove}", String.class);
//                    ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
//                }
//                
//            }
//        }
//        
        
        
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
        restServiceAdapter.setRequestURI("/webservices/rest/XXE_MOBILE_NOTIFN_SERVICE/get_message_text/");
        String postData= "{\n" + 
        "  \"GET_MESSAGE_TEXT_Input\" : {\n" + 
        "   \"RESTHeader\": {\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "       \"P_MESSAGE_TYPE\": \""+messageType+"\",\n" + 
        "       \"P_MESSAGE_NAME\": \""+name+"\",\n" + 
        "       \"P_NOTIFICATION_ID\" : "+notificationId+"\n" + 
        "       }    \n" + 
        "   }\n" + 
        "}";
            
           
           restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            String  data=output.getString("X_MESSAGE_TEXT");
            
            
       
          ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationReason}", String.class);
            ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),data);
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            
           
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
     
     //Get Rejection Code Reasons
        try{
            
            
        
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
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_req_reject_reason_codes/");
        String postData= "{\n" + 
        "  \"GET_REQ_REJECT_REASON_CODES_Input\" : {\n" + 
        "   \"RESTHeader\": {\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "       }	   \n" + 
        "   }\n" + 
        "}";
            
           
            RejectionReasonList.rej_List.clear();
            restServiceAdapter.setRetryLimit(0);
            System.out.println("postData===============================" + postData);
            
            String response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            JSONObject data=output.getJSONObject("X_REQ_REJECT_CODES_TL");
            RejectionReasonList.rej_List.clear();
            rejectionReasonList.clear();
                Rejection r1=new Rejection("Please Select","Please Select","Please Select"); 
                RejectionReasonList.rej_List.add(r1);
                rejectionReasonList.add(r1);
            if(data.get("X_REQ_REJECT_CODES_TL_ITEM") instanceof  JSONArray){
              JSONArray segments=data.getJSONArray("X_REQ_REJECT_CODES_TL_ITEM");
              for(int i=0;i<segments.length();i++) {
                JSONObject notification=segments.getJSONObject(i);
                String lookupType=notification.getString("LOOKUP_TYPE");
                String lookupCode=notification.getString("LOOKUP_CODE");
               
                String meaning=notification.getString("MEANING");
                Rejection rejc=new Rejection(lookupType, lookupCode, meaning);
                RejectionReasonList.rej_List.add(rejc);
                rejectionReasonList.add(rejc);
                System.out.println("Rejection List Size"+rejectionReasonList.size());  
                  
              }
            
            }
            
            else if(data.get("X_REQ_REJECT_CODES_TL_ITEM") instanceof  JSONObject){
               
                JSONObject notification=data.getJSONObject("X_REQ_REJECT_CODES_TL_ITEM");
                String lookupType=notification.getString("LOOKUP_TYPE");
                String lookupCode=notification.getString("LOOKUP_CODE");
                
                String meaning=notification.getString("MEANING");
                
                
                Rejection rejc=new Rejection(lookupType, lookupCode, meaning);
                RejectionReasonList.rej_List.add(rejc);
                rejectionReasonList.add(rejc);  
               
            }
                System.out.println("Rejection List Size"+RejectionReasonList.rej_List.size());
             
              AmxAttributeBinding rejectionList = (AmxAttributeBinding) AdfmfJavaUtilities
                                                      .evaluateELExpression("#{bindings.rejection}");
                                    AmxIteratorBinding rejectionListIterator =  rejectionList.getIteratorBinding();
                                    rejectionListIterator.refresh();
            
            
            }
            catch(Exception e){
            e.printStackTrace();
            }
                
        
        
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                       "adf.mf.api.amx.doNavigation", new Object[] { "detail" });    
    }
    
    public String showCart() {
        // Add event code here...
        
        
        //String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        SelectedItemsList.items_selected.clear();
        SelectedItemsList.s_jobs.clear();
        
        for(int i=0;i<RequisitionLineItemsList.s_jobs.size();i++) {
            RequisitionLineItem rl=(RequisitionLineItem)RequisitionLineItemsList.s_jobs.get(i);
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(1000000000);
            String itemType=rl.getLineType();
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
            ve41.setValue(AdfmfJavaUtilities.getAdfELContext(),itemType);
            
            SelectedItem sl=new SelectedItem(rl.getPoNo(), rl.getVendorName(), rl.getVendorSite(), rl.getCategory(), rl.getProductTitle(), rl.getUnitPrice(), "", "true", "O", rl.getUom(), rl.getQuantity(), rl.getDeliverToLocation(), rl.getNeedByDate(), rl.getLineTotal(), String.valueOf(randomInt), "","0","","","","","","","","","","","","","","","","","","","");
            SelectedItemsList.items_selected.add(sl);
        }
        
       SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
        int count=SelectedItemsList.items_selected.size();
        
        String default_cost_center_id="";
        String default_cost_center_name="";
        String default_deliver_to_location_id="";
        String default_deliver_to_location_name="";
        
        try{
            
            
            
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
         ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
         default_cost_center_id = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());
            
         ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to}", String.class);
         default_deliver_to_location_id = (String)ve49.getValue(AdfmfJavaUtilities.getAdfELContext());    
            
            
            
            
         //Deliver to Location   
            
         RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
         // Clear any previously set request properties, if any
         restServiceAdapter.clearRequestProperties();
         // Set the connection name
         restServiceAdapter.setConnectionName("enrich");
         
         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
         String postData= "{\n" + 
         "\n" + 
         "  \"GET_DELIVER_TO_Input\" : {\n" + 
         "\n" + 
         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_deliver_to/\",\n" + 
         "\n" + 
         "   \"RESTHeader\": {\n" + 
         "\n" + 
         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
         "    },\n" + 
         "\n" + 
         "   \"InputParameters\": {\n" + 
         "\n" + 
         "        \"P_USER_ID\":"+userId+"\n" + 
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
            JSONObject data=new JSONObject();
          try{
              data=output.getJSONObject("X_DELIVER_TO_TL");
             DeliverToLocationList.s_jobs.clear();
             
             if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
               JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
               for(int i=0;i<segments.length();i++) {
                 JSONObject location=segments.getJSONObject(i);
                 String locationId=location.getString("LOCATION_ID");
                 String locationCode=location.getString("LOCATION_CODE");
                 String locationDescription=location.getString("DESCRIPTION");
                  if(locationId.equalsIgnoreCase(default_deliver_to_location_id)) {
                      default_deliver_to_location_name=locationCode;
                  }
                 DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                 DeliverToLocationList.s_jobs.add(loc);
               }
             
             }
             
             else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
                
                JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                 String locationId=location.getString("LOCATION_ID");
                 String locationCode=location.getString("LOCATION_CODE");
                 String locationDescription=location.getString("DESCRIPTION");
                 if(locationId.equalsIgnoreCase(default_deliver_to_location_id)) {
                     default_deliver_to_location_name=locationCode;
                 }
                 DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                 DeliverToLocationList.s_jobs.add(loc);
                
             }
             }
             catch(Exception e) {
                 e.printStackTrace();
             }
         
            
            
            
            
            
            
            
            
            
         //Cost center     
        
        restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cost_center/");
        postData= "{\n" + 
        "\n" + 
        "  \"GET_COST_CENTER_Input\" : {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_cost_center/\",\n" + 
        "\n" + 
        "   \"RESTHeader\": {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "\n" + 
        "   \"InputParameters\": {\n" + 
        "\n" + 
        "          \"P_USER_ID\" : "+userId+"\n" + 
        "\n" + 
        "     }\n" + 
        "\n" + 
        "  }\n" + 
        "\n" + 
        "}  ";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
            response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response); 
             resp=new JSONObject(response);
             output=resp.getJSONObject("OutputParameters");
        try{
             data=output.getJSONObject("X_COST_CENTER_TL");
            CostCenterList.s_jobs.clear();
            
            if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
              JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
              for(int i=0;i<segments.length();i++) {
                  //String name=(String)segments.get(i);
                  JSONObject ci=(JSONObject)segments.get(i);
                  String name=ci.getString("SEGMENT_VALUE");
                  String description=ci.getString("DESCRIPTION");
                  if(name.equalsIgnoreCase(default_cost_center_id)) {
                      default_cost_center_name=description;
                  }
                  CostCenter c=new CostCenter(name,description);
                  CostCenterList.s_jobs.add(c);
                  
              }
            
            }
            
            else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
               
               JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
                String name=ci.getString("SEGMENT_VALUE");
                String description=ci.getString("DESCRIPTION");
                if(name.equalsIgnoreCase(default_cost_center_id)) {
                    default_cost_center_name=description;
                }
                CostCenter c=new CostCenter(name,description);
                CostCenterList.s_jobs.add(c);
               
            }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
      
            
            
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
            
            
            
        
        
         
        
        
           
            
            
            
            
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets.iterator}");  
//            vex.refresh();
                
                
        
        
        return "checkout";
        
       
        
        
        
        
        
    }
    
    

    public void selectCartItem(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        populateCartTotal();
    }
    public void populateCartTotal(){
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets.iterator}"); 
        Double cartValue=0.0;
        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
        for(int i=0;i<vex.getTotalRowCount();i++)
         {
             vex.setCurrentIndex(i);
             SelectedItem item=(SelectedItem)vex.getDataProvider();
             if(item.getChecked().equalsIgnoreCase("true")) {
                 cartValue=cartValue+ Double.parseDouble(item.getAmount());
             }
         }
        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(Math.round(cartValue)));        
        AdfmfJavaUtilities.flushDataChangeEvent();     

        
        
    }
    
    public void deleteFromCart(ActionEvent actionEvent) {
        // Add event code here...
        
        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartRowId}", String.class);
        String rowId=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());

        
        
         System.out.print("rowId is   =========>"+rowId);
         
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets.iterator}");  
         
        for(int i=0;i<vex.getTotalRowCount();i++)
                {
                    vex.setCurrentIndex(i);
                    SelectedItem item1=(SelectedItem)vex.getDataProvider();
                    if(rowId.equalsIgnoreCase(item1.getRowid())) {
                        System.out.print("Row removed");
                        vex.removeCurrentRow();
                        SelectedItemsList.items_selected.remove(item1);
                        SelectedItemsList.purchase_items.remove(item1);
                    }
                }
         
        vex.refresh();
        
    //        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
    //
    //        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartProductTitle}", String.class);
    //        String title=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
    //
    //        System.out.print("title is   =========>"+title);
    //        for(int i=0;i<vex.getTotalRowCount();i++)
    //        {
    //            vex.setCurrentIndex(i);
    //            SelectedItem item1=(SelectedItem)vex.getDataProvider();
    //            if(title.equalsIgnoreCase(item1.getProductTitle())) {
    //                System.out.print("Row removed");
    //                vex.removeCurrentRow();
    //                SelectedItemsList.items_selected.remove(item1);
    //                SelectedItemsList.purchase_items.remove(item1);
    //            }
    //        }
    //
    //        Double cartTotal=0.0;
    //
    //        for(int i=0;i<vex.getTotalRowCount();i++)
    //        {
    //            vex.setCurrentIndex(i);
    //            SelectedItem item1=(SelectedItem)vex.getDataProvider();
    //
    //            cartTotal=cartTotal+Double.parseDouble(item1.getAmount());
    //        }
    //
    //        ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
    //        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(cartTotal));
    //
    //        vex.refresh();
        
       
        int count=SelectedItemsList.items_selected.size();
        ValueExpression ve45 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
        if(count==0) {
            
            ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
        }
        else{
            ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
        }
        AdfmfJavaUtilities.flushDataChangeEvent();
    }
    
    public void quantityChanged(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        System.out.print("Location  =========>"+valueChangeEvent.getNewValue().toString());
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets.iterator}"); 
        Double cartValue=0.0;
      
        for(int i=0;i<vex.getTotalRowCount();i++)
         {
             vex.setCurrentIndex(i);
             SelectedItem item=(SelectedItem)vex.getDataProvider();
             Double d=Double.parseDouble(item.getUnitPrice());
             Double qty=Double.parseDouble(item.getQuantity());
             Double price=d*qty;
             item.setAmount(String.valueOf(Math.round(price)));
         }
        
        
        
    //
    //        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
    //        SelectedItem item=(SelectedItem)vex.getDataProvider();
    //        GenericType row= (GenericType)vex.getCurrentRow();
    //
    //
    //
    //        row.setAttribute("amount", String.valueOf(Math.round(price)));
    //        System.out.print("price is   =========>"+Math.round(price));
        vex.refresh();
        
    }
    
    public void needByDateChangedCheckout(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets.iterator}");  
        
        for(int i=0;i<vex.getTotalRowCount();i++)
          {
                    vex.setCurrentIndex(i);
                    SelectedItem item=(SelectedItem)vex.getDataProvider();
        try{
            
           
           
            
            
        System.out.println("New Date "+valueChangeEvent.getNewValue().toString());
        
         String arr[]=valueChangeEvent.getNewValue().toString().split("T");
         String date="";
            if(arr.length>1){
                 date=arr[0];    
            }
        
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date sel_date=sdf.parse(item.getNeed_by_date());
            Date today_date=sdf.parse(sdf.format(new Date()));
            
            long diff = today_date.getTime() - sel_date.getTime();
            
            System.out.println("today_date "+sdf.format(today_date));
            System.out.println("Comparing Dates "+sel_date.equals(today_date));
            
    //            if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)>0) {
    //                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
    //                                             AdfmfJavaUtilities.getFeatureName(),
    //                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
    //                                             "Need by date should be greater than or equal to current date",
    //                                             null,
    //                                             null });
    //                item.setNeed_by_date("");
    //
    //
    //            }
            
            if(sel_date.after(today_date)){
               
               
            }
            else if(sel_date.equals(today_date)){
                
            }
            else{
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                             AdfmfJavaUtilities.getFeatureName(),
                                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                             "Need by date should be greater than or equal to current date",
                                                             null,
                                                             null });
                                item.setNeed_by_date(""); 
            }
            
            System.out.println("Formatted Date "+date);
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
                }
        vex.refresh();
        
    }
    
    public void makeRequesition(ActionEvent actionEvent) {
        // Add event code here...
        
       
        boolean isSelected=false;
       
        
        String action="req_submit";
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets.iterator}");  
       
        for(int i=0;i<vex.getTotalRowCount();i++)
        {
            vex.setCurrentIndex(i);
            SelectedItem item=(SelectedItem)vex.getDataProvider();
            System.out.println("***"+item.getProductTitle()+" "+item.getChecked());
            
            if(item.getChecked().equalsIgnoreCase("true")) {
                isSelected=true;
            }
        }
       
       
        if(isSelected){
       
       System.out.println(vex.getCurrentRowKey());
       
        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        ValueExpression ve412 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
       String searchType= (String)ve412.getValue(AdfmfJavaUtilities.getAdfELContext());
       
      
                       try{
                           SelectedItemsList.purchase_items=SelectedItemsList.items_selected;
                       // Add event code here...
                       RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
                       // Clear any previously set request properties, if any
                       restServiceAdapter.clearRequestProperties();
                       // Set the connection name
                       restServiceAdapter.setConnectionName("enrich");
                       
                       restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                       restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                       restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                       restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                       restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/checkout/");
                           
                         
                           StringBuffer sb = new StringBuffer("[\n");
                           SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                           Random randomGenerator = new Random();
                           int randomInt = randomGenerator.nextInt(100);
                           
                           
                           ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
                           String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
                           
                           ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
                           String productTitle = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
                           
                                sb.append("{\n");
                                
                                sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                                sb.append("    \"DEVICE_SERIAL_ID\":\"ABCD\",\n");
                                sb.append("    \"USER_ID\":\""+userName+"\",\n");
                                sb.append("    \"SEARCH_TYPE\":\"\",\n");
                                sb.append("    \"SEARCH_TEXT\":\""+productTitle+"\",\n");
                                
                                sb.append("    \"RESULT_COUNT\":\""+0+"\",\n");
                                
                                sb.append("    \"REQUEST_TYPE\":\"REJECT_REQ\"\n");
                                sb.append("},");
               
                           String header_value = sb.substring(0, sb.length() - 1).concat("]");
                           
                           System.out.println("header_value===============================" + header_value);
                           
                           
                           sb = new StringBuffer("[\n");
                           
                           for(int i=0;i<vex.getTotalRowCount();i++)
                           {
                               vex.setCurrentIndex(i);
                               SelectedItem item=(SelectedItem)vex.getDataProvider();
                               System.out.println("***"+item.getProductTitle()+" "+item.getChecked());
                               
                               if(item.getChecked().equalsIgnoreCase("true")) {
                                   SelectedItem it=item;
                                   sb.append("{\n");
                                   
                                   sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
//                                   if(it.getSource().equalsIgnoreCase("Contracted")){
//                                   sb.append("    \"PRODUCT_SOURCE\":\"O\",\n");
//                                   }
//                                   else{
//                                       sb.append("    \"PRODUCT_SOURCE\":\"I\",\n");
//                                   }
                                   
                                  
                                 
                                   
                                   
                                   sb.append("    \"PRODUCT_SOURCE\":\"O\",\n");
                                   sb.append("    \"PRODUCT_CATEGORY\":\""+it.getProductCategory()+"\",\n");
                                   sb.append("    \"PRODUCT_TITLE\":\""+it.getProductTitle()+"\",\n");
                                   sb.append("    \"QUANTITY\":\""+it.getQuantity()+"\",\n");
                                   sb.append("    \"UOM_CODE\":\""+it.getUom()+"\",\n");
                                   sb.append("    \"UNIT_PRICE\":\""+it.getUnitPrice()+"\",\n");
                                   sb.append("    \"CURRENCY_CODE\":\"USD\",\n");
                                   
                                   DeliverToLocation loc=new DeliverToLocation();
                                   if(!it.getDeliver_to_location().equalsIgnoreCase("")){
                                       for(int k=0;k<DeliverToLocationList.s_jobs.size();k++){
                                           DeliverToLocation l=(DeliverToLocation)DeliverToLocationList.s_jobs.get(k);
                                           if(l.getCode().equalsIgnoreCase(it.getDeliver_to_location())) {
                                               loc=l;
                                           }
                                       }
                                      
                                   }
                                   else{
                                       loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get(0);
                                   }
                                   
                                   
                                   
                                   sb.append("    \"DELIVER_TO_LOCATION\":\""+loc.getCode()+"\",\n");
                                   String arr[]=it.getNeed_by_date().split("T");
                                   sb.append("    \"NEED_BY_DATE\":\""+arr[0]+"\",\n");
                                   sb.append("    \"BPA_NUM\":\"\",\n");
                                   sb.append("    \"VENDOR_NAME\":\""+it.getVendorName()+"\",\n");
                                   sb.append("    \"VENDOR_SITE\":\""+it.getVendorSiteCode()+"\",\n");
                                   sb.append("    \"VENDOR_SITE_ADDRESS1\":\"\",\n");
                                   sb.append("    \"VENDOR_SITE_ADDRESS2\":\"\",\n");
                                   sb.append("    \"VENDOR_SITE_ADDRESS3\":\"\",\n");
                                   sb.append("    \"VENDOR_SITE_CITY\":\"\",\n");
                                   sb.append("    \"VENDOR_SITE_STATE\":\"\",\n");
                                   sb.append("    \"VENDOR_SITE_ZIP\":\"\",\n");
                                   sb.append("    \"VENDOR_SITE_COUNTRY\":\"\",\n");
                                   sb.append("    \"VENDOR_CONTACT_FIRST_NAME\":\"JAMES\",\n");
                                   sb.append("    \"VENDOR_CONTACT_LAST_NAME\":\"FRANKLINE\",\n");
                                   sb.append("    \"VENDOR_CONTACT_PHONE\":\"\",\n");
                                   sb.append("    \"VENDOR_CONTACT_EMAIL\":\"james_fr@gmail.com\",\n");
                                   sb.append("    \"SELECTED_FLAG\":\"Y\",\n");
                                   sb.append("    \"ITEM_TYPE\":\""+itemType+"\",\n");
                                   CostCenter c=new CostCenter();
                                   if(!it.getCostCenter().equalsIgnoreCase("")){
                                       for(int k=0;k<CostCenterList.s_jobs.size();k++){
                                           CostCenter l=(CostCenter)CostCenterList.s_jobs.get(k);
                                           if(l.getDescription().equalsIgnoreCase(it.getCostCenter())) {
                                               c=l;
                                           }
                                       }
                                    
                                   }
                                   else{
                                       c=(CostCenter)CostCenterList.s_jobs.get(0); 
                                   }
                                   
                                   sb.append("    \"COST_CENTER\":\""+c.getName()+"\",\n");
                                   
                                   for(int z=0;z<RequisitionLineItemsList.s_jobs.size();z++) {
                                       RequisitionLineItem rl=(RequisitionLineItem)RequisitionLineItemsList.s_jobs.get(z);
                                       if(rl.getProductTitle().equalsIgnoreCase(it.getProductTitle())) {
                                           sb.append("    \"REQUISITION_HEADER_ID\":\""+rl.getReqHeaderId()+"\",\n");
                                           sb.append("    \"REQUISITION_LINE_ID\":\""+rl.getReqLineId()+"\"\n");
                                       }
                                   }
                                   
                                   
                                   
                                   
                                   sb.append("},");
                                   
                                   SelectedItemsList.purchase_items.remove(item);
                                   SelectedItemsList.items_selected.remove(item);
                               }
                                   
                           }
                           
                       
                           
                           String body_value = sb.substring(0, sb.length() - 1).concat("]");
                           
                           System.out.println("body_value===============================" + body_value);
                           
                           String data= "{\n" + 
                           "  \"CHECKOUT_Input\" : {\n" + 
                           "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/checkout/\",\n" + 
                           "   \"RESTHeader\": {\n" + 
                           "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                           "    },\n" + 
                           "   \"InputParameters\": {\n" + 
                           "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" + 
                           "       },\n" + 
                           "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"         \n" + 
                           "       }    \n" + 
                           "      }\n" + 
                           "   }\n" + 
                           "}\n";
                           
                           
                     
                                                   restServiceAdapter.setRetryLimit(0);
                          System.out.println("postData===============================" + data);
                       
                       String response = restServiceAdapter.send(data);
                        
                           System.out.println("response===============================" + response);
                           
                          
                           
                         
                           
                           ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
                           ve4.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(Math.round(0)));  
                           
                           
                           
                           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                      "displayAlert",
                                                                                      new Object[] { });
                           
                           int count=SelectedItemsList.items_selected.size();
                           
                           ValueExpression ve45 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
                           if(count==0) {
                               
                               ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
                           }
                           else{
                               ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
                           }
                           AdfmfJavaUtilities.flushDataChangeEvent();
                           
                           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                          "adf.mf.api.amx.doNavigation", new Object[] { "__back" });    
                      
                      
                      
                      
                      
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
        else{
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "No items Chosen.",
                                         null,
                                         null }); 
        }
           
       
     
        

        
        
    }
    
    public void showBuyerHelp(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfContainerUtilities.resetFeature("mp.Buyerhelp");
        AdfmfContainerUtilities.gotoFeature("mp.Buyerhelp");
        
    }    
    public void Buyerhelpcontent(String rr) {
        try{
            
            
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
//        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
//        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//         ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
//         String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_buyer_help_text/");
        String postData= "{\n" + 
        "\n" + 
        "    \"GET_BUYER_HELP_TEXT_Input\":{\n" + 
        "        \"RESTHeader\":{\n" + 
        "        },\n" + 
        "        \"InputParameters\":{\n" + 
        "        }\n" + 
        "    }\n" + 
        "\n" + 
        "}";
            
            
           restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            String helpmsg=output.getString("X_MESSAGE_TEXT");
            System.out.println("helptext===============================" + helpmsg);
            ValueExpression vmsg = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.helpertext}", String.class);
            vmsg.setValue(AdfmfJavaUtilities.getAdfELContext(),helpmsg);
           AdfmfJavaUtilities.flushDataChangeEvent();
            
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void buyerHelp(ActionEvent actionEvent) {
        // Add event code here...
        
        AdfmfContainerUtilities.gotoFeature("mp.Buyerhelp");
    }

    public void updateStatusFYI(ActionEvent actionEvent) {
        // Add event code here...
        
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationId}", String.class);
        String s=(String)ve18.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationMessageType}", String.class);
        String type=(String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        try{
            
        
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXE_MOBILE_NOTIFN_SERVICE/process_response/");
        String postData= "{\n" + 
        "\n" + 
        "  \"PROCESS_RESPONSE_Input\" : {\n" + 
        "\n" + 
        "   \"RESTHeader\": {\n" + 
        "\n" + 
        "    },\n" + 
        "\n" + 
        "   \"InputParameters\": {\n" + 
        "\n" + 
        "                     \"P_MESSAGE_TYPE\" : \""+type+"\",\n" + 
        "\n" + 
        "                     \"P_NOTIFICATION_ID\" : \""+s+"\",\n" + 
        "\n" + 
        "          \"P_RESPONSE_VALUE\" : \"CLOSED\",\n" + 
        "\n" + 
        "          \"P_RESPONDER\" : \""+userName+"\"\n" + 
        "\n" + 
        "       }         \n" + 
        "\n" + 
        "   }\n" + 
        "\n" + 
        "}";
            
           
           restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                           "adf.mf.api.amx.doNavigation", new Object[] { "__back" });  
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void rejectREQ(ActionEvent actionEvent) {
        // Add event code here...
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationId}", String.class);
        String s=(String)ve18.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationMessageType}", String.class);
        String type=(String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RejectReason}", String.class);
        String rejectReason=(String)ve22.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RejectionComments}", String.class);
        String rejectCommand=(String)ve23.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        System.out.println("Rejection Command"+rejectCommand+"Reject Reason"+rejectReason);
        if(!rejectReason.equalsIgnoreCase("") && !rejectReason.equalsIgnoreCase("0")) {
            System.out.println("Enter into if Loop");
            Rejection c=(Rejection)RejectionReasonList.rej_List.get(Integer.parseInt(rejectReason.toString()));
            System.out.println("-----"+c.getLookupCode());
            String LookupMeaning=c.getMeaning();
            
            try{
                 
             System.out.println("---LookupCode--"+LookupMeaning);
             RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
             // Clear any previously set request properties, if any
             restServiceAdapter.clearRequestProperties();
             // Set the connection name
             restServiceAdapter.setConnectionName("enrich");
             
             restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
             restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
             restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
             restServiceAdapter.addRequestProperty("Content-Type", "application/json");
             restServiceAdapter.setRequestURI("/webservices/rest/XXE_MOBILE_NOTIFN_SERVICE/process_response/");
             String postData= "{\n" + 
             "\n" + 
             "  \"PROCESS_RESPONSE_Input\" : {\n" + 
             "\n" + 
             "   \"RESTHeader\": {\n" + 
             "\n" + 
             "    },\n" + 
             "\n" + 
             "   \"InputParameters\": {\n" + 
             "\n" + 
             "                     \"P_MESSAGE_TYPE\" : \""+type+"\",\n" + 
             "\n" + 
             "                     \"P_NOTIFICATION_ID\" : \""+s+"\",\n" + 
             "\n" + 
             "          \"P_RESPONSE_VALUE\" : \"REJECT\",\n" + 
             "\n" + 
             "          \"P_REJECT_REASON\" : \""+LookupMeaning+"\",\n" + 
             "          \"P_COMMENTS\" : \""+rejectCommand+"\"\n" + 
             "\n" +                    
             
             "       }         \n" + 
             "\n" + 
             "   }\n" + 
             "\n" + 
             "}";
                 
                
                restServiceAdapter.setRetryLimit(0);
                System.out.println("postData===============================" + postData);
                 
                String response = restServiceAdapter.send(postData);
                 
                     System.out.println("postData===============================" + response);
                 
                 AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                "adf.mf.api.amx.doNavigation", new Object[] { "__back" });  
              
                 }
                 
             
                 
             
             catch(Exception e) {
                 e.printStackTrace();
             }
        }
        else {
            System.out.println("Inside Else loop");
                         AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                    "checkRejectionValue",
                                                                                    new Object[] { });

        }
       
           
        
       
        

        
        
    }

    public void approveReq(ActionEvent actionEvent) {
        // Add event code here...
        
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationId}", String.class);
        String s=(String)ve18.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.notificationMessageType}", String.class);
        String type=(String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        try{
            
    
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXE_MOBILE_NOTIFN_SERVICE/process_response/");
        String postData= "{\n" + 
        "\n" + 
        "  \"PROCESS_RESPONSE_Input\" : {\n" + 
        "\n" + 
        "   \"RESTHeader\": {\n" + 
        "\n" + 
        "    },\n" + 
        "\n" + 
        "   \"InputParameters\": {\n" + 
        "\n" + 
        "                     \"P_MESSAGE_TYPE\" : \""+type+"\",\n" + 
        "\n" + 
        "                     \"P_NOTIFICATION_ID\" : \""+s+"\",\n" + 
        "\n" + 
        "          \"P_RESPONSE_VALUE\" : \"APPROVE\",\n" + 
        "\n" + 
        "          \"P_RESPONDER\" : \""+userName+"\"\n" + 
        "\n" + 
        "       }         \n" + 
        "\n" + 
        "   }\n" + 
        "\n" + 
        "}";
            
           
           restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
       
            
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                       "adf.mf.api.amx.doNavigation", new Object[] { "__back" });  
            
    }
        catch(Exception e) {
            e.printStackTrace();
        }
 
 
    }

    public void createRequition(ActionEvent actionEvent) {
        // Add event code here...
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String DefaultMultiOrgOrg=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        if(DefaultMultiOrgOrg.contains(("{\"@xsi:nil\":\"true\"}"))) {
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                           "chooseMultiOrg",
                                                                                           new Object[] { });
        }
        else
        {
            AdfmfContainerUtilities.gotoFeature("feature1");
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction("feature1",
            "adf.mf.api.amx.doNavigation", new Object[] { "quickSearch_default" });
            System.out.println("Default Org Value is Empty...");
        }
        //SelectedItemsList.items_selected.clear();
    }

    public void viewacceptSupplier(ActionEvent actionEvent) {
        // Add event code here...
            // Add event code here...
            //AdfmfContainerUtilities.gotoFeature("mp.Quotation");
            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String DefaultMultiOrgOrg=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(DefaultMultiOrgOrg.contains(("{\"@xsi:nil\":\"true\"}"))) {
                System.out.println("enter into Check Requistion");
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                               "chooseMultiOrg",
                                                                                               new Object[] { });
            }
            else{
            AdfmfContainerUtilities.resetFeature("mp.Quotation",true);
            }
    }

    public void checkRequisition(ActionEvent actionEvent) {
        // Add event code here...
        //AdfmfContainerUtilities.gotoFeature("mp.Requisition");
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String DefaultMultiOrgOrg=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        if(DefaultMultiOrgOrg.contains(("{\"@xsi:nil\":\"true\"}"))) {
            System.out.println("enter into Check Requistion");
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                           "chooseMultiOrg",
                                                                                           new Object[] { });
        }
        else
        {
       AdfmfContainerUtilities.resetFeature("mp.Requisition",true);
        }
    }

    public void manageProfile(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfContainerUtilities.resetFeature("mp.Userprofile",true);
    }
    
    public void refreshRequesitionDetails(String rr) {
        
        try{
            RequisitionLineItemsList.s_jobs.clear();
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.requesitionLineItems.iterator}");   
            vex.refresh();   
            
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.reqSearchGroupId}", String.class);
        String headerId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionOrderType}", String.class);
        String orderType = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
//        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionStatus}", String.class);
//        String status = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
//           
             String status="";
            System.out.println("Header id is =>"+headerId);
            
            
        
        
        if(status.equalsIgnoreCase("REJECTED")) {
            ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showRequisitionEdit}", String.class);
            ve11.setValue(AdfmfJavaUtilities.getAdfELContext(), "true");
        }
        else{
            
            ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showRequisitionEdit}", String.class);
            ve11.setValue(AdfmfJavaUtilities.getAdfELContext(), "false");
        }
            
        
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_req_details_v2/");
        String postData= "{\n" + 
        "  \"GET_REQ_DETAILS_V2_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_req_details_v2/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "        \"P_SEARCH_GROUP_ID\":"+headerId+"\n" + 
        "     }\n" + 
        "  }}";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            if(!output.isNull("X_REQ_DETAILS_TL")){
            JSONObject summTBL=output.getJSONObject("X_REQ_DETAILS_TL");
            RequisitionLineItemsList.s_jobs.clear();
            if(summTBL.get("X_REQ_DETAILS_TL_ITEM") instanceof JSONArray) {
                System.out.println("inside json");
                
                JSONArray summItm=summTBL.getJSONArray("X_REQ_DETAILS_TL_ITEM");
               
                for(int i=0;i<summItm.length();i++) {
                    System.out.println("for");
                    JSONObject data=(JSONObject)summItm.get(i);
                    
                    String requisitionLineNo=data.getString("LINE_NUM");
                    String productTitle=data.getString("PRODUCT_TITLE");
                    String quantity=data.getString("QUANTITY");
                    String deliverToLocation=data.getString("DELIVER_TO_LOCATION");
                    String unitPrice="$"+data.getString("UNIT_PRICE");
                    if (unitPrice.contains("{")) {
                        unitPrice = "";
                    }
                    String currencyCode=data.getString("CURRENCY_CODE");
                    if (currencyCode.contains("{")) {
                        currencyCode = "";
                    }
                    String uom=data.getString("UOM");
                    if (unitPrice.equalsIgnoreCase("")) {
                        uom = "";
                    } 
                    String needByDate=data.getString("NEED_BY_DATE");
                    
                   
                    
                    if (needByDate.contains("{")) {
                        needByDate = "";
                    }
                    
//                    SimpleDateFormat fdf=new SimpleDateFormat("dd-MMM-yyyy");
//                    SimpleDateFormat tdf=new SimpleDateFormat("yyyy-MM-dd");
//                    if(!needByDate.equalsIgnoreCase("")){
//                        String arr[]=needByDate.split("T");
//                        needByDate=fdf.format(tdf.parse(arr[0]));
//                    }
                    
                    
                    String lineTotal="$"+data.getString("LINE_TOTAL");
                    if (lineTotal.contains("{")) {
                        lineTotal = "";
                    }
                    String category=data.getString("CATEGORY");
                    if (category.contains("{")) {
                        category = "";
                    }
                    String poNo=data.getString("PO_NUM");
                    if (poNo.contains("{")) {
                        poNo = "";
                    }
                    
                    String vendorName=data.getString("VENDOR_NAME");
                    if (vendorName.contains("{")) {
                        vendorName = "";
                    }
                    
                    
                    
//                    String vendorSite=data.getString("VENDOR_SITE");
//                    if (vendorSite.contains("{")) {
//                        vendorSite = "";
//                    }
//                    
//                    String costCenter=data.getString("COST_CENTER");
//                    if (costCenter.contains("{")) {
//                        costCenter = "";
//                    }
//                    String costCenterDescription=data.getString("COST_CENTER_DESC");
//                    if (costCenterDescription.contains("{")) {
//                        costCenterDescription = "";
//                    }
                    
                    String vendorSite="";
                    String costCenter="";
                    String costCenterDescription="";
                    
                    String lineType=data.getString("LINE_TYPE");
                    if (lineType.contains("{")) {
                        lineType = "";
                    }
                    
                    String reqHeaderId=data.getString("REQ_HEADER_ID");
                    if (reqHeaderId.contains("{")) {
                        reqHeaderId = "";
                    }
                    
//                    String reqLineId=data.getString("REQUISITION_LINE_ID");
//                    if (reqLineId.contains("{")) {
//                        reqLineId = "";
//                    }
                    String reqLineId="";
                    String reqNum=data.getString("REQ_NUM");
                    if (reqNum.contains("{")) {
                        reqNum = "";
                    }
                    
                    String reqStatus=data.getString("REQ_STATUS");
                    if (reqStatus.contains("{")) {
                        reqStatus = "";
                    }
                    
                    String rfqNum=data.getString("RFQ_NUM");
                    if (rfqNum.contains("{")) {
                        rfqNum = "";
                    }
                    
                    String rfqStatus=data.getString("RFQ_STATUS");
                    if (rfqStatus.contains("{")) {
                        rfqStatus = "";
                    }
                    
                    String rfqHeaderId=data.getString("RFQ_HEADER_ID");
                    if (rfqHeaderId.contains("{")) {
                        rfqHeaderId = "";
                    }
                    String showReqSection="";
                    String showRfqSection="";
                    if(orderType.equalsIgnoreCase("REQ")){
                        showReqSection="true";
                        showRfqSection="false";
                        if (!rfqNum.equalsIgnoreCase("")) {
                            showRfqSection="true";
                        }
                    }
                    if(orderType.equalsIgnoreCase("RFQ")){
                        showReqSection="false";
                        showRfqSection="true";
                        if (!reqNum.equalsIgnoreCase("")) {
                            showReqSection="true";
                        }
                    }
                    
                    System.out.println("***"+productTitle);
                    RequisitionLineItem req=new RequisitionLineItem(requisitionLineNo, productTitle, quantity, deliverToLocation, unitPrice, currencyCode, uom, needByDate, lineTotal, category, poNo,vendorName,vendorSite,costCenter,costCenterDescription,lineType,reqHeaderId,reqLineId,reqNum,reqStatus,rfqNum,rfqStatus,rfqHeaderId,showReqSection,showRfqSection);
                    RequisitionLineItemsList.s_jobs.add(req);
                }
                
            }
            
            if(summTBL.get("X_REQ_DETAILS_TL_ITEM") instanceof JSONObject) {
                
                JSONObject summItm=summTBL.getJSONObject("X_REQ_DETAILS_TL_ITEM");
                JSONObject data=summItm;
                String requisitionLineNo=data.getString("LINE_NUM");
                String productTitle=data.getString("PRODUCT_TITLE");
                String quantity=data.getString("QUANTITY");
                String deliverToLocation=data.getString("DELIVER_TO_LOCATION");
                String unitPrice="$"+data.getString("UNIT_PRICE");
                if (unitPrice.contains("{")) {
                    unitPrice = "";
                }
                String currencyCode=data.getString("CURRENCY_CODE");
                if (currencyCode.contains("{")) {
                    currencyCode = "";
                }
                String uom=data.getString("UOM");
                if (unitPrice.equalsIgnoreCase("")) {
                    uom = "";
                } 
                String needByDate=data.getString("NEED_BY_DATE");
                if (needByDate.contains("{")) {
                    needByDate = "";
                }
                
//                SimpleDateFormat fdf=new SimpleDateFormat("dd-MMM-yyyy");
//                SimpleDateFormat tdf=new SimpleDateFormat("yyyy-MM-dd");
//                if(!needByDate.equalsIgnoreCase("")){
//                    String arr[]=needByDate.split("T");
//                    needByDate=fdf.format(tdf.parse(arr[0]));
//                }
                String lineTotal="$"+data.getString("LINE_TOTAL");
                if (lineTotal.contains("{")) {
                    lineTotal = "";
                }
                String category=data.getString("CATEGORY");
                if (category.contains("{")) {
                    category = "";
                }
                String poNo=data.getString("PO_NUM");
                if (poNo.contains("{")) {
                    poNo = "";
                }
                String vendorName=data.getString("VENDOR_NAME");
                if (vendorName.contains("{")) {
                    vendorName = "";
                }
                
//                String vendorSite=data.getString("VENDOR_SITE");
//                if (vendorSite.contains("{")) {
//                    vendorSite = "";
//                }
                String vendorSite="";
//                String costCenter=data.getString("COST_CENTER");
//                if (costCenter.contains("{")) {
//                    costCenter = "";
//                }
                  String costCenter="";
//                String costCenterDescription=data.getString("COST_CENTER_DESC");
//                if (costCenterDescription.contains("{")) {
//                    costCenterDescription = "";
//                }
                String costCenterDescription="";
                String lineType=data.getString("LINE_TYPE");
                if (lineType.contains("{")) {
                    lineType = "";
                }
                String reqHeaderId=data.getString("REQ_HEADER_ID");
                if (reqHeaderId.contains("{")) {
                    reqHeaderId = "";
                }
                
//                String reqLineId=data.getString("REQUISITION_LINE_ID");
//                if (reqLineId.contains("{")) {
//                    reqLineId = "";
//                }
                 String reqLineId="";
                String reqNum=data.getString("REQ_NUM");
                if (reqNum.contains("{")) {
                    reqNum = "";
                }
                
                String reqStatus=data.getString("REQ_STATUS");
                if (reqStatus.contains("{")) {
                    reqStatus = "";
                }
                
                String rfqNum=data.getString("RFQ_NUM");
                if (rfqNum.contains("{")) {
                    rfqNum = "";
                }
                
                String rfqStatus=data.getString("RFQ_STATUS");
                if (rfqStatus.contains("{")) {
                    rfqStatus = "";
                }
                
                String rfqHeaderId=data.getString("RFQ_HEADER_ID");
                if (rfqHeaderId.contains("{")) {
                    rfqHeaderId = "";
                }
                String showReqSection="";
                String showRfqSection="";
             
                if(orderType.equalsIgnoreCase("REQ")){
                    showReqSection="true";
                    showRfqSection="false";
                    if (!rfqNum.equalsIgnoreCase("")) {
                        showRfqSection="true";
                    }
                }
                if(orderType.equalsIgnoreCase("RFQ")){
                    showReqSection="false";
                    showRfqSection="true";
                    if (!reqNum.equalsIgnoreCase("")) {
                        showReqSection="true";
                    }
                }
                
                System.out.println("***"+productTitle);
                RequisitionLineItem req=new RequisitionLineItem(requisitionLineNo, productTitle, quantity, deliverToLocation, unitPrice, currencyCode, uom, needByDate, lineTotal, category, poNo,vendorName,vendorSite,costCenter,costCenterDescription,lineType,reqHeaderId,reqLineId,reqNum,reqStatus,rfqNum,rfqStatus,rfqHeaderId,showReqSection,showRfqSection);
                RequisitionLineItemsList.s_jobs.add(req);
            }
           
            
            
            }
            
            vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.requesitionLineItems.iterator}");   
            System.out.println("data "+vex.getTotalRowCount());
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

    public String showRFQDetails() {
        // Add event code here...
        try{
            
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRfqHeaderId}", String.class);
            String headerId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
        
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_rfq_details/");
        String postData= "{\n" + 
        "\n" + 
        "  \"GET_RFQ_DETAILS_Input\" : {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_req_details/\",\n" + 
        "\n" + 
        "   \"RESTHeader\": {\n" + 
        "\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "\n" + 
        "    },\n" + 
        "\n" + 
        "   \"InputParameters\": {\n" + 
        "\n" + 
        "        \"P_RFQ_HEADER_ID\":"+ headerId+"\n" + 
        "\n" + 
        "     }\n" + 
        "\n" + 
        "  }\n" + 
        "\n" + 
        "}";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            JSONObject summTBL=output.getJSONObject("X_RFQ_DETAILS_TL");
            RequisitionLineItemsList.s_jobs.clear();
            if(summTBL.get("X_RFQ_DETAILS_TL_ITEM") instanceof JSONArray) {
                System.out.println("inside json");
                
                JSONArray summItm=summTBL.getJSONArray("X_RFQ_DETAILS_TL_ITEM");
               
                for(int i=0;i<summItm.length();i++) {
                
                }
                
            }
            
            if(summTBL.get("X_RFQ_DETAILS_TL_ITEM") instanceof JSONObject) {
                
                JSONObject summItm=summTBL.getJSONObject("X_RFQ_DETAILS_TL_ITEM");
                JSONObject data=summItm;
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQNo}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("RFQ_NUM"));
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQtatus}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("RFQ_STATUS"));
                
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQCreatedDate}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("RFQ_CREATION_DATE"));
                
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQNo}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("RFQ_NUM"));
                
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQItemDescription}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("RFQ_DESCRIPTION"));
                
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQQty}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("QUANTITY"));
                
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQSent}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("NO_OF_RFQ_SENT"));
                
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQCloseDate}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("RFQ_CLOSED_DATE"));
                
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.requisitionRFQBidsRecieved}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("NO_OF_BIDS_RECEIVED"));
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqCreatedBy}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),data.getString("RFQ_CREATED_BY"));
                
                AdfmfJavaUtilities.flushDataChangeEvent();
             
            }
            return "RFQ_details";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public void LocationHandler(Location currentPosition) {
        
        try{
        
       
                               String url = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=en&latlng="+currentPosition.getLatitude()+","+currentPosition.getLongitude();
                                               
                                               URL obj = new URL(url);
                                               HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    
                                               // optional default is GET
                                               con.setRequestMethod("GET");
                    
                                               //add request header
                                              
                    
                                               int responseCode = con.getResponseCode();
                                               System.out.println("\nSending 'GET' request to URL : " + url);
                                               System.out.println("Response Code : " + responseCode);
                    
                                               BufferedReader in = new BufferedReader(
                                                       new InputStreamReader(con.getInputStream()));
                                               String inputLine;
                                               StringBuffer response1 = new StringBuffer();
                    
                                               while ((inputLine = in.readLine()) != null) {
                                                       response1.append(inputLine);
                                               }
                                               in.close();
                    
                                               //print result
                                           //    System.out.println(response1.toString());
                                    
                                              JSONObject resp=new JSONObject(response1.toString());
                                              JSONArray output=resp.getJSONArray("results");
                        if(output.length()>0){
                                JSONObject fobj=output.getJSONObject(0);
                                String address=fobj.getString("formatted_address");
                                ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentServicelocation}", String.class);
                                ve.setValue(AdfmfJavaUtilities.getAdfELContext(),address);           
                                AdfmfJavaUtilities.flushDataChangeEvent();
                                      
                            }
                                             
        }
        catch(Exception e){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "Error occured while fetching positions",
                                         null,
                                         null });    
        }
        
    }

    public void searchRequisition(ActionEvent actionEvent) {
        // Add event code here...
        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.searchRequisition.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
    }
    
    public void searchReq(String ss){
        ValueExpression veSearch = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchRequisitionValue}", String.class);
        veSearch.setValue(AdfmfJavaUtilities.getAdfELContext(),ss);
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.searchRequisition.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

    public void clearRequisitionSearch(ActionEvent actionEvent) {
        // Add event code here...
//        ValueExpression veSearch = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchRequisitionValue}", String.class);
//        veSearch.setValue(AdfmfJavaUtilities.getAdfELContext(),"");    
//        AdfmfJavaUtilities.flushDataChangeEvent();
//        
//        getRequisitions("");
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.clearRequisitionSearch.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }
    
    
    public void getOrders(String rr) {
        try{
            RequisitionsList.s_jobs.clear();
            OrderList.s_jobs.clear();
              
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());

            ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String multiOrgId = (String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());

        
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
        "          \"P_USER_ID\" : \""+userId+"\",\n" +
        "          \"P_ORG_ID\" : \""+multiOrgId+"\"\n" +
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

    public void closeFYInotification(ActionEvent actionEvent) {
        // Add event code here...
        
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                   "clearFYI",
                                                                   new Object[] { });
        
 
    }
    
    public void clearAllFyiNotification(String rr) {
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
        restServiceAdapter.setRequestURI("/webservices/rest/XXE_MOBILE_NOTIFN_SERVICE/close_fyi_notifications/");
        String postData= "{\n" + 
        "  \"CLOSE_FYI_NOTIFICATIONS_Input\" : {\n" + 
        "   \"RESTHeader\": {\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "          \"P_USER_ID\" : \""+userId+"\"\n" + 
        "       }          \n" + 
        "   }\n" + 
        "}\n";
           restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            String response = restServiceAdapter.send(postData);
             
             System.out.println("response===============================" + response);
            AdfmfContainerUtilities.resetFeature("mp.Notification");
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void getMultiOrgValues(String rr) {
        
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String default_mulOrg = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());

        if(default_mulOrg.contains(("{"))) {
            System.out.println("Multi Org Value"+default_mulOrg);     
            ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.displayMultiOrgDrop}", String.class);
            ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            
        }
        else {
            System.out.println("Multi Org Value"+default_mulOrg);
            ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.displayMultiOrgDrop}", String.class);
            ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
            }
       System.out.println("SpringBoard Default MultiOrg--"+default_mulOrg);
        
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
            restServiceAdapter.setRequestURI("/webservices/rest/XXEUserService/get_user_org_access/");
            String postData="{\n" + 
                 "\n" + 
                 "    \"GET_USER_ORG_ACCESS_Input\":{\n" + 
                 "        \"RESTHeader\":{\n" + 
                 "        },\n" + 
                 "        \"InputParameters\":{\n" + 
                 "            \"P_USER_ID\": "+userId+"\n" + 
                 "        }\n" + 
                 "    }\n" + 
                 "\n" + 
                 "}";
            
                                        restServiceAdapter.setRetryLimit(0);
               System.out.println("postData===============================" + postData);
                
               String response = restServiceAdapter.send(postData);
                
                System.out.println("response===============================" + response); 
                JSONObject resp=new JSONObject(response);
                JSONObject output=resp.getJSONObject("OutputParameters");
               JSONObject data1=new JSONObject();
            try{
                 data1=output.getJSONObject("X_ORG_ACCESS_TL");
                 MultiOrgList.org_List.clear();
                 multiOrgList.clear();
               if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONArray){
                   /*MultiOrg m1=new MultiOrg("Please Select","\u2009"); 
                   MultiOrgList.org_List.add(m1);
                   multiOrgList.add(m1); */  
                 JSONArray segments=data1.getJSONArray("X_ORG_ACCESS_TL_ITEM");
                 for(int i=0;i<segments.length();i++) {
                   JSONObject org=segments.getJSONObject(i);
                   String orgId=org.getString("ORG_ID");
                   String orgName=org.getString("ORG_NAME");
                   MultiOrg mulList=new MultiOrg(orgId, orgName);
                   MultiOrgList.org_List.add(mulList);
                   multiOrgList.add(mulList);
                     
                 }
                   
                   
                 System.out.println("++++MULTIORGLIST"+MultiOrgList.org_List.size());
               }
                   else if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONObject){
                       JSONObject org=data1.getJSONObject("X_ORG_ACCESS_TL_ITEM");
                        String orgId=org.getString("ORG_ID");
                        String orgName=org.getString("ORG_NAME");
                       MultiOrg mulList=new MultiOrg(orgId, orgName);
                       MultiOrgList.org_List.add(mulList);
                       multiOrgList.add(mulList);
                   }
               }
               catch(Exception e) {
                   e.printStackTrace();
               }

            
            //Default Setting for MultiORG Values 
            try{
                for(int k=0;k<MultiOrgList.org_List.size();k++) {
                 MultiOrg mul=(MultiOrg)MultiOrgList.org_List.get(k);
                    //System.out.println("*****mul.getId"+mul.getOrgId()+"Application Scope"+mulOrg);
                    
                    if(mul.getOrgId().equals(default_mulOrg)){
                        System.out.println("*****Defult_SpringBoardMultiOrg_getId"+mul.getOrgId()+"value of "+String.valueOf(k));
                         ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedMultiOrg}", String.class);
                         ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(k));  

                        }
                    else {
                        System.out.println("Inside to else loop in SpringBoard MultiOrg");
                    /*    ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedMultiOrg}", String.class);
                        ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");  */

                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        AmxAttributeBinding MulOrg = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.multiOrgs}");
        AmxIteratorBinding orgListIterator =  MulOrg.getIteratorBinding();
        orgListIterator.refresh();
        // Add event code here...
    }

    public void getMulitiOrgValues(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        MultiOrg o=(MultiOrg)MultiOrgList.org_List.get(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
        System.out.println(o.getOrgId()+"----"+o.getOrgName());
        if(!o.getOrgName().equalsIgnoreCase("Please Select")){
            System.out.println("inside IF LOOp in multiOrg");
            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.profileMultiOrgId}", String.class);
            ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),o.getOrgId());
            ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedMultiOrgProfile}", String.class);
            ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),o.getOrgId());
            
            AdfmfJavaUtilities.flushDataChangeEvent();
        }
        else {
            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.profileMultiOrgId}", String.class);
            ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            AdfmfJavaUtilities.flushDataChangeEvent();
        }
    }
    public void getMultiOrgValuesProfile(String rr) {
        
        
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String default_mulOrg = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        System.out.println("Enter New Profile MultiOrg"+userId+"--- Default_Org-- "+default_mulOrg);
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
            restServiceAdapter.setRequestURI("/webservices/rest/XXEUserService/get_user_org_access/");
            String postData="{\n" + 
                 "\n" + 
                 "    \"GET_USER_ORG_ACCESS_Input\":{\n" + 
                 "        \"RESTHeader\":{\n" + 
                 "        },\n" + 
                 "        \"InputParameters\":{\n" + 
                 "            \"P_USER_ID\": "+userId+"\n" + 
                 "        }\n" + 
                 "    }\n" + 
                 "\n" + 
                 "}";
            
                                        restServiceAdapter.setRetryLimit(0);
               System.out.println("postData===============================" + postData);
                
               String response = restServiceAdapter.send(postData);
                
                System.out.println("response===============================" + response); 
                JSONObject resp=new JSONObject(response);
                JSONObject output=resp.getJSONObject("OutputParameters");
               JSONObject data1=new JSONObject();
            try{
                 data1=output.getJSONObject("X_ORG_ACCESS_TL");
                 MultiOrgList.org_List.clear();
                 multiOrgList.clear();
               if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONArray){
                 MultiOrg m1=new MultiOrg("Please Select","Please Select"); 
                   MultiOrgList.org_List.add(m1);
                   multiOrgList.add(m1);
                 JSONArray segments=data1.getJSONArray("X_ORG_ACCESS_TL_ITEM");
                 for(int i=0;i<segments.length();i++) {
                   JSONObject org=segments.getJSONObject(i);
                   String orgId=org.getString("ORG_ID");
                   String orgName=org.getString("ORG_NAME");
                   MultiOrg mulList=new MultiOrg(orgId, orgName);
                   MultiOrgList.org_List.add(mulList);
                   multiOrgList.add(mulList);
                     
                 }
                   
                   
                 System.out.println("++++MULTIORGLIST"+MultiOrgList.org_List.size());
               }
                   else if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONObject){
                     MultiOrg m1=new MultiOrg("Please Select","Please Select"); 
                     MultiOrgList.org_List.add(m1);
                     multiOrgList.add(m1);
                       JSONObject org=data1.getJSONObject("X_ORG_ACCESS_TL_ITEM");
                        String orgId=org.getString("ORG_ID");
                        String orgName=org.getString("ORG_NAME");
                       MultiOrg mulList=new MultiOrg(orgId, orgName);
                       MultiOrgList.org_List.add(mulList);
                       multiOrgList.add(mulList);
                   }
               }
               catch(Exception e) {
                   e.printStackTrace();
               }

            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        AmxAttributeBinding MultiList = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.multiOrgs}");
        AmxIteratorBinding orgListIterator =  MultiList.getIteratorBinding();
        orgListIterator.refresh();
        // Add event code here...
    }

    public void getSpringboardMultiOrg(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String default_mulOrg = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
       System.out.println("Default MultiOrg--"+default_mulOrg);
    ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedMultiOrg}", String.class);
    String select_multiOrg=(String)ve14.getValue(AdfmfJavaUtilities.getAdfELContext());
    System.out.println("new Selected MultiOrg--"+select_multiOrg);
    ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
    String cartvalue=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
    
    
        
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
            restServiceAdapter.setRequestURI("/webservices/rest/XXEUserService/get_user_org_access/");
            String postData="{\n" + 
                 "\n" + 
                 "    \"GET_USER_ORG_ACCESS_Input\":{\n" + 
                 "        \"RESTHeader\":{\n" + 
                 "        },\n" + 
                 "        \"InputParameters\":{\n" + 
                 "            \"P_USER_ID\": "+userId+"\n" + 
                 "        }\n" + 
                 "    }\n" + 
                 "\n" + 
                 "}";
            
                                        restServiceAdapter.setRetryLimit(0);
               System.out.println("postData===============================" + postData);
                
               String response = restServiceAdapter.send(postData);
                
                System.out.println("response===============================" + response); 
                JSONObject resp=new JSONObject(response);
                JSONObject output=resp.getJSONObject("OutputParameters");
               JSONObject data1=new JSONObject();
            try{
                 data1=output.getJSONObject("X_ORG_ACCESS_TL");
                 MultiOrgList.org_List.clear();
                 multiOrgList.clear();
               if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONArray){
                   /*MultiOrg m1=new MultiOrg("Please Select","\u2009"); 
                   MultiOrgList.org_List.add(m1);
                   multiOrgList.add(m1); */  
                 JSONArray segments=data1.getJSONArray("X_ORG_ACCESS_TL_ITEM");
                 for(int i=0;i<segments.length();i++) {
                   JSONObject org=segments.getJSONObject(i);
                   String orgId=org.getString("ORG_ID");
                   String orgName=org.getString("ORG_NAME");
                   MultiOrg mulList=new MultiOrg(orgId, orgName);
                   MultiOrgList.org_List.add(mulList);
                   multiOrgList.add(mulList);
                     
                 }
                   
                   
                 System.out.println("++++MULTIORGLIST"+MultiOrgList.org_List.size());
               }
                   else if(data1.get("X_ORG_ACCESS_TL_ITEM") instanceof  JSONObject){
                       JSONObject org=data1.getJSONObject("X_ORG_ACCESS_TL_ITEM");
                        String orgId=org.getString("ORG_ID");
                        String orgName=org.getString("ORG_NAME");
                       MultiOrg mulList=new MultiOrg(orgId, orgName);
                       MultiOrgList.org_List.add(mulList);
                       multiOrgList.add(mulList);
                   }
               }
               catch(Exception e) {
                   e.printStackTrace();
               }

            
            String updateMultiOrg="";
            if(!select_multiOrg.equalsIgnoreCase("")){
            
            MultiOrg mul=(MultiOrg)multiOrgList.get((Integer.parseInt(select_multiOrg)));
            updateMultiOrg=mul.getOrgId();
            System.out.println("New Value mul Org++++++++"+updateMultiOrg);
            }
            System.out.println("New Value mul Org++++++++"+updateMultiOrg+"Defult Org Value"+default_mulOrg);
                     
            //Default Setting for MultiORG Values 
            /*for(int k=0;k<MultiOrgList.org_List.size();k++) {
             MultiOrg mul=(MultiOrg)MultiOrgList.org_List.get(k);
                //System.out.println("*****mul.getId"+mul.getOrgId()+"Application Scope"+mulOrg);
                
                if(!mul.getOrgId().equals(default_mulOrg)){
                    System.out.println("*****Defult_SpringBoardMultiOrg_getId"+mul.getOrgId()+"value of "+String.valueOf(k));
                        ValueExpression ove13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
                        ove13.setValue(AdfmfJavaUtilities.getAdfELContext(),default_mulOrg);

                    }
                else {
                    
                 /*   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                               "newMultiOrgValue",
                                                                               new Object[] { });*/

                  //  System.out.println("Inside to else loop in SpringBoard MultiOrg");
                /*    ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedMultiOrg}", String.class);
                    ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");  */
//
             //   }
 //           }
    
    
            try{
                if(!updateMultiOrg.equals(default_mulOrg)) {
                
                
                    if(!cartvalue.equalsIgnoreCase(""))
                    {
           
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                                   "newMultiOrgValue",
                                                                                                   new Object[] { });
                    
                    ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.updateMultiOrg}", String.class);
                    ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),updateMultiOrg);
                    
                    
                    System.out.println("Enter into IF loop in multiOrg---"+updateMultiOrg+"-----"+default_mulOrg);
                    }
                    else {
                        AdfmfContainerUtilities.resetFeature("mp.springboard",false);
                        AdfmfContainerUtilities.resetFeature("feature1",false);
                        AdfmfContainerUtilities.resetFeature("mp.Requisition",false);
                        AdfmfContainerUtilities.resetFeature("mp.Quotation",false);
                        AdfmfContainerUtilities.resetFeature("mp.Notification",false);    
                        ValueExpression ov13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
                        ov13.setValue(AdfmfJavaUtilities.getAdfELContext(),updateMultiOrg);

                        
                    }
                    
                }
                else {
                    
                    ValueExpression ove13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
                    ove13.setValue(AdfmfJavaUtilities.getAdfELContext(),default_mulOrg);
                }
                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        // Add event code here...
}
    
    public void updateNewMultiOrgValues(String rr) {
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.updateMultiOrg}", String.class);
        String update_multiOrg=(String)ve14.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
        String cartvalue=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ove13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        ove13.setValue(AdfmfJavaUtilities.getAdfELContext(),update_multiOrg);
        
        AdfmfContainerUtilities.resetFeature("mp.springboard",false);
        AdfmfContainerUtilities.resetFeature("feature1",false);
        AdfmfContainerUtilities.resetFeature("mp.Requisition",false);
        AdfmfContainerUtilities.resetFeature("mp.Quotation",false);
        AdfmfContainerUtilities.resetFeature("mp.Notification",false);    
        ValueExpression ove113 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
        ove113.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        System.out.println("update_multiOrg Via popup"+update_multiOrg+"Cart Value"+cartvalue);    

    }
    public void showPopup(String rr) {
        ValueExpression ve33 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String DefaultMultiOrgOrg=(String)ve33.getValue(AdfmfJavaUtilities.getAdfELContext());
        if(DefaultMultiOrgOrg.contains("{"))
        {
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                       "chooseMultiOrg",
                                                                                       new Object[] { });
        }

    }
    
    public void gotoManageProfile(String rr) {
        AdfmfContainerUtilities.gotoFeature("mp.Userprofile");
        AdfmfContainerUtilities.resetFeature("mp.Userprofile",true);
    }

    public void sortItemQuotation(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        System.out.println("New Selected value is "+valueChangeEvent.getNewValue().toString());
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.QuotationSortBy}", String.class);
        String quotSort=(String)ve14.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedRFQId}", String.class);
        String rfqId=(String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
        System.out.println("New Selected RFQ ID:"+rfqId);
      
        try{
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();    
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
            
                
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_sort_options/");
        String postData= "{\n" +
        "  \"GET_SORT_OPTIONS_Input\" : {\n" +
        "   \"RESTHeader\": {\n" +
        "    },\n" +
        "   \"InputParameters\": {\n" +
        "        \"P_TYPE\": \"QUOTE\"\n" +
        "     }\n" +
        "  }\n" +
        "}";
            
           
            
            restServiceAdapter.setRetryLimit(0);
            System.out.println("postData===============================" + postData);
            
          String  response = restServiceAdapter.send(postData);
            
            System.out.println("response===============================" + response);
            
           JSONObject resp=new JSONObject(response);
             JSONObject output=resp.getJSONObject("OutputParameters");
            JSONObject data=output.getJSONObject("X_SORT_OPTIONS_TL");
            QuotationSortList.sort_List.clear();
            quotationSortList.clear();
            if(data.get("X_SORT_OPTIONS_TL_ITEM") instanceof  JSONArray){
              JSONArray segments=data.getJSONArray("X_SORT_OPTIONS_TL_ITEM");
              for(int i=0;i<segments.length();i++) {
                JSONObject notification=segments.getJSONObject(i);
                String lookupType=notification.getString("LOOKUP_TYPE");
                String lookupCode=notification.getString("LOOKUP_CODE");
               
                String meaning=notification.getString("MEANING");
                QuotationSort sort=new QuotationSort(lookupType, lookupCode, meaning);
                QuotationSortList.sort_List.add(sort);
                quotationSortList.add(sort);
                //System.out.println("Quotation List Size"+quotationSortList.size());  
                  
              }
            
            }
            
            else if(data.get("X_SORT_OPTIONS_TL_ITEM") instanceof  JSONObject){
               
                JSONObject notification=data.getJSONObject("X_SORT_OPTIONS_TL_ITEM");
                String lookupType=notification.getString("LOOKUP_TYPE");
                String lookupCode=notification.getString("LOOKUP_CODE");
                
                String meaning=notification.getString("MEANING");
                
                
                QuotationSort sort=new QuotationSort(lookupType, lookupCode, meaning);
                QuotationSortList.sort_List.add(sort);
                quotationSortList.add(sort);  
               
            }
                System.out.println("Quotation List Size"+QuotationSortList.sort_List.size());
             
           
            
            
            }
            catch(Exception e){
            e.printStackTrace();
            }
        String updateQuotSort="";
        if(!quotSort.equalsIgnoreCase("")){
        
        QuotationSort qst=(QuotationSort)quotationSortList.get((Integer.parseInt(quotSort)));
        updateQuotSort=qst.getLookupCode();
        System.out.println("New Value mul Org++++++++"+updateQuotSort);
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.QuotationSortValue}", String.class);
            ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), updateQuotSort);
        
        }
      
        /*try{
                     
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
        "        \"P_RFQ_ID\":\""+rfqId+"\",\n" +
        "\n" + 
        "        \"P_SORT_OPTION\":\""+updateQuotSort+"\"\n" +                 
                         
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
               
             QuotationList.s_jobs.clear();
           
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
                    QuotationList.s_jobs.add(q);
                    
               
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
                QuotationList.s_jobs.add(q);
                
                
            }
            
            
            
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.quotations.iterator}");   
            vex.setDataProvider(QuotationList.s_jobs);
            
            
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
           // System.out.println("Size is "+ mobile.QuotationList.s_jobs.size()+" "+vex.getTotalRowCount());
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
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

    public void buyerAssisted(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfContainerUtilities.gotoFeature("feature1");
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction("feature1",
        "adf.mf.api.amx.doNavigation", new Object[] { "buyerAssisted_default" });
        try{
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "chooseItemType", new Object[] { });
        // AdfmfContainerUtilities.invokeContainerJavaScriptFunction("feature1","adf.mf.api.amx.doNavigation", new Object[] { "buyerAssisted" });
        
        ValueExpression ve_user = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve_user.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve_orgId = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String multiOrgId = (String)ve_orgId.getValue(AdfmfJavaUtilities.getAdfELContext());

        ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to_locationCode}", String.class);
        String default_deliver_to_location_code = (String)ve49.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                "getItemtype",
                                                                                new Object[] { });
        }
        catch(Exception e){
        e.printStackTrace();
        }
        
      /*  try{
                 RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();    
                // Clear any previously set request properties, if any
                restServiceAdapter.clearRequestProperties();
                // Set the connection name
                restServiceAdapter.setConnectionName("enrich");
                    
                        
                restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_line_types/");
                String postData= "{\n" + 
                "  \"GET_LINE_TYPES_Input\" : {\n" + 
                "   \"RESTHeader\": {\n" + 
                "    },\n" + 
                "   \"InputParameters\": {\n" + 
                "       }	   \n" + 
                "   }\n" + 
                "}\n";
                    
                   
                    
                    restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                    
                  String  response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response);
                    
                   JSONObject resp=new JSONObject(response);
                     JSONObject output=resp.getJSONObject("OutputParameters");
                    JSONObject data=output.getJSONObject("X_LINE_TYPE_TL");
                    ItemTypeList.itemType_List.clear();
                    itemTypeList.clear();
                    if(data.get("X_LINE_TYPE_TL_ITEM") instanceof  JSONArray){
                      JSONArray segments=data.getJSONArray("X_LINE_TYPE_TL_ITEM");
                      for(int i=0;i<segments.length();i++) {
                        JSONObject notification=segments.getJSONObject(i);
                        String lineTypeId=notification.getString("LINE_TYPE_ID");
                        String lineTypeCode=notification.getString("LINE_TYPE_CODE");
                        String lineTypeDisc=notification.getString("LINE_TYPE_DESC");
                        ItemType item=new ItemType(lineTypeId, lineTypeCode, lineTypeDisc);
                        ItemTypeList.itemType_List.add(item);
                        itemTypeList.add(item);
                          
                      }
                    
                    }
                    
                    else if(data.get("X_LINE_TYPE_TL_ITEM") instanceof  JSONObject){
                       
                        JSONObject notification=data.getJSONObject("X_LINE_TYPE_TL_ITEM");
                        String lineTypeId=notification.getString("LINE_TYPE_ID");
                        String lineTypeCode=notification.getString("LINE_TYPE_CODE");
                        String lineTypeDisc=notification.getString("LINE_TYPE_DESC");
                        ItemType item=new ItemType(lineTypeId, lineTypeCode, lineTypeDisc);
                        ItemTypeList.itemType_List.add(item);
                        itemTypeList.add(item);
                       
                    }
                        System.out.println("Item Type List Size"+ItemTypeList.itemType_List.size());
                     
                   
                    
        
        }
        catch(Exception e){
        e.printStackTrace();
        }
      */
     
        
    }
     public void getFreeFormValues(String rr) {
         System.out.println("--------------Hello ItemType");
        /* ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costcenterForm}", String.class);
          //ve_searchText.setValue(AdfmfJavaUtilities.getAdfELContext(),"Search your costcenter");
          ve_searchText.setValue(AdfmfJavaUtilities.getAdfELContext()," ");*/
         
        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSearch}", String.class);
        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
        
        ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSubmit}", String.class);
        ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
         
         
        ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddCartButton}", String.class);
        ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
        
         ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showOthers}", String.class);
         ve23.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");

         ValueExpression veb1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showWeb}", String.class);
         veb1.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
         ValueExpression vebe1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.webURL}", String.class);
         vebe1.setValue(AdfmfJavaUtilities.getAdfELContext(),""); 
         ValueExpression vec1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemType}", String.class);
         vec1.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemNo}", String.class);
         vec2.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemDescriptionFrom}", String.class);
         vec3.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemCategoryForm}", String.class);
         vec4.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RequestTypeForm}", String.class);
         vec5.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
         vec6.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec8 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.MaxPriceFrom}", String.class);
         vec8.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec10 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
         vec10.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierForm}", String.class);
         vec11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierNotForm}", String.class);
         vec12.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
         ValueExpression vec13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierSiteForm}", String.class);
         vec13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costcenterForm}", String.class);
         vec14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.GLAccountForm}", String.class);
         vec15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.partnoForm}", String.class);
         vec16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression vec17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.referencenoForm}", String.class);
         vec17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         ValueExpression ve123 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
         ve123.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
         
         try{
                          RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();    
                         // Clear any previously set request properties, if any
                         restServiceAdapter.clearRequestProperties();
                         // Set the connection name
                         restServiceAdapter.setConnectionName("enrich");
                             
                                 
                         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_line_types/");
                         String postData= "{\n" + 
                         "  \"GET_LINE_TYPES_Input\" : {\n" + 
                         "   \"RESTHeader\": {\n" + 
                         "    },\n" + 
                         "   \"InputParameters\": {\n" + 
                         "       }          \n" + 
                         "   }\n" + 
                         "}\n";
                             
                            
                             
                             restServiceAdapter.setRetryLimit(0);
                             System.out.println("postData===============================" + postData);
                             
                           String  response = restServiceAdapter.send(postData);
                             
                             System.out.println("response===============================" + response);
                             
                            JSONObject resp=new JSONObject(response);
                              JSONObject output=resp.getJSONObject("OutputParameters");
                             JSONObject data=output.getJSONObject("X_LINE_TYPE_TL");
                             ItemTypeList.itemType_List.clear();
                             itemTypeList.clear();
                             ItemType l2=new ItemType("Please Select","Please Select","Please Select"); 
                             ItemTypeList.itemType_List.add(l2);
                             itemTypeList.add(l2);
                             if(data.get("X_LINE_TYPE_TL_ITEM") instanceof  JSONArray){
                               JSONArray segments=data.getJSONArray("X_LINE_TYPE_TL_ITEM");
                               for(int i=0;i<segments.length();i++) {
                                 JSONObject notification=segments.getJSONObject(i);
                                 String lineTypeId=notification.getString("LINE_TYPE_ID");
                                 String lineTypeCode=notification.getString("LINE_TYPE_CODE");
                                 String lineTypeDisc=notification.getString("LINE_TYPE_DESC");
                                 ItemType item=new ItemType(lineTypeId, lineTypeCode, lineTypeDisc);
                                 ItemTypeList.itemType_List.add(item);
                                 itemTypeList.add(item);
                                   
                               }
                             
                             }
                             
                             else if(data.get("X_LINE_TYPE_TL_ITEM") instanceof  JSONObject){
                                
                                 JSONObject notification=data.getJSONObject("X_LINE_TYPE_TL_ITEM");
                                 String lineTypeId=notification.getString("LINE_TYPE_ID");
                                 String lineTypeCode=notification.getString("LINE_TYPE_CODE");
                                 String lineTypeDisc=notification.getString("LINE_TYPE_DESC");
                                 ItemType item=new ItemType(lineTypeId, lineTypeCode, lineTypeDisc);
                                 ItemTypeList.itemType_List.add(item);
                                 itemTypeList.add(item);
                                
                             }
                                 System.out.println("Item Type List Size"+ItemTypeList.itemType_List.size());
                              
                            
                             
                 
                 }
                 catch(Exception e){
                 e.printStackTrace();
                 }
         
         
         ValueExpression ve_user = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
         String userId = (String)ve_user.getValue(AdfmfJavaUtilities.getAdfELContext());
         
         ValueExpression ve_orgId = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
         String multiOrgId = (String)ve_orgId.getValue(AdfmfJavaUtilities.getAdfELContext());

         ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to_locationCode}", String.class);
         String default_deliver_to_location_code = (String)ve49.getValue(AdfmfJavaUtilities.getAdfELContext());
         
         ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
         String default_cost_center_Value = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());

         ValueExpression ve150 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_natural_account}", String.class);
         String default_natural_account_Value = (String)ve150.getValue(AdfmfJavaUtilities.getAdfELContext());

         
         System.out.println("Default Location Code-->"+default_deliver_to_location_code);
         try{
             RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
               // Clear any previously set request properties, if any
               restServiceAdapter.clearRequestProperties();
               // Set the connection name
               restServiceAdapter.setConnectionName("enrich");
               
               restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
               restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                   ///
               restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
               restServiceAdapter.addRequestProperty("Content-Type", "application/json");
               restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_uom/");
               String postData= "{\n" + 
               "\n" + 
               "  \"GET_UOM_Input\" : {\n" + 
               "\n" + 
               "    \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_uom/\",\n" + 
               "\n" + 
               "   \"RESTHeader\": {\n" + 
               "\n" + 
               "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
               "\n" + 
               "    },\n" + 
               "\n" + 
               "   \"InputParameters\": {\n" + 
               "\n" + 
               "     }\n" + 
               "\n" + 
               "   } \n" + 
               "\n" + 
               "}";
             restServiceAdapter.setRetryLimit(0);
             System.out.println("postData===============================" + postData);
             
             String response = restServiceAdapter.send(postData);
             
             JSONObject resp=new JSONObject(response);
             JSONObject output=resp.getJSONObject("OutputParameters");
             JSONObject tbl=output.getJSONObject("X_UOM_TL");
             UOMList.s_jobs.clear();
             UOM u1=new UOM("Please Select");
             UOMList.s_jobs.add(u1);
             if(tbl.get("X_UOM_TL_ITEM") instanceof  JSONArray){
                  JSONArray segments=tbl.getJSONArray("X_UOM_TL_ITEM");
                  for(int i=0;i<segments.length();i++) {
                      JSONObject item=(JSONObject)segments.get(i);
                      UOM u=new UOM(item.getString("UNIT_OF_MEASURE"));
                      if(item.getString("UNIT_OF_MEASURE").equalsIgnoreCase("Each")){
                          System.out.println("Each match "+i);
                      ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.UOMFrom}", String.class);
                      ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf((i+1)));
                      }
                      
                      
                      UOMList.s_jobs.add(u);
                      
                  }
               
               }
               
               else if(tbl.get("X_UOM_TL_ITEM") instanceof  JSONObject){
                   
                   JSONObject item=tbl.getJSONObject("X_UOM_TL_ITEM");
                   UOM u=new UOM(item.getString("UNIT_OF_MEASURE"));
                   if(item.getString("UNIT_OF_MEASURE").equalsIgnoreCase("Each")){
                   ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
                   ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(1));
                   }
                   UOMList.s_jobs.add(u);
                   
                   //JSONObject segments=data.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
                   
               }
             //====Alias
              
             restServiceAdapter = Model.createRestServiceAdapter();
             // Clear any previously set request properties, if any
             restServiceAdapter.clearRequestProperties();
             // Set the connection name
             restServiceAdapter.setConnectionName("enrich");
             
             restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
             restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
             restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
             restServiceAdapter.addRequestProperty("Content-Type", "application/json");
             restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_category_alias/");
             postData= "{\n" + 
              "\n" + 
              "    \"GET_CATEGORY_ALIAS_Input\":{\n" + 
              "        \"RESTHeader\":{\n" + 
              "        },\n" + 
              "        \"InputParameters\":{\n" + 
              "        }\n" + 
              "    }\n" + 
              "\n" + 
              "}";
             
                                         restServiceAdapter.setRetryLimit(0);
                System.out.println("postData===============================" + postData);
                 
                response = restServiceAdapter.send(postData);
                 
                 System.out.println("response===============================" + response); 
                  resp=new JSONObject(response);
                  output=resp.getJSONObject("OutputParameters");
                JSONObject data1=new JSONObject();
              try{
                  data1=output.getJSONObject("X_CATEGORY_ALIAS_TL");
                    AliasList.s_jobs.clear();
                    aliasList.clear();
                 if(data1.get("X_CATEGORY_ALIAS_TL_ITEM") instanceof  JSONArray){
                     Alias c2=new Alias("Please Select","Please Select","Please Select","Please Select"); 
                     AliasList.s_jobs.add(c2);
                     aliasList.add(c2);   
                   JSONArray segments=data1.getJSONArray("X_CATEGORY_ALIAS_TL_ITEM");
                   for(int i=0;i<segments.length();i++) {
                     JSONObject alias=segments.getJSONObject(i);
                     String aliasname=alias.getString("ALIAS_NAME");
                     String oracleCategotyId=alias.getString("ORACLE_CATEGORY_ID");
                     //String indixCategotyId=alias.getString("INDIX_CATEGORY");
                     String oracleCategotySeg=alias.getString("ORACLE_CATEGORY_SEG");
                       String indixCategotyId=""; 
                       if(alias.get("INDIX_CATEGORY_TL") instanceof  JSONObject) {
                           JSONObject indixTL=alias.getJSONObject("INDIX_CATEGORY_TL");
                           indixCategotyId=indixTL.getString("INDIX_CATEGORY_TL_ITEM");
                       }
                       else {
                           indixCategotyId=alias.getString("INDIX_CATEGORY_TL");
                       }
                       
                       System.out.println("((((indixCategotyId)))))"+indixCategotyId);

                     Alias als=new Alias(aliasname, oracleCategotyId, indixCategotyId, oracleCategotySeg);
                     AliasList.s_jobs.add(als);
                       aliasList.add(als);
                   }
                 
                 }
                 
                 else if(data1.get("X_CATEGORY_ALIAS_TL_ITEM") instanceof  JSONObject){
                     Alias c2=new Alias("Please Select","Please Select","Please Select","Please Select"); 
                     AliasList.s_jobs.add(c2);
                     aliasList.add(c2);
                    JSONObject alias=data1.getJSONObject("X_CATEGORY_ALIAS_TL_ITEM");
                     String aliasname=alias.getString("ALIAS_NAME");
                     String oracleCategotyId=alias.getString("ORACLE_CATEGORY_ID");
                     //String indixCategotyId=alias.getString("INDIX_CATEGORY");
                     String oracleCategotySeg=alias.getString("ORACLE_CATEGORY_SEG");
                     String indixCategotyId=""; 
                     if(alias.get("INDIX_CATEGORY_TL") instanceof  JSONObject) {
                         JSONObject indixTL=alias.getJSONObject("INDIX_CATEGORY_TL");
                         indixCategotyId=indixTL.getString("INDIX_CATEGORY_TL_ITEM");
                     }
                     else {
                         indixCategotyId=alias.getString("INDIX_CATEGORY_TL");
                     }
                     
                     System.out.println("((((indixCategotyId)))))"+indixCategotyId);

                     Alias als=new Alias(aliasname, oracleCategotyId, indixCategotyId, oracleCategotySeg);
                     AliasList.s_jobs.add(als);
                     aliasList.add(als);
                 }
                 }
                 catch(Exception e) {
                     e.printStackTrace();
                 }
             
             
             // =====Alias End
             //   Deliver to Location Start
             
             // Clear any previously set request properties, if any
             restServiceAdapter.clearRequestProperties();
             // Set the connection name
             restServiceAdapter.setConnectionName("enrich");
             
             restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
             restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
             restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
             restServiceAdapter.addRequestProperty("Content-Type", "application/json");
             restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
             postData= "{\n" +
             "\n" +
             "  \"GET_DELIVER_TO_Input\" : {\n" +
             "\n" +
             "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_deliver_to/\",\n" +
             "\n" +
             "   \"RESTHeader\": {\n" +
             "\n" +
             "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
             "    },\n" +
             "\n" +
             "   \"InputParameters\": {\n" +
             "\n" +
             "        \"P_USER_ID\":\""+userId+"\",\n" +
                         "         \"P_ORG_ID\":\""+multiOrgId+"\"\n" + 
             "\n" +
             "     }\n" +
             "\n" +
             "  }\n" +
             "\n" +
             "}  ";
                                   restServiceAdapter.setRetryLimit(0);
             System.out.println("postData===============================" + postData);
             response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response);
                    resp=new JSONObject(response);
                    output=resp.getJSONObject("OutputParameters");
                    JSONObject data=new JSONObject();
             try{
                      data=output.getJSONObject("X_DELIVER_TO_TL");
                      DeliverToLocationList.s_jobs.clear();
                        deliverToLocationList.clear();
                        DeliverToLocation l2=new DeliverToLocation("Please Select","Please Select","Please Select"); 
                        DeliverToLocationList.s_jobs.add(l2);
                        deliverToLocationList.add(l2);

                      if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
                      JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
                      for(int i=0;i<segments.length();i++) {
                        JSONObject location=segments.getJSONObject(i);
                        String locationId=location.getString("LOCATION_ID");
                        String locationCode=location.getString("LOCATION_CODE");
                        String locationDescription=location.getString("DESCRIPTION");
                         if(locationCode.equalsIgnoreCase(default_deliver_to_location_code)) {
                            System.out.println("Deliver to Location position"+"Match Occurs"+locationCode+"---"+default_deliver_to_location_code+"---"+String.valueOf(i));
                             ValueExpression ve_deliver_code = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.DeliverLocationForm}", String.class);
                             ve_deliver_code.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf((i+1)));
                         }
                        DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                        DeliverToLocationList.s_jobs.add(loc);
                          deliverToLocationList.add(loc);
                      }
                      
                      }
                      
                      else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
                       
                       JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                        String locationId=location.getString("LOCATION_ID");
                        String locationCode=location.getString("LOCATION_CODE");
                        String locationDescription=location.getString("DESCRIPTION");
                        if(locationCode.equalsIgnoreCase(default_deliver_to_location_code)) {
                            System.out.println("Deliver to Location position"+String.valueOf(0));
                        }
                        DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                        DeliverToLocationList.s_jobs.add(loc);
                        deliverToLocationList.add(loc);
                       
                      }
                      }
                      catch(Exception e) {
                        e.printStackTrace();
                      }
             
             //Get Cost Center Values
             restServiceAdapter = Model.createRestServiceAdapter();
                   // Clear any previously set request properties, if any
                   restServiceAdapter.clearRequestProperties();
                   // Set the connection name
                   restServiceAdapter.setConnectionName("enrich");
                   
                   restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                   restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                   restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                   restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                   restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cost_center/");
                   postData= "{\n" +
                          "\n" +
                          "  \"GET_COST_CENTER_Input\" : {\n" +
                          "\n" +
                          "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_cost_center/\",\n" +
                          "\n" +
                          "   \"RESTHeader\": {\n" +
                          "\n" +
                          "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
                          "    },\n" +
                          "\n" +
                          "   \"InputParameters\": {\n" +
                          "\n" +
                           "          \"P_USER_ID\" : \""+userId+"\",\n" +
                           "          \"P_ORG_ID\" : \""+multiOrgId+"\",\n" +
                           "          \"P_SEARCH_TEXT\" : \"\"\n" +                    
                          "\n" +
                          "     }\n" +
                          "\n" +
                          "  }\n" +
                          "\n" +
                          "}  ";
                       
                           restServiceAdapter.setRetryLimit(0);
                      System.out.println("postData===============================" + postData);
                      System.out.println("postData===============================" + postData);
                          
                       response = restServiceAdapter.send(postData);
                       System.out.println("response===============================" + response);   
                       resp=new JSONObject(response);
                       output=resp.getJSONObject("OutputParameters");
             try{
                            data=output.getJSONObject("X_COST_CENTER_TL");
                                   CostCenterList.s_jobs.clear();
                                   costCenterList.clear();
                             
                                 if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
                                                                 JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
                                                                 for(int i=0;i<segments.length();i++) {
                                                                     //String name=(String)segments.get(i);
                                                                     JSONObject ci=(JSONObject)segments.get(i);
                                                                     String name=ci.getString("SEGMENT_VALUE");
                                                                     String description=ci.getString("DESCRIPTION");
                                                                     if(description.equalsIgnoreCase(default_cost_center_Value)) {
                                                                      vec14.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
                                                                       System.out.println("Dafult Cost Center Value-->"+default_cost_center_Value);

                                                                      }

                                                                     CostCenter c=new CostCenter(name,description);
                                                                     CostCenterList.s_jobs.add(c);
                                                                     costCenterList.add(c);
                                                                     
                                                                 }
                                                               
                                                               }
                                                               
                                                               else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
                                                                  
                                                                  JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
                                                                   String name=ci.getString("SEGMENT_VALUE");
                                                                   String description=ci.getString("DESCRIPTION");
                                                                   CostCenter c=new CostCenter(name,description);
                                                                   CostCenterList.s_jobs.add(c);
                                                                   costCenterList.add(c);
                                                                  
                                                               }
                                                                 
                             
                             }
                             catch(Exception e) {
                                 e.printStackTrace();
                             }
             // GET Natural Account based MultiOrg 
             restServiceAdapter = Model.createRestServiceAdapter();
             // Clear any previously set request properties, if any
             restServiceAdapter.clearRequestProperties();
             // Set the connection name
             restServiceAdapter.setConnectionName("enrich");
             
             restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
             restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
             restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
             restServiceAdapter.addRequestProperty("Content-Type", "application/json");
             restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_natural_acct/");
                 postData= "{\n" + 
                 "\n" + 
                 "  \"GET_NATURAL_ACCT_Input\" : {\n" + 
                 "\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_natural_acct/\",\n" + 
                 "\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "\n" + 
                 "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                 "    },\n" + 
                 "\n" + 
                 "   \"InputParameters\": {\n" + 
                 "\n" + 
                 "          \"P_USER_ID\" : \""+userId+"\",\n" +
                 "          \"P_ORG_ID\" : \""+multiOrgId+"\"\n" + 
                 "\n" + 
                 "     }\n" + 
                 "\n" + 
                 "  }\n" + 
                 "\n" + 
                 "}  ";
                 
                     restServiceAdapter.setRetryLimit(0);
                System.out.println("postData===============================" + postData);
                 
                 response = restServiceAdapter.send(postData);
                 
                 System.out.println("response===============================" + response); 
                  resp=new JSONObject(response);
                  output=resp.getJSONObject("OutputParameters");
             try{
                 data=output.getJSONObject("X_NATURAL_ACC_TL");
                 NaturalAcccountList.acc_List.clear();
                 naturalAccountList.clear();
                 
                 if(data.get("X_NATURAL_ACC_TL_ITEM") instanceof  JSONArray){
                   JSONArray segments=data.getJSONArray("X_NATURAL_ACC_TL_ITEM");
                   for(int i=0;i<segments.length();i++) {
                       //String name=(String)segments.get(i);
                       JSONObject na=(JSONObject)segments.get(i);
                       String name=na.getString("SEGMENT_VALUE");
                       String description=na.getString("DESCRIPTION");
                       if(description.equalsIgnoreCase(default_natural_account_Value)) {
                        vec15.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
                         System.out.println("Dafult GL Account Value-->"+default_natural_account_Value);
                        }

                       NaturalAccounts c=new NaturalAccounts(name,description);
                       NaturalAcccountList.acc_List.add(c);
                       naturalAccountList.add(c);
                       
                   }
                 
                 }
                 
                 else if(data.get("X_NATURAL_ACC_TL_ITEM") instanceof  JSONObject){
                    
                    JSONObject na=data.getJSONObject("X_NATURAL_ACC_TL_ITEM");
                     String name=na.getString("SEGMENT_VALUE");
                     String description=na.getString("DESCRIPTION");
  /*                   if(description.equalsIgnoreCase(default_natural_account_Value)) {
                      ve_natural.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
                       System.out.println("Dafult GL Account Value-->"+default_natural_account_Value);
                      }
*/
                     NaturalAccounts c=new NaturalAccounts(name,description);
                     NaturalAcccountList.acc_List.add(c);
                     naturalAccountList.add(c);
                    
                 }
                  
                 
                 }
                 catch(Exception e) {
                     e.printStackTrace();
                 }
             
  
          //GET  Request Type
          // 
          restServiceAdapter = Model.createRestServiceAdapter();
          // Clear any previously set request properties, if any
          restServiceAdapter.clearRequestProperties();
          // Set the connection name
          restServiceAdapter.setConnectionName("enrich");
          
          restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
          restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
          restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
          restServiceAdapter.addRequestProperty("Content-Type", "application/json");
          restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_request_type_lkup/");
              postData= "{\n" + 
              "  \"GET_REQUEST_TYPE_LKUP_Input\" : {\n" + 
              "   \"RESTHeader\": {\n" + 
              "    },\n" + 
              "   \"InputParameters\": {\n" + 
              "       }	   \n" + 
              "   }\n" + 
              "}\n";
              
              restServiceAdapter.setRetryLimit(0);
              System.out.println("postData===============================" + postData);
              
              response = restServiceAdapter.send(postData);
              
              System.out.println("response===============================" + response); 
               resp=new JSONObject(response);
               output=resp.getJSONObject("OutputParameters");
          try{
              data=output.getJSONObject("X_REQUEST_TYPE_TL");
              RequestTypeList.req_List.clear();
              requestTypeList.clear();
                  RequestType l2=new RequestType("Please Select","Please Select","Please Select"); 
                  RequestTypeList.req_List.add(l2);
                  requestTypeList.add(l2);
              
              if(data.get("X_REQUEST_TYPE_TL_ITEM") instanceof  JSONArray){
                JSONArray segments=data.getJSONArray("X_REQUEST_TYPE_TL_ITEM");
                for(int i=0;i<segments.length();i++) {
                    JSONObject na=(JSONObject)segments.get(i);
                    String lookupType=na.getString("LOOKUP_TYPE");
                    String lookupCode=na.getString("LOOKUP_CODE");
                    String meaning=na.getString("MEANING");
                    RequestType c=new RequestType(lookupType, lookupCode, meaning);
                    RequestTypeList.req_List.add(c);
                    requestTypeList.add(c);
                    
                }
              
              }
              
              else if(data.get("X_REQUEST_TYPE_TL_ITEM") instanceof  JSONObject){
                 
                 JSONObject na=data.getJSONObject("X_REQUEST_TYPE_TL_ITEM");
                  String lookupType=na.getString("LOOKUP_TYPE");
                  String lookupCode=na.getString("LOOKUP_CODE");
                  String meaning=na.getString("MEANING");
                  RequestType c=new RequestType(lookupType, lookupCode, meaning);
                  RequestTypeList.req_List.add(c);
                  requestTypeList.add(c);                 
              }
               
              
              }
              catch(Exception e) {
                  e.printStackTrace();
              }
          
         }
         catch(Exception e) {
            e.printStackTrace();
         }
         
         AmxAttributeBinding itemType = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.itemType}");
         AmxIteratorBinding itemTypeListIterator =  itemType.getIteratorBinding();
         itemTypeListIterator.refresh();
         
         AmxAttributeBinding reqType = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.requestType}");
         AmxIteratorBinding reqTypeListIterator =  reqType.getIteratorBinding();
         reqTypeListIterator.refresh();
         
         AmxAttributeBinding deliverLoc = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.deliverToLocations}");
         AmxIteratorBinding deliverLocListIterator =  deliverLoc.getIteratorBinding();
         deliverLocListIterator.refresh();
         
         AmxAttributeBinding uomList = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.UOM}");
         AmxIteratorBinding uomListIterator =  uomList.getIteratorBinding();
         uomListIterator.refresh();
         
         /*AmxAttributeBinding costList = (AmxAttributeBinding) AdfmfJavaUtilities
                           .evaluateELExpression("#{bindings.costCenters}");
         AmxIteratorBinding costListIterator =  costList.getIteratorBinding();
         costListIterator.refresh();*/
         
        /* AmxAttributeBinding naturalList = (AmxAttributeBinding) AdfmfJavaUtilities
                           .evaluateELExpression("#{bindings.naturalAccounts}");
         AmxIteratorBinding naturalListIterator =  naturalList.getIteratorBinding();
         naturalListIterator.refresh();*/
     }

    public void getCostCenterValueform(ActionEvent actionEvent) {
        // Add event code here...
        
        // Add event code here...
        /*
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression vemul = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String multiOrgId=(String)vemul.getValue(AdfmfJavaUtilities.getAdfELContext());
       
        
        ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costcenterFormSearchText}", String.class);
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
                    restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cost_center/");
                     String  postData= "{\n" +
                           "\n" +
                           "  \"GET_COST_CENTER_Input\" : {\n" +
                           "\n" +
                           "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_cost_center/\",\n" +
                           "\n" +
                           "   \"RESTHeader\": {\n" +
                           "\n" +
                           "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
                           "    },\n" +
                           "\n" +
                           "   \"InputParameters\": {\n" +
                           "\n" +
                            "          \"P_USER_ID\" : \""+userId+"\",\n" +
                            "          \"P_ORG_ID\" : \""+multiOrgId+"\",\n" +
                            "          \"P_SEARCH_TEXT\" : \""+searchText+"\"\n" +                    
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
                    try{
                       JSONObject   data=output.getJSONObject("X_COST_CENTER_TL");
                              CostCenterList.s_jobs.clear();
                              costCenterList.clear();
                        
                            if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
                                                            JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
                                                            for(int i=0;i<segments.length();i++) {
                                                                //String name=(String)segments.get(i);
                                                                JSONObject ci=(JSONObject)segments.get(i);
                                                                String name=ci.getString("SEGMENT_VALUE");
                                                                String description=ci.getString("DESCRIPTION");
                                                                CostCenter c=new CostCenter(name,description);
                                                                CostCenterList.s_jobs.add(c);
                                                                costCenterList.add(c);
                                                                
                                                            }
                                                          
                                                          }
                                                          
                                                          else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
                                                             
                                                             JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
                                                              String name=ci.getString("SEGMENT_VALUE");
                                                              String description=ci.getString("DESCRIPTION");
                                                              CostCenter c=new CostCenter(name,description);
                                                              CostCenterList.s_jobs.add(c);
                                                              costCenterList.add(c);
                                                             
                                                          }
                        
                         
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
            
            
            }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        AmxAttributeBinding accountList = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.costCenters}");
        AmxIteratorBinding accountListIterator =  accountList.getIteratorBinding();
        accountListIterator.refresh();
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.costCenters1.iterator}");
        vex.refresh();
        
        */
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showCostCenters.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.costCenters.iterator}");  
        vex.refresh();
    }

    public void rowchangeCostCenterForm(ActionEvent actionEvent) {
        // Add event code here...
        ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costcenterFormSearchText}", String.class);
        ve_searchText.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
    }

    public void rowchangeGLAccountForm(ActionEvent actionEvent) {
        // Add event code here...
        ValueExpression v_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.GLAccountFormSearchText}", String.class);
        v_searchText.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
    }

    public void getGLAccountValueform(ActionEvent actionEvent) {
        // Add event code here...
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showGLAcccounts.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
         
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.naturalAccounts.iterator}");  
        vex.refresh();
    }

    public void getSuppliersValueform(ActionEvent actionEvent) {
        // Add event code here...
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showSuppliers.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

    public void rowchangeSupplierForm(ActionEvent actionEvent) {
        // Add event code here...
        ValueExpression searchSupplierText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SupplierFormSearchText}", String.class);
        searchSupplierText.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
    }

    public void getSupplierSiteform(ActionEvent actionEvent) {
        // Add event code here...
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showSupplierSites.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

    public void rowchangeSupplierSiteForm(ActionEvent actionEvent) {
        // Add event code here...
        ValueExpression searchSiteText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierSiteFormSearchText}", String.class);
        searchSiteText.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
    }

    public void showSearchButton(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
       
        RequestType c=(RequestType)RequestTypeList.req_List.get(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
        if(c.getMeaning().equalsIgnoreCase("others"))
        {
        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSearch}", String.class);
        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
        ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddCartButton}", String.class);
        ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),"flase");
        }
        else {
            ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSearch}", String.class);
            ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddCartButton}", String.class);
            ve21.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
        }
    }
}
