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


public class NaturalAcccountList {
    public static List acc_List = new ArrayList();
    public NaturalAcccountList() {
        super();
        if (acc_List == null) {
            acc_List = new ArrayList();
        }
    }
    public NaturalAccounts[] getNaturalAccounts() {
        NaturalAccounts e[] = null;
        e = (NaturalAccounts[])acc_List.toArray(new NaturalAccounts[acc_List.size()]);
        return e;
    }
    
    
    public void getNaturalAcccountList() {
        
        NaturalAccounts nAcc = new NaturalAccounts(); 
        acc_List.add(nAcc);
        
          
        
    }
    
        public void showGLAcccounts() {
            // Add event code here...
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression vemul = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String multiOrgId=(String)vemul.getValue(AdfmfJavaUtilities.getAdfELContext());
             ValueExpression ve_costcode = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costcenterFormId}", String.class);
             String costCenterCode=(String)ve_costcode.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.GLAccountFormSearchText}", String.class);
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
                    restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_natural_acct/");
                     String  postData= "{\n" + 
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
                        "          \"P_ORG_ID\" : \""+multiOrgId+"\",\n" +
                        "          \"P_COST_CENTER\" : \""+costCenterCode+"\",\n" + 
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
                           JSONObject data=output.getJSONObject("X_NATURAL_ACC_TL");
                                NaturalAcccountList.acc_List.clear();
                                System.out.println("GLAccount outside if loop:"+NaturalAcccountList.acc_List.size());
                            
                                if(data.get("X_NATURAL_ACC_TL_ITEM") instanceof  JSONArray){
                                  JSONArray segments=data.getJSONArray("X_NATURAL_ACC_TL_ITEM");
                                  for(int i=0;i<segments.length();i++) {
                                      //String name=(String)segments.get(i);
                                      JSONObject na=(JSONObject)segments.get(i);
                                      String name=na.getString("SEGMENT_VALUE");
                                      String description=na.getString("DESCRIPTION");
                                      
                                      NaturalAccounts n=new NaturalAccounts(name,description);
                                      NaturalAcccountList.acc_List.add(n);
                                      
                                  }
                                System.out.println("GLAccount inside if loop list:"+NaturalAcccountList.acc_List.size());
                                }
                                
                                else if(data.get("X_NATURAL_ACC_TL_ITEM") instanceof  JSONObject){
                                   
                                   JSONObject na=data.getJSONObject("X_NATURAL_ACC_TL_ITEM");
                                    String name=na.getString("SEGMENT_VALUE");
                                    String description=na.getString("DESCRIPTION");
                                   
                                    NaturalAccounts n=new NaturalAccounts(name,description);
                                    NaturalAcccountList.acc_List.add(n);
                                   
                                }  
                             
                            }
                            catch(Exception e) {
                                e.printStackTrace();
                            }
                
                
                }
            catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("GL Account List size::"+NaturalAcccountList.acc_List.size());
             BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.naturalAccounts.iterator}");  
             vex.refresh();
         }

    }

