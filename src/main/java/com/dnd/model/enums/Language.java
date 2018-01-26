package com.dnd.model.enums;

import java.util.ArrayList;
import java.util.List;

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
        try {
            return Language.valueOf(language.trim().replaceAll("[\\s]+", "_").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static List<Language> getLanguageList(String languages) {
        List<Language> languageList = new ArrayList<Language>();
        String[] split = languages.split(",");
        for (String s : split) {
            languageList.add(getLanguage(s));
        }
        return languageList;
    }

}
