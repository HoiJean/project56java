package Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Jean on 14-12-2015.
 */
public class DateHelper {
    
    public static LocalDateTime mergeDateAndTime(String date, String time)
    {
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYY-MM-dd H:m:s");

        LocalDateTime mydatetime = LocalDateTime.of(localDate, localTime);

        return mydatetime;
    }

    public static LocalDateTime CombineDateAndTime(String datetime)
    {
        String[] splitedDate = datetime.split(" ");
        String date = splitedDate[0];
        String time = splitedDate[1];

        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYY-MM-dd H:m:s");

        LocalDateTime mydatetime = LocalDateTime.of(localDate, localTime);

        return mydatetime;

    }
}
