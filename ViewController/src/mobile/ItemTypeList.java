package mobile;

import java.util.ArrayList;
import java.util.List;

public class ItemTypeList {
    public static List itemType_List = new ArrayList();
    public ItemTypeList() {
        super();
        if (itemType_List == null) {
            itemType_List = new ArrayList();
        }
    }
    public ItemType[] getItemType() {
        ItemType e[] = null;
        e = (ItemType[])itemType_List.toArray(new ItemType[itemType_List.size()]);
        return e;
    }
    
    
    public void getItemTypeList() {
        
        ItemType iType = new ItemType(); 
        itemType_List.add(iType);
        
          
        
    }
    }
