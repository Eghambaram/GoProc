

package mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.logging.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Random;

import javax.el.ValueExpression;


import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
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
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.String;

import oracle.adfmf.bindings.dbf.AmxAttributeBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;

public class ItemsList {
        private static Object tag;
        private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
        private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        public static List s_jobs  = new ArrayList();
        private static List items  = new ArrayList();
        public static List items_selected = new ArrayList();
        public static List items_ref = new ArrayList();
        public static List cpy_list = new ArrayList();
        public static List filt_list = new ArrayList();
        public static List items_list = new ArrayList();
        public static List temp_items_list = new ArrayList();
        public static int pageNo=1;
        public static String search;
        public static String aliasCategoriesId;
        public static String itemType;
        public static boolean isFilterSet;
        public static boolean isSortSet;
        String categoryText1="";
        String categoryText2="";
        String categoryText3="";
        String categoryText4="";
        public static int categoryId=0;
        public static String categoryRef="&";
        public static int pageSize=0;
        public static int size=0;
        public static List deliverToLocationList=new ArrayList();
        public static List costCenterList=new ArrayList();
        public static HashMap<Integer,Integer> pageMap=new HashMap();
        public static List Specification = new ArrayList();
        public static List naturalAccountList = new ArrayList();
        public static List costCenterNaturalAccountsList = new ArrayList();
     
        public ItemsList() {
            if (s_jobs == null) {
                s_jobs = new ArrayList();
            }
          
        }
        
        public ItemsList(String a) {
            
        }
       
        public void addProviderChangeListener(ProviderChangeListener l) {
    
                providerChangeSupport.addProviderChangeListener(l);
    
            }
    
         
    
