package mobile;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class ImageList {
    public static List imageList = new ArrayList();
    private  transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private  transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public ImageList() {
        super();
        if (imageList == null) {
            imageList = new ArrayList();
        }
    }
    public Image[] getImages() {
        Image e[] = null;
        e = (Image[])imageList.toArray(new Image[imageList.size()]);
        return e;
    }
    
    
    public void getImageList() {
        Image j = new Image(); 
        imageList.add(j);
    }
    
    
    
    
    public void clearImage(){     
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.imageURL}", String.class);
        String imageUrl = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        AdfmfJavaUtilities.flushDataChangeEvent();
        
        for(int i=0;i<ImageList.imageList.size();i++){
            System.out.println("Image List Value In For Loop:: =>"+imageUrl);
            Image img=(Image)ImageList.imageList.get(i);
            if(img.getImageURL().equalsIgnoreCase(imageUrl)){
                System.out.println("Image List Value in If Loop:: =>"+imageUrl+" =>"+img.getImageURL());
                ImageList.imageList.remove(i);
                //ImageList.imageList.remove(i);
                //EncodeBased64Binary.encodeFileToBase64Binary(selectedImages.get(i))
            }
            
        }
        
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.selectedImagesCount}", String.class);
        ve1.setValue(AdfmfJavaUtilities.getAdfELContext(),ImageList.imageList.size()+" images selected");
        AdfmfJavaUtilities.flushDataChangeEvent();
        System.out.println("Page Flow Scope In Selected Image:: =>"+ImageList.imageList.size());
        
    }
}
