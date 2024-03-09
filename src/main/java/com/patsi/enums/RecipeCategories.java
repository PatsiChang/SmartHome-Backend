package com.patsi.enums;

public enum RecipeCategories {

    American("American"),
    Asian("Asian"),
    African("African"),
    British("British"),
    Chinese("Chinese"),
    European("European"),
    Indonesian("Indonesian"),
    Indian("Indian"),
    Italian("Italian"),
    Japanese("Japanese"),
    Korean("Korean"),
    Lebanese("Lebanese"),
    Malaysian("Malaysian"),
    Mexican("Mexican"),
    MiddleEastern("MiddleEastern"),
    Pakistani("Pakistani"),
    Polish("Polish"),
    Portuguese("Portuguese"),
    Romanian("Romanian"),
    SriLanka("Sri Lanka"),
    Thai("Thai"),
    Turkish("Turkish"),
    Ukrainian("Ukrainian"),
    Vegan("Vegan");

    private String label;

    RecipeCategories(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
