package mobile;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.text.SimpleDateFormat;

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

import java.io.BufferedReader;

import java.io.File;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;

import javax.el.MethodExpression;

import javax.el.ValueExpression;

import mobile.CostCenter;
import mobile.CostCenterList;
import mobile.DatabaseHelper;
import mobile.DeliverToLocation;
import mobile.DeliverToLocationList;
import mobile.Item;
import mobile.ItemsList;
import mobile.SelectedItem;
import mobile.SelectedItemsList;

import mobile.SupplierList;
import mobile.UOM;

import mobile.UOMList;

import mobile.Alias;
import mobile.AliasList;

import oracle.adf.model.datacontrols.device.DeviceManager;
import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

import oracle.adf.model.datacontrols.device.Location;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.RangeChangeEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.FeatureContext;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;

import org.json.JSONArray;
import org.json.JSONObject;


public class Rest {
    boolean isPopupOpen = false;
    public Rest() {
    }
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List items;
    DatabaseHelper dh=new DatabaseHelper();
    String categoryText1="";
    String categoryText2="";
    String categoryText3="";
    String categoryText4="";
    public static String itemCategories="",quantity="",needByDate="",itemType="",resultCount="";
    public static boolean found=false;
    public static int pageNo=1;
    public static String search;
    public static ArrayList<String> selectedImages = new ArrayList<String>(); 
    ArrayList<Integer> selectedSuppliers = new ArrayList<Integer>(); 
    
    public static ArrayList<ServiceLocation> serviceLocationList = new ArrayList<ServiceLocation>();    
    public static ArrayList<Lookup> contractLookupList = new ArrayList<Lookup>(); 
    public static ArrayList<Lookup> frequencyPeriodList = new ArrayList<Lookup>(); 
    public static ArrayList<Lookup> serviceFrequencyTypeList = new ArrayList<Lookup>();
    public static ArrayList<Alias> aliasList = new ArrayList<Alias>();
    public static ArrayList<Rejection> rejectionReasonList = new ArrayList<Rejection>();
    
    public static List costCenterList=new ArrayList();
    
/* valid login
    public void validateLogin(ActionEvent actionEvent) {
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
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/validate_user_login/");
        String postData= "{\n" + 
        "  \"VALIDATE_USER_LOGIN_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/validate_user_login/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "        	   \"P_USER\":\""+userName+"\",\n" + 
        "        	   \"P_PWD\":\""+password+"\"\n" + 
        "       }	   \n" + 
        "   }\n" + 
        "}\n";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
            
           String response = restServiceAdapter.send(postData);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
             String status=output.getString("X_RESULT");
        
             if(status.equalsIgnoreCase("Y")) {
                 
                 
                 
                 String user_id=output.getString("X_USER_ID");
                     
                 ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
                 ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),userName);
                 
                 ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
                 ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),user_id);
                 
                 
                 ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.uuid}", String.class);
                 String device_serial_id=(String)ve14.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.platform}", String.class);
                 String device_type=(String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.model}", String.class);
                 String device_model=(String)ve16.getValue(AdfmfJavaUtilities.getAdfELContext());
                 
                 
                 
                 //register the device related info to oracle
                 
                 restServiceAdapter = Model.createRestServiceAdapter();
                         // Clear any previously set request properties, if any
                         restServiceAdapter.clearRequestProperties();
                         // Set the connection name
                         restServiceAdapter.setConnectionName("enrich");
                         
                         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/register_user/");
                         postData= "{\n" + 
                         "\n" + 
                         "  \"REGISTER_USER_Input\" : {\n" + 
                         "\n" + 
                         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/register_user/\",\n" + 
                         "\n" + 
                         "   \"RESTHeader\": {\n" + 
                         "\n" + 
                         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                         "    },\n" + 
                         "\n" + 
                         "   \"InputParameters\": {\n" + 
                         "\n" + 
                         "        \"P_USER_ID\" : \""+user_id+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_SERIAL_ID\" : \""+device_serial_id+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_TYPE\" : \""+device_type+"\",\n" + 
                         "\n" + 
                         "        \"P_DEVICE_MODEL\" : \""+device_model+"\",\n" + 
                         "\n" + 
                         "        \"P_TOKEN\" : \"29119201\"\n" + 
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
                 
                 
                 
                 //get user preferences
                           
                  String homeScreen="";         
                           
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
                 "                   \"P_USER_ID\" :"+user_id+"\n" + 
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
                      JSONObject data=output.getJSONObject("X_USER_PREFERENCE_TL");
                     if(data.get("X_USER_PREFERENCE_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_USER_PREFERENCE_TL_ITEM");
                       for(int i=0;i<segments.length();i++) {
                           JSONObject pref= segments.getJSONObject(i);
                           if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("HOME_PAGE")) {
                               
                               homeScreen=pref.getString("ATTRIBUTE_VALUE");
                           }
                       }
                     
                     }
                     
                     else if(data.get("X_USER_PREFERENCE_TL_ITEM") instanceof  JSONObject){
                        
                        JSONObject segments=data.getJSONObject("X_USER_PREFERENCE_TL_ITEM");
                         JSONObject pref= segments;
                         if(pref.getString("ATTRIBUTE_NAME").equalsIgnoreCase("HOME_PAGE")) {
                             
                             homeScreen=pref.getString("ATTRIBUTE_VALUE");
                         }
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
                 
                 
                 
                 
                 
                 
                 
                 ////////////////
                 
                 if(homeScreen.equalsIgnoreCase("req_sts")){
                     AdfmfContainerUtilities.gotoFeature("mp.Requisition"); 
                 }
                 else if(homeScreen.equalsIgnoreCase("qts_screen")){
                     AdfmfContainerUtilities.gotoFeature("mp.Quotation"); 
                 }
                 else{
                     
                     AdfmfContainerUtilities.gotoFeature("feature1");
//                     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
//                                                                                    "adf.mf.api.amx.doNavigation", new Object[] { "valid_login" });    
                 }
                 
                 
//                  AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
//                                                                                    "adf.mf.api.amx.doNavigation", new Object[] { "valid_login" });   
//                 
                
                 
                 
                 
             }
             else{
                 AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                              AdfmfJavaUtilities.getFeatureName(),
                                              "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                              "Invalid UserName or Password",
                                              null,
                                              null });
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

*/
    public void doSearch() {
        // Add event code here...
        
//        ValueExpression ve91 = AdfmfJavaUtilities.getVa
// lueExpression("#{applicationScope.rdItemType}", String.class);
//        itemType=(String)ve91.getValue(AdfmfJavaUtilities.getAdfELContext());
//        pageNo=1;
//        
//       
//        if(itemType!=null && !itemType.equalsIgnoreCase("")) {
//        
//        
//        try{
//            
//            
//            
//            ItemsList.s_jobs.clear();
//           
//            ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
//            search = (String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext()); 
//            
//            
//            
//                
//            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
//            ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), "S");
//            
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.user_name}", String.class);
//        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.password}", String.class);
//        String password = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
//            RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
//            String response="";
//            String postData="";
//            JSONObject resp=new JSONObject();
//            JSONObject output=new JSONObject();
//            int rowCount=0;
//            int size=0;
//            try{
//        
//        
//        // Clear any previously set request properties, if any
//        restServiceAdapter.clearRequestProperties();
//        // Set the connection name
//        restServiceAdapter.setConnectionName("enrich");
//        
//        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//        restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/search_products/");
//
//            
//                     postData= "{\n" + 
//                    "  \"SEARCH_PRODUCTS_Input\" : {\n" + 
//                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/search_products/\",\n" + 
//                    "   \"RESTHeader\": {\n" + 
//                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//                    "    },\n" + 
//                    "   \"InputParameters\": {\n" + 
//                    "        \"P_SEARCH_ATTRIBS\": \n" + 
//                    "            {\n" + 
//                    "             \"SEARCH_TEXT\": \""+search+"\",\n" + 
//                    "             \"ITEM_TYPE\": \""+itemType+"\"\n" +              
//                    "            }\n" + 
//                    "      }\n" + 
//                    "   }\n" + 
//                    "}\n";
//            
//            
//            
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//            
//           response = restServiceAdapter.send(postData);
//            
//            System.out.println("response===============================" + response);
//           resp=new JSONObject(response);
//                
//           output=resp.getJSONObject("OutputParameters");
//            if(!output.getString("X_ERROR_MESSAGE").equalsIgnoreCase("No products found")){
//                rowCount=1;
//           JSONObject results=output.getJSONObject("X_SEARCH_RESULT_TBL");
//           
//           if(results.get("X_SEARCH_RESULT_TBL_ITEM") instanceof JSONArray){
//           
//           JSONArray body=results.getJSONArray("X_SEARCH_RESULT_TBL_ITEM");
//           for(int i=0;i<body.length();i++) {
//                JSONObject item=body.getJSONObject(i);   
//               String poNo=item.getString("PO_NUMBER");
//                String vendorName=item.getString("VENDOR_NAME");
//                String vendorSiteCode=item.getString("VENDOR_SITE_CODE");
//                String productCategory=item.getString("PRODUCT_CATEGORY");
//                String productTitle=item.getString("PRODUCT_TITLE");
//                String unitPrice=item.getString("UNIT_PRICE");
//                String uom=item.getString("UOM");
//                String source=item.getString("CONTRACTED");
//               //diverseSupplier
//                String diverseSupplier=item.getString("DIVERSE_SUPPLIER");
//               
//                Random randomGenerator = new Random();
//                int randomInt = randomGenerator.nextInt(1000000000);
//               
//               String showDiverSeImage="true";
//               String diverseImageURL="/images/contract.png";
//               if(diverseSupplier.equalsIgnoreCase("1")) {
//                   diverseImageURL="/images/diverse_contract.png";
//               }
//               
//                Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,"","/images/uncheck.png","Contracted",uom,String.valueOf(randomInt),diverseSupplier,showDiverSeImage,diverseImageURL,pageNo);
//               // ItemsList.s_jobs.add(j); 
//                ItemsList.items_list.add(j); 
//               
//            }
//           }
//           else if(results.get("X_SEARCH_RESULT_TBL_ITEM") instanceof JSONObject){
//               
//               JSONObject item=results.getJSONObject("X_SEARCH_RESULT_TBL_ITEM");   
//               String poNo=item.getString("PO_NUMBER");
//               String vendorName=item.getString("VENDOR_NAME");
//               String vendorSiteCode=item.getString("VENDOR_SITE_CODE");
//               String productCategory=item.getString("PRODUCT_CATEGORY");
//               String productTitle=item.getString("PRODUCT_TITLE");
//               String unitPrice=item.getString("UNIT_PRICE");
//               String uom=item.getString("UOM");
//               String source=item.getString("CONTRACTED");
//               String diverseSupplier=item.getString("DIVERSE_SUPPLIER");
//               
//               Random randomGenerator = new Random();
//               int randomInt = randomGenerator.nextInt(1000000000);
//               String showDiverSeImage="true";
//               String diverseImageURL="/images/contract.png";
//               if(diverseSupplier.equalsIgnoreCase("1")) {
//                   diverseImageURL="/images/diverse_contract.png";
//               }
//               Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,"","/images/uncheck.png","Contracted",uom,String.valueOf(randomInt),diverseSupplier,showDiverSeImage,diverseImageURL,pageNo);
//               //ItemsList.s_jobs.add(j); 
//               ItemsList.items_list.add(j); 
//           }
//           
//           
//            
//            providerChangeSupport.fireProviderRefresh("assets");
//            }
//            
//            }
//            catch(Exception e){
//                e.printStackTrace();
//                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                             AdfmfJavaUtilities.getFeatureName(),
//                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                             "Cannot connect to Services on Oracle Server.",
//                                             null,
//                                             null }); 
//            }
//            
//            
//            //since indix doesn't have services search we can restrict the search 
//            if(itemType.equalsIgnoreCase("goods")){
//            
//            try{
//            
//        //            restServiceAdapter = Model.createRestServiceAdapter();
//        //                    // Clear any previously set request properties, if any
//        //                    restServiceAdapter.clearRequestProperties();
//        //                    // Set the connection name
//        //                    restServiceAdapter.setConnectionName("indix");
//        //
//        //                    restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_GET);
//        //                    restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//        //                    restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//        //                    restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//        //                    restServiceAdapter.setRequestURI("/v2/summary/products?countryCode=US&q="+search+"&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc");
//        //                    restServiceAdapter.setRetryLimit(0);
//        //                       System.out.println("postData===============================" + postData);
//        //
//        //                    response = restServiceAdapter.send(postData);
//        //
//        //            resp=new JSONObject(response.toString());
//        //            output=resp.getJSONObject("result");
//        //            JSONArray resArr=output.getJSONArray("products");
//        //            System.out.println("***********");
//        //            for(int i=0;i<resArr.length();i++) {
//        //                System.out.println("***********");
//        //                JSONObject productObj=resArr.getJSONObject(i);
//        //
//        //                String poNo="";
//        //                 String vendorName="";
//        //                 String vendorSiteCode="";
//        //                 String productCategory=productObj.getString("categoryName");
//        //                 String productTitle=productObj.getString("title");
//        //                 String unitPrice=productObj.getString("minSalePrice");
//        //                 String imageUrl=productObj.getString("imageUrl");
//        //
//        //                 Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each");
//        //                 ItemsList.s_jobs.add(j);
//        //                System.out.println("***********");
//        //
//        //            }
//            
//            
//            ////
//                
//                        size=ItemsList.items_list.size()+500;
//                       
//                       // System.out.println("response===============================" + response);
//                      
//                       String url = "https://api.indix.com/v2/offersPremium/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+"&availability=IN_STOCK&lastRecordedIn=30&pageSize=10&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
//                                       
//                                       URL obj = new URL(url);
//                                       HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            
//                                       // optional default is GET
//                                       con.setRequestMethod("GET");
//            
//                                       //add request header
//                                      
//            
//                                       int responseCode = con.getResponseCode();
//                                       System.out.println("\nSending 'GET' request to URL : " + url);
//                                       System.out.println("Response Code : " + responseCode);
//            
//                                       BufferedReader in = new BufferedReader(
//                                               new InputStreamReader(con.getInputStream()));
//                                       String inputLine;
//                                       StringBuffer response1 = new StringBuffer();
//            
//                                       while ((inputLine = in.readLine()) != null) {
//                                               response1.append(inputLine);
//                                       }
//                                       in.close();
//            
//                                       //print result
//                                   //    System.out.println(response1.toString());
//                            
//                                       resp=new JSONObject(response1.toString());
//                                       output=resp.getJSONObject("result");
//                                       JSONArray resArr=output.getJSONArray("products");
//               // System.out.println("resArr.length() "+resArr.length());
//                                       for(int i=0;i<resArr.length();i++) {
//                                           rowCount=1;
//                                       //    System.out.println("***********");
//                                           JSONObject productObj=resArr.getJSONObject(i);
//                                      //     System.out.println(productObj.toString());
//                                        //   System.out.println("***********");
//                                           String poNo="";
//                                            String vendorName="";
//                                            String vendorSiteCode="";
//                                            String productCategory=productObj.getString("categoryName");
//                                            
//                                            String unitPrice=productObj.getString("minSalePrice");
//                                            String imageUrl=productObj.getString("imageUrl");
//                                            JSONObject stores=productObj.getJSONObject("stores");
//                                           
//                                           
//                                           Iterator<?> keys = stores.keys();
//
//                                           while( keys.hasNext() ) {
//                                               String key = (String)keys.next();
//                                               JSONObject store=stores.getJSONObject(key);
//                                               vendorName=store.getString("storeName");
//                                                String productTitle=store.getString("title");
//                                                Random randomGenerator = new Random();
//                                                int randomInt = randomGenerator.nextInt(1000000000);
//                                                String showDiverSeImage="false";
//                                                String diverseImageURL="";
//                                                
//                                               //since for the indix suppliers they will not be diversed so update as -1
//                                                 Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo);
//                                                 //ItemsList.s_jobs.add(j); 
//                                                 ItemsList.items_list.add(j); 
//                                            }
//                                           
//                                         
//                                        //   System.out.println("***********");
//                                       }
//            
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
//            
//            
//            
//            
//            
//            
//            
//            }
//            else{
//                size=ItemsList.items_list.size();
//            }
//            
//            
//            
//            
//        //
//        //
//        //           // System.out.println("response===============================" + response);
//        //
//        //           String url = "https://api.indix.com/v2/summary/products"+"?"+"countryCode=US&q="+search+"&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
//        //
//        //                           URL obj = new URL(url);
//        //                           HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        //
//        //                           // optional default is GET
//        //                           con.setRequestMethod("GET");
//        //
//        //                           //add request header
//        //
//        //
//        //                           int responseCode = con.getResponseCode();
//        //                           System.out.println("\nSending 'GET' request to URL : " + url);
//        //                           System.out.println("Response Code : " + responseCode);
//        //
//        //                           BufferedReader in = new BufferedReader(
//        //                                   new InputStreamReader(con.getInputStream()));
//        //                           String inputLine;
//        //                           StringBuffer response1 = new StringBuffer();
//        //
//        //                           while ((inputLine = in.readLine()) != null) {
//        //                                   response1.append(inputLine);
//        //                           }
//        //                           in.close();
//        //
//        //                           //print result
//        //                           System.out.println(response1.toString());
//        //
//        //                           resp=new JSONObject(response1.toString());
//        //                           output=resp.getJSONObject("result");
//        //                           JSONArray resArr=output.getJSONArray("products");
//        //                           System.out.println("***********");
//        //                           for(int i=0;i<resArr.length();i++) {
//        //                               System.out.println("***********");
//        //                               JSONObject productObj=resArr.getJSONObject(i);
//        //
//        //                               String poNo="";
//        //                                String vendorName="";
//        //                                String vendorSiteCode="";
//        //                                String productCategory=productObj.getString("categoryName");
//        //                                String productTitle=productObj.getString("title");
//        //                                String unitPrice=productObj.getString("minSalePrice");
//        //                                String imageUrl=productObj.getString("imageUrl");
//        //
//        //                                Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each");
//        //                                ItemsList.s_jobs.add(j);
//        //                               System.out.println("***********");
//        //                           }
//            
//            
//            
//            
//            
//                 populateItemsBasedOnPageNo();
//            
//            
//            
//                System.out.println("outside "+ItemsList.s_jobs.size());
//            
//                
//                ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
//                ve5.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//                
//                ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
//                ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//                
//                ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
//                ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),size+" matches found");
//            
//                ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
//                ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
//            
//                ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
//                ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//            
//                
//          
////                   AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
////                                                                                  "adf.mf.api.amx.doNavigation", new Object[] { "refresh" });
//            
//                  providerChangeSupport.fireProviderRefresh("assets");
//            
//            
//                  
//            
//           BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//           vex.refresh();
//            
//            
//            
//            
//            
//           
//          
//            if(rowCount==0){
//                
//               // String error=output.getString("X_ERROR_MESSAGE");
//        //                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//        //                                                         AdfmfJavaUtilities.getFeatureName(),
//        //                                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//        //                                                        "Oops!! No products found,Try a Refined Search",
//        //                                                         null,
//        //                                                         null });
//        //                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
//        //                                                                               "adf.mf.api.amx.doNavigation", new Object[] { "refined_search" });
//                
//                
//                
//                    if(itemType.equalsIgnoreCase("goods")){
//                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                           "confirm_refined_search",
//                                                                           new Object[] { });             
//                    }
//                    else{
//                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                                   "confirm_refined_search_service",
//                                                                                   new Object[] { });      
//                    }
//                
//            }
//            
//        //            AmxAttributeBinding customerList = (AmxAttributeBinding) AdfmfJavaUtilities
//        //                                           .evaluateELExpression("#{bindings.assets3}");
//        //
//        //             StringBuffer selectedCustomerNames = new StringBuffer();
//        //
//        //             //access the list iterator to first set the current row by the indexes obtained
//        //             //from the value change event and then read the row object, which in this sample
//        //             //represents a customer
//        //             AmxIteratorBinding amxListIterator =  customerList.getIteratorBinding();
//        //
//        //             //the basic iterator in the AmxIteratorBinding is what we need to work with
//        //              BasicIterator      basicIterator = amxListIterator.getIterator();
//        //
//        //                  Item item = (Item) basicIterator.getDataProvider();
//        //
//        //            System.out.println("item===============================" + item.getProductTitle());
//        //
//            
//            
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        
//        }
//        else{
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                     AdfmfJavaUtilities.getFeatureName(),
//                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                     "Item Type is mandatory and cannot be empty.",
//                                                     null,
//                                                     null });  
//        }
        
        
        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }
    
    public void performSearch(ActionEvent actionEvent) {
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasIndixItemcategories}", String.class);
        String itemAliasIndixId = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        String searchValue= (String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
        String oracleId = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve91 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        String itemType=(String)ve91.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        System.out.println("Item Type--->"+itemType);
        
        System.out.println("++++itemAliasIndixId++"+itemAliasIndixId+"++++searchValue+++"+searchValue+"oracle ID "+ oracleId+"Alias indix length"+itemAliasIndixId.length());
        if(itemType.equalsIgnoreCase("goods"))
        {
        if(!oracleId.equals("") && !itemAliasIndixId.equals("") && !oracleId.equalsIgnoreCase("Please Select"))
        {
        doSearch();
        }
       
        else {  
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                                  "SearchWithItemCategories",
                                                                                                  new Object[] { });
        }
        }
        else {
            doSearch();
        }
    }
    public void proceedSearchwithCategories(String rr) {
        System.out.println("<<<welcome to proceed Search>>>");
        doSearch();
        
    }
    
    public void navigateToRefinedSearch(String rr) {
//        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
//                                                                                       "adf.mf.api.amx.doNavigation", new Object[] { "refined_search" });    

          populateUOM();
        ValueExpression ve91 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        itemType=(String)ve91.getValue(AdfmfJavaUtilities.getAdfELContext());
        if(itemType.equalsIgnoreCase("goods"))
        {
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                               "adf.mf.api.amx.doNavigation", new Object[] { "refined_search" });
        }
        else{
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                                   "adf.mf.api.amx.doNavigation", new Object[] { "refined_search_services" });
                 
        }
        
    }

    public String getItemDetails() {
        // Add event code here...
        String action="item_detail";
//        try{
//        
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.productTitle}", String.class);
//        String productTitle = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemRowId}", String.class);
//        String rowId = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
//
//
//        
//       
//            //SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
//            int count=SelectedItemsList.s_jobs.size();
//            ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
//            ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
//            AdfmfJavaUtilities.flushDataChangeEvent();
//        
//        
//        
//        for(int j=0;j<ItemsList.s_jobs.size();j++) {
//            Item i=(Item)ItemsList.s_jobs.get(j);    
//            if(i.getRowId().equalsIgnoreCase(rowId)) {
//                ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.unitPrice}", String.class);
//                ve1.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getUnitPrice());
//                
//                ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.productCategory}", String.class);
//                ve2.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getProductCategory());
//                
//                ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.vendorName}", String.class);
//                ve3.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getVendorName());                
//
//                ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.vendorSiteCode}", String.class);
//                ve4.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getVendorSiteCode());
//                
//                ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.poNo}", String.class);
//                ve5.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getPoNo());
//                ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom}", String.class);
//                ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getUom());
//                ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.source}", String.class);
//                ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),i. getSource());
//                
//                ValueExpression ve8 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemImageUrl}", String.class);
//                ve8.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getImageUrl());
//                
//                ValueExpression ve10 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDetailRowId}", String.class);
//                ve10.setValue(AdfmfJavaUtilities.getAdfELContext(),rowId);
//                
//                ValueExpression ve9 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddToCart}", String.class);
//                ve9.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//                
//                // Delivery type Image
//                ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemImageDeliverSource}", String.class);
//                ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getDiverseImageURL());                
//                ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showDiverse}", String.class);
//                ve23.setValue(AdfmfJavaUtilities.getAdfELContext(),i.getShowDiverseImage());                
//
//
////                rendered="#{row.showDiverseImage}" source="#{row.diverseImageURL}"
//                
//                
////                if(SelectedItemsList.items_selected!=null){
////                    
////                   
////                    
////                   for(int k=0;k<SelectedItemsList.items_selected.size();k++) {
////                       SelectedItem si=(SelectedItem)SelectedItemsList.items_selected.get(k);
////                       if(i.getRowId().equalsIgnoreCase(si.getRowid())) {
////                           
////                           ve9 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddToCart}", String.class);
////                           ve9.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
////                       }
////                       
////                   }
////                   
////                }
//                   
//                AdfmfJavaUtilities.flushDataChangeEvent();
//                
//            }
//        }
//        
//        }
//        catch(Exception e) {
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Cannot connect to Services on Oracle Server.",
//                                         null,
//                                         null });          
//        }
        
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.getItemDetails.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        return action;
    }

    public String getAttributeDetails() {
    MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.getSpecDetails.execute}", Object.class, new Class[] {});
    me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    return "";
    }
        
    public String makeReq() {
        // Add event code here...
        String action="req_submit";
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");  
        //Item selectedItem=(Item)vex.next()
         while(vex.hasNext()) {
             GenericType row= (GenericType)vex.next();
             
             for(int i=0;i<row.getAttributeCount();i++) {
                 System.out.println("***"+row.getAttribute(i));
             }
             
         }
            
            

        
        
      
        
        
        
//        
//        try{
//        
//            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.productTitle}", String.class);
//            String productTitle = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.unitPrice}", String.class);
//            String unitPrice = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.productCategory}", String.class);
//            String productCategory = (String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.vendorName}", String.class);
//            String vendorName = (String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.vendorSiteCode}", String.class);
//            String vendorSiteCode = (String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.poNo}", String.class);
//            String poNo = (String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
//            String quantity = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
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
//        restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/create_requisition/");
//        String postData= "{\n" + 
//        "  \"GET_Input\" : {\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/create_requisition/\",\n" + 
//        "   \"RESTHeader\": {\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//        "    },\n" + 
//        "   \"InputParameters\": {\n" + 
//        "    \"P_CATEGORY\" : \""+productCategory+"\",\n" + 
//        "    \"P_PRODUCT_TITLE\" : \""+productTitle+"\" ,\n" + 
//        "    \"P_QUANTITY\" : \""+quantity+"\" ,\n" + 
//        "    \"P_UOM\" : \"Each\" ,\n" + 
//        "    \"P_UNIT_PRICE\" : \""+unitPrice+"\" ,\n" + 
//        "    \"P_VENDOR_NAME\" : \""+vendorName+"\" ,\n" + 
//        "    \"P_VENDOR_SITE_CODE\" : \""+vendorSiteCode+"\" \n" + 
//        "   }\n" + 
//        "}\n" + 
//        "}\n";
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//            
//            
//            
//           String response = restServiceAdapter.send(postData);
//            
//            
//            System.out.println("response===============================" + response);
//            
//         AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                       AdfmfJavaUtilities.getFeatureName(),
//                        "navigator.notification.alert",
//                        new Object[] {"Your Requesition has been Created Successfully", "Ok"});
//            
//            
////            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
////                                         AdfmfJavaUtilities.getFeatureName(),
////                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
////                                        response,
////                                         null,
////                                         null });
//        
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        
        return action;
    }

    public void makeRFQ(ActionEvent actionEvent) {
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
         itemCategories = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
        String partNo = (String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
        String brand = (String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
        String upc = (String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());  
        
        ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
         quantity = (String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom}", String.class);
        String uom = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());  
        
        boolean checkError = false;
        String errorMessage = "";
        //quantity
       
                if( !itemCategories.equalsIgnoreCase("") && !quantity.equalsIgnoreCase("") && !uom.equalsIgnoreCase("") ){
                    
                            if(!brand.equalsIgnoreCase("")){
                                    if( !partNo.equalsIgnoreCase("") ){
                                        checkError = false;                                       
                                    }else{
                                        
                                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                                 "Brand works in combination with Manufacturer Part No. and so please enter the same too",
                                                                                 null,
                                                                                 null });                                        
                                        checkError = true;
                                    }
                            }else{                                
                                checkError = false;
                            }
                            
                            
                            if(checkError == false){
                                
                                //==================================
                                try{
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
                                restServiceAdapter.setRequestURI("webservices/rest/XXRFQ/main/");
                                String postData= "{\n" + 
                                "  \"MAIN_Input\" : {\n" + 
                                "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXRFQ/main/\",\n" + 
                                "   \"RESTHeader\": {\n" + 
                                "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXRFQ/header\",\n" + 
                                "    },\n" + 
                                "   \"InputParameters\": {\n" + 
                                "        \"P_SUPP_DTL_TAB\": {\"P_SUPP_DTL_TAB_ITEM\":    [\n" + 
                                "            {\n" + 
                                "             \"SUPPLIER_NAME\": \"Star Gate Ltd\",\n" + 
                                "             \"SUPPLIER_SITE\": \"STAR GATE - BUY\",\n" + 
                                "             \"SUPPLIER_CONTACT\": \"Peter\"\n" + 
                                "            },\n" + 
                                "            {\n" + 
                                "             \"SUPPLIER_NAME\": \"Global, Inc.\",\n" + 
                                "             \"SUPPLIER_SITE\": \"GLOBAL-GA\",\n" + 
                                "             \"SUPPLIER_CONTACT\": \"Rich\"\n" + 
                                "            }\n" + 
                                "         ]\n" + 
                                "       }\n" + 
                                "      }\n" + 
                                "   }\n" + 
                                "}\n" + 
                                "}\n";
                                                            restServiceAdapter.setRetryLimit(0);
                                   System.out.println("postData===============================" + postData);
                                
                                String response = restServiceAdapter.send(postData);
                                 
                                 
                                 System.out.println("response===============================" + response);
                                 
                                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                            AdfmfJavaUtilities.getFeatureName(),
                                             "navigator.notification.alert",
                                             new Object[] {"Your Request For Quotation has been Created Successfully", "Ok"});
                                }
                                catch(Exception e) {
                                    e.printStackTrace();
                                }
                                
                                //==================================
                            }
                    
                }else{
                        
                    if(itemCategories.equalsIgnoreCase("")){                        
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Item Category is mandatory",
                                                                 null,
                                                                 null });
                    }
                    
                    if(quantity.equalsIgnoreCase("")){                        
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Quantity Needed is mandatory",
                                                                 null,
                                                                 null });
                    }
                    
                    if(uom.equalsIgnoreCase("")){
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Unit Of Measurement is mandatory",
                                                                 null,
                                                                 null });
                        
                    }
                            
                }      
        
       
    }
    

    
    
    
    public void addPropertyChangeListener(PropertyChangeListener l) {

            propertyChangeSupport.addPropertyChangeListener(l);

        }


        public void removePropertyChangeListener(PropertyChangeListener l) {

            propertyChangeSupport.removePropertyChangeListener(l);

        }

    public void selectedItems(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets3.iterator}");  
        Item selectedItem=(Item)vex.getDataProvider();
        GenericType row= (GenericType)vex.getCurrentRow();
        
        Object newVal = (Object) valueChangeEvent.getNewValue();
        
        System.out.println(""+new Integer((String)newVal));
        
         for(int i=0;i<row.getAttributeCount();i++) {
             
             
           
             System.out.println("***"+row.getAttribute(i));
         }
            
            
        System.out.print("After =========>"+selectedItem.getProductTitle()+" is "+ valueChangeEvent.getNewValue().toString());
        
        //        ValueExpression vex = AdfmfJavaUtilities.getValueExpression("#{bindings.assets3.iterator}", Object.class);
        //             AmxIteratorBinding iter = (AmxIteratorBinding)vex.getValue(AdfmfJavaUtilities.getAdfELContext());
        //             GenericType row = null;
        //             BasicIterator bIter = iter.getIterator();
        //             iter.getIterator().first();
        //            ArrayList employees = new ArrayList();
        //        System.out.print("Before =========>");
        //             for(int i = 0; i < iter.getIterator().getTotalRowCount(); i++)
        //             {
        //                 row = (GenericType)iter.getCurrentRow();
        //
        //             }
        
        System.out.print("After =========>");
        
    }
    
    
    
    public void populateCartTotal(){
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}"); 
        Double cartValue=0.0;
        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
        for(int i=0;i<vex.getTotalRowCount();i++)
         {
             vex.setCurrentIndex(i);
             SelectedItem item=(SelectedItem)vex.getDataProvider();
             if(item.getChecked().equalsIgnoreCase("true")) {
                 if(item.getAmount()!=null && !item.getAmount().equalsIgnoreCase("")){
                    cartValue=cartValue+ Double.parseDouble(item.getAmount());
                 }
             }
         }
        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(Math.round(cartValue)));        
        AdfmfJavaUtilities.flushDataChangeEvent();     

        
        
    }
    

    public void selectItems(ActionEvent actionEvent) {
        // Add event code here...
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");  
//        Item item=(Item)vex.getDataProvider();
//        GenericType row= (GenericType)vex.getCurrentRow();
//
//
//        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//       
//        Random randomGenerator = new Random();
//        int randomInt = randomGenerator.nextInt(1000000000);
//
//        
//        SelectedItem selectItem=new SelectedItem(item.getPoNo(), item.getVendorName(), item.getVendorSiteCode(), item.getProductCategory(), item.getProductTitle(), item.getUnitPrice(), item.getImageUrl(), "true", item.getSource(), item.getUom(), "1", "1", "",item.getUnitPrice(),String.valueOf(randomInt),"",item.getRowId());
//      
//      
//        
//        for(int i=0;i<row.getAttributeCount();i++) {
//            
//            
//          
//            System.out.println("***"+row.getAttribute(i));
//        }
//        
//       
//        if(ItemsList.items_selected==null) {
//            ItemsList.items_selected=new ArrayList();
//        }
//        if(item.getChecked().equalsIgnoreCase("/images/uncheck.png")){
//         row.setAttribute("checked", new String("/images/check.png"));
//            
//           // propertyChangeSupport.firePropertyChange("checked", "images/uncheck.png", "/images/check.png");
//           System.out.println("select");
//           SelectedItemsList.items_selected.add(selectItem) ;   
//        }
//         else{
//             row.setAttribute("checked", new String("/images/uncheck.png"));
//             System.out.println("un select");
//            for(int k=0;k<SelectedItemsList.items_selected.size();k++) {
//                SelectedItem it=(SelectedItem)SelectedItemsList.items_selected.get(k);
//                System.out.println(it.getRowid() +"="+ selectItem.getRowid());
//                if(it.getRowid().equalsIgnoreCase(selectItem.getRowid())) {
//                    SelectedItemsList.items_selected.remove(k) ;         
//                }
//            }
//             
//             //propertyChangeSupport.firePropertyChange("checked", "images/check.png", "/images/uncheck.png");
//         }
//         
//         
//       
//        
//       
//            
//        
////        ValueExpression vex = AdfmfJavaUtilities.getValueExpression("#{bindings.assets3.iterator}", Object.class);  
////             AmxIteratorBinding iter = (AmxIteratorBinding)vex.getValue(AdfmfJavaUtilities.getAdfELContext());  
////             GenericType row = null;  
////             BasicIterator bIter = iter.getIterator();  
////             iter.getIterator().first();      
////            ArrayList employees = new ArrayList();  
////        System.out.print("Before =========>");
////             for(int i = 0; i < iter.getIterator().getTotalRowCount(); i++)  
////             {  
////                 row = (GenericType)iter.getCurrentRow();  
////                        
////             }  
//        
//        System.out.print("After =========>"+SelectedItemsList.items_selected.size());
//      
//        vex.refresh();
//
//        
                    MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.selectItem.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
      
    }
    
    public void doSelectItem(String rr)  {
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.doSelectItem.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }
    public void doSelectItemDetails(String rr)  {
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.doSelectItemDetails.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }
    

    public void showPopup(ActionEvent actionEvent) {
        // Add event code here...
        
//        try{
//        // Add event code here...
//            
//            //Oracle
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
//        restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/get_category_segments/");
//        String postData= "{\n" + 
//        "  \"GET_CATEGORY_SEGMENTS_Input\" : {\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_category_segments/\",\n" + 
//        "   \"RESTHeader\": {\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//        "    },\n" + 
//        "   \"InputParameters\": {\n" + 
//        "        \"P_SEGMENTS_TL\": { \"P_SEGMENTS_TL_ITEM\": [\n" + 
//       
//        "          ] \n" + 
//        "        }\n" + 
//        "     }\n" + 
//        "  }\n" + 
//        "}  \n";
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//        
//        String response = restServiceAdapter.send(postData);
//         
//         
//         System.out.println("response===============================" + response);
//            
//            JSONObject resp=new JSONObject(response);
//            JSONObject output=resp.getJSONObject("OutputParameters");
//            JSONObject segment=output.getJSONObject("X_SEGMENT_VALUES_TL");
//            JSONArray segments=segment.getJSONArray("X_SEGMENT_VALUES_TL_ITEM"); 
//            
//            CategoryList.categories.clear();
//            CategoryList1.categories.clear();
//            CategoryList2.categories.clear();
//            CategoryList3.categories.clear();
//            CategoryList4.categories.clear();
//            
//            ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription}", String.class);
//            ve61.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
//            
//            
//            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription1}", String.class);
//            ve62.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
//            
//            
//            ValueExpression ve63 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
//            ve63.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
//            
//            
//            
//            ValueExpression ve64 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
//            ve64.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
//            AdfmfJavaUtilities.flushDataChangeEvent();
//            
//            
//            Category c2=new Category("Please Select","0","Please Select" ,"");
//            CategoryList.categories.add(c2);
//            for(int i=0 ;i<segments.length();i++) {
//                System.out.println(segments.get(i));
//                JSONObject jobj=(JSONObject)segments.get(i);
//                String segment_value=jobj.getString("SEGMENT_VALUE");
//                String segment_description=jobj.getString("DESCRIPTION");
//                Category c = new Category(segment_value,"",segment_description,"O");
//                CategoryList.categories.add(c);
//
//            }
//            
//            
//           
//            
//            
//            //fetch top level categories and populate the drop down
//            
//             //  dh.populateParentCategories();
//      
//        }
//        catch(Exception e) {
//            
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Cannot connect to Services on Oracle Server.",
//                                         null,
//                                         null }); 
//            
//            
//          
//            e.printStackTrace();
//        }
//        
//        
//        
        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showCategories.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        /*Object errorMsg = AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
                                                                  "popupUtilsShowPopup", new Object[] {
                                                                  "_popShowId" });*/
    }


    public void getCategories1(ValueChangeEvent valueChangeEvent) {
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryText1}", String.class);
        ve61.setValue(AdfmfJavaUtilities.getAdfELContext(), valueChangeEvent.getNewValue().toString());
        
        System.out.println("Category=============++++++++++========"+ve61);
        
