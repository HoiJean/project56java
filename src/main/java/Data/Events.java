package Data;

/**
 * Created by Jean on 19-12-2015.
 */
public class Events extends Connections{

    protected String filename = "Events.csv";
    protected String tablename = "events";

    public Events() {
        super.setFilename(filename);
        super.setTablename(tablename);
    }

}
