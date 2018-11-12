package com.dnd.dao;

import com.dnd.model.Spell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.dnd.utils.Utils.getEnumSqlArray;

@Repository
@Transactional
public class SpellDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private String insertSpell = "INSERT INTO spell(name, description, page, range, components, materials, ritual, duration, concentration, casting_time, level, school, classes, higher_level) values(:name, :description, :page, :range, :components, :materials, :ritual, :duration, :concentration, :casting_time, :level, :school, :classes, :higher_level);";
    private String getSpellDetails = "SELECT name, school, description FROM spell;";

    public List<Spell> getSpellDetails() {
        return jdbcTemplate.queryForList(getSpellDetails, new HashMap<>(), Spell.class);
    }

    public void insertSpell(Spell spell) throws SQLException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", Optional.ofNullable(spell.getName()).orElse(""));
        params.addValue("description", Optional.ofNullable(spell.getDescription()).orElse(""));
        params.addValue("page", Optional.ofNullable(spell.getPage()).orElse(""));
        params.addValue("range", Optional.ofNullable(spell.getRange()).orElse(""));
//        params.addValue("components", getEnumSqlArray(spell.getComponents(), dataSource));
        params.addValue("components", Optional.ofNullable(spell.getComponents()).orElse(""));
        params.addValue("materials", Optional.ofNullable(spell.getMaterials()).orElse(""));
        params.addValue("ritual", spell.isRitual());
        params.addValue("duration", Optional.ofNullable(spell.getDuration()).orElse(""));
        params.addValue("concentration", spell.isConcentration());
        params.addValue("casting_time", Optional.ofNullable(spell.getCastingTime()).orElse(""));
        params.addValue("level", spell.getLevel());
        params.addValue("school",spell.getSchool());
//        params.addValue("classes", getEnumSqlArray(spell.getClasses(), dataSource));
        params.addValue("classes", Optional.ofNullable(spell.getClasses()));
        params.addValue("higher_level", Optional.ofNullable(spell.getHigherLevel()));
        jdbcTemplate.update(insertSpell, params);
    }



}
