package org.dalol.amharickeyboardlibrary.keyinfo;

import java.io.Serializable;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/6/2016
 */
public interface IKeyTypography extends Serializable {

    Character getCharCodeValue();

    Character getCharCode();

    String getCharPronunciation();

    String getType();
}
