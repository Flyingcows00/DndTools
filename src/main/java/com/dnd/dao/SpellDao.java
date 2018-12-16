package com.dnd.dao;

import com.dnd.model.Spell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class SpellDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private static final String INSERT_SPELL = "INSERT INTO spell(name, description, page, spell_range, components, materials, ritual, duration, concentration, casting_time, level, school, classes, higher_level) values(:name, :description, :page, :spell_range, :components, :materials, :ritual, :duration, :concentration, :casting_time, :level, :school, :classes, :higher_level);";
    private static final String GET_SPELLS = "SELECT name, description, page, spell_range, components, materials, ritual, duration, concentration, casting_time, level, school, classes, higher_level FROM spell;";
    private static final String GET_SPELL_BY_ID = "SELECT name, description, page, spell_range, components, materials, ritual, duration, concentration, casting_time, level, school, classes, higher_level FROM spell WHERE spell_id = :spell_id;";

    public List<Spell> getSpells() {
        return jdbcTemplate.query(GET_SPELLS, new BeanPropertyRowMapper<>(Spell.class));
    }

    public Spell getSpellById(String spellId) {
        Map<String, String> params = new HashMap<>();
        params.put("spell_id", spellId);
        List<Spell> spells = jdbcTemplate.query(GET_SPELL_BY_ID, params, new BeanPropertyRowMapper<>(Spell.class));
        if (spells.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return spells.get(0);
    }

    public void insertSpell(Spell spell) throws SQLException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", Optional.ofNullable(spell.getName()).orElse(""));
        params.addValue("description", Optional.ofNullable(spell.getDescription()).orElse(""));
        params.addValue("page", Optional.ofNullable(spell.getPage()).orElse(""));
        params.addValue("spell_range", Optional.ofNullable(spell.getSpellRange()).orElse(""));
        params.addValue("components", Optional.ofNullable(spell.getComponents()).orElse(""));
        params.addValue("materials", Optional.ofNullable(spell.getMaterials()).orElse(""));
        params.addValue("ritual", spell.isRitual());
        params.addValue("duration", Optional.ofNullable(spell.getDuration()).orElse(""));
        params.addValue("concentration", spell.isConcentration());
        params.addValue("casting_time", Optional.ofNullable(spell.getCastingTime()).orElse(""));
        params.addValue("level", spell.getLevel());
        params.addValue("school", spell.getSchool());
        params.addValue("classes", Optional.ofNullable(spell.getClasses()).orElse(""));
        params.addValue("higher_level", Optional.ofNullable(spell.getHigherLevel()).orElse(""));
        jdbcTemplate.update(INSERT_SPELL, params);
    }

}
