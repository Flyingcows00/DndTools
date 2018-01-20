package com.dnd.model;

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

    public static CreatureType getCreatureType(String type){
        return CreatureType.valueOf(type.trim().replaceAll("[//s]+", " ").toUpperCase());
    }

}
