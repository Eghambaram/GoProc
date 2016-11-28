package mobile;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

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
    
        public void itemTypeValues(){
            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ItemType}", String.class);
            String itemType=(String)ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            System.out.println("Item Type List"+ItemTypeList.itemType_List.size());
            
            ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RequestTypeForm}", String.class);
            String reqType=(String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());


            ItemType it=(ItemType)ItemTypeList.itemType_List.get((Integer.parseInt(itemType)));
            System.out.println("Item Type-->"+it.getLineTypeCode());
            if(reqType!=null && !reqType.equalsIgnoreCase(""))
            {
            RequestType rt=(RequestType)RequestTypeList.req_List.get((Integer.parseInt(reqType)));
            System.out.println("Req Type-->"+rt.getLookupCode()+rt.getMeaning());
                
                if(rt.getMeaning().equalsIgnoreCase("Others") && it.getLineTypeCode().equalsIgnoreCase("Goods")){

                    ValueExpression veb1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showWeb}", String.class);
                    veb1.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                }
                else{
                    ValueExpression veb1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showWeb}", String.class);
                    veb1.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                    
                }
            
            }
            
            if(it.getLineTypeCode().equalsIgnoreCase("Services - Fixed Price")) {
                ValueExpression vee1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
                vee1.setValue(AdfmfJavaUtilities.getAdfELContext(),"1");
                ValueExpression vee2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantityReadOnly}", String.class);
                vee2.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                /*ValueExpression vee3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
                vee3.setValue(AdfmfJavaUtilities.getAdfELContext(),"1");*/
                ValueExpression vee4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.UOMReadOnly}", String.class);
                vee4.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
            }
            else {
                ValueExpression vee1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
                vee1.setValue(AdfmfJavaUtilities.getAdfELContext(),"");
                ValueExpression vee2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantityReadOnly}", String.class);
                vee2.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                /*ValueExpression vee3 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
                vee3.setValue(AdfmfJavaUtilities.getAdfELContext(),"1");*/
                ValueExpression vee4 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.UOMReadOnly}", String.class);
                vee4.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
            }

        }
        public void showSearchButton() {
            System.out.println("Req Type Change-->"+RequestTypeList.req_List.size());
                   ValueExpression ve5 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RequestTypeForm}", String.class);
                   String reqType=(String)ve5.getValue(AdfmfJavaUtilities.getAdfELContext());
                   
                   RequestType rt=(RequestType)RequestTypeList.req_List.get((Integer.parseInt(reqType)));
                   System.out.println("Req Type-->"+rt.getLookupCode()+rt.getMeaning());
                   if(rt.getMeaning().equalsIgnoreCase("Others")) {
                       ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSearch}", String.class);
                       ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                       ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddCartButton}", String.class);
                       ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSubmit}", String.class);
                       ve23.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       ValueExpression ve24 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.showOthers}", String.class);
                       ve24.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       ValueExpression veb1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showWeb}", String.class);
                       veb1.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");

                   }
                   else if(rt.getMeaning().equalsIgnoreCase("Sole Source"))
                   {
                       ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSearch}", String.class);
                       ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddCartButton}", String.class);
                       ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSubmit}", String.class);
                       ve23.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                       ValueExpression ve24 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.showOthers}", String.class);
                       ve24.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                       ValueExpression veb1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showWeb}", String.class);
                       veb1.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                   }
                   else {
                       ValueExpression ve19 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSearch}", String.class);
                       ve19.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       ValueExpression ve23 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showSubmit}", String.class);
                       ve23.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                       ValueExpression ve22 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showAddCartButton}", String.class);
                       ve22.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                       ValueExpression ve24 = AdfmfJavaUtilities.getValueExpression("#{applicationScope.showOthers}", String.class);
                       ve24.setValue(AdfmfJavaUtilities.getAdfELContext(),"true");
                       ValueExpression veb1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.showWeb}", String.class);
                       veb1.setValue(AdfmfJavaUtilities.getAdfELContext(),"false");
                   }
            
        }
    }
