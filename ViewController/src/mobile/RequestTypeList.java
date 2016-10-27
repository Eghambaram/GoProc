package mobile;

import java.util.ArrayList;
import java.util.List;

public class RequestTypeList {
    public static List req_List = new ArrayList();
    public RequestTypeList() {
        super();
        if (req_List == null) {
            req_List = new ArrayList();
        }
    }
    public RequestType[] getRequestType() {
        RequestType e[] = null;
        e = (RequestType[])req_List.toArray(new RequestType[req_List.size()]);
        return e;
    }
    
    
    public void getRequestTypeList() {
        
        RequestType rType = new RequestType(); 
        req_List.add(rType);
        
    }
}
