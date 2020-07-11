package com.dnd.dao;

import com.dnd.model.Campaign;
import com.dnd.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class PlayerDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String CREATE_PLAYER = "INSERT INTO player(campaign_id, player_name, character_name) VALUES(:campaignId, :player, :character);";
    private static final String GET_PLAYERS_BY_CAMPAIGN = "SELECT player_name, character_name, notes FROM player WHERE campaign_id = :campaignId";

    public List<Player> getPlayersByCampaign(String campaignId) {
        Map<String, String> params = new HashMap<>();
        params.put("campaignId", campaignId);
        return jdbcTemplate.query(GET_PLAYERS_BY_CAMPAIGN, params, new BeanPropertyRowMapper<>(Player.class));
    }

    public void createPlayer(String campaignId, Player player) {
        Map<String, String> params = new HashMap<>();
        params.put("player", player.getPlayerName());
        params.put("character", player.getCharacterName());
        params.put("campaignId", campaignId);
        int rows = jdbcTemplate.update(CREATE_PLAYER, params);
        if (rows == 0) {
            throw new EmptyResultDataAccessException("Failed to create player with name " + player.getCharacterName(), 1);
        }
    }
}
