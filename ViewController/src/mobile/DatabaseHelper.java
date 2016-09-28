package mobile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class DatabaseHelper {
    public DatabaseHelper() {
        super();
        try{
            conn=getConnection();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    static  Connection conn = null;
    public static Connection getConnection() throws Exception {
        if (conn == null) {
            try {
                // create a database connection
                String Dir = AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory);
                String connStr = "jdbc:sqlite:" + Dir + "/metaprocure.db";
                conn = new SQLite.JDBCDataSource(connStr).getConnection();
            } catch (SQLException e) {
                // if the error message is "out of memory",
                // it probably means no database file is found
                System.err.println(e.getMessage());
            }
        }
        return conn;
    }
    
    
    public void insertCategory(String id,String parentId,String name,String source){
        
        try{
          
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("Insert into Categories (id,parentId,name,source) values (" +
                                                              "'"+id.trim()+"','"+parentId.trim()+"','"+name.trim()+"','"+source.trim()+"')");
        conn.commit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void populateParentCategories(){
        try{
            
            PreparedStatement stat2 = conn.prepareStatement("SELECT * from Categories where parentId='0'");
            ResultSet rs2 = stat2.executeQuery();
            CategoryList.categories.clear();
            
            Category c2=new Category("Please Select","0","0" ,"");
            CategoryList.categories.add(c2);
            while(rs2.next()) {
                
                Category c=new Category(rs2.getString("name"),rs2.getString("id"),rs2.getString("parentId") ,rs2.getString("source"));
                CategoryList.categories.add(c);   
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void populateChildCategories(String parentId,int level){
        
        try{
            
            PreparedStatement stat2 = conn.prepareStatement("SELECT * from Categories where parentId='"+parentId+"'");
            ResultSet rs2 = stat2.executeQuery();

            if(level==1){
            
            CategoryList1.categories.clear();
                Category c2=new Category("Please Select","0","0" ,"");
                CategoryList1.categories.add(c2);
            while(rs2.next()) {
                
                Category c=new Category(rs2.getString("name"),rs2.getString("id"),rs2.getString("parentId") ,rs2.getString("source"));
                CategoryList1.categories.add(c);   
            }
            }
            else if(level==2){
            
            CategoryList2.categories.clear();
                Category c2=new Category("Please Select","0","0" ,"");
                CategoryList2.categories.add(c2);
            while(rs2.next()) {
                
                Category c=new Category(rs2.getString("name"),rs2.getString("id"),rs2.getString("parentId") ,rs2.getString("source"));
                CategoryList2.categories.add(c);   
            }
            }
            else{
            
            CategoryList3.categories.clear();
                Category c2=new Category("Please Select","0","0" ,"");
                CategoryList3.categories.add(c2);
            while(rs2.next()) {
                
                Category c=new Category(rs2.getString("name"),rs2.getString("id"),rs2.getString("parentId") ,rs2.getString("source"));
                CategoryList3.categories.add(c);   
            }
            }
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
