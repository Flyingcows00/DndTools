package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public enum Condition {
    @SerializedName("Blinded")
    BLINDED,
    @SerializedName("Charmed")
    CHARMED,
    @SerializedName("Deafened")
    DEAFENED,
    @SerializedName("Frightened")
    FRIGHTENED,
    @SerializedName("Grappled")
    GRAPPLED,
    @SerializedName("Incapacitated")
    INCAPACITATED,
    @SerializedName("Invisible")
    INVISIBLE,
    @SerializedName("Paralyzed")
    PARALYZED,
    @SerializedName("Petrified")
    PETRIFIED,
    @SerializedName("Poisoned")
    POISONED,
    @SerializedName("Prone")
    PRONE,
    @SerializedName("Restrained")
    RESTRAINED,
    @SerializedName("Stunned")
    STUNNED,
    @SerializedName("Unconscious")
    UNCONSCIOUS;

    public static Condition getCondition(String condition) {
        try {
            return Condition.valueOf(condition.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static List<Condition> getConditionList(String conditions) {
        List<Condition> conditionList = new ArrayList<Condition>();
        String[] split = conditions.split(",");
        for (String s : split) {
            conditionList.add(getCondition(s));
        }
        return conditionList;
    }

}
