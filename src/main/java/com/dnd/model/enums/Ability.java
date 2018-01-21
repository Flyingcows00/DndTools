package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

public enum Ability {

    @SerializedName("Strength")
    STRENGTH,
    @SerializedName("Dexterity")
    DEXTERITY,
    @SerializedName("Constitution")
    CONSTITUTION,
    @SerializedName("Wisdom")
    WISDOM,
    @SerializedName("Intelligence")
    INTELLIGENCE,
    @SerializedName("Charisma")
    CHARISMA;

    public static Ability getAbility(String ability) {
        try {
            return Ability.valueOf(ability.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}
