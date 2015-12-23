package Core;

import Data.Connections;
import Data.Events;
import Data.Monitoring;
import Data.Positions;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by Jean on 13-12-2015.
 */
public class Client {


    public static void main(String[] args) throws ParseException, SQLException {

        System.out.println("=========== Project 56 =============");

        try
        {
            Connections connections = new Connections();
            connections.start();

            Events events = new Events();
            events.start();

            Monitoring monitoring = new Monitoring();
            monitoring.start();

            Positions positions = new Positions();
            positions.start();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }


    }

}
