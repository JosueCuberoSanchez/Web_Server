import java.awt.*;
import java.io.*;

/**
 * Created by Renato on 22/03/2018.
 */
public class LogManager {

    public LogManager() {
        this.showLog();
    }

    public synchronized void write(String method, String time, String server, String refer, String url, String data) throws IOException {
        //String injectCode = "<tr><td>" + method + "</td><td>" + time + "</td><td>" + server + "</td><td>" + refer + "</td><td>" + url + "</td><td>" + data + "</td></tr>";
        //File htmlFile = new File("src/main/resources/LogTable.html");
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
