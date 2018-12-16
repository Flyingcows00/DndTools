package com.dnd.model.enums;

public enum CreatureType {

    ABERRATION,
    BEAST,
    CELESTIAL,
    CONSTRUCT,
    DRAGON,
    ELEMENTAL,
    FEY,
    FIEND,
    GIANT,
    HUMANOID,
    MONSTROSITIES,
    OOZE,
    PLANT,
    UNDEAD;

    public static CreatureType getCreatureType(String type) {
        try {
            return CreatureType.valueOf(type.trim().replaceAll("[\\s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}