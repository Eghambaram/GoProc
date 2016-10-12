package mobile;

import java.util.ArrayList;
import java.util.List;

public class CostCenterNaturalAccountsList {
    public static List CC_NA_List = new ArrayList();
    public CostCenterNaturalAccountsList() {
        super();
        if (CC_NA_List == null) {
            CC_NA_List = new ArrayList();
        }
    }
    public CostCenterNaturalAccounts[] getCostCenterNaturalAccounts() {
        CostCenterNaturalAccounts e[] = null;
        e = (CostCenterNaturalAccounts[])CC_NA_List.toArray(new CostCenterNaturalAccounts[CC_NA_List.size()]);
        return e;
    }
    
    
    public void getCostCenterNaturalAccountsList() {
        
        CostCenterNaturalAccounts CAcc = new CostCenterNaturalAccounts(); 
        CC_NA_List.add(CAcc);
        
          
        
    }
}
