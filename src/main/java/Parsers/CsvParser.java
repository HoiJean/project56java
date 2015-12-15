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

        BufferedReader bufferedReader = null;
        String line;

        String[] data;

        ArrayList<String[]> output = new ArrayList<String[]>();

        try
        {
            bufferedReader = new BufferedReader(new FileReader(csvFile));


            while ( (line = bufferedReader.readLine()) != null)
            {
                data = line.split(SPLIT_BY);

                output.add(data);
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


        return output;
    }

}
