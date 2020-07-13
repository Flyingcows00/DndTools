package com.dnd.dao;

import com.dnd.model.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CampaignDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String CREATE_CAMPAIGN = "INSERT INTO campaign(name) VALUES(:name);";
    private static final String DELETE_CAMPAIGN = "DELETE FROM campaign WHERE name=:name";
    private static final String GET_CAMPAIGNS = "SELECT campaign_id, name, create_timestamp FROM campaign;";
    private static final String GET_CAMPAIGN = "SELECT campaign_id, name, create_timestamp FROM campaign WHERE name = :name;";

    public List<Campaign> getCampaigns() {
        return jdbcTemplate.query(GET_CAMPAIGNS, new BeanPropertyRowMapper<>(Campaign.class));
    }

    public Campaign getCampaign(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        return jdbcTemplate.queryForObject(GET_CAMPAIGN, params, new BeanPropertyRowMapper<>(Campaign.class));
    }

    public void createCampaign(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        jdbcTemplate.update(CREATE_CAMPAIGN, params);
    }

    public void deleteCampaign(String campaign) {
        Map<String, String> params = new HashMap<>();
        params.put("name", campaign);
        jdbcTemplate.update(DELETE_CAMPAIGN, params);
    }

}
