/*
 * Copyright (c) 2016 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.amharickeyboardlibrary.model.layoutmanager.properties;

import android.util.Log;

import org.dalol.amharickeyboardlibrary.model.layoutmanager.TableKeyboardManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/30/2016
 */
public class Row {

    private static final String TAG = Row.class.getSimpleName();
    private final TableKeyboardManager mLayoutManager;
    private final List<Row> mRows;
    private List<KeyButton> keyButtons = new ArrayList<>();

    public Row(TableKeyboardManager layoutManager) {
        //this.grid = layoutManager;
        mLayoutManager = layoutManager;
        mRows = mLayoutManager.getRows();
    }

    public void addKeyButton(KeyButton button) {
        int buttonsColCount = keyButtons.size();
        int rowsCount = mRows.size();

        //int rowsCount = mLayoutManager.getRowsCount();
        int colsCount = mLayoutManager.getColsCount();

        int childColumnCount = button.getColumnCount();
        int childRowCount = button.getRowCount();




        int rowCount = button.getRowCount();
        int columnCount = button.getColumnCount();

        if (rowsCount > 0) {
            int left = 0;
            int top = 0;

            int maxLeft;
            int maxTop;

            for (int i = 0; i < rowsCount; i++) {
                Row row = mRows.get(i);
                List<KeyButton> rowKeyButtons = row.getKeyButtons();

                Log.d(TAG, String.format("All Keys [%d][%d][%d]", i, mRows.size(), buttonsColCount));

                for (int j = 0; j < rowKeyButtons.size(); j++) {
                    KeyButton keyButton = rowKeyButtons.get(j);

                    Log.d(TAG, String.format("KeyButton [%d][%d][%d][%d][%d][%d]", i, mRows.size(), keyButtons.size(),
                            keyButton.getColumnCount(), keyButton.getRowCount(), rowsCount));

                    if (rowsCount == keyButton.getRowCount()) {

                        for (int k = i; k < (i + childRowCount); k++) {
                            for (int l = j; l < (j + childColumnCount); l++) {
                                mLayoutManager.setKeyBoundaryOccupied(k, l);
                            }
                        }


//                        left = keyButton.getColumnCount();
//
                        if (buttonsColCount > 0) {
                            KeyButton previousKeyButton = keyButtons.get(buttonsColCount - 1);
                            button.setLeft(left + previousKeyButton.getLeft() + previousKeyButton.getColumnCount());
                        } else {
                            button.setLeft(left);
                        }
                    }
                }
            }

//            if (cells.size() > 0) {
//                // Set cell column and row.
//                Cell lastCell = cells.get(cells.size() - 1);
//                if (!lastCell.endRow) {
//                    cell.column = lastCell.column + lastCell.colspan;
//                    cell.row = lastCell.row;
//                } else {
//                    cell.column = 0;
//                    cell.row = lastCell.row + 1;
//                }
//                // Set the index of the cell above.
//                if (cell.row > 0) {
//                    outer:
//                    for (int i = cells.size() - 1; i >= 0; i--) {
//                        Cell other = cells.get(i);
//                        for (int column = other.column, nn = column + other.colspan; column < nn; column++) {
//                            if (column == cell.column) {
//                                cell.cellAboveIndex = i;
//                                break outer;
//                            }
//                        }
//                    }
//                }
//            } else {
//                cell.column = 0;
//                cell.row = 0;
//            }

            Log.d(TAG, "LeftRow -> " + left + " ** [" + button.getColumnCount() + "][" + rowCount + "] RowCount -- " + rowsCount);

//            if (buttonsColCount > 0) {
//                KeyButton previousKeyButton = keyButtons.get(buttonsColCount - 1);
//                button.setLeft(left + previousKeyButton.getLeft() + previousKeyButton.getColumnCount());
//            } else {
//                button.setLeft(left);
//            }
        }
    else if (buttonsColCount > 0) {

            KeyButton previousKeyButton = keyButtons.get(buttonsColCount - 1);
            int left = previousKeyButton.getLeft() + previousKeyButton.getColumnCount();

//            int currLeft = previousKeyButton.getLeft() ;
//
//            for (int i = currLeft; i < (currLeft + columnCount); i++) {
//                for (int j = 0; j < rowCount; j++) {
//                    mLayoutManager.setKeyBoundaryOccupied(i, j);
//                }
//            }

            button.setLeft(left);
            button.setTop(0);
            Log.d(TAG, "LeftRow else if -> " + left + " ** [" + button.getColumnCount() + "][" + rowCount + "] RowCount -- " + rowsCount);
        } else {
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    mLayoutManager.setKeyBoundaryOccupied(i, j);
                }
            }
            button.setLeft(0);
            button.setTop(0);
            Log.d(TAG, "LeftRow else -> " + 0 + " ** [" + button.getColumnCount() + "][" + rowCount + "] RowCount -- " + rowsCount);
        }
        keyButtons.add(button);
    }

    public List<KeyButton> getKeyButtons() {
        return keyButtons;
    }
}
