package com.unp.a3.Controller;

import com.unp.a3.Model.Output;

public class OutputController {
    public static int getTotalOutputs() {
         return new Output().getAll().size();
    }
    
    public static double getTotalRevenue() {
        return new Output().getAll()
            .stream()
            .map(o -> (Output) o)
            .mapToDouble(o -> o.getQuantity() * o.getProductModel().getPrice())
            .sum();
    }
}
