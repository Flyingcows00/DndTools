package com.dnd.model;

import com.dnd.model.enums.*;

import java.util.List;
import java.util.Map;

public class Monster {

    private String name;
    private Size size;
    private CreatureType type;
    private Alignment alignment;
    private int armorClass;
    private int hitPoints;
    private int hitDiceCount;
    private Dice hitDice;
    private int walkingSpeed;
    private int swimingSpeed;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int passivePerception;
    private int darkvision;
    private int blindsight;
    private int truesight;
    private float challengeRating;
    private List<DamageType> damageVulnerabilities;
    private List<DamageType> damaveResistances;
    private List<DamageType> damageImmunities;
    private List<Condition> conditionImmunities;
    private List<Language> writtenLanguages;
    private List<Language> spokenLanguages;

    //Saves? - figure out formulas for these
    //
//    private int

    private Map<String, String> abilities;
    private List<Action> actions;

}
