package com.unp.a3.Model;

public enum ProductAction {
    INPUT("Saída"),
    OUTPUT("Entrada");
    
   private String label;
   
   private ProductAction(String label) {
       this.label = label;
   }
   
   public String getLabel() {
       return this.label;
   }
}
