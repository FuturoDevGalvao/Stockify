package com.unp.a3.Model.enums;

public enum ReportType {
    INPUT("Saída"),
    OUTPUT("Entrada");
    
   private String label;
   
   private ReportType(String label) {
       this.label = label;
   }
   
   public String getLabel() {
       return this.label;
   }
}
