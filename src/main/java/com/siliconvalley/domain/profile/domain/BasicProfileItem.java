package com.siliconvalley.domain.profile.domain;

public enum BasicProfileItem {
    BASIC_HAMSTER("hamster");

    private String ItemName;

    BasicProfileItem(String itemName) {
        this.ItemName = itemName;
    }

    public String getItemName() {
        return this.ItemName;
    }
}
