package com.example.hospital.cart.orders;

import com.example.hospital.cart.OldOrders.OldPojo;

import java.util.ArrayList;
import java.util.List;

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