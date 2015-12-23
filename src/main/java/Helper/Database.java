package Helper;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Created by Jean on 14-12-2015.
 */
public class Database {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/citygis";

    private static String USERNAME = "root";
    private static String PASSWORD = "root";

    static Properties properties = new Properties();
    static InputStream inputStream = null;

    public Database()
    {
        try {
            inputStream = new FileInputStream("config.properties");
            properties.load(inputStream);

            JDBC_CONNECTION_URL = "jdbc:mysql://"+ properties.get("dbhost") + ":3306/" + properties.get("dbname");
            USERNAME = (String) properties.get("dbusername");
            PASSWORD = (String) properties.get("dbpassword");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMysqlConnection() throws SQLException {

        try {
            inputStream = new FileInputStream("config.properties");
            properties.load(inputStream);

            JDBC_CONNECTION_URL = "jdbc:mysql://"+ properties.get("dbhost") + ":3306/" + properties.get("dbname");
            USERNAME = (String) properties.get("dbusername");
            PASSWORD = (String) properties.get("dbpassword");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "DB Error");
            e.printStackTrace();
        }


        return DriverManager.getConnection(JDBC_CONNECTION_URL, USERNAME, PASSWORD);
    }

    public static void insertConnection(LocalDateTime datetime, String port, int value, long unitid) throws SQLException, ParseException {

        Connection con = null;

        try
        {
            con = Database.getMysqlConnection();

            String query = "INSERT INTO connections(datetime, port, value, unit_id) values(?, ?, ?, ?)";

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
}
