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

public class CategoryList3 {
    public static List categories = new ArrayList();
    
    
    public CategoryList3() {
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
    
    public void getCategories3() {
        
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryText3}", String.class);
        String ct=(String)ve61.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        String  categoryText1,categoryText2,categoryText3;
        
        ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category1Name}", String.class);
        categoryText1=(String)ve62.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ValueExpression ve63 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category2Name}", String.class);
        categoryText2=(String)ve63.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        
        
        Category c=(Category)CategoryList2.categories.get(Integer.parseInt(ct));
        System.out.println(c.getName()+" "+c.getId()+" "+c.getSource());
        CategoryList4.categories.clear();
        
        if(!c.getName().equalsIgnoreCase("Please Select")){
            
            ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription2}", String.class);
            ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),c.getName());
            AdfmfJavaUtilities.flushDataChangeEvent();
    
            categoryText3=c.getName();
            ValueExpression ve64 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category3Name}", String.class);
            ve64.setValue(AdfmfJavaUtilities.getAdfELContext(), categoryText3);
            
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
                        categoryText1+","+categoryText2+","+categoryText3+"] \n" +
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
                        CategoryList3.categories.clear();
                        CategoryList4.categories.clear();
            
                        if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONArray){
                        JSONArray segments=segment.getJSONArray("X_SEGMENT_VALUES_TL_ITEM");
            
            
                            Category c2=new Category("Please Select","0","Please Select" ,"");
                            CategoryList3.categories.add(c2);
                        for(int i=0 ;i<segments.length();i++) {
                            System.out.println(segments.get(i));
                            JSONObject jobj=(JSONObject)segments.get(i);
                            String segment_value=jobj.getString("SEGMENT_VALUE");
                            String segment_description=jobj.getString("DESCRIPTION"); 
                            Category c1 = new Category(segment_value,"",segment_description,"0");
                            CategoryList3.categories.add(c1);
            
                        }
                        }
                        
                        else if(segment.get("X_SEGMENT_VALUES_TL_ITEM") instanceof  JSONObject){
                            
                            JSONObject segments=segment.getJSONObject("X_SEGMENT_VALUES_TL_ITEM");
                            JSONObject jobj=segments;
                            String segment_value=jobj.getString("SEGMENT_VALUE");
                            String segment_description=jobj.getString("DESCRIPTION");
                            Category c2=new Category("Please Select","0","Please Select" ,"");
                            CategoryList3.categories.add(c2);
                            Category c1 = new Category(segment_value,"",segment_description,"0");
                            CategoryList3.categories.add(c1);
                            
                        }
                        else{
                            String cat=segment.getString("X_SEGMENT_VALUES_TL_ITEM");
                            Category c2=new Category("Please Select","0","Please Select" ,"");
                            CategoryList3.categories.add(c2);
                            Category c1 = new Category(cat,"","","0");
                            CategoryList3.categories.add(c1);
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
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.categoriesIterator3.iterator}");  
        vex.refresh();
        
        ValueExpression ve14 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription3}", String.class);
        ve14.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
        AdfmfJavaUtilities.flushDataChangeEvent();
    }
    
    
    
    public void getCategories4() {
        // Add event code here...
        
        String categoryText4;
        ValueExpression ve61 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryText4}", String.class);
        String ct=(String)ve61.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        Category c=(Category)CategoryList3.categories.get(Integer.parseInt(ct));
        System.out.println(c.getName()+" "+c.getId()+" "+c.getSource());
        categoryText4=c.getName();
        
        ValueExpression ve62 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.category4Name}", String.class);
        ve62.setValue(AdfmfJavaUtilities.getAdfELContext(), categoryText4);
        
        if(!c.getName().equalsIgnoreCase("Please Select")){
        ValueExpression ve6 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.categoryDescription3}", String.class);
        ve6.setValue(AdfmfJavaUtilities.getAdfELContext(),c.getName());
        AdfmfJavaUtilities.flushDataChangeEvent();
        }
        
        
    }
    
}
