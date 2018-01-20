package com.dnd.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Monster {

    private String name;
    private Size size;
    private CreatureType type;
    private Alignment name;
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

    //Saves? - figure out formulas for these
    //
    private int

    private Map<String, String> abilities;
    private List<Action> actions;

}
