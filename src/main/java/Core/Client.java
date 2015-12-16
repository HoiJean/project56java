package Core;

import Data.Connections;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by Jean on 13-12-2015.
 */
public class Client {


    public static void main(String[] args) throws ParseException, SQLException {

        System.out.println("=========== Project 56 =============");

        new Connections().insert();

    }

}
