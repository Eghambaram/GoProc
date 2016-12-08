package mobile;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;

import org.json.JSONArray;
import org.json.JSONObject;

public class AlertsList {
    public static List alertList = new ArrayList();
    public AlertsList() {
        super();
        if (alertList == null) {
            alertList = new ArrayList();
        }
    }
    
    public Alert[] getAlerts() {
        Alert e[] = null;
        e = (Alert[])alertList.toArray(new Alert[alertList.size()]);
        return e;
    }
    
    
    public void getAlertList() {
        Alert j = new Alert(); 
        alertList.add(j);
    }
    
    public void notifyTypeRequisition() {
        try{
            
            
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
         ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
         String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
        
            ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.notificationType}", String.class);
            String notifyType = (String)ve16.getValue(AdfmfJavaUtilities.getAdfELContext());
            
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
            "          \"P_USER_ID\" : \""+userId+"\",\n" +
            "          \"P_NOTIFICATION_TYPE\" : \""+notifyType+"\"\n" +
        "       }          \n" + 
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
    
}
