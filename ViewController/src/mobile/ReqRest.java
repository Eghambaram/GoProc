package mobile;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;

import oracle.adfmf.util.GenericType;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReqRest {
    public ReqRest() {
    }

    public void getRequisitions(ActionEvent actionEvent) {
        // Add event code here...
        
        
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
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "U0lWQU06d2VsY29tZTE=");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_req_summary/");
        String postData= "{\n" + 
        "  \"GET_REQ_SUMMARY_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_req_summary/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\",\n" + 
        "   \"Responsibility\" : \"SYSTEM_ADMINISTRATOR\",\n" + 
        "   \"RespApplication\" : \"SYSADMIN\",\n" + 
        "   \"SecurityGroup\": \"STANDARD\",\n" + 
        "   \"NLSLanguage\" : \"AMERICAN\",\n" + 
        "   \"Org_Id\" : \"83\" \n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "        \"P_USER_ID\": \""+1150+"\"\n" + 
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

    public void getReqRows(ActionEvent actionEvent) {
        // Add event code here...
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.requisitions.iterator}");  
        //Item selectedItem=(Item)vex.next()
        System.out.println("Inside Get Req Rows "); 
         while(vex.hasNext()) {
             System.out.println("Inside while loop "); 
             GenericType row= (GenericType)vex.next();
             
             for(int i=0;i<row.getAttributeCount();i++) {
                 System.out.println("***"+row.getAttribute(i));
             }
             
         }
    }
}
