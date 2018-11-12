package com.dnd.model.enums;

import java.util.ArrayList;
import java.util.List;

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
        try {
            return Condition.valueOf(condition.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static List<Condition> getConditionList(String conditions) {
        try {
            List<Condition> conditionList = new ArrayList<Condition>();
            String[] split = conditions.split(",");
            for (String s : split) {
                conditionList.add(getCondition(s));
            }
            return conditionList;
        } catch (NullPointerException e) {
            return null;
        }
    }

}
