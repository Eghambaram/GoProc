package application;

import java.util.HashMap;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.event.Event;
import oracle.adfmf.framework.event.EventListener;
import oracle.adfmf.framework.exception.AdfException;

public class NativePushNotificationListener implements EventListener {
    public NativePushNotificationListener() {
    }

    public void onMessage(Event event) {
        String msg = event.getPayload();
        System.out.println("#### Message from the Server :" + msg);
        
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(
                                     AdfmfJavaUtilities.getFeatureName(),
                                     "adf.mf.api.amx.addMessage", new Object[] {AdfException.ERROR,
                                     "Message from the Server :"+msg,
                                     null,
                                     null }); 
        
//        // Parse the payload of the push notification
//        HashMap payload = null;
//        String pushMsg = "No message received";
//        try
//        {
//          payload = (HashMap)JSONBeanSerializationHelper.fromJSON(HashMap.class, msg);
//          pushMsg = (String)payload.get("alert");
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        // Write the push message to app scope to display to the user        
//        AdfmfJavaUtilities.setELValue("#{applicationScope.pushMessage}", pushMsg);
    }

    public void onError(AdfException adfException) {
        System.out.println("#### Error: " + adfException.toString());
        // Write the error into app scope        
        AdfmfJavaUtilities.setELValue("#{applicationScope.errorMessage}", adfException.toString());

    }

    public void onOpen(String token) {
        System.out.println("#### Registration token:" + token);
        // Clear error in app scope
        AdfmfJavaUtilities.setELValue("#{applicationScope.errorMessage}", null);
        
        // Write the token into app scope
        AdfmfJavaUtilities.setELValue("#{applicationScope.deviceToken}", token);
    }
    
}