            public void removeProviderChangeListener(ProviderChangeListener l) {
    
                providerChangeSupport.removeProviderChangeListener(l);
    
            }
       
        
        public Item[] getAssets() {
            Item e[] = null;
            e = (Item[])s_jobs.toArray(new Item[s_jobs.size()]);
            return e;
        }
       
      
        public void getAssetList() {
            
            Item j = new Item(); 
            s_jobs.add(j);
            
              
            
        }
        
        
        public void selectItem(){
            System.out.println("Enter into selected List");
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");  
            Item item=(Item)vex.getDataProvider();
            if(item.getSource().equalsIgnoreCase("Contracted")){
              System.out.println("Source of the item ------>"+item.getSource());
                doSelectItem();
            }
            else{
            System.out.println("Enter into else part selected List");
                System.out.println("Source of the item ------>"+item.getSource());   
            ValueExpression isContractedItemPresent = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isContractedItemPresent}", String.class);
            System.out.println("Contract items present------>"+isContractedItemPresent);
            String isContractedAvailable=(String)isContractedItemPresent.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(isContractedAvailable.equalsIgnoreCase("true") && item.getChecked().equalsIgnoreCase("/images/uncheck.png")){
            System.out.println("ALERT----->"+isContractedAvailable);
                System.out.println("Contract items present------>"+isContractedItemPresent);
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                           "contracted_alert",
                                                                           new Object[] { });
            }
            else{
                System.out.println("Enter into inside else part selected List");
                System.out.println("Source of the item ------>"+item.getSource());
                doSelectItem();
            }
            }
        }
        
        
        public void doSelectItem(){
            
            try {
                //CostCenter with Natural Account
                ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
                String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
                String multiOrgId = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression vf3 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_costNaturalAccount}", String.class);
                String default_cost_natural_accout = (String)vf3.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression loc_code = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to_locationCode}", String.class);
                String default_loc_code=(String)loc_code.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression vf_loc_code = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to_locationValue}", String.class);
                vf_loc_code.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
                
                
           
           
                ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
                String default_cost_center_Value = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());

                ValueExpression ve150 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_natural_account}", String.class);
                String default_natural_account_Value = (String)ve150.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression ve_cost = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_centerId}", String.class);
                ve_cost.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
                
                ValueExpression ve_natural = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_natural_accountId}", String.class);
                ve_natural.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
                

                System.out.println("Dafult Cost Center Value-->"+default_cost_natural_accout);

                ValueExpression vf31 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_costNaturalAccountId}", String.class);
                vf31.setValue(AdfmfJavaUtilities.getAdfELContext(), "");

                RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
                
                // GET Cost Center based MultiOrg 
                
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
                     String postData= "{\n" +
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
                            "          \"P_ORG_ID\" : \""+multiOrgId+"\"\n" +
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
                        JSONObject  resp=new JSONObject(response);
                         JSONObject output=resp.getJSONObject("OutputParameters");
                     try{
                             JSONObject  data=output.getJSONObject("X_COST_CENTER_TL");
                             CostCenterList.s_jobs.clear();
                             costCenterList.clear();
                         
                             if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
                                                             JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
                                                             for(int i=0;i<segments.length();i++) {
                                                                 JSONObject ci=(JSONObject)segments.get(i);
                                                                 String name=ci.getString("SEGMENT_VALUE");
                                                                 String description=ci.getString("DESCRIPTION");
                                                                 if(description.equalsIgnoreCase(default_cost_center_Value)) {
                                                                  ve_cost.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
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
                                                               if(description.equalsIgnoreCase(default_cost_center_Value)) {
                                                                ve_cost.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
                                                                System.out.println("Dafult Cost Center Value-->"+default_cost_center_Value);

                                                                }
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
                    JSONObject data=output.getJSONObject("X_NATURAL_ACC_TL");
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
                           ve_natural.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
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
                        if(description.equalsIgnoreCase(default_natural_account_Value)) {
                         ve_natural.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
                          System.out.println("Dafult GL Account Value-->"+default_natural_account_Value);
                         }
                        NaturalAccounts c=new NaturalAccounts(name,description);
                        NaturalAcccountList.acc_List.add(c);
                        naturalAccountList.add(c);
                       
                    }
                     
                    
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                
                         
                                // Natural Accounts
                            
                       /*     restServiceAdapter = Model.createRestServiceAdapter();
                            // Clear any previously set request properties, if any
                            restServiceAdapter.clearRequestProperties();
                            // Set the connection name
                            restServiceAdapter.setConnectionName("enrich");
                            
                            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cc_acct_lov/");
                            String postData= "{\n" + 
                                "  \"GET_CC_ACCT_LOV_Input\" : {\n" + 
                                "   \"RESTHeader\": {\n" + 
                                "    },\n" + 
                                "   \"InputParameters\": {\n" + 
                                "          \"P_USER_ID\" : \""+userId+"\"\n" + 
                                "         ,\"P_ORG_ID\" : \""+multiOrgId+"\"\n" + 
                                "       }          \n" + 
                                "   }\n" + 
                                "}\n";
                                
                               restServiceAdapter.setRetryLimit(0);
                               System.out.println("postData===============================" + postData);
                                
                              String response = restServiceAdapter.send(postData);
                                
                                System.out.println("response===============================" + response); 
                               JSONObject  resp=new JSONObject(response);
                                JSONObject output=resp.getJSONObject("OutputParameters");
                            try{
                                JSONObject data=output.getJSONObject("X_CC_ACCT_TL");
                                NaturalAcccountList.acc_List.clear();
                                naturalAccountList.clear();
                                
                                if(data.get("X_CC_ACCT_TL_ITEM") instanceof  JSONArray){
                                  JSONArray segments=data.getJSONArray("X_CC_ACCT_TL_ITEM");
                                  for(int i=0;i<segments.length();i++) {
                                      JSONObject na=(JSONObject)segments.get(i);
                                      String costCenter=na.getString("COST_CENTER");
                                      String naturalAccount=na.getString("NATURAL_ACCOUNT");
                                      String costCenterDisc=na.getString("COST_CENTER_DESC");
                                      String naturalAccountDisc=na.getString("NATURAL_ACCT_DESC");
                                      String DispValue=na.getString("DISPLAY_VALUE");
                                      System.out.println("Dafult Cost Center Value-->"+default_cost_natural_accout+"---"+DispValue);
                                      if(DispValue.equalsIgnoreCase(default_cost_natural_accout)) {
                                          vf31.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(i));
                                          
                                          System.out.println("Dafult Cost Center Value-->"+default_cost_natural_accout);

                                      }
                                      
                                      CostCenterNaturalAccounts c=new CostCenterNaturalAccounts(costCenter, naturalAccount, costCenterDisc, naturalAccountDisc, DispValue);
                                      CostCenterNaturalAccountsList.CC_NA_List.add(c);
                                      costCenterNaturalAccountsList.add(c);
                                      
                                  }
                                
                                }
                                
                                else if(data.get("X_CC_ACCT_TL_ITEM") instanceof  JSONObject){
                                   
                                   JSONObject na=data.getJSONObject("X_CC_ACCT_TL_ITEM");
                                    String costCenter=na.getString("COST_CENTER");
                                    String naturalAccount=na.getString("NATURAL_ACCOUNT");
                                    String costCenterDisc=na.getString("COST_CENTER_DESC");
                                    String naturalAccountDisc=na.getString("NATURAL_ACCT_DESC");
                                    String DispValue=na.getString("DISPLAY_VALUE");
                                    if(DispValue.equalsIgnoreCase(default_cost_natural_accout)) {
                                        vf31.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(0));
                                    }
                                    
                                    CostCenterNaturalAccounts c=new CostCenterNaturalAccounts(costCenter, naturalAccount, costCenterDisc, naturalAccountDisc, DispValue);
                                    CostCenterNaturalAccountsList.CC_NA_List.add(c);
                                    costCenterNaturalAccountsList.add(c);
                                   
                                }
                                 
                                
                                }
                                catch(Exception e) {
                                    e.printStackTrace();
                                }
                                */
                          
            
            
            //Deliver to Location   
               
           
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
                      if(locationCode.equalsIgnoreCase(default_loc_code)) {
                          vf_loc_code.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(i+1));
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
                        if(locationCode.equalsIgnoreCase(default_loc_code)) {
                            vf_loc_code.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(0));
                        }
                    DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                    DeliverToLocationList.s_jobs.add(loc);
                    deliverToLocationList.add(loc);
                   
                }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            
            catch(Exception e) {
                e.printStackTrace();
            }
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");  
            Item item=(Item)vex.getDataProvider();
            GenericType row= (GenericType)vex.getCurrentRow();
            
            
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(1000000000);
            
           ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_centerId}", String.class);
            String default_cost_center = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());
               
            ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to_locationValue}", String.class);
            String default_deliver_to_location = (String)ve49.getValue(AdfmfJavaUtilities.getAdfELContext()); 
        
            ValueExpression ve150 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_natural_accountId}", String.class);
            String default_natural_account = (String)ve150.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve151 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_costNaturalAccountId}", String.class);
            String default_cost_natural_account = (String)ve151.getValue(AdfmfJavaUtilities.getAdfELContext()); 

       //     String natural=default_natural_account;
        //    System.out.println("Natural-->"+default_natural_account+"---->"+default_cost_natural_account);
            
          /*  NaturalAccounts nAcc;
            if(natural.equalsIgnoreCase("")){
                nAcc=(NaturalAccounts)naturalAccountList.get(0);
            }
            else{
                nAcc=(NaturalAccounts)naturalAccountList.get(Integer.parseInt(natural));
            }
            
            
            
            
            
            System.out.println("Cost center is "+default_cost_center+" Deliver to is "+default_deliver_to_location+"NAtural Accout"+nAcc.getDescription());
           */
            
            System.out.println("Attribute Values"+item.getAttrib());
            System.out.println("Specification Values "+item.getSpec());         
            
            String specList="";
            if(item.getSpec()!=""){
                System.out.println("Specification Values=====> "+item.getSpec()); 
                String spec0 =item.getSpec().substring(0,item.getSpec().length()-2);
                String spec1 = spec0.replaceAll("\"", "\\\\\"");                
                String spec2 = spec1.replaceAll(":", "\",\"ATTRIBUTE_VALUE\" : \"");
                String spec3 = spec2.replaceAll("#,", "\"},{\"ATTRIBUTE_NAME\" : \"");
                String spec ="{\"ATTRIBUTE_NAME\" : \""+spec3+"\"}";
/*         
                {"ATTRIBUTE_NAME" : "soapui_24jun16_name1","ATTRIBUTE_VALUE" : "soapui_24jun16_value2"}
*/
                /*:--","ATTRIBUTE_VALUE" : "
                * | "},{"ATTRIBUTE_NAME" : "*/
                //String spec = item.getSpec().replaceAll("#,", " | ").replaceAll("\"", "\\\\\"");
                
                //specList = spec.substring(0,spec.length()-2);
                specList = spec;
                System.out.println("After replace Spec Values=====> "+specList); 
                
                System.out.println("After Attrib Replace=====> "+specList); 
            }
            else if(item.getAttrib()!="") {
                String spec = item.getAttrib();
                specList = spec.substring(0,spec.length()-2);
                System.out.println("After Attrib=====> "+specList); 
            }
            
            System.out.println("Deliver To Location-->"+default_deliver_to_location+"Cost Center-->"+default_cost_center+"GL Account-->"+default_natural_account);
            
            SelectedItem selectItem=new SelectedItem(item.getPoNo(), item.getVendorName(), item.getVendorSiteCode(), item.getProductCategory(), item.getProductTitle(), item.getUnitPrice(), item.getImageUrl(), "true", item.getSource(), item.getUom(), "1", default_deliver_to_location, "",item.getUnitPrice(),String.valueOf(randomInt),default_cost_center,item.getRowId(),item.getIndixCategoryId(),specList,default_natural_account,default_cost_natural_account,"goods","","","","","","","","","","","","","","");
        
        
            
            
            
            for(int i=0;i<row.getAttributeCount();i++) {
                
                
              
                System.out.println("***"+row.getAttribute(i));
            }
            
            
            if(ItemsList.items_selected==null) {
                ItemsList.items_selected=new ArrayList();
            }
            if(item.getChecked().equalsIgnoreCase("/images/uncheck.png")){
             row.setAttribute("checked", new String("/images/check.png"));
                
               // propertyChangeSupport.firePropertyChange("checked", "images/uncheck.png", "/images/check.png");
               System.out.println("select");
               SelectedItemsList.items_selected.add(selectItem) ;   
            }
             else{
                 row.setAttribute("checked", new String("/images/uncheck.png"));
                 System.out.println("un select");
                for(int k=0;k<SelectedItemsList.items_selected.size();k++) {
                    SelectedItem it=(SelectedItem)SelectedItemsList.items_selected.get(k);
                    System.out.println(it.getItemRef() +"="+ selectItem.getItemRef());
                    System.out.println("Row ID--->"+it.getRowid());
                    //if(it.getRowid().equalsIgnoreCase(selectItem.getItemRef()))
                    if(it.getItemRef().equalsIgnoreCase(selectItem.getItemRef())) {
                        SelectedItemsList.items_selected.remove(it) ; 
                        System.out.println("Removed Item"+SelectedItemsList.items_selected.remove(it));
                    }
                }
                 
             }
             
            
            System.out.print("After =========>"+SelectedItemsList.items_selected.size());
            
            vex.refresh();
        }
        
        
        public void addItemToCart(){
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
            String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(itemType!=null && !itemType.equalsIgnoreCase("")) {
            
                for (int i = 0 ; i<SelectedItemsList.items_selected.size();i++){
                    SelectedItemsList.s_jobs.add(SelectedItemsList.items_selected.get(i)) ;
                }
            int count=SelectedItemsList.s_jobs.size();
            
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
            
            ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
            
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}"); 
            for(int i=0;i<vex.getTotalRowCount();i++)
            {
                vex.setCurrentIndex(i);
                Item item=(Item)vex.getDataProvider();
                item.setChecked("/images/uncheck.png");
                
            }
            vex.refresh();
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            if(SelectedItemsList.items_selected.size()>0){
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                       "displayAlert",
                                                                       new Object[] {}); 
            
            }
            else{
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "No items Chosen.",
                                             null,
                                             null }); 
            }
            
            
            
            
            SelectedItemsList.items_selected.clear();
            
            ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rdGoods}", String.class);
            String rdGoods=(String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rdServices}", String.class);
            String rdServices=(String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            System.out.println("rdGoods ==============> "+rdGoods);
            System.out.println("rdServices ==============> "+rdServices);
            
            
            }
            else{
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Item Type is mandatory and cannot be empty.",
                                             null,
                                             null });    
            }
            
        }
        
        public void showCart(){
            // Add event code here...
            Double cartValue=0.0;
            
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
            String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
            //SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
            int count=SelectedItemsList.s_jobs.size();
            
            String default_cost_center_id="";
            String default_cost_center_name="";
            String default_deliver_to_location_id="";
            String default_deliver_to_location_name="";
            
            String default_natural_account_seg="";
            String default_natural_account_description="";
            
            if(count>0){
            if(itemType!=null && !itemType.equalsIgnoreCase(""))  {
            try{
                
                
                
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String multiOrgId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());

            
//             ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
//             default_cost_center_id = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());
//                
//             ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to}", String.class);
//             default_deliver_to_location_id = (String)ve49.getValue(AdfmfJavaUtilities.getAdfELContext());    
                
                
                
                
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
                 
                String response = restServiceAdapter.send(postData);
                 
                 System.out.println("response===============================" + response); 
                 JSONObject resp=new JSONObject(response);
                 JSONObject output=resp.getJSONObject("OutputParameters");
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
                      if(name.equalsIgnoreCase(default_cost_center_id)) {
                          default_cost_center_name=String.valueOf(i);
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
                    if(name.equalsIgnoreCase(default_cost_center_id)) {
                        default_cost_center_name=String.valueOf(0);
                    }
                    CostCenter c=new CostCenter(name,description);
                    CostCenterList.s_jobs.add(c);
                    costCenterList.add(c);
                   
                }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            


// Natural Accounts
            
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
                      if(name.equalsIgnoreCase(default_natural_account_seg)) {
                          default_natural_account_description=String.valueOf(i);
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
                    if(name.equalsIgnoreCase(default_natural_account_seg)) {
                        default_natural_account_description=String.valueOf(0);
                    }
                    NaturalAccounts c=new NaturalAccounts(name,description);
                    NaturalAcccountList.acc_List.add(c);
                    naturalAccountList.add(c);
                   
                }
                 /*   AmxAttributeBinding accountList = (AmxAttributeBinding) AdfmfJavaUtilities
                                      .evaluateELExpression("#{bindings.naturalAccounts}");
                    AmxIteratorBinding accountListIterator =  accountList.getIteratorBinding();
                    accountListIterator.refresh();*/
                
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

//CostCenter with Natural Account
                
                // Natural Accounts
            
            restServiceAdapter = Model.createRestServiceAdapter();
            // Clear any previously set request properties, if any
            restServiceAdapter.clearRequestProperties();
            // Set the connection name
            restServiceAdapter.setConnectionName("enrich");
            
            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cc_acct_lov/");
                postData= "{\n" + 
                "  \"GET_CC_ACCT_LOV_Input\" : {\n" + 
                "   \"RESTHeader\": {\n" + 
                "    },\n" + 
                "   \"InputParameters\": {\n" + 
                "   	   \"P_USER_ID\" : \""+userId+"\"\n" + 
                "   	  ,\"P_ORG_ID\" : \""+multiOrgId+"\"\n" + 
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
                 data=output.getJSONObject("X_CC_ACCT_TL");
            //    NaturalAcccountList.acc_List.clear();
              //  naturalAccountList.clear();
                CostCenterNaturalAccountsList.CC_NA_List.clear();
                costCenterNaturalAccountsList.clear();
                if(data.get("X_CC_ACCT_TL_ITEM") instanceof  JSONArray){
                  JSONArray segments=data.getJSONArray("X_CC_ACCT_TL_ITEM");
                  for(int i=0;i<segments.length();i++) {
                      JSONObject na=(JSONObject)segments.get(i);
                      String costCenter=na.getString("COST_CENTER");
                      String naturalAccount=na.getString("NATURAL_ACCOUNT");
                      String costCenterDisc=na.getString("COST_CENTER_DESC");
                      String naturalAccountDisc=na.getString("NATURAL_ACCT_DESC");
                      String DispValue=na.getString("DISPLAY_VALUE");
                     /* if(name.equalsIgnoreCase(default_natural_account_seg)) {
                          default_natural_account_description=String.valueOf(i);
                      }*/
                      
                      CostCenterNaturalAccounts c=new CostCenterNaturalAccounts(costCenter, naturalAccount, costCenterDisc, naturalAccountDisc, DispValue);
                      CostCenterNaturalAccountsList.CC_NA_List.add(c);
                      costCenterNaturalAccountsList.add(c);
                      
                  }
                
                }
                
                else if(data.get("X_CC_ACCT_TL_ITEM") instanceof  JSONObject){
                   
                   JSONObject na=data.getJSONObject("X_CC_ACCT_TL_ITEM");
                    String costCenter=na.getString("COST_CENTER");
                    String naturalAccount=na.getString("NATURAL_ACCOUNT");
                    String costCenterDisc=na.getString("COST_CENTER_DESC");
                    String naturalAccountDisc=na.getString("NATURAL_ACCT_DESC");
                    String DispValue=na.getString("DISPLAY_VALUE");
                    /*if(name.equalsIgnoreCase(default_natural_account_seg)) {
                        default_natural_account_description=String.valueOf(0);
                    }*/
                    
                    CostCenterNaturalAccounts c=new CostCenterNaturalAccounts(costCenter, naturalAccount, costCenterDisc, naturalAccountDisc, DispValue);
                    CostCenterNaturalAccountsList.CC_NA_List.add(c);
                    costCenterNaturalAccountsList.add(c);
                   
                }
                  /*  AmxAttributeBinding accountList = (AmxAttributeBinding) AdfmfJavaUtilities
                                      .evaluateELExpression("#{bindings.naturalAccounts}");
                    AmxIteratorBinding accountListIterator =  accountList.getIteratorBinding();
                    accountListIterator.refresh();*/
                
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
                
                
                
            
            
                for(int i=0;i<SelectedItemsList.s_jobs.size();i++)
                 {
                     SelectedItem item=(SelectedItem)SelectedItemsList.s_jobs.get(i);
                     cartValue=cartValue+ Double.parseDouble(item.getAmount());
                     
                 }
                
                ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
                        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(Math.round(cartValue)));        
                        AdfmfJavaUtilities.flushDataChangeEvent();   
                
    //            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
    //            vex.refresh();
                    
                    
            
            
           // return "checkout";
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                                   "adf.mf.api.amx.doNavigation", new Object[] { "checkout" });    
            
            }
            else{
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Item Type is mandatory and cannot be empty.",
                                             null,
                                             null });    
                
            }
            
            }
            else{
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Your cart is empty.",
                                             null,
                                             null });    
               
            }
            
            
        }
       
        
        public void refresh(){
                   ////////////////////
                        
                  /* ValueExpression default_orid = AdfmfJavaUtilities.getValueExpression("#{applicationScope.SpringboardmultiOrgId}", String.class);
                   String default_multi_orid = (String)default_orid.getValue(AdfmfJavaUtilities.getAdfELContext());
                   System.out.println("default"+default_multi_orid);
                   ValueExpression default_orid_page = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedMultiOrg}", String.class);
                   String default_P_multi_orid = (String)default_orid_page.getValue(AdfmfJavaUtilities.getAdfELContext());
                   System.out.println("default pageflow"+default_P_multi_orid);*/
            
                   ValueExpression ve91 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
                   itemType=(String)ve91.getValue(AdfmfJavaUtilities.getAdfELContext());
                   pageNo=1;
                   search="";
                   isFilterSet=false;
                   isSortSet=false;
                   pageSize=0;
                   
                   ValueExpression ve_is_brand_category_selected = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandCategorySelected}", String.class);
                   ve_is_brand_category_selected.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                                                 
                    ValueExpression ve_brand_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandRef}", String.class);
                    ve_brand_ref.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                                                  
                    ValueExpression ve_category_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryRef}", String.class);
                    ve_category_ref.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                    
                    ValueExpression isContractedItemPresent = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isContractedItemPresent}", String.class);
                    isContractedItemPresent.setValue(AdfmfJavaUtilities.getAdfELContext(), "false");
                  
                   ValueExpression ve119 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySortOption}", String.class);
                   ve119.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
    
    
                    ValueExpression ve_is_query_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isQueryRefSet}", String.class);
                    ve_is_query_ref.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");  
                                         
                    ValueExpression ve_query_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.queryRef}", String.class);
                    ve_query_ref.setValue(AdfmfJavaUtilities.getAdfELContext(),"");  
                   
                   if(itemType!=null && !itemType.equalsIgnoreCase("")) {
                   
                   
                   try{
                       
                       
                       
                       ItemsList.s_jobs.clear();
                       ItemsList.items_list.clear();
                      
                       ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
                       search = (String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext()); 
                       
                       ValueExpression ve31 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasIndixItemcategories}", String.class);
                       aliasCategoriesId = (String)ve31.getValue(AdfmfJavaUtilities.getAdfELContext());
                       
                       System.out.println("Category Mapped indix Id's--->"+aliasCategoriesId);
                       
                       ValueExpression veb1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.formWebURL}", String.class);
                       String fromUrl = (String)veb1.getValue(AdfmfJavaUtilities.getAdfELContext());
                       
                       ValueExpression ve32 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
                       String oracleId = (String)ve32.getValue(AdfmfJavaUtilities.getAdfELContext());
                           
                       ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
                       ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), "S");
                       ValueExpression veid = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
                       String userId = (String)veid.getValue(AdfmfJavaUtilities.getAdfELContext());
                       ValueExpression orid = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
                       String multiorgId = (String)orid.getValue(AdfmfJavaUtilities.getAdfELContext());
                       System.out.println("Changable Org Values---->"+multiorgId);
                       
                     
                       /*ValueExpression mOrgid = AdfmfJavaUtilities.getValueExpression("#{applicationScope.multiOrgId}", String.class);
                       String multOrgid = (String)mOrgid.getValue(AdfmfJavaUtilities.getAdfELContext());
                       System.out.println("MultiORG ID Values---->"+multOrgid);*/

                   ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
                   String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
                   
                   ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
                   String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
                       RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
                       String response="";
                       String postData="";
                       JSONObject resp=new JSONObject();
                       JSONObject output=new JSONObject();
                       int rowCount=0;
                       size=0;
                       try{
                   
                   
                   // Clear any previously set request properties, if any
                   restServiceAdapter.clearRequestProperties();
                   // Set the connection name
                   restServiceAdapter.setConnectionName("enrich");
                   
                   restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                   restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                   restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                   restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                   restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/search_products/");
    
                       
                                postData= "{\n" + 
                               "  \"SEARCH_PRODUCTS_Input\" : {\n" + 
                               "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/search_products/\",\n" + 
                               "   \"RESTHeader\": {\n" + 
                               "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                               "    },\n" + 
                               "   \"InputParameters\": {\n" + 
                               "        \"P_SEARCH_ATTRIBS\": \n" + 
                               "            {\n" + 
                               "             \"SEARCH_TEXT\": \""+search+"\",\n" + 
                               "             \"CATEGORY\": \""+oracleId+"\",\n" +  
                               "             \"USER_ID\": \""+userId+"\",\n" +              
                               "             \"ITEM_TYPE\": \""+itemType+"\",\n" +
                               "             \"ORG_ID\": \""+multiorgId+"\"\n" +           
                               "            }\n" + 
                               "      }\n" + 
                               "   }\n" + 
                               "}\n";
                            
                       
                       
                                               restServiceAdapter.setRetryLimit(0);
                      System.out.println("postData===============================" + postData);
                       
                      response = restServiceAdapter.send(postData);
                       
                       System.out.println("response===============================" + response);
                      resp=new JSONObject(response);
                           
                      output=resp.getJSONObject("OutputParameters");
                       if(!output.getString("X_ERROR_MESSAGE").equalsIgnoreCase("No products found")){
                           rowCount=1;
                      JSONObject results=output.getJSONObject("X_SEARCH_RESULT_TBL");
                      
                      if(results.get("X_SEARCH_RESULT_TBL_ITEM") instanceof JSONArray){
                      
                      JSONArray body=results.getJSONArray("X_SEARCH_RESULT_TBL_ITEM");
                      for(int i=0;i<body.length();i++) {
                           JSONObject item=body.getJSONObject(i);   
                          String poNo=item.getString("PO_NUMBER");
                           String vendorName=item.getString("VENDOR_NAME");
                           String vendorSiteCode=item.getString("VENDOR_SITE_CODE");
                           String productCategory=item.getString("PRODUCT_CATEGORY");
                           String productTitle=item.getString("PRODUCT_TITLE");
                           String unitPrice=item.getString("UNIT_PRICE");
                           String uom=item.getString("UOM");
                           String source=item.getString("CONTRACTED");
                          //diverseSupplier
                           String diverseSupplier=item.getString("DIVERSE_SUPPLIER");
                          //for test
                          if(diverseSupplier.equalsIgnoreCase("0")) {
                              diverseSupplier="-1";
                          }
                          
                           String imageURL=item.getString("IMAGE_URL");
                          
                           if (imageURL.contains("{")) {
                               imageURL = "";
                           }
                          
                          
                           Random randomGenerator = new Random();
                           int randomInt = randomGenerator.nextInt(1000000000);
                          
                          String showDiverSeImage="true";
                          String diverseImageURL="/images/contract.png";
                          if(diverseSupplier.equalsIgnoreCase("1")) {
                              diverseImageURL="/images/diverse_contract.png";
                          }
                          
                           isContractedItemPresent = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isContractedItemPresent}", String.class);
                           isContractedItemPresent.setValue(AdfmfJavaUtilities.getAdfELContext(), "true");
                          
                           Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageURL,"/images/uncheck.png","Contracted",uom,String.valueOf(randomInt),"1",showDiverSeImage,diverseImageURL,pageNo,"","","","","","","");
                          // ItemsList.s_jobs.add(j); 
                           ItemsList.items_list.add(j); 
                          
                       }
                      }
                      else if(results.get("X_SEARCH_RESULT_TBL_ITEM") instanceof JSONObject){
                          
                          JSONObject item=results.getJSONObject("X_SEARCH_RESULT_TBL_ITEM");   
                          String poNo=item.getString("PO_NUMBER");
                          String vendorName=item.getString("VENDOR_NAME");
                          String vendorSiteCode=item.getString("VENDOR_SITE_CODE");
                          String productCategory=item.getString("PRODUCT_CATEGORY");
                          String productTitle=item.getString("PRODUCT_TITLE");
                          String unitPrice=item.getString("UNIT_PRICE");
                          String uom=item.getString("UOM");
                          String source=item.getString("CONTRACTED");
                          String diverseSupplier=item.getString("DIVERSE_SUPPLIER");
                          //for test
                          if(diverseSupplier.equalsIgnoreCase("0")) {
                              diverseSupplier="-1";
                          }
                          String imageURL=item.getString("IMAGE_URL");
                          Random randomGenerator = new Random();
                          int randomInt = randomGenerator.nextInt(1000000000);
                          String showDiverSeImage="true";
                          String diverseImageURL="/images/contract.png";
                          if(diverseSupplier.equalsIgnoreCase("1")) {
                              diverseImageURL="/images/diverse_contract.png";
                          }
                          if (imageURL.contains("{")) {
                              imageURL = "";
                          }
                          isContractedItemPresent = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isContractedItemPresent}", String.class);
                          isContractedItemPresent.setValue(AdfmfJavaUtilities.getAdfELContext(), "true");
                          Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageURL,"/images/uncheck.png","Contracted",uom,String.valueOf(randomInt),diverseSupplier,showDiverSeImage,diverseImageURL,pageNo,"","","","","","","");
                          //ItemsList.s_jobs.add(j);
                          System.out.println("diverseSupplier----"+diverseSupplier);
                          ItemsList.items_list.add(j); 
                          size=ItemsList.items_list.size();
                      }
                      
                      
                       
                       providerChangeSupport.fireProviderRefresh("assets");
                       }
                       
                       }
                       catch(Exception e){
                           e.printStackTrace();
                           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                        AdfmfJavaUtilities.getFeatureName(),
                                                        "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                        "Cannot connect to Services on Oracle Server.",
                                                        null,
                                                        null }); 
                       }
                       
                   
                      System.out.println("Oracle item Values--->"+ItemsList.items_list.size());
                      size=ItemsList.items_list.size();
                      System.out.println("Oracle item Values size--->"+size);
                      
                      int indixcount=0;
                      
                       
                       //since indix doesn't have services search we can restrict the search 
                       if(itemType.equalsIgnoreCase("goods")){
                       
                       try{
                           

                           //https://api.indix.com/v2/universal/products?countryCode=US&q=bearings&categoryId=23059&availability=IN_STOCK&lastRecordedIn=30&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc
                           String url="";
                           
                           if(!aliasCategoriesId.equals("") && !aliasCategoriesId.contains("null"))
                           {
                               url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+aliasCategoriesId+"&availability=IN_STOCK&lastRecordedIn=30&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                      //         url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+aliasCategoriesId+"&availability=IN_STOCK&lastRecordedIn=30&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                           }
                          else if(!fromUrl.equalsIgnoreCase("")) {
                               System.out.println("Enter into WebURL");
                               url = "https://api.indix.com/v2/universal/products?countryCode=US"+fromUrl+"&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                               System.out.println("Enter into WebURL"+ url);
                           }
                           
                  else {
                               url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+"&availability=IN_STOCK&lastRecordedIn=30&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                           }
                                  
                                                  
                                                  URL obj = new URL(url);
                                                  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                       
                                                  // optional default is GET
                                                  con.setRequestMethod("GET");
                       
                                                  //add request header
                                                 
                       System.out.println("-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*---*-*-*-*");
                           System.out.println(url);
                           System.out.println("-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*---*-*-*-*");
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
                                       
                                                  resp=new JSONObject(response1.toString());
                                                  output=resp.getJSONObject("result");
                                                  JSONArray resArr=output.getJSONArray("products");
                                                  String result_size=output.getString("count"); 
                                                /* if(Integer.parseInt(result_size)>500) {
                                                      size=ItemsList.items_list.size()+500;
                                                  }
                                                  else{
                                                      size=ItemsList.items_list.size()+Integer.parseInt(result_size);
                                                  }*/
                                                  
                                                  indixcount=Integer.parseInt(result_size);
                          // System.out.println("resArr.length() "+resArr.length());
                                                  for(int i=0;i<resArr.length();i++) {
                                                      rowCount=1;
                                                  //    System.out.println("***********");
                                                      JSONObject productObj=resArr.getJSONObject(i);
                                                 //     System.out.println(productObj.toString());
                                                   //   System.out.println("***********");
                                                      String poNo="";
                                                       String vendorName="";
                                                       String vendorSiteCode="";
                                                       String productCategory=productObj.getString("categoryName");
                                                       
//                                                       String unitPrice=productObj.getString("minSalePrice");
                                                      
//                                                       String imageUrl=productObj.getString("imageUrl");
                                                       JSONObject stores=productObj.getJSONObject("stores");
                                                      String indixCategoryId=productObj.getString("categoryId");
                                                      
                                                      Iterator<?> keys = stores.keys();
    
                                                      while( keys.hasNext() ) {
                                                          String key = (String)keys.next();
                                                          JSONObject store=stores.getJSONObject(key);

                                                      vendorName=store.getString("storeName");
                                                       String productTitle=store.getString("title");
                                                       Random randomGenerator = new Random();
                                                          
                                                           
                                                      
                                                       String showDiverSeImage="false";
                                                       String diverseImageURL="";
                                                           //*-*-*-//
                                                      JSONArray Offer=store.getJSONArray("offers");
                                                           System.out.println("======---"+vendorName+"--======-----> "+store+"========> "+Offer.length());
                                                           for(int k=0;k<Offer.length();k++) {
                                                               rowCount=1;
                                                               JSONObject offer=Offer.getJSONObject(k);
                                                               String imageUrl=offer.getString("imageUrl");
                                                               String seller=offer.getString("seller");
                                                               String unitPrice=offer.getString("salePrice");
                                                               System.out.println("*-*-*-Image Url is "+imageUrl+"*-*-*-Seller Is"+seller+"*-*-*-*-Seller Price"+unitPrice);
                                                               String showSeller="true";
                                                               if (seller.equalsIgnoreCase("")) {
                                                                                       showSeller="false";
                                                                                   }
                                                               JSONObject attValues=offer.getJSONObject("attributes");
//                                                                   System.out.println("JSON SPECS VALUES---------------->"+attValues);
                                                                ItemsList.Specification.clear();
                                                               String showAttrib="true";
                                                               String showSpec="true";
                                                               String resultVal = "";  
                                                               String spec = "";
                                                               System.out.println(offer+"<======UP=======>"+attValues.length());
                                                               
                                                               
                                                               if (attValues.length() == 0) {
                                                                   System.out.println("UP length 0");
                                                                   showAttrib = "false";
                                                                   showSpec="false";
                                                                   Specification.clear();
                                                                   BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                                   vex.refresh();
                                                               }else{
                                                                  
                                                                  Iterator<?> att = attValues.keys(); 
                                                                  if(attValues.length() == 1){
                                                                      StringBuffer sb2 = new StringBuffer();
                                                                      System.out.println("UP length 1");
                                                                      showAttrib = "true";
                                                                      showSpec="false";
                                                                      while(att.hasNext()) {
                                                                          String attributeKey = (String)att.next();
//                                                                          System.out.println("JSON Key Single---------------->"+attributeKey);
                                                                          JSONArray attributeValues = attValues.getJSONArray(attributeKey);
//                                                                          System.out.println("JSON Value Single ---------------->"+attributeValues);
                                                                          resultVal =attributeKey+" : "+attributeValues.getString(0);
                                                                          ItemsList.Specification.add(resultVal+"#");
                                                                          sb2.append(resultVal+"#,");
//                                                                          System.out.println("KEY&Value---------------->"+resultVal);
//                                                                          System.out.println("Single Attributes---------------->"+Specification);

                                                                      }
                                                                      BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                                      vex.refresh();
                                                                      //spec = Specification.toString();
                                                                      spec = sb2.toString();
                                                                      //Specification.clear();
                                                                  }else{
                                                                      System.out.println("UP length >1");
                                                                      showAttrib = "false";
                                                                      showSpec="true";
                                                                      StringBuffer sb2 = new StringBuffer();
                                                                      while(att.hasNext()) {
                                                                          String attributeKey = (String)att.next();
                                                                          JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                                                                          resultVal =  attributeKey+" : "+attributeValues.getString(0);
                                                                          ItemsList.Specification.add(resultVal+"#");    
                                                                          sb2.append(resultVal+"#,");
                                                                         /* for(int a=0; a < attributeValues.length(); a++) {
                                                                              resultVal =attributeKey+" : "+attributeValues.getString(a);
                                                                              ItemsList.Specification.add(resultVal);    
//                                                                              System.out.println("MultiAttribute Json<------------->"+attributeKey+"  :  "+resultVal);
                                                                          }*/
                                                                          
                                                                          
//                                                                          System.out.println("Multi-Attribute LIST <------------->"+spec);

                                                                      }
                                                                      BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                                      vex.refresh();
                                                                      //spec = Specification.toString();
                                                                      spec = sb2.toString();
                                                                      //Specification.clear(); 
                                                                  }  
                                                                   
                                                                   
                                                                   
                                                               }
                                                                   
                                                                 // String spec1 = Specification.toString();
                                                               //System.out.println("*-*-*-*-*-*-*-Spec/-/-*-*/"+spec1);

//                                                               /* Attribute*/
//                                                               String attrib=offer.getString("attributes");
//                                                               System.out.println("+-+-+-+-+- Attribute"+attrib);
//                                                               String showAttrib="true";
//                                                               if (attrib.equals("{}")) {
//                                                                                       showAttrib="false";
//                                                                                   }
//                                                               
//                                                               String spec=offer.getString("attributes");
//                                                               System.out.println("+-+-+-+-+- Specifications"+spec);
//
//                                                               String showSpec="true";
//                                                               if (spec.equals("{}")) {
//                                                                                       showSpec="false";
//                                                                                   }
//                                                               
//                                                               JSONObject attValues = new JSONObject(attrib);
//                                                               if(attValues.length()==1) {
//                                                                   Iterator<?> att = attValues.keys();
//                                                                   showSpec="false";
//                                                                   while(att.hasNext() ) {
//                                                                       String attribKey = (String)att.next();
//                                                                       System.out.println("**>ATTRIBUTE KEY<**"+attribKey);
//        
//                                                                       if ( attValues.get(attribKey) instanceof JSONObject ) {
//                                                                           JSONObject getAtrib = new JSONObject(attValues.get(attribKey).toString());
//                                                                           System.out.println("*************>ATTRIBUTE VALUE<***********"+getAtrib);
//                                                                       }
//                                                                   }
//                                                               }
//                                                               else{
//                                                                   showAttrib="false";
//                                                                   System.out.println("*************>Length<***********"+attValues.length());
//                                                                   
//                                                               }
//                                                               
//                                                              
//                                                               System.out.println("**>OBJECTValues<**"+attrib);
//
//                                                               
//
//                                                               /* Specification*/
////                                                               else{
////                                                                   JSONObject specValues = new JSONObject(spec);
////                                                                   if(specValues.length()>1){
////                                                                   
////                                                                   Iterator<?> spc = specValues.keys();
////                                                                   
////                                                                   while(spc.hasNext() ) {
////                                                                       String specKey = (String)spc.next();
////                                                                       if ( specValues.get(specKey) instanceof JSONObject ) {
////                                                                           JSONObject sValue = new JSONObject(specValues.get(specKey).toString());
////                                                                           System.out.println("*************>SPECIFICATION<***********"+sValue);
////                                                                       }
////                                                                   }
////                                                                   }
////                                                               }


                                                                     int randomInt = randomGenerator.nextInt(1000000000);
                                                                   //since for the indix suppliers they will not be diversed so update as -1
                                                                   System.out.println("UP spec before insert ===> "+spec);
                                                                     Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo,indixCategoryId,seller,showSeller,resultVal,showAttrib,spec,showSpec);
                                                                     //ItemsList.s_jobs.add(j); 
                                                                   
                                                                   System.out.println("=================================");
                                                                   System.out.println(k+"====> "+items_list);
                                                                   System.out.println("=================================");
                                                                   
                                                                     ItemsList.items_list.add(j);    
                                                           }    
                                                          
                                                          //*-*-*-//
                                                          
                                                           
                                                           
                                                       }
                                                      
                                                    
                                                   //   System.out.println("***********");
                                                  }
                           
                           
                           System.out.println("Oracle item Values--->"+ItemsList.items_list.size()); 
                           System.out.println("Total Item in Orace + indix---->"+size+"  "+indixcount);
                           
                          /* if(Integer.parseInt(result_size)<20) {
                               size=ItemsList.items_list.size();
                           }*/
                           
                       
                       }
                       catch(Exception e){
                           e.printStackTrace();
                       }
                       
                       
                       
                       
                       
                       
                       
                       }
                       /*else{
                           size=ItemsList.items_list.size();
                       }*/
                       
                       
                       
                       System.out.println("Oracle item Values--->"+ItemsList.items_list.size()); 
                       System.out.println("Total Item in Orace + indix---->"+size+"  "+indixcount);
                       
                       
                       if(indixcount>500) {
                           size=ItemsList.items_list.size()+500;
                       }
                       
                       else if(indixcount < ItemsList.items_list.size()) {
                           size=ItemsList.items_list.size();
                       }
                       
                      /* else if(!fromUrl.equalsIgnoreCase("")) {
                           size=ItemsList.items_list.size();
                       }
                       */
                       else {
                           size=indixcount;
                       }
                       
                       
                       if(ItemsList.items_list.size()>0) {
                           rowCount=1;
                       }
                       
                         
                       
                            populateItemsBasedOnPageNo();
                       
                       
                           pageSize=ItemsList.s_jobs.size();
                           
                           
                       
                           System.out.println("outside "+ItemsList.s_jobs.size());
                       
                           
                          ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
                           ve5.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                           
                          ValueExpression ve55 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySortOption}", String.class);
                          ve55.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                           
                           ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
                           ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                           
                           ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
                          // ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+"/"+size+" results");
                       if(size>500){
                           ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),"More than 500 results found");
                       }
                       else{
                           ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),size+" results found");
                       }
                       
                           ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
                           ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       
                           ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
                           ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       
                           
                     
                   //                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                   //                                                                                  "adf.mf.api.amx.doNavigation", new Object[] { "refresh" });
                       
                             providerChangeSupport.fireProviderRefresh("assets");
                       
                       
                             
                       
                      BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
                      vex.refresh();
                       
                       
                       
                       if(rowCount>0)
                           {
                               ValueExpression veAdd = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
                                veAdd.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                               ValueExpression veAdd1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySortOption}", String.class);
                                veAdd1.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                               
                               ValueExpression vedply = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
                               vedply.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                           }
                     
                      // if(rowCount==0 && !search.trim().equalsIgnoreCase(""))
                       if(rowCount==0){
                           
                           System.out.println("RowCOUNT inside If Loddp===Welcome"+rowCount);
                           System.out.println("Search String========Welcome "+search);
                          // String error=output.getString("X_ERROR_MESSAGE");
                   //                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                   //                                                         AdfmfJavaUtilities.getFeatureName(),
                   //                                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                   //                                                        "Oops!! No products found,Try a Refined Search",
                   //                                                         null,
                   //                                                         null });
                   //                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                   //                                                                               "adf.mf.api.amx.doNavigation", new Object[] { "refined_search" });
                           
                           
                           
                               if(itemType.equalsIgnoreCase("goods")){
                           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                      "confirm_refined_search",
                                                                                      new Object[] { });             
                               }
                               else{
//                                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                                              "confirm_refined_search_service",
//                                                                                              new Object[] { });      
                                    
                                    UOMList.populateUOM();
                                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                                                          "adf.mf.api.amx.doNavigation", new Object[] { "refined_search_services" });
                               }
                           
                       }
                       
                   //            AmxAttributeBinding customerList = (AmxAttributeBinding) AdfmfJavaUtilities
                   //                                           .evaluateELExpression("#{bindings.assets3}");
                   //
                   //             StringBuffer selectedCustomerNames = new StringBuffer();
                   //
                   //             //access the list iterator to first set the current row by the indexes obtained
                   //             //from the value change event and then read the row object, which in this sample
                   //             //represents a customer
                   //             AmxIteratorBinding amxListIterator =  customerList.getIteratorBinding();
                   //
                   //             //the basic iterator in the AmxIteratorBinding is what we need to work with
                   //              BasicIterator      basicIterator = amxListIterator.getIterator();
                   //
                   //                  Item item = (Item) basicIterator.getDataProvider();
                   //
                   //            System.out.println("item===============================" + item.getProductTitle());
                   //
                       
                       
                   }
                   catch(Exception e) {
                       e.printStackTrace();
                   }
                   
                   }
                   else{
                       AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                AdfmfJavaUtilities.getFeatureName(),
                                                                "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                "Item Type is mandatory and cannot be empty.",
                                                                null,
                                                                null });  
                   }
                   ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedSortBy}", String.class);
                   ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"relevance");
            sortItems();
                   //////////////
                   providerChangeSupport.fireProviderRefresh("assets");
               }
        
       
        
     
     
     
     
     
        public void populateItemsBasedOnPageNo(){
            ItemsList.s_jobs.clear();
            for(int k=0;k<ItemsList.items_list.size();k++) {
                Item it=(Item)ItemsList.items_list.get(k);
                String checked="/images/uncheck.png";
                if(it.getPageNo()==pageNo) {
                    for(int z=0;z<SelectedItemsList.items_selected.size();z++) {
                        SelectedItem se=(SelectedItem)SelectedItemsList.items_selected.get(z);
                        if(se.getItemRef().equalsIgnoreCase(it.getRowId())) {
                            checked="/images/check.png";
                        }
                    }
                    Item item=new Item(it.getPoNo(), it.getVendorName(), it.getVendorSiteCode(), it.getProductCategory(), it.getProductTitle(), it.getUnitPrice(), it.getImageUrl(), checked, it.getSource(), it.getUom(), it.getRowId(), it.getDiverseSupplier(), it.getShowDiverseImage(), it.getDiverseImageURL(), pageNo,it.getIndixCategoryId(),it.getSellerName(),it.getShowSeller(),it.getAttrib(),it.getShowAttrib(),it.getSpec(),it.getShowSpec());
                    ItemsList.s_jobs.add(item);
                }
            }
            
            
            
           
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
            vex.setCurrentIndex(ItemsList.s_jobs.size());
            vex.refresh();
            
            
        }
        
        public void getSpecDetails(){
            try{
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.productTitle}", String.class);
                String productTitle = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemRowId}", String.class);
                String rowId = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@---> "+rowId);
                
                
                    //SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
                    int count=SelectedItemsList.s_jobs.size();
                    ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
                    ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
                    AdfmfJavaUtilities.flushDataChangeEvent();
                
                
                
                for(int j=0;j<ItemsList.s_jobs.size();j++) {
                    Item i=(Item)ItemsList.s_jobs.get(j);    
                    if(i.getRowId().equalsIgnoreCase(rowId)) {
                        StringBuffer sb = new StringBuffer();
//                        String replaceSpec = i.getSpec().toString().replace("[", " ").replace("]", "");
//                        String specArray[] = replaceSpec.split("#,");
                        String replaceSpec = i.getSpec().toString().replace("[", " ").replace("]", "");
                        String specArray[] = i.getSpec().split("#,");
                        for(int p=0;p< specArray.length;p++){
                            
                            sb.append(specArray[p]+"\n\n");
                        }
                        System.out.println(sb.length()+"=--111=-=-=-=> "+sb.toString());
                        String finalValue = sb.substring(0, sb.length() - 2);
                        System.out.println(finalValue.length()+"=--=-=-=-=> "+finalValue);
                        
                        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.specDetails}", String.class);
                        ve2.setValue(AdfmfJavaUtilities.getAdfELContext(),finalValue);
                        AdfmfJavaUtilities.flushDataChangeEvent();
                        
                        
                        
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!---> "+ve2);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();    
            }
        }
        
        public void getNextPageItems(ActionEvent actionEvent) {
            // Add event code here...
           
            pageNo= pageNo+1;
            
            System.out.println("pageNo is "+pageNo);
            
            //check if pageno is already in cached list
            boolean isPageLoaded=false;
            for(int k=0;k<ItemsList.items_list.size();k++) {
                Item i=(Item)ItemsList.items_list.get(k);
                if(i.getPageNo()==pageNo) {
                    isPageLoaded=true;
                }
            }
                
            
             
             if(itemType.equalsIgnoreCase("goods")){
                     ValueExpression ve31 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasIndixItemcategories}", String.class);
                     aliasCategoriesId = (String)ve31.getValue(AdfmfJavaUtilities.getAdfELContext());
             
             try{
             
                 
                 if(!isPageLoaded){
                        // System.out.println("response===============================" + response);
                        String url="";
                        if(!isFilterSet){
                          
                            ValueExpression ve_is_brand_category_selected = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandCategorySelected}", String.class);   
                            String isBrandCategorySelected=(String)ve_is_brand_category_selected.getValue(AdfmfJavaUtilities.getAdfELContext());
                            if(isBrandCategorySelected.equalsIgnoreCase("true")) {
                               
                              ValueExpression ve_brand_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandRef}", String.class);
                              String brand_ref=(String)ve_brand_ref.getValue(AdfmfJavaUtilities.getAdfELContext());
                                                                        
                               ValueExpression ve_category_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryRef}", String.class);
                              String veCategoryRef=(String) ve_category_ref.getValue(AdfmfJavaUtilities.getAdfELContext());
                              
                               
                                
                                url = "https://api.indix.com/v2/universal/products?countryCode=US&brandId="+brand_ref+veCategoryRef+"&availability=IN_STOCK&lastRecordedIn=30&pageNumber="+pageNo+"&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc"; 
                                }
                                
                             
                          /*  else{
                                System.out.println("Category ID-->"+aliasCategoriesId+"Category Ref--->"+categoryRef);
                             url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+"&"+categoryRef+"availability=IN_STOCK&lastRecordedIn=30&pageNumber="+pageNo+"&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                            }*/
                                
                            else if(!aliasCategoriesId.equals(""))
                            {
                                url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+aliasCategoriesId+"&availability=IN_STOCK&lastRecordedIn=30&pageNumber="+pageNo+"&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                            //         url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+aliasCategoriesId+"&availability=IN_STOCK&lastRecordedIn=30&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                            }
                            else {
                                url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+"&availability=IN_STOCK&lastRecordedIn=30&pageNumber="+pageNo+"&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                            }
                              
                            
                        }
                        if(isFilterSet){
                            
                            ValueExpression ve_is_brand_category_selected = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandCategorySelected}", String.class);   
                            String isBrandCategorySelected=(String)ve_is_brand_category_selected.getValue(AdfmfJavaUtilities.getAdfELContext());
                            if(isBrandCategorySelected.equalsIgnoreCase("true")) {
                               
                              ValueExpression ve_brand_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brandRef}", String.class);
                              String brand_ref=(String)ve_brand_ref.getValue(AdfmfJavaUtilities.getAdfELContext());
                                                                        
                               ValueExpression ve_category_ref = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryRef}", String.class);
                              String veCategoryRef=(String) ve_category_ref.getValue(AdfmfJavaUtilities.getAdfELContext());
                                
                               
                              url = "https://api.indix.com/v2/universal/products?countryCode=US&brandId="+brand_ref+veCategoryRef+"&availability=IN_STOCK&lastRecordedIn=30&pageNumber="+pageNo+"&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc"; 
                            }
                            
                            else{
                                System.out.println("Category ID-->"+aliasCategoriesId+"Category Ref--->"+categoryRef);
                               url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+"&"+categoryRef+"availability=IN_STOCK&lastRecordedIn=30&pageNumber="+pageNo+"&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                            }
                        }
                                        
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
                                        JSONObject output=resp.getJSONObject("result");
                                        JSONArray resArr=output.getJSONArray("products");
                // System.out.println("resArr.length() "+resArr.length());
                                        for(int i=0;i<resArr.length();i++) {
                                           int rowCount=1;
                                        //    System.out.println("***********");
                                            JSONObject productObj=resArr.getJSONObject(i);
                                       //     System.out.println(productObj.toString());
                                         //   System.out.println("***********");
                                            String poNo="";
                                             String vendorName="";
                                             String vendorSiteCode="";
                                             String productCategory=productObj.getString("categoryName");
                                             
//                                             String unitPrice=productObj.getString("minSalePrice");
//                                             String imageUrl=productObj.getString("imageUrl");
                                             JSONObject stores=productObj.getJSONObject("stores");
                                            String indixCategoryId=productObj.getString("categoryId");
                                            
                                            Iterator<?> keys = stores.keys();
    
                                            while( keys.hasNext() ) {
                                                String key = (String)keys.next();
                                                JSONObject store=stores.getJSONObject(key);
                                                vendorName=store.getString("storeName");
                                                 String productTitle=store.getString("title");
                                                 Random randomGenerator = new Random();
                                                 int randomInt = randomGenerator.nextInt(1000000000);
                                                //since for the indix suppliers they will not be diversed so update as -1
                                                String showDiverSeImage="false";
                                                String diverseImageURL="";
                                      
                                                 //*-*-*-//
                                                 JSONArray Offer=store.getJSONArray("offers");
                                                 for(int k=0;k<Offer.length();k++) {
                                                     rowCount=1;
                                                     JSONObject offer=Offer.getJSONObject(k);
                                                     String imageUrl=offer.getString("imageUrl");
                                                     String seller=offer.getString("seller");
                                                 String unitPrice=offer.getString("salePrice");
                                                 System.out.println("*-*-*-Image Url is "+imageUrl+"*-*-*-Seller Is"+seller+"*-*-*-*-Seller Price"+unitPrice);
                                                 String showSeller="true";
                                                 if (seller.equalsIgnoreCase("")) {
                                                                         showSeller="false";
                                                                     }
                                                     
                                                 JSONObject attValues=offer.getJSONObject("attributes");
                                                 //                                                                   System.out.println("JSON SPECS VALUES---------------->"+attValues);
                                                  ItemsList.Specification.clear();
                                                 String showAttrib="true";
                                                 String showSpec="true";
                                                 String resultVal = "";  
                                                 String spec = "";
                                                     System.out.println(offer+"======>num===>"+attValues.length());
                                                 if (attValues.length() == 0) {
                                                     System.out.println("Length 0");
                                                     showAttrib = "false";
                                                     showSpec="false";
                                                     Specification.clear();
                                                     BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                     vex.refresh();
                                                 }else{
                                                    
                                                    Iterator<?> att = attValues.keys(); 
                                                    if(attValues.length() == 1){
                                                        StringBuffer sb2 = new StringBuffer();
                                                        System.out.println("Length 1");
                                                        showAttrib = "true";
                                                        showSpec="false";
                                                        while(att.hasNext()) {
                                                            String attributeKey = (String)att.next();
                                                 //                                                                          System.out.println("JSON Key Single---------------->"+attributeKey);
                                                            JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                                                 //                                                                          System.out.println("JSON Value Single ---------------->"+attributeValues);
                                                            resultVal =attributeKey+" : "+attributeValues.getString(0);
                                                            
                                                            System.out.println("");
                                                            ItemsList.Specification.add(resultVal+"#");
                                                 //                                                                          System.out.println("KEY&Value---------------->"+resultVal);
                                                 //                                                                          System.out.println("Single Attributes---------------->"+Specification);
                                                            sb2.append(resultVal+"#,");
                                                        }
                                                        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                        vex.refresh();
                                                        //spec = Specification.toString();
                                                        spec = sb2.toString();
                                                        System.out.println("Value at 1===> "+spec);
                                                        Specification.clear();
                                                    }else{
                                                        System.out.println("Length >1");
                                                        StringBuffer sb2 = new StringBuffer();
                                                        showAttrib = "false";
                                                        showSpec="true";
                                                        while(att.hasNext()) {
                                                            String attributeKey = (String)att.next();
                                                            JSONArray attributeValues = attValues.getJSONArray(attributeKey);
                                                            resultVal =attributeKey+" : "+attributeValues.getString(0);
                                                            ItemsList.Specification.add(resultVal+"#");
                                                            /*
                                                            for(int a=0; a < attributeValues.length(); a++) {
                                                                resultVal =attributeKey+" : "+attributeValues.getString(a);
                                                                ItemsList.Specification.add(resultVal);    
                                                 //                                                                              System.out.println("MultiAttribute Json<------------->"+attributeKey+"  :  "+resultVal);
                                                            }
                                                            */
                                                          sb2.append(resultVal+"#,");
                                                            
                                                 //                                                                          System.out.println("Multi-Attribute LIST <------------->"+spec);
                                                 BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");
                                                 vex.refresh();
                                                        }
                                                        //spec = Specification.toString();
                                                        spec = sb2.toString();
                                                        System.out.println("Value at >1===> "+spec);
                                                        Specification.clear();
                                                        
                                                    }  
                                                 }
                                                     
                                                    //String spec1 = Specification.toString();
                                                     
//                                                 String specification = offer.getString("attributes");
//
//                                                 
//                                                 if (specification.equals("{}")) {
//                                                     showSpec="false";
//                                                 }
                                                     
                                                     
                                                     
                                                     
                                                     
                                                 /* Attribute*/
                                                     /*
                                                 String attrib=offer.getString("attributes");

                                                 String showAttrib="true";
                                                 if (attrib.equals("{}")) {
                                                                         showAttrib="false";
                                                                     }

                                                 
                                                 String spec=offer.getString("attributes");

                                                 String showSpec="true";
                                                 if (spec.equals("{}")) {
                                                                         showSpec="false";
                                                                     }
                                                 JSONObject attValues = new JSONObject(attrib);
                                                 JSONObject specValues = new JSONObject(spec);
                                                 if(attValues.length()==1) {
                                                     Iterator<?> att = attValues.keys();
                                                     showSpec="false";
                                                     while(att.hasNext() ) {
                                                         String attribKey = (String)att.next();
                                                         System.out.println("**>ATTRIBUTE KEY<**"+attribKey);
                                                 
                                                         if ( attValues.get(attribKey) instanceof JSONObject ) {
                                                             JSONObject getAtrib = new JSONObject(attValues.get(attribKey).toString());
                                                             System.out.println("*************>ATTRIBUTE VALUE<***********"+getAtrib);
                                                         }
                                                     }
                                                 }
                                                 else{
                                                     showAttrib="false";
                                                     if(specValues.length()>1) {
                                                     Iterator<?> spc = specValues.keys();
                                                     while(spc.hasNext() ) {
                                                         String specKey = (String)spc.next();
                                                         System.out.println("**>ATTRIBUTE KEY<**"+specKey);
                                                     
                                                         if ( specValues.get(specKey) instanceof JSONObject ) {
                                                             JSONObject getSpec = new JSONObject(specValues.get(specKey).toString());
                                                             System.out.println("*************>SPECIFICATION ATT VALUE<***********"+getSpec);
                                                         }
                                                     }
                                                     }

                                                     System.out.println("*************>Length<***********"+attValues.length());
                                                     
                                                 }
                                                 
                                                 
                                                 System.out.println("**>OBJECTValues<**"+attrib);
                                                     
                                                     
                                                  */   
                                                     
                                                     
                                                     
                                                     
                                                     
  
                                                     //since for the indix suppliers they will not be diversed so update as -1
                                                     System.out.println("Spec befor add===> "+spec);
                                                    Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo,indixCategoryId,seller,showSeller,resultVal,showAttrib,spec,showSpec);
                                                     providerChangeSupport.fireProviderCreate("assets", String.valueOf(randomInt), j);
                                                  ItemsList.items_list.add(j); 
                                                
                                                  
                                              
                                             }
                                            
                                            
                                            }                       
                                            
                                            System.out.println("Size is "+ItemsList.items_list.size());
                                            
                                          
                                         //   System.out.println("***********");
                                        }
                 
                 }
                 
                 populateItemsBasedOnPageNo();
        //             ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
        //             ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+" matches found");
                 
                 
                 
                pageSize =pageSize+ItemsList.s_jobs.size();
            
                 
              //   ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
               //  ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),pageSize+"/"+size+" results");
            
                 
                 ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
                 ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                 
                 ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
                 ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                 
                 AdfmfJavaUtilities.flushDataChangeEvent();
                 
                 BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
                 vex.setCurrentIndex(ItemsList.s_jobs.size());
                 vex.refresh();
                
                 
             
             }
             catch(Exception e){
                 e.printStackTrace();
             }
             
             
             
             
                 
                 }
             
             
                
        }
    
        public void showPrevPageItems(ActionEvent actionEvent) {
            // Add event code here...
    
              if(pageNo>1) {
                  pageNo=pageNo-1;
                  
              }
              else{
                
              }
              
            populateItemsBasedOnPageNo();
            
              if(pageNo==1){
                  ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
                  ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                  pageSize =ItemsList.s_jobs.size();
              }
              else{
                  pageSize =pageSize-ItemsList.s_jobs.size();
                  ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
                  ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
              }
              
             
              
              
            
            
             
          //   ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
          //   ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),pageSize+"/"+size+" results");
              
            ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
            ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
            
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
            vex.setCurrentIndex(ItemsList.s_jobs.size());
            vex.refresh();
            
        }
        
     /*  public void filterItemsByCategory() {
            // Add event code here...
            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories.inputValue}", String.class);
            String c1=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories1.inputValue}", String.class);
            String c2=(String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories2.inputValue}", String.class);
            String c3=(String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories3.inputValue}", String.class);
            String c4=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
                
            
            ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
            
            String catText="";
            ItemsList.cpy_list.clear();
            ItemsList.filt_list.clear();
            
            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category1Name}", String.class);
            categoryText1=(String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve63 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category2Name}", String.class);
            categoryText2=(String)ve63.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve64 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category3Name}", String.class);
            categoryText3=(String)ve64.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve65 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category4Name}", String.class);
            categoryText4=(String)ve65.getValue(AdfmfJavaUtilities.getAdfELContext());
             pageNo=1;
            
            
            for(int i=0;i<ItemsList.items_list.size();i++) {
                System.out.println("index is ===============================" + i);
               Item item=(Item)ItemsList.items_list.get(i);
                ItemsList.filt_list.add(item);
            }
            
            if(c1!=null && !c1.equalsIgnoreCase("Please Select")) {
                catText=categoryText1;
            }
            if(c2!=null && !c2.equalsIgnoreCase("Please Select")) {
                catText=catText+"."+categoryText2;
            }
            if(c3!=null && !c3.equalsIgnoreCase("Please Select")) {
                catText=catText+"."+categoryText3;
            }
            if(c4!=null && !c4.equalsIgnoreCase("Please Select")) {
                catText=catText+"."+categoryText4; 
            }
            
            //catText contains the category mapping now get equivalent indix category for selected oracle category
            
            
            try{
            
            //get category Id from oracle and send the category id  along with the brand id
            
            RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
             // Clear any previously set request properties, if any
             restServiceAdapter.clearRequestProperties();
             // Set the connection name
             restServiceAdapter.setConnectionName("enrich");
             
             restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
             restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
             restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
             restServiceAdapter.addRequestProperty("Content-Type", "application/json");
             restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_indix_category/");
             String data= "{\n" + 
             "\n" + 
             "  \"GET_INDIX_CATEGORY_Input\" : {\n" + 
             "\n" + 
             "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_indix_category/\",\n" + 
             "\n" + 
             "   \"RESTHeader\": {\n" + 
             "\n" + 
             "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
             "    },\n" + 
             "\n" + 
             "   \"InputParameters\": {\n" + 
             "\n" + 
             "        \"P_ORACLE_CATEGORY\": \""+catText+"\"\n" + 
             "\n" + 
             "      }         \n" + 
             "\n" + 
             "   }\n" + 
             "\n" + 
             "}";                         
             restServiceAdapter.setRetryLimit(0);
             System.out.println("postData===============================" + data);
             String response = restServiceAdapter.send(data);
             System.out.println("response===============================" + response);
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
             String indix_category=output.getString("X_INDIX_CATEGORY");
             JSONObject indix_category_tl=output.getJSONObject("X_INDIX_CATEGORY_TL");   
                categoryRef="";
             if(indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM") instanceof JSONArray){
                   
                JSONArray items=(JSONArray)indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM");
                for(int i=0;i<items.length();i++){
                  categoryRef=categoryRef+"categoryId="+items.getString(i)+"&";
                }
                    
              }
              if(indix_category_tl.get("X_INDIX_CATEGORY_TL_ITEM") instanceof String){
                  categoryRef=categoryRef+"categoryId="+indix_category_tl.getString("X_INDIX_CATEGORY_TL_ITEM")+"&";   
                    
              }
                System.out.println("indix_category===============================" + indix_category);
             //quey indix to find categoryId for found category
                
                
    //         String url = "https://api.indix.com/v2/categories?app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
    //                                                
    //         URL obj = new URL(url);
    //         HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    //         // optional default is GET
    //         con.setRequestMethod("GET");
    //         //add request header
    //         int responseCode = con.getResponseCode();
    //         System.out.println("\nSending 'GET' request to URL : " + url);
    //         System.out.println("Response Code : " + responseCode);
    //         BufferedReader in = new BufferedReader(
    //            new InputStreamReader(con.getInputStream()));
    //         String inputLine;
    //         StringBuffer response1 = new StringBuffer();
    //         while ((inputLine = in.readLine()) != null) {
    //                   response1.append(inputLine);
    //              }
    //            in.close();
    //              //print result
    //         //    System.out.println(response1.toString());
    //         resp=new JSONObject(response1.toString());
    //         output=resp.getJSONObject("result");
    //         JSONArray resArr=output.getJSONArray("categories");
    //         
    //         for(int i=0;i<resArr.length();i++) {
    //             JSONObject resObj=(JSONObject)resArr.get(i);
    //             if(indix_category.equalsIgnoreCase(resObj.getString("name"))) {
    //                 System.out.println("************* Match occurs *********************");
    //                 categoryId=Integer.parseInt(resObj.getString("id"));
    //                 
    //                 //******************test************************
    //             }
    //                 
    //         }
                
                
              // call oracle api filter only items matching oracle category
                
                
                
               /////////////
               
                   
                   
               String url="";
                   ItemsList.s_jobs.clear();
                   ItemsList.items_list.clear();
                  
                   ValueExpression ve31 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
                   search = (String)ve31.getValue(AdfmfJavaUtilities.getAdfELContext()); 
                   
                ValueExpression veid = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
                String userId = (String)veid.getValue(AdfmfJavaUtilities.getAdfELContext());

                ValueExpression ve32 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
                String oracleId = (String)ve32.getValue(AdfmfJavaUtilities.getAdfELContext()); 
                   
                       
                   ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
                   ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), "S");
                   
               ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
               String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
                ValueExpression orid = AdfmfJavaUtilities.getValueExpression("#{applicationScope.org_id}", String.class);
                String orgId = (String)orid.getValue(AdfmfJavaUtilities.getAdfELContext());
               
               ValueExpression ve121 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
               String password = (String)ve121.getValue(AdfmfJavaUtilities.getAdfELContext());
                   restServiceAdapter = Model.createRestServiceAdapter();
                   response="";
                   String postData="";
                   resp=new JSONObject();
                   output=new JSONObject();
                   int rowCount=0;
                   
                 
               
               
               // Clear any previously set request properties, if any
               restServiceAdapter.clearRequestProperties();
               // Set the connection name
               restServiceAdapter.setConnectionName("enrich");
               
               restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
               restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
               restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
               restServiceAdapter.addRequestProperty("Content-Type", "application/json");
               restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/search_products/");
    
                   
                            postData= "{\n" + 
                           "  \"SEARCH_PRODUCTS_Input\" : {\n" + 
                           "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/search_products/\",\n" + 
                           "   \"RESTHeader\": {\n" + 
                           "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                           "    },\n" + 
                           "   \"InputParameters\": {\n" + 
                           "        \"P_SEARCH_ATTRIBS\": \n" + 
                           "            {\n" + 
                           "             \"SEARCH_TEXT\": \""+search+"\",\n" + 
                           "             \"CATEGORY\": \""+oracleId+"\",\n" +  
                           "             \"USER_ID\": \""+userId+"\",\n" +              
                           "             \"ITEM_TYPE\": \""+itemType+"\",\n" +
                           "             \"ORG_ID\": \""+orgId+"\"\n" +           
                           "            }\n" + 
                           "      }\n" + 
                           "   }\n" + 
                           "}\n";
                   
                   
                   
                                           restServiceAdapter.setRetryLimit(0);
                  System.out.println("postData===============================" + postData);
                   
                  response = restServiceAdapter.send(postData);
                   
                   System.out.println("response===============================" + response);
                  resp=new JSONObject(response);
                       
                  output=resp.getJSONObject("OutputParameters");
                   if(!output.getString("X_ERROR_MESSAGE").equalsIgnoreCase("No products found")){
                       rowCount=1;
                  JSONObject results=output.getJSONObject("X_SEARCH_RESULT_TBL");
                  
                  if(results.get("X_SEARCH_RESULT_TBL_ITEM") instanceof JSONArray){
                  
                  JSONArray body=results.getJSONArray("X_SEARCH_RESULT_TBL_ITEM");
                  for(int i=0;i<body.length();i++) {
                       JSONObject item=body.getJSONObject(i);   
                      String poNo=item.getString("PO_NUMBER");
                       String vendorName=item.getString("VENDOR_NAME");
                       String vendorSiteCode=item.getString("VENDOR_SITE_CODE");
                       String productCategory=item.getString("PRODUCT_CATEGORY");
                       String productTitle=item.getString("PRODUCT_TITLE");
                       String unitPrice=item.getString("UNIT_PRICE");
                       String uom=item.getString("UOM");
                       String source=item.getString("CONTRACTED");
                      //diverseSupplier
                       String diverseSupplier=item.getString("DIVERSE_SUPPLIER");
                       String imageURL=item.getString("IMAGE_URL");
                                           
                                            if (imageURL.contains("{")) {
                                                imageURL = "";
                                            }
                      
                       Random randomGenerator = new Random();
                       int randomInt = randomGenerator.nextInt(1000000000);
                      
                      String showDiverSeImage="true";
                      String diverseImageURL="/images/contract.png";
                      if(diverseSupplier.equalsIgnoreCase("1")) {
                          diverseImageURL="/images/diverse_contract.png";
                      }
                      
                       if(productCategory.equalsIgnoreCase(catText)) {
                            Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageURL,"/images/uncheck.png","Contracted",uom,String.valueOf(randomInt),diverseSupplier,showDiverSeImage,diverseImageURL,pageNo,"","","","","","","");
                            //ItemsList.s_jobs.add(j); 
                            ItemsList.items_list.add(j); 
                        } 
                      
                      
                      
                   }
                  }
                  else if(results.get("X_SEARCH_RESULT_TBL_ITEM") instanceof JSONObject){
                      
                      JSONObject item=results.getJSONObject("X_SEARCH_RESULT_TBL_ITEM");   
                      String poNo=item.getString("PO_NUMBER");
                      String vendorName=item.getString("VENDOR_NAME");
                      String vendorSiteCode=item.getString("VENDOR_SITE_CODE");
                      String productCategory=item.getString("PRODUCT_CATEGORY");
                      String productTitle=item.getString("PRODUCT_TITLE");
                      String unitPrice=item.getString("UNIT_PRICE");
                      String uom=item.getString("UOM");
                      String source=item.getString("CONTRACTED");
                      String diverseSupplier=item.getString("DIVERSE_SUPPLIER");
                      String imageURL=item.getString("IMAGE_URL");
                                          
                                           if (imageURL.contains("{")) {
                                               imageURL = "";
                                           }
                      
                      Random randomGenerator = new Random();
                      int randomInt = randomGenerator.nextInt(1000000000);
                      String showDiverSeImage="true";
                      String diverseImageURL="/images/contract.png";
                      if(diverseSupplier.equalsIgnoreCase("1")) {
                          diverseImageURL="/images/diverse_contract.png";
                      }
                      
                      if(productCategory.equalsIgnoreCase(catText)) {
                           Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageURL,"/images/uncheck.png","Contracted",uom,String.valueOf(randomInt),diverseSupplier,showDiverSeImage,diverseImageURL,pageNo,"","","","","","","");
                           //ItemsList.s_jobs.add(j); 
                           ItemsList.items_list.add(j); 
                       } 
                     
                  }
                  
                  
                   
                   providerChangeSupport.fireProviderRefresh("assets");
                   }
                   
                   
                   //call indix api with categoryId applied
                
                if(itemType.equalsIgnoreCase("goods")){
                
                try{
                
                           url = "https://api.indix.com/v2/universal/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+"&"+categoryRef+"availability=IN_STOCK&lastRecordedIn=30&pageSize=20&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                                           
                                          URL obj = new URL(url);
                                         HttpURLConnection  con = (HttpURLConnection) obj.openConnection();
                
                                           // optional default is GET
                                           con.setRequestMethod("GET");
                
                                           //add request header
                                          
                
                                          int responseCode = con.getResponseCode();
                                           System.out.println("\nSending 'GET' request to URL : " + url);
                                           System.out.println("Response Code : " + responseCode);
                
                                         BufferedReader  in = new BufferedReader(
                                                   new InputStreamReader(con.getInputStream()));
                                          
                                          StringBuffer  response1 = new StringBuffer();
                                          String inputLine;
                                           while ((inputLine = in.readLine()) != null) {
                                                   response1.append(inputLine);
                                           }
                                           in.close();
                
                                           //print result
                                       //    System.out.println(response1.toString());
                                
                                           resp=new JSONObject(response1.toString());
                                           output=resp.getJSONObject("result");
                                           JSONArray resArr=output.getJSONArray("products");
                                           String result_size=output.getString("count"); 
                                           if(Integer.parseInt(result_size)>500) {
                                               size=ItemsList.items_list.size()+500;
                                           }
                                           else{
                                               size=ItemsList.items_list.size()+Integer.parseInt(result_size);
                                           }
                                           
                   // System.out.println("resArr.length() "+resArr.length());
                                           for(int i=0;i<resArr.length();i++) {
                                               rowCount=1;
                                           //    System.out.println("***********");
                                               JSONObject productObj=resArr.getJSONObject(i);
                                          //     System.out.println(productObj.toString());
                                            //   System.out.println("***********");
                                               String poNo="";
                                                String vendorName="";
                                                String vendorSiteCode="";
                                                String productCategory=productObj.getString("categoryName");
                                                
                                                String unitPrice=productObj.getString("minSalePrice");
                                                String imageUrl=productObj.getString("imageUrl");
                                                JSONObject stores=productObj.getJSONObject("stores");
                                               String indixCategoryId=productObj.getString("categoryId");
                                               
                                               Iterator<?> keys = stores.keys();
    
                                               while( keys.hasNext() ) {
                                                   String key = (String)keys.next();
                                                   JSONObject store=stores.getJSONObject(key);
                                                   vendorName=store.getString("storeName");
                                                    String productTitle=store.getString("title");
                                                    Random randomGenerator = new Random();
                                                    int randomInt = randomGenerator.nextInt(1000000000);
                                                    String showDiverSeImage="false";
                                                    String diverseImageURL="";
                                                    
                                                   //since for the indix suppliers they will not be diversed so update as -1
                                                     Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo,indixCategoryId,"","","","","","");
                                                     //ItemsList.s_jobs.add(j); 
                                                     ItemsList.items_list.add(j); 
                                                }
                                               
                                             
                                            //   System.out.println("***********");
                                           }
                
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
                }
                
               isFilterSet=true;
               populateItemsBasedOnPageNo();
                   
                 
                System.out.println("The size is "+ItemsList.items_list.size());
          
            
                ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
                ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                
                ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
                //ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.items_list.size()+" matches found");
                if(ItemsList.items_list.size()>500){
                   ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),"More than 500 results found");
                }
                else{
                   ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.items_list.size()+" results found");
                }
            
            
            
            //bind the list with the new filtered list
                BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
                vex.refresh();
                AdfmfJavaUtilities.flushDataChangeEvent();
                
                if(ItemsList.items_list.size()==0) {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                               "displayNoMatch",
                                                                               new Object[] { });
                }
                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
        }
        */
        
        public void clearFilter() {
            // Add event code here...
    //        System.out.println("Item size is "+ItemsList.cpy_list.size());
    //        ItemsList.s_jobs.clear();
    //        ItemsList.items_list.clear();
    //        for(int i=0;i<ItemsList.filt_list.size();i++) {
    //            Item s=(Item)ItemsList.filt_list.get(i);
    //            ItemsList.items_list.add(s);
    //        }
    //        
    //        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
    //        vex.refresh();
    //        
    //        ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
    //        ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+" matches found");
            
            isFilterSet=false;
            categoryId=0;
            categoryRef="&";
            refresh();
            
        }
        
        public void sortItems() {
            // Add event code here...
            //#{pageFlowScope.selectedSortBy}
            System.out.println("Enter into Sort Items");
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedSortBy}", String.class);
            String sortBy=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String multiOrgId = (String)ve17.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            /*String defaultsortBy="relevance";
            
            if(defaultsortBy.equalsIgnoreCase("relevance")) {
                ComparatorChain chain = new ComparatorChain();  
                //call the relevance api to get the relevance factors
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
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_relevance_factor/");
                String postData= "{\n" + 
                "\n" + 
                "  \"GET_RELEVANCE_FACTOR_TEXT_Input\" : {\n" + 
                "\n" + 
                "   \"RESTHeader\": {\n" + 
                "\n" + 
                "    },\n" + 
                "\n" + 
                "   \"InputParameters\": {\n" + 
                "\n" + 
                "       }         \n" + 
                "\n" + 
                "   }\n" + 
                "\n" + 
                "}";
                
                   restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                   String response = restServiceAdapter.send(postData);
                   System.out.println("response===============================" + response); 
                   JSONObject resp=new JSONObject(response);
                   JSONObject output=resp.getJSONObject("OutputParameters");
                   JSONObject data=output.getJSONObject("X_RELEVANCE_FACTOR_TL"); 
                   
                    if(data.get("X_RELEVANCE_FACTOR_TL_ITEM") instanceof  JSONArray){
                                  JSONArray segments=data.getJSONArray("X_RELEVANCE_FACTOR_TL_ITEM");
                                  for(int i=0;i<segments.length();i++) {
                                      //String name=(String)segments.get(i);
                                      JSONObject ci=(JSONObject)segments.get(i);
                                      String type_name=ci.getString("LOOKUP_TYPE");
                                      String type_code=ci.getString("LOOKUP_CODE");
                                      String meaning=ci.getString("MEANING");
                                      String tag=ci.getString("TAG");
                                      
                                      if(type_code.equalsIgnoreCase("DIV_SUPPLIER")) {
                                          System.out.println("div supplier===============================");
                                          Comparator<Item> comparatorDiversedSupplier = new Comparator<Item>() {  
                                                       @Override  
                                                       public int compare(Item o1, Item o2) {  
                                                            return Double.compare(Double.parseDouble(o2.getDiverseSupplier()), Double.parseDouble(o1.getDiverseSupplier()));  
                                                       }  
                                                  };  
                                          chain.addComparator(comparatorDiversedSupplier); 
                                      }
                                      if(type_code.equalsIgnoreCase("PRICE_LOW_TO_HIGH")) {
                                          System.out.println("low to high===============================");
                                          Comparator<Item> comparatorPriceLowToHigh = new Comparator<Item>() {  
                                                       @Override  
                                                       public int compare(Item o1, Item o2) {  
                                                           return Double.compare(Double.parseDouble(o1.getUnitPrice()), Double.parseDouble(o2.getUnitPrice()));  
                                                       }  
                                                  };  
                                          chain.addComparator(comparatorPriceLowToHigh); 
                                          
                                      }
                                      
                                  }
                                    
                                    Collections.sort(ItemsList.s_jobs,chain);
                                    BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
                                    vex.refresh();
                                
                                }
                                
                                else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
                                   
                                   JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
                                    String type_name=ci.getString("LOOKUP_TYPE");
                                    String type_code=ci.getString("LOOKUP_CODE");
                                    String meaning=ci.getString("MEANING");
                                    String tag=ci.getString("TAG");
                                    
                                    if(type_code.equalsIgnoreCase("DIV_SUPPLIER")) {
                                        
                                        Comparator<Item> comparatorDiversedSupplier = new Comparator<Item>() {  
                                                     @Override  
                                                     public int compare(Item o1, Item o2) {  
                                                          return Double.compare(Double.parseDouble(o2.getDiverseSupplier()), Double.parseDouble(o1.getDiverseSupplier()));  
                                                     }  
                                                };  
                                        chain.addComparator(comparatorDiversedSupplier); 
                                    }
                                    if(type_code.equalsIgnoreCase("PRICE_LOW_TO_HIGH")) {
                                        
                                        Comparator<Item> comparatorPriceLowToHigh = new Comparator<Item>() {  
                                                     @Override  
                                                     public int compare(Item o1, Item o2) {  
                                                         return Double.compare(Double.parseDouble(o2.getUnitPrice()), Double.parseDouble(o1.getUnitPrice()));  
                                                     }  
                                                };  
                                        chain.addComparator(comparatorPriceLowToHigh); 
                                        
                                    }
                                   
                                    Collections.sort(ItemsList.s_jobs,chain);
                                    BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
                                    vex.refresh();
                                }
                   
                   
                
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }
            */
            if(sortBy.equalsIgnoreCase("relevance")) {
                ComparatorChain chain = new ComparatorChain();  
                //call the relevance api to get the relevance factors
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
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_relevance_factor/");
                String postData= "{\n" + 
                "\n" + 
                "  \"GET_RELEVANCE_FACTOR_TEXT_Input\" : {\n" + 
                "\n" + 
                "   \"RESTHeader\": {\n" + 
                "\n" + 
                "    },\n" + 
                "\n" + 
                    "   \"InputParameters\": {\n" +
                    "          \"P_ORG_ID\" : \""+multiOrgId+"\",\n" + 
                    "        \"P_TYPE\": \"SEARCH\"\n" +
                    "     }\n" +
                    "  }\n" +
                    "}";
                
                   restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                   String response = restServiceAdapter.send(postData);
                   System.out.println("response===============================" + response); 
                   JSONObject resp=new JSONObject(response);
                   JSONObject output=resp.getJSONObject("OutputParameters");
                   JSONObject data=output.getJSONObject("X_RELEVANCE_FACTOR_TL"); 
                   
                    if(data.get("X_RELEVANCE_FACTOR_TL_ITEM") instanceof  JSONArray){
                                  JSONArray segments=data.getJSONArray("X_RELEVANCE_FACTOR_TL_ITEM");
                                  for(int i=0;i<segments.length();i++) {
                                      //String name=(String)segments.get(i);
                                      JSONObject ci=(JSONObject)segments.get(i);
                                      String type_name=ci.getString("LOOKUP_TYPE");
                                      String type_code=ci.getString("LOOKUP_CODE");
                                      String meaning=ci.getString("MEANING");
                                      String tag=ci.getString("TAG");
                                      
                                     
                                      if(type_code.equalsIgnoreCase("DIV_SUPPLIER")) {
                                          
                                          System.out.println("div supplier===============================");
                                          Comparator<Item> comparatorDiversedSupplier = new Comparator<Item>() {
                                              
                                                       @Override
                                                       
                                                       
                                                       public int compare(Item o1, Item o2) {  
                                                            return Double.compare(Double.parseDouble(o2.getDiverseSupplier()), Double.parseDouble(o1.getDiverseSupplier()));  
                                                       }  
                                                  };  
                                          chain.addComparator(comparatorDiversedSupplier); 
                                      }
                                      
                                      if(type_code.equalsIgnoreCase("PRICE_LOW_TO_HIGH")) {
                                          System.out.println("low to high===============================");
                                          Comparator<Item> comparatorPriceLowToHigh = new Comparator<Item>() {  
                                                       @Override  
                                                       public int compare(Item o1, Item o2) {
                                                           
                                                           System.out.println("----Item1 Price----"+o1.getUnitPrice()+"---Item2 Price---"+o2.getUnitPrice());
                                                           return Double.compare(Double.parseDouble(o1.getUnitPrice()), Double.parseDouble(o2.getUnitPrice()));  
                                                       }  
                                                  };  
                                          chain.addComparator(comparatorPriceLowToHigh); 
                                          
                                      }
                                      
                                  }
                                    
                                    System.out.println("chain Values--->"+chain.toString());
                                    Collections.sort(ItemsList.s_jobs,chain);
                                    BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
                                    vex.refresh();
                                
                                }
                                
                                else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
                                   
                                   JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
                                    String type_name=ci.getString("LOOKUP_TYPE");
                                    String type_code=ci.getString("LOOKUP_CODE");
                                    String meaning=ci.getString("MEANING");
                                    String tag=ci.getString("TAG");
                                    
                                    if(type_code.equalsIgnoreCase("DIV_SUPPLIER")) {
                                        
                                        Comparator<Item> comparatorDiversedSupplier = new Comparator<Item>() {  
                                                     @Override  
                                                     public int compare(Item o1, Item o2) {
                                                        
                                                         return Double.compare(Double.parseDouble(o2.getDiverseSupplier()), Double.parseDouble(o1.getDiverseSupplier()));  
                                                     }  
                                                };  
                                        chain.addComparator(comparatorDiversedSupplier); 
                                    }
                                    if(type_code.equalsIgnoreCase("PRICE_LOW_TO_HIGH")) {
                                        
                                        Comparator<Item> comparatorPriceLowToHigh = new Comparator<Item>() {  
                                                     @Override  
                                                     public int compare(Item o1, Item o2) {  
                                                         return Double.compare(Double.parseDouble(o2.getUnitPrice()), Double.parseDouble(o1.getUnitPrice()));  
                                                     }  
                                                };  
                                        chain.addComparator(comparatorPriceLowToHigh); 
                                        
                                    }
                                   
                                    Collections.sort(ItemsList.s_jobs,chain);
                                    BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
                                    vex.refresh();
                                }
                   
                   
                
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }
            if(sortBy.equalsIgnoreCase("low_to_high")) {
                
                //clear the existing data and reset the page to 1 and start downloading the info for page 1 from oracle and then download indix with filter applied and sort
                
              
               //           
               pageMap=new HashMap();
                isSortSet=true;
                
                //get no of Items in page no 1 and group the rest as other pages
                
                getFirstPageItemsCount();
                Collections.sort( ItemsList.items_list,new Comparator<Item>() {
                                                  public int compare(Item o1, Item o2) {
                                                          int res=0;
                                                      if (o1.getUnitPrice() == null || o2.getUnitPrice() == null)
                                                      {
                                                        res= 0;
                                                      }
                                                      else{
                                                          
                                                          res=Double.compare(Double.parseDouble(o1.getUnitPrice()), Double.parseDouble(o2.getUnitPrice()));
                                                          
        //                                                  try {
        //                                                            res= sdf.parse(o2.getCreatedDate()).compareTo(sdf.parse(o1.getCreatedDate()));
        //                                                    } catch (Exception e) {
        //                                                            // TODO Auto-generated catch block
        //                                                            e.printStackTrace();
        //                                                    }
                                                          
                                                          
                                                      }
                                                        return res;
                                                  }
                                                 
                                                });  
                reorderItemsBasedOnPageNo();
                pageNo=1;
                populateItemsBasedOnPageNo();
                
                if(pageNo==1){
                  pageSize=ItemsList.s_jobs.size();
                }
                else{
                    pageSize+=ItemsList.s_jobs.size();
                }
                
                ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
             //   ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),pageSize+"/"+size+" results");
             //Recently Change sort Values
             
             //ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.items_list.size()+" results");
             
                if(size>500){
                    ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),"More than 500 results found");
                }
                else{
                    ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),size+" results found");
                }
             

               
                
                ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
                ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
    
                ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
                ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                
                AdfmfJavaUtilities.flushDataChangeEvent();
                
    //            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
    //            vex.refresh();
            }
            
            if(sortBy.equalsIgnoreCase("high_to_low")) {
                pageMap=new HashMap();
                
                getFirstPageItemsCount();
                Collections.sort( ItemsList.items_list,new Comparator<Item>() {
                                                  public int compare(Item o1, Item o2) {
                                                          int res=0;
                                                      if (o1.getUnitPrice() == null || o2.getUnitPrice() == null)
                                                      {
                                                        res= 0;
                                                      }
                                                      else{
                                                          
                                                          res=Double.compare(Double.parseDouble(o2.getUnitPrice()), Double.parseDouble(o1.getUnitPrice()));
                                                          
                //                                                  try {
                //                                                            res= sdf.parse(o2.getCreatedDate()).compareTo(sdf.parse(o1.getCreatedDate()));
                //                                                    } catch (Exception e) {
                //                                                            // TODO Auto-generated catch block
                //                                                            e.printStackTrace();
                //                                                    }
                                                          
                                                          
                                                      }
                                                        return res;
                                                  }
                                                 
                                                });  
                reorderItemsBasedOnPageNo();
                pageNo=1;
                populateItemsBasedOnPageNo();
                
                
                    
                if(pageNo==1){
                  pageSize=ItemsList.s_jobs.size();
                }
                else{
                    pageSize+=ItemsList.s_jobs.size();
                }
                
                ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
                ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
    
                ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
                ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                
                ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
               // ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),pageSize+"/"+size+" results");
             //  ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),+size+" results");
             //ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),+ItemsList.items_list.size()+" results");
               
                if(size>500){
                    ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),"More than 500 results found");
                }
                else{
                    ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),size+" results found");
                } 
               
                AdfmfJavaUtilities.flushDataChangeEvent();
                
            }
            
            
            
        }
        
        
        public void getItemDetails() {
            // Add event code here...
           
            try{
            
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.productTitle}", String.class);
            String productTitle = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemRowId}", String.class);
            String rowId = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
    
    System.out.println("GetItemDetails===>"+rowId);
            
           
                //SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
                int count=SelectedItemsList.s_jobs.size();
                ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
                ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
                AdfmfJavaUtilities.flushDataChangeEvent();
            System.out.println("GetItemDetails List size===>"+ItemsList.s_jobs.size());
            
            
            for(int j=0;j<ItemsList.s_jobs.size();j++) {
                Item i=(Item)ItemsList.s_jobs.get(j);    
                if(i.getRowId().equalsIgnoreCase(rowId)) {
                    ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.unitPrice}", String.class);
                    ve1.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getUnitPrice());
                  
                    
                    ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.productCategory}", String.class);
                    ve2.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getProductCategory());
                    
                    ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.vendorName}", String.class);
                    ve3.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getVendorName());                
    
                    ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.vendorSiteCode}", String.class);
                    ve4.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getVendorSiteCode());
                    
                    ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.poNo}", String.class);
                    ve5.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getPoNo());
                    ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom}", String.class);
                    ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getUom());
                    ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.source}", String.class);
                    ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),i. getSource());
                    
                    ValueExpression ve8 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemImageUrl}", String.class);
                    ve8.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getImageUrl());
                    
                    ValueExpression ve10 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.getItemDetails}", String.class);
                    ve10.setValue(AdfmfJavaUtilities.getAdfELContext(),rowId);
                    System.out.println("Detail page Row id::::::::::"+ rowId);
                    
                    ValueExpression ve9 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddToCart}", String.class);
                    ve9.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                    
                    ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.sellerName}", String.class);
                    ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getSellerName());
                    
                    ValueExpression ve116 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.Spec}", String.class);
                    ve116.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getSpec());
                    
                    if (i.getSellerName().equalsIgnoreCase("")){
                    ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.sellerDetailShow}", String.class);
                    ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                    }
                    else{
                        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.sellerDetailShow}", String.class);
                        ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                        
                    }
