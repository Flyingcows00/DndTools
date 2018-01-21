package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

public enum Skill {

    @SerializedName("Acrobatics")
    ACROBATICS,
    @SerializedName("Animal Handling")
    ANIMAL_HANDLING,
    @SerializedName("Arcana")
    ARCANA,
    @SerializedName("Athletics")
    ATHLETICS,
    @SerializedName("Deception")
    DECEPTION,
    @SerializedName("History")
    HISTORY,
    @SerializedName("Insight")
    INSIGHT,
    @SerializedName("Intimidation")
    INTIMIDATION,
    @SerializedName("Investigation")
    INVESTIGATION,
    @SerializedName("Medicine")
    MEDICINE,
    @SerializedName("Nature")
    NATURE,
    @SerializedName("Perception")
    PERCEPTION,
    @SerializedName("Performance")
    PERFORMANCE,
    @SerializedName("Persuasion")
    PERSUASION,
    @SerializedName("Religion")
    RELIGION,
    @SerializedName("Slight of Hand")
    SLIGHT_OF_HAND,
    @SerializedName("Stealth")
    STEALTH,
    @SerializedName("Survival")
    SURVIVAL;

    public static Skill getSkill(String skill) {
        try {
            return Skill.valueOf(skill.trim().replaceAll("[//s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static boolean isSkill(String skill) {
        try {
            getSkill(skill);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
