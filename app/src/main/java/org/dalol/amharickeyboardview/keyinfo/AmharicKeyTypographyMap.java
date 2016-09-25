package org.dalol.amharickeyboardview.keyinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/6/2016
 */
public enum AmharicKeyTypographyMap implements IKeyTypography {

    HU(AmharicKeyTypography.HA, '\u1201', 'ሁ', "ETHIOPIC SYLLABLE", "HU"),
    HI(AmharicKeyTypography.HA, '\u1202', 'ሂ', "ETHIOPIC SYLLABLE", "HI"),
    HAA(AmharicKeyTypography.HA, '\u1203', 'ሃ', "ETHIOPIC SYLLABLE", "HAA"),
    HEE(AmharicKeyTypography.HA, '\u1204', 'ሄ', "ETHIOPIC SYLLABLE", "HEE"),
    HE(AmharicKeyTypography.HA, '\u1205', 'ህ', "ETHIOPIC SYLLABLE", "HE"),
    HO(AmharicKeyTypography.HA, '\u1206', 'ሆ', "ETHIOPIC SYLLABLE", "HO"),
    HOA(AmharicKeyTypography.HA, '\u1207', 'ሇ', "ETHIOPIC SYLLABLE", "HOA"),
    LU(AmharicKeyTypography.LA, '\u1209', 'ሉ' , "ETHIOPIC SYLLABLE", "LU"),
    LI(AmharicKeyTypography.LA, '\u120A', 'ሊ' , "ETHIOPIC SYLLABLE", "LI"),
    LAA(AmharicKeyTypography.LA, '\u120B', 'ላ' , "ETHIOPIC SYLLABLE", "LAA"),
    LEE(AmharicKeyTypography.LA, '\u120C', 'ሌ' , "ETHIOPIC SYLLABLE", "LEE"),
    LE(AmharicKeyTypography.LA, '\u120D', 'ል' , "ETHIOPIC SYLLABLE", "LE"),
    LO(AmharicKeyTypography.LA, '\u120E', 'ሎ' , "ETHIOPIC SYLLABLE", "LO"),
    LWA(AmharicKeyTypography.LA, '\u120F', 'ሏ' , "ETHIOPIC SYLLABLE", "LWA");

    private final IKeyTypography parentTypography;
    private final char unicodeChar;
    private final char ch;
    private final String type;
    private final String pronunciation;

    AmharicKeyTypographyMap(IKeyTypography parentTypography,
                            char unicodeChar, char ch, String type, String pronunciation) {
        this.parentTypography = parentTypography;
        this.unicodeChar = unicodeChar;
        this.ch = ch;
        this.type = type;
        this.pronunciation = pronunciation;
    }

    @Override
    public Character getCharCodeValue() {
        return this.unicodeChar;
    }

    @Override
    public Character getCharCode() {
        return this.ch;
    }

    @Override
    public String getCharPronunciation() {
        return this.pronunciation;
    }

    public static List<IKeyTypography> getTypographyList(IKeyTypography typography) {
        List<IKeyTypography> typographyList = new ArrayList<>();
        AmharicKeyTypographyMap[] values = AmharicKeyTypographyMap.values();
        for (int i = 0; i < values.length; i++) {
            AmharicKeyTypographyMap value = values[i];
            if (value.parentTypography == typography) {
                typographyList.add(value);
            }
        }
        return typographyList;
    }

    @Override
    public String getType() {
        return type;
    }
}
