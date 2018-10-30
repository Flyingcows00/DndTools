package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

public enum CharacterClass {

    @SerializedName("Barbarian")
    BARBARIAN,
    @SerializedName("Bard")
    BARD,
    @SerializedName("Cleric")
    CLERIC,
    @SerializedName("Druid")
    DRUID,
    @SerializedName("Fighter")
    FIGHTER,
    @SerializedName("Monk")
    MONK,
    @SerializedName("Paladin")
    PALADIN,
    @SerializedName("Ranger")
    RANGER,
    @SerializedName("Rogue")
    ROGUE,
    @SerializedName("Sorcerer")
    SORCERER,
    @SerializedName("Warlock")
    WARLOCK,
    @SerializedName("Wizard")
    WIZARD,
    @SerializedName("Blood Hunter")
    BLOOD_HUNTER;

    public static CharacterClass getCharacterClass(String characterClass) {
        try {
            return CharacterClass.valueOf(characterClass.trim().replaceAll("[\\s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}
