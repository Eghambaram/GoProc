package mobile;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class CostCenterList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
    public CostCenterList() {
        super();
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public CostCenter[] getCostCenters() {
        CostCenter e[] = null;
        e = (CostCenter[])s_jobs.toArray(new CostCenter[s_jobs.size()]);
        return e;
    }
    
    
    public void getCostCenterList() {
        
        CostCenter j = new CostCenter(); 
        s_jobs.add(j);
        
          
        
    }
    
    public void showCostCenters() {
        // Add event code here...
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
                             
                        
                            if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
                                                            JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
                                                            for(int i=0;i<segments.length();i++) {
                                                                //String name=(String)segments.get(i);
                                                                JSONObject ci=(JSONObject)segments.get(i);
                                                                String name=ci.getString("SEGMENT_VALUE");
                                                                String description=ci.getString("DESCRIPTION");
                                                                CostCenter c=new CostCenter(name,description);
                                                                CostCenterList.s_jobs.add(c);
                                                                
                                                            }
                                                          
                                                          }
                                                          
                                                          else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
                                                             
                                                             JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
                                                              String name=ci.getString("SEGMENT_VALUE");
                                                              String description=ci.getString("DESCRIPTION");
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
        }
         BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.costCenters.iterator}");  
         vex.refresh();
     }

    }
