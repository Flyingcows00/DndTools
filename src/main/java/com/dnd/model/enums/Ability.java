package com.dnd.model.enums;

public enum Ability {

    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    WISDOM,
    INTELLIGENCE,
    CHARISMA;

    public static Ability getAbility(String ability) {
        try {
            return Ability.valueOf(ability.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}
