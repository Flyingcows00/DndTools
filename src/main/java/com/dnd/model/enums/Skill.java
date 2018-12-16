package com.dnd.model.enums;

public enum Skill {

    ACROBATICS,
    ANIMAL_HANDLING,
    ARCANA,
    ATHLETICS,
    DECEPTION,
    HISTORY,
    INSIGHT,
    INTIMIDATION,
    INVESTIGATION,
    MEDICINE,
    NATURE,
    PERCEPTION,
    PERFORMANCE,
    PERSUASION,
    RELIGION,
    SLIGHT_OF_HAND,
    STEALTH,
    SURVIVAL;

    public static Skill getSkill(String skill) {
        try {
            return Skill.valueOf(skill.trim().replaceAll("[\\s]+", "_").toUpperCase());
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
