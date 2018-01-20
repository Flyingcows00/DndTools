package com.dnd.model;

import com.dnd.model.enums.Dice;

import java.util.Map;

public class Action {

    private int damageBonus;
    private Map<Dice, Integer> damageDice;
    private int attackBonus;
    private String description;
    private String name;


}
