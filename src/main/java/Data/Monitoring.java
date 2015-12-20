package Data;

import Contract.CsvParseable;
import Helper.Database;
import Helper.DateHelper;
import Parsers.CsvParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Jean on 19-12-2015.
 */
public class Monitoring implements CsvParseable {

    protected String filename = "Monitoring.csv";

    protected String tablename = "monitoring";

    // monitoring csv data positions
    private final int UNITID_POSITION = 0;
    private final int BEGINTIME_POSITION = 1;
    private final int ENDTIME_POSITION = 2;
    private final int TYPE_POSITION = 3;
    private final int MIN_POSITION = 4;
    private final int MAX_POSITION = 5;
    private final int SUM_POSITION = 6;

    public String getFile() {
        return filename;
    }

    public ArrayList<String[]> parseline() {
        ArrayList<String[]> parsedLines = new CsvParser().parseCSV(getFile());

        return parsedLines;
    }

    /**
     * Bulk insert
     * @throws SQLException
     */
    public void insert() throws SQLException {

        for(int i=1; i < parseline().size(); i++)
        {
            String[] line = parseline().get(i);
            long unitid = Long.parseLong(line[UNITID_POSITION]);

            LocalDateTime begintime = DateHelper.CombineDateAndTime(line[BEGINTIME_POSITION]);
            LocalDateTime endtime = DateHelper.CombineDateAndTime(line[ENDTIME_POSITION]);

            String type = line[TYPE_POSITION];
            long min = Long.parseLong(line[MIN_POSITION]);
            long max = Long.parseLong(line[MAX_POSITION]);
            double sum = Double.parseDouble(line[SUM_POSITION]);

            insertMonitoring(unitid, begintime, endtime, type, min, max, sum);

        }

        System.out.println( tablename.toUpperCase() + " insert Process complete!");

    }

    /**
     * Insert a single monitor record into the database
     * @param unitid
     * @param begintime
     * @param endtime
     * @param type
     * @param min
     * @param max
     * @param sum
     * @throws SQLException
     */
    private void insertMonitoring(long unitid, LocalDateTime begintime, LocalDateTime endtime, String type, long min, long max, double sum) throws SQLException {
        Connection con = null;

        try
        {
            con = Database.getMysqlConnection();

            String query = "INSERT INTO " + tablename + " (unit_id, begin_time, end_time, type, min, max, sum) values(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, unitid);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(begintime));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(endtime));
            preparedStatement.setString(4, type);
            preparedStatement.setLong(5, min);
            preparedStatement.setLong(6, max);
            preparedStatement.setDouble(7, sum);


            preparedStatement.execute();

        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            if( con != null )
            {
                con.close();
            }
        }
    }


}
