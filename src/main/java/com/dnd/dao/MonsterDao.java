package com.dnd.dao;

import com.dnd.model.Monster;
import com.dnd.model.enums.Ability;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.postgresql.jdbc42.Jdbc42Array;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

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
    @Resource(name = "getMonstersByName")
    private String getMonstersByName;

    @Resource(name = "insertMonster")
    private String insertMonster;
    @Resource(name = "insertAbilities")
    private String insertAbilities;
    @Resource(name = "insertAction")
    private String insertAction;
    @Resource(name = "insertSavingThrows")
    private String insertSavingThrows;
    @Resource(name = "insertSkills")
    private String insertSkills;

    public List<Monster> getAllMonsters() {
        return null;
    }

    public List<String> getAllMonsterNames() {
        return null;
    }

    public Monster getMonsterByName(String name) {
        return null;
    }

    public void createMonster(Monster monster) throws SQLException {
        MapSqlParameterSource params = new MapSqlParameterSource();
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
        params.addValue("damage_vulnerabilities", getEnumList(monster.getDamageVulnerabilities()));
        params.addValue("damage_resistances", getEnumList(monster.getDamageResistances()));
        params.addValue("damage_immunities", getEnumList(monster.getDamageImmunities()));
        params.addValue("condition_immunities", getEnumList(monster.getConditionImmunities()));

        int id = jdbcTemplate.queryForObject(insertMonster, params, Integer.class);
        insertAbilities(monster.getAbilities(), id);
        insertSavingThrows(monster.getSavingThrows(), id);
        System.out.println(id);
    }

    private void insertAbilities(Map<Ability, Integer> abilities, int id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("monster_id", id);
        params.addValue("strength", abilities.get(Ability.STRENGTH));
        params.addValue("dexterity", abilities.get(Ability.DEXTERITY));
        params.addValue("constitution", abilities.get(Ability.CONSTITUTION));
        params.addValue("wisdom", abilities.get(Ability.WISDOM));
        params.addValue("intelligence", abilities.get(Ability.INTELLIGENCE));
        params.addValue("charisma", abilities.get(Ability.CHARISMA));

        jdbcTemplate.update(insertAbilities, params);
    }

    private void insertSavingThrows(Map<Ability, Integer> savingThrows, int id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("monster_id", id);
        params.addValue("strength", savingThrows.get(Ability.STRENGTH));
        params.addValue("dexterity", savingThrows.get(Ability.DEXTERITY));
        params.addValue("constitution", savingThrows.get(Ability.CONSTITUTION));
        params.addValue("wisdom", savingThrows.get(Ability.WISDOM));
        params.addValue("intelligence", savingThrows.get(Ability.INTELLIGENCE));
        params.addValue("charisma", savingThrows.get(Ability.CHARISMA));

        jdbcTemplate.update(insertSavingThrows, params);
    }

    private Array getEnumList(List<? extends Enum> list) throws SQLException {
        if (!CollectionUtils.isEmpty(list)) {
            String[] array = list.stream().filter(a -> a != null).map(Enum::name).toArray(String[]::new);
            return dataSource.getConnection().createArrayOf("varchar", array);
        }
        return dataSource.getConnection().createArrayOf("varchar", new String[]{});
    }

    private String getEnumValue(Enum value) {
        if (value != null) {
            return value.name();
        }
        return null;
    }

}
