import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Universidad de Costa Rica
 * Facultad de Ingenierías
 * Escuela de Ciencias de la Computación e Informática
 * Profesor Braulio Solano
 * @author Josué Cubero Sánchez, B42190
 * @author Renato Mainieri Sáenz, B54076
 * Primer Ciclo, 2018
 */

public class LogManager {

    private List<PersistentData> registers;
    private PersistentData persistentData;

    public LogManager() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.registers = mapper.readValue(new File("src/main/resources/logRegister.json"), List.class);
        } catch (IOException e) {
            System.out.println("Json file is empty");
            this.registers = new ArrayList<PersistentData>();
        }
        //this.showLog();
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

    private void showLog() {

    }
}
