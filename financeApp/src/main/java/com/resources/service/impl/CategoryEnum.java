package com.resources.service.impl;

/**
 *
 * @author Praca
 */
public enum CategoryEnum {
    PROFIT("profit", 8);

    public final String name;
    public final int categoryId;

    private CategoryEnum(String name, int categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }
}
