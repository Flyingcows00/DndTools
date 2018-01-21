package com.dnd.dao;

import com.dnd.model.Action;
import com.dnd.model.Monster;
import com.dnd.model.enums.Ability;
import com.dnd.model.enums.ActionType;
import com.dnd.model.enums.Skill;
import com.dnd.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Array;
import java.sql.SQLException;
import java.util.*;

import static com.dnd.utils.Utils.getEnumSqlArray;
import static com.dnd.utils.Utils.getEnumValue;

@Repository
@Transactional
public class MonsterDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Resource(name = "getAllMonsterNames")
    private String getAllMonsterNames;
    @Resource(name = "getAllMonsters")
    private String getAllMonsters;
    @Resource(name = "getMonsterByName")
    private String getMonsterByName;
    @Resource(name = "insertMonster")
    private String insertMonster;
    @Resource(name = "insertAction")
    private String insertAction;

    public List<Monster> getAllMonsters() {
        return null;
    }

    public List<String> getAllMonsterNames() {
        return null;
    }

    public Monster getMonsterByName(String name) {
        return null;
    }

    private List<Action> getActionsByMonsterName(String name){
        return null;
    }

    public void createMonster(Monster monster, int user_id) throws SQLException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("created_by", user_id);
        params.addValue("name", monster.getName()) ;
        params.addValue("size", getEnumValue(monster.getSize()));
        params.addValue("type", getEnumValue(monster.getType()));
        params.addValue("subtype", getEnumValue(monster.getSubtype()));
        params.addValue("alignment", getEnumValue(monster.getAlignment()));
        params.addValue("armor_class", monster.getArmorClass());
        params.addValue("hit_points", monster.getHitPoints());
        params.addValue("hit_dice", monster.getHitDice());
        params.addValue("speed", monster.getSpeed());
        params.addValue("senses", monster.getSenses());
        params.addValue("languages", monster.getLanguages());
        params.addValue("challenge_rating", monster.getChallengeRating());
        params.addValue("damage_vulnerabilities", getEnumSqlArray(monster.getDamageVulnerabilities(), dataSource));
        params.addValue("damage_resistances", getEnumSqlArray(monster.getDamageResistances(), dataSource));
        params.addValue("damage_immunities", getEnumSqlArray(monster.getDamageImmunities(), dataSource));
        params.addValue("condition_immunities", getEnumSqlArray(monster.getConditionImmunities(), dataSource));
        params.addValue("strength", monster.getAbilities().get(Ability.STRENGTH));
        params.addValue("dexterity", monster.getAbilities().get(Ability.DEXTERITY));
        params.addValue("constitution", monster.getAbilities().get(Ability.CONSTITUTION));
        params.addValue("wisdom", monster.getAbilities().get(Ability.WISDOM));
        params.addValue("intelligence", monster.getAbilities().get(Ability.INTELLIGENCE));
        params.addValue("charisma", monster.getAbilities().get(Ability.CHARISMA));
        params.addValue("strength_save", monster.getSavingThrows().get(Ability.STRENGTH));
        params.addValue("dexterity_save", monster.getSavingThrows().get(Ability.DEXTERITY));
        params.addValue("constitution_save", monster.getSavingThrows().get(Ability.CONSTITUTION));
        params.addValue("wisdom_save", monster.getSavingThrows().get(Ability.WISDOM));
        params.addValue("intelligence_save", monster.getSavingThrows().get(Ability.INTELLIGENCE));
        params.addValue("charisma_save", monster.getSavingThrows().get(Ability.CHARISMA));
        params.addValue("acrobatics", monster.getSkills().get(Skill.ACROBATICS));
        params.addValue("animal_handling",  monster.getSkills().get(Skill.ANIMAL_HANDLING));
        params.addValue("arcana",  monster.getSkills().get(Skill.ARCANA));
        params.addValue("athletics",  monster.getSkills().get(Skill.ATHLETICS));
        params.addValue("deception",  monster.getSkills().get(Skill.DECEPTION));
        params.addValue("history",  monster.getSkills().get(Skill.HISTORY));
        params.addValue("insight",  monster.getSkills().get(Skill.INSIGHT));
        params.addValue("intimidation",  monster.getSkills().get(Skill.INTIMIDATION));
        params.addValue("investigation",  monster.getSkills().get(Skill.INVESTIGATION));
        params.addValue("medicine",  monster.getSkills().get(Skill.MEDICINE));
        params.addValue("nature",  monster.getSkills().get(Skill.NATURE));
        params.addValue("perception",  monster.getSkills().get(Skill.PERCEPTION));
        params.addValue("performance",  monster.getSkills().get(Skill.PERFORMANCE));
        params.addValue("persuasion",  monster.getSkills().get(Skill.PERSUASION));
        params.addValue("religion",  monster.getSkills().get(Skill.RELIGION));
        params.addValue("slight_of_hand",  monster.getSkills().get(Skill.SLIGHT_OF_HAND));
        params.addValue("stealth",  monster.getSkills().get(Skill.STEALTH));
        params.addValue("survival",  monster.getSkills().get(Skill.SURVIVAL));

        jdbcTemplate.update(insertMonster, params);
        insertActions(ActionType.SPECIAL_ABILITY, monster.getSpecialAbilities(), monster.getName());
        insertActions(ActionType.ACTION, monster.getActions(), monster.getName());
        insertActions(ActionType.LEGENDARY_ACTION, monster.getLegendaryActions(), monster.getName());
    }

    private void insertActions(ActionType actionType, List<Action> actions, String monsterName) {
        for (Action action : actions) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("monster_name", monsterName);
            params.addValue("action_type", actionType.getId());
            params.addValue("name", action.getName());
            params.addValue("attack_bonus", action.getAttackBonus());
            params.addValue("damage_bonus", action.getDamageBonus());
            params.addValue("damage_dice", action.getDamageDice());
            params.addValue("description", action.getDesc());
            jdbcTemplate.update(insertAction, params);
        }
    }


}
