import com.fasterxml.jackson.databind.ObjectMapper;

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
        this.registers = new ArrayList<PersistentData>();
    }

    public synchronized void write(String method, long time, String server, String refer, String url, String data) {
        this.persistentData = new PersistentData(method, time, server, refer, url, data);
        this.registers.add(this.persistentData);
        this.parseToJson();
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
