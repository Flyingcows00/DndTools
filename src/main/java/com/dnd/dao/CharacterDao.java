package com.dnd.dao;

import com.dnd.model.Campaign;
import com.dnd.model.Character;
import com.dnd.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CharacterDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String GET_CHARACTERS_BY_PLAYER = "SELECT c.character_id, c.character_name, c.notes, cc.campaign_id FROM `character` c LEFT JOIN character_campaign cc on c.character_id = cc.character_id WHERE c.player_id = :playerId";
    private static final String CREATE_CHARACTER = "INSERT INTO `character`(player_id, character_name) VALUES(:playerId, :name);";
    private static final String ADD_CHARACTER_TO_CAMPAIGN = "INSERT INTO character_campaign VALUES(:characterId, :campaignId);";

    public Collection<Character> getCharacters(int playerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("playerId", playerId);
        List<Character> characters = jdbcTemplate.query(GET_CHARACTERS_BY_PLAYER, params, new BeanPropertyRowMapper<>(Character.class));
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

    public void addCharacterToCampaign(int characterId, int campaignId) {
        Map<String, Object> params = new HashMap<>();
        params.put("characterId", characterId);
        params.put("campaignId", campaignId);
        jdbcTemplate.update(ADD_CHARACTER_TO_CAMPAIGN, params);
    }
}
