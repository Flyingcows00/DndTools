package com.dnd.dao;

import com.dnd.model.Spell;
import com.dnd.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

import static com.dnd.utils.Utils.getEnumSqlArray;

@Repository
@Transactional
public class SpellDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Resource(name = "insertSpell")
    private String insertSpell;

    public void insertSpell(Spell spell) throws SQLException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", spell.getName());
        params.addValue("description", spell.getDescription());
        params.addValue("page", spell.getPage());
        params.addValue("range", spell.getRange());
        params.addValue("components", getEnumSqlArray(spell.getComponents(), dataSource));
        params.addValue("materials", spell.getMaterials());
        params.addValue("ritual", spell.isRitual());
        params.addValue("duration", spell.getDuration());
        params.addValue("concentration", spell.isConcentration());
        params.addValue("casting_time", spell.getCastingTime());
        params.addValue("level", spell.getLevel());
        params.addValue("school", spell.getSchool().name());
        params.addValue("classes", getEnumSqlArray(spell.getClasses(), dataSource));
        params.addValue("higher_level", spell.getHigherLevel());
        jdbcTemplate.update(insertSpell, params);
    }

}
