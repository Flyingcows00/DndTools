package com.dnd.model.enums;

import com.dnd.model.Action;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum ActionType {

    SPECIAL_ABILITY(1),
    ACTION(2),
    LEGENDARY_ACTION(3);

    private int id;

    ActionType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ActionType getActionTypeById(int actionId) {
        ActionType result = null;
        for (ActionType type : ActionType.values()) {
            if (type.getId() == actionId) {
                result = type;
                break;
            }
        }
        return result;
    }

    public static ActionType getActionType(String actionType) {
        try {
            return ActionType.valueOf(actionType.trim().replaceAll("[\\s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static Map<ActionType, List<Action>> splitByActionType(List<Action> actions) {
        return actions.stream().collect(Collectors.groupingBy(Action::getActionType));
    }


}
