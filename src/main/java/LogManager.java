import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Renato on 22/03/2018.
 */
public class LogManager {

    public LogManager() {
        this.showLog();
    }

    public void write(String method, String time, String server, String refer, String url, String data){
    }

    private void showLog(){
        File htmlFile = new File("src/main/resources/LogTable.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
