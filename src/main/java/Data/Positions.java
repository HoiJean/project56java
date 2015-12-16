package Data;

import Contract.CsvParseable;
import Parsers.CsvParser;

import java.util.ArrayList;

/**
 * Created by Jean on 16-12-2015.
 */
public class Positions implements CsvParseable {

    public String getFile() {
        return null;
    }

    public ArrayList<String[]> parseline() {
        ArrayList<String[]> parsedLines = new CsvParser().parseCSV(getFile());

        return parsedLines;
    }

    public void insert() {

    }

}
