package Core;

import Data.Connections;
import Data.Events;
import Data.Monitoring;
import Data.Positions;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by Jean on 13-12-2015.
 */
public class Client {


    public static void main(String[] args) throws ParseException, SQLException {

        System.out.println("=========== Project 56 =============");
        JOptionPane.showMessageDialog(null, "CityGis");

        try
        {
            Positions positions = new Positions();
            positions.start();

            Connections connections = new Connections();
            connections.start();

            Events events = new Events();
            events.start();

            Monitoring monitoring = new Monitoring();
            monitoring.start();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }


    }

}
