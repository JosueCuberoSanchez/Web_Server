import java.awt.*;
import java.io.*;

/**
 * Created by Renato on 22/03/2018.
 */
public class LogManager {

    public LogManager() {
        //this.showLog();
    }

    public synchronized void write(String method, long time, String server, String refer, String url, String data)  {
        System.out.println(method+" "+time+" "+server+" "+" "+refer+" "+url+" "+" "+data);
    }

    private void showLog() {
        File htmlFile = new File("src/main/resources/LogTable.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