////        Category c=(Category)CategoryList.categories.get(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
////        System.out.println(c.getName()+" "+c.getId());
////        
////        if(!c.getName().equalsIgnoreCase("Please Select")){
////    
////            categoryText1=c.getName();
////            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription}", String.class);
////            ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),c.getName());
////            AdfmfJavaUtilities.flushDataChangeEvent();
////        
////        
////            try{
////                    // Add event code here...
////                    RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
////                    // Clear any previously set request properties, if any
////                    restServiceAdapter.clearRequestProperties();
////                    // Set the connection name
////                    restServiceAdapter.setConnectionName("enrich");
////            
////                    restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
////                    restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
////                    restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
////                    restServiceAdapter.addRequestProperty("Content-Type", "application/json");
////                    restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/get_category_segments/");
////                    String postData= "{\n" +
////                    "  \"GET_CATEGORY_SEGMENTS_Input\" : {\n" +
////                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_category_segments/\",\n" +
////                    "   \"RESTHeader\": {\n" +
////                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
////                    "    },\n" +
////                    "   \"InputParameters\": {\n" +
////                    "        \"P_SEGMENTS_TL\": { \"P_SEGMENTS_TL_ITEM\": [\n" +
////                    "\""+categoryText1+"\"] \n" +
////                    "        }\n" +
////                    "     }\n" +
////                    "  }\n" +
////                    "}  \n";
////                                                restServiceAdapter.setRetryLimit(0);
////                       System.out.println("postData===============================" + postData);
////            
////                    String response = restServiceAdapter.send(postData);
////            
////            
////                     System.out.println("response===============================" + response);
////            
////                        JSONObject resp=new JSONObject(response);
////                        JSONObject output=resp.getJSONObject("OutputParameters");
////                        JSONObject segment=output.getJSONObject("X_SEGMENT_VALUES_TL");
////            
////                        if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONArray){
////                        JSONArray segments=segment.getJSONArray("X_SEGMENT_VALUES_TL_ITEM");
////                     
////            
////                            Category c2=new Category("Please Select","0","Please Select" ,"");
////                            CategoryList1.categories.add(c2);
////                        for(int i=0 ;i<segments.length();i++) {
////                            System.out.println(segments.get(i));
////                            JSONObject jobj=(JSONObject)segments.get(i);
////                            String segment_value=jobj.getString("SEGMENT_VALUE");
////                            String segment_description=jobj.getString("DESCRIPTION");
////                            Category c1 = new Category(segment_value,"",segment_description,"0");
////                            CategoryList1.categories.add(c1);
////            
////                        }
////                        }
////                        
////                        else if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONObject){
////                            
////                            JSONObject segments=segment.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
////                            JSONObject jobj=segments;
////                            String segment_value=jobj.getString("SEGMENT_VALUE");
////                            String segment_description=jobj.getString("DESCRIPTION");
////                            Category c2=new Category("Please Select","0","Please Select" ,"");
////                            CategoryList1.categories.add(c2);
////                            Category c1 = new Category(segment_value,"",segment_description,"0");
////                            CategoryList1.categories.add(c1);
////                            
////                        }
////                        else{
////                            String cat=segment.getString("X_SEGMENT_VALUES_TL_ITEM");
////                            Category c2=new Category("Please Select","0","Please Select" ,"");
////                            CategoryList1.categories.add(c2);
////                            Category c1 = new Category(cat,"","","0");
////                            CategoryList1.categories.add(c1);
////                            //JSONObject segment=
////                        }
////            
////                    }
////                    catch(Exception e) {
////                        e.printStackTrace();
////                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
////                                                     AdfmfJavaUtilities.getFeatureName(),
////                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
////                                                     "Cannot connect to Services on Oracle Server.",
////                                                     null,
////                                                     null }); 
////                    }
////       
//        
//        
//        
//        
//    }
//        
//        
//        
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator1.iterator}");  
//        vex.refresh();
        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.getCategories1.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
    }
    
    
    
    
    public void getCategories2(ValueChangeEvent valueChangeEvent) {
        
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryText2}", String.class);
        ve61.setValue(AdfmfJavaUtilities.getAdfELContext(), valueChangeEvent.getNewValue().toString());
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.getCategories2.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        
//        ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category1Name}", String.class);
//        categoryText1=(String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        
//        Category c=(Category)CategoryList1.categories.get(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
//        System.out.println(c.getName()+" "+c.getId()+" "+c.getSource());
//        
//        if(!c.getName().equalsIgnoreCase("Please Select")){
//     
//            categoryText2=c.getName();
//            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription1}", String.class);
//            ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),c.getName());
//            AdfmfJavaUtilities.flushDataChangeEvent();
//            try{
//                    // Add event code here...
//                    RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
//                    // Clear any previously set request properties, if any
//                    restServiceAdapter.clearRequestProperties();
//                    // Set the connection name
//                    restServiceAdapter.setConnectionName("enrich");
//            
//                    restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//                    restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//                    restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//                    restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//                    restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/get_category_segments/");
//                    String postData= "{\n" +
//                    "  \"GET_CATEGORY_SEGMENTS_Input\" : {\n" +
//                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_category_segments/\",\n" +
//                    "   \"RESTHeader\": {\n" +
//                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
//                    "    },\n" +
//                    "   \"InputParameters\": {\n" +
//                    "        \"P_SEGMENTS_TL\": { \"P_SEGMENTS_TL_ITEM\": [\n" +
//                    categoryText1+","+categoryText2+"] \n" +
//                    "        }\n" +
//                    "     }\n" +
//                    "  }\n" +
//                    "}  \n";
//                                                restServiceAdapter.setRetryLimit(0);
//                       System.out.println("postData===============================" + postData);
//            
//                    String response = restServiceAdapter.send(postData);
//            
//            
//                     System.out.println("response===============================" + response);
//            
//                        JSONObject resp=new JSONObject(response);
//                        JSONObject output=resp.getJSONObject("OutputParameters");
//                        JSONObject segment=output.getJSONObject("X_SEGMENT_VALUES_TL");
//                        CategoryList2.categories.clear();
//                        if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONArray){
//                        JSONArray segments=segment.getJSONArray("X_SEGMENT_VALUES_TL_ITEM");
//            
//                            Category c2=new Category("Please Select","0","Please Select" ,"");
//                            CategoryList2.categories.add(c2);
//            
//                        for(int i=0 ;i<segments.length();i++) {
//                            System.out.println(segments.get(i));
//                            JSONObject jobj=(JSONObject)segments.get(i);
//                            String segment_value=jobj.getString("SEGMENT_VALUE");
//                            String segment_description=jobj.getString("DESCRIPTION");
//                            Category c1 = new Category(segment_value,"",segment_description,"0");
//                            CategoryList2.categories.add(c1);
//            
//                        }
//                        }
//                        
//                        else if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONObject){
//                            
//                            JSONObject segments=segment.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
//                            JSONObject jobj=segments;
//                            String segment_value=jobj.getString("SEGMENT_VALUE");
//                            String segment_description=jobj.getString("DESCRIPTION");
//                            Category c2=new Category("Please Select","0","Please Select" ,"");
//                            CategoryList2.categories.add(c2);
//                            Category c1 = new Category(segment_value,"",segment_description,"0");
//                            CategoryList2.categories.add(c1);
//                        }
//                        else{
//                            String cat=segment.getString("X_SEGMENT_VALUES_TL_ITEM");
//                            Category c2=new Category("Please Select","0","Please Select" ,"");
//                            CategoryList2.categories.add(c2);
//                            Category c1 = new Category(cat,"","","0");
//                            CategoryList2.categories.add(c1);
//                            //JSONObject segment=
//                        }
//            
//                    }
//                    catch(Exception e) {
//                        e.printStackTrace();
//                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                     AdfmfJavaUtilities.getFeatureName(),
//                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                     "Cannot connect to Services on Oracle Server.",
//                                                     null,
//                                                     null }); 
//                    }
//       
//        
//        
//        }
//        
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator2.iterator}");  
//        vex.refresh();
    }
    
    
    
    public void getCategories3(ValueChangeEvent valueChangeEvent) {
        
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryText3}", String.class);
        ve61.setValue(AdfmfJavaUtilities.getAdfELContext(), valueChangeEvent.getNewValue().toString());
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.getCategories3.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
//        Category c=(Category)CategoryList2.categories.get(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
//        System.out.println(c.getName()+" "+c.getId()+" "+c.getSource());
//        
//        if(!c.getName().equalsIgnoreCase("Please Select")){
//            
//            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
//            ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),c.getName());
//            AdfmfJavaUtilities.flushDataChangeEvent();
//    
//            categoryText3=c.getName();
//            try{
//                    // Add event code here...
//                    RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
//                    // Clear any previously set request properties, if any
//                    restServiceAdapter.clearRequestProperties();
//                    // Set the connection name
//                    restServiceAdapter.setConnectionName("enrich");
//            
//                    restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//                    restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//                    restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//                    restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//                    restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/get_category_segments/");
//                    String postData= "{\n" +
//                    "  \"GET_CATEGORY_SEGMENTS_Input\" : {\n" +
//                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_category_segments/\",\n" +
//                    "   \"RESTHeader\": {\n" +
//                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
//                    
//                    
//                    
//                    
//                    
//                    "    },\n" +
//                    "   \"InputParameters\": {\n" +
//                    "        \"P_SEGMENTS_TL\": { \"P_SEGMENTS_TL_ITEM\": [\n" +
//                        categoryText1+","+categoryText2+","+categoryText3+"] \n" +
//                    "        }\n" +
//                    "     }\n" +
//                    "  }\n" +
//                    "}  \n";
//                                                restServiceAdapter.setRetryLimit(0);
//                       System.out.println("postData===============================" + postData);
//            
//                    String response = restServiceAdapter.send(postData);
//            
//            
//                     System.out.println("response===============================" + response);
//            
//                        JSONObject resp=new JSONObject(response);
//                        JSONObject output=resp.getJSONObject("OutputParameters");
//                        JSONObject segment=output.getJSONObject("X_SEGMENT_VALUES_TL");
//                        CategoryList3.categories.clear();
//            
//                        if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONArray){
//                        JSONArray segments=segment.getJSONArray("X_SEGMENT_VALUES_TL_ITEM");
//            
//            
//                            Category c2=new Category("Please Select","0","Please Select" ,"");
//                            CategoryList3.categories.add(c2);
//                        for(int i=0 ;i<segments.length();i++) {
//                            System.out.println(segments.get(i));
//                            JSONObject jobj=(JSONObject)segments.get(i);
//                            String segment_value=jobj.getString("SEGMENT_VALUE");
//                            String segment_description=jobj.getString("DESCRIPTION"); 
//                            Category c1 = new Category(segment_value,"",segment_description,"0");
//                            CategoryList3.categories.add(c1);
//            
//                        }
//                        }
//                        
//                        else if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONObject){
//                            
//                            JSONObject segments=segment.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
//                            JSONObject jobj=segments;
//                            String segment_value=jobj.getString("SEGMENT_VALUE");
//                            String segment_description=jobj.getString("DESCRIPTION");
//                            Category c2=new Category("Please Select","0","Please Select" ,"");
//                            CategoryList3.categories.add(c2);
//                            Category c1 = new Category(segment_value,"",segment_description,"0");
//                            CategoryList3.categories.add(c1);
//                            
//                        }
//                        else{
//                            String cat=segment.getString("X_SEGMENT_VALUES_TL_ITEM");
//                            Category c2=new Category("Please Select","0","Please Select" ,"");
//                            CategoryList3.categories.add(c2);
//                            Category c1 = new Category(cat,"","","0");
//                            CategoryList3.categories.add(c1);
//                            //JSONObject segment=
//                        }
//            
//                    }
//                    catch(Exception e) {
//                        e.printStackTrace();
//                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                     AdfmfJavaUtilities.getFeatureName(),
//                                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                     "Cannot connect to Services on Oracle Server.",
//                                                     null,
//                                                     null }); 
//                    }
//        
//        
//        }
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator3.iterator}");  
//        vex.refresh();
    }
    
    

    public void makeCheckout(String rr)  {
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.makeCheckout.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }
    public void makeRequesition(ActionEvent actionEvent) {
        // Add event code here...
//        
//        
//        boolean isSelected=false;
//        boolean isNeedByDateEmpty=false;
//        boolean isQuantityEmpty=false;
//        boolean isQuantityDecimal=false;
//        
//        String action="req_submit";
//        
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
//       
//        for(int i=0;i<vex.getTotalRowCount();i++)
//        {
//            vex.setCurrentIndex(i);
//            SelectedItem item=(SelectedItem)vex.getDataProvider();
//            System.out.println("***"+item.getProductTitle()+" "+item.getChecked());
//            
//            if(item.getChecked().equalsIgnoreCase("true")) {
//                isSelected=true;
//            }
//            if(item.getNeed_by_date().equalsIgnoreCase("")){
//                isNeedByDateEmpty=true;
//            }
//            if(item.getQuantity().equalsIgnoreCase("")){
//                isQuantityEmpty=true;
//            }
//            if((Double.parseDouble(item.getQuantity())%1!=0)) {
//                isQuantityDecimal=true;
//            }
//        }
//       
//       
//        if(isSelected){
//       
//            if(!isNeedByDateEmpty && !isQuantityEmpty && !isQuantityDecimal){
//       System.out.println(vex.getCurrentRowKey());
//       
//        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
//        String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        
//        ValueExpression ve412 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
//       String searchType= (String)ve412.getValue(AdfmfJavaUtilities.getAdfELContext());
//       
//      
//                       try{
//                           SelectedItemsList.purchase_items=SelectedItemsList.items_selected;
//                       // Add event code here...
//                       RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
//                       // Clear any previously set request properties, if any
//                       restServiceAdapter.clearRequestProperties();
//                       // Set the connection name
//                       restServiceAdapter.setConnectionName("enrich");
//                       
//                       restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//                       restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//                       restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//                       restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//                       restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/checkout/");
//                           
//                         
//                           StringBuffer sb = new StringBuffer("[\n");
//                           SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
//                           Random randomGenerator = new Random();
//                           int randomInt = randomGenerator.nextInt(100);
//                           
//                           
//                           ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_name}", String.class);
//                           String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//                           
//                           ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
//                           String productTitle = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
//                           
//                                sb.append("{\n");
//                                
//                                sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
//                                sb.append("    \"DEVICE_SERIAL_ID\":\"ABCD\",\n");
//                                sb.append("    \"USER_ID\":\""+userName+"\",\n");
//                                sb.append("    \"SEARCH_TYPE\":\""+searchType+"\",\n");
//                                sb.append("    \"SEARCH_TEXT\":\""+productTitle+"\",\n");
//                                sb.append("    \"RESULT_COUNT\":\""+ItemsList.s_jobs.size()+"\",\n");
//                                sb.append("    \"REQUEST_TYPE\":\"REQ\"\n");
//                                sb.append("},");
//               
//                           String header_value = sb.substring(0, sb.length() - 1).concat("]");
//                           
//                           System.out.println("header_value===============================" + header_value);
//                           
//                           
//                           sb = new StringBuffer("[\n");
//                           
//                           for(int i=0;i<vex.getTotalRowCount();i++)
//                           {
//                               vex.setCurrentIndex(i);
//                               SelectedItem item=(SelectedItem)vex.getDataProvider();
//                               System.out.println("***"+item.getProductTitle()+" "+item.getChecked());
//                               
//                               if(item.getChecked().equalsIgnoreCase("true")) {
//                                   SelectedItem it=item;
//                                   sb.append("{\n");
//                                   
//                                   sb.append("    \"SEARCH_ID\":\""+randomInt+"\",\n");
//                                   if(it.getSource().equalsIgnoreCase("Contracted")){
//                                   sb.append("    \"PRODUCT_SOURCE\":\"O\",\n");
//                                   }
//                                   else{
//                                       sb.append("    \"PRODUCT_SOURCE\":\"I\",\n");
//                                   }
//                                   
//                                  
//                                 
//                                   
//                                   
//                                   
//                                   sb.append("    \"PRODUCT_CATEGORY\":\""+it.getProductCategory()+"\",\n");
//                                   sb.append("    \"PRODUCT_TITLE\":\""+it.getProductTitle()+"\",\n");
//                                   sb.append("    \"QUANTITY\":\""+it.getQuantity()+"\",\n");
//                                   sb.append("    \"UOM_CODE\":\""+it.getUom()+"\",\n");
//                                   sb.append("    \"UNIT_PRICE\":\""+it.getUnitPrice()+"\",\n");
//                                   sb.append("    \"CURRENCY_CODE\":\"USD\",\n");
//                                   
//                                   DeliverToLocation loc;
//                                   if(!it.getDeliver_to_location().equalsIgnoreCase("")){
//                                      loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get((Integer.parseInt(it.getDeliver_to_location())-1));
//                                   }
//                                   else{
//                                       loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get(0);
//                                   }
//                                   
//                                   
//                                   
//                                   sb.append("    \"DELIVER_TO_LOCATION\":\""+loc.getCode()+"\",\n");
//                                   String arr[]=it.getNeed_by_date().split("T");
//                                   sb.append("    \"NEED_BY_DATE\":\""+arr[0]+"\",\n");
//                                   sb.append("    \"BPA_NUM\":\"\",\n");
//                                   sb.append("    \"VENDOR_NAME\":\""+it.getVendorName()+"\",\n");
//                                   sb.append("    \"VENDOR_SITE\":\""+it.getVendorSiteCode()+"\",\n");
//                                   sb.append("    \"VENDOR_SITE_ADDRESS1\":\"\",\n");
//                                   sb.append("    \"VENDOR_SITE_ADDRESS2\":\"\",\n");
//                                   sb.append("    \"VENDOR_SITE_ADDRESS3\":\"\",\n");
//                                   sb.append("    \"VENDOR_SITE_CITY\":\"\",\n");
//                                   sb.append("    \"VENDOR_SITE_STATE\":\"\",\n");
//                                   sb.append("    \"VENDOR_SITE_ZIP\":\"\",\n");
//                                   sb.append("    \"VENDOR_SITE_COUNTRY\":\"\",\n");
//                                   sb.append("    \"VENDOR_CONTACT_FIRST_NAME\":\"JAMES\",\n");
//                                   sb.append("    \"VENDOR_CONTACT_LAST_NAME\":\"FRANKLINE\",\n");
//                                   sb.append("    \"VENDOR_CONTACT_PHONE\":\"\",\n");
//                                   sb.append("    \"VENDOR_CONTACT_EMAIL\":\"james_fr@gmail.com\",\n");
//                                   sb.append("    \"SELECTED_FLAG\":\"Y\",\n");
//                                   sb.append("    \"ITEM_TYPE\":\""+itemType+"\",\n");
//                                   CostCenter c;
//                                   if(!it.getCostCenter().equalsIgnoreCase("")){
//                                     c=(CostCenter)CostCenterList.s_jobs.get((Integer.parseInt(it.getCostCenter())));
//                                   }
//                                   else{
//                                       c=(CostCenter)CostCenterList.s_jobs.get(0); 
//                                   }
//                                   
//                                   sb.append("    \"COST_CENTER\":\""+c.getName()+"\"\n");
//                                   
//                                   sb.append("},");
//                                   
//                                   SelectedItemsList.purchase_items.remove(item);
//                                   SelectedItemsList.items_selected.remove(item);
//                                   SelectedItemsList.s_jobs.remove(item);
//                               }
//                                   
//                           }
//                           
//                       
//                           
//                           String body_value = sb.substring(0, sb.length() - 1).concat("]");
//                           
//                           System.out.println("body_value===============================" + body_value);
//                           
//                           String data= "{\n" + 
//                           "  \"CHECKOUT_Input\" : {\n" + 
//                           "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/checkout/\",\n" + 
//                           "   \"RESTHeader\": {\n" + 
//                           "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//                           "    },\n" + 
//                           "   \"InputParameters\": {\n" + 
//                           "        \"P_SEARCH_HEADER\": {\"P_SEARCH_HEADER_ITEM\": \n"+header_value+"\n" + 
//                           "       },\n" + 
//                           "        \"P_SEARCH_LINES\": {\"P_SEARCH_LINES_ITEM\": \n"+body_value+"         \n" + 
//                           "       }    \n" + 
//                           "      }\n" + 
//                           "   }\n" + 
//                           "}\n";
//                           
//                           
//                     
//                                                   restServiceAdapter.setRetryLimit(0);
//                          System.out.println("postData===============================" + data);
//                       
//                       String response = restServiceAdapter.send(data);
//                        
//                           System.out.println("response===============================" + response);
//                           
//                          
//                           
//                         
//                           
//                           ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
//                           ve4.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(Math.round(0)));  
//                           
//                           
//                           
//                           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                                      "displayAlert",
//                                                                                      new Object[] { });
//                           
//                           int count=SelectedItemsList.s_jobs.size();
//                           
//                           ValueExpression ve45 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
//                           if(count==0) {
//                               
//                               ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
//                           }
//                           else{
//                               ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
//                           }
//                           AdfmfJavaUtilities.flushDataChangeEvent();
//                           
//                           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
//                                                                                          "adf.mf.api.amx.doNavigation", new Object[] { "__back" });    
//                      
//                      
//                      
//                      
//                      
//                       }
//                       catch(Exception e) {
//                           e.printStackTrace();
//                           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                        AdfmfJavaUtilities.getFeatureName(),
//                                                        "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                        "Cannot connect to Services on Oracle Server.",
//                                                        null,
//                                                        null }); 
//                       }
//                       
//            }
//            else{
//                
//                if(isQuantityEmpty){
//                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                 AdfmfJavaUtilities.getFeatureName(),
//                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                 "Quantity cannot be empty",
//                                                 null,
//                                                 null }); 
//                }
//                if(isQuantityDecimal){
//                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                 AdfmfJavaUtilities.getFeatureName(),
//                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                 "Quantity for item type goods cannot be decimal",
//                                                 null,
//                                                 null }); 
//                }
//                
//                if(isNeedByDateEmpty){
//                    
//                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                 AdfmfJavaUtilities.getFeatureName(),
//                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                 "Need by date cannot be empty",
//                                                 null,
//                                                 null }); 
//                }
//            }
//        
//        }
//        else{
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "No items Chosen.",
//                                         null,
//                                         null }); 
//        }
//           
//       
//     
        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.makeRequesition.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});

        
        
    }

    public void addItemsToCart(ActionEvent actionEvent) {
        // Add event code here...
//        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
//        String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
//        if(itemType!=null && !itemType.equalsIgnoreCase("")) {
//        
//        //SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
//        
//            for (int i = 0 ; i<SelectedItemsList.items_selected.size();i++){
//                SelectedItemsList.s_jobs.add(SelectedItemsList.items_selected.get(i)) ;
//            }
//        int count=SelectedItemsList.s_jobs.size();
//        
//        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
////        String old_count_value=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
////         if(old_count_value.equalsIgnoreCase("")) {
////             ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
////         }
////         else{
////             
////             int x= Integer.parseInt(old_count_value)+count;
////             ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(x));
////             
////         }
//        
//        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
//        
//       
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}"); 
//        for(int i=0;i<vex.getTotalRowCount();i++)
//        {
//            vex.setCurrentIndex(i);
//            Item item=(Item)vex.getDataProvider();
//            item.setChecked("/images/uncheck.png");
//            
//        }
//        vex.refresh();
//        
//        AdfmfJavaUtilities.flushDataChangeEvent();
//        
//        if(SelectedItemsList.items_selected.size()>0){
//        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                   "displayAlert",
//                                                                   new Object[] {}); 
//        
//        }
//        else{
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "No items Chosen.",
//                                         null,
//                                         null }); 
//        }
//        
//        
//        
//        
//        SelectedItemsList.items_selected.clear();
//        
//        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rdGoods}", String.class);
//        String rdGoods=(String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rdServices}", String.class);
//        String rdServices=(String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        System.out.println("rdGoods ==============> "+rdGoods);
//        System.out.println("rdServices ==============> "+rdServices);
//        
//        
//        }
//        else{
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Item Type is mandatory and cannot be empty.",
//                                         null,
//                                         null });    
//        }
//        
        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.addItemToCart.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
    }


    public void cartCheck(ActionEvent actionEvent) {
        // Add event code here...
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
        Item item=(Item)vex.getDataProvider();
        GenericType row= (GenericType)vex.getCurrentRow();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000000000);
        String specList="";
        if(item.getSpec()!=""){
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
        
        SelectedItem selectItem=new SelectedItem(item.getPoNo(), item.getVendorName(), item.getVendorSiteCode(), item.getProductCategory(), item.getProductTitle(), item.getUnitPrice(), item.getImageUrl(), "false", item.getSource(), item.getUom(), "1", "1", "1",item.getUnitPrice(),String.valueOf(randomInt),"",item.getRowId(),item.getIndixCategoryId(),specList,"","","","","","","","","","","","","");
        
        for(int i=0;i<row.getAttributeCount();i++) {
            
            
          
            System.out.println("***"+row.getAttribute(i));
        }
        
        
        if(ItemsList.items_selected==null) {
            ItemsList.items_selected=new ArrayList();
        }
        if(item.getChecked().equalsIgnoreCase("/images/uncheck.png")){
         row.setAttribute("checked", new String("/images/check.png"));
            SelectedItemsList.items_selected.add(selectItem);
           // propertyChangeSupport.firePropertyChange("checked", "images/uncheck.png", "/images/check.png");
           
           
        }
         else{
            
              SelectedItemsList.items_selected.remove(selectItem) ;  
             row.setAttribute("checked", new String("/images/uncheck.png"));
             
             //propertyChangeSupport.firePropertyChange("checked", "images/check.png", "/images/uncheck.png");
         }
         
         
        
        
        
            
        
        //        ValueExpression vex = AdfmfJavaUtilities.getValueExpression("#{bindings.assets3.iterator}", Object.class);
        //             AmxIteratorBinding iter = (AmxIteratorBinding)vex.getValue(AdfmfJavaUtilities.getAdfELContext());
        //             GenericType row = null;
        //             BasicIterator bIter = iter.getIterator();
        //             iter.getIterator().first();
        //            ArrayList employees = new ArrayList();
        //        System.out.print("Before =========>");
        //             for(int i = 0; i < iter.getIterator().getTotalRowCount(); i++)
        //             {
        //                 row = (GenericType)iter.getCurrentRow();
        //
        //             }
        
   
        
        vex.refresh();
        
    }

    public void makePredictiveSearch(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        doSearch();
       
    }
    
    public void predictiveSearch(String rr) {
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        String productTitle = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        System.out.print("Text =========>"+productTitle);
    }

    public void selectDeliverLocation(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        
        System.out.print("Location  =========>"+valueChangeEvent.getNewValue());
    }

    public void quantityChanged(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
//        
//        System.out.print("Location  =========>"+valueChangeEvent.getNewValue().toString());
//        
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}"); 
//        Double cartValue=0.0;
//        
//        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
//        String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        
//        
//        boolean error=false;
//      
//        for(int i=0;i<vex.getTotalRowCount();i++)
//         {
//             vex.setCurrentIndex(i);
//             SelectedItem item=(SelectedItem)vex.getDataProvider();
//             Double d=Double.parseDouble(item.getUnitPrice());
//            
//            if(itemType.equalsIgnoreCase("goods") && (Double.parseDouble(item.getQuantity())%1==0)){
//             Double qty=Double.parseDouble(item.getQuantity());
//             Double price=d*qty;
//             item.setAmount(String.valueOf(Math.round(price)));
//            }
//            else{
//                System.out.print("Inside error  case");
//                error=true;
//                item.setAmount("");
//                
//            }
//            
//         }
//        
//        
//        
////        
////        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
////        SelectedItem item=(SelectedItem)vex.getDataProvider();
////        GenericType row= (GenericType)vex.getCurrentRow();
////        
////        
////        
////        row.setAttribute("amount", String.valueOf(Math.round(price)));
////        System.out.print("price is   =========>"+Math.round(price));
//        if(error){
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Quantity cannot be in decimals for item type goods",
//                                         null,
//                                         null });    
//        }
//        vex.refresh();
//        System.out.print("before populate total");
//        populateCartTotal();
//        System.out.print("after populate total");
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.quantityChanged.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
    }

    public void deleteFromCart(ActionEvent actionEvent) {
        // Add event code here...
        
//        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartRowId}", String.class);
//        String rowId=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
//
//        
//        
//         System.out.print("rowId is   =========>"+rowId);
//         
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
//         
//        for(int i=0;i<vex.getTotalRowCount();i++)
//                {
//                    vex.setCurrentIndex(i);
//                    SelectedItem item1=(SelectedItem)vex.getDataProvider();
//                    if(rowId.equalsIgnoreCase(item1.getRowid())) {
//                        System.out.print("Row removed");
//                        vex.removeCurrentRow();
//                        SelectedItemsList.items_selected.remove(item1);
//                        SelectedItemsList.purchase_items.remove(item1);
//                        SelectedItemsList.s_jobs.remove(item1);
//                    }
//                }
//         
//        vex.refresh();
//        
////        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
////        
////        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartProductTitle}", String.class);
////        String title=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
////        
////        System.out.print("title is   =========>"+title);
////        for(int i=0;i<vex.getTotalRowCount();i++)
////        {
////            vex.setCurrentIndex(i);
////            SelectedItem item1=(SelectedItem)vex.getDataProvider();
////            if(title.equalsIgnoreCase(item1.getProductTitle())) {
////                System.out.print("Row removed");
////                vex.removeCurrentRow();
////                SelectedItemsList.items_selected.remove(item1);
////                SelectedItemsList.purchase_items.remove(item1);
////            }
////        }
////        
////        Double cartTotal=0.0;
////        
////        for(int i=0;i<vex.getTotalRowCount();i++)
////        {
////            vex.setCurrentIndex(i);
////            SelectedItem item1=(SelectedItem)vex.getDataProvider();
////            
////            cartTotal=cartTotal+Double.parseDouble(item1.getAmount());
////        }
////        
////        ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
////        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(),String.valueOf(cartTotal));  
////        
////        vex.refresh();
//        
//       
//        int count=SelectedItemsList.s_jobs.size();
//        ValueExpression ve45 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
//        if(count==0) {
//            
//            ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
//        }
//        else{
//            ve45.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
//        }
//        
//        populateCartTotal();
//        AdfmfJavaUtilities.flushDataChangeEvent();
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.deleteFromCart.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        
    }

    public void selectCartItem(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        //populateCartTotal();
         
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.populateCartTotal.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
    }

    public void needByDateChanged(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
//        
//        for(int i=0;i<vex.getTotalRowCount();i++)
//          {
//                    vex.setCurrentIndex(i);
//                    SelectedItem item=(SelectedItem)vex.getDataProvider();
//        try{
//            
//           
//           
//            
//            
//        System.out.println("New Date "+valueChangeEvent.getNewValue().toString());
//        
//         String arr[]=valueChangeEvent.getNewValue().toString().split("T");
//         String date="";
//            if(arr.length>1){
//                 date=arr[0];    
//            }
//        
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//            Date sel_date=sdf.parse(item.getNeed_by_date());
//            Date today_date=sdf.parse(sdf.format(new Date()));
//            
//            long diff = today_date.getTime() - sel_date.getTime();
//            
//            System.out.println("today_date "+sdf.format(today_date));
//            System.out.println("Comparing Dates "+sel_date.equals(today_date));
//            
////            if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)>0) {
////                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
////                                             AdfmfJavaUtilities.getFeatureName(),
////                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
////                                             "Need by date should be greater than or equal to current date",
////                                             null,
////                                             null });
////                item.setNeed_by_date("");
////                
////                
////            }
//            
//            if(sel_date.after(today_date)){
//               
//               
//            }
//            else if(sel_date.equals(today_date)){
//                
//            }
//            else{
//                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                             AdfmfJavaUtilities.getFeatureName(),
//                                                             "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                             "Need by date should be greater than or equal to current date",
//                                                             null,
//                                                             null });
//                                item.setNeed_by_date(""); 
//            }
//            
//            System.out.println("Formatted Date "+date);
//            
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//                }
//        vex.refresh();
        
      
            
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.needByDateCheckout}", String.class);
        ve61.setValue(AdfmfJavaUtilities.getAdfELContext(),valueChangeEvent.getNewValue().toString());
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.needByDateChanged.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
    }
    
    
    
    
    public void validateNeedByDateChanged(ValueChangeEvent valueChangeEvent) {
        try{
            
            
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
        String needByDate = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date sel_date=sdf.parse(needByDate);
        Date today_date=sdf.parse(sdf.format(new Date()));
        
        long diff = today_date.getTime() - sel_date.getTime();
        
        System.out.println("today_date "+sdf.format(today_date));
        System.out.println("Comparing Dates "+sel_date.equals(today_date));
        
        
        
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
        }
        
        System.out.println("Formatted Date "+needByDate);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }
    
    
    public void validateNeedByDateChangedServices(ValueChangeEvent valueChangeEvent) {
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
        String endDate = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        try{
            if(endDate.equalsIgnoreCase("")){ 
            
           
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
        String needByDate = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date sel_date=sdf.parse(needByDate);
        Date today_date=sdf.parse(sdf.format(new Date()));
        
        long diff = today_date.getTime() - sel_date.getTime();
        
        System.out.println("today_date "+sdf.format(today_date));
        System.out.println("Comparing Dates "+sel_date.equals(today_date));
        
        
        
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
        }
        
        System.out.println("Formatted Date "+needByDate);
        }
        else{
              if(!endDate.equalsIgnoreCase("")) {
                  
                  ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
                  String needByDate = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
                      
                  
                  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                  Date sel_date=sdf.parse(needByDate);
                  Date end_date=sdf.parse(endDate);
                  Date today_date=sdf.parse(sdf.format(new Date()));
                  
                  long diff = today_date.getTime() - sel_date.getTime();
                  
                  System.out.println("today_date "+sdf.format(today_date));
                  System.out.println("End_date "+sdf.format(end_date));
                  System.out.println("Comparing Dates "+sel_date.equals(today_date));
                  if(sel_date.after(today_date) && sel_date.before(end_date)){
                     
                     
                  }
                  else if(sel_date.equals(today_date) && sel_date.before(end_date)){
                      
                  }
                  else if(sel_date.before(today_date)){
                      AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                   AdfmfJavaUtilities.getFeatureName(),
                                                                   "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                   "Need by date should be greater than or equal to current date",
                                                                   null,
                                                                   null });
                                      needByDate=""; 
                      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), needByDate);
                      
                  }
                  else{
                      AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                   AdfmfJavaUtilities.getFeatureName(),
                                                                   "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                   "Need by date should be lesser than End Date",
                                                                   null,
                                                                   null });
                                      needByDate=""; 
                      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), needByDate);
                  }
              }
               
                
            }
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }
    
    public void validateEndDateChanged(ValueChangeEvent valueChangeEvent) {
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
        String needByDate = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        try{
            if(needByDate.equalsIgnoreCase("")){ 
            
           
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
        String endDate = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date sel_date=sdf.parse(endDate);
        Date today_date=sdf.parse(sdf.format(new Date()));
        
            
        
        long diff = today_date.getTime() - sel_date.getTime();
        
        System.out.println("today_date "+sdf.format(today_date));
        System.out.println("Comparing Dates "+sel_date.equals(today_date));
        
        
        
        if(sel_date.after(today_date)){
           
           
        }
        
        else{
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                         AdfmfJavaUtilities.getFeatureName(),
                                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                         "End Date should be greater than current date",
                                                         null,
                                                         null });
                            endDate=""; 
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), needByDate);
        }
        
        System.out.println("Formatted Date "+endDate);
        }
        else{
              if(!needByDate.equalsIgnoreCase("")) {
                  
                  ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
                  String endDate = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
                      
                  
                  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                  Date sel_date=sdf.parse(endDate);
                  Date need_date=sdf.parse(needByDate);
                  Date today_date=sdf.parse(sdf.format(new Date()));
                  
                  long diff = today_date.getTime() - sel_date.getTime();
                  
                  System.out.println("today_date "+sdf.format(today_date));
                  System.out.println("need date "+sdf.format(need_date));
                  System.out.println("Comparing Dates "+sel_date.equals(today_date));
                  if(sel_date.after(today_date) && sel_date.after(need_date)){
                     
                     
                  }
                 
                 
                  else{
                      AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                   AdfmfJavaUtilities.getFeatureName(),
                                                                   "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                   "End Date should be greater than Need By Date",
                                                                   null,
                                                                   null });
                                      endDate=""; 
                      ve.setValue(AdfmfJavaUtilities.getAdfELContext(), endDate);
                  }
              }
               
                
            }
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }
    
    public void addToCartDetails(ActionEvent actionEvent) {
        // Add event code here...
      
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.productTitle}", String.class);
        String productTitle = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDetailRowId}", String.class);
        String rowId = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        
        for(int j=0;j<ItemsList.s_jobs.size();j++) {
            Item item=(Item)ItemsList.s_jobs.get(j);    
            if(item.getRowId().equalsIgnoreCase(rowId)) {
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
                
                SelectedItem selectItem=new SelectedItem(item.getPoNo(), item.getVendorName(), item.getVendorSiteCode(), item.getProductCategory(), item.getProductTitle(), item.getUnitPrice(), item.getImageUrl(), "false", item.getSource(), item.getUom(), "1", "1", "1",item.getUnitPrice(),item.getRowId(),"",item.getRowId(),item.getIndixCategoryId(),specList,"","","","","","","","","","","","","");
                SelectedItemsList.items_selected.add(selectItem); 
                SelectedItemsList.s_jobs.add(selectItem); 
                
                
                
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                           "displayAlert",
                                                                           new Object[] {});
                
//                ValueExpression ve10 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddToCart}", String.class);
//                ve10.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                
                   
                
            }
        }
        
        
        //SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
        
        int count=SelectedItemsList.items_selected.size();
        
        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        
    }


    public void itemTypeChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
       

        String oldstr = "";
        String newstr = "";
        Object value = null;

        value = valueChangeEvent.getOldValue();
        if (value != null)
        {
          oldstr = value.toString();
        }

        value = valueChangeEvent.getNewValue();
        if (value != null)
        {
          newstr = value.toString();
        }

        String oldval = "Old Value: " + oldstr;
        String newval = "New Value: " + newstr;

       System.out.println("===========> New Value "+newval+" Old Value"+oldval);
       
       
        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), newstr);
        
       
        
    }

    public void doRefinedSearch(ActionEvent actionEvent) {
        // Add event code here...
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.doRefinedSearch.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});   
    }
    public String populateUOM(){
       
        
//        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
//        String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
//            if(!itemType.equalsIgnoreCase("")){ 
//       
//        try{
//     
//        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
//        // Clear any previously set request properties, if any
//        restServiceAdapter.clearRequestProperties();
//        // Set the connection name
//        restServiceAdapter.setConnectionName("enrich");
//        
//        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//            ///
//        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_uom/");
//        String postData= "{\n" + 
//        "\n" + 
//        "  \"GET_UOM_Input\" : {\n" + 
//        "\n" + 
//        "    \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_uom/\",\n" + 
//        "\n" + 
//        "   \"RESTHeader\": {\n" + 
//        "\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//        "\n" + 
//        "    },\n" + 
//        "\n" + 
//        "   \"InputParameters\": {\n" + 
//        "\n" + 
//        "     }\n" + 
//        "\n" + 
//        "   } \n" + 
//        "\n" + 
//        "}";
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//            
//           String response = restServiceAdapter.send(postData);
//            
//            JSONObject resp=new JSONObject(response);
//            JSONObject output=resp.getJSONObject("OutputParameters");
//            JSONObject tbl=output.getJSONObject("X_UOM_TL");
//            UOMList.s_jobs.clear();
//            UOM u1=new UOM("Please Select");
//            UOMList.s_jobs.add(u1);
//            
//                if(tbl.get("X_UOM_TL_ITEM") instanceof  JSONArray){
//                   JSONArray segments=tbl.getJSONArray("X_UOM_TL_ITEM");
//                   for(int i=0;i<segments.length();i++) {
//                       JSONObject item=(JSONObject)segments.get(i);
//                       UOM u=new UOM(item.getString("UNIT_OF_MEASURE"));
//                       if(item.getString("UNIT_OF_MEASURE").equalsIgnoreCase("Each")){
//                           System.out.println("Each match "+i);
//                       ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
//                       ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf((i+1)));
//                       }
//                       
//                       
//                       UOMList.s_jobs.add(u);
//                       
//                   }
//                
//                }
//                
//                else if(tbl.get("X_UOM_TL_ITEM") instanceof  JSONObject){
//                    
//                    JSONObject item=tbl.getJSONObject("X_UOM_TL_ITEM");
//                    UOM u=new UOM(item.getString("UNIT_OF_MEASURE"));
//                    if(item.getString("UNIT_OF_MEASURE").equalsIgnoreCase("Each")){
//                    ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
//                    ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(1));
//                    }
//                    UOMList.s_jobs.add(u);
//                    
//                    //JSONObject segments=data.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
//                    
//                }
//            
//            
//            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            
//            ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
//            ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
//            AdfmfJavaUtilities.flushDataChangeEvent();      
//                  
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        
//        //Defaulting UOM to each
//        
//       
//        AdfmfJavaUtilities.flushDataChangeEvent();
//        
//        
//        return "refined_search";
//        }
//        else{
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Item Type is mandatory and cannot be empty.",
//                                         null,
//                                         null });    
//            return "";
//        }
//            
        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.populateUOM.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        ValueExpression ve91 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        itemType=(String)ve91.getValue(AdfmfJavaUtilities.getAdfELContext());
        String search="";
        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        search = (String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext()); 
        if(!search.equalsIgnoreCase("")){
            if(itemType.equalsIgnoreCase("goods")){
               return "refined_search";
            }
            else{
                return "refined_search_services";
            }
        }
        else {
            return "refresh";
        }
        
    }


    public void proceedRefinedSearch(String rr)  {
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.proceedRefinedSearch.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});   
    }
    public void showBrandsPopup(ActionEvent actionEvent) {
        // Add event code here...
        try{
            
//           
//            BrandList.s_jobs.clear();
//            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
//            String brand = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
//
//                   String url = "https://api.indix.com/v2/brands?q="+URLEncoder.encode(brand)+"&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
//                                   
//                                   URL obj = new URL(url);
//                                   HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        
//                                   // optional default is GET
//                                   con.setRequestMethod("GET");
//        
//                                   //add request header
//                                  
//        
//                                   int responseCode = con.getResponseCode();
//                                   System.out.println("\nSending 'GET' request to URL : " + url);
//                                   System.out.println("Response Code : " + responseCode);
//        
//                                   BufferedReader in = new BufferedReader(
//                                           new InputStreamReader(con.getInputStream()));
//                                   String inputLine;
//                                   StringBuffer response1 = new StringBuffer();
//        
//                                   while ((inputLine = in.readLine()) != null) {
//                                           response1.append(inputLine);
//                                   }
//                                   in.close();
//        
//                                   JSONObject resp=new JSONObject(response1.toString());
//                                   JSONObject output=resp.getJSONObject("result");
//                                   JSONArray resArr=output.getJSONArray("brands");
//           
//                                   BrandList.s_jobs.clear();
//                                   for(int i=0;i<resArr.length();i++) {
//                                       JSONObject brandObj=resArr.getJSONObject(i);
//                                       Brand j = new Brand(String.valueOf(brandObj.getInt("id")), brandObj.getString("name"));
//                                       BrandList.s_jobs.add(j); 
//                                    //   System.out.println("***********");
//                                   }
//        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showBrandsPopup.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.brands.iterator}");  
        vex.refresh();
        
        Object errorMsg = AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
                                                                  "popupUtilsShowPopup", new Object[] {
                                                                  "_popShowId1" });
    }


    public String setCategories() {
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
        
        if(c1!=null && !c1.equalsIgnoreCase("Please Select")) {
            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category1Name}", String.class);
            categoryText1=(String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            catText=categoryText1;
        }
        if(c2!=null &&  !c2.equalsIgnoreCase("Please Select")) {
            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category2Name}", String.class);
            categoryText2=(String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
            catText=catText+"."+categoryText2;
        }
        if(c3!=null && !c3.equalsIgnoreCase("Please Select")) {
            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category3Name}", String.class);
            categoryText3=(String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
            catText=catText+"."+categoryText3;
        }
        if(c4!=null && !c4.equalsIgnoreCase("Please Select")) {
            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category4Name}", String.class);
            categoryText4=(String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
            catText=catText+"."+categoryText4; 
        }
        
        ve5.setValue(AdfmfJavaUtilities.getAdfELContext(),catText);    
            
        return "submitRequisition";
    }

    public void performSupplierSearch(ActionEvent actionEvent) {
        // Add event code here...
//        
//        try{
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchSupplierValue}", String.class);
//        String supplierName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
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
//        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/predictive_search_supplier/");
//        String postData= "{\n" + 
//        "  \"PREDICTIVE_SEARCH_SUPPLIER_Input\" : {\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/predictive_search_supplier/\",\n" + 
//        "   \"RESTHeader\": {\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//        "    },\n" + 
//        "   \"InputParameters\": {\n" + 
//        "        \"P_TEXT\": \""+supplierName+"\"\n" + 
//        "     }\n" + 
//        "  }\n" + 
//        "}  \n";
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//            
//           String response = restServiceAdapter.send(postData);
//            System.out.println("response===============================" + response);
//            
//            JSONObject resp=new JSONObject(response);
//            JSONObject output=resp.getJSONObject("OutputParameters");
//             JSONObject data=output.getJSONObject("X_SEARCH_RESULTS_TL");
//            SupplierList.s_jobs.clear();
//            
//            if(data.get("X_SEARCH_RESULTS_TL_ITEM") instanceof  JSONArray){
//               JSONArray segments=data.getJSONArray("X_SEARCH_RESULTS_TL_ITEM");
//               for(int i=0;i<segments.length();i++) {
//                   String name=(String)segments.get(i);
//                   Supplier s=new Supplier(name);
//                   SupplierList.s_jobs.add(s);
        
        
//                   
//               }
//            
//            }
//            
//            else if(data.get("X_SEARCH_RESULTS_TL_ITEM") instanceof  JSONObject){
//                
//                //JSONObject segments=data.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
//                
//            }
//            else{
//                String seg=data.getString("X_SEARCH_RESULTS_TL_ITEM");
//                Supplier s=new Supplier(seg);
//                SupplierList.s_jobs.add(s);
//            }
//            
//            
//            
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.supplier.iterator}");  
//            vex.refresh();
//            
//        }
//        catch(Exception e) {
//            
//            
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Cannot connect to Services on Oracle Server.",
//                                         null,
//                                         null }); 
//        }
         

        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.performSupplierSearch.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

   
   
    public void selectSuppliers(ActionEvent actionEvent) {
        // Add event code here...
        
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
//        String supplierNames = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        for(int i=0;i<selectedSuppliers.size();i++) {
//            int indx=selectedSuppliers.get(i);
//            Supplier s=(Supplier)SupplierList.s_jobs.get(indx);
//            if(supplierNames==null || supplierNames.equalsIgnoreCase("")) {
//                
//                supplierNames=s.getName();
//            }
//            else{
//                supplierNames=supplierNames+","+s.getName();
//            }
//            
//        }
//        
//        System.out.println("Selected suppliers are "+supplierNames);
//        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), supplierNames);
//        AdfmfJavaUtilities.flushDataChangeEvent();
        
        
      ValueExpression vedef = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierDefault}", String.class);
      vedef.setValue(AdfmfJavaUtilities.getAdfELContext(),null);
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.selectSuppliers.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        
    }
    
    public void selectServiceLocations(ActionEvent actionEvent) {
      
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.selectServiceLocations.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        
    }
    
    

    public void onSupplierChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Object[] newVal = (Object[]) valueChangeEvent.getNewValue();
