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
public class Positions implements CsvParseable {

    private String filename = "Positions.csv";

    // Connection csv data positions
    private final int DATE_POSITION = 0;
    private final int UNITID_POSITION = 1;
    private final int RDX_POSITION = 2;
    private final int RDY_POSITION = 3;
    private final int SPEED_POSITION = 4;
    private final int COURSE_POSITION = 5;
    private final int NUMSATEL_POSITION = 6;
    private final int HDOP_POSITION = 7;
    private final int QUALITY_POSITION = 8;

    public String getFile() {
        return filename;
    }

    public ArrayList<String[]> parseline() {
        ArrayList<String[]> parsedLines = new CsvParser().parseCSV(getFile());

        return parsedLines;
    }

    public void insert() {
        for(int i=1; i < parseline().size(); i++)
        {
            String[] line = parseline().get(i);

            LocalDateTime datetime = DateHelper.CombineDateAndTime(line[DATE_POSITION]);
            long unitid = Long.parseLong(line[UNITID_POSITION]);
            double rdx = Double.parseDouble(line[RDX_POSITION]);
            double rdy = Double.parseDouble(line[RDY_POSITION]);

            int speed = Integer.parseInt(line[SPEED_POSITION]);
            int course = Integer.parseInt(line[COURSE_POSITION]);
            int numsatel = Integer.parseInt(line[NUMSATEL_POSITION]);
            int hdop = Integer.parseInt(line[HDOP_POSITION]);
            String quality = line[QUALITY_POSITION];

//            System.out.println("Unit id: " + unitid);
//            System.out.println("RDX: " + rdx);
//            System.out.println("RDY: " + rdy);

            try {
                insertPosition(datetime, unitid, rdx, rdy, speed, course, numsatel, hdop, quality);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Positions insert Process complete!");
    }

    private void insertPosition(LocalDateTime datetime, long unitid, double rdx, double rdy, int speed, int course, int numsatel, int hdop, String quality) throws SQLException {
        Connection con = null;

        try
        {
            con = Database.getMysqlConnection();

            String query = "INSERT INTO positions(datetime, unit_id, rdx, rdy, speed, course, numsatel, hdop, quality) values(?, ?, ?, ?,?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(datetime));

            preparedStatement.setLong(2, unitid);
            preparedStatement.setDouble(3, rdx);
            preparedStatement.setDouble(4, rdy);

            preparedStatement.setInt(5, speed);
            preparedStatement.setInt(6, course);
            preparedStatement.setInt(7, numsatel);
            preparedStatement.setInt(8, hdop);
            preparedStatement.setString(9, quality);

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

    public static void main(String[] args)
    {
        new Positions().insert();
    }

}
