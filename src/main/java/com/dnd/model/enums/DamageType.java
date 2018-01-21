package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public enum DamageType {

    @SerializedName("Acid")
    ACID,
    @SerializedName("Bludgeoning")
    BLUDGEONING,
    @SerializedName("Cold")
    COLD,
    @SerializedName("Fire")
    FIRE,
    @SerializedName("Force")
    FORCE,
    @SerializedName("Lightning")
    LIGNTNING,
    @SerializedName("Necrotic")
    NECROTIC,
    @SerializedName("Piercing")
    PIERCING,
    @SerializedName("Poison")
    POISON,
    @SerializedName("Psychic")
    PSYCHIC,
    @SerializedName("Radiant")
    RADIANT,
    @SerializedName("Slashing")
    SLASHING,
    @SerializedName("Thunder")
    THUNDER;

    public static DamageType getDamageType(String damageType) {
        try {
            return DamageType.valueOf(damageType.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static List<DamageType> getDamageTypeList(String damageType) {
        List<DamageType> damages = new ArrayList<DamageType>();
        String[] split = damageType.split(",");
        for (String s : split) {
            damages.add(getDamageType(s));
        }
        return damages;
    }

}
