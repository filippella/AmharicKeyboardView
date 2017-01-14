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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Filippo Engidashet &#60;filippo.eng@gmail.com&#62;
 * @version 1.0.0
 * @since 12/18/2016
 */
public class GeezInputKeysInfo extends BaseInputKeysInfo {

    public static final String NUMBER_KEY_LABEL = "?123";
    private List<InputKeysRow> inputKeysRows = new ArrayList<>();
    private Map<String, String[]> modifiersMap;

    @Override
    public List<InputKeysRow> getKeysRowList() {
        if (inputKeysRows == null || inputKeysRows.isEmpty()) {
            populateInputKeyRows();
        }
        return inputKeysRows;
    }

    private void populateInputKeyRows() {
        InputKeysRow keysRow1 = new InputKeysRow();

        keysRow1.addKeyInfo(new KeyInfo("\u1200", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1208", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1210", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1218", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1220", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1228", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1230", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1238", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1240", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow1.addKeyInfo(new KeyInfo("\u1250", 1, true, KeyInfo.KEY_EVENT_NORMAL));

        InputKeysRow keysRow2 = new InputKeysRow();
        keysRow2.addKeyInfo(new KeyInfo("\u1260", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u1268", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u1270", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u1278", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u1280", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u1290", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u1298", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u12A0", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u12A8", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow2.addKeyInfo(new KeyInfo("\u12B8", 1, true, KeyInfo.KEY_EVENT_NORMAL));

        InputKeysRow keysRow3 = new InputKeysRow();
        keysRow3.addKeyInfo(new KeyInfo("\u12C8", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u12D0", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u12D8", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u12E0", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u12E8", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u12F0", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u1300", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u1308", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u1320", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow3.addKeyInfo(new KeyInfo("\u1328", 1, true, KeyInfo.KEY_EVENT_NORMAL));

        InputKeysRow keysRow4 = new InputKeysRow();
        keysRow4.addKeyInfo(new KeyInfo("\u1330", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("\u1338", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("\u1340", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("\u1348", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("\u1350", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("\u1360", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("\u1369", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo("\u1373", 1, true, KeyInfo.KEY_EVENT_NORMAL));
        keysRow4.addKeyInfo(new KeyInfo(R.mipmap.ic_backspace_white_24dp, 2, false, KeyInfo.KEY_EVENT_BACKSPACE).setPadding(12));

        InputKeysRow keysRow5 = new InputKeysRow();
        keysRow5.addKeyInfo(new KeyInfo(NUMBER_KEY_LABEL, 2, false, KeyInfo.KEY_EVENT_SYMBOLS_ONE));
        keysRow5.addKeyInfo(new KeyInfo(ENGLISH_KEY_LABEL, 2, false, KeyInfo.KEY_EVENT_ENGLISH));
        keysRow5.addKeyInfo(new KeyInfo(R.mipmap.ic_space_bar_white_24dp, 3, false, KeyInfo.KEY_EVENT_SPACE).setPadding(9));
        keysRow5.addKeyInfo(new KeyInfo(R.mipmap.ic_keyboard_close, 1, false, KeyInfo.KEY_EVENT_CLOSE_KEYBOARD).setPadding(8));
        keysRow5.addKeyInfo(new KeyInfo(R.mipmap.ic_subdirectory_arrow_left, 1, false, KeyInfo.KEY_EVENT_NEW_LINE).setPadding(8));
        keysRow5.addKeyInfo(new KeyInfo(R.mipmap.ic_send, 1, false, KeyInfo.KEY_EVENT_ENTER).setPadding(8));

        inputKeysRows.add(keysRow1);
        inputKeysRows.add(keysRow2);
        inputKeysRows.add(keysRow3);
        inputKeysRows.add(keysRow4);
        inputKeysRows.add(keysRow5);
    }

    @Override
    public String[] getModifiers(String keyLabel) {

        if (keyLabel == null) {
            return null;
        }

        if (modifiersMap == null) {
            populateModifiers();
        }
        return modifiersMap.get(keyLabel);
    }

    private void populateModifiers() {
        modifiersMap = new HashMap<>();
        modifiersMap.put("\u1200", new String[]{"\u1200", "\u1201", "\u1202", "\u1203", "\u1204", "\u1205", "\u1206", "\u1207"});//HA
        modifiersMap.put("\u1208", new String[]{"\u1208", "\u1209", "\u120A", "\u120B", "\u120C", "\u120D", "\u120E", "\u120F"});//LE
        modifiersMap.put("\u1210", new String[]{"\u1210", "\u1211", "\u1212", "\u1213", "\u1214", "\u1215", "\u1216", "\u1217"});//HA
        modifiersMap.put("\u1218", new String[]{"\u1218", "\u1219", "\u121A", "\u121B", "\u121C", "\u121D", "\u121E", "\u121F"});//ME
        modifiersMap.put("\u1220", new String[]{"\u1220", "\u1221", "\u1222", "\u1223", "\u1224", "\u1225", "\u1226", "\u1227"});//SE
        modifiersMap.put("\u1228", new String[]{"\u1228", "\u1229", "\u122A", "\u122B", "\u122C", "\u122D", "\u122E", "\u122F"});//RE
        modifiersMap.put("\u1230", new String[]{"\u1230", "\u1231", "\u1232", "\u1233", "\u1234", "\u1235", "\u1236", "\u1237"});//SE
        modifiersMap.put("\u1238", new String[]{"\u1238", "\u1239", "\u123A", "\u123B", "\u123C", "\u123D", "\u123E", "\u123F"});//SHE
        modifiersMap.put("\u1240", new String[]{"\u1240", "\u1241", "\u1242", "\u1243", "\u1244", "\u1245", "\u1246", "\u124B"});//QE
        modifiersMap.put("\u1250", new String[]{"\u1250", "\u1251", "\u1252", "\u1253", "\u1254", "\u1255", "\u1256", "\u1258"});//BE

        modifiersMap.put("\u1260", new String[]{"\u1260", "\u1261", "\u1262", "\u1263", "\u1264", "\u1265", "\u1266", "\u1267"});//BE
        modifiersMap.put("\u1268", new String[]{"\u1268", "\u1269", "\u126A", "\u126B", "\u126C", "\u126D", "\u126E", "\u126F"});//VE
        modifiersMap.put("\u1270", new String[]{"\u1270", "\u1271", "\u1272", "\u1273", "\u1274", "\u1275", "\u1276", "\u1277"});//TE
        modifiersMap.put("\u1278", new String[]{"\u1278", "\u1279", "\u127A", "\u127B", "\u127C", "\u127D", "\u127E", "\u127F"});//CE
        modifiersMap.put("\u1280", new String[]{"\u1280", "\u1281", "\u1282", "\u1283", "\u1284", "\u1285", "\u1286", "\u1287"});//HA
        modifiersMap.put("\u1290", new String[]{"\u1290", "\u1291", "\u1292", "\u1293", "\u1294", "\u1295", "\u1296", "\u1297"});//NE
        modifiersMap.put("\u1298", new String[]{"\u1298", "\u1299", "\u129A", "\u129B", "\u129C", "\u129D", "\u129E", "\u129F"});//GNE
        modifiersMap.put("\u12A0", new String[]{"\u12A0", "\u12A1", "\u12A2", "\u12A3", "\u12A4", "\u12A5", "\u12A6", "\u12A7"});//A
        modifiersMap.put("\u12A8", new String[]{"\u12A8", "\u12A9", "\u12AA", "\u12AB", "\u12AC", "\u12AD", "\u12AE", "\u12B3"});//KE
        modifiersMap.put("\u12B8", new String[]{"\u12B8", "\u12B9", "\u12BA", "\u12BB", "\u12BC", "\u12BD", "\u12BE", "\u12C0"});//HE

        modifiersMap.put("\u12C8", new String[]{"\u12C8", "\u12C9", "\u12CA", "\u12CB", "\u12CC", "\u12CD", "\u12CE", "\u12CF"});//WE
        modifiersMap.put("\u12D0", new String[]{"\u12D0", "\u12D1", "\u12D2", "\u12D3", "\u12D4", "\u12D5", "\u12D6"});//AA
        modifiersMap.put("\u12D8", new String[]{"\u12D8", "\u12D9", "\u12DA", "\u12DB", "\u12DC", "\u12DD", "\u12DE", "\u12DF"});//ZE
        modifiersMap.put("\u12E0", new String[]{"\u12E0", "\u12E1", "\u12E2", "\u12E3", "\u12E4", "\u12E5", "\u12E6", "\u12E7"});//ZSE
        modifiersMap.put("\u12E8", new String[]{"\u12E8", "\u12E9", "\u12EA", "\u12EB", "\u12EC", "\u12ED", "\u12EE", "\u12EF"});//YE
        modifiersMap.put("\u12F0", new String[]{"\u12F0", "\u12F1", "\u12F2", "\u12F3", "\u12F4", "\u12F5", "\u12F6", "\u12F7"});//DE
        modifiersMap.put("\u1300", new String[]{"\u1300", "\u1301", "\u1302", "\u1303", "\u1304", "\u1305", "\u1306", "\u1307"});//JE
        modifiersMap.put("\u1308", new String[]{"\u1308", "\u1309", "\u130A", "\u130B", "\u130C", "\u130D", "\u130E", "\u1313"});//GE
        modifiersMap.put("\u1320", new String[]{"\u1320", "\u1321", "\u1322", "\u1323", "\u1324", "\u1325", "\u1326", "\u1327"});//THE
        modifiersMap.put("\u1328", new String[]{"\u1328", "\u1329", "\u132A", "\u132B", "\u132C", "\u132D", "\u132E", "\u132F"});//CHEE

        modifiersMap.put("\u1330", new String[]{"\u1330", "\u1331", "\u1332", "\u1333", "\u1334", "\u1335", "\u1336", "\u1337"});//PEE
        modifiersMap.put("\u1338", new String[]{"\u1338", "\u1339", "\u133A", "\u133B", "\u133C", "\u133D", "\u133E", "\u133F"});//TSE
        modifiersMap.put("\u1340", new String[]{"\u1340", "\u1341", "\u1342", "\u1343", "\u1344", "\u1345", "\u1346", "\u1347"});//TSEE
        modifiersMap.put("\u1348", new String[]{"\u1348", "\u1349", "\u134A", "\u134B", "\u134C", "\u134D", "\u134E", "\u134F"});//FE
        modifiersMap.put("\u1350", new String[]{"\u1350", "\u1351", "\u1352", "\u1353", "\u1354", "\u1355", "\u1356", "\u1357"});//PE
        modifiersMap.put("\u1360", new String[]{"\u1360", "\u1361", "\u1362", "\u1363", "\u1364", "\u1365", "\u1366", "\u1367", "\u1368"});//geez punctuations
        modifiersMap.put("\u1369", new String[]{"\u1369", "\u136A", "\u136B", "\u136C", "\u136D", "\u136E", "\u136F", "\u1370", "\u1371", "\u1372"});//1-10 geez
        modifiersMap.put("\u1373", new String[]{"\u1373", "\u1374", "\u1375", "\u1376", "\u1377", "\u1378", "\u1379", "\u137A", "\u137B", "\u137C"});//20-100
        modifiersMap.put("0", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});//1-10 english
    }

    @Override
    public boolean isModifiersEnabled() {
        return true;
    }

    @Override
    public void clean() {
        inputKeysRows = null;
        modifiersMap = null;
    }

    {
        populateInputKeyRows();
        populateModifiers();
    }
}
