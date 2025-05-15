package org.synergym.backend.entity;

public enum LanguageType {
    ENGLISH(1, "영어"),
    GERMAN(2, "독일어"),
    FRENCH(3, "프랑스어"),
    SPANISH(4, "스페인어"),
    ITALIAN(5, "이탈리아어"),
    DUTCH(6, "네덜란드어"),
    RUSSIAN(7, "러시아어"),
    CHINESE(8, "중국어"),
    JAPANESE(9, "일본어"),
    KOREAN(10, "한국어");

    private final int id;
    private final String displayName;

    LanguageType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public static String getDisplayNameById(int id) {
        for (LanguageType lt : values()) {
            if (lt.id == id) {
                return lt.displayName;
            }
        }
        return "Unknown";
    }
}
