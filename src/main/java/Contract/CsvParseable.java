package Contract;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Jean on 15-12-2015.
 */
public interface CsvParseable {

    /**
     * Get the the file to work with
     *
     * @return String
     */
    String getFile();

    /**
     * Parse a line and return an SQL string
     * @return String
     */
    ArrayList<String[]> parseline();

    void insert() throws SQLException;
}
