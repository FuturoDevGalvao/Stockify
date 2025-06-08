package com.unp.a3.Model.enums;

public enum ProductCategory {
    ELECTRONICS("Eletrônicos"),
    CLOTHING("Vestuário"),
    FOOD_AND_BEVERAGES("Alimentos e Bebidas"),
    HOME_APPLIANCES("Eletrodomésticos"),
    FURNITURE("Móveis"),
    HEALTH_AND_BEAUTY("Saúde e Beleza"),
    STATIONERY("Papelaria"),
    SPORTS_AND_LEISURE("Esportes e Lazer"),
    AUTOMOTIVE("Automotivo"),
    TOYS("Brinquedos"),
    PET_SUPPLIES("Produtos para Pets"),
    CLEANING_SUPPLIES("Produtos de Limpeza"),
    CONSTRUCTION("Materiais de Construção"),
    BOOKS_AND_MAGAZINES("Livros e Revistas");

    private final String label;

    ProductCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

