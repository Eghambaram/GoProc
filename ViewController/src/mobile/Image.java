package mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class Image {
    private String imageURL = "";
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
        propertyChangeSupport.firePropertyChange("imageURL", this.imageURL,imageURL);
    }

    public String getImageURL() {
        return imageURL;
    }

    public Image() {
        super();
    }
    public Image(String url) {
        this.imageURL=url;
    }
    
}
