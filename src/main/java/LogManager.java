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

    public void write(String method, String time, String server, String refer, String url, String data) {
        this.showLog();

    }


    private void showLog() {
        String htmlFileName = "src/main/resources/LogTable.html";
        File htmlFile = new File(htmlFileName);
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
