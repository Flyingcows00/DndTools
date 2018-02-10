package com.dnd.model.adapter;

import com.dnd.model.Monster;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonsterRowMapper implements RowMapper<Monster> {

    @Override
    public Monster mapRow(ResultSet rs, int rowNum) throws SQLException {
        Monster monster = new Monster();
        monster.setName(rs.getString("name"));
        monster.setSize(rs.getString("size"));
        monster.setType(rs.getString("type"));
        monster.setSubtype(rs.getString("subtype"));
        monster.setAlignment(rs.getString("alignment"));
        monster.setArmorClass(rs.getInt("armor_class"));
        monster.setHitPoints(rs.getInt("hit_points"));
        monster.setHitDice(rs.getString("hit_dice"));
        monster.setSpeed(rs.getString("speed"));
        monster.setSenses(rs.getString("senses"));
        monster.setLanguages(rs.getString("languages"));
        monster.setChallengeRating(rs.getFloat("challenge_rating"));
        monster.setDamageVulnerabilities(rs.getString("damage_vulnerabilities"));
        monster.setDamageResistances(rs.getString("damage_resistances"));
        monster.setDamageImmunities(rs.getString("damage_immunities"));
        monster.setConditionImmunities(rs.getString("condition_immunities"));
        monster.addAbility("strength", rs.getInt("strength"));
        monster.addAbility("dexterity", rs.getInt("dexterity"));
        monster.addAbility("constitution", rs.getInt("constitution"));
        monster.addAbility("wisdom", rs.getInt("wisdom"));
        monster.addAbility("intelligence", rs.getInt("intelligence"));
        monster.addAbility("charisma", rs.getInt("charisma"));
        monster.addSavingThrow("strength", rs.getInt("strength_save"));
        monster.addSavingThrow("dexterity", rs.getInt("dexterity_save"));
        monster.addSavingThrow("constitution", rs.getInt("constitution_save"));
        monster.addSavingThrow("wisdom", rs.getInt("wisdom_save"));
        monster.addSavingThrow("intelligence", rs.getInt("intelligence_save"));
        monster.addSavingThrow("charisma", rs.getInt("charisma_save"));
        monster.addSkill("acrobatics", rs.getInt("acrobatics"));
        monster.addSkill("animal_handling", rs.getInt("animal_handling"));
        monster.addSkill("arcana", rs.getInt("arcana"));
        monster.addSkill("athletics", rs.getInt("athletics"));
        monster.addSkill("deception", rs.getInt("deception"));
        monster.addSkill("history", rs.getInt("history"));
        monster.addSkill("insight", rs.getInt("insight"));
        monster.addSkill("intimidation", rs.getInt("intimidation"));
        monster.addSkill("investigation", rs.getInt("investigation"));
        monster.addSkill("medicine", rs.getInt("medicine"));
        monster.addSkill("nature", rs.getInt("nature"));
        monster.addSkill("perception", rs.getInt("perception"));
        monster.addSkill("performance", rs.getInt("performance"));
        monster.addSkill("persuasion", rs.getInt("persuasion"));
        monster.addSkill("religion", rs.getInt("religion"));
        monster.addSkill("slight_of_hand", rs.getInt("slight_of_hand"));
        monster.addSkill("stealth", rs.getInt("stealth"));
        monster.addSkill("survival", rs.getInt("survival"));
        return monster;
    }
}
