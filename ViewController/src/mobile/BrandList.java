package mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.framework.FeatureContext;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.JSONArray;
import org.json.JSONObject;

public class BrandList {
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static List s_jobs = new ArrayList();
   
    
    public BrandList() {
        if (s_jobs == null) {
            s_jobs = new ArrayList();
        }
    }
    
    public Brand[] getBrands() {
        Brand e[] = null;
        e = (Brand[])s_jobs.toArray(new Brand[s_jobs.size()]);
        return e;
    }
    
    
    public void getBrandList() {
        
        Brand j = new Brand(); 
        s_jobs.add(j);
        
          
        
    }
    public void showBrandsPopup() {
        // Add event code here...
        try{
            
           
            BrandList.s_jobs.clear();
            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.brand}", String.class);
            String brand = (String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());

                   String url = "https://api.indix.com/v2/brands?q="+URLEncoder.encode(brand)+"&app_id=9867e55c&app_key=8d79be1be9b9d8ce50af3a978b4d5ccc";
                                   
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
        
                                   JSONObject resp=new JSONObject(response1.toString());
                                   JSONObject output=resp.getJSONObject("result");
                                   JSONArray resArr=output.getJSONArray("brands");
           
                                   BrandList.s_jobs.clear();
                                   for(int i=0;i<resArr.length();i++) {
                                       JSONObject brandObj=resArr.getJSONObject(i);
                                       Brand j = new Brand(String.valueOf(brandObj.getInt("id")), brandObj.getString("name"));
                                       BrandList.s_jobs.add(j); 
                                    //   System.out.println("***********");
                                   }
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        BasicIterator vex = (BasicIterator) AdfmfJavaUtilities.getELValue("#{bindings.brands.iterator}");  
        vex.refresh();
        
       
    }
}
