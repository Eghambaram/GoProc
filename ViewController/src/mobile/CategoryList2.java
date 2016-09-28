package mobile;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfException;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryList2 {
    public static List categories = new ArrayList();
    
    
    public CategoryList2() {
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
    
    public void getCategories2() {
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryText2}", String.class);
        String ct=(String)ve61.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        String  categoryText1,categoryText2;
        
        ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category1Name}", String.class);
        categoryText1=(String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        
        
        Category c=(Category)CategoryList1.categories.get(Integer.parseInt(ct));
        System.out.println(c.getName()+" "+c.getId()+" "+c.getSource());
        CategoryList3.categories.clear();
        CategoryList4.categories.clear();
        
        if(!c.getName().equalsIgnoreCase("Please Select")){
     
            categoryText2=c.getName();
            ValueExpression ve63 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category2Name}", String.class);
            ve63.setValue(AdfmfJavaUtilities.getAdfELContext(), categoryText2);
            
            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription1}", String.class);
            ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),c.getName());
            AdfmfJavaUtilities.flushDataChangeEvent();
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
                    restServiceAdapter.setRequestURI("webservices/rest/XXETailSpendAPI/get_category_segments/");
                    String postData= "{\n" +
                    "  \"GET_CATEGORY_SEGMENTS_Input\" : {\n" +
                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/get_category_segments/\",\n" +
                    "   \"RESTHeader\": {\n" +
                    "   \"@xmlns\" : \"http://xmlns.oracle.com/apps/po/rest/XXETailSpendAPI/header\"\n" +
                    "    },\n" +
                    "   \"InputParameters\": {\n" +
                    "        \"P_SEGMENTS_TL\": { \"P_SEGMENTS_TL_ITEM\": [\n" +
                    categoryText1+","+categoryText2+"] \n" +
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
                        CategoryList2.categories.clear();                        
                        CategoryList3.categories.clear();
                        CategoryList4.categories.clear();
                
                        if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONArray){
                        JSONArray segments=segment.getJSONArray("X_SEGMENT_VALUES_TL_ITEM");
            
                            Category c2=new Category("Please Select","0","Please Select" ,"");
                            CategoryList2.categories.add(c2);
            
                        for(int i=0 ;i<segments.length();i++) {
                            System.out.println(segments.get(i));
                            JSONObject jobj=(JSONObject)segments.get(i);
                            String segment_value=jobj.getString("SEGMENT_VALUE");
                            String segment_description=jobj.getString("DESCRIPTION");
                            Category c1 = new Category(segment_value,"",segment_description,"0");
                            CategoryList2.categories.add(c1);
            
                        }
                        }
                        
                        else if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONObject){
                            
                            JSONObject segments=segment.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
                            JSONObject jobj=segments;
                            String segment_value=jobj.getString("SEGMENT_VALUE");
                            String segment_description=jobj.getString("DESCRIPTION");
                            Category c2=new Category("Please Select","0","Please Select" ,"");
                            CategoryList2.categories.add(c2);
                            Category c1 = new Category(segment_value,"",segment_description,"0");
                            CategoryList2.categories.add(c1);
                        }
                        else{
                            String cat=segment.getString("X_SEGMENT_VALUES_TL_ITEM");
                            Category c2=new Category("Please Select","0","Please Select" ,"");
                            CategoryList2.categories.add(c2);
                            Category c1 = new Category(cat,"","","0");
                            CategoryList2.categories.add(c1);
                            //JSONObject segment=
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
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator2.iterator}");  
        vex.refresh();
        
        BasicIterator vex2 = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator3.iterator}");  
        vex2.refresh();
        ValueExpression ve13 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
        ve13.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription3}", String.class);
        ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        AdfmfJavaUtilities.flushDataChangeEvent();
    }
    
}
