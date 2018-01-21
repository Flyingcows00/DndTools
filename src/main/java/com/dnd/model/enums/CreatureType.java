package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

public enum CreatureType {

    @SerializedName(value = "Aberration", alternate = {"aberration"})
    ABERRATION,
    @SerializedName(value = "Beast", alternate = {"beast"})
    BEAST,
    @SerializedName(value = "Celestial", alternate = {"celestial"})
    CELESTIAL,
    @SerializedName(value = "Construct", alternate = {"construct"})
    CONSTRUCT,
    @SerializedName(value = "Dragon", alternate = {"dragon"})
    DRAGON,
    @SerializedName(value = "Elemental", alternate = {"elemental"})
    ELEMENTAL,
    @SerializedName(value = "Fey", alternate = {"fey"})
    FEY,
    @SerializedName(value = "Fiend", alternate = {"fiend"})
    FIEND,
    @SerializedName(value = "Giant", alternate = {"giant"})
    GIANT,
    @SerializedName(value = "Humanoid", alternate = {"humanoid"})
    HUMANOID,
    @SerializedName(value = "Monstrosities", alternate = {"monstrosities"})
    MONSTROSITIES,
    @SerializedName(value = "Ooze", alternate = {"ooze"})
    OOZE,
    @SerializedName(value = "Plant", alternate = {"plant"})
    PLANT,
    @SerializedName(value = "Undead", alternate = {"undead"})
    UNDEAD;

    public static CreatureType getCreatureType(String type) {
        try {
            return CreatureType.valueOf(type.trim().replaceAll("[//s]+", " ").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}