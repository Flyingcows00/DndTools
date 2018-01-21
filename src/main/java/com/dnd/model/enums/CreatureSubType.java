package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

public enum CreatureSubType {

    @SerializedName(value = "Air", alternate = {"air"})
    AIR,
    @SerializedName(value = "Angel", alternate = {"angel"})
    ANGEL,
    @SerializedName(value = "Aquatic", alternate = {"aquatic"})
    AQUATIC,
    @SerializedName(value = "Archon", alternate = {"archon"})
    ARCHON,
    @SerializedName(value = "Augmented", alternate = {"augmented"})
    AUGMENTED,
    @SerializedName(value = "Chaotic", alternate = {"chaotic"})
    CHAOTIC,
    @SerializedName(value = "Cold", alternate = {"cold"})
    COLD,
    @SerializedName(value = "Earth", alternate = {"earth"})
    EARTH,
    @SerializedName(value = "Evil", alternate = {"evil"})
    EVIL,
    @SerializedName(value = "Extraplanar", alternate = {"extraplanar"})
    EXTRAPLANAR,
    @SerializedName(value = "Fire", alternate = {"fire"})
    FIRE,
    @SerializedName(value = "Goblinoid", alternate = {"goblinoid"})
    GOBLINOID,
    @SerializedName(value = "Good", alternate = {"good"})
    GOOD,
    @SerializedName(value = "Incorporeal", alternate = {"incorporeal"})
    INCORPOREAL,
    @SerializedName(value = "Lawful", alternate = {"lawful"})
    LAWFUL,
    @SerializedName(value = "Native", alternate = {"native"})
    NATIVE,
    @SerializedName(value = "Reptilian", alternate = {"reptilian"})
    REPTILIAN,
    @SerializedName(value = "Shapechanger", alternate = {"shapechanger"})
    SHAPECHANGER,
    @SerializedName(value = "Swarm", alternate = {"swarm"})
    SWARM,
    @SerializedName(value = "Water", alternate = {"water"})
    WATER;

    public static CreatureSubType getCreatureSubType(String type) {
        try {
            return CreatureSubType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }


}
