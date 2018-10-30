package com.dnd.model.enums;

public enum SpellComponent {

    V("Verbal"),
    S("Somatic"),
    M("Material");

    private String value;

    SpellComponent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SpellComponent getSpellComponent(String component) {
        try {
            return SpellComponent.valueOf(component.trim().replaceAll("[\\s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
