package com.dnd.model.enums;

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
        return DamageType.valueOf(damageType.trim().toUpperCase());
    }

}
