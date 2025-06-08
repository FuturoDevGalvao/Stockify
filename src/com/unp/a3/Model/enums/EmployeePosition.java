package com.unp.a3.Model.enums;

public enum EmployeePosition {
    ADMINISTRATOR("Administrador"),
    MANAGER("Gerente"),
    SUPERVISOR("Supervisor"),
    SALES_REPRESENTATIVE("Vendedor"),
    CASHIER("Caixa"),
    STOCK_CONTROLLER("Estoquista"),
    CUSTOMER_SERVICE("Atendente"),
    ACCOUNTANT("Contador"),
    TECHNICIAN("Técnico"),
    INTERN("Estagiário");

    private final String label;

    EmployeePosition(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

