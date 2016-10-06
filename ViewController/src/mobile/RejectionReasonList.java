package mobile;

import java.util.ArrayList;
import java.util.List;

public class RejectionReasonList {
    public static List rej_List = new ArrayList();
    public RejectionReasonList() {
        super();
        if (rej_List == null) {
            rej_List = new ArrayList();
        }
    }
    public Rejection[] getRejection() {
        Rejection e[] = null;
        e = (Rejection[])rej_List.toArray(new Rejection[rej_List.size()]);
        return e;
    }
    public void getRejectionReasonList() {
        
        Rejection rej = new Rejection(); 
        rej_List.add(rej);
        
          
        
    }
}
