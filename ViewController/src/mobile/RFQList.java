package mobile;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.bindings.dbf.AmxAttributeBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
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

public class RFQList {
    
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    
    public RFQList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public RFQ[] getRFQ() {
        RFQ e[] = null;
        e = (RFQ[])s_jobs.toArray(new RFQ[s_jobs.size()]);
        return e;
    }
    
    
    public void getRFQList() {
        
        RFQ j = new RFQ(); 
        s_jobs.add(j);
        
          
        
    }
    
    public void searchRFQs() {
        try{
               ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
               String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
               
               ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
               String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
                   
               ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
               String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
               ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
               String multiOrgId = (String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
            
                   ValueExpression ve_search = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchRFQValue}", String.class);
                   String searchRFQTxt = (String)ve_search.getValue(AdfmfJavaUtilities.getAdfELContext());
        
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                   String searchRFQNo="";
                       
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
               "          \"P_ITEM_DESCRIPTION\" : \""+searchRFQTxt+"\",\n" +
               "          \"P_ORG_ID\" : \""+multiOrgId+"\",\n" +
               "          \"P_RFQ_NUM\" : \""+searchRFQNo+"\"\n" + 
               "     }\n" + 
               "  }\n" + 
               "}  \n";
                                           restServiceAdapter.setRetryLimit(0);
                  System.out.println("postData===============================" + postData);
                   
                  String response = restServiceAdapter.send(postData);
                   
                   System.out.println("response===============================" + response);
                 //  System.out.println("response===============================" + response);
                   JSONObject resp=new JSONObject(response);
                   JSONObject output=resp.getJSONObject("OutputParameters");
                   JSONObject summTBL=output.getJSONObject("X_RFQ_TL");
                   
                   RFQList.s_jobs.clear();
                //   RFQ rfq1=new RFQ("", "", "Please Select", "","","");
                //   RFQList.s_jobs.add(rfq1);
                   
                   if(summTBL.get("X_RFQ_TL_ITEM") instanceof JSONArray) {
                      // System.out.println("Inside JSON ===============================" + response);                             
                       JSONArray summItm=summTBL.getJSONArray("X_RFQ_TL_ITEM");
                       for(int i=0;i<summItm.length();i++) {
                           JSONObject data=(JSONObject)summItm.get(i);
                           
                           String rfqId=data.getString("RFQ_ID");
                           String rfgNo=data.getString("RFQ_NUM");
                           String itemDescription=data.getString("ITEM_DESCRIPTION");
                           //itemDescription=itemDescription+"-"+rfgNo;
                           String rfqCloseDate=data.getString("RFQ_CLOSE_DATE");
                           if (rfqCloseDate.contains("{")) {
                               rfqCloseDate = "";
                           }
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
                       String rfqCloseDate=data.getString("RFQ_CLOSE_DATE");
                       if (rfqCloseDate.contains("{")) {
                                               rfqCloseDate = "";
                                           }
                       String rfqNeedByDate=data.getString("NEED_BY_DATE");
                       if (rfqNeedByDate.contains("{")) {
                                               rfqNeedByDate = "";
                                           }
                       String deliverToLocation=data.getString("DELIVER_TO_LOC_CODE");
                       RFQ rfq=new RFQ(rfqId, rfgNo, itemDescription, rfqCloseDate,rfqNeedByDate,deliverToLocation);
                       RFQList.s_jobs.add(rfq);                                          
                   
                   }
                   
               //            AdfmfContainerUtilities.gotoFeature("mp.Requisition");
                 
               BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.RFQ.iterator}");   
               vex.refresh();
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
    
}
