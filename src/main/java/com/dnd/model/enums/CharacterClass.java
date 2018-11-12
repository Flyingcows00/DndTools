package com.dnd.model.enums;

public enum CharacterClass {

    BARBARIAN,
    BARD,
    CLERIC,
    DRUID,
    FIGHTER,
    MONK,
    PALADIN,
    RANGER,
    ROGUE,
    SORCERER,
    WARLOCK,
    WIZARD,
    BLOOD_HUNTER;

    public static CharacterClass getCharacterClass(String characterClass) {
        try {
            return CharacterClass.valueOf(characterClass.trim().replaceAll("[\\s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}