//            selectedSuppliers.clear();
        
            MethodExpression me1 = AdfmfJavaUtilities.getMethodExpression("#{bindings.clearSuppliers.execute}", Object.class, new Class[] {});
            me1.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
         for (int i = 0; i < newVal.length; i++) {
             
//             System.out.println(newVal[i].toString());
//             selectedSuppliers.add(new Integer((String)newVal[i]));
             
          try{
              
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierName}", String.class);
            ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), new String((String)newVal[i]));      
           
           MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.onSupplierAdd.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
            
            
        }
        catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                          "adf.mf.api.amx.addMessage", new Object[] {
                                                          AdfException.ERROR,
                                                          "Inside Exception===> " + sw.toString(),
                                                          null, null
            });
        }
         }
        
        SupplierList.onSupplierChange(newVal);
        
       
        
         

    }
    
    
    public void onServiceLocationChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Object[] newVal = (Object[]) valueChangeEvent.getNewValue();
    //            selectedSuppliers.clear();
        
            MethodExpression me1 = AdfmfJavaUtilities.getMethodExpression("#{bindings.clearServiceLocations.execute}", Object.class, new Class[] {});
            me1.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
         for (int i = 0; i < newVal.length; i++) {
             
    //             System.out.println(newVal[i].toString());
    //             selectedSuppliers.add(new Integer((String)newVal[i]));
             
          try{
              
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.multipleServiceLocationSelection}", String.class);
            ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), new String((String)newVal[i]));      
           
           MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.onServiceLocationAdd.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
            
            
        }
        catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                          "adf.mf.api.amx.addMessage", new Object[] {
                                                          AdfException.ERROR,
                                                          "Inside Exception===> " + sw.toString(),
                                                          null, null
            });
        }
         }
        
        SupplierList.onSupplierChange(newVal);
        
       
        
         

    }

    public void showCart() {
//        
//     
//        // Add event code here...
//        Double cartValue=0.0;
//        
//        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
//        String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
//        //asdf
//       //SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
//        int count=SelectedItemsList.s_jobs.size();
//        
//        String default_cost_center_id="";
//        String default_cost_center_name="";
//        String default_deliver_to_location_id="";
//        String default_deliver_to_location_name="";
//        
//        
//        if(count>0){
//        if(itemType!=null && !itemType.equalsIgnoreCase(""))  {
//        try{
//            
//            
//            
//        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
//        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        
//         ValueExpression ve48 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_cost_center}", String.class);
//         default_cost_center_id = (String)ve48.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//         ValueExpression ve49 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_deliver_to}", String.class);
//         default_deliver_to_location_id = (String)ve49.getValue(AdfmfJavaUtilities.getAdfELContext());    
//            
//            
//            
//            
//         //Deliver to Location   
//            
//         RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
//         // Clear any previously set request properties, if any
//         restServiceAdapter.clearRequestProperties();
//         // Set the connection name
//         restServiceAdapter.setConnectionName("enrich");
//         
//         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_deliver_to/");
//         String postData= "{\n" + 
//         "\n" + 
//         "  \"GET_DELIVER_TO_Input\" : {\n" + 
//         "\n" + 
//         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_deliver_to/\",\n" + 
//         "\n" + 
//         "   \"RESTHeader\": {\n" + 
//         "\n" + 
//         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//         "    },\n" + 
//         "\n" + 
//         "   \"InputParameters\": {\n" + 
//         "\n" + 
//         "        \"P_USER_ID\":"+userId+"\n" + 
//         "\n" + 
//         "     }\n" + 
//         "\n" + 
//         "  }\n" + 
//         "\n" + 
//         "}  ";
//                                     restServiceAdapter.setRetryLimit(0);
//            System.out.println("postData===============================" + postData);
//             
//            String response = restServiceAdapter.send(postData);
//             
//             System.out.println("response===============================" + response); 
//             JSONObject resp=new JSONObject(response);
//             JSONObject output=resp.getJSONObject("OutputParameters");
//            JSONObject data=new JSONObject();
//          try{
//              data=output.getJSONObject("X_DELIVER_TO_TL");
//             DeliverToLocationList.s_jobs.clear();
//             
//             if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONArray){
//               JSONArray segments=data.getJSONArray("X_DELIVER_TO_TL_ITEM");
//               for(int i=0;i<segments.length();i++) {
//                 JSONObject location=segments.getJSONObject(i);
//                 String locationId=location.getString("LOCATION_ID");
//                 String locationCode=location.getString("LOCATION_CODE");
//                 String locationDescription=location.getString("DESCRIPTION");
//                  if(locationId.equalsIgnoreCase(default_deliver_to_location_id)) {
//                      default_deliver_to_location_name=locationCode;
//                  }
//                 DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
//                 DeliverToLocationList.s_jobs.add(loc);
//               }
//             
//             }
//             
//             else if(data.get("X_DELIVER_TO_TL_ITEM") instanceof  JSONObject){
//                
//                JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
//                 String locationId=location.getString("LOCATION_ID");
//                 String locationCode=location.getString("LOCATION_CODE");
//                 String locationDescription=location.getString("DESCRIPTION");
//                 if(locationId.equalsIgnoreCase(default_deliver_to_location_id)) {
//                     default_deliver_to_location_name=locationCode;
//                 }
//                 DeliverToLocation loc=new DeliverToLocation(locationId, locationCode, locationDescription);
//                 DeliverToLocationList.s_jobs.add(loc);
//                
//             }
//             }
//             catch(Exception e) {
//                 e.printStackTrace();
//             }
//         
//            
//            
//            
//            
//            
//            
//            
//            
//            
//         //Cost center     
//        
//        restServiceAdapter = Model.createRestServiceAdapter();
//        // Clear any previously set request properties, if any
//        restServiceAdapter.clearRequestProperties();
//        // Set the connection name
//        restServiceAdapter.setConnectionName("enrich");
//        
//        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//        restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_cost_center/");
//        postData= "{\n" + 
//        "\n" + 
//        "  \"GET_COST_CENTER_Input\" : {\n" + 
//        "\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_cost_center/\",\n" + 
//        "\n" + 
//        "   \"RESTHeader\": {\n" + 
//        "\n" + 
//        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//        "    },\n" + 
//        "\n" + 
//        "   \"InputParameters\": {\n" + 
//        "\n" + 
//        "          \"P_USER_ID\" : "+userId+"\n" + 
//        "\n" + 
//        "     }\n" + 
//        "\n" + 
//        "  }\n" + 
//        "\n" + 
//        "}  ";
//                                    restServiceAdapter.setRetryLimit(0);
//           System.out.println("postData===============================" + postData);
//            
//            response = restServiceAdapter.send(postData);
//            
//            System.out.println("response===============================" + response); 
//             resp=new JSONObject(response);
//             output=resp.getJSONObject("OutputParameters");
//        try{
//             data=output.getJSONObject("X_COST_CENTER_TL");
//            CostCenterList.s_jobs.clear();
//            
//            if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONArray){
//              JSONArray segments=data.getJSONArray("X_COST_CENTER_TL_ITEM");
//              for(int i=0;i<segments.length();i++) {
//                  //String name=(String)segments.get(i);
//                  JSONObject ci=(JSONObject)segments.get(i);
//                  String name=ci.getString("SEGMENT_VALUE");
//                  String description=ci.getString("DESCRIPTION");
//                  if(name.equalsIgnoreCase(default_cost_center_id)) {
//                      default_cost_center_name=description;
//                  }
//                  CostCenter c=new CostCenter(name,description);
//                  CostCenterList.s_jobs.add(c);
//                  
//              }
//            
//            }
//            
//            else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
//               
//               JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
//                String name=ci.getString("SEGMENT_VALUE");
//                String description=ci.getString("DESCRIPTION");
//                if(name.equalsIgnoreCase(default_cost_center_id)) {
//                    default_cost_center_name=description;
//                }
//                CostCenter c=new CostCenter(name,description);
//                CostCenterList.s_jobs.add(c);
//               
//            }
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }
//      
//            
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
//            
//            
//            
//        
//        
//            for(int i=0;i<SelectedItemsList.s_jobs.size();i++)
//             {
//                 SelectedItem item=(SelectedItem)SelectedItemsList.s_jobs.get(i);
//                 cartValue=cartValue+ Double.parseDouble(item.getAmount());
//                 
//             }
//            
//            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartTotal}", String.class);
//                    ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(Math.round(cartValue)));        
//                    AdfmfJavaUtilities.flushDataChangeEvent();   
//            
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
//            vex.refresh();
//                
//                
//        
//        
//        return "checkout";
//        
//        }
//        else{
//            
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Item Type is mandatory and cannot be empty.",
//                                         null,
//                                         null });    
//            return "";
//        }
//        
//        }
//        else{
//            
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                         AdfmfJavaUtilities.getFeatureName(),
//                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                         "Your cart is empty.",
//                                         null,
//                                         null });    
//            return "";
//        }
//        
//        
//        
            
        
    }
    
    
    
    public String showCartDetail() {
        // Add event code here...
        
        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
        
      // SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
        int count=SelectedItemsList.s_jobs.size();
        
        String default_cost_center_id="";
        String default_cost_center_name="";
        String default_deliver_to_location_id="";
        String default_deliver_to_location_name="";
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
            
            
          
          
          
        return "checkout";
        
        }
        else{
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "Item Type is mandatory and cannot be empty.",
                                         null,
                                         null });    
            return "";
        }
        
        }
        else{
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "Your cart is empty.",
                                         null,
                                         null });    
            return "";
        }
        
        
    }
    
    
    


    public void onDeliverToLocationChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
        SelectedItem item=(SelectedItem)vex.getDataProvider();
        
