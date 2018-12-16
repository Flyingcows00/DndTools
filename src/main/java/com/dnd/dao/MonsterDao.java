package com.dnd.dao;

import com.dnd.model.Action;
import com.dnd.model.Monster;
import com.dnd.model.adapter.ActionRowMapper;
import com.dnd.model.adapter.MonsterRowMapper;
import com.dnd.model.enums.Ability;
import com.dnd.model.enums.ActionType;
import com.dnd.model.enums.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.dnd.utils.Utils.getEnumSqlArray;
import static com.dnd.utils.Utils.getEnumValue;

@Repository
@Transactional
public class MonsterDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private static final String GET_ALL_MONSTER_NAMES = "SELECT name FROM monster;";
    private static final String GET_MONSTER_BY_NAME = "SELECT u.user_id, u.username AS username, m.name, m.size, m.type, m.subtype, m.alignment, m.armor_class, m.hit_points, m.hit_dice, m.speed, m.senses, m.languages, m.challenge_rating, m.damage_vulnerabilities, m.damage_resistances, m.damage_immunities, m.condition_immunities, al.strength, al.dexterity, al.constitution, al.wisdom, al.intelligence, al.charisma, st.strength AS strength_save, st.dexterity AS dexterity_save, st.constitution AS constitution_save, st.wisdom AS wisdom_save, st.intelligence AS intelligence_save, st.charisma AS charisma_save, sk.acrobatics, sk.animal_handling, sk.arcana, sk.athletics, sk.deception, sk.history, sk.insight, sk.intimidation, sk.investigation, sk.medicine, sk.nature, sk.perception, sk.performance, sk.persuasion, sk.religion, sk.slight_of_hand, sk.stealth, sk.survival FROM monster AS m INNER JOIN ability_levels AS al ON m.name = al.name INNER JOIN saving_throws AS st ON m.name = st.name INNER JOIN skills AS sk ON m.name = sk.name INNER JOIN users AS u ON m.created_by = u.user_id WHERE UPPER(m.name) = UPPER(:monster_name);";
    private static final String GET_ACTIONS_BY_MONSTER_NAME = "SELECT name, description, action_type, damage_bonus, damage_dice, attack_bonus FROM action WHERE monster_name = :monster_name;";
    private static final String INSERT_MONSTER = "INSERT INTO monster(name,created_by,size,type,subtype,alignment,armor_class,hit_points,hit_dice,speed,senses,languages,challenge_rating,damage_vulnerabilities,damage_resistances,damage_immunities,condition_immunities) VALUES(:name,:created_by,:size,:type,:subtype,:alignment,:armor_class,:hit_points,:hit_dice,:speed,:senses,:languages,:challenge_rating,:damage_vulnerabilities,:damage_resistances,:damage_immunities,:condition_immunities); INSERT INTO ability_levels(name,strength,dexterity,constitution,wisdom,intelligence,charisma) VALUES(:name,:strength,:dexterity,:constitution,:wisdom,:intelligence,:charisma); INSERT INTO saving_throws(name,strength,dexterity,constitution,wisdom,intelligence,charisma) VALUES(:name,:strength_save,:dexterity_save,:constitution_save,:wisdom_save,:intelligence_save,:charisma_save); INSERT INTO skills(name,acrobatics,animal_handling,arcana,athletics,deception,history,insight,intimidation,investigation,medicine,nature,perception,performance,persuasion,religion,slight_of_hand,stealth,survival) VALUES(:name,:acrobatics,:animal_handling,:arcana,:athletics,:deception,:history,:insight,:intimidation,:investigation,:medicine,:nature,:perception,:performance,:persuasion,:religion,:slight_of_hand,:stealth,:survival);";
    private static final String INSTERT_ACTION = "INSERT INTO action(monster_name,action_type,name,damage_bonus,damage_dice,attack_bonus,description) VALUES(:monster_name,:action_type,:name,:damage_bonus,:damage_dice,:attack_bonus,:description);";


    public List<String> getAllMonsterNames() {
        return jdbcTemplate.queryForList(GET_ALL_MONSTER_NAMES, new HashMap<>(), String.class);
    }

    public Monster getMonsterByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("monster_name", name);
        Monster monster = jdbcTemplate.queryForObject(GET_MONSTER_BY_NAME, params, new MonsterRowMapper());
        List<Action> actions = getActionsByMonsterName(name);
        monster.setActions(actions);
        return monster;
    }

    private List<Action> getActionsByMonsterName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("monster_name", name);
        return jdbcTemplate.query(GET_ACTIONS_BY_MONSTER_NAME, params, new ActionRowMapper());
    }

    public void createMonster(Monster monster, int user_id) throws SQLException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("created_by", user_id);
        params.addValue("name", monster.getName());
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
        params.addValue("animal_handling", monster.getSkills().get(Skill.ANIMAL_HANDLING));
        params.addValue("arcana", monster.getSkills().get(Skill.ARCANA));
        params.addValue("athletics", monster.getSkills().get(Skill.ATHLETICS));
        params.addValue("deception", monster.getSkills().get(Skill.DECEPTION));
        params.addValue("history", monster.getSkills().get(Skill.HISTORY));
        params.addValue("insight", monster.getSkills().get(Skill.INSIGHT));
        params.addValue("intimidation", monster.getSkills().get(Skill.INTIMIDATION));
        params.addValue("investigation", monster.getSkills().get(Skill.INVESTIGATION));
        params.addValue("medicine", monster.getSkills().get(Skill.MEDICINE));
        params.addValue("nature", monster.getSkills().get(Skill.NATURE));
        params.addValue("perception", monster.getSkills().get(Skill.PERCEPTION));
        params.addValue("performance", monster.getSkills().get(Skill.PERFORMANCE));
        params.addValue("persuasion", monster.getSkills().get(Skill.PERSUASION));
        params.addValue("religion", monster.getSkills().get(Skill.RELIGION));
        params.addValue("slight_of_hand", monster.getSkills().get(Skill.SLIGHT_OF_HAND));
        params.addValue("stealth", monster.getSkills().get(Skill.STEALTH));
        params.addValue("survival", monster.getSkills().get(Skill.SURVIVAL));
        jdbcTemplate.update(INSERT_MONSTER, params);
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
            jdbcTemplate.update(INSTERT_ACTION, params);
        }
    }


}
