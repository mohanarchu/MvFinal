package mv.hospital.cart.orders;

import mv.hospital.cart.OldOrders.OldPojo;

public class UtilityClass {

   private static UtilityClass instance;

   private OldPojo.Result[] list;

   public OldPojo.Result[] getList() {
       return list;
   }

   public void setList(OldPojo.Result[] list) {
       this.list = list;
   }

   private UtilityClass(){}

   public static UtilityClass getInstance(){
       if(instance == null){
           instance = new UtilityClass();
       }
       return instance;
       }
}