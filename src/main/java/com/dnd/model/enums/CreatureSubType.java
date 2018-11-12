package com.dnd.model.enums;

public enum CreatureSubType {

    AIR,
    ANGEL,
    AQUATIC,
    ARCHON,
    AUGMENTED,
    CHAOTIC,
    COLD,
    EARTH,
    EVIL,
    EXTRAPLANAR,
    FIRE,
    GOBLINOID,
    GOOD,
    INCORPOREAL,
    LAWFUL,
    NATIVE,
    REPTILIAN,
    SHAPECHANGER,
    SWARM,
    WATER;

    public static CreatureSubType getCreatureSubType(String type) {
        try {
            return CreatureSubType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }


}
