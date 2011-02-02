package org.code_house.console.table;

import static org.fusesource.jansi.Ansi.Color.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.fusesource.jansi.AnsiConsole;

public class Test {

    public static void main(String[] args) throws Exception {
        AnsiConsole.systemInstall();

        DataTable table = new DataTable(System.out);
        table.setBorderStyle(new Style().bold().color(RED).invisible());

        Row headers = new Row(false);
        headers.setStyle(new Style().bold().color(GREEN));
        headers.addCell(new Cell("ID"));
        headers.addCell(new Cell("State"));
        headers.addCell(new Cell("Blueprint"));
        headers.addCell(new Cell("Level"));
        headers.addCell(new Cell("Name"));
        table.addRow(headers);

        Column idCol = new Column(3, true, HAlign.right);
        idCol.setStyle(new Style().bold());
        table.addColumn(idCol);

        table.addColumn(new Column(10, HAlign.center));
        Column bpColumn = new Column(12, HAlign.center);
        bpColumn.setStyleCalculator(new StyleCalculator() {
            public Style calculate(String values) {
                return values.equals("Failure") ? new Style().background(RED).bold().color(RED) : null;
            }
        });
        table.addColumn(bpColumn);
        table.addColumn(new Column(5, HAlign.right));
        table.addColumn(new Column(true));

//        table.setHeaderStyle(new Style().bold());

//        table.addColumn(new Column("ID", 3, /*Align.right, */ansi().a(INTENSITY_BOLD)));
//        Column column = new Column("State", /*Align.center, */10);
//        column.setFormatter(new StyleCalculator() {
//            public Ansi format(String data) {
//                if ("Active".equals(data.trim())) {
//                    return ansi().fg(GREEN).a(BLINK_FAST);
//                }
//                return null;
//            }
//        });
//
//        table.addColumn(column);
//        Column bp = new Column("Blueprint", 12);
//        bp.setFormatter(new StyleCalculator() {
//            public Ansi format(String data) {
//                if ("Created".equals(data.trim())) {
//                    return ansi().fg(GREEN);
//                } else if ("GracePeriod".equals(data.trim())) {
//                    return ansi().fg(YELLOW);
//                } else if ("Failure".equals(data.trim())) {
//                    return ansi().fg(RED).bg(RED).a(INTENSITY_BOLD);
//                }
//                return null;
//            }
//        });
//        bp.setAlign(HAlign.center);
//        table.addColumn(bp);
//        table.addColumn(new Column("Level", 5/*, Align.right*/));
//        table.addColumn(new Column("Name"));

        // load sample data
        BufferedReader stream = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
        String line;
        while ((line = stream.readLine()) != null) {
            String[] values = line.split(",");
            table.addRow(values);
        }

        Row row = new Row(false);
        Cell cell = new Cell("");
        cell.setColSpan(4);
        row.addCell(cell);
        Cell cell2 = new Cell("Fragments: 10, 11, 12");
        row.addCell(cell2);
        table.addRow(row);

        table.addRow(new String[] {"4", "Active", "Error","60","Spring Context Support (3.0.5.RELEASE)"});

        row = new Row(false);
        cell = new Cell("");
        cell.setColSpan(4);
        row.addCell(cell);
        row.addCell(new Cell("Hosts: 10, 20, 30"));
        table.addRow(row);

        table.flush();

    }

}
