import java.awt.*;
import java.io.*;

/**
 * Created by Renato on 22/03/2018.
 */
public class LogManager {

    private boolean isOpen;

    public LogManager() {
        this.isOpen = false;
    }

    public void write(String method, String time, String server, String refer, String url, String data) throws IOException {
        String injectCode = "<tr><td>"+method+"</td><td>"+time+"</td><td>"+server+"</td><td>"+refer+"</td><td>"+url+"</td><td>"+data+"</td></tr>";
        File htmlFile = new File("src/main/resources/LogTable.html");

        this.showLog();
    }

    private void showLog() {
        File htmlFile = new File("src/main/resources/LogTable.html");
        if (!this.isOpen) {
            try {
                Desktop.getDesktop().browse(htmlFile.toURI());
                this.isOpen = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
