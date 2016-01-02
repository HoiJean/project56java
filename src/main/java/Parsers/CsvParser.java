package Parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jean on 13-12-2015.
 */
public class CsvParser {

    protected final String SPLIT_BY = ";";

    public ArrayList<String[]> parseCSV(String csvFile)
    {
        System.out.println("Parsing csv file");
        BufferedReader bufferedReader = null;
        String line;

        String[] data;

        ArrayList<String[]> output = new ArrayList<String[]>();

        try
        {
            bufferedReader = new BufferedReader(new FileReader(csvFile), 1024);

            while ( (line = bufferedReader.readLine()) != null)
            {
                try
                {
                    data = line.split(SPLIT_BY);
                    output.add(data);
                }
                catch (OutOfMemoryError e)
                {
                    e.printStackTrace();
                }

            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if( bufferedReader != null )
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done parsing");

        return output;
    }


}
