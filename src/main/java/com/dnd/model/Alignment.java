package com.dnd.model;

public enum Alignment {

    LAWFUL_GOOD,
    LAWFUL_NEUTRAL,
    LAWFUL_EVIL,
    NEUTRAL_GOOD,
    NEUTRAL_NEUTRAL,
    NEUTRAL_EVIL,
    CHAOTIC_GOOD,
    CHAOTIC_NEUTRAL,
    CHAOTIC_EVIL;

    public static Alignment getAlignment(String alignment){
        return Alignment.valueOf(alignment.trim().replaceAll("[//s]+", " ").toUpperCase());
    }


}
