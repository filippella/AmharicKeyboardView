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

package org.dalol.amharickeyboardlibrary.model.layoutmanager;

import org.dalol.amharickeyboardlibrary.base.BaseKeyboardLayoutManager;
import org.dalol.amharickeyboardlibrary.model.layoutmanager.properties.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/30/2016
 */
public class TableKeyboardManager extends BaseKeyboardLayoutManager {

    private List<Row> rows = new ArrayList<>();
    private int rowsCount;
    private int colsCount;
    private boolean keyGrid[][];

    public TableKeyboardManager(int rowsCount, int colsCount) {
        this.rowsCount = rowsCount;
        this.colsCount = colsCount;
        this.keyGrid = new boolean[rowsCount][colsCount];
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public int getColsCount() {
        return colsCount;
    }

    public void setColsCount(int colsCount) {
        this.colsCount = colsCount;
    }

    public boolean[][] getKeyGrid() {
        return keyGrid;
    }

    public void setKeyGrid(boolean[][] keyGrid) {
        this.keyGrid = keyGrid;
    }

    public void setKeyBoundaryOccupied(int x, int y) {
        this.keyGrid[x][y] = true;
    }
}
