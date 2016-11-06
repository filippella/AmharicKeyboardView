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

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/1/2016
 */
public class Backup {
//    boolean flag = false;
//
//    for (int i = 0; i < rowsCount; i++) {
//        if (flag) break;
//        for (int j = 0; j < colsCount; j++) {
//
//            if ((j + childColumnCount) >= colsCount) {
//                continue;
//            }
//
//            if ((i + childRowCount) >= rowsCount) {
//                continue;
//            }
//
//            boolean bb = true, otherCheck, ijj, wasTrue = false;
//
//            for (int k = i; k < (i + childRowCount); k++) {
//                for (int l = j; l < (j + childColumnCount); l++) {
//                    if (!mLayoutManager.getKeyGrid()[k][l]) {
//                        wasTrue = true;
//                        bb = true;
//                    } else {
//                        bb = false;
//                    }
//                }
//            }
//
//            if (bb) {
//                for (int k = i; k < (i + childRowCount); k++) {
//                    for (int l = j; l < (j + childColumnCount); l++) {
//                        mLayoutManager.setKeyBoundaryOccupied(k, l);
//                    }
//                }
//
//                button.setTop(i);
//
//                int left = 0;
//
//                if (buttonsColCount > 0) {
//                    KeyButton previousKeyButton = keyButtons.get(buttonsColCount - 1);
//                    left = previousKeyButton.getLeft() + previousKeyButton.getColumnCount();
//                }
//
//                button.setLeft(left + i);
//                keyButtons.add(button);
//                flag = true;
//                break;
//            }


//                if (!mLayoutManager.getKeyGrid()[i][j] && !mLayoutManager.getKeyGrid()[i + childRowCount][j + childColumnCount]) {
//                    for (int k = i; k < (i + childRowCount); k++) {
//                        for (int l = j; l < (j + childColumnCount); l++) {
//                            mLayoutManager.setKeyBoundaryOccupied(k, l);
//                        }
//                    }
//                    button.setLeft(i);
//                    button.setTop(j);
//                    flag = true;
//                    break;
//                }

//
//
//                int left = i + childColumnCount;
//                int top = j + childRowCount;
//                if(left > rowsCount || top > colsCount) {
//                    if (!mLayoutManager.getKeyGrid()[i][j]) {
//                        for (int k = i; k < left; k++) {
//                            for (int l = j; l < top; l++) {
//                                mLayoutManager.setKeyBoundaryOccupied(k, l);
//                            }
//                        }
//                        button.setLeft(left);
//                        button.setTop(top);
//                    }
//                } else {
//                    if (!mLayoutManager.getKeyGrid()[i][j] && !mLayoutManager.getKeyGrid()[left][top]) {
//                        for (int k = i; k < left; k++) {
//                            for (int l = j; l < top; l++) {
//                                mLayoutManager.setKeyBoundaryOccupied(k, l);
//                            }
//                        }
//                        button.setLeft(left);
//                        button.setTop(top);
//                    }
//                }
//
//            }
//        }
//    }

//}
}
