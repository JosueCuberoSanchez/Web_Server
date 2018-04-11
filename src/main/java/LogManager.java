import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renato on 22/03/2018.
 */
public class LogManager {

    private List<PersistentData> registers;
    private PersistentData persistentData;

    public LogManager() {
        this.showLog();
        this.registers = new ArrayList<PersistentData>();
    }

    public synchronized void write(String method, long time, String server, String refer, String url, String data) {
        this.persistentData = new PersistentData(method, time, server, refer, url, data);
        this.registers.add(this.persistentData);
        this.parseToJson();
        System.out.println(method + " " + time + " " + server + " " + " " + refer + " " + url + " " + " " + data);
    }

    private void showLog() {
        File htmlFile = new File("src/main/resources/LogTable.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("src/main/resources/logRegister.json"), this.registers);
        } catch (IOException e) {
            System.out.println("Can't write in Json file");
        }
    }
}
