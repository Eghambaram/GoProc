package mobile;

import java.util.ArrayList;
import java.util.List;

public class AlertsList {
    public static List alertList = new ArrayList();
    public AlertsList() {
        super();
        if (alertList == null) {
            alertList = new ArrayList();
        }
    }
    
    public Alert[] getAlerts() {
        Alert e[] = null;
        e = (Alert[])alertList.toArray(new Alert[alertList.size()]);
        return e;
    }
    
    
    public void getAlertList() {
        Alert j = new Alert(); 
        alertList.add(j);
    }
    
}
