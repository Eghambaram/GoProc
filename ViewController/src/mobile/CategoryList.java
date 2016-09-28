package mobile;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.FeatureContext;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryList {
    public static List categories = new ArrayList();
    
    
    public CategoryList() {
        super();
        if (categories == null) {
            categories = new ArrayList();
        }
    }
    public Category[] getCategories() {
        Category e[] = null;
        e = (Category[])categories.toArray(new Category[categories.size()]);
        return e;
    }
    
    
    public void getCategoryList() {
        Category j = new Category(); 
        categories.add(j);
    }
    
    public void showCategories() {
        // Add event code here...
        
        try{
        // Add event code here...
            
            //Oracle
            
            
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();
        // Set the connection name
        restServiceAdapter.setConnectionName("enrich");
        
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_POST);
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic " + "WFhFX1JFU1RfU0VSVklDRVNfQURNSU46b3JhY2xlMTIz");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/get_category_segments/");
        String postData= "{\n" + 
        "  \"GET_CATEGORY_SEGMENTS_Input\" : {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_category_segments/\",\n" + 
        "   \"RESTHeader\": {\n" + 
        "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" + 
        "    },\n" + 
        "   \"InputParameters\": {\n" + 
        "        \"P_SEGMENTS_TL\": { \"P_SEGMENTS_TL_ITEM\": [\n" + 
       
        "          ] \n" + 
        "        }\n" + 
        "     }\n" + 
        "  }\n" + 
        "}  \n";
                                    restServiceAdapter.setRetryLimit(0);
           System.out.println("postData===============================" + postData);
        
        String response = restServiceAdapter.send(postData);
         
         
         System.out.println("response===============================" + response);
            
            JSONObject resp=new JSONObject(response);
            JSONObject output=resp.getJSONObject("OutputParameters");
            if(!output.isNull("X_SEGMENT_VALUES_TL")){
            JSONObject segment=output.getJSONObject("X_SEGMENT_VALUES_TL");
            if(!segment.isNull("X_SEGMENT_VALUES_TL_ITEM")){
            JSONArray segments=segment.getJSONArray("X_SEGMENT_VALUES_TL_ITEM"); 
            
            CategoryList.categories.clear();
            CategoryList1.categories.clear();
            CategoryList2.categories.clear();
            CategoryList3.categories.clear();
            CategoryList4.categories.clear();
            
            ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription}", String.class);
            ve61.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription1}", String.class);
            ve62.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            ValueExpression ve63 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
            ve63.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            
            ValueExpression ve64 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
            ve64.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            ValueExpression ve65 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category1Name}", String.class);
            ve65.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            ValueExpression ve66 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category2Name}", String.class);
            ve66.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            ValueExpression ve67 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category3Name}", String.class);
            ve67.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            ValueExpression ve68 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category4Name}", String.class);
            ve68.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
            
            
            
            AdfmfJavaUtilities.flushDataChangeEvent();
            
            
            Category c2=new Category("Please Select","0","Please Select" ,"");
            CategoryList.categories.add(c2);
            for(int i=0 ;i<segments.length();i++) {
                System.out.println(segments.get(i));
                JSONObject jobj=(JSONObject)segments.get(i);
                String segment_value=jobj.getString("SEGMENT_VALUE");
                String segment_description=jobj.getString("DESCRIPTION");
                Category c = new Category(segment_value,"",segment_description,"O");
                CategoryList.categories.add(c);

            }
            
            
           
            
            
            //fetch top level categories and populate the drop down
            
             //  dh.populateParentCategories();
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
            
            
          
            e.printStackTrace();
        }
        
        
        
        
        
        
        Object errorMsg = AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
                                                                  "popupUtilsShowPopup", new Object[] {
                                                                  "_popShowId" });
    }
    
    
 
    
}
