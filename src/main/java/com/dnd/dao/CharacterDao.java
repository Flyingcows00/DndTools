package com.dnd.dao;

import com.dnd.model.Campaign;
import com.dnd.model.Character;
import com.dnd.model.Player;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.lang.String.join;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

@Repository
@Transactional
public class CharacterDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String GET_CHARACTERS = "SELECT c.character_id, c.player_id, c.character_name, c.alive, c.notes, cc.campaign_id FROM `character` c LEFT JOIN character_campaign cc on c.character_id = cc.character_id %s";
    private static final String CREATE_CHARACTER = "INSERT INTO `character`(player_id, character_name) VALUES(:playerId, :name);";
    private static final String ADD_CHARACTER_TO_CAMPAIGNS = "INSERT INTO character_campaign VALUES(:characterId, :campaignId);";
    private static final String REMOVE_CHARACTER_FROM_CAMPAIGN = "DELETE FROM character_campaign WHERE character_id=:characterId;";
    private static final String UPDATE_CHARACTER = "UPDATE `character` SET %s WHERE character_id = :characterId";
    private static final String DELETE_CHARACTER = "DELETE FROM `character` WHERE character_id = :characterId";

    public Character getCharacter(int characterId) {
        try {
            return getCharacters(0, 0, characterId).iterator().next();
        } catch (NoSuchElementException e) {
            throw new EmptyResultDataAccessException(1);
        }
    }

    public Collection<Character> getCharacters(int campaignId, int playerId, int characterId) {
        List<String> where = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        if (campaignId > 0) {
            where.add(format("cc.campaign_id = %s", campaignId));
        }
        if (playerId > 0) {
            where.add(format("c.player_id = %s", playerId));
        }
        if (characterId > 0) {
            where.add(format("c.character_id = %s", characterId));
        }
        String whereClause = join(" AND ", where);
        List<Character> characters = jdbcTemplate.query(format(GET_CHARACTERS, (isBlank(whereClause) ? "" : "WHERE ") + whereClause), params, new BeanPropertyRowMapper<>(Character.class));
        return characters.stream()
                .collect(Collectors.toMap(
                        Character::getCharacterId,
                        Character::new,
                        Character::merge))
                .values();
    }

    public void createCharacter(int playerId, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("playerId", playerId);
        params.put("name", name);
        jdbcTemplate.update(CREATE_CHARACTER, params);
    }

    private void addCharacterToCampaigns(int characterId, List<Integer> campaignIds) {
        List<MapSqlParameterSource> parameters = new ArrayList<>();
        campaignIds.forEach(campaignId -> {
            Map<String, Integer> map = new HashMap<>();
            map.put("characterId", characterId);
            map.put("campaignId", campaignId);
            parameters.add(new MapSqlParameterSource(map));
        });
        jdbcTemplate.batchUpdate(ADD_CHARACTER_TO_CAMPAIGNS, parameters.toArray(new MapSqlParameterSource[0]));
    }

    private void removeCharacterFromAllCampaigns(int characterId) {
        Map<String, Object> params = new HashMap<>();
        params.put("characterId", characterId);
        jdbcTemplate.update(REMOVE_CHARACTER_FROM_CAMPAIGN, params);
    }

    public void updateCharacter(int characterId, Character character) {
        List<String> setters = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("characterId", characterId);
        if (isNotBlank(character.getCharacterName())) {
            setters.add(format("character_name='%s'", character.getCharacterName()));
        }
        if (isNotBlank(character.getNotes())) {
            setters.add(format("notes='%s'", character.getNotes()));
        }
        if (character.getAlive() != null) {
            setters.add(format("alive=%b", character.getAlive()));
        }
        if (character.getPlayerId() > 0) {
            setters.add(format("player_id='%s'", character.getPlayerId()));
        }
        if (!setters.isEmpty()) {
            jdbcTemplate.update(format(UPDATE_CHARACTER, join(",", setters)), params);
        }
        if (!isEmpty(character.getCampaignIds())) {
            removeCharacterFromAllCampaigns(characterId);
            addCharacterToCampaigns(characterId, character.getCampaignIds());
        }
    }

    public void deleteCharacter(int characterId) {
        Map<String, Object> params = new HashMap<>();
        params.put("characterId", characterId);
        jdbcTemplate.update(DELETE_CHARACTER, params);
    }
}
