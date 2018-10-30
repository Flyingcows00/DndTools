package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

public enum MagicSchool {
    @SerializedName("Abjuration")
    ABJURATION,
    @SerializedName("Conjuration")
    CONJURATION,
    @SerializedName("Divination")
    DIVINATION,
    @SerializedName("Enchantment")
    ENCHANTMENT,
    @SerializedName("Evocation")
    EVOCATION,
    @SerializedName("Illusion")
    ILLUSION,
    @SerializedName("Necromancy")
    NECROMANCY,
    @SerializedName("Transmutation")
    TRANSMUTATION;

    public static MagicSchool getMagicSchool(String school) {
        try {
            return MagicSchool.valueOf(school.trim().replaceAll("[\\s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}
