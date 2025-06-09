package com.unp.a3.Controller;

import com.unp.a3.Model.Input;

public class InputController {
    public static int getTotalInputs() {
         return new Input().getAll().size();
    }
    
    public static double getTotalRevenue() {
        return new Input().getAll()
            .stream()
            .map(i -> (Input) i)
            .mapToDouble(i -> i.getQuantity() * i.getProductModel().getPrice())
            .sum();
    }
}