//         String locationId=item.getDeliver_to_location(); 
//         DeliverToLocation loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get(Integer.parseInt(locationId));
//         item.setDeliver_to_location(loc.getCode());
        
         System.out.println("deliver to change "+valueChangeEvent.getNewValue().toString());
        GenericType row= (GenericType)vex.getCurrentRow();
        row.setAttribute("deliver_to_location", valueChangeEvent.getNewValue().toString());
              
        //vex.refresh();
    }

    public void onCostCenterChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression vemul = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String multiOrgId=(String)vemul.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartRowId}", String.class);
        String rowId=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
        System.out.println("Cart Row ID: "+rowId);
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");  
        for(int i=0;i<vex.getTotalRowCount();i++)
                {
                    vex.setCurrentIndex(i);
                    SelectedItem item=(SelectedItem)vex.getDataProvider();
                    //        System.out.print("rowId is  new =========>"+item.getRowid()+"CostCenter Value"+item.getCostCenter());
                        GenericType row= (GenericType)vex.getCurrentRow();
                        String newrowId=item.getRowid();
                        String newrowId1=row.getAttribute("rowid").toString();
            System.out.println("newRowid---------"+newrowId1);
                        if(!row.getAttribute("rowid").toString().equalsIgnoreCase("")){
                            System.out.println("----");
                            
                        }
                    
            
        //         String locationId=item.getDeliver_to_location();
        //         DeliverToLocation loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get(Integer.parseInt(locationId));
        //         item.setDeliver_to_location(loc.getCode());
        
        
                    }  
        CostCenter c=(CostCenter)CostCenterList.s_jobs.get(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
         System.out.println("deliver to change "+c.getDescription()+"----"+c.getName());
        String cost="003";
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
                        "          \"P_COST_CENTER\" : \""+c.getName()+"\"\n" +               
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

    public void getCategories4(ValueChangeEvent valueChangeEvent) {
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryText4}", String.class);
        ve61.setValue(AdfmfJavaUtilities.getAdfELContext(), valueChangeEvent.getNewValue().toString());
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.getCategories4.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        // Add event code here...
//        Category c=(Category)CategoryList3.categories.get(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
//        System.out.println(c.getName()+" "+c.getId()+" "+c.getSource());
//        categoryText4=c.getName();
//        
//        if(!c.getName().equalsIgnoreCase("Please Select")){
//        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription3}", String.class);
//        ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),c.getName());
//        AdfmfJavaUtilities.flushDataChangeEvent();
//        }
//        
        
    }

    public void addItemFromDetailToCart(ActionEvent actionEvent) {
        // Add event code here...
        
//       ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
//       String itemType=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
//       if(itemType!=null && !itemType.equalsIgnoreCase("")) {
//        
//        
//        ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemRowId}", String.class);
//        String rowId = (String)ve21.getValue(AdfmfJavaUtilities.getAdfELContext());
//        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        for(int j=0;j<ItemsList.s_jobs.size();j++) {
//            Item item=(Item)ItemsList.s_jobs.get(j);    
//                   if(item.getRowId().equalsIgnoreCase(rowId)) {
//                      Random randomGenerator = new Random();
//                      int randomInt = randomGenerator.nextInt(1000000000);
//         SelectedItem selectItem=new SelectedItem(item.getPoNo(), item.getVendorName(), item.getVendorSiteCode(), item.getProductCategory(), item.getProductTitle(), item.getUnitPrice(), item.getImageUrl(), "true", item.getSource(), item.getUom(), "1", "1", date,item.getUnitPrice(),String.valueOf(randomInt),"",item.getRowId());
//         SelectedItemsList.s_jobs.add(selectItem) ; 
//                  }
//                }
//            
//       // SelectedItemsList.s_jobs=SelectedItemsList.items_selected;
//        int count=SelectedItemsList.s_jobs.size();
//        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
//        ve4.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
//        AdfmfJavaUtilities.flushDataChangeEvent();
//        
//       }
//       else{
//           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                    AdfmfJavaUtilities.getFeatureName(),
//                                                    "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                    "Item Type is mandatory and cannot be empty.",
//                                                    null,
//                                                    null });  
//       }
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.addItemFromDetailToCart.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
    }

    public void sortItems(ActionEvent actionEvent) {
        // Add event code here...
        //#{pageFlowScope.selectedSortBy}
//        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedSortBy}", String.class);
//        String sortBy=(String)ve41.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        if(sortBy.equalsIgnoreCase("relevance")) {
//            ComparatorChain chain = new ComparatorChain();  
//            //call the relevance api to get the relevance factors
//            try{
//            RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
//            // Clear any previously set request properties, if any
//            restServiceAdapter.clearRequestProperties();
//            // Set the connection name
//            restServiceAdapter.setConnectionName("enrich");
//            
//            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//            restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_relevance_factor/");
//            String postData= "{\n" + 
//            "\n" + 
//            "  \"GET_RELEVANCE_FACTOR_TEXT_Input\" : {\n" + 
//            "\n" + 
//            "   \"RESTHeader\": {\n" + 
//            "\n" + 
//            "    },\n" + 
//            "\n" + 
//            "   \"InputParameters\": {\n" + 
//            "\n" + 
//            "       }         \n" + 
//            "\n" + 
//            "   }\n" + 
//            "\n" + 
//            "}";
//            
//               restServiceAdapter.setRetryLimit(0);
//               System.out.println("postData===============================" + postData);
//               String response = restServiceAdapter.send(postData);
//               System.out.println("response===============================" + response); 
//               JSONObject resp=new JSONObject(response);
//               JSONObject output=resp.getJSONObject("OutputParameters");
//               JSONObject data=output.getJSONObject("X_RELEVANCE_FACTOR_TL"); 
//               
//                if(data.get("X_RELEVANCE_FACTOR_TL_ITEM") instanceof  JSONArray){
//                              JSONArray segments=data.getJSONArray("X_RELEVANCE_FACTOR_TL_ITEM");
//                              for(int i=0;i<segments.length();i++) {
//                                  //String name=(String)segments.get(i);
//                                  JSONObject ci=(JSONObject)segments.get(i);
//                                  String type_name=ci.getString("LOOKUP_TYPE");
//                                  String type_code=ci.getString("LOOKUP_CODE");
//                                  String meaning=ci.getString("MEANING");
//                                  String tag=ci.getString("TAG");
//                                  
//                                  if(type_code.equalsIgnoreCase("DIV_SUPPLIER")) {
//                                      System.out.println("div supplier===============================");
//                                      Comparator<Item> comparatorDiversedSupplier = new Comparator<Item>() {  
//                                                   @Override  
//                                                   public int compare(Item o1, Item o2) {  
//                                                        return Double.compare(Double.parseDouble(o2.getDiverseSupplier()), Double.parseDouble(o1.getDiverseSupplier()));  
//                                                   }  
//                                              };  
//                                      chain.addComparator(comparatorDiversedSupplier); 
//                                  }
//                                  if(type_code.equalsIgnoreCase("PRICE_LOW_TO_HIGH")) {
//                                      System.out.println("low to high===============================");
//                                      Comparator<Item> comparatorPriceLowToHigh = new Comparator<Item>() {  
//                                                   @Override  
//                                                   public int compare(Item o1, Item o2) {  
//                                                       return Double.compare(Double.parseDouble(o1.getUnitPrice()), Double.parseDouble(o2.getUnitPrice()));  
//                                                   }  
//                                              };  
//                                      chain.addComparator(comparatorPriceLowToHigh); 
//                                      
//                                  }
//                                  
//                              }
//                                
//                                Collections.sort(ItemsList.s_jobs,chain);
//                                BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//                                vex.refresh();
//                            
//                            }
//                            
//                            else if(data.get("X_COST_CENTER_TL_ITEM") instanceof  JSONObject){
//                               
//                               JSONObject ci=data.getJSONObject("X_COST_CENTER_TL_ITEM");
//                                String type_name=ci.getString("LOOKUP_TYPE");
//                                String type_code=ci.getString("LOOKUP_CODE");
//                                String meaning=ci.getString("MEANING");
//                                String tag=ci.getString("TAG");
//                                
//                                if(type_code.equalsIgnoreCase("DIV_SUPPLIER")) {
//                                    
//                                    Comparator<Item> comparatorDiversedSupplier = new Comparator<Item>() {  
//                                                 @Override  
//                                                 public int compare(Item o1, Item o2) {  
//                                                      return Double.compare(Double.parseDouble(o2.getDiverseSupplier()), Double.parseDouble(o1.getDiverseSupplier()));  
//                                                 }  
//                                            };  
//                                    chain.addComparator(comparatorDiversedSupplier); 
//                                }
//                                if(type_code.equalsIgnoreCase("PRICE_LOW_TO_HIGH")) {
//                                    
//                                    Comparator<Item> comparatorPriceLowToHigh = new Comparator<Item>() {  
//                                                 @Override  
//                                                 public int compare(Item o1, Item o2) {  
//                                                     return Double.compare(Double.parseDouble(o2.getUnitPrice()), Double.parseDouble(o1.getUnitPrice()));  
//                                                 }  
//                                            };  
//                                    chain.addComparator(comparatorPriceLowToHigh); 
//                                    
//                                }
//                               
//                                Collections.sort(ItemsList.s_jobs,chain);
//                                BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//                                vex.refresh();
//                            }
//               
//               
//            
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
//            
//        }
//        
//        if(sortBy.equalsIgnoreCase("low_to_high")) {
//            Collections.sort( ItemsList.s_jobs,new Comparator<Item>() {
//                                              public int compare(Item o1, Item o2) {
//                                                      int res=0;
//                                                  if (o1.getUnitPrice() == null || o2.getUnitPrice() == null)
//                                                  {
//                                                    res= 0;
//                                                  }
//                                                  else{
//                                                      
//                                                      res=Double.compare(Double.parseDouble(o1.getUnitPrice()), Double.parseDouble(o2.getUnitPrice()));
//                                                      
////                                                  try {
////                                                            res= sdf.parse(o2.getCreatedDate()).compareTo(sdf.parse(o1.getCreatedDate()));
////                                                    } catch (Exception e) {
////                                                            // TODO Auto-generated catch block
////                                                            e.printStackTrace();
////                                                    }
//                                                      
//                                                      
//                                                  }
//                                                    return res;
//                                              }
//                                             
//                                            });  
//            
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//            vex.refresh();
//        }
//        
//        if(sortBy.equalsIgnoreCase("high_to_low")) {
//            
//            
//            Collections.sort( ItemsList.s_jobs,new Comparator<Item>() {
//                                              public int compare(Item o1, Item o2) {
//                                                      int res=0;
//                                                  if (o1.getUnitPrice() == null || o2.getUnitPrice() == null)
//                                                  {
//                                                    res= 0;
//                                                  }
//                                                  else{
//                                                      
//                                                      res=Double.compare(Double.parseDouble(o2.getUnitPrice()), Double.parseDouble(o1.getUnitPrice()));
//                                                      
//            //                                                  try {
//            //                                                            res= sdf.parse(o2.getCreatedDate()).compareTo(sdf.parse(o1.getCreatedDate()));
//            //                                                    } catch (Exception e) {
//            //                                                            // TODO Auto-generated catch block
//            //                                                            e.printStackTrace();
//            //                                                    }
//                                                      
//                                                      
//                                                  }
//                                                    return res;
//                                              }
//                                             
//                                            });  
//            
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//            vex.refresh();
//            
//        }
//        
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.sortItems.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }
    
        

    public String filterItemsByCategory() {
//        // Add event code here...
//        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories.inputValue}", String.class);
//        String c1=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories1.inputValue}", String.class);
//        String c2=(String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories2.inputValue}", String.class);
//        String c3=(String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
//        
//        ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories3.inputValue}", String.class);
//        String c4=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
//            
//        
//        ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item_categories}", String.class);
//        
//        String catText="";
//        ItemsList.cpy_list.clear();
//        ItemsList.filt_list.clear();
//        for(int i=0;i<ItemsList.s_jobs.size();i++) {
//            System.out.println("index is ===============================" + i);
//           Item item=(Item)ItemsList.s_jobs.get(i);
//            ItemsList.filt_list.add(item);
//        }
//        
//        if(c1!=null && !c1.equalsIgnoreCase("")&& !c1.equalsIgnoreCase("Please Select")) {
//            catText=categoryText1;
//        }
//        if(c2!=null && !c2.equalsIgnoreCase("")&& !c2.equalsIgnoreCase("Please Select")) {
//            catText=catText+"."+categoryText2;
//        }
//        if(c3!=null && !c3.equalsIgnoreCase("")&& !c3.equalsIgnoreCase("Please Select")) {
//            catText=catText+"."+categoryText3;
//        }
//        if(c4!=null && !c4.equalsIgnoreCase("")&& !c4.equalsIgnoreCase("Please Select")) {
//            catText=catText+"."+categoryText4; 
//        }
//        
//        //catText contains the category mapping now get equivalent indix category for selected oracle category
//        
//        
//        try{
//        
//        //get category Id from oracle and send the category id  along with the brand id
//        
//        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
//         // Clear any previously set request properties, if any
//         restServiceAdapter.clearRequestProperties();
//         // Set the connection name
//         restServiceAdapter.setConnectionName("enrich");
//         
//         restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
//         restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
//         restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
//         restServiceAdapter.addRequestProperty("Content-Type", "application/json");
//         restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_indix_category/");
//         String data= "{\n" + 
//         "\n" + 
//         "  \"GET_INDIX_CATEGORY_Input\" : {\n" + 
//         "\n" + 
//         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_indix_category/\",\n" + 
//         "\n" + 
//         "   \"RESTHeader\": {\n" + 
//         "\n" + 
//         "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
//         "    },\n" + 
//         "\n" + 
//         "   \"InputParameters\": {\n" + 
//         "\n" + 
//         "        \"P_ORACLE_CATEGORY\": \""+catText+"\"\n" + 
//         "\n" + 
//         "      }         \n" + 
//         "\n" + 
//         "   }\n" + 
//         "\n" + 
//         "}";                         
//         restServiceAdapter.setRetryLimit(0);
//         System.out.println("postData===============================" + data);
//         String response = restServiceAdapter.send(data);
//         System.out.println("response===============================" + response);
//        JSONObject resp=new JSONObject(response);
//        JSONObject output=resp.getJSONObject("OutputParameters");
//         String indix_category=output.getString("X_INDIX_CATEGORY");
//         System.out.println("indix_category===============================" + indix_category);
//         //quey indix to find categoryId for found category
//         
//               
//            //ItemsList.cpy_list.clear();
//            System.out.println("ItemsList.s_jobs.size()===============================" + ItemsList.s_jobs.size());    
//         for(int i=0;i<ItemsList.s_jobs.size();i++) {
//             System.out.println("index is ===============================" + i);
//            Item item=(Item)ItemsList.s_jobs.get(i);
//             System.out.println("category for item is===============================" + item.getProductCategory());
//             if(item.getSource().equalsIgnoreCase("Contracted")) {
////               //means that the product is from oracle so try filtering with category selected
////                  
//                 if(item.getProductCategory().equalsIgnoreCase(catText)) {
//                     ItemsList.cpy_list.add(item);
//                 }
//                 
//                 
//             }
//             else{
//                 //means the product is from indix so filter it by indix category
//                 
//                 if(item.getProductCategory().equalsIgnoreCase(indix_category)) {
//                     ItemsList.cpy_list.add(item);
//                 }
//                 
//             }
//             
//             
//         }
//            
//            ItemsList.s_jobs.clear();
//            
//            for(int i=0;i<ItemsList.cpy_list.size();i++) {
//                System.out.println("index is ===============================" + i);
//               Item item=(Item)ItemsList.cpy_list.get(i);
//                ItemsList.s_jobs.add(item);
//            }
//            
//            //ItemsList.s_jobs=ItemsList.cpy_list;
//        
//            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
//            ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//            
//            ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
//            ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+" matches found");
//        
//        //bind the list with the new filtered list
//            BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//            vex.refresh();
//            AdfmfJavaUtilities.flushDataChangeEvent();
//            
//            if(ItemsList.s_jobs.size()==0) {
//                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
//                                                                           "displayNoMatch",
//                                                                           new Object[] { });
//            }
//            
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
        
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.filterItemsByCategory.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        return "";
    }
    
    
    

    public void itemsRangeChange(RangeChangeEvent rangeChangeEvent) {
        // Add event code here...
       
       pageNo= pageNo+1;
        
        if(itemType.equalsIgnoreCase("goods")){
        
        try{
        
       
                   // System.out.println("response===============================" + response);
                  
                   String url = "https://api.indix.com/v2/offersPremium/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+"&availability=IN_STOCK&lastRecordedIn=30&pageNumber="+pageNo+"&pageSize=10&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                                   
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
                                           //since for the indix suppliers they will not be diversed so update as -1
                                           String showDiverSeImage="false";
                                           String diverseImageURL="";
                                                      
                                             Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo,indixCategoryId,"","","","","","");
                                            providerChangeSupport.fireProviderCreate("assets", String.valueOf(randomInt), j);
                                             ItemsList.items_list.add(j); 
                                           
                                             
                                         
                                        }
                                       
                                       System.out.println("Size is "+ItemsList.s_jobs.size());
                                       
                                     
                                    //   System.out.println("***********");
                                   }
            
            
            ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
            ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+" matches found");
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



    public String clearFilter() {
        // Add event code here...
//        System.out.println("Item size is "+ItemsList.cpy_list.size());
//        ItemsList.s_jobs.clear();
//        for(int i=0;i<ItemsList.filt_list.size();i++) {
//            Item s=(Item)ItemsList.filt_list.get(i);
//            ItemsList.s_jobs.add(s);
//        }
//        
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//        vex.refresh();
//        
//        ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
//        ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+" matches found");
        
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.clearFilter.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        return "";
    }

    public void validateQuantityText(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        String qty=(String)ve7.getValue(AdfmfJavaUtilities.getAdfELContext());
        if((Double.parseDouble(qty)%1!=0)) {
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                         AdfmfJavaUtilities.getFeatureName(),
                                         "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                         "Quantity for item type goods cannot be decimal",
                                         null,
                                         null }); 
        }
        
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
                Item item=new Item(it.getPoNo(), it.getVendorName(), it.getVendorSiteCode(), it.getProductCategory(), it.getProductTitle(), it.getUnitPrice(), it.getImageUrl(), checked, it.getSource(), it.getUom(), it.getRowId(), it.getDiverseSupplier(), it.getShowDiverseImage(), it.getDiverseImageURL(), pageNo,it.getIndixCategoryId(),"","","","","","");
                ItemsList.s_jobs.add(item);
            }
        }
        
    }

    public void getNextPageItems(ActionEvent actionEvent) {
        // Add event code here...
//       
//        pageNo= pageNo+1;
//        
//        //check if pageno is already in cached list
//        boolean isPageLoaded=false;
//        for(int k=0;k<ItemsList.items_list.size();k++) {
//            Item i=(Item)ItemsList.items_list.get(k);
//            if(i.getPageNo()==pageNo) {
//                isPageLoaded=true;
//            }
//        }
//            
//        
//         
//         if(itemType.equalsIgnoreCase("goods")){
//         
//         try{
//         
//             
//             if(!isPageLoaded){
//                    // System.out.println("response===============================" + response);
//                   
//                    String url = "https://api.indix.com/v2/offersPremium/products"+"?"+"countryCode=US&q="+URLEncoder.encode(search)+"&availability=IN_STOCK&lastRecordedIn=30&pageNumber="+pageNo+"&pageSize=10&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
//                                    
//                                    URL obj = new URL(url);
//                                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//         
//                                    // optional default is GET
//                                    con.setRequestMethod("GET");
//         
//                                    //add request header
//                                   
//         
//                                    int responseCode = con.getResponseCode();
//                                    System.out.println("\nSending 'GET' request to URL : " + url);
//                                    System.out.println("Response Code : " + responseCode);
//         
//                                    BufferedReader in = new BufferedReader(
//                                            new InputStreamReader(con.getInputStream()));
//                                    String inputLine;
//                                    StringBuffer response1 = new StringBuffer();
//         
//                                    while ((inputLine = in.readLine()) != null) {
//                                            response1.append(inputLine);
//                                    }
//                                    in.close();
//         
//                                    //print result
//                                //    System.out.println(response1.toString());
//                         
//                                   JSONObject resp=new JSONObject(response1.toString());
//                                    JSONObject output=resp.getJSONObject("result");
//                                    JSONArray resArr=output.getJSONArray("products");
//            // System.out.println("resArr.length() "+resArr.length());
//                                    for(int i=0;i<resArr.length();i++) {
//                                       int rowCount=1;
//                                    //    System.out.println("***********");
//                                        JSONObject productObj=resArr.getJSONObject(i);
//                                   //     System.out.println(productObj.toString());
//                                     //   System.out.println("***********");
//                                        String poNo="";
//                                         String vendorName="";
//                                         String vendorSiteCode="";
//                                         String productCategory=productObj.getString("categoryName");
//                                         
//                                         String unitPrice=productObj.getString("minSalePrice");
//                                         String imageUrl=productObj.getString("imageUrl");
//                                         JSONObject stores=productObj.getJSONObject("stores");
//                                        
//                                        
//                                        Iterator<?> keys = stores.keys();
//
//                                        while( keys.hasNext() ) {
//                                            String key = (String)keys.next();
//                                            JSONObject store=stores.getJSONObject(key);
//                                            vendorName=store.getString("storeName");
//                                             String productTitle=store.getString("title");
//                                             Random randomGenerator = new Random();
//                                             int randomInt = randomGenerator.nextInt(1000000000);
//                                            //since for the indix suppliers they will not be diversed so update as -1
//                                            String showDiverSeImage="false";
//                                            String diverseImageURL="";
//                                                       
//                                              Item j = new Item(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice,imageUrl,"/images/uncheck.png","","Each",String.valueOf(randomInt),"-1",showDiverSeImage,diverseImageURL,pageNo);
//                                             providerChangeSupport.fireProviderCreate("assets", String.valueOf(randomInt), j);
//                                              ItemsList.items_list.add(j); 
//                                            
//                                              
//                                          
//                                         }
//                                        
//                                        
//                                        
//                                        
//                                        System.out.println("Size is "+ItemsList.s_jobs.size());
//                                        
//                                      
//                                     //   System.out.println("***********");
//                                    }
//             
//             }
//             
//             populateItemsBasedOnPageNo();
////             ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCount}", String.class);
////             ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),ItemsList.s_jobs.size()+" matches found");
//             
//             
//             
//             ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
//             ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//             
//             ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
//             ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//             
//             AdfmfJavaUtilities.flushDataChangeEvent();
//             
//             BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//             vex.setCurrentIndex(ItemsList.s_jobs.size());
//             vex.refresh();
//            
//             
//         
//         }
//         catch(Exception e){
//             e.printStackTrace();
//         }
//         
//         
//         
//         
//             
//             }
//         
         
            
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.getNextPageItems.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
        vex.setCurrentIndex(ItemsList.s_jobs.size());
        vex.refresh();
    }

    public void showPrevPageItems(ActionEvent actionEvent) {
        // Add event code here...
//
//          if(pageNo>1) {
//              pageNo=pageNo-1;
//              
//          }
//          else{
//            
//          }
//          
//          if(pageNo==1){
//              ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
//              ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
//          }
//          else{
//              ValueExpression ve71 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayPrev}", String.class);
//              ve71.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//          }
//          
//          populateItemsBasedOnPageNo();
//          
//        ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayNext}", String.class);
//        ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
//        AdfmfJavaUtilities.flushDataChangeEvent();
//        
//        
//        
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
//        vex.setCurrentIndex(ItemsList.s_jobs.size());
//        vex.refresh();
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showPrevPageItems.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets5.iterator}");   
        vex.setCurrentIndex(ItemsList.s_jobs.size());
        vex.refresh();
        
    }

    public void showCartItems(ActionEvent actionEvent) {
        // Add event code here...
        try{
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showCart.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
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
    }

    public void showCartDetailsScreen(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Hello Cart-->");
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showCartDetail.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

    public void showGallery(ActionEvent actionEvent) {
        
       
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "showGallery", new Object[] { });
        
//        
//        // Add event code here...
//        String photo1 =
//        DeviceManagerFactory.getDeviceManager().getPicture(40, DeviceManager.CAMERA_DESTINATIONTYPE_DATA_URL,
//                                                                       DeviceManager.CAMERA_SOURCETYPE_PHOTOLIBRARY, false,
//                                                                       DeviceManager.CAMERA_ENCODINGTYPE_JPEG, 1000, 1000);
//        
//        
//        
//        
//        
//        String img = "data:image/gif;base64," + photo1;
//        ValueExpression rfqImg = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
//        rfqImg.setValue(AdfmfJavaUtilities.getAdfELContext(), img);     
//        AdfmfJavaUtilities.flushDataChangeEvent();
        
        
        
    }

    public void onFrequencyPeriodChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("New value is "+valueChangeEvent.getNewValue().toString());
      
        ValueExpression contractTypeText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.frequencyPeriodText}", String.class);
        contractTypeText.setValue(AdfmfJavaUtilities.getAdfELContext(),valueChangeEvent.getNewValue().toString());
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.onFrequencyPeriodChange.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

    public void onContractTypeChanged(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("New value is "+valueChangeEvent.getNewValue().toString());
        
        ValueExpression contractTypeText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractText}", String.class);
        contractTypeText.setValue(AdfmfJavaUtilities.getAdfELContext(),valueChangeEvent.getNewValue().toString());
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.onContractTypeChanged.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

   

    public void serviceLocationChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("New value is "+valueChangeEvent.getNewValue().toString());
        
        ValueExpression contractTypeText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serviceLocationText}", String.class);
        contractTypeText.setValue(AdfmfJavaUtilities.getAdfELContext(),valueChangeEvent.getNewValue().toString());
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.serviceLocationChange.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

    public void doRefinedSearchServices(ActionEvent actionEvent) {
        // Add event code here...
        try{
            
        boolean isError=false;
        String error="";
            
        ValueExpression ve130 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        String search = (String)ve130.getValue(AdfmfJavaUtilities.getAdfELContext()); 
        

        ValueExpression ve411 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        itemType=(String)ve411.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        ValueExpression ve41 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchType}", String.class);
        ve41.setValue(AdfmfJavaUtilities.getAdfELContext(), "R");
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
        itemCategories = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        quantity = (String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
        String uom = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        String searchText = (String)ve61.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
        needByDate = (String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve_supp = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
        String supplierNames = (String)ve_supp.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userName = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String multiOrgId = (String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
            
        ValueExpression serviceFrequency = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups1}", String.class);
        String txtServiceFrequency = (String)serviceFrequency.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        ValueExpression contractType = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups}", String.class);
        String txtContractType = (String)contractType.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression serviceLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serviceLocations}", String.class);
        String txtServiceLocation = (String)serviceLocation.getValue(AdfmfJavaUtilities.getAdfELContext());
        System.out.println("serviceLocation => "+txtServiceLocation);
        
        ValueExpression serviceComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqCommentsServices}", String.class);
        String txtServiceComments = (String)serviceComments.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression multipleServiceLocations = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.multipleServiceLocations}", String.class);
        String txtMultipleServiceLocations = (String)multipleServiceLocations.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression currentServiceLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentServicelocation}", String.class);
        String txtCurrentServiceLocation = (String)currentServiceLocation.getValue(AdfmfJavaUtilities.getAdfELContext()); 
        
        ValueExpression frequencyPeriod = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups2}", String.class);
        String txtFrequencyPeriod = (String)frequencyPeriod.getValue(AdfmfJavaUtilities.getAdfELContext()); 
         
            
        //-----------------    
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
                       ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
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
            
            
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            
            
            /*   Error IN Date
             
            ValueExpression ve421 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
            ve421.setValue(AdfmfJavaUtilities.getAdfELContext(), "");
        */
            DeliverToLocationList deliverToLocationList =new DeliverToLocationList();
            
            ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
            String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            if(itemType.equalsIgnoreCase("services")){
                
                
                
                //get Service locations
                
                //get deliver to list
                restServiceAdapter = Model.createRestServiceAdapter();
                // Clear any previously set request properties, if any
                restServiceAdapter.clearRequestProperties();
                // Set the connection name
                restServiceAdapter.setConnectionName("enrich");
                
                restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_service_location/");
                postData= "{\n" + 
                "  \"GET_SERVICE_LOCATION_Input\" : {\n" + 
                "   \"RESTHeader\": {\n" + 
                "    },\n" + 
                "   \"InputParameters\": {\n" + 
                "          \"P_USER_ID\" : \""+userId+"\"\n" +        
                "       }          \n" + 
                "   }\n" + 
                "}";
                                            restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                    
                   response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response); 
                     resp=new JSONObject(response);
                     output=resp.getJSONObject("OutputParameters");
                   JSONObject data=new JSONObject();
                 try{
                     data=output.getJSONObject("X_SERVICE_LOC_TL");
                    ServiceLocationsList.s_jobs.clear();
                        serviceLocationList.clear();
                    if(data.get("X_SERVICE_LOC_TL_ITEM") instanceof  JSONArray){
                      JSONArray segments=data.getJSONArray("X_SERVICE_LOC_TL_ITEM");
                        ServiceLocation c2=new ServiceLocation("Please Select","Please Select","Please Select"); 
                        ServiceLocationsList.s_jobs.add(c2);
                        serviceLocationList.add(c2);
                      for(int i=0;i<segments.length();i++) {
                        JSONObject location=segments.getJSONObject(i);
                        String locationId=location.getString("LOCATION_ID");
                        String locationCode=location.getString("LOCATION_CODE");
                        String locationDescription=location.getString("DESCRIPTION");
                        ServiceLocation loc=new ServiceLocation(locationId, locationCode, locationDescription);
                        ServiceLocationsList.s_jobs.add(loc);
                        serviceLocationList.add(loc);
                      }
                    
                    }
                    
                    else if(data.get("X_SERVICE_LOC_TL_ITEM") instanceof  JSONObject){
                        ServiceLocation c2=new ServiceLocation("Please Select","Please Select","Please Select"); 
                        ServiceLocationsList.s_jobs.add(c2);
                        serviceLocationList.add(c2);
                       JSONObject location=data.getJSONObject("X_DELIVER_TO_TL_ITEM");
                        String locationId=location.getString("LOCATION_ID");
                        String locationCode=location.getString("LOCATION_CODE");
                        String locationDescription=location.getString("DESCRIPTION");
                        ServiceLocation loc=new ServiceLocation(locationId, locationCode, locationDescription);
                        ServiceLocationsList.s_jobs.add(loc);
                        serviceLocationList.add(loc);
                        
                    }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                
                
                 System.out.println("Deliver to location list is "+ServiceLocationsList.s_jobs.size());
                
                
                // contract types
                 
                restServiceAdapter = Model.createRestServiceAdapter();
                // Clear any previously set request properties, if any
                restServiceAdapter.clearRequestProperties();
                // Set the connection name
                restServiceAdapter.setConnectionName("enrich");
                
                restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_contract_type_lkup/");
                postData= "{\n" + 
                "  \"GET_CONTRACT_TYPE_Input\" : {\n" + 
                "   \"RESTHeader\": {\n" + 
                "    },\n" + 
                "   \"InputParameters\": {\n" + 
                "       }          \n" + 
                "   }\n" + 
                "}\n";
                                            restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + postData);
                    
                   response = restServiceAdapter.send(postData);
                    
                    System.out.println("response===============================" + response); 
                     resp=new JSONObject(response);
                     output=resp.getJSONObject("OutputParameters");
                     data=new JSONObject();
                 try{
                     data=output.getJSONObject("X_CONTRACT_TYPE_TL");
                    ContractLookupList.s_jobs.clear();
                     contractLookupList.clear();
                        
                    if(data.get("X_CONTRACT_TYPE_TL_ITEM") instanceof  JSONArray){
                      JSONArray segments=data.getJSONArray("X_CONTRACT_TYPE_TL_ITEM");
        //                       Lookup c2=new Lookup("Please Select","0","Please Select","");
        //                        ContractLookupList.s_jobs.add(c2);
                       
                      for(int i=0;i<segments.length();i++) {
                        JSONObject lookup=segments.getJSONObject(i);
                        String lookupType=lookup.getString("LOOKUP_TYPE");
                        String lookupCode=lookup.getString("LOOKUP_CODE");
                        String meaning=lookup.getString("MEANING");
                        String tag=lookup.getString("TAG");  
                        Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                        ContractLookupList.s_jobs.add(loc);
                          contractLookupList.add(loc);
                          
                          
                      }
                    
                    }
                    
                    else if(data.get("X_CONTRACT_TYPE_TL_ITEM") instanceof  JSONObject){
        //                        Lookup c2=new Lookup("Please Select","0","Please Select","");
        //                        ContractLookupList.s_jobs.add(c2);
                         
                        JSONObject lookup=data.getJSONObject("X_CONTRACT_TYPE_TL_ITEM");
                        String lookupType=lookup.getString("LOOKUP_TYPE");
                        String lookupCode=lookup.getString("LOOKUP_CODE");
                        String meaning=lookup.getString("MEANING");
                        String tag=lookup.getString("TAG");  
                        Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                        ContractLookupList.s_jobs.add(loc);
                        contractLookupList.add(loc);
                        
                    }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                 
                 
                 
                 // frequency periods
                 
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_freq_period_lkup/");
                 postData= "{\n" + 
                 "  \"GET_FREQ_PERIOD_LKUP_Input\" : {\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "    },\n" + 
                 "   \"InputParameters\": {\n" + 
                 "       }         \n" + 
                 "   }\n" + 
                 "}";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                    response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                      data=new JSONObject();
                  try{
                      data=output.getJSONObject("X_FREQ_PERIOD_TL");
                        FrequencyPeriodList.s_jobs.clear();
                      frequencyPeriodList.clear();
                         
                     if(data.get("X_FREQ_PERIOD_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_FREQ_PERIOD_TL_ITEM");
                         Lookup c2=new Lookup("Please Select","0","Please Select",""); 
                          FrequencyPeriodList.s_jobs.add(c2);
                          frequencyPeriodList.add(c2);
                       for(int i=0;i<segments.length();i++) {
                         JSONObject lookup=segments.getJSONObject(i);
                         String lookupType=lookup.getString("LOOKUP_TYPE");
                         String lookupCode=lookup.getString("LOOKUP_CODE");
                         String meaning=lookup.getString("MEANING");
                         String tag=lookup.getString("TAG");  
                         Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                                FrequencyPeriodList.s_jobs.add(loc);
                           frequencyPeriodList.add(loc);
                           
                       }
                     
                     }
                     
                     else if(data.get("X_FREQ_PERIOD_TL_ITEM") instanceof  JSONObject){
                         Lookup c2=new Lookup("Please Select","0","Please Select",""); 
                          FrequencyPeriodList.s_jobs.add(c2);
                         frequencyPeriodList.add(c2);
                         JSONObject lookup=data.getJSONObject("X_FREQ_PERIOD_TL_ITEM");
                         String lookupType=lookup.getString("LOOKUP_TYPE");
                         String lookupCode=lookup.getString("LOOKUP_CODE");
                         String meaning=lookup.getString("MEANING");
                         String tag=lookup.getString("TAG");  
                         Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                            FrequencyPeriodList.s_jobs.add(loc);
                         frequencyPeriodList.add(loc);
                         
                     }
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
                 
                 
                 // service frequency types
                  
                 restServiceAdapter = Model.createRestServiceAdapter();
                 // Clear any previously set request properties, if any
                 restServiceAdapter.clearRequestProperties();
                 // Set the connection name
                 restServiceAdapter.setConnectionName("enrich");
                 
                 restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
                 restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
                 restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
                 restServiceAdapter.addRequestProperty("Content-Type", "application/json");
                 restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_service_freq_lkup/");
                 postData= "{\n" + 
                 "  \"GET_SERVICE_FREQ_LKUP_Input\" : {\n" + 
                 "   \"RESTHeader\": {\n" + 
                 "    },\n" + 
                 "   \"InputParameters\": {\n" + 
                 "       }         \n" + 
                 "   }\n" + 
                 "}";
                                             restServiceAdapter.setRetryLimit(0);
                    System.out.println("postData===============================" + postData);
                     
                    response = restServiceAdapter.send(postData);
                     
                     System.out.println("response===============================" + response); 
                      resp=new JSONObject(response);
                      output=resp.getJSONObject("OutputParameters");
                      data=new JSONObject();
                  try{
                      data=output.getJSONObject("X_SERVICE_FREQUENCY_TL");
                     ServiceFrequencyTypeList.s_jobs.clear();
                      serviceFrequencyTypeList.clear();
                         
                     if(data.get("X_SERVICE_FREQUENCY_TL_ITEM") instanceof  JSONArray){
                       JSONArray segments=data.getJSONArray("X_SERVICE_FREQUENCY_TL_ITEM");
        //                         Lookup c2=new Lookup("Please Select","0","Please Select","");
        //                          ServiceFrequencyTypeList.s_jobs.add(c2);
                       for(int i=0;i<segments.length();i++) {
                         JSONObject lookup=segments.getJSONObject(i);
                         String lookupType=lookup.getString("LOOKUP_TYPE");
                         String lookupCode=lookup.getString("LOOKUP_CODE");
                         String meaning=lookup.getString("MEANING");
                         String tag=lookup.getString("TAG");  
                         Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                         ServiceFrequencyTypeList.s_jobs.add(loc);
                           serviceFrequencyTypeList.add(loc);
                           
                       }
                     
                     }
                     
                     else if(data.get("X_SERVICE_FREQUENCY_TL_ITEM") instanceof  JSONObject){
        //                         Lookup c2=new Lookup("Please Select","0","Please Select","");
        //                          ServiceFrequencyTypeList.s_jobs.add(c2);
                         JSONObject lookup=data.getJSONObject("X_SERVICE_FREQUENCY_TL_ITEM");
                         String lookupType=lookup.getString("LOOKUP_TYPE");
                         String lookupCode=lookup.getString("LOOKUP_CODE");
                         String meaning=lookup.getString("MEANING");
                         String tag=lookup.getString("TAG");  
                         Lookup loc=new Lookup(lookupType, lookupCode, meaning,tag);
                         ServiceFrequencyTypeList.s_jobs.add(loc);
                         serviceFrequencyTypeList.add(loc);
                         
                     } 
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }
                 
               
                  
                 
                
            }
        }catch(Exception e){
            e.printStackTrace();    
        }
            
            
       //-----------------------------     
            
            
            
        
        
        if(txtServiceFrequency!=null && !txtServiceFrequency.equalsIgnoreCase("")) {
            //Lookup loc=(Lookup)ServiceFrequencyTypeList.s_jobs.get(Integer.parseInt(txtServiceFrequency));
            Lookup loc=(Lookup)serviceFrequencyTypeList.get(Integer.parseInt(txtServiceFrequency));
            txtServiceFrequency=loc.getMeaning();
        }
        else{
           
        }
        
        if(txtFrequencyPeriod!=null && !txtFrequencyPeriod.equalsIgnoreCase("")) {
            //Lookup loc=(Lookup)FrequencyPeriodList.s_jobs.get(Integer.parseInt(txtFrequencyPeriod));
            Lookup loc=(Lookup)frequencyPeriodList.get(Integer.parseInt(txtFrequencyPeriod));
            txtFrequencyPeriod=loc.getMeaning();
        }
        else{
           
        }
        
        System.out.println("txtFrequencyPeriod => "+txtFrequencyPeriod);
        
        ValueExpression serviceEndDate = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
        String txtServiceEndDate = (String)serviceEndDate.getValue(AdfmfJavaUtilities.getAdfELContext());   
        
        if(itemCategories==null || itemCategories.equalsIgnoreCase("")){
            isError=true;
            error="category";
        }
        
        if(txtServiceFrequency==null || txtServiceFrequency.equalsIgnoreCase("")){
            isError=true;
            error="serviceFrequency";
        }
        if(txtContractType==null || txtContractType.equalsIgnoreCase("")){
            isError=true;
            error="contractType";
        }
         if(needByDate==null || needByDate.equalsIgnoreCase("")){
            isError=true;
            error="needByDate";
            System.out.println("need by date is => "+needByDate);
        }
         if(txtServiceLocation==null || txtServiceLocation.equalsIgnoreCase("")){
             isError=true;
             error="serviceLocation";
         }
         
       

        
         if(txtContractType!=null && txtContractType.equalsIgnoreCase("Time and Material")){
             
             if(quantity==null || quantity.equalsIgnoreCase("")){
                 isError=true;
                 error="quantity";
             }
             if(uom==null || uom.equalsIgnoreCase("")){
                 isError=true;
                 error="uom";
             }
         }
         
         //---new
         if(txtServiceFrequency !=null && txtServiceFrequency.equalsIgnoreCase("Recurring")){
             if(txtFrequencyPeriod == null || txtFrequencyPeriod.equalsIgnoreCase("")){
                isError = true;
                error = "txtFrequencyPeriod";
             }
             if(txtServiceEndDate == null || txtServiceEndDate.equalsIgnoreCase("")){
                isError = true;
                error = "txtServiceEndDate";
            }
         }
         
         //--new end

            if (txtServiceComments == null || txtServiceComments.equalsIgnoreCase("")) {
                isError = true;
                error = "serviceComments";
            }
         
         System.out.println("txtServiceLocation "+txtServiceLocation);
         
            if(txtServiceLocation!=null){
                
              if(txtServiceLocation.equalsIgnoreCase("Multiple")){
                 if(txtMultipleServiceLocations==null || txtMultipleServiceLocations.equalsIgnoreCase("")){
                    isError=true;
                     error="multipleLocation";
                 }
              }
              if(txtServiceLocation.equalsIgnoreCase("Current")){
                   if(txtCurrentServiceLocation==null || txtCurrentServiceLocation.equalsIgnoreCase("")){
                      isError=true;
                       error="currentLocation";
                   }
              }
                
            }
        
            if(!isError){
                
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
                restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/refined_search/");
                String data="";
                    
                    if(supplierNames==null || supplierNames.equalsIgnoreCase("")){
                    
                data= "{\n" + 
                "  \"REFINED_SEARCH_Input\" : {\n" + 
                "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/refined_search/\",\n" + 
                "   \"RESTHeader\": {\n" + 
                "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                 
                 
                 
                 
                 
                "    }, \n" + 
                "   \"InputParameters\": {\n" + 
                "        \"P_REF_SEARCH_ATTRIBS_TL\": { \n" + 
                "             \"CATEGORY\"  : \""+itemCategories+"\", \n" + 
                        "             \"SUPPLIER_TL\"  :{\n" +             
                        "             \"SUPPLIER_TL_ITEM\"  : [\n ]\n"+                      
                        "             }, \n" +    
                "                 \"USER_ID\"  : \""+userName+"\",\n" +
                "                 \"ORG_ID\"  : \""+multiOrgId+"\"\n" +      
                "        }\n" + 
                "     }\n" + 
                "  }\n" + 
                "}  \n";       
                    }
                    else{
                        
                        StringBuffer sb=new StringBuffer();
                        String arr[]=supplierNames.split(",");
                        String supp="";
                        for(int k=0;k<arr.length;k++)
                        {
                            supp=supp+"\""+arr[k]+"\",";
                        }
                        
                        if (supp.length() > 0 && supp.charAt(supp.length()-1)==',') {
                              supp = supp.substring(0, supp.length()-1);
                            }
                        
                        
                        System.out.println("Suppliers "+supp);
                        
                        data= "{\n" + 
                        "  \"REFINED_SEARCH_Input\" : {\n" + 
                        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/refined_search/\",\n" + 
                        "   \"RESTHeader\": {\n" + 
                        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
                         
                         
                         
                         
                         
                        "    }, \n" + 
                        "   \"InputParameters\": {\n" + 
                        "        \"P_REF_SEARCH_ATTRIBS_TL\": { \n" + 
                        "             \"CATEGORY\"  : \""+itemCategories+"\", \n" + 
                        "             \"SUPPLIER_TL\"  :{\n" +             
                        "             \"SUPPLIER_TL_ITEM\"  : [\n" +supp+"]\n"+                      
                        "             }, \n" +                     
                        "                 \"USER_ID\"  : \""+userName+"\",\n" +
                        "                 \"ORG_ID\"  : \""+multiOrgId+"\"\n" +              
                        "        }\n" + 
                        "     }\n" + 
                        "  }\n" + 
                        "}  \n";       
                        
                    }
                    
                   
                   restServiceAdapter.setRetryLimit(0);
                   System.out.println("postData===============================" + data);
                    
                    String response = restServiceAdapter.send(data);
                    System.out.println("response===============================" + response);
                   JSONObject resp=new JSONObject(response);
                   JSONObject output=resp.getJSONObject("OutputParameters");
                    String result_count=output.getString("X_RESULT_COUNT");
                    
                    if(supplierNames==null || supplierNames.equalsIgnoreCase("")){
                        System.out.println("Inside found false");
                        proceedRefinedSearchServices("");
                        found=false;
                        resultCount=result_count;
                    }
                    else{
                    if(result_count!=null && !result_count.equalsIgnoreCase("") && !result_count.equalsIgnoreCase("0")){
                        found=true;
                        resultCount=result_count;
                    }
                    
                    
                    if(!found) {
                        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                   "confirm_refined_search",
                                                                                   new Object[] { });
                    }
                    else{
                        proceedRefinedSearchServices("");
                    }
                    
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
                
            }
            else{
                
                if(error.equalsIgnoreCase("category")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Service category is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("serviceFrequency")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Service frequency is mandatory.",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("contractType")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Contract type is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("needByDate")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Need by date is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("serviceLocation")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Service location is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("serviceComments")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Description is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("quantity")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Quantity is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("uom")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "UOM is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("multipleLocation")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Location name is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("currentLocation")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Location name is mandatory",
                                                 null,
                                                 null }); 
                }//----------
                else if(error.equalsIgnoreCase("txtFrequencyPeriod")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "Frequency Period is mandatory",
                                                 null,
                                                 null }); 
                }
                else if(error.equalsIgnoreCase("txtServiceEndDate")){
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "End Date is mandatory",
                                                 null,
                                                 null }); 
                }
                //--
                else{
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                 AdfmfJavaUtilities.getFeatureName(),
                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                 "error",
                                                 null,
                                                 null });  
                }
            }
            
            
    }
        catch(Exception e) {
            
            e.printStackTrace();
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
        
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                  "adf.mf.api.amx.addMessage", new Object[] {
                                                                  AdfException.ERROR,
                                                                  "Inside Exception===> " + sw.toString(),
                                                                 null, null
                    });
        }
        ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasIndixItemcategories}", String.class);
        ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
    }
        

  public void proceedRefinedSearchServices(String rr){
      
      
      ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.resultCountServices}", String.class);
      ve.setValue(AdfmfJavaUtilities.getAdfELContext(),resultCount);
      
      MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.proceedRefinedSearchServices.execute}", Object.class, new Class[] {});
      me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
  
  }
  
    public void LocationHandler(Location currentPosition) {
        
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                     AdfmfJavaUtilities.getFeatureName(),
                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                     "Lat:"+currentPosition.getLatitude()+" Long:"+currentPosition.getLongitude(),
                                     null,
                                     null });    
    }
    
    
    public void addAttachment(String rr){
        try{
            
        //  System.out.println("=========>"+EncodeBased64Binary.encodeFileToBase64Binary(rr));
           
        ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.os}", String.class);
        String device_os=(String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
            File folder;  
            if(device_os.equalsIgnoreCase("iOS")){
                String path=System.getProperty("user.dir");
                path=path.replace("/Documents", "/tmp");
                folder = new File(path);
            }
            else{
                folder = new File("//data//data//com.enrich.goprocure//cache");    
            }
            
            
            
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                   // System.out.println("=========>"+EncodeBased64Binary.encodeFileToBase64Binary(file.getPath())); 
                 if(!selectedImages.contains(file.getPath()) && !file.getPath().contains("shaders_cache")){
                   selectedImages.add(file.getPath());
                 
                   
                    ValueExpression imgPath = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refinedImagePath}", String.class);
                    imgPath.setValue(AdfmfJavaUtilities.getAdfELContext(),file.getPath());
                    
                    MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.addToSelectedImage.execute}", Object.class, new Class[] {});
                    me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
                  }
                    
                    
                    ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
                    ve.setValue(AdfmfJavaUtilities.getAdfELContext(),ImageList.imageList.size()+" images selected");
                   AdfmfJavaUtilities.flushDataChangeEvent();
                    
                    
