/*
 * Copyright (c) 2016 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.amharickeyboardlibrary.keyboard.model;

import org.dalol.amharickeyboardlibrary.R;
import org.dalol.amharickeyboardlibrary.keyboard.base.BaseInputKeysInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 12/29/2016
 */
public class SymbolsTwoInputKeysInfo extends BaseInputKeysInfo {

    @Override
    public List<InputKeysRow> getKeysRowList() {
        if (inputKeysRows == null || inputKeysRows.isEmpty()) {
            populateKeys();
        }
        return inputKeysRows;
    }

    @Override
    public String[] getModifiers(String keyLabel) {
        return new String[0];
    }

    @Override
    public boolean isModifiersEnabled() {
        return false;
    }

    @Override
    public void clean() {
        inputKeysRows = null;
    }

    private List<InputKeysRow> inputKeysRows;

    {
        populateKeys();
    }

    private void populateKeys() {
        inputKeysRows = new ArrayList<>();

        InputKeysRow keysRow1 = new InputKeysRow();
        keysRow1.addKeyInfo(new KeyInfo("1", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("2", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("3", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("4", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("5", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("6", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("7", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("8", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("9", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("0", 1, true, KeyInfo.KEY_EVENT_NORMAL));

        InputKeysRow keysRow2 = new InputKeysRow();
        keysRow2.addKeyInfo(new KeyInfo("~", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("±", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("♤", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("♡", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("•", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("°", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("`", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("´", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("《", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("》", 1, true, KeyInfo.KEY_EVENT_NORMAL));

        InputKeysRow keysRow3 = new InputKeysRow();
        keysRow3.addKeyInfo(new KeyInfo("©", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("£", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("€", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("^", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("®", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("¥", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("₩", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("☆", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("♢", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("♧", 1, true, KeyInfo.KEY_EVENT_NORMAL));

        InputKeysRow keysRow4 = new InputKeysRow();

        keysRow4.addKeyInfo(new KeyInfo(R.mipmap.ic_keyboard_caps, 1, false, KeyInfo.KEY_EVENT_SYMBOLS_ONE).setPadding(8).selected());
        keysRow4.addKeyInfo(new KeyInfo("¡", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("□", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("■", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("¢", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("|", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("\\", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("¿", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo(R.mipmap.ic_backspace_white_24dp, 2, false, KeyInfo.KEY_EVENT_BACKSPACE).setPadding(12));

        InputKeysRow keysRow5 = new InputKeysRow();
        keysRow5.addKeyInfo(new KeyInfo("\u1200\u1201", 2, false, KeyInfo.KEY_EVENT_HAHU));
        keysRow5.addKeyInfo(new KeyInfo(ENGLISH_KEY_LABEL, 2, false, KeyInfo.KEY_EVENT_ENGLISH));
        keysRow5.addKeyInfo(new KeyInfo(R.mipmap.ic_space_bar_white_24dp, 3, false, KeyInfo.KEY_EVENT_SPACE).setPadding(9));
        keysRow5.addKeyInfo(new KeyInfo(",", 1, false, KeyInfo.KEY_EVENT_NORMAL));
        keysRow5.addKeyInfo(new KeyInfo(R.mipmap.ic_subdirectory_arrow_left, 1, false, KeyInfo.KEY_EVENT_NEW_LINE).setPadding(8));
        keysRow5.addKeyInfo(new KeyInfo(R.mipmap.ic_send, 1, false, KeyInfo.KEY_EVENT_ENTER).setPadding(8));

        inputKeysRows.add(keysRow1);
        inputKeysRows.add(keysRow2);
        inputKeysRows.add(keysRow3);
        inputKeysRows.add(keysRow4);
        inputKeysRows.add(keysRow5);
    }
}
