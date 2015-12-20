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

        new Monitoring().insert();
        new Connections().insert();
        new Events().insert();
        new Positions().insert();

    }

}
