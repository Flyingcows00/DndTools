package com.dnd.model;

import com.dnd.model.adapter.ConditionAdapter;
import com.dnd.model.adapter.DamageAdapter;
import com.dnd.model.enums.*;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monster {

    private Map<Ability, Integer> abilities;
    private Map<Ability, Integer> savingThrows;
    private Map<Skill, Integer> skills;
    @JsonAdapter(DamageAdapter.class)
    private List<DamageType> damageVulnerabilities;
    @JsonAdapter(DamageAdapter.class)
    private List<DamageType> damageResistances;
    @JsonAdapter(DamageAdapter.class)
    private List<DamageType> damageImmunities;
    @JsonAdapter(ConditionAdapter.class)
    private List<Condition> conditionImmunities;
    private List<Action> specialAbilities;
    private List<Action> actions;
    private List<Action> legendaryActions;
    private String name;
    private Size size;
    private CreatureType type;
    private CreatureSubType subtype;
    private Alignment alignment;
    private int armorClass;
    private int hitPoints;
    private String hitDice;
    private String speed;
    private String senses;
    private String languages;
    private float challengeRating;

    public Monster() {
        abilities = new HashMap<>();
        savingThrows = new HashMap<>();
        skills = new HashMap<>();
        damageVulnerabilities = new ArrayList<>();
        damageResistances = new ArrayList<>();
        damageImmunities = new ArrayList<>();
        conditionImmunities = new ArrayList<>();
        specialAbilities = new ArrayList<>();
        actions = new ArrayList<>();
        legendaryActions = new ArrayList<>();
    }

    public void setStrength(int strength) {
        abilities.put(Ability.STRENGTH, strength);
    }

    public void setSize(String size) {
        this.size = Size.getSize(size);
    }

    public void setType(String type) {
        this.type = CreatureType.getCreatureType(type);
    }

    public void setSubtype(String subtype) {
        this.subtype = CreatureSubType.getCreatureSubType(subtype);
    }

    public void setAlignment(String alignment) {
        this.alignment = Alignment.getAlignment(alignment);
    }

    public void setDamageVulnerabilities(String damageVulnerabilities) {
        this.damageVulnerabilities = DamageType.getDamageTypeList(damageVulnerabilities);
    }

    public void setDamageResistances(String damageResistances) {
        this.damageResistances = DamageType.getDamageTypeList(damageResistances);
    }

    public void setDamageImmunities(String damageImmunities) {
        this.damageImmunities = DamageType.getDamageTypeList(damageImmunities);
    }

    public void setConditionImmunities(String conditionImmunities) {
        this.conditionImmunities = Condition.getConditionList(conditionImmunities);
    }

    public void addAbility(String ability, int value) {
        this.abilities.put(Ability.getAbility(ability), value);
    }

    public void addSavingThrow(String ability, int value) {
        this.savingThrows.put(Ability.getAbility(ability), value);
    }

    public void addSkill(String skill, int value) {
        this.skills.put(Skill.getSkill(skill), value);
    }

    public Map<Ability, Integer> getAbilities() {
        return abilities;
    }

    public Map<Ability, Integer> getSavingThrows() {
        return savingThrows;
    }

    public Map<Skill, Integer> getSkills() {
        return skills;
    }

    public List<DamageType> getDamageVulnerabilities() {
        return damageVulnerabilities;
    }

    public List<DamageType> getDamageResistances() {
        return damageResistances;
    }

    public List<DamageType> getDamageImmunities() {
        return damageImmunities;
    }

    public List<Condition> getConditionImmunities() {
        return conditionImmunities;
    }

    public List<Action> getSpecialAbilities() {
        return specialAbilities;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Action> getLegendaryActions() {
        return legendaryActions;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public CreatureType getType() {
        return type;
    }

    public CreatureSubType getSubtype() {
        return subtype;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public String getHitDice() {
        return hitDice;
    }

    public String getSpeed() {
        return speed;
    }

    public String getSenses() {
        return senses;
    }

    public String getLanguages() {
        return languages;
    }

    public float getChallengeRating() {
        return challengeRating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setSenses(String senses) {
        this.senses = senses;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setChallengeRating(float challengeRating) {
        this.challengeRating = challengeRating;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
