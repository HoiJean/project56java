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
 * Created by Jean on 16-12-2015.
 */
public class Connections implements CsvParseable, Runnable {

    protected String filename = "Connections.csv";

    protected String tablename = "connections";

    private Thread thread;

    // Connections/events csv data positions
    private final int DATE_POSITION = 0;
    private final int VALUE_POSITION = 3;
    private final int PORT_POSITION = 2;
    private final int UNITID_POSITION = 1;

    public Connections() {

    }

    public Connections(String filename, String tablename) {
        this.filename = filename;
        this.tablename = tablename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getFile() {
        return filename;
    }

    public ArrayList<String[]> parseline() {
        ArrayList<String[]> parsedLines = new CsvParser().parseCSV(getFile());

        return parsedLines;
    }

    /**
     * Insert a single connection into the database
     * @param datetime
     * @param port
     * @param value
     * @param unitid
     * @throws SQLException
     */
    private void insertConnection(LocalDateTime datetime, String port, int value, long unitid) throws SQLException {
        Connection con = null;

        try
        {
            con = Database.getMysqlConnection();

            String query = "INSERT INTO " + tablename + " (datetime, port, value, unit_id) values(?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(datetime));
            preparedStatement.setString(2, port);
            preparedStatement.setInt(3, value);
            preparedStatement.setLong(4, unitid);

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

    public void run() {
        for(int i=1; i < parseline().size(); i++)
        {
            String[] line = parseline().get(i);

            LocalDateTime datetime = DateHelper.CombineDateAndTime(line[DATE_POSITION]);
            String port = line[PORT_POSITION];
            int value = Integer.parseInt(line[VALUE_POSITION]);
            long unitid = Long.parseLong(line[UNITID_POSITION]);

            try {
                insertConnection(datetime, port, value, unitid);
                Thread.sleep(50);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println( tablename.toUpperCase() + " insert Process complete!");
    }

    public void start()
    {
        if( thread == null)
        {
            thread = new Thread(this);
            thread.start();
        }
    }
}
