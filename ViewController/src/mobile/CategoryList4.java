package mobile;

import java.util.ArrayList;
import java.util.List;

public class CategoryList4 {
    public static List categories = new ArrayList();
    
    
    public CategoryList4() {
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
    
    
    
}
