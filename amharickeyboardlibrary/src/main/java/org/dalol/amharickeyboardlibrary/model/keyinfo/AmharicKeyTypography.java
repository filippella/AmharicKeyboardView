package org.dalol.amharickeyboardlibrary.model.keyinfo;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/6/2016
 */
public enum AmharicKeyTypography implements IKeyTypography {

    HA('\u1200', 'ሀ', "ETHIOPIC SYLLABLE", "HA"),
    LA('\u1208', 'ለ', "ETHIOPIC SYLLABLE", "LA"),
    HHA('\u1210', 'ሐ', "ETHIOPIC SYLLABLE", "HHA");

    private final char charCode;
    private final String pronounciation;
    private final String type;
    private final char ch;

    AmharicKeyTypography(char charCode, char ch, String type, String pronounciation) {
        this.charCode = charCode;
        this.ch = ch;
        this.type = type;
        this.pronounciation = pronounciation;
    }

    @Override
    public Character getCharCodeValue() {
        return charCode;
    }

    @Override
    public Character getCharCode() {
        return ch;
    }

    @Override
    public String getCharPronunciation() {
        return pronounciation;
    }

    @Override
    public String getType() {
        return type;
    }
}
