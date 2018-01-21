package com.dnd.model.enums;

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

    public static ActionType getActionType(String actionType) {
        try {
            return ActionType.valueOf(actionType.trim().replaceAll("[//s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}
