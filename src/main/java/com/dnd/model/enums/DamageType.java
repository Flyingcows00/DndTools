package com.dnd.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum DamageType {

    ACID,
    BLUDGEONING,
    COLD,
    FIRE,
    FORCE,
    LIGNTNING,
    NECROTIC,
    PIERCING,
    POISON,
    PSYCHIC,
    RADIANT,
    SLASHING,
    THUNDER;

    public static DamageType getDamageType(String damageType) {
        try {
            return DamageType.valueOf(damageType.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static List<DamageType> getDamageTypeList(String damageType) {
        try {
            List<DamageType> damages = new ArrayList<DamageType>();
            String[] split = damageType.split(",");
            for (String s : split) {
                damages.add(getDamageType(s));
            }
            return damages;
        } catch (NullPointerException e) {
            return null;
        }
    }

}
