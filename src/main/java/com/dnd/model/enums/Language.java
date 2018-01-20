package com.dnd.model.enums;

public enum Language {

    COMMON,
    DWARVISH,
    ELVISH,
    GIANT,
    GNOMISH,
    GOBLIN,
    HALFLING,
    ORC,
    ABYSSAL,
    CELESTIAL,
    DEEP_SPEECH,
    DRACONIC,
    INFERNAL,
    PRIMORDIAL,
    SYLVAN,
    UNDERCOMMON;

    public static Language getLanguage(String language) {
        return Language.valueOf(language.trim().replaceAll("[//s]+", " ").toUpperCase());
    }

}
