package com.dnd.model.enums;

public enum Condition {

    BLINDED,
    CHARMED,
    DEAFENED,
    FRIGHTENED,
    GRAPPLED,
    INCAPACITATED,
    INVISIBLE,
    PARALYZED,
    PETRIFIED,
    POISONED,
    PRONE,
    RESTRAINED,
    STUNNED,
    UNCONSCIOUS;

    public static Condition getCondition(String condition) {
        return Condition.valueOf(condition.trim().toUpperCase());
    }

}
