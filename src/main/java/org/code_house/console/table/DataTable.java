/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.code_house.console.table;

import static org.code_house.console.table.StringUtil.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple data table.
 * 
 * @author ldywicki
 */
public class DataTable extends TableElement {

    private final Appendable target;
    private Style borderStyle = new Style();

    private List<Column> columns = new ArrayList<Column>();
    private List<Row> rows = new ArrayList<Row>();

    public DataTable(Appendable target) {
        this.target = target;
    }

    public DataTable(PrintStream out, int colCount) {
        this(out);

        for (int i = 0; i < colCount; i++) {
            addColumn(new Column(true));
        }
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

    private void printBorder(Row row, String border) {
        if (row.isBorders()) {
            append(borderStyle.apply(border));
        } else {
            append(repeat(" ", border.length()));
        }
    }

    public void beign() {
//        if (rows.isEmpty()) {
//            return;
//        }
//
//        append("\n");
//        printRows();
//
//        rows.clear();
    }

    public void end() {
        printRows();
        rows.clear();
    }

    private void printRows() {
        for (Row row : rows) {
            List<Cell> cells = row.getCells();

            int colSpanCount = 0;
            for (int i = 0, size = cells.size(); i < size; i++) {
                Cell cell = cells.get(i);
                int colSpan = cell.getColSpan();

                Column column = columns.get(colSpanCount + i);
                int colSize = column.getSize();

                if (colSpan > 0) {
                    for (int j = 0; j < colSpan; j++) {
                        colSize += columns.get(i + j).getSize();
                    }
                    if (colSpanCount > 0) {
                        colSize += 3;
                    }
                    colSpanCount += colSpan;
                    colSize += 2 * colSpanCount;
                    colSize += 2;
                }

                boolean first = i == 0;
                boolean last = i + 1 == size;

                Style style = calculateStyle(column, row, cell);

                if (first) {
                    printBorder(row, "[");
                } else if (!last) {
                    printBorder(row, "][");
                } else {
                    printBorder(row, "] ");
                }

                String value = cell.getValue();

                if (value.length() > colSize) {
                    if (column.isMayGrow()) {
                        column.setSize(value.length());
                    } else {
                        value = value.substring(value.length() - 2) + "..";
                    }
                } 

                append(style.apply(column.getAlign().position(value, colSize)));

            }
            append("\n");
        }
    }

    private Style calculateStyle(Column column, Row row, Cell cell) {
        StyleCalculator styleCalculator = column.getStyleCalculator();
        Style dynamic = null;

        if (styleCalculator != null) {
            dynamic = styleCalculator.calculate(cell.getValue());
        }

        if (!cell.getStyle().isClean()) {
            return cell.getStyle();
        } else if (dynamic != null) {
            return dynamic;
        } else if (!row.getStyle().isClean()) {
            return row.getStyle();
        } else if (!column.getStyle().isClean()) {
            return column.getStyle();
        }
        return new Style();
    }

    private void append(String string) {
        try {
            target.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRow(Object[] row) {
        addRow(new Row(row));
    }

    public void addRow(Row row) {
        rows.add(row);

        if ((rows.size() % 10) == 0) {
            printRows();
            rows.clear();
        }
    }

    public void setBorderStyle(Style style) {
        this.borderStyle = style;
    }

}
