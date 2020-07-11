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

    public List<Player> getPlayers() {
        return jdbcTemplate.query(GET_PLAYERS, new BeanPropertyRowMapper<>(Player.class));
    }

    public void createPlayer(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        jdbcTemplate.update(CREATE_PLAYER, params);
    }

}
