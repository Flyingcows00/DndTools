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
    private static final String GET_PLAYERS = "SELECT player_id, player_name FROM player";
    private static final String CREATE_PLAYER = "INSERT INTO player(player_name) VALUES(:name);";
    private static final String DELETE_PLAYER = "DELETE FROM player WHERE player_id=:playerId;";

    public List<Player> getPlayers() {
        return jdbcTemplate.query(GET_PLAYERS, new BeanPropertyRowMapper<>(Player.class));
    }

    public void createPlayer(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        jdbcTemplate.update(CREATE_PLAYER, params);
    }

    public void deletePlayer(int playerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("playerId", playerId);
        jdbcTemplate.update(DELETE_PLAYER, params);
    }

}
