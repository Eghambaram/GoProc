package mobile;

import java.util.ArrayList;
import java.util.List;

public class NaturalAcccountList {
    public static List acc_List = new ArrayList();
    public NaturalAcccountList() {
        super();
        if (acc_List == null) {
            acc_List = new ArrayList();
        }
    }
    public NaturalAccounts[] getNaturalAccounts() {
        NaturalAccounts e[] = null;
        e = (NaturalAccounts[])acc_List.toArray(new NaturalAccounts[acc_List.size()]);
        return e;
    }
    
    
    public void getNaturalAcccountList() {
        
        NaturalAccounts nAcc = new NaturalAccounts(); 
        acc_List.add(nAcc);
        
          
        
    }
    }

