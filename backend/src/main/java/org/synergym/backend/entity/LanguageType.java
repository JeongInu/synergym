package org.synergym.backend.entity;

public enum LanguageType {
    DE(1, "독일어"),
    EN(2, "영어"),
    BG(3, "불가리아어"),
    ES(4, "스페인어"),
    RU(5, "러시아어"),
    NL(6, "네덜란드어"),
    PT(7, "포르투갈어"),
    EL(8, "그리스어"),
    CS(9, "체코어"),
    SV(10, "스웨덴어"),
    NO(11, "노르웨이어"),
    FR(12, "프랑스어"),
    IT(13, "이탈리아어"),
    PL(14, "폴란드어"),
    UK(15, "우크라이나어"),
    TR(16, "터키어"),
    AZ(18, "아제르바이잔어"),
    EO(19, "에스페란토"),
    HR(22, "크로아티아어"),
    ID(23, "인도네시아어");

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
