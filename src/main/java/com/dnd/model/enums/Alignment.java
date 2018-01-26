package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

public enum Alignment {

    @SerializedName(value = "Lawful Good", alternate = {"lawful good"})
    LAWFUL_GOOD,
    @SerializedName(value = "Lawful Neutral", alternate = {"lawful neutral"})
    LAWFUL_NEUTRAL,
    @SerializedName(value = "Lawful Evil", alternate = {"lawful evil"})
    LAWFUL_EVIL,
    @SerializedName(value = "Neutral Good", alternate = {"neutral good"})
    NEUTRAL_GOOD,
    @SerializedName(value = "Neutral", alternate = {"neutral"})
    NEUTRAL,
    @SerializedName(value = "Neutral Evil", alternate = {"neutral evil"})
    NEUTRAL_EVIL,
    @SerializedName(value = "Chaotic Good", alternate = {"chaotic good"})
    CHAOTIC_GOOD,
    @SerializedName(value = "Chaotic Neutral", alternate = {"chaotic neutral"})
    CHAOTIC_NEUTRAL,
    @SerializedName(value = "Chaotic Evil", alternate = {"chaotic evil"})
    CHAOTIC_EVIL;

    public static Alignment getAlignment(String alignment) {
        try {
            return Alignment.valueOf(alignment.trim().replaceAll("[\\s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }


}
