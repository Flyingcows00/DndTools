package com.dnd.model.adapter;

import com.dnd.model.Action;
import com.dnd.model.enums.ActionType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionRowMapper implements RowMapper<Action> {

    @Override
    public Action mapRow(ResultSet rs, int rowNum) throws SQLException {
        Action action = new Action();
        action.setActionType(ActionType.getActionTypeById(rs.getInt("action_type")));
        action.setAttackBonus(rs.getInt("attack_bonus"));
        action.setDamageBonus(rs.getInt("damage_bonus"));
        action.setDamageDice(rs.getString("damage_dice"));
        action.setDesc(rs.getString("description"));
        action.setName(rs.getString("name"));
        return action;
    }

}
