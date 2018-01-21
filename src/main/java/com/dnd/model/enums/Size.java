package com.dnd.model.enums;

import com.google.gson.annotations.SerializedName;

public enum Size {

    @SerializedName("Fine")
    FINE(.5f, .125f),
    @SerializedName("Diminutive")
    DIMINUTIVE(1, 1),
    @SerializedName("Tiny")
    TINY(2, 8),
    @SerializedName("Small")
    SMALL(4, 60),
    @SerializedName("Medium")
    MEDIUM(8, 500),
    @SerializedName("Large")
    LARGE(16, 4000),
    @SerializedName("Huge")
    HUGE(32, 32000),
    @SerializedName("Gargantuan")
    GARGANTUAN(64, 250000),
    @SerializedName("Colossal")
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