//                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
//                                                 AdfmfJavaUtilities.getFeatureName(),
//                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
//                                                 EncodeBased64Binary.encodeFileToBase64Binary(file.getPath()),
//                                                 null,
//                                                 null }); 
                }
            }


            
           // System.out.println("=========>"+EncodeBased64Binary.encodeFileToBase64Binary(rr));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public String refreshImageList() {
        // Add event code here...
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.images.iterator}");   
        vex.refresh();
        
        return null;
    }

    public void deleteImage(ActionEvent actionEvent) {
        // Add event code here...
        try{
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.imageURL}", String.class);
        String imageUrl = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        AdfmfJavaUtilities.flushDataChangeEvent();
        
//         System.out.println("Image URL =>"+imageUrl);
        for(int i=0;i<selectedImages.size();i++){
             System.out.println("Selected For Loop Images:: =>"+imageUrl);
            if(selectedImages.get(i).equalsIgnoreCase(imageUrl)){
                System.out.println("Image Value in IF Loop =>"+imageUrl+" =>"+selectedImages.get(i));
                selectedImages.remove(i);
                //ImageList.imageList.remove(i);
                //EncodeBased64Binary.encodeFileToBase64Binary(selectedImages.get(i))
            }
            
        }
            
        /*
            for(int i=0;i<ImageList.imageList.size();i++){
                System.out.println("Image List Value In For Loop:: =>"+imageUrl);
                Image img=(Image)ImageList.imageList.get(i);
                if(img.getImageURL().equalsIgnoreCase(imageUrl)){
                    System.out.println("Image List Value in If Loop:: =>"+imageUrl+" =>"+img.getImageURL());
                    ImageList.imageList.remove(i);
                    //ImageList.imageList.remove(i);
                    //EncodeBased64Binary.encodeFileToBase64Binary(selectedImages.get(i))
                }
                
            }
*/
            
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.clearImage.execute}", Object.class, new Class[] {});
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
            
        
//        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
//        ve1.setValue(AdfmfJavaUtilities.getAdfELContext(),ImageList.imageList.size()+" images selected");
//        AdfmfJavaUtilities.flushDataChangeEvent();
//        System.out.println("Page Flow Scope In Selected Image:: =>"+ImageList.imageList.size());
//        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.images.iterator}");   
        vex.refresh();
        }
        catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                          "adf.mf.api.amx.addMessage", new Object[] {
                                                          AdfException.ERROR,
                                                          "Inside Exception===> " + sw.toString(),
                                                          null, null
            });
        }
        
    }
    public void makeSearch(String rr) {
        ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        ve3.setValue(AdfmfJavaUtilities.getAdfELContext(),rr);
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasIndixItemcategories}", String.class);
        String itemAliasIndixId = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchValue}", String.class);
        String searchValue= (String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
        String oracleId = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve91 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.rdItemType}", String.class);
        String itemType=(String)ve91.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        System.out.println("Item Type--->"+itemType);
        
        System.out.println("++++itemAliasIndixId++"+itemAliasIndixId+"++++searchValue+++"+searchValue+"oracle ID "+ oracleId+"Alias indix length"+itemAliasIndixId.length());
        if(itemType.equalsIgnoreCase("goods"))
        {
        if(!oracleId.equals("") && !itemAliasIndixId.equals("") && !oracleId.equalsIgnoreCase("Please Select"))
        {
        doSearch();
        }
        
        else {  
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                                  "SearchWithItemCategories",
                                                                                                  new Object[] { });
        }
        }
        else {
            doSearch();
        }
    }

    public String clearAttachments() {
        // Add event code here...
        
        try{
            //path for android
            File folder;
            
            ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.os}", String.class);
            String device_os=(String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(device_os.equalsIgnoreCase("iOS")){
                    String path=System.getProperty("user.dir");
                    path=path.replace("/Documents", "/tmp");
                    folder = new File(path);
                }
                else{
                    folder = new File("//data//data//com.enrich.goprocure//cache");    
                }
            
            
            //folder = new File("//data//data//com.enrich.goprocure//cache");
            
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    file.delete();
                }
            }           
            
        }catch(Exception e){
            e.printStackTrace();    
        }
        
        return "__back";        
    }
    public void goBack(String rr) {
          AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                                        "adf.mf.api.amx.doNavigation", new Object[] { "__back" });
      }
    public static void clearRefinedSearch(ActionEvent actionEvent) {
        ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasCategorieEmpty}", String.class);
        ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
        ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
        ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
        ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
        ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
        ve16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        ve17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
        ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
        ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
        ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
        ValueExpression veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
        veComments.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
        veImage.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression veImageCount = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
        veImageCount.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve21 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
        ve21.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription1}", String.class);
        ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
        ve23.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve24 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription3}", String.class);
        ve24.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        AdfmfJavaUtilities.flushDataChangeEvent();
        selectedImages.clear();
        
    }
    public static void clearRefinedServices(ActionEvent actionEvent) {
        selectedImages.clear();
        CategoryList1.categories.clear();
        CategoryList2.categories.clear();
        CategoryList3.categories.clear();
        CategoryList4.categories.clear();
        ImageList.imageList.clear();
        
//        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator1.iterator}");  
//        vex.refresh();
//        BasicIterator vex1 = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator2.iterator}");  
//        vex1.refresh();
//        BasicIterator vex2 = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator3.iterator}");  
//        vex2.refresh();

        ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
        ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
        ValueExpression ve117 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasCategorieEmpty}", String.class);
        ve117.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        
            
        ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
        ve12.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.web_url}", String.class);
        ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.part_no}", String.class);
        ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
        ve15.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.upc}", String.class);
        ve16.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        ve17.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve18 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom1}", String.class);
        ve18.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayAddToCart}", String.class);
        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
        ValueExpression ve20 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displaySearchCount}", String.class);
        ve20.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
        ValueExpression veComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqComments}", String.class);
        veComments.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression veImage = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqImage}", String.class);
        veImage.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression serviceFrequency = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups1}", String.class);
        serviceFrequency.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression showContract = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showContractTypeAttrb}", String.class);
        showContract.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression contractDes = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription}", String.class);
        contractDes.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression contractDes1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription1}", String.class);
        contractDes1.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression contractDes2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
        contractDes2.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression contractDes3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription3}", String.class);
        contractDes3.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression searchSupp = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchSupplierValue}", String.class);
        searchSupp.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression contractType = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups}", String.class);
        contractType.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression serviceLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serviceLocations}", String.class);
        serviceLocation.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression serviceComments = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.rfqCommentsServices}", String.class);
        serviceComments.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression multipleServiceLocations = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.multipleServiceLocations}", String.class);
        multipleServiceLocations.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression currentServiceLocation = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentServicelocation}", String.class);
        currentServiceLocation.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression frequencyPeriod = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.contractLookups2}", String.class);
        frequencyPeriod.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression serviceEndDate = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.end_date}", String.class);
        serviceEndDate.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression veImageCount = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
        veImageCount.setValue(AdfmfJavaUtilities.getAdfELContext(),"");                
        ValueExpression need_by_date = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
        need_by_date.setValue(AdfmfJavaUtilities.getAdfELContext(),""); 
