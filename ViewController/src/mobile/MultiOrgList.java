package mobile;

import java.util.ArrayList;
import java.util.List;

public class MultiOrgList {
    public static List org_List = new ArrayList();
    public MultiOrgList() {
        super();
        if (org_List == null) {
            org_List = new ArrayList();
        }
    }
    public MultiOrg[] getMultiOrgs() {
        MultiOrg e[] = null;
        e = (MultiOrg[])org_List.toArray(new MultiOrg[org_List.size()]);
        return e;
    }
    
    
    public void getMultiOrgList() {
        
        MultiOrg org = new MultiOrg(); 
        org_List.add(org);
        
          
        
    }
}
