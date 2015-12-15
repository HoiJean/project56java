package Core;

import Helper.Database;
import Helper.DateHelper;
import Parsers.CsvParser;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Jean on 13-12-2015.
 */
public class Client {

    // Connection csv data positions
    private final int DATE_POSITION = 0;
    private final int VALUE_POSITION = 3;
    private final int PORT_POSITION = 2;
    private final int UNITID_POSITION = 1;

    private ArrayList<String[]> csvData = new ArrayList<String[]>();
    private final String[] CSV_FILES ={
            "Connections.csv"
    };

    public Client()
    {
        String csvFile = "Connections.csv";
        CsvParser reader = new CsvParser();
        csvData = reader.parseCSV(csvFile);

    }

    public static void main(String[] args) throws ParseException {

        System.out.println("=========== Project 56 =============");

        new Client().insertConnections();
    }

    public void start()
    {
        // Grab each csv file and parse it
        for(int i = 0; i <= CSV_FILES.length; i++)
        {

        }
    }

    /**
     * Insert connections into database
     * @throws ParseException
     */
    public void insertConnections() throws ParseException {
        System.out.println("Begin inserting connections...");

        for(int i = 1; i <= csvData.size() -1; i++)
        {
            String the_date = csvData.get(i)[DATE_POSITION];
            String the_port = csvData.get(i)[PORT_POSITION];
            int the_value = Integer.parseInt(csvData.get(i)[VALUE_POSITION]);
            long the_unitid = Long.parseLong( csvData.get(i)[UNITID_POSITION] );

            String[] splitedDate = the_date.split(" ");
            String date = splitedDate[0];
            String time = splitedDate[1];

            LocalDateTime datetime = DateHelper.mergeDateAndTime(date, time);

            try {
                Database.insertConnection( datetime , the_port, the_value, the_unitid);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        System.out.println("End inserting connections...");
    }

}