//        ValueExpression cate1 = AdfmfJavaUtilities.getValueExpression("#{bindings.categories1.inputValue}", String.class);
//        cate1.setValue(AdfmfJavaUtilities.getAdfELContext(),"");                
//        ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category1Name}", String.class);
//        ve62.setValue(AdfmfJavaUtilities.getAdfELContext(),""); 
//        ValueExpression ve63 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category2Name}", String.class);
//        ve62.setValue(AdfmfJavaUtilities.getAdfELContext(),""); 
        AdfmfJavaUtilities.flushDataChangeEvent();
        
    }

    public void getAliasCategories(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("New Alias value is "+valueChangeEvent.getNewValue().toString());
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasText}", String.class);
        ve61.setValue(AdfmfJavaUtilities.getAdfELContext(), valueChangeEvent.getNewValue().toString());
        
        ValueExpression ve31 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasText}", String.class);
        String  pos = (String)ve31.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        System.out.println("String Position-->"+pos);
        
        Alias c=(Alias)AliasList.s_jobs.get(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
        System.out.println(c.getName()+" "+c.getOracleCategotySeg()+" "+c.getIndixId());
        String aliasText="";
       if(!c.getName().equalsIgnoreCase("Please Select")){
       aliasText=c.getName();
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
        ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),c.getOracleCategotySeg());
        
        
        
           String sample= c.getIndixId();
           String sample1 ="&categoryId="+sample;
           System.out.println("*******"+ sample1);
           String aliasIndixValues = "";
         
         if(!sample1.equals("")) {
             
             if(sample1.contains("[")) {
                 String spec1 = c.getIndixId().substring(0,c.getIndixId().length()-2);
                 String value1= spec1.replaceAll("\\[\"", "&categoryId=");
                 aliasIndixValues = value1.replaceAll("\",\"", "&categoryId=");
                 //System.out.println("<<Hello>>"+aliasIndixValues);
             }
             else if(sample1.contains("null")) {
                 aliasIndixValues = sample1;
                 //System.out.println("<<null>>"+aliasIndixValues);
             }
             else {
                 String spec1 = c.getIndixId();
                 aliasIndixValues ="&categoryId="+spec1;
                // System.out.println("<<Hello>>"+aliasIndixValues);
             }
         }
         
       
        ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasIndixItemcategories}", String.class);
        ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),aliasIndixValues);
        AdfmfJavaUtilities.flushDataChangeEvent();
       }
       else {
           ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasOracleItemcategories}", String.class);
           ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
           ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasIndixItemcategories}", String.class);
           ve7.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
           AdfmfJavaUtilities.flushDataChangeEvent();
       }


        
   
    }
    
    public void clearPotentialSuppliers(ActionEvent actionEvent) {
        // Add event code here...
        
        ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchSupplierValue}", String.class);
        ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
           ValueExpression ve119 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.suppliers}", String.class);
           ve119.setValue(AdfmfJavaUtilities.getAdfELContext(),"");   
        ValueExpression showPotentialSupplier = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.potentialSuppliers}", String.class);
        showPotentialSupplier.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
       }
    public void getAliasCategoiresValues(String rr) {
            ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.aliasIndixItemcategories}", String.class);
            ve11.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        try{
            System.out.println("--------------ALIASSSS-----");
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
            restServiceAdapter.setRequestURI("/webservices/rest/XXETailSpendAPI/get_category_alias/");
            String postData= "{\n" + 
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
                
               String response = restServiceAdapter.send(postData);
                
                System.out.println("response===============================" + response); 
                JSONObject resp=new JSONObject(response);
                JSONObject output=resp.getJSONObject("OutputParameters");
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
                    Alias als=new Alias(aliasname, oracleCategotyId, indixCategotyId, oracleCategotySeg);
                    AliasList.s_jobs.add(als);
                    aliasList.add(als);
                }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            
            
            // =====Alias End
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        AmxAttributeBinding AliasToList = (AmxAttributeBinding) AdfmfJavaUtilities
                          .evaluateELExpression("#{bindings.aliass}");
        AmxIteratorBinding amxListIterator1 =  AliasToList.getIteratorBinding();
        amxListIterator1.refresh();
        }

  /*  public void checkDefaultMultiOrg(String rr) {
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.Default_MultiOrg}", String.class);
        String default_mulOrg = (String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
        System.out.println("In Search Page"+default_mulOrg);
    }*/
    
 /* public void newpop(String rr) {
      
      
      //ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.Default_MultiOrgProfile}", String.class);
      ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
      String defaul_MultitOrg=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
      
      System.out.println("Popup Value in Default Org"+defaul_MultitOrg);
      try{
       System.out.println("Hello POPup----->"+defaul_MultitOrg);
          if(defaul_MultitOrg.contains("{\"@xsi:nil\":\"true\"}")) {
              //the error object is filled with the reason for a JS exception. If the variable is empty, no exception has 
              //occured. Note that usually you use the return object to pass information from the JS function back to MAF.
              //In this case, the function doesn't return a value and so its only used here to allow debugging of exceptions 
              Object errorMsg = AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
                                                                        "popupUtilsShowPopup", new Object[] {
                                                                        "_popShowMultiId" });
              
              ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayMultiOrgDrop}", String.class);
              ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");              
              //isPopupOpen = true;

              //wait a few seconds and then close the popup
   /*           Timer timer = new Timer();
              timer.schedule(new TimerTask() {
                  @Override
                  public void run() {
                    closePopup();
                    this.cancel();
                  }

              },50000);*/

     /*     }
          else {
              ValueExpression ve72 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.displayMultiOrgDrop}", String.class);
              ve72.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
              System.out.println("Not inside into Pop-UP");
          }
          
      }
      catch(Exception e) {
          
          e.printStackTrace();
      }
  }*/

  /*  public void gotoProfile(ActionEvent actionEvent) {
        System.out.println("Go to Profile Page");
        AdfmfContainerUtilities.gotoFeature("mp.Userprofile");
        AdfmfContainerUtilities.resetFeature("mp.Userprofile",true);
        // Add event code here...
    }*/
    
    public void showManagePopup(String rr) {
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
    
    public void getRejectionReasons(String rr) {
            
            //Get Rejection Code Reasons for dropdown
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
               "       }          \n" + 
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
               
}

    public void rowCostCenterChange(ActionEvent actionEvent) {
        // Add event code here...
        
     /*   BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
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
             item.setNaturalAccount("");
            }
            System.out.println("new Item Cost Center"+item.getCostCenter());    
         }
        vex.refresh();*/
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.rowCostCenterChange.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
        
    }

    public void getGLAccountValue(ActionEvent actionEvent) {
        // Add event code here...
        /*ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
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
        */
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.getGLAccountValue.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
        
    }

    public void rowGLAccountChange(ActionEvent actionEvent) {
        // Add event code here...
       /* BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
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
        vex.refresh();*/
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.rowGLAccountChange.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }

    public void getCostCenterValue(ActionEvent actionEvent) {
        // Add event code here...
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.user_id}", String.class);
        String userId = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ValueExpression vemul = AdfmfJavaUtilities.getValueExpression("#{applicationScope.default_multi_org_id}", String.class);
        String multiOrgId=(String)vemul.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve_searchText = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchCostCenterText}", String.class);
        String searchText=(String)ve_searchText.getValue(AdfmfJavaUtilities.getAdfELContext());
        System.out.println("Search Txt-->"+searchText+"Cost Center List"+CostCenterList.s_jobs.size()+"Deliver To Location"+DeliverToLocationList.s_jobs.size()+"Natural Account"+NaturalAcccountList.acc_List.size());
        
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.assets2.iterator}");
        ValueExpression ve_row = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.cartRowId}", String.class);
        String rowId=(String)ve_row.getValue(AdfmfJavaUtilities.getAdfELContext());
        try{
            List costCenterList = new ArrayList();
            
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
                                                            
                            AmxAttributeBinding accountList = (AmxAttributeBinding) AdfmfJavaUtilities
                                              .evaluateELExpression("#{bindings.costCenters}");
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

    public void freeformAddtoCart(ActionEvent actionEvent) {
        // Add event code here...
        try{
            
            boolean isError=false;
            String error="";

            //Free Form Values

            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemType}", String.class);
            String itemType=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemNo}", String.class);
            String itemNo=(String)ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemDescriptionFrom}", String.class);
            String itemDescription=(String)ve3.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemCategoryForm}", String.class);
            String itemCategory=(String)ve4.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RequestTypeForm}", String.class);
            String reqType=(String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
            String qty=(String)ve6.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve7 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.UOMFrom}", String.class);
            String uom=(String)ve7.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve8 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.MaxPriceFrom}", String.class);
            String maxPrice=(String)ve8.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve9 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.DeliverLocationForm}", String.class);
            String deliverLoc=(String)ve9.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve10 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.need_by_date}", String.class);
            String needByDate=(String)ve10.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve11 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierForm}", String.class);
            String supplier=(String)ve11.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve12 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierNotForm}", String.class);
            String supplierNot=(String)ve12.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.supplierSiteForm}", String.class);
            String supplierSite=(String)ve13.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costcenterForm}", String.class);
            String costCenter=(String)ve14.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve15 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.GLAccountForm}", String.class);
            String glAccount=(String)ve15.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve16 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.partnoForm}", String.class);
            String partNo=(String)ve16.getValue(AdfmfJavaUtilities.getAdfELContext());
            ValueExpression ve17 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.referencenoForm}", String.class);
            String refNo=(String)ve17.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            System.out.println("itemType+itemNo+itemDescription+itemCategory+reqType+qty+uom+maxPrice+deliverLoc+needByDate+supplier+supplierNot+supplierSite+costCenter+glAccount+partNo+refNo-->"+itemType+itemNo+itemDescription+itemCategory+reqType+qty+uom+maxPrice+deliverLoc+needByDate+supplier+supplierNot+supplierSite+costCenter+glAccount+partNo+refNo);
          
          
            if(itemType==null || itemType.equalsIgnoreCase("")){
                isError=true;
                error="itemType";
            }
            
            if(itemCategory==null || itemCategory.equalsIgnoreCase("")){
                isError=true;
                error="itemCategory";
            }
            if(reqType==null || reqType.equalsIgnoreCase("")){
                isError=true;
                error="reqType";
            }
            if(qty==null || qty.equalsIgnoreCase("")){
                     isError=true;
                     error="quantity";
                 }

            if(uom==null || uom.equalsIgnoreCase("")){
                     isError=true;
                     error="uom";
                 }

            if(maxPrice==null || maxPrice.equalsIgnoreCase("")){
                     isError=true;
                     error="maxPrice";
                 }

             if(deliverLoc==null || deliverLoc.equalsIgnoreCase("")){
                 isError=true;
                 error="deliverLoc";
             }

             if(needByDate==null || needByDate.equalsIgnoreCase("")){
                isError=true;
                error="needByDate";
                System.out.println("need by date is => "+needByDate);
            }
            
            
            if(!isError){            
                
            ItemType it=(ItemType)ItemTypeList.itemType_List.get((Integer.parseInt(itemType)));
            System.out.println("Item Type-->"+it.getLineTypeCode()+it.getLineTypeCode());
            
            Alias al=(Alias)AliasList.s_jobs.get((Integer.parseInt(itemCategory)));
            System.out.println("Alias-->"+al.getOracleId()+al.getIndixId());
            
            RequestType rt=(RequestType)RequestTypeList.req_List.get((Integer.parseInt(reqType)));
            System.out.println("Req Type-->"+rt.getLookupCode()+rt.getMeaning());
            
            UOM um=(UOM)UOMList.s_jobs.get((Integer.parseInt(uom)));
            System.out.println("UOM-->"+um.getName());
            
            DeliverToLocation loc=(DeliverToLocation)DeliverToLocationList.s_jobs.get((Integer.parseInt(deliverLoc)));
            System.out.println("Deliver Loc-->"+loc.getCode()+loc.getDescription());
            
            String indixCategoryId="";
            
            
                double result = Double.parseDouble(qty) * Double.parseDouble(maxPrice);
                String amount = Double.toString(result); 
            
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(1000000000);
             
            //SelectedItem sel= new SelectedItem(poNo, vendorName, vendorSiteCode, productCategory, productTitle, unitPrice, imageUrl, checked, source, uom, quantity, deliver_to_location, need_by_date, amount, rowId, costCenter, itemRef, indixCategoryId, indixAttributes, naturalAccount, costCenterNaturalAccount, itemType, itemNo, vendorPartNo, maxEstPrice, lineReqType, supplierName, supplierSite, suppliernotknown, internalRefNo, supplierpartNo, formAttachment)
                
            SelectedItem selectItem= new SelectedItem("", supplier, supplierSite, al.getOracleCategotySeg(), itemDescription, maxPrice, "", "true", "U", um.getName(), qty, deliverLoc, needByDate, amount, String.valueOf(randomInt), costCenter, String.valueOf(randomInt), indixCategoryId, "", glAccount, "", it.getLineTypeCode(), itemNo, "", maxPrice, rt.getLookupCode(), supplier, supplierSite, supplierNot, refNo, partNo, "");
            SelectedItemsList.s_jobs.add(selectItem) ;
            System.out.println("<---Selected item List--->"+SelectedItemsList.s_jobs.size());
            int count=SelectedItemsList.s_jobs.size();
            ValueExpression ve_cart = AdfmfJavaUtilities.getValueExpression("#{applicationScope.unreadCount}", String.class);
            ve_cart.setValue(AdfmfJavaUtilities.getAdfELContext(), String.valueOf(count));
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            }
           else {
                if(error.equalsIgnoreCase("itemType")){
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Item Type is mandatory",
                                                                 null,
                                                                 null }); 
                                }
                                else if(error.equalsIgnoreCase("itemCategory")){
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Item Category is mandatory.",
                                                                 null,
                                                                 null }); 
                                }
                                else if(error.equalsIgnoreCase("reqType")){
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Request Type is mandatory",
                                                                 null,
                                                                 null }); 
                                }
                                else if(error.equalsIgnoreCase("quantity")){
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Quantity is mandatory",
                                                                 null,
                                                                 null }); 
                                }
                                else if(error.equalsIgnoreCase("uom")){
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "UOM is mandatory",
                                                                 null,
                                                                 null }); 
                                }
                                else if(error.equalsIgnoreCase("maxPrice")){
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Max.Esitimated Price is mandatory",
                                                                 null,
                                                                 null }); 
                                }
                                else if(error.equalsIgnoreCase("deliverLoc")){
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Deliver to Location is mandatory",
                                                                 null,
                                                                 null }); 
                                }
                                else if(error.equalsIgnoreCase("needByDate")){
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                                                 AdfmfJavaUtilities.getFeatureName(),
                                                                 "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                                                 "Need By Date is mandatory",
                                                                 null,
                                                                 null }); 
                                }
                                
                                
            }
            
        }
        catch(Exception e){
                     e.printStackTrace();
       }
            
    }

    public void showCartFreeForm(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Hello Cart-->");
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.showCartFreeForm.execute}", Object.class, new Class[] {});
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[]{});
    }
}
