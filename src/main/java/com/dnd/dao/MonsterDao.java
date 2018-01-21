package com.dnd.dao;

import com.dnd.model.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class MonsterDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Resource(name = "getAllMonsterNames")
    private String getAllMonsterNames;
    @Resource(name = "getAllMonsters")
    private String getAllMonsters;
    @Resource(name = "getMonstersByName")
    private String getMonstersByName;
    @Resource(name = "insertNewMonster")
    private String insertNewMonster;

    public List<Monster> getAllMonsters() {
        return null;
    }

    public List<String> getAllMonsterNames() {
        return null;
    }

    public Monster getMonsterByName(String name) {
        return null;
    }

    public void createMonster(Monster monster) {
        System.out.println(getAllMonsterNames);
    }

}
