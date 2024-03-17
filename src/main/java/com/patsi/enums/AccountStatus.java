package com.patsi.enums;

public enum AccountStatus {
    Active("Active"),
    Inactive("Inactive"),
    Deactivate("Deactivate"),
    Suspended("Suspended"),
    Closed("Closed");

    private String label;
    AccountStatus(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