//                    #{pageFlowScope.sellerDetailShow}

                    // Delivery type Image
                    ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemImageDeliverSource}", String.class);
                    ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getDiverseImageURL());                
                    ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showDiverse}", String.class);
                    ve23.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getShowDiverseImage());                
                    AdfmfJavaUtilities.flushDataChangeEvent();
                    
                }
            }
            
            }
            catch(Exception e) {
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Cannot connect to Services on Oracle Server.",
                                             null,
                                             null });          
            }
            
          
        }
        
        
        public void showCartDetail() {
            // Add event code here...
            System.out.println("Hello Cart in Item List");
            
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
            String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression orid = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String multiorgId = (String)orid.getValue(AdfmfJavaUtilities.getAdfELContext());
            
          // SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
            int count=SelectedItemsList.s_jobs.size();
            
            String default_cost_center_id="";
            String default_cost_center_name="";
            String default_deliver_to_location_id="";
            String default_deliver_to_location_name="";
            
            String default_natural_account_seg="";
            String default_natural_account_description="";
            
            Double cartValue=0.0;
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
            for(int i=0;i<SelectedItemsList.s_jobs.size();i++)
             {
                 SelectedItem item=(SelectedItem)SelectedItemsList.s_jobs.get(i);
                  cartValue=cartValue+ (Double.parseDouble(item.getAmount())*Integer.parseInt(item.getQuantity()));
                 
             }
            ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(Math.round(cartValue)));        
            AdfmfJavaUtilities.flushDataChangeEvent();  
            
            
            if(count>0){
            if(itemType!=null && !itemType.equalsIgnoreCase(""))  {
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
                "        \"P_USER_ID\":\""+userId+"\",\n" + 
                "         \"P_ORG_ID\":\""+multiorgId+"\"\n" +  
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
                      if(locationId.equalsIgnoreCase(default_deliver_to_location_id)) {
                          default_deliver_to_location_name=locationCode;
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
                     if(locationId.equalsIgnoreCase(default_deliver_to_location_id)) {
                         default_deliver_to_location_name=locationCode;
                     }
                     DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                     DeliverToLocationList.s_jobs.add(loc);
                     deliverToLocationList.add(loc);
                    
                 }
                 }
                 catch(Exception e) {
                     e.printStackTrace();
                 }
             
                
                
                
                // Natural Accounts
                            
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
                                "          \"P_ORG_ID\" : \""+multiorgId+"\"\n" + 
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
                                      if(name.equalsIgnoreCase(default_natural_account_seg)) {
                                          default_natural_account_description=String.valueOf(i);
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
                                    if(name.equalsIgnoreCase(default_natural_account_seg)) {
                                        default_natural_account_description=String.valueOf(0);
                                    }
                                    NaturalAccounts c=new NaturalAccounts(name,description);
                                    NaturalAcccountList.acc_List.add(c);
                                    naturalAccountList.add(c);
                                   
                                }
                          /*          AmxAttributeBinding accountList = (AmxAttributeBinding) AdfmfJavaUtilities
                                                      .evaluateELExpression("#{bindings.naturalAccounts}");
                                    AmxIteratorBinding accountListIterator =  accountList.getIteratorBinding();
                                    accountListIterator.refresh();*/
                                
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
                "          \"P_USER_ID\" : \""+userId+"\",\n" +
                "          \"P_ORG_ID\" : \""+multiorgId+"\"\n" + 
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
                      costCenterList.add(c);
                      
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
                    costCenterList.add(c);
                   
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
                
                
              
              
              
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                                   "adf.mf.api.amx.doNavigation", new Object[] { "checkout" });    
            
            }
            else{
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Item Type is mandatory and cannot be empty.",
                                             null,
                                             null });    
                
            }
            
            }
            else{
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Your cart is empty.",
                                             null,
                                             null });    
                
            }
            
            
        }
           
           
        public void addItemFromDetailToCart() {
            
           // Add event code here...
            
           ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
           String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
           boolean isNonContractedItemPresent=false;
           if(itemType!=null && !itemType.equalsIgnoreCase("")) {
            
             /*  ValueExpression ve10 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDetailRowId}", String.class);
               String rowId = (String)ve10.getValue(AdfmfJavaUtilities.getAdfELContext());*/
               
           ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemRowId}", String.class);
            String rowId = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            for(int j=0;j<ItemsList.s_jobs.size();j++) {
                Item item=(Item)ItemsList.s_jobs.get(j); 
                        System.out.println("item.getRowId()---->"+item.getRowId());
                        System.out.println(" Rowid----if:"+rowId); 
                       if(item.getRowId().equalsIgnoreCase(rowId)) {
                          System.out.println("Enter into If Rowid----if:"+rowId+"item.getRowId()"+item.getRowId());
                           if(item.getSource().equalsIgnoreCase("Contracted")){
                               doSelectItemDetails();
                           }
                           else{
                               ValueExpression isContractedItemPresent = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isContractedItemPresent}", String.class);
                                          String isContractedAvailable=(String)isContractedItemPresent.getValue(AdfmfJavaUtilities.getAdfELContext());
                               
                                          if(isContractedAvailable.equalsIgnoreCase("true")){
                                              
                                              AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                                         "contracted_alert",
                                                                                                         new Object[] { });
                                          }
                                          else{
                                              //System.out.println("Enter into else Rowid----if:"+rowId+"item.getRowId()"+item.getRowId());
                                              //System.out.println(" Rowid----Else:"+rowId);
                                              //System.out.println("item.getRowId()---->"+item.getRowId());
                                              doSelectItemDetails();
                                          }
                           }
                      }
                    }
                
                     
            
           }
           else{
               AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                        AdfmfJavaUtilities.getFeatureName(),
                                                        "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                        "Item Type is mandatory and cannot be empty.",
                                                        null,
                                                        null });  
           }
            
                
        }
        
        
        public void doSelectItemDetails(){
            //New Designstart
            
            try {
                //CostCenter with Natural Account
                ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
                String userId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
                String multiOrgId = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression vf3 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_costNaturalAccount}", String.class);
                String default_cost_natural_accout = (String)vf3.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression loc_code = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to_locationCode}", String.class);
                String default_loc_code=(String)loc_code.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression vf_loc_code = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to_locationValue}", String.class);
                vf_loc_code.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
                
                
            
            
                ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
                String default_cost_center_Value = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());

                ValueExpression ve150 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_natural_account}", String.class);
                String default_natural_account_Value = (String)ve150.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                ValueExpression ve_cost = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_centerId}", String.class);
                ve_cost.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
                
                ValueExpression ve_natural = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_natural_accountId}", String.class);
                ve_natural.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
                

                System.out.println("Dafult Cost Center Value-->"+default_cost_natural_accout);

                ValueExpression vf31 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_costNaturalAccountId}", String.class);
                vf31.setValue(AdfmfJavaUtilities.getAdfELContext(), "");

                RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
                
                // GET Cost Center based MultiOrg 
                
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
                     String postData= "{\n" +
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
                            "          \"P_ORG_ID\" : \""+multiOrgId+"\"\n" +
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
                        JSONObject  resp=new JSONObject(response);
                         JSONObject output=resp.getJSONObject("OutputParameters");
                     try{
                             JSONObject  data=output.getJSONObject("X_COST_CENTER_TL");
                             CostCenterList.s_jobs.clear();
                             costCenterList.clear();
                         
                             if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
                                                             JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
                                                             for(int i=0;i<segments.length();i++) {
                                                                 JSONObject ci=(JSONObject)segments.get(i);
                                                                 String name=ci.getString("SEGMENT_VALUE");
                                                                 String description=ci.getString("DESCRIPTION");
                                                                 if(description.equalsIgnoreCase(default_cost_center_Value)) {
                                                                  ve_cost.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
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
                                                               if(description.equalsIgnoreCase(default_cost_center_Value)) {
                                                                ve_cost.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
                                                                System.out.println("Dafult Cost Center Value-->"+default_cost_center_Value);

                                                                }
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
                    JSONObject data=output.getJSONObject("X_NATURAL_ACC_TL");
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
                           ve_natural.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
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
                        if(description.equalsIgnoreCase(default_natural_account_Value)) {
                         ve_natural.setValue(AdfmfJavaUtilities.getAdfELContext(),description);
                          System.out.println("Dafult GL Account Value-->"+default_natural_account_Value);
                         }
                        NaturalAccounts c=new NaturalAccounts(name,description);
                        NaturalAcccountList.acc_List.add(c);
                        naturalAccountList.add(c);
                       
                    }
                     
                    
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                
                         
                                // Natural Accounts
                            
                       /*     restServiceAdapter = Model.createRestServiceAdapter();
                            // Clear any previously set request properties, if any
                            restServiceAdapter.clearRequestProperties();
                            // Set the connection name
                            restServiceAdapter.setConnectionName("enrich");
                            
                            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cc_acct_lov/");
                            String postData= "{\n" + 
                                "  \"GET_CC_ACCT_LOV_Input\" : {\n" + 
                                "   \"RESTHeader\": {\n" + 
                                "    },\n" + 
                                "   \"InputParameters\": {\n" + 
                                "          \"P_USER_ID\" : \""+userId+"\"\n" + 
                                "         ,\"P_ORG_ID\" : \""+multiOrgId+"\"\n" + 
                                "       }          \n" + 
                                "   }\n" + 
                                "}\n";
                                
                               restServiceAdapter.setRetryLimit(0);
                               System.out.println("postData===============================" + postData);
                                
                              String response = restServiceAdapter.send(postData);
                                
                                System.out.println("response===============================" + response); 
                               JSONObject  resp=new JSONObject(response);
                                JSONObject output=resp.getJSONObject("OutputParameters");
                            try{
                                JSONObject data=output.getJSONObject("X_CC_ACCT_TL");
                                NaturalAcccountList.acc_List.clear();
                                naturalAccountList.clear();
                                
                                if(data.get("X_CC_ACCT_TL_ITEM") instanceof  JSONArray){
                                  JSONArray segments=data.getJSONArray("X_CC_ACCT_TL_ITEM");
                                  for(int i=0;i<segments.length();i++) {
                                      JSONObject na=(JSONObject)segments.get(i);
                                      String costCenter=na.getString("COST_CENTER");
                                      String naturalAccount=na.getString("NATURAL_ACCOUNT");
                                      String costCenterDisc=na.getString("COST_CENTER_DESC");
                                      String naturalAccountDisc=na.getString("NATURAL_ACCT_DESC");
                                      String DispValue=na.getString("DISPLAY_VALUE");
                                      System.out.println("Dafult Cost Center Value-->"+default_cost_natural_accout+"---"+DispValue);
                                      if(DispValue.equalsIgnoreCase(default_cost_natural_accout)) {
                                          vf31.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(i));
                                          
                                          System.out.println("Dafult Cost Center Value-->"+default_cost_natural_accout);

                                      }
                                      
                                      CostCenterNaturalAccounts c=new CostCenterNaturalAccounts(costCenter, naturalAccount, costCenterDisc, naturalAccountDisc, DispValue);
                                      CostCenterNaturalAccountsList.CC_NA_List.add(c);
                                      costCenterNaturalAccountsList.add(c);
                                      
                                  }
                                
                                }
                                
                                else if(data.get("X_CC_ACCT_TL_ITEM") instanceof  JSONObject){
                                   
                                   JSONObject na=data.getJSONObject("X_CC_ACCT_TL_ITEM");
                                    String costCenter=na.getString("COST_CENTER");
                                    String naturalAccount=na.getString("NATURAL_ACCOUNT");
                                    String costCenterDisc=na.getString("COST_CENTER_DESC");
                                    String naturalAccountDisc=na.getString("NATURAL_ACCT_DESC");
                                    String DispValue=na.getString("DISPLAY_VALUE");
                                    if(DispValue.equalsIgnoreCase(default_cost_natural_accout)) {
                                        vf31.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(0));
                                    }
                                    
                                    CostCenterNaturalAccounts c=new CostCenterNaturalAccounts(costCenter, naturalAccount, costCenterDisc, naturalAccountDisc, DispValue);
                                    CostCenterNaturalAccountsList.CC_NA_List.add(c);
                                    costCenterNaturalAccountsList.add(c);
                                   
                                }
                                 
                                
                                }
                                catch(Exception e) {
                                    e.printStackTrace();
                                }
                                */
                          
            
            
            //Deliver to Location   
               
            
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
                      if(locationCode.equalsIgnoreCase(default_loc_code)) {
                          vf_loc_code.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(i+1));
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
                        if(locationCode.equalsIgnoreCase(default_loc_code)) {
                            vf_loc_code.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(0));
                        }
                    DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
                    DeliverToLocationList.s_jobs.add(loc);
                    deliverToLocationList.add(loc);
                   
                }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            
            catch(Exception e) {
                e.printStackTrace();
            }
            //End
            
            
            ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_centerId}", String.class);
             String default_cost_center = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());
                
             ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to_locationValue}", String.class);
             String default_deliver_to_location = (String)ve49.getValue(AdfmfJavaUtilities.getAdfELContext()); 
            
             ValueExpression ve150 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_natural_accountId}", String.class);
             String default_natural_account = (String)ve150.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
                   String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
                   if(itemType!=null && !itemType.equalsIgnoreCase("")) {

                    
                    ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemRowId}", String.class);
                    String rowId = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
                    System.out.println("DoSelectItemDetals--------->"+rowId);
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    
                    for(int j=0;j<ItemsList.s_jobs.size();j++) {
                        Item item=(Item)ItemsList.s_jobs.get(j);
                                System.out.println(item.getRowId()+"="+rowId);
                               if(item.getRowId().equalsIgnoreCase(rowId)) {
                                  Random randomGenerator = new Random();
                                  int randomInt = randomGenerator.nextInt(1000000000);
                                  System.out.println("Row Id random values:--->"+randomInt);
                                  System.out.println("Attribute Values"+item.getAttrib());
                                  System.out.println("Specification Values "+item.getSpec());
                                  String specList="";
                                  if(item.getSpec()!=""){
                                      System.out.println("Specification Values=====> "+item.getSpec()); 
                                      String spec0 =item.getSpec().substring(0,item.getSpec().length()-2);
                                      String spec1 = spec0.replaceAll("\"", "\\\\\"");                
                                      String spec2 = spec1.replaceAll(":", "\",\"ATTRIBUTE_VALUE\" : \"");
                                      String spec3 = spec2.replaceAll("#,", "\"},{\"ATTRIBUTE_NAME\" : \"");
                                      String spec ="{\"ATTRIBUTE_NAME\" : \""+spec3+"\"}";
                                      /*
                                      {"ATTRIBUTE_NAME" : "soapui_24jun16_name1","ATTRIBUTE_VALUE" : "soapui_24jun16_value2"}
                                      */
                                      /*:--","ATTRIBUTE_VALUE" : "
                                      * | "},{"ATTRIBUTE_NAME" : "*/
                                      //String spec = item.getSpec().replaceAll("#,", " | ").replaceAll("\"", "\\\\\"");
                                      
                                      //specList = spec.substring(0,spec.length()-2);
                                      specList = spec;
                                      System.out.println("After replace Spec Values=====> "+specList); 
                                      
                                  }
                                  else if(item.getAttrib()!="") {
                                      String spec = item.getAttrib();
                                      specList = spec.substring(0,spec.length()-2);
                                      System.out.println("After Attrib=====> "+specList); 
                                  }


                     SelectedItem selectItem=new SelectedItem(item.getPoNo(), item.getVendorName(), item.getVendorSiteCode(), item.getProductCategory(), item.getProductTitle(), item.getUnitPrice(), item.getImageUrl(), "true", item.getSource(), item.getUom(), "1", default_deliver_to_location, "",item.getUnitPrice(),String.valueOf(randomInt),default_cost_center,item.getRowId(),item.getIndixCategoryId(),specList,default_natural_account,"","goods","","","","","","","","","","","","","","");
                     SelectedItemsList.s_jobs.add(selectItem) ; 
                              }
                            }
                        
                   // SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
                    int count=SelectedItemsList.s_jobs.size();
                    ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
                    ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
                    AdfmfJavaUtilities.flushDataChangeEvent();
                       AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                  "displayAlert",
                                                                                  new Object[] {}); 
                    
                   }
                   else{
                       AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                AdfmfJavaUtilities.getFeatureName(),
                                                                "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                "Item Type is mandatory and cannot be empty.",
                                                                null,
                                                                null });  
                   }
        }
        
        
        public void deleteFromCart() {
            // Add event code here...
            
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartRowId}", String.class);
            String rowId=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
    
            
            
             System.out.print("rowId is   =========>"+rowId);
             
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
             
            for(int i=0;i<vex.getTotalRowCount();i++)
                    {
                        vex.setCurrentIndex(i);
                        SelectedItem item1=(SelectedItem)vex.getDataProvider();
                        if(rowId.equalsIgnoreCase(item1.getRowid())) {
                            System.out.print("Row removed");
                            if(!item1.getFormAttachment().equalsIgnoreCase("")) {
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
                                restServiceAdapter.setRequestURI("/webservices/rest/XXEReqService/delete_attachments/");
                                
                                String deleteAttachment = "{\n" + 
                                "  \"DELETE_ATTACHMENTS_Input\" : {\n" + 
                                "   \"RESTHeader\": {\n" + 
                                "    },\n" + 
                                "   \"InputParameters\": {\n" + 
                                "        \"P_SEARCH_RESULT_LINE_ID\" : \""+item1.getFormAttachment()+"\"\n" + 
                                "       }	   \n" + 
                                "   }\n" + 
                                "}";
                                
                                  restServiceAdapter.setRetryLimit(0);
                                  System.out.println("postData===============================" + deleteAttachment);
                                  String response = restServiceAdapter.send(deleteAttachment);
                                  System.out.println("response===============================" + response);
                                }
                                catch(Exception e){
                                             e.printStackTrace();
                                }
                            }
                            vex.removeCurrentRow();
                            SelectedItemsList.items_selected.remove(item1);
                            SelectedItemsList.purchase_items.remove(item1);
                            SelectedItemsList.s_jobs.remove(item1);
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
            
           
            int count=SelectedItemsList.s_jobs.size();
            ValueExpression ve45 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
            if(count==0) {
                
                ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
            }
            else{
                ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
            }
            
            populateCartTotal();
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
            
        }
        
        
        public void populateCartTotal(){
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}"); 
            Double cartValue=0.0;
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
            for(int i=0;i<vex.getTotalRowCount();i++)
             {
                 vex.setCurrentIndex(i);
                 SelectedItem item=(SelectedItem)vex.getDataProvider();
                System.out.println("-------ChecK/uncheck Items in checkout-----"+item.getChecked());
                 if(item.getChecked().equalsIgnoreCase("true")) {
                     if(item.getAmount()!=null && !item.getAmount().equalsIgnoreCase("")){
                        cartValue=cartValue+ Double.parseDouble(item.getAmount());
                     }
                 }
             }
            ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(Math.round(cartValue)));        
            AdfmfJavaUtilities.flushDataChangeEvent();     
    
            
            
        }
        
        
        public void quantityChanged() {
            // Add event code here...
            
            
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}"); 
            Double cartValue=0.0;
            
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
            String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            
            boolean error=false;
          
            for(int i=0;i<vex.getTotalRowCount();i++)
             {
                 vex.setCurrentIndex(i);
                System.out.println("Current Index"+i);
                 SelectedItem item=(SelectedItem)vex.getDataProvider();
                
                System.out.println("Item Details inside qty changed"+item.getProductTitle()+item.getDeliver_to_location());
                 Double d=Double.parseDouble(item.getUnitPrice());
                
                if(itemType.equalsIgnoreCase("goods") && (Double.parseDouble(item.getQuantity())%1==0)){
                 Double qty=Double.parseDouble(item.getQuantity());
                 Double price=d*qty;
                 item.setAmount(String.valueOf(Math.round(price)));
                }
                else{
                    System.out.print("Inside error  case");
                    error=true;
                    item.setAmount("");
                    
                }
                
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
            if(error){
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Quantity cannot be in decimals for item type goods",
                                             null,
                                             null });    
            }
            vex.refresh();
            System.out.print("before populate total");
            populateCartTotal();
            System.out.print("after populate total");
            
        }
     
     
     
        public void needByDateChanged(){
            
            ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.needByDateCheckout}", String.class);
            String ct=(String)ve61.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
            
            for(int i=0;i<vex.getTotalRowCount();i++)
              {
                        vex.setCurrentIndex(i);
                        SelectedItem item=(SelectedItem)vex.getDataProvider();
            try{
                
               
            //--new 9-4-16
                
            if(!item.getNeed_by_date().equalsIgnoreCase("")){
                
                
            System.out.println("==="+item+"======>New Date "+ct);
            
             String arr[]=ct.split("T");
             String date="";
                if(arr.length>1){
                     date=arr[0];    
                }
            
                System.out.println("====XXXX=====> Date "+date);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                
                System.out.println("*******====>item date "+item.getNeed_by_date());
                Date sel_date=sdf.parse(item.getNeed_by_date());
                System.out.println("=========>sel Date "+sel_date);
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
                
                
            }
            catch(Exception e) {
                //e.printStackTrace();
                
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                              "adf.mf.api.amx.addMessage", new Object[] {
                                                              AdfException.ERROR,
                                                              "TEST Inside Exception===> " + sw.toString(),
                                                              null, null
                });
                
                
            }
                    }
            vex.refresh();
        }
        
        
        public void getFirstPageItemsCount(){
            //get 1st page items count
        int pageCount=0;
        
            for(int i=1;i<=pageNo;i++) {
                    for(int k=0;k<ItemsList.items_list.size();k++){
                    Item it=(Item)ItemsList.items_list.get(k);
                    if(it.getPageNo()==i) {
                        pageCount++;
                    }
                }
                pageMap.put(i, pageCount);
            }
            
            System.out.println("First page Count is "+pageCount);
            
        }
        
        public void reorderItemsBasedOnPageNo(){


            for (Map.Entry<Integer,Integer> entry : pageMap.entrySet()) {
              Integer key = entry.getKey();
              Integer value = entry.getValue();
              // do stuff
              System.out.println("key="+key+" value="+value);
            }


             int newPageNo=1;
             boolean pageSizeExceed=false;
            ItemsList.temp_items_list.clear();
            int counter=0;
            System.out.println("Inside reorder");
            for(int i=0;i<ItemsList.items_list.size();i++) {
                Item it=(Item)ItemsList.items_list.get(i);
                
                if(pageMap.get(newPageNo)!=null){
                System.out.println(pageMap.get(newPageNo));
                int sz=pageMap.get(newPageNo);
                if(i<sz) {
                    it.setPageNo(newPageNo);
                }
                else{
                    newPageNo++;
                    it.setPageNo(newPageNo);
                }
                ItemsList.temp_items_list.add(it);       
                
                }
            }
            
            
            
            
//            for(int i=0;i<ItemsList.items_list.size();i++) {
//                Item it=(Item)ItemsList.items_list.get(i);
//                if(i<firstPageCount){
//                    System.out.println("First page Count is "+firstPageCount+" i value is "+i);
//                    it.setPageNo(newPageNo);
//                }
//                else if(i==firstPageCount){
//                    newPageNo++;
//                    System.out.println("Inside else if page no is "+newPageNo);
//                    it.setPageNo(newPageNo);
//                }
//                else{
//                    //check if index is multiples of 20
//                    
//                    if(i%20 ==0) {
//                        it.setPageNo(newPageNo);
//                        
//                        i++;
//                        newPageNo++;
//                    }
//                    
//                    System.out.println("Inside else page no is "+newPageNo);
//                    it.setPageNo(newPageNo);
//                }
//                  
//                ItemsList.temp_items_list.add(it);
//            }
            
            
            
            
            // check if sorted correctly
            ItemsList.items_list.clear();
            
            for(int i=0;i<ItemsList.temp_items_list.size();i++) {
                Item it=(Item)ItemsList.temp_items_list.get(i);
                ItemsList.items_list.add(it);
                 //System.out.println(it.getProductTitle() + " "+it.getPageNo()+" "+it.getUnitPrice());
            }
            
                 
        }
        
        
        public void makeCheckout() {
            // Add event code here...
            System.out.println("+++++++++++Enter into Itemlist Make Checkout++++++++++++");
            ValueExpression ve122 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
            String CartCount=(String)ve122.getValue(AdfmfJavaUtilities.getAdfELContext());
            System.out.println("----Cart---"+CartCount);
     
            try{
            
            boolean isSelected=false;
            boolean isNeedByDateEmpty=false;
            boolean isQuantityEmpty=false;
            boolean isQuantityDecimal=false;
           
            
            //String action="req_submit";
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
            if(vex !=null){
            vex.refresh();
            for(int i=0;i<vex.getTotalRowCount();i++)
            {
                vex.setCurrentIndex(i);
                SelectedItem item=(SelectedItem)vex.getDataProvider();
                System.out.println("***"+item.getProductTitle()+" "+item.getChecked()+"cost center"+item.getCostCenter());
                
                if(item.getChecked().equalsIgnoreCase("true")) {
                    isSelected=true;
                    
                    if(item.getNeed_by_date().equalsIgnoreCase("")){
                        isNeedByDateEmpty=true;
                    }
                }
                
                if(item.getQuantity().equalsIgnoreCase("")){
                    isQuantityEmpty=true;
                }
                if((Double.parseDouble(item.getQuantity())%1!=0)) {
                    isQuantityDecimal=true;
                }
            }
           
           
            if(isSelected){
           
                if(!isNeedByDateEmpty && !isQuantityEmpty && !isQuantityDecimal){
           //System.out.println(vex.getCurrentRowKey());
           System.out.println("********date"+isNeedByDateEmpty+"Qty"+isQuantityEmpty+"QtyDecimal"+isQuantityDecimal);
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

                               ValueExpression ve132 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
                               String multiOrgId = (String)ve132.getValue(AdfmfJavaUtilities.getAdfELContext());

                                    sb.append("{\n");
                                    
                                    sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                                    sb.append("    \"DEVICE_SERIAL_ID\":\"ABCD\",\n");
                                    sb.append("    \"USER_ID\":\""+userName+"\",\n");
                                    sb.append("    \"SEARCH_TYPE\":\""+searchType+"\",\n");
                                    sb.append("    \"SEARCH_TEXT\":\""+productTitle+"\",\n");
                                    sb.append("    \"RESULT_COUNT\":\""+ItemsList.s_jobs.size()+"\",\n");
                                    sb.append("    \"REQUEST_TYPE\":\"REQ\",\n");
                                   sb.append("    \"ORG_ID\":\""+multiOrgId+"\"\n");
                                    sb.append("},");
                   
                               String header_value = sb.substring(0, sb.length() - 1).concat("]");
                               
                               System.out.println("header_value===============================" + header_value);
                               
                               
                               sb = new StringBuffer("[\n");
                               
                               for(int i=0;i<vex.getTotalRowCount();i++)
                               {
                                   vex.setCurrentIndex(i);
                                   SelectedItem item=(SelectedItem)vex.getDataProvider();
                                   GenericType row= (GenericType)vex.getCurrentRow();
          //                         System.out.println("***"+i+" "+row.getAttribute(i)+"location "+row.getAttribute("deliver_to_location")+"  cost "+row.getAttribute("costCenter")+"Natural Account"+row.getAttribute("naturalAccount"));
        //                                   for(int k=0;k<row.getAttributeCount();k++){
        //                                       System.out.println("***"+k+" "+row.getAttribute(k)+"location "+row.getAttribute("deliver_to_location")+"  cost "+row.getAttribute("costCenter"));
        //
        //                                   }
        //                                   System.out.println("***"+item.getProductTitle()+" "+item.getChecked()+"---"+item.getDeliver_to_location()+"---"+item.getCostCenter()+"***");
                                   
                                   if(item.getChecked().equalsIgnoreCase("true")) {
                                       
                                       sb.append("{\n");
                                       
                                       sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
                                       if(item.getSource().equalsIgnoreCase("Contracted")){
                                       sb.append("    \"PRODUCT_SOURCE\":\"O\",\n");
                                       }
                                       else if(item.getSource().equalsIgnoreCase("U")) {
                                           sb.append("    \"PRODUCT_SOURCE\":\"U\",\n");    
                                       }
                                       else{
                                           sb.append("    \"PRODUCT_SOURCE\":\"I\",\n");
                                       }
                                       
                                      
                                     
                                       
                                       
                                       
                                       sb.append("    \"PRODUCT_CATEGORY\":\""+item.getProductCategory()+"\",\n");
                                       sb.append("    \"PRODUCT_TITLE\":\""+item.getProductTitle().replaceAll("\"", "\\\\\"")+"\",\n");
                                       sb.append("    \"QUANTITY\":\""+item.getQuantity()+"\",\n");
                                       sb.append("    \"UOM_CODE\":\""+item.getUom()+"\",\n");
                                       sb.append("    \"UNIT_PRICE\":\""+item.getUnitPrice()+"\",\n");
                                       sb.append("    \"CURRENCY_CODE\":\"USD\",\n");
                                       
                                       DeliverToLocation loc;
                                      // System.out.println("deliver to location list size is "+deliverToLocationList.size());
        //                                       if(!item.getDeliver_to_location().equalsIgnoreCase("")){
        //                                           System.out.println("deliver to location list size is "+item.getDeliver_to_location());
        //                                          loc=(DeliverToLocation)deliverToLocationList.get((Integer.parseInt(item.getDeliver_to_location())-1));
        //                                       }
        //                                       else{
        //                                           loc=(DeliverToLocation)deliverToLocationList.get(0);
        //                                       }
                                       
                                       if(row.getAttribute("deliver_to_location").toString().equalsIgnoreCase("")){
                                           loc=(DeliverToLocation)deliverToLocationList.get(0);
                                       }
                                       else{
                                       loc=(DeliverToLocation)deliverToLocationList.get(Integer.parseInt(row.getAttribute("deliver_to_location").toString()));
                                       }
                                       
                                       System.out.println("Deliver To location"+loc.getDescription());
                                       
                                       sb.append("    \"DELIVER_TO_LOCATION\":\""+loc.getCode()+"\",\n");
                                       String arr[]=item.getNeed_by_date().split("T");
                                       sb.append("    \"NEED_BY_DATE\":\""+arr[0]+"\",\n");
                                       sb.append("    \"BPA_NUM\":\"\",\n");
                                       sb.append("    \"VENDOR_NAME\":\""+item.getVendorName()+"\",\n");
                                       sb.append("    \"VENDOR_SITE\":\""+item.getVendorSiteCode()+"\",\n");
                                       sb.append("    \"VENDOR_SITE_ADDRESS1\":\"\",\n");
                                       sb.append("    \"VENDOR_SITE_ADDRESS2\":\"\",\n");
                                       sb.append("    \"VENDOR_SITE_ADDRESS3\":\"\",\n");
                                       sb.append("    \"VENDOR_SITE_CITY\":\"\",\n");
                                       sb.append("    \"VENDOR_SITE_STATE\":\"\",\n");
                                       sb.append("    \"VENDOR_SITE_ZIP\":\"\",\n");
                                       sb.append("    \"VENDOR_SITE_COUNTRY\":\"\",\n");
                                       sb.append("    \"VENDOR_CONTACT_FIRST_NAME\":\""+item.getVendorFname()+"\",\n");
                                       sb.append("    \"VENDOR_CONTACT_LAST_NAME\":\""+item.getVendorLname()+"\",\n");
                                       sb.append("    \"VENDOR_CONTACT_PHONE\":\""+item.getVendorContact()+"\",\n");
                                       sb.append("    \"VENDOR_CONTACT_EMAIL\":\""+item.getVendorEmail()+"\",\n");
                                       sb.append("    \"SELECTED_FLAG\":\"Y\",\n");
                                       sb.append("    \"ITEM_TYPE\":\""+item.getItemType()+"\",\n");
                                       
                      //                 CostCenter c;
        //                                       if(!item.getCostCenter().equalsIgnoreCase("")){
        //                                         c=(CostCenter)costCenterList.get((Integer.parseInt(item.getCostCenter())));
        //                                       }
        //                                       else{
        //                                           c=(CostCenter)costCenterList.get(0);
        //                                       }
                                       
                                   /*    if(row.getAttribute("costCenter").toString().equalsIgnoreCase("")){
                                           c=(CostCenter)costCenterList.get(0);
                                       }
                                       else{
                                           c=(CostCenter)costCenterList.get(Integer.parseInt(row.getAttribute("costCenter").toString()));
                                       }
                                       
                                       System.out.println("CostCenter with Natural Account:"+c.getDescription());
                                       
                                       //Cost Center with Natural Accounts
                                       CostCenterNaturalAccounts ccNacc;
                                       if(row.getAttribute("costCenterNaturalAccount").toString().equalsIgnoreCase("")){
                                           ccNacc=(CostCenterNaturalAccounts)costCenterNaturalAccountsList.get(0);
                                       }
                                       else{
                                           ccNacc=(CostCenterNaturalAccounts)costCenterNaturalAccountsList.get(Integer.parseInt(row.getAttribute("costCenterNaturalAccount").toString()));
                                       }
                                       System.out.println("CostCenter with Natural Account:"+ccNacc.getCostCenter()+"  "+ ccNacc.getNaturalAccount());
                                       //Natural Accounts
                                   /*    NaturalAccounts nAcc;
                                       if(row.getAttribute("naturalAccount").toString().equalsIgnoreCase("")){
                                           nAcc=(NaturalAccounts)naturalAccountList.get(0);
                                       }
                                       else{
                                           nAcc=(NaturalAccounts)naturalAccountList.get(Integer.parseInt(row.getAttribute("naturalAccount").toString()));
                                       }*/
                                       
                                       
                                       sb.append("    \"COST_CENTER\":\""+item.getCostCenter()+"\",\n");
                                       sb.append("    \"NATURAL_ACCOUNT\":\""+item.getNaturalAccount()+"\",\n");
                                       
                                       sb.append("    \"ITEM_NUMBER\":\""+item.getItemNo()+"\",\n");
                                       sb.append("    \"VENDOR_PART_NUM\":\""+item.getSupplierpartNo()+"\",\n");
                                       sb.append("    \"MAX_ESTIMATED_PRICE \":\""+item.getMaxEstPrice()+"\",\n");
                                       sb.append("    \"INTERNAL_REFERENCE_NUM\":\""+item.getInternalRefNo()+"\",\n");
                                       sb.append("    \"LINE_REQUEST_TYPE\":\""+item.getLineReqType()+"\",\n");
                                       sb.append("    \"SEARCH_RESULT_LINE_ID\":\""+item.getFormAttachment()+"\",\n");
                                       sb.append("    \"CHARGE_ACCOUNT\":\"\",\n");
                                       sb.append("    \"MARKUP_PRICE\":\"\",\n");
                                       sb.append("    \"REQUISITION_HEADER_ID\":\"\",\n");
                                       sb.append("    \"REQUISITION_LINE_ID\":\"\",\n");
                                       sb.append("    \"COMMENTS\":\"\",\n");
                                       sb.append("    \"ATTACHMENT_FILE\":\"\",\n");
                                       
                                       
                                       if(item.getSource().equalsIgnoreCase("Contracted")){                                    
                                        sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"N\",\n");
                                       }
                                       else{
                                           ValueExpression isContractedItemPresent = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.isContractedItemPresent}", String.class);
                                           String isContractedAvailable=(String)isContractedItemPresent.getValue(AdfmfJavaUtilities.getAdfELContext());
                                           if(isContractedAvailable.equalsIgnoreCase("true")){
                                               sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"Y\",\n");
                                           }
                                           else{
                                               sb.append("    \"CONTRACT_ITEM_EXCEPTION\":\"N\", \n");
                                           }
                                           
                                       }
                                       sb.append("    \"INDIX_CATEGORY_ID\":\""+item.getIndixCategoryId()+"\",\n");
                                       sb.append( " \"PRODUCT_ATTRIBUTE_TL\" : {\"PRODUCT_ATTRIBUTE_TL_ITEM\" : ["+item.getIndixAttributes()+"]}");
                                       
                                       //sb.append("    \"PRODUCT_ATTRIBUTE\":\""+item.getIndixAttributes()+"\"\n");
                                       
                                       sb.append("},");
                                       
                                       SelectedItemsList.purchase_items.remove(item);
                                       SelectedItemsList.items_selected.remove(item);
                                       SelectedItemsList.s_jobs.remove(item);
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
                               "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"\n" + 
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
                               
                               int count=SelectedItemsList.s_jobs.size();
                               
                               ValueExpression ve45 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
                               if(count==0) {
                                   
                                   ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
                               }
                               else{
                                   ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
                               }
                               AdfmfJavaUtilities.flushDataChangeEvent();
                               
        //                               AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
        //                                                                                              "adf.mf.api.amx.doNavigation", new Object[] { "__back" });

                          
                          
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
                    
                    if(isQuantityEmpty){
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                     AdfmfJavaUtilities.getFeatureName(),
                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                     "Quantity cannot be empty",
                                                     null,
                                                     null }); 
                    }
                    if(isQuantityDecimal){
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                     AdfmfJavaUtilities.getFeatureName(),
                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                     "Quantity for item type goods cannot be decimal",
                                                     null,
                                                     null }); 
                    }
                    
                    if(isNeedByDateEmpty){
                        
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                     AdfmfJavaUtilities.getFeatureName(),
                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                     "Need by date cannot be empty",
                                                     null,
                                                     null }); 
                    }
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
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
        // Checkout Notification
        
        public void makeRequesition() {
         // Add event code here...
         
         try{
         
         boolean isSelected=false;
         boolean isNeedByDateEmpty=false;
         boolean isQuantityEmpty=false;
         boolean isQuantityDecimal=false;
         boolean isDeliverToLocationEmpty=false;
         boolean isCostCenterEmpty=false;
         boolean isGLAccountEmpty=false;    
         
         //String action="req_submit";
         
         BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
         if(vex !=null){
         vex.refresh();
         for(int i=0;i<vex.getTotalRowCount();i++)
         {
             vex.setCurrentIndex(i);
             SelectedItem item=(SelectedItem)vex.getDataProvider();
             System.out.println("***"+item.getProductTitle()+" "+item.getChecked()+"Deliver To Location"+item.getDeliver_to_location());
             
             if(item.getChecked().equalsIgnoreCase("true")) {
                 isSelected=true;
                 
                 if(item.getNeed_by_date().equalsIgnoreCase("")){
                     isNeedByDateEmpty=true;
                 }
                 if(item.getDeliver_to_location().equalsIgnoreCase("") || item.getDeliver_to_location().equalsIgnoreCase("0")){
                     isDeliverToLocationEmpty=true;
                 }
                 if(item.getCostCenter().equalsIgnoreCase("")) {
                     isCostCenterEmpty=true;
                 }
                 if(item.getNaturalAccount().equalsIgnoreCase("") || item.getNaturalAccount().equalsIgnoreCase(" ")) {
                     isGLAccountEmpty=true;
                 }
             }
             
             if(item.getQuantity().equalsIgnoreCase("")){
                 isQuantityEmpty=true;
             }
             
             
             if((Double.parseDouble(item.getQuantity())%1!=0)) {
                 isQuantityDecimal=true;
             }
         }
        
        
         if(isSelected){
        
             if(!isNeedByDateEmpty && !isQuantityEmpty && !isQuantityDecimal && !isDeliverToLocationEmpty && !isCostCenterEmpty && !isGLAccountEmpty){
        //System.out.println(vex.getCurrentRowKey());
        System.out.println("********date"+isNeedByDateEmpty+"Qty"+isQuantityEmpty+"QtyDecimal"+isQuantityDecimal);
                 AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                            "checkoutSelection",
                                                                            new Object[] { });
             }
        
        
             else{
                 
                 if(isQuantityEmpty){
                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                  AdfmfJavaUtilities.getFeatureName(),
                                                  "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                  "Quantity cannot be empty",
                                                  null,
                                                  null }); 
                 }
                 if(isQuantityDecimal){
                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                  AdfmfJavaUtilities.getFeatureName(),
                                                  "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                  "Quantity for item type goods cannot be decimal",
                                                  null,
                                                  null }); 
                 }
                 
                 if(isNeedByDateEmpty){
                     
                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                  AdfmfJavaUtilities.getFeatureName(),
                                                  "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                  "Need by date cannot be empty",
                                                  null,
                                                  null }); 
                 }
                 if(isDeliverToLocationEmpty){
                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                  AdfmfJavaUtilities.getFeatureName(),
                                                  "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                  "Please Choose Your Deliver To Location",
                                                  null,
                                                  null }); 
                     
                 }
                 if(isCostCenterEmpty){
                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                  AdfmfJavaUtilities.getFeatureName(),
                                                  "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                  "Please Choose Your Cost Center",
                                                  null,
                                                  null }); 
                     
                 }
                 if(isGLAccountEmpty){
                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                  AdfmfJavaUtilities.getFeatureName(),
                                                  "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                  "Please Choose Your GL Account",
                                                  null,
                                                  null }); 
                     
                 }
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
             
         }
         catch(Exception e){
             e.printStackTrace();
         }
        }
        //End Checkout Notification

        
        public void onDeliverToLocationChange(){
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
            SelectedItem item=(SelectedItem)vex.getDataProvider();
            
             String locationId=item.getDeliver_to_location(); 
             DeliverToLocation loc=(DeliverToLocation)deliverToLocationList.get(Integer.parseInt(locationId));
             item.setDeliver_to_location(loc.getCode());
            GenericType row= (GenericType)vex.getCurrentRow();
            row.setAttribute("deliver_to_location", loc.getCode());
                  
            vex.refresh();
        }
        
        public void onCostCenterChange() {
            // Add event code here...
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
            SelectedItem item=(SelectedItem)vex.getDataProvider();
            
             String costCenterId=item.getCostCenter(); 
             CostCenter c=(CostCenter)costCenterList.get(Integer.parseInt(costCenterId));
             item.setCostCenter(c.getName());
            GenericType row= (GenericType)vex.getCurrentRow();
            row.setAttribute("costCenter", c.getName());
            vex.refresh();
        }
        public void rowCostCenterChange() {
               BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
               ValueExpression ve_row = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartRowId}", String.class);
               String rowId=(String)ve_row.getValue(AdfmfJavaUtilities.getAdfELContext());
               ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costCenterValue}", String.class);
               String costCenterItem=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
               ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCostCenterText}", String.class);
               ve_searchText.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
               
                   
               System.out.println("new Cost Center --->"+costCenterItem);
               
               for(int i=0;i<vex.getTotalRowCount();i++)
                {
                    vex.setCurrentIndex(i);
                    SelectedItem item=(SelectedItem)vex.getDataProvider();
                    if(rowId.equalsIgnoreCase(item.getRowid()))
                    {
                    item.setCostCenter(String.valueOf(costCenterItem));
                    item.setNaturalAccount(" ");
                   }
                   System.out.println("new Item Cost Center"+item.getCostCenter());    
                }
               vex.refresh();
        }
        public void getGLAccountValue() {
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression vemul = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String multiOrgId=(String)vemul.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchGLAccountText}", String.class);
            String searchText=(String)ve_searchText.getValue(AdfmfJavaUtilities.getAdfELContext());
            System.out.println("Search Txt-->"+searchText+"Cost Center List"+CostCenterList.s_jobs.size()+"Deliver To Location"+DeliverToLocationList.s_jobs.size()+"Natural Account"+NaturalAcccountList.acc_List.size());
            
            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
            ValueExpression ve_row = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartRowId}", String.class);
            String rowId=(String)ve_row.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            
            System.out.println("Get Total RowCount Values-->"+vex.getTotalRowCount());
            
            String costSeg="";
            
            for(int i=0;i<vex.getTotalRowCount();i++)
             {
                 vex.setCurrentIndex(i);
                 System.out.println("Get Total RowCount  Before Selected Item Values-->"+vex.getTotalRowCount());
                 SelectedItem item=(SelectedItem)vex.getDataProvider();
                 System.out.println("Get Total RowCount  After Selected Item Values-->"+vex.getTotalRowCount());
                 if(rowId.equalsIgnoreCase(item.getRowid()))
                 {
                 costSeg = item.getCostCenter();
                 
                }
                System.out.println("new Item Cost Center"+item.getCostCenter());    
             }
            
            System.out.println("new Item Cost Center"+costSeg+"Cost Center List size"+CostCenterList.s_jobs.size());

            String costCenterCode="";
            
            for(int k=0;k<CostCenterList.s_jobs.size();k++) {
                CostCenter c=(CostCenter)CostCenterList.s_jobs.get(k);
                System.out.println("Cost Center Name"+c.getName()+"Cost Center Description"+c.getDescription());
                
                if(c.getDescription().equalsIgnoreCase(costSeg))
                {
                    costCenterCode=c.getName();
                System.out.println("Match occurs "+c.getDescription());
                }
                
            }
            
            System.out.println("Code for Costcenter"+costCenterCode);
            // Natural Accounts
            try{
                List naturalAccountList = new ArrayList();
                
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
                           JSONObject  data=output.getJSONObject("X_NATURAL_ACC_TL");
                            NaturalAcccountList.acc_List.clear();
                            naturalAccountList.clear();
                            
                            if(data.get("X_NATURAL_ACC_TL_ITEM") instanceof  JSONArray){
                              JSONArray segments=data.getJSONArray("X_NATURAL_ACC_TL_ITEM");
                              for(int i=0;i<segments.length();i++) {
                                  //String name=(String)segments.get(i);
                                  JSONObject na=(JSONObject)segments.get(i);
                                  String name=na.getString("SEGMENT_VALUE");
                                  String description=na.getString("DESCRIPTION");
                                  
                                  NaturalAccounts n=new NaturalAccounts(name,description);
                                  NaturalAcccountList.acc_List.add(n);
                                  naturalAccountList.add(n);
                                  
                              }
                            
                            }
                            
                            else if(data.get("X_NATURAL_ACC_TL_ITEM") instanceof  JSONObject){
                               
                               JSONObject na=data.getJSONObject("X_NATURAL_ACC_TL_ITEM");
                                String name=na.getString("SEGMENT_VALUE");
                                String description=na.getString("DESCRIPTION");
                               
                                NaturalAccounts n=new NaturalAccounts(name,description);
                                NaturalAcccountList.acc_List.add(n);
                                naturalAccountList.add(n);
                               
                            }
                              
                                AmxAttributeBinding accountList = (AmxAttributeBinding) AdfmfJavaUtilities
                                                  .evaluateELExpression("#{bindings.naturalAccounts}");
                                AmxIteratorBinding accountListIterator =  accountList.getIteratorBinding();
                                accountListIterator.refresh();
                            
                            }
                            catch(Exception e) {
                                e.printStackTrace();
                            }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
            
        }
        
        public void rowGLAccountChange(){
             BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
             ValueExpression ve_row = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartRowId}", String.class);
             String rowId=(String)ve_row.getValue(AdfmfJavaUtilities.getAdfELContext());
             ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.GLAccountValue}", String.class);
             String GLItem=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
             
             ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchGLAccountText}", String.class);
             ve_searchText.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
             
             System.out.println("GL Account --->"+GLItem);
             
             for(int i=0;i<vex.getTotalRowCount();i++)
              {
                  vex.setCurrentIndex(i);
                  SelectedItem item=(SelectedItem)vex.getDataProvider();
                  if(rowId.equalsIgnoreCase(item.getRowid()))
                  {
                  item.setNaturalAccount(String.valueOf(GLItem));
                 }
                 System.out.println("new Item Cost Center"+item.getNaturalAccount());    
              }
             vex.refresh();
            
        }
        
        public void showCartFreeForm() {
            
          /*  System.out.println("Show Cart Free Form--->"+SelectedItemsList.s_jobs.size());
            Double cartValue=0.0;
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
            for(int i=0;i<SelectedItemsList.s_jobs.size();i++)
             {
                 SelectedItem item=(SelectedItem)SelectedItemsList.s_jobs.get(i);
                 //System.out.println("item-- Qty"+item.getQuantity()+item.getAmount());
                 cartValue=cartValue+ (Double.parseDouble(item.getAmount())*Integer.parseInt(item.getQuantity()));
                 
             }
            ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(Math.round(cartValue)));        
            AdfmfJavaUtilities.flushDataChangeEvent();  
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                               "adf.mf.api.amx.doNavigation", new Object[] { "checkout" });*/
            
            //Old Cart
            
            Double cartValue=0.0;
            
            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
            String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
            //SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
            int count=SelectedItemsList.s_jobs.size();
            
            String default_cost_center_id="";
            String default_cost_center_name="";
            String default_deliver_to_location_id="";
            String default_deliver_to_location_name="";
            
            String default_natural_account_seg="";
            String default_natural_account_description="";
            
            if(count>0){
            if(itemType!=null && !itemType.equalsIgnoreCase(""))  {
            try{
                
                
                
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
            String multiOrgId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());

            
            //             ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
            //             default_cost_center_id = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());
            //
            //             ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to}", String.class);
            //             default_deliver_to_location_id = (String)ve49.getValue(AdfmfJavaUtilities.getAdfELContext());
                
                
                
                
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
                 
                String response = restServiceAdapter.send(postData);
                 
                 System.out.println("response===============================" + response); 
                 JSONObject resp=new JSONObject(response);
                 JSONObject output=resp.getJSONObject("OutputParameters");
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
                      if(name.equalsIgnoreCase(default_cost_center_id)) {
                          default_cost_center_name=String.valueOf(i);
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
                    if(name.equalsIgnoreCase(default_cost_center_id)) {
                        default_cost_center_name=String.valueOf(0);
                    }
                    CostCenter c=new CostCenter(name,description);
                    CostCenterList.s_jobs.add(c);
                    costCenterList.add(c);
                   
                }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            


            // Natural Accounts
            
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
                      if(name.equalsIgnoreCase(default_natural_account_seg)) {
                          default_natural_account_description=String.valueOf(i);
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
                    if(name.equalsIgnoreCase(default_natural_account_seg)) {
                        default_natural_account_description=String.valueOf(0);
                    }
                    NaturalAccounts c=new NaturalAccounts(name,description);
                    NaturalAcccountList.acc_List.add(c);
                    naturalAccountList.add(c);
                   
                }
                 /*   AmxAttributeBinding accountList = (AmxAttributeBinding) AdfmfJavaUtilities
                                      .evaluateELExpression("#{bindings.naturalAccounts}");
                    AmxIteratorBinding accountListIterator =  accountList.getIteratorBinding();
                    accountListIterator.refresh();*/
                
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
                
                
                
            
            
                for(int i=0;i<SelectedItemsList.s_jobs.size();i++)
                 {
                     SelectedItem item=(SelectedItem)SelectedItemsList.s_jobs.get(i);
                     cartValue=cartValue+ Double.parseDouble(item.getAmount());
                     
                 }
                
                ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
                        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(Math.round(cartValue)));        
                        AdfmfJavaUtilities.flushDataChangeEvent();   
                
            //            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
            //            vex.refresh();
                    
                    
            
            
            // return "checkout";
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                                   "adf.mf.api.amx.doNavigation", new Object[] { "checkout" });    
            
            }
            else{
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Item Type is mandatory and cannot be empty.",
                                             null,
                                             null });    
                
            }
            
            }
            else{
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                             AdfmfJavaUtilities.getFeatureName(),
                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                             "Your cart is empty.",
                                             null,
                                             null });    
               
            }
            
        }

    }