package com.dnd.model.enums;

public enum Size {

    FINE(.5f, .125f),
    DIMINUTIVE(1, 1),
    TINY(2, 8),
    SMALL(4, 60),
    MEDIUM(8, 500),
    LARGE(16, 4000),
    HUGE(32, 32000),
    GARGANTUAN(64, 250000),
    COLOSSAL(Float.MAX_VALUE, Float.MAX_VALUE);

    private float maxSizeInFeet;
    private float maxWeightInPounds;

    Size(float maxSizeInFeet, float maxWeightInPounds) {
        this.maxSizeInFeet = maxSizeInFeet;
        this.maxWeightInPounds = maxWeightInPounds;
    }

    public static Size getSize(String size) {
        try {
            return Size.valueOf(size.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
