package Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Jean on 14-12-2015.
 */
public class DateHelperTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMergeDateAndTime() throws Exception {
        String date = "2015-02-10";
        String time = "13:07:01";
        
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYY-MM-dd H:m:s");

        LocalDateTime expected = LocalDateTime.of(localDate, localTime);
        
        System.out.println("DateTime: " + expected);
        
        Assert.assertTrue(expected instanceof LocalDateTime);
    }

    /**
     * Test of CombineDateAndTime method, of class DateHelper.
     */
    @Test
    public void testCombineDateAndTime() {
        String datetime = "2015-11-01 20:03:13";
        String expResult = "2015-11-01T20:03:13";
        LocalDateTime result = DateHelper.CombineDateAndTime(datetime);
        assertEquals(expResult, result.toString());
        // TODO review the generated test code and remove the default call to fail.
    }
}