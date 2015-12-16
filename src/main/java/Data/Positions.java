package Data;

import Contract.CsvParseable;
import Parsers.CsvParser;

import java.util.ArrayList;

/**
 * Created by Jean on 16-12-2015.
 */
public class Positions implements CsvParseable {

    private String filename = "Connections.csv";

    // Connection csv data positions
    private final int DATE_POSITION = 0;
    private final int VALUE_POSITION = 3;
    private final int PORT_POSITION = 2;
    private final int UNITID_POSITION = 1;

    public String getFile() {
        return filename;
    }

    public ArrayList<String[]> parseline() {
        ArrayList<String[]> parsedLines = new CsvParser().parseCSV(getFile());

        return parsedLines;
    }

    public void insert() {

    }

}
