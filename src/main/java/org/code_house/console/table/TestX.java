package org.code_house.console.table;

import org.fusesource.jansi.AnsiConsole;

public class TestX {

    public static void main(String[] args) throws Exception {
        AnsiConsole.systemInstall();

        DataTable table = new DataTable(System.out);

        table.addColumn(new Column(3, true));
        table.addColumn(new Column(5));
        table.addColumn(new Column(12));
        table.addColumn(new Column(12));
        table.addColumn(new Column(5));
        table.addColumn(new Column(true));

        Row row = new Row();
        row.addCell(new Cell("OSGi", HAlign.center, 2));
        row.addCell(new Cell("Extender", HAlign.center, 2));
        row.addCell(new Cell("Misc", 2));
        table.addRow(row);

        row = new Row();
        row.addCell("ID");
        row.addCell("State");
        row.addCell("Spring");
        row.addCell("Blueprint");
        row.addCell("Level");
        row.addCell("Name");
        table.addRow(row);

        // load sample data
        for (int i = 0; i < 5; i++) {
            table.addRow(new Object[] {i, i, i, i, i, i});
        }
        table.flush();

    }

}
