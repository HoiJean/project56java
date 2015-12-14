package Helper;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * Created by Jean on 14-12-2015.
 */
public class Database {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //    private static final String JDBC_CONNECTION_URL =
    private static final String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/citygis";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static Connection getMysqlConnection() throws SQLException {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return DriverManager.getConnection(JDBC_CONNECTION_URL, USERNAME, PASSWORD);
    }

    public static void insertConnection(LocalDateTime datetime, String port, String value, int unitid) throws SQLException, ParseException {

        Connection con = null;

        try
        {
            con = Database.getMysqlConnection();

            String query = "INSERT INTO connections(datetime, port, value, unit_id) values(?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(datetime));
            preparedStatement.setInt(2, Integer.valueOf("4211"));
            preparedStatement.setString(3, value);
            preparedStatement.setInt(4, Integer.valueOf(unitid));

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
