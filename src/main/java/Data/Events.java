package Data;

/**
 * Created by Jean on 19-12-2015.
 */
public class Events extends Connections{

    protected String filename = "Events.csv";
    protected String tablename = "events";
    protected boolean alreadyParsed = false;

    public Events() {
        super.setFilename(filename);
        super.setTablename(tablename);
    }

    public void start()
    {


        if( thread == null )
        {
            System.out.println("Start Event Thread");
            try
            {
                thread = new Thread(new Events());
//                thread.setPriority(Thread.MAX_PRIORITY);
                thread.start();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

}
